package com.ace.service.coupon.impl;

import com.ace.controller.admin.concerns.DataTable;
import com.ace.dao.CouponMapper;
import com.ace.entity.coupon.Coupon;
import com.ace.service.coupon.CouponService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("couponService")
public class CouponServiceImpl implements CouponService {
    Logger logger = LoggerFactory.getLogger(CouponServiceImpl.class);

    @Resource
    private CouponMapper couponMapper;

    @Override
    public DataTable<Coupon> dataTable(int start, int length, String keyword) {
        DataTable<Coupon> dataTable = new DataTable<>();
        dataTable.setStart(start);
        dataTable.setLength(length);
        dataTable.setRecordsTotal(couponMapper.recordsTotal(keyword));
        dataTable.setData(couponMapper.dataList(start, length, keyword));
        return dataTable;
    }

    @Override
    public Coupon findById(int id) {
        return couponMapper.findById(id);
    }

    @Override
    public void create(Coupon coupon) {
        couponMapper.create(coupon);
    }

    @Override
    public void update(Coupon coupon) {
        logger.info("更新数据");
        couponMapper.update(coupon);
    }

    @Override
    public void delete(int id) {
        couponMapper.destroy(id);
    }
}
