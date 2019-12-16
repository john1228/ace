package com.ace.service.admin;

import com.ace.controller.admin.concerns.DataTable;
import com.ace.controller.admin.concerns.ReportCriteria;
import com.ace.entity.ChannelReport;
import com.ace.entity.Staff;

/**
 * @author john
 * @date 19-9-25 下午5:30
 */
public interface ChannelService {
    void data(Staff staff, DataTable<ChannelReport> dataTable, ReportCriteria criteria);
}
