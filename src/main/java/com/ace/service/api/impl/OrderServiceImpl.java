package com.ace.service.api.impl;

import com.ace.controller.api.concerns.ListStatus;
import com.ace.dao.*;
import com.ace.entity.*;
import com.ace.entity.concern.Payment;
import com.ace.entity.concern.Period;
import com.ace.entity.concern.enums.CouponStatus;
import com.ace.entity.concern.enums.OrderStatus;
import com.ace.entity.concern.enums.Week;
import com.ace.service.api.OrderService;
import com.ace.service.concerns.JobTools;
import com.ace.service.concerns.OrderTools;
import com.ace.service.concerns.RoomTools;
import com.ace.util.AlipayBuilder;
import com.ace.util.wxpay.WxpayBuilder;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("api_order_service")
@Log4j2
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
    private OrderTools orderTools;
    @Resource
    private RoomTools roomTools;
    @Resource
    private JobTools jobTools;
    @Resource
    private RefundMapper refundMapper;
    @Resource
    private AlipayMapper alipayMapper;
    @Resource
    private WxpayMapper wxpayMapper;


    @Override
    public List<Order> customerOrder(Account account, ListStatus status, int page) {
        switch (status) {
            case CANCELED:
                return orderMapper.customerOrder(account, Arrays.asList(new OrderStatus[]{
                        OrderStatus.CANCELED
                }), page, pageSize);
            case PENDING:
                return orderMapper.customerOrder(account, Arrays.asList(new OrderStatus[]{
                        OrderStatus.UNPAID2CONFIRM,
                        OrderStatus.CONFIRM2PAID
                }), page, pageSize);
            case CONFIRMING:
                return orderMapper.customerOrder(account, Arrays.asList(new OrderStatus[]{
                        OrderStatus.PAID2CONFIRM,
                        OrderStatus.UNPAID2CONFIRM
                }), page, pageSize);
            case PAID:
                return orderMapper.customerOrder(account, Arrays.asList(new OrderStatus[]{
                        OrderStatus.PAIDANDCONFIRM
                }), page, pageSize);
            default:
                return orderMapper.customerOrder(account, null, page, pageSize);
        }
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
        } else if (appointment.getStartTime().before(new Timestamp(System.currentTimeMillis()))) {
            account.setErrMsg("该时间段已过预约时间");
            return null;
        } else {
            Room room = roomMapper.findById(appointment.getRoomId());
            Order order = new Order(account.getAccountId(), account.getAccountName());
            SimpleDateFormat wf = new SimpleDateFormat("EEEE", Locale.ENGLISH);

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
            try {
                //计算总价
                BigDecimal total = orderTools.fittedPrice(prices, appointment.getStartTime(), appointment.getEndTime(), room.getRental());
                order.setTotal(total);
                //校验优惠券
                log.info("使用优惠券编号:" + couponId);
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
                Long orderId = createOrder(order, appointment);
                if (orderId != null && orderId != 0L) {
                    order = orderMapper.findById(orderId);
                    if (order.getStatus().equals(OrderStatus.UNPAID2CONFIRM) || order.getStatus().equals(OrderStatus.CONFIRM2PAID)) {
                        //支付宝
                        Payment payment = new Payment();
                        Alipay alipay = alipayMapper.findBy(room.getProjectId());
                        Wxpay wxpay = wxpayMapper.findBy(room.getProjectId());
                        if (alipay != null) {
                            payment.setAlipay(AlipayBuilder.instance.getPay(alipay, order));
                        }
                        if (wxpay != null) {
                            payment.setWxpay(WxpayBuilder.instance.getPay(wxpay, order));
                        }
                        order.setPayment(payment);
                    }
                    if (order.getStatus().equals(OrderStatus.CONFIRM2PAID)) {
                        jobTools.cancelOrder(order.getOrderNo());
                    }
                    return order;
                } else {
                    account.setErrMsg("创建订单失败");
                    return null;
                }
            } catch (Exception e) {
                account.setErrMsg("该时间段未开放预约");
                return null;
            }
        }
    }

    @Override
    public Order show(String orderNo) {
        Room room = roomMapper.appointedRoom(orderNo);
        Order order = orderMapper.findByOrderNo(orderNo);
        Appointment appointment = appointmentMapper.selectByOrder(order.getId());
        List<OrderSupport> supportList = orderSupportMapper.supportList(order.getId());
        appointment.setService(supportList);
        order.setAppointment(appointment);
        if (order.getStatus().equals(OrderStatus.UNPAID2CONFIRM) || order.getStatus().equals(OrderStatus.CONFIRM2PAID)) {
            //支付宝
            Alipay alipay = alipayMapper.findBy(room.getProjectId());
            Wxpay wxpay = wxpayMapper.findBy(room.getProjectId());
            Payment payment = new Payment();
            payment.setAlipay(AlipayBuilder.instance.getPay(alipay, order));
            payment.setWxpay(WxpayBuilder.instance.getPay(wxpay, order));
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
                        //TODO: 通知用户付款
                        jobTools.cancelOrder(orderNo);
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
    @Transactional
    public void cancel(String orderNo) {
        Order order = orderMapper.findByOrderNo(orderNo);
        switch (order.getStatus()) {
            case PAIDANDCONFIRM:
                if (order.getPayAmount().compareTo(new BigDecimal("0")) == 1)
                    refundMapper.create(RefundApplication.build(order));
                orderMapper.update(orderNo, OrderStatus.REFUNDING);
                break;
            case UNPAID2CONFIRM:
                orderMapper.update(orderNo, OrderStatus.CANCELED);
                break;
            case CONFIRM2PAID:
                orderMapper.update(orderNo, OrderStatus.CANCELED);
                break;
            case PAID2CONFIRM:
                if (order.getPayAmount().compareTo(new BigDecimal("0")) == 1)
                    refundMapper.create(RefundApplication.build(order));
                orderMapper.update(orderNo, OrderStatus.REFUNDING);
                break;
        }
    }

    @Override
    @Transactional
    public void paying(Receipt receipt, String payType) {
        Order order = orderMapper.findByOrderNo(receipt.getOrderNo());
        orderMapper.defray(order.getOrderNo(), OrderStatus.PAID2CONFIRM, payType);
        switch (order.getStatus()) {
            case UNPAID2CONFIRM:
                orderMapper.defray(order.getOrderNo(), OrderStatus.PAID2CONFIRM, payType);
                break;
            case CONFIRM2PAID:
                orderMapper.defray(order.getOrderNo(), OrderStatus.PAIDANDCONFIRM, payType);
                break;
        }
    }

    @Transactional
    protected Long createOrder(Order order, Appointment appointment) {
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
        return orderId;
    }

    public static void main(String[] args) {
        System.err.println(new BigDecimal(1).compareTo(new BigDecimal("0")));
    }
}
