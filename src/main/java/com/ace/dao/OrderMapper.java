package com.ace.dao;

import com.ace.entity.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface OrderMapper {
    List<Order> dataList(@Param("start") int start, @Param("length") int length, @Param("keyword") String keyword);

    Integer recordsTotal(@Param("keyword") String keyword);

    void create(Order order);

    Order findById(@Param("id") int id);

    void update(Order order);
}
