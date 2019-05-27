package com.ace.entity;

import com.ace.entity.concern.enums.CouponStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class MemberCoupon extends Coupon {
    private String accountId;
    private CouponStatus status;
    private Date createdAt;
    private Date updatedAt;
}
