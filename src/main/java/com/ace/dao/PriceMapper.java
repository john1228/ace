package com.ace.dao;

import com.ace.entity.Staff;
import com.ace.entity.Price;
import com.ace.entity.Room;
import org.apache.ibatis.annotations.Param;

import java.sql.Date;
import java.util.List;


public interface PriceMapper {
    List<Price> dataList(@Param("staff") Staff staff, @Param("start") int start, @Param("length") int length, @Param("keyword") String keyword);

    Long recordsTotal(@Param("staff") Staff staff, @Param("keyword") String keyword);

    void create(Price price);

    void createRef(@Param("priceId") Long priceId, @Param("rooms") List<Integer> rooms);

    Price findById(@Param("id") Long id);

    List<Integer> findRefById(@Param("id") Long id);

    void update(Price price);

    void delete(Long id);

    void deleteRef(Long id);

    //APP相关接口
    List<Price> priceList(@Param("rooms") List<Room> rooms, @Param("date") Date date);

    List<Price> prices(@Param("room") Long room, @Param("date") Date date);
}
