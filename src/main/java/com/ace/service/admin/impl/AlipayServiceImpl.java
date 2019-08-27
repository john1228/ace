package com.ace.service.admin.impl;

import com.ace.controller.admin.concerns.DataTable;
import com.ace.controller.admin.concerns.PayCriteria;
import com.ace.dao.AlipayMapper;
import com.ace.entity.*;
import com.ace.service.admin.AlipayService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("admin_alipay_service")
public class AlipayServiceImpl implements AlipayService {

    @Resource
    private AlipayMapper alipayMapper;

    @Override
    public void data(DataTable<Alipay> dataTable, PayCriteria criteria) {
        dataTable.setRecordsFiltered(alipayMapper.recordsTotal(criteria));
        dataTable.setData(alipayMapper.dataList(criteria));
    }

    @Override
    public Alipay findById(String projectId) {
        return alipayMapper.findBy(projectId);
    }

    @Override
    public void create(Alipay alipay) {
        alipayMapper.create(alipay);
    }

    @Override
    public void update(Alipay alipay) {
        alipayMapper.update(alipay);
    }

    @Override
    public void delete(String projectId) {

    }
}
