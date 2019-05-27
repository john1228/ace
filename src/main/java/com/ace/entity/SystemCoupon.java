package com.ace.entity;

import com.ace.controller.admin.concerns.AdminView;
import com.ace.entity.concern.enums.CouponExpired;
import com.ace.entity.concern.enums.CouponStatus;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class SystemCoupon extends Coupon {
    private CouponExpired expiredType = CouponExpired.CONVENTION;
    @JsonView(AdminView.Table.class)
    private Integer amount;
    @JsonView(AdminView.Table.class)
    private Integer duration;


    public MemberCoupon grant(String accountId) {
        if (amount > 0) {
            MemberCoupon mc = new MemberCoupon();
            BeanUtils.copyProperties(this, mc);
            if (expiredType == CouponExpired.ASSIGNMENT) {
                mc.setStartDate(new Date(System.currentTimeMillis()));
                mc.setEndDate(new Date(System.currentTimeMillis() + (duration - 1) * 24 * 3600 * 1000L));
            }
            mc.setAccountId(accountId);
            mc.setStatus(CouponStatus.PENDING);
            this.amount -= 1;
            return mc;
        } else {
            return null;
        }
    }
}
