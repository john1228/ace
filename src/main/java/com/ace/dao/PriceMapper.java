package com.ace.dao;

import com.ace.entity.room.Price;
import com.ace.tk.TKMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface PriceMapper extends TKMapper<Price> {
    List<Price> dataList(@Param("start") int start, @Param("length") int length, @Param("keyword") String keyword);

    Integer recordsTotal(@Param("keyword") String keyword);

    void create(Price price);

    Price findById(@Param("id") int id);

    void update(Price price);

    void destroy(int id);
}
