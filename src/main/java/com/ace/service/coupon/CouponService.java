package com.ace.service.coupon;

import com.ace.controller.admin.concerns.DataTable;
import com.ace.entity.coupon.Coupon;

public interface CouponService {

    DataTable<Coupon> dataTable(int start, int length, String keyword);

    Coupon findById(int id);

    void create(Coupon attribute);

    void update(Coupon attribute);

    void delete(int id);
}
