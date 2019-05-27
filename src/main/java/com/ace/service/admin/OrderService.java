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

    Order findById(String orderNo);

    void create(Order order);

    void update(String orderNo, OrderStatus status);

    void delete(String orderNo);
}
