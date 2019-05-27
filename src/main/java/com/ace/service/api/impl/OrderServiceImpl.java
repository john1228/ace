package com.ace.service.api.impl;

import com.ace.dao.*;
import com.ace.entity.*;
import com.ace.entity.concern.Period;
import com.ace.entity.concern.enums.OrderStatus;
import com.ace.entity.concern.enums.RoomRental;
import com.ace.entity.concern.enums.Week;
import com.ace.service.api.OrderService;
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
            order.setOrderNo("");
            SimpleDateFormat wf = new SimpleDateFormat("EEEE", Locale.ENGLISH);
            SimpleDateFormat hf = new SimpleDateFormat("HH:mm");
            Date appointedDate = new Date(appointment.getStartTime().getTime());
            Week week = Week.valueOf(wf.format(appointedDate).toUpperCase());
            //价格计算
            List<Price> prices = priceMapper.prices(appointment.getRoomId(), appointedDate);
            Optional<Price> optional = prices.stream().filter(item -> {
                if (item.getWday().contains(week)) {
                    Timestamp start = Timestamp.valueOf(appointedDate.toString() + " " + item.getStartTime() + ":00");
                    Timestamp end = Timestamp.valueOf(appointedDate.toString() + " " + item.getEndTime() + ":00");
                    switch (room.getRental()) {
                        case HOUR:
                            return start.compareTo(appointment.getStartTime()) <= 0 && end.compareTo(appointment.getEndTime()) >= 0;
                        case PERIOD:
                            return start.equals(appointment.getStartTime()) && end.equals(appointment.getEndTime());
                        default:
                            return false;
                    }
                } else {
                    return false;
                }

            }).findFirst();
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
                appointment.getService().forEach(orderSupport -> {
                    Optional<RoomSupport> found = roomSupports.stream().filter(roomSupport -> roomSupport.getId() == orderSupport.getSupportId()).findFirst();
                    if (found.isPresent()) {
                        RoomSupport roomSupport = found.get();
                        roomSupport.copyTo(orderSupport);
                        order.setTotal(order.getTotal().add(roomSupport.getPrice().multiply(new BigDecimal(orderSupport.getAmount()))));
                    } else {
                        account.setErrMsg("无效的服务");
                    }
                });
                if (Strings.isBlank(account.getErrMsg())) {
                    order.setPayAmount(order.getTotal().subtract(order.getCoupon()));
                    if (order.getPayAmount().compareTo(new BigDecimal(0)) == -1) {
                        order.setPayAmount(new BigDecimal(0));
                        order.setStatus(OrderStatus.PAID);
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
                    return false;
                }
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
        orderMapper.update(orderNo, OrderStatus.CONFIRMING);
    }

    @Override
    public void cancel(String orderNo) {
        orderMapper.update(orderNo, OrderStatus.CANCELED);
    }

}
