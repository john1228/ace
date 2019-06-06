package com.ace.service.api;

import com.ace.entity.Account;
import com.ace.entity.RoomReport;

import java.sql.Date;
import java.util.List;

public interface ReportService {
    List<RoomReport> reportList(Account account, Date from, Date to);
}
