package com.ace.service.api.impl;

import com.ace.dao.handler.RoomReportMapper;
import com.ace.entity.Account;
import com.ace.entity.RoomReport;
import com.ace.service.api.ReportService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.List;

@Service("api-report-service")
public class ReportServiceImpl implements ReportService {

    @Resource
    RoomReportMapper reportMapper;

    @Override
    public List<RoomReport> reportList(Account account, Date from, Date to) {
        return reportMapper.reports(account, from, to);
    }
}
