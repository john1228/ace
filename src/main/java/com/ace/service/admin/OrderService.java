package com.ace.service.admin;

import com.ace.controller.admin.concerns.DataTable;
import com.ace.controller.admin.concerns.OrderCriteria;
import com.ace.entity.Order;
import com.ace.entity.Staff;
import com.ace.entity.concern.enums.OrderStatus;
import org.springframework.stereotype.Service;


@Service
public interface OrderService {

    void dataTable(Staff staff, OrderCriteria criteria, DataTable<Order> dataTable);

    Order findByOrderNo(String orderNo);

    void create(Staff staff, Order order);

    void update(String orderNo, OrderStatus status);

    void confirm(Staff staff, String orderNo);

    void delete(String orderNo);
}
