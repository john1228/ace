package com.ace.service.admin;

import com.ace.controller.admin.concerns.DataTable;
import com.ace.controller.admin.concerns.ReportCriteria;
import com.ace.entity.OperReport;
import com.ace.entity.Staff;

/**
 * @author john
 * @date 19-9-25 下午5:30
 */
public interface OperService {
    void data(Staff staff, DataTable<OperReport> dataTable, ReportCriteria criteria);
}
