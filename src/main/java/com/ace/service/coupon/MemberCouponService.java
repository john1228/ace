package com.ace.service.coupon;

import com.ace.controller.admin.concerns.DataTable;
import com.ace.entity.coupon.MemberCoupon;

public interface MemberCouponService {

    DataTable<MemberCoupon> dataTable(int couponId, int start, int length, String keyword);

    MemberCoupon findById(int id);

    void update(MemberCoupon attribute);
}
