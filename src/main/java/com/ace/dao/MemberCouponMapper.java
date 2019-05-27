package com.ace.dao;

import com.ace.entity.MemberCoupon;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface MemberCouponMapper {
    List<MemberCoupon> dataList(@Param("couponId") Long couponId, @Param("start") int start, @Param("length") int length, @Param("keyword") String keyword);

    Long recordsTotal(@Param("couponId") Long couponId, @Param("keyword") String keyword);

    MemberCoupon findById(@Param("id") Long id);

    void create(@Param("coupons") List<MemberCoupon> mcList);

    void update(MemberCoupon Coupon);
}
