package com.ace.service.admin;

import com.ace.controller.admin.concerns.DataTable;
import com.ace.entity.MemberCoupon;

public interface MemberCouponService {

    DataTable<MemberCoupon> dataTable(Long couponId, int start, int length, String keyword);

    MemberCoupon findById(Long id);

    void update(MemberCoupon attribute);
}
