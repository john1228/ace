package com.ace.service.coupon;

import com.ace.controller.admin.concerns.DataTable;
import com.ace.entity.Grant;
import com.ace.entity.coupon.SystemCoupon;
import com.ace.entity.Staff;

public interface CouponService {

    DataTable<SystemCoupon> dataTable(Staff staff, int start, int length, String keyword);

    SystemCoupon findById(int id);

    void create(SystemCoupon attribute);

    void update(SystemCoupon attribute);

    void delete(int id);

    void grant(SystemCoupon coupon, Grant grant);
}
