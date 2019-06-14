package com.ace.dao;

import com.ace.entity.Account;
import com.ace.entity.MemberCoupon;
import com.ace.entity.Staff;
import com.ace.entity.concern.enums.CouponStatus;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface MemberCouponMapper {
    List<MemberCoupon> dataList(@Param("couponId") Long couponId, @Param("start") int start, @Param("length") int length, @Param("keyword") String keyword);

    Long recordsTotal(@Param("couponId") Long couponId, @Param("keyword") String keyword);

    MemberCoupon findById(@Param("id") Long id);

    void grant(@Param("coupons") List<MemberCoupon> mcList);

    void use(@Param("id") Long id, @Param("status") CouponStatus status);

    List<MemberCoupon> projectCoupons(@Param("account") Account account, @Param("project") String projectId);
}
