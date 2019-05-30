package com.ace.service.api.impl;

import com.ace.dao.*;
import com.ace.entity.*;
import com.ace.entity.concern.Period;
import com.ace.entity.concern.enums.OrderStatus;
import com.ace.entity.concern.enums.RoomRental;
import com.ace.entity.concern.enums.Week;
import com.ace.service.api.OrderService;
import com.ace.service.concerns.OrderTools;
import com.ace.service.concerns.RoomTools;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service("api_order_service")
public class OrderServiceImpl extends BaseService implements OrderService {
    Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

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


    @Override
    public List<Order> customerOrder(Account account, OrderStatus status, int page) {
        return orderMapper.customerOrder(account, status, page, pageSize);
    }

    @Override
    public List<Order> supplierOrder(Account account, OrderStatus status, int page) {
        return orderMapper.supplierOrder(account, status, page, pageSize);
    }

    @Override
    @Transactional
    public boolean create(Account account, Appointment appointment, Long couponId) {
        if (appointmentMapper.isExists(appointment.getRoomId(), appointment.getStartTime(), appointment.getEndTime())) {
            account.setErrMsg("已经有客户预约了该时间段");
            return false;
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
                return false;
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
                    Long hours = (appointment.getEndTime().getTime() - appointment.getStartTime().getTime()) / (60 * 60 * 1000);
                    order.setTotal(price.getPrice().multiply(new BigDecimal(hours.intValue())));
                }
                //校验优惠券
                if (couponId != 0) {
                    Date date = new Date(System.currentTimeMillis());
                    MemberCoupon coupon = mcMapper.findById(couponId);
                    if (date.before(coupon.getStartDate())) {
                        account.setErrMsg("优惠券还未到使用时间");
                        return false;
                    } else if (date.after(coupon.getEndDate())) {
                        account.setErrMsg("优惠券已经过期");
                        return false;
                    } else {
                        if (!coupon.getLimitWday().contains(week)) {
                            account.setErrMsg("优惠券不能用于该日期");
                            return false;
                        } else if (!coupon.getLimitRoom().contains(appointment.getRoomId())) {
                            account.setErrMsg("优惠券不能用于该会议室");
                            return false;
                        } else {
                            order.setCoupon(new BigDecimal(coupon.getDiscount()));
                        }
                    }
                    //TODO:更新优惠券状态
                }
                //计算服务费用
                List<RoomSupport> roomSupports = rsMapper.supportList(room.getId());
                StringBuilder errMsg = new StringBuilder();
                BigDecimal supportFee = orderTools.cacl(appointment.getService(), roomSupports, errMsg);
                if (errMsg.length() != 0) {
                    account.setErrMsg(errMsg.toString());
                    return false;
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
                            order.setStatus(OrderStatus.UNPAID2CONFIRM);
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
                //创建预约
                appointment.setOrderId(order.getId());
                appointmentMapper.create(appointment);
                //创建服务
                if (appointment.getService().size() > 0) {
                    appointment.getService().forEach(service -> service.setOrderId(order.getId()));
                    orderSupportMapper.create(appointment.getService());
                }
                //缓存预约时间
                redisTemplate.opsForSet().add("ROOM::" + appointment.getRoomId() + "::APPOINTED::" + appointedDate.toString(), new Period(
                        hf.format(appointment.getStartTime()),
                        hf.format(appointment.getEndTime())
                ));
                return true;
            } else {
                account.setErrMsg("该时间段未开放预约");
                return false;
            }
        }
    }

    @Override
    public Order show(Account account, String orderNo) {
        Order order = orderMapper.findById(orderNo);
        Appointment appointment = appointmentMapper.selectByOrder(order.getId());
        List<OrderSupport> supportList = orderSupportMapper.supportList(order.getId());
        appointment.setService(supportList);
        order.setAppointment(appointment);
        return order;
    }

    @Override
    public void confirm(String orderNo) {
        Order order = orderMapper.findById(orderNo);
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

}
