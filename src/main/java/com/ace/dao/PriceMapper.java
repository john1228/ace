package com.ace.dao;

import com.ace.entity.Staff;
import com.ace.entity.room.Price;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface PriceMapper {
    List<Price> dataList(@Param("staff") Staff staff, @Param("start") int start, @Param("length") int length, @Param("keyword") String keyword);

    Integer recordsTotal(@Param("staff") Staff staff, @Param("keyword") String keyword);

    void create(Price price);

    Price findById(@Param("id") int id);

    void update(Price price);

    void destroy(int id);
}
