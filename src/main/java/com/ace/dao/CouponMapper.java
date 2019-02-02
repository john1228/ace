package com.ace.dao;

import com.ace.entity.coupon.Coupon;
import com.ace.entity.room.Attribute;
import com.ace.tk.TKMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface CouponMapper extends TKMapper<Attribute> {
    List<Coupon> dataList(@Param("start") int start, @Param("length") int length, @Param("keyword") String keyword);

    Integer recordsTotal(@Param("keyword") String keyword);

    void create(Coupon coupon);

    Coupon findById(@Param("id") int id);

    void update(Coupon Coupon);

    void destroy(int id);
}
