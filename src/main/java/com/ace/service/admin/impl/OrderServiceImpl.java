package com.ace.service.admin.impl;

import com.ace.controller.admin.concerns.DataTable;
import com.ace.controller.admin.concerns.OrderCriteria;
import com.ace.dao.*;
import com.ace.entity.*;
import com.ace.entity.concern.Period;
import com.ace.entity.concern.enums.OrderStatus;
import com.ace.entity.concern.enums.RoomRental;
import com.ace.service.admin.OrderService;
import com.ace.service.concerns.OrderTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@Service("admin_order_service")
public class OrderServiceImpl implements OrderService {
    Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Resource
    private OrderMapper orderMapper;
    @Resource
    private OrderSupportMapper orderSupportMapper;
    @Resource
    private AppointmentMapper appointmentMapper;
    @Resource
    private RoomMapper roomMapper;
    @Resource
    private PriceMapper priceMapper;
    @Resource
    private RoomSupportMapper rsMapper;
    @Resource
    private RedisTemplate<String, Period> redisTemplate;
    @Resource
    private OrderTools orderTools;

    @Override
    public void dataTable(Staff staff, OrderCriteria criteria, DataTable<Order> dataTable) {
        logger.info("##############" + orderTools.abc());
        dataTable.setRecordsFiltered(orderMapper.recordsTotal(staff, criteria));
        dataTable.setData(orderMapper.dataList(staff, criteria));
    }

    @Override
    public Order findById(String orderNo) {
        return orderMapper.findById(orderNo);
    }

    @Override
    public void create(Staff staff, Order order) {
        Appointment appointment = order.getAppointment();
        if (appointmentMapper.isExists(appointment.getRoomId(), appointment.getStartTime(), appointment.getEndTime())) {
            staff.getErrMsg().append("已经有客户预约了该时间段");
        } else {
            Room room = roomMapper.findById(appointment.getRoomId());
            SimpleDateFormat hf = new SimpleDateFormat("HH:mm");
            Date appointedDate = new Date(appointment.getStartTime().getTime());
            //价格计算
            List<Price> prices = priceMapper.prices(appointment.getRoomId(), appointedDate);
            Optional<Price> optional = null;//OrderTools.fittedPrice(prices, appointment.getStartTime(), appointment.getEndTime(), room.getRental());
            if (optional.isPresent()) {
                Price price = optional.get();
                //计算总价
                if (price.getRental().equals(RoomRental.PERIOD)) {
                    order.setTotal(price.getPrice());
                } else {
                    Long hours = (appointment.getEndTime().getTime() - appointment.getStartTime().getTime()) / (60 * 60 * 1000);
                    order.setTotal(price.getPrice().multiply(new BigDecimal(hours.intValue())));
                }
                //计算服务费用
                List<RoomSupport> roomSupports = rsMapper.supportList(room.getId());
                StringBuilder errMsg = new StringBuilder();
                BigDecimal supportFee = new BigDecimal(0); //OrderTools.cacl(roomSupports, appointment.getService(), errMsg);
                order.getTotal().add(supportFee);
                staff.getErrMsg().append(errMsg);
                if (staff.getErrMsg().length() == 0) {
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
                }
            } else {
                staff.getErrMsg().append("该时间段未开放预约");
            }
        }
    }

    @Override
    public void update(String orderNo, OrderStatus status) {
        orderMapper.update(orderNo, status);
    }

    @Override
    public void delete(String orderNo) {
        orderMapper.update(orderNo, OrderStatus.CANCELED);
    }
}
