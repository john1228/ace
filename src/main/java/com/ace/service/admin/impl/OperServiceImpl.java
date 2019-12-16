package com.ace.service.admin.impl;

import com.ace.controller.admin.concerns.DataTable;
import com.ace.controller.admin.concerns.ReportCriteria;
import com.ace.dao.handler.RoomReportMapper;
import com.ace.entity.OperReport;
import com.ace.entity.Staff;
import com.ace.service.admin.OperService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author john
 * @date 19-9-25 下午5:30
 */
@Service("admin-operation-service")
public class OperServiceImpl implements OperService {

    @Resource
    private RoomReportMapper rrMapper;

    @Override
    public void data(Staff staff, DataTable<OperReport> dataTable, ReportCriteria criteria) {
        dataTable.setRecordsFiltered(rrMapper.recordsTotal(staff, criteria));
        dataTable.setData(rrMapper.operationReport(staff, criteria));
    }
}
