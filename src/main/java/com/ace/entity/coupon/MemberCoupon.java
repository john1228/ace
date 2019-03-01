package com.ace.entity.coupon;

import com.ace.entity.coupon.concern.CouponUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class MemberCoupon extends Coupon {
    private String accountId;
    private CouponUtil.Status status;
    private Date createdAt;
    private Date updatedAt;
}
