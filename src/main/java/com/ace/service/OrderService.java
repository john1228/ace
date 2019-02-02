package com.ace.service;

import com.ace.controller.admin.concerns.DataTable;
import com.ace.entity.Order;

public interface OrderService {

    DataTable<Order> dataTable(int start, int length, String keyword);

    Order findById(int id);

    void create(Order order);

    void update(Order order);

    void delete(int id);
}
