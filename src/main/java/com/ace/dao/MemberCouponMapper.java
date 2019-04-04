package com.ace.dao;

import com.ace.entity.coupon.MemberCoupon;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface MemberCouponMapper {
    List<MemberCoupon> dataList(@Param("couponId") int couponId, @Param("start") int start, @Param("length") int length, @Param("keyword") String keyword);

    Integer recordsTotal(@Param("couponId") int couponId, @Param("keyword") String keyword);

    MemberCoupon findById(@Param("id") int id);

    void create(@Param("coupons") List<MemberCoupon> mcList);

    void update(MemberCoupon Coupon);
}
