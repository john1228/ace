package com.ace.service.admin.impl;

import com.ace.controller.admin.concerns.DataTable;
import com.ace.controller.admin.concerns.OrderCriteria;
import com.ace.dao.*;
import com.ace.entity.*;
import com.ace.entity.concern.Period;
import com.ace.entity.concern.enums.OrderStatus;
import com.ace.entity.concern.enums.RoomRental;
import com.ace.service.admin.OrderService;
import com.ace.service.concerns.JobTools;
import com.ace.service.concerns.OrderTools;
import com.ace.service.concerns.RoomTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("admin_order_service")
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;
    @Resource
    private OrderSupportMapper orderSupportMapper;
    @Resource
    private AppointmentMapper appointmentMapper;
    @Resource
    private RedisTemplate<String, Period> redisTemplate;
    @Resource
    private JobTools jt;

    @Override
    public void dataTable(Staff staff, OrderCriteria criteria, DataTable<Order> dataTable) {
        dataTable.setRecordsFiltered(orderMapper.recordsTotal(staff, criteria));
        dataTable.setData(orderMapper.dataList(staff, criteria));
    }

    @Override
    public Order findByOrderNo(String orderNo) {
        return orderMapper.findByOrderNo(orderNo);
    }

    @Override
    public void create(Staff staff, Order order) {
        Appointment appointment = order.getAppointment();
        if (appointmentMapper.isExists(appointment.getRoomId(), appointment.getStartTime(), appointment.getEndTime())) {
            staff.getErrMsg().append("已经有客户预约了该时间段");
        } else {
            SimpleDateFormat hf = new SimpleDateFormat("HH:mm");
            Date appointed = new Date(appointment.getStartTime().getTime());
            //价格计算
            order.setStatus(OrderStatus.PAIDANDCONFIRM);
            orderMapper.create(order);
            appointment.setOrderId(order.getId());
            appointmentMapper.create(appointment);
            List<OrderSupport> selectedSupport = appointment.getService().stream().filter(item -> item.getSupportId() != null).collect(Collectors.toList());
            if (selectedSupport.size() > 0) {
                appointment.getService().forEach(service -> service.setOrderId(order.getId()));
                orderSupportMapper.create(appointment.getService());
            }
            //缓存预约时间
            redisTemplate.opsForSet().add("ROOM::" + appointment.getRoomId() + "::APPOINTED::" + appointed.toString(), new Period(
                    hf.format(appointment.getStartTime()),
                    hf.format(appointment.getEndTime())
            ));
        }
    }

    @Override
    public void update(String orderNo, OrderStatus status) {
        orderMapper.update(orderNo, status);
    }


    @Override
    public void confirm(Staff staff, String orderNo) {
        Order order = orderMapper.findByOrderNo(orderNo);
        if (order.getStatus().equals(OrderStatus.UNPAID2CONFIRM)) {
            orderMapper.update(orderNo, OrderStatus.CONFIRM2PAID);
            jt.cancelOrder(orderNo);
        }
    }

    @Override
    public void delete(String orderNo) {
        orderMapper.update(orderNo, OrderStatus.CANCELED);
    }
}
