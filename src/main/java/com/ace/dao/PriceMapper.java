package com.ace.dao;

import com.ace.controller.admin.concerns.PriceCriteria;
import com.ace.entity.Staff;
import com.ace.entity.Price;
import com.ace.entity.Room;
import org.apache.ibatis.annotations.Param;

import java.sql.Date;
import java.util.List;


public interface PriceMapper {
    Long recordsTotal(@Param("staff") Staff staff, PriceCriteria criteria);

    List<Price> dataList(@Param("staff") Staff staff, PriceCriteria criteria);

    void create(Price price);

    void createRef(@Param("priceId") Long priceId, @Param("rooms") List<Integer> rooms);

    Price findById(@Param("id") Long id);

    List<Integer> findRefById(@Param("id") Long id);

    void update(Price price);

    void delete(Long id);

    //APP相关接口
    List<Price> priceList(@Param("rooms") List<Room> rooms, @Param("date") Date date);

    List<Price> prices(@Param("room") Long room, @Param("date") Date date);
}
