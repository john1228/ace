package com.ace.service.admin.impl;

import com.ace.controller.admin.concerns.DataTable;
import com.ace.controller.admin.concerns.OperLogCriteria;
import com.ace.dao.OperLogMapper;
import com.ace.dao.SupportMapper;
import com.ace.entity.OperLog;
import com.ace.entity.Order;
import com.ace.entity.Staff;
import com.ace.entity.Support;
import com.ace.service.admin.OperLogService;
import com.ace.service.admin.SupportService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("admin-oper-log-service")
public class OperLogServiceImpl implements OperLogService {
    @Resource
    OperLogMapper loggerMapper;

    @Override
    public void dataTable(OperLogCriteria criteria, DataTable<OperLog> dataTable) {
        criteria.setStart(dataTable.getStart());
        criteria.setLength(dataTable.getLength());
        dataTable.setData(loggerMapper.dataList(criteria));
        dataTable.setRecordsFiltered(loggerMapper.recordsTotal(criteria));
    }

    @Override
    public void create(OperLog operLog) {
        loggerMapper.create(operLog);
    }
}
