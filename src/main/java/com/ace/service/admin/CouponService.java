package com.ace.service.admin;

import com.ace.controller.admin.concerns.CouponCriteria;
import com.ace.controller.admin.concerns.DataTable;
import com.ace.entity.Grant;
import com.ace.entity.SystemCoupon;
import com.ace.entity.Staff;

public interface CouponService {

    void data(Staff staff, DataTable<SystemCoupon> dataTable, CouponCriteria criteria);

    SystemCoupon findById(int id);

    void create(Staff staff, SystemCoupon attribute);

    void update(SystemCoupon attribute);

    void delete(int id);

    void grant(SystemCoupon coupon, Grant grant);
}
