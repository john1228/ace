package com.ace.dao;

import com.ace.controller.admin.concerns.CouponCriteria;
import com.ace.entity.SystemCoupon;
import com.ace.entity.Staff;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface CouponMapper {
    List<SystemCoupon> dataList(@Param("staff") Staff staff, @Param("criteria") CouponCriteria criteria, @Param("start") int start, @Param("length") int length);

    Long recordsTotal(@Param("staff") Staff staff, @Param("criteria") CouponCriteria criteria);

    void create(@Param("staff") Staff staff, @Param("coupon") SystemCoupon coupon);

    SystemCoupon findById(@Param("id") int id);

    void update(SystemCoupon Coupon);

    void destroy(int id);
}
