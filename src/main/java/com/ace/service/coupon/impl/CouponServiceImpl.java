package com.ace.service.coupon.impl;

import com.ace.controller.admin.concerns.DataTable;
import com.ace.dao.CouponMapper;
import com.ace.dao.MemberCouponMapper;
import com.ace.entity.Grant;
import com.ace.entity.coupon.SystemCoupon;
import com.ace.entity.coupon.MemberCoupon;
import com.ace.entity.Staff;
import com.ace.service.coupon.CouponService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("couponService")
public class CouponServiceImpl implements CouponService {
    Logger logger = LoggerFactory.getLogger(CouponServiceImpl.class);

    @Resource
    private CouponMapper couponMapper;
    @Resource
    private MemberCouponMapper mcMapper;

    @Override
    public DataTable<SystemCoupon> dataTable(Staff staff, int start, int length, String keyword) {
        DataTable<SystemCoupon> dataTable = new DataTable<>();
        dataTable.setStart(start);
        dataTable.setLength(length);
        dataTable.setRecordsTotal(couponMapper.recordsTotal(staff, keyword));
        dataTable.setData(couponMapper.dataList(staff, start, length, keyword));
        return dataTable;
    }

    @Override
    public SystemCoupon findById(int id) {
        return couponMapper.findById(id);
    }

    @Override
    public void create(SystemCoupon coupon) {
        couponMapper.create(coupon);
    }

    @Override
    public void update(SystemCoupon coupon) {
        couponMapper.update(coupon);
    }

    @Override
    public void delete(int id) {
        couponMapper.destroy(id);
    }

    @Override
    @Transactional
    public void grant(SystemCoupon coupon, Grant grant) {
        List<MemberCoupon> mcList = new ArrayList<>();
        couponMapper.update(coupon);
        if (mcList.size() > 0)
            mcMapper.create(mcList);
    }
}
