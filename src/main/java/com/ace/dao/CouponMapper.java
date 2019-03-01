package com.ace.dao;

import com.ace.entity.coupon.SystemCoupon;
import com.ace.entity.Staff;
import com.ace.tk.TKMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface CouponMapper extends TKMapper<SystemCoupon> {
    List<SystemCoupon> dataList(@Param("staff") Staff staff, @Param("start") int start, @Param("length") int length, @Param("keyword") String keyword);

    Integer recordsTotal(@Param("staff") Staff staff, @Param("keyword") String keyword);

    void create(SystemCoupon coupon);

    SystemCoupon findById(@Param("id") int id);

    void update(SystemCoupon Coupon);

    void destroy(int id);
}
