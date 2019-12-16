package com.ace.service.api;

import com.ace.entity.Account;
import com.ace.entity.DataReport;

import java.sql.Date;
import java.util.List;

public interface ReportService {
    List<DataReport> reportList(Account account, Date from, Date to);
}
