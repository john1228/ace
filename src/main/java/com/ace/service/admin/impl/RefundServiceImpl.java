package com.ace.service.admin.impl;

import com.ace.controller.admin.concerns.DataTable;
import com.ace.controller.admin.concerns.RefundCriteria;
import com.ace.dao.RefundMapper;
import com.ace.entity.RefundApplication;
import com.ace.entity.Staff;
import com.ace.service.admin.RefundService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author john
 * @date 19-8-8 下午12:20
 */
@Service("admin-refund-service")
public class RefundServiceImpl implements RefundService {

    @Resource
    private RefundMapper refundMapper;

    @Override
    public void dataTable(Staff staff, RefundCriteria criteria, DataTable<RefundApplication> dataTable) {
        dataTable.setRecordsFiltered(refundMapper.recordsTotal(staff, criteria));
        dataTable.setData(refundMapper.dataList(staff, criteria, dataTable.getStart(), dataTable.getLength()));
    }

    @Override
    public void agree(Staff staff, Long id, BigDecimal confirmAmt) {
        refundMapper.agree(staff, id, confirmAmt);
    }

    @Override
    public void reject(Staff staff, Long id) {
        refundMapper.reject(staff, id);
    }
}
