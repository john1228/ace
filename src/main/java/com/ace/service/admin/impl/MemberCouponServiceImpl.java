package com.ace.service.admin.impl;

import com.ace.controller.admin.concerns.DataTable;
import com.ace.dao.MemberCouponMapper;
import com.ace.entity.MemberCoupon;
import com.ace.service.admin.MemberCouponService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("mcService")
public class MemberCouponServiceImpl implements MemberCouponService {
    Logger logger = LoggerFactory.getLogger(MemberCouponServiceImpl.class);

    @Resource
    private MemberCouponMapper mcMapper;

    @Override
    public DataTable<MemberCoupon> dataTable(Long couponId, int start, int length, String keyword) {
        DataTable<MemberCoupon> dataTable = new DataTable<>();
        dataTable.setStart(start);
        dataTable.setLength(length);
        dataTable.setRecordsFiltered(mcMapper.recordsTotal(couponId, keyword));
        dataTable.setData(mcMapper.dataList(couponId, start, length, keyword));
        return dataTable;
    }

    @Override
    public MemberCoupon findById(Long id) {
        return mcMapper.findById(id);
    }


    @Override
    public void update(MemberCoupon mc) {
        logger.info("更新数据");
        mcMapper.update(mc);
    }
}
