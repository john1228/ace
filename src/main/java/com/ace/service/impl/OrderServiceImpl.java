package com.ace.service.impl;

import com.ace.controller.admin.concerns.DataTable;
import com.ace.dao.CouponMapper;
import com.ace.dao.OrderMapper;
import com.ace.entity.Order;
import com.ace.entity.coupon.Coupon;
import com.ace.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("orderService")
public class OrderServiceImpl implements OrderService {
    Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Resource
    private OrderMapper orderMapper;

    @Override
    public DataTable<Order> dataTable(int start, int length, String keyword) {
        DataTable<Order> dataTable = new DataTable<>();
        dataTable.setStart(start);
        dataTable.setLength(length);
        dataTable.setRecordsTotal(orderMapper.recordsTotal(keyword));
        dataTable.setData(orderMapper.dataList(start, length, keyword));
        return dataTable;
    }

    @Override
    public Order findById(int id) {
        return orderMapper.findById(id);
    }

    @Override
    public void create(Order order) {
        orderMapper.create(order);
    }

    @Override
    public void update(Order order) {
        logger.info("更新数据");
        orderMapper.update(order);
    }

    @Override
    public void delete(int id) {
        Order order = orderMapper.findById(id);
        orderMapper.update(order);
    }
}
