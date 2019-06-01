package com.ace.service.api;

import com.ace.entity.Account;
import com.ace.entity.Appointment;
import com.ace.entity.Order;
import com.ace.entity.concern.enums.OrderStatus;

import java.util.List;

public interface OrderService {

    List<Order> customerOrder(Account account, OrderStatus status, int page);

    List<Order> supplierOrder(Account account, OrderStatus status, int page);

    boolean create(Account account, Appointment appointment, Long couponId);

    Order show(Account account, String orderNo);

    void confirm(String orderNo);

    void cancel(String orderNo);
}