package com.ace.service.admin.impl;

import com.ace.controller.admin.concerns.CouponCriteria;
import com.ace.controller.admin.concerns.DataTable;
import com.ace.dao.CouponMapper;
import com.ace.dao.MemberCouponMapper;
import com.ace.entity.Grant;
import com.ace.entity.SystemCoupon;
import com.ace.entity.MemberCoupon;
import com.ace.entity.Staff;
import com.ace.service.admin.CouponService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("admin-coupon-service")
public class CouponServiceImpl implements CouponService {

    @Resource
    private CouponMapper couponMapper;
    @Resource
    private MemberCouponMapper mcMapper;

    @Override
    public void data(Staff staff, DataTable<SystemCoupon> dataTable, CouponCriteria criteria) {
        dataTable.setRecordsFiltered(couponMapper.recordsTotal(staff, criteria));
        dataTable.setData(couponMapper.dataList(staff, criteria, dataTable.getStart(), dataTable.getLength()));
    }

    @Override
    public SystemCoupon findById(int id) {
        return couponMapper.findById(id);
    }

    @Override
    public void create(Staff staff, SystemCoupon coupon) {
        couponMapper.create(staff, coupon);
    }

    @Override
    public void update(SystemCoupon coupon) {
        couponMapper.update(coupon);
    }

    @Override
    public void delete(int id) {
        couponMapper.destroy(id);
    }

    //发放优惠券
    @Override
    @Transactional
    public void grant(SystemCoupon coupon, Grant grant) {
        List<MemberCoupon> mcList = new ArrayList<>();
        for (int i = 0; i < grant.getAmount(); i++) {
            mcList.add(coupon.grant(grant.getOrgId(), grant.getEmpId()));
        }
        if (mcList.size() > 0)
            mcMapper.grant(mcList);
    }
}
