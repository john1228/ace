package com.ace.service.admin;

import com.ace.controller.admin.concerns.DataTable;
import com.ace.controller.admin.concerns.RefundCriteria;
import com.ace.entity.RefundApplication;
import com.ace.entity.Staff;

import java.math.BigDecimal;

/**
 * @author john
 * @date 19-8-8 下午12:19
 */
public interface RefundService {

    void dataTable(Staff staff, RefundCriteria criteria, DataTable<RefundApplication> dataTable);

    void agree(Staff staff, Long id, BigDecimal confirmAmt);

    void reject(Staff staff, Long id);
}
