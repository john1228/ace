package com.ace.service.coupon.impl;

import com.ace.controller.admin.concerns.DataTable;
import com.ace.dao.MemberCouponMapper;
import com.ace.entity.coupon.MemberCoupon;
import com.ace.service.coupon.MemberCouponService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("memberCouponService")
public class MemberCouponServiceImpl implements MemberCouponService {
    Logger logger = LoggerFactory.getLogger(MemberCouponServiceImpl.class);

    @Resource
    private MemberCouponMapper mcMapper;

    @Override
    public DataTable<MemberCoupon> dataTable(int start, int length, String keyword) {
        DataTable<MemberCoupon> dataTable = new DataTable<>();
        dataTable.setStart(start);
        dataTable.setLength(length);
        dataTable.setRecordsTotal(mcMapper.recordsTotal(keyword));
        dataTable.setData(mcMapper.dataList(start, length, keyword));
        return dataTable;
    }

    @Override
    public MemberCoupon findById(int id) {
        return mcMapper.findById(id);
    }


    @Override
    public void update(MemberCoupon mc) {
        logger.info("更新数据");
        mcMapper.update(mc);
    }
}
