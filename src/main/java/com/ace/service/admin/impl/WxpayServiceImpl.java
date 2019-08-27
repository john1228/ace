package com.ace.service.admin.impl;

import com.ace.controller.admin.concerns.DataTable;
import com.ace.controller.admin.concerns.PayCriteria;
import com.ace.dao.AlipayMapper;
import com.ace.dao.WxpayMapper;
import com.ace.entity.Alipay;
import com.ace.entity.Wxpay;
import com.ace.service.admin.AlipayService;
import com.ace.service.admin.WxpayService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("admin_wxpay_service")
public class WxpayServiceImpl implements WxpayService {

    @Resource
    private WxpayMapper wxpayMapper;

    @Override
    public void data(DataTable<Wxpay> dataTable, PayCriteria criteria) {
        dataTable.setRecordsFiltered(wxpayMapper.recordsTotal(criteria));
        dataTable.setData(wxpayMapper.dataList(criteria));
    }

    @Override
    public Wxpay findById(String projectId) {
        return wxpayMapper.findBy(projectId);
    }

    @Override
    public void create(Wxpay wxpay) {
        wxpayMapper.create(wxpay);
    }

    @Override
    public void update(Wxpay wxpay) {
        wxpayMapper.update(wxpay);
    }

    @Override
    public void delete(String projectId) {
        wxpayMapper.delete(projectId);
    }
}
