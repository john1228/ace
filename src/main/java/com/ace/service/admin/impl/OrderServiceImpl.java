package com.ace.service.admin.impl;

import com.ace.controller.admin.concerns.DataTable;
import com.ace.controller.admin.concerns.OrderCriteria;
import com.ace.dao.OrderMapper;
import com.ace.entity.Order;
import com.ace.entity.Staff;
import com.ace.entity.concern.enums.OrderStatus;
import com.ace.service.admin.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("admin_order_service")
public class OrderServiceImpl implements OrderService {
    Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Resource
    private OrderMapper orderMapper;

    @Override
    public void dataTable(Staff staff, OrderCriteria criteria, DataTable<Order> dataTable) {
        dataTable.setRecordsFiltered(orderMapper.recordsTotal(staff, criteria));
        dataTable.setData(orderMapper.dataList(staff, criteria));
    }

    @Override
    public Order findById(String orderNo) {
        return orderMapper.findById(orderNo);
    }

    @Override
    public void create(Order order) {
        orderMapper.create(order);
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
