package com.ace.service.api;

import com.ace.controller.api.concerns.ListStatus;
import com.ace.entity.Account;
import com.ace.entity.Appointment;
import com.ace.entity.Order;
import com.ace.entity.Receipt;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService {

    List<Order> customerOrder(Account account, ListStatus status, int page);

    List<Order> supplierOrder(Account account, String status, int page);

    Order create(Account account, Appointment appointment, Long couponId);

    Order show(String orderNo);

    void confirm(String orderNo);

    void cancel(String orderNo);

    void paying(Receipt receipt, String payType);
}
