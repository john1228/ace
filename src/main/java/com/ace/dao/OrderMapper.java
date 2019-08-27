package com.ace.dao;

import com.ace.controller.admin.concerns.OrderCriteria;
import com.ace.entity.Account;
import com.ace.entity.Order;
import com.ace.entity.Staff;
import com.ace.entity.concern.Period;
import com.ace.entity.concern.enums.OrderStatus;
import org.apache.ibatis.annotations.Param;

import java.sql.Date;
import java.util.List;


public interface OrderMapper {
    List<Order> dataList(@Param("staff") Staff staff, @Param("criteria") OrderCriteria criteria);

    Long recordsTotal(@Param("staff") Staff staff, @Param("criteria") OrderCriteria criteria);

    void create(Order order);

    Order findByOrderNo(@Param("orderNo") String orderNO);

    Order findById(@Param("id") Long id);

    void update(@Param("orderNo") String orderNo, @Param("status") OrderStatus status);

    //用户订单
    List<Order> customerOrder(@Param("account") Account account, @Param("statuses") List<OrderStatus> status, @Param("page") int page, @Param("length") int length);

    //供应商订单
    List<Order> supplierOrder(@Param("account") Account account, @Param("status") String status, @Param("page") int page, @Param("length") int length);

    //订单付款
    void defray(@Param("id") String orderNo, @Param("status") OrderStatus status, @Param("payType") String payType);

    //订单确认试用
    void using();

    //已预约列表
    List<Period> appointedList(@Param("roomId") Long roomId, @Param("from") Date from, @Param("to") Date to);
}
