package com.ace.service.admin;

import com.ace.controller.admin.concerns.DataTable;
import com.ace.controller.admin.concerns.OperLogCriteria;
import com.ace.entity.OperLog;

public interface OperLogService {

    void dataTable(OperLogCriteria criteria, DataTable<OperLog> dataTable);

    void create(OperLog operLog);

}
