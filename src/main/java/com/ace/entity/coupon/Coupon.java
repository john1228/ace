package com.ace.entity.coupon;

import com.ace.entity.coupon.concern.CouponUtil;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Table;

@Table(name = "bb_coupons")
@Getter
@Setter
public class Coupon extends Base {
    private CouponUtil.Expired expiredType;
    private Integer amount;
    private Integer duration;
}
