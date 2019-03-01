package com.ace.entity.coupon;

import com.ace.entity.coupon.concern.CouponUtil;
import lombok.*;
import org.springframework.beans.BeanUtils;

import javax.persistence.Table;
import java.util.Date;

@Table(name = "bb_coupons")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class SystemCoupon extends Coupon {
    @NonNull
    private Integer staffId;
    private CouponUtil.Expired expiredType;
    private Integer amount;
    private Integer duration;


    public MemberCoupon grant(String accountId) {
        if (amount > 0) {
            MemberCoupon mc = new MemberCoupon();
            BeanUtils.copyProperties(this, mc);
            if (expiredType == CouponUtil.Expired.ASSIGNMENT) {
                mc.setStartDate(new Date(System.currentTimeMillis()));
                mc.setEndDate(new Date(System.currentTimeMillis() + (duration - 1) * 24 * 3600 * 1000L));
            }
            mc.setAccountId(accountId);
            mc.setStatus(CouponUtil.Status.PENDING);
            this.amount -= 1;
            return mc;
        } else {
            return null;
        }
    }
}
