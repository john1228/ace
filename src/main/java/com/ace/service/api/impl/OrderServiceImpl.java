package com.ace.service.api.impl;

import com.ace.dao.*;
import com.ace.entity.*;
import com.ace.entity.concern.Payment;
import com.ace.entity.concern.Period;
import com.ace.entity.concern.enums.CouponStatus;
import com.ace.entity.concern.enums.OrderStatus;
import com.ace.entity.concern.enums.RoomRental;
import com.ace.entity.concern.enums.Week;
import com.ace.service.api.OrderService;
import com.ace.service.concerns.OrderTools;
import com.ace.service.concerns.RoomTools;
import com.ace.util.AlipayBuilder;
import com.ace.util.wxpay.WxpayBuilder;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service("api_order_service")
public class OrderServiceImpl extends BaseService implements OrderService {

    @Resource
    private RoomMapper roomMapper;
    @Resource
    private RoomSupportMapper rsMapper;
    @Resource
    private RoomClosedMapper rcMapper;
    @Resource
    private MemberCouponMapper mcMapper;
    @Resource
    private PriceMapper priceMapper;
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private AppointmentMapper appointmentMapper;
    @Resource
    private OrderSupportMapper orderSupportMapper;
    @Resource
    private RedisTemplate<String, Period> redisTemplate;
    @Resource
    private OrderTools orderTools;
    @Resource
    private RoomTools roomTools;
    @Resource
    private ReceiptMapper receiptMapper;
    @Resource
    private SettingMapper settingMapper;

    @Override
    public List<Order> customerOrder(Account account, OrderStatus status, int page) {
        return orderMapper.customerOrder(account, status, page, pageSize);
    }

    @Override
    public List<Order> supplierOrder(Account account, String status, int page) {
        return orderMapper.supplierOrder(account, status, page, pageSize);
    }

    @Override
    @Transactional
    public Order create(Account account, Appointment appointment, Long couponId) {
        if (appointmentMapper.isExists(appointment.getRoomId(), appointment.getStartTime(), appointment.getEndTime())) {
            account.setErrMsg("已经有客户预约了该时间段");
            return null;
        } else {
            Room room = roomMapper.findById(appointment.getRoomId());
            Order order = new Order(account.getAccountId(), account.getAccountName());
            SimpleDateFormat wf = new SimpleDateFormat("EEEE", Locale.ENGLISH);
            SimpleDateFormat hf = new SimpleDateFormat("HH:mm");
            Date appointedDate = new Date(appointment.getStartTime().getTime());
            Week week = Week.valueOf(wf.format(appointedDate).toUpperCase());
            //校验时间是否有效
            List<RoomClosed> closedList = rcMapper.closedList(Arrays.asList(new Room[]{room}), appointedDate);
            if (roomTools.check(closedList, appointment.getStartTime(), appointment.getEndTime())) {
                account.setErrMsg("该时间段不开放预约");
                return null;
            }
            //预约时间检测
            Long units = (appointment.getEndTime().getTime() - appointment.getStartTime().getTime()) / (30 * 60 * 1000);
            if (room.getUnit() > units) {
                account.setErrMsg("您租借的时间太短，最少租用时长为" + room.getUnit() * 30 + "分钟");
                return null;
            }
            //价格计算
            List<Price> prices = priceMapper.prices(appointment.getRoomId(), appointedDate);
            Optional<Price> optional = orderTools.fittedPrice(prices, appointment.getStartTime(), appointment.getEndTime(), room.getRental());
            if (optional.isPresent()) {
                Price price = optional.get();
                //计算总价
                if (price.getRental().equals(RoomRental.PERIOD)) {
                    order.setTotal(price.getPrice());
                } else {
                    order.setTotal(price.getPrice().multiply(new BigDecimal(units.intValue())));
                }
                //校验优惠券
                if (couponId != 0) {
                    Date date = new Date(System.currentTimeMillis());
                    MemberCoupon coupon = mcMapper.findById(couponId);
                    if (date.before(coupon.getStartDate())) {
                        account.setErrMsg("优惠券还未到使用时间");
                        return null;
                    } else if (date.after(coupon.getEndDate())) {
                        account.setErrMsg("优惠券已经过期");
                        return null;
                    } else {
                        if (!coupon.getLimitWday().contains(week)) {
                            account.setErrMsg("优惠券不能用于该日期");
                            return null;
                        } else if (!coupon.getLimitRoom().contains(appointment.getRoomId())) {
                            account.setErrMsg("优惠券不能用于该会议室");
                            return null;
                        } else {
                            order.setCoupon(new BigDecimal(coupon.getDiscount()));
                        }
                    }
                    //TODO:更新优惠券状态
                    mcMapper.use(couponId, CouponStatus.USED);
                }
                //计算服务费用
                List<RoomSupport> roomSupports = rsMapper.supportList(room.getId());
                StringBuilder errMsg = new StringBuilder();
                BigDecimal supportFee = orderTools.cacl(appointment.getService(), roomSupports, errMsg);
                if (errMsg.length() != 0) {
                    account.setErrMsg(errMsg.toString());
                    return null;
                }
                order.setTotal(order.getTotal().add(supportFee));
                order.setPayAmount(order.getTotal().subtract(order.getCoupon()));


                if (order.getPayAmount().compareTo(new BigDecimal(0)) == 0) {
                    switch (room.getCfm()) {
                        case AUTO:
                            order.setStatus(OrderStatus.PAIDANDCONFIRM);
                            break;
                        case AFTER:
                            order.setStatus(OrderStatus.PAID2CONFIRM);
                            break;
                        case BEFORE:
                            order.setStatus(OrderStatus.PAID2CONFIRM);
                            break;
                    }

                } else {
                    switch (room.getCfm()) {
                        case AUTO:
                            order.setStatus(OrderStatus.CONFIRM2PAID);
                            break;
                        case AFTER:
                            order.setStatus(OrderStatus.UNPAID2CONFIRM);
                            break;
                        case BEFORE:
                            order.setStatus(OrderStatus.UNPAID2CONFIRM);
                            break;
                    }
                }

                orderMapper.create(order);
                Long orderId = order.getId();
                //创建预约
                appointment.setOrderId(order.getId());
                appointmentMapper.create(appointment);
                //创建服务
                if (appointment.getService().size() > 0) {
                    appointment.getService().forEach(service -> service.setOrderId(orderId));
                    orderSupportMapper.create(appointment.getService());
                }
                //缓存预约时间
                redisTemplate.opsForSet().add("ROOM::" + appointment.getRoomId() + "::APPOINTED::" + appointedDate.toString(), new Period(
                        hf.format(appointment.getStartTime()),
                        hf.format(appointment.getEndTime())
                ));

                order = orderMapper.findById(order.getId());
                if (order.getStatus().equals(OrderStatus.UNPAID2CONFIRM) || order.getStatus().equals(OrderStatus.CONFIRM2PAID)) {
                    //支付宝
                    Alipay alipay = settingMapper.alipay(room.getProjectId());
                    Wxpay wxpay = settingMapper.wxpay(room.getProjectId());
                    Payment payment = new Payment();
                    if (alipay != null) {
                        payment.setAlipay(AlipayBuilder.instance.getPay(alipay, order));
                    }
                    if (wxpay != null) {
                        payment.setWxpay(WxpayBuilder.instance.getPay(wxpay, order));
                    }
                    order.setPayment(payment);
                }
                return order;
            } else {
                account.setErrMsg("该时间段未开放预约");
                return null;
            }
        }
    }

    @Override
    public Order show(Account account, String orderNo) {
        Order order = orderMapper.findByOrderNo(orderNo);
        Appointment appointment = appointmentMapper.selectByOrder(order.getId());
        List<OrderSupport> supportList = orderSupportMapper.supportList(order.getId());
        appointment.setService(supportList);
        order.setAppointment(appointment);
        if (order.getStatus().equals(OrderStatus.UNPAID2CONFIRM) || order.getStatus().equals(OrderStatus.CONFIRM2PAID)) {
            //支付宝
            Room room = roomMapper.findById(appointment.getRoomId());
            Alipay alipay = settingMapper.alipay(room.getProjectId());
            Wxpay wxpay = settingMapper.wxpay(room.getProjectId());
            Payment payment = new Payment();
            if (alipay != null) {
                payment.setAlipay(AlipayBuilder.instance.getPay(alipay, order));
            }
            if (wxpay != null) {
                payment.setWxpay(WxpayBuilder.instance.getPay(wxpay, order));
            }
            order.setPayment(payment);
        }
        return order;
    }

    @Override
    public void confirm(String orderNo) {
        Order order = orderMapper.findByOrderNo(orderNo);
        Appointment appointment = appointmentMapper.selectByOrderNo(orderNo);
        Room room = roomMapper.findById(appointment.getRoomId());
        switch (room.getCfm()) {
            case AUTO:
                break;
            case BEFORE:
                switch (order.getStatus()) {
                    case PAID2CONFIRM:
                        orderMapper.update(orderNo, OrderStatus.PAIDANDCONFIRM);
                        break;
                    case UNPAID2CONFIRM:
                        orderMapper.update(orderNo, OrderStatus.CONFIRM2PAID);
                        break;
                }
                break;
            case AFTER:
                switch (order.getStatus()) {
                    case PAID2CONFIRM:
                        orderMapper.update(orderNo, OrderStatus.PAIDANDCONFIRM);
                        break;
                }
                break;
        }
    }

    @Override
    public void cancel(String orderNo) {
        orderMapper.update(orderNo, OrderStatus.CANCELED);
    }

    @Override
    @Transactional
    public void paying(Receipt receipt, String payType) {
        receiptMapper.create(receipt);
        Order order = orderMapper.findByOrderNo(receipt.getOrderNo());
        switch (order.getStatus()) {
            case UNPAID2CONFIRM:
                orderMapper.defray(order.getOrderNo(), OrderStatus.PAID2CONFIRM, payType);
                break;
            case CONFIRM2PAID:
                orderMapper.defray(order.getOrderNo(), OrderStatus.PAIDANDCONFIRM, payType);
                break;
        }
    }
}
