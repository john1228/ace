package com.ace.service.admin;

import com.ace.controller.admin.concerns.DataTable;
import com.ace.controller.admin.concerns.ReceiptCriteria;
import com.ace.entity.Receipt;
import com.ace.entity.ReceiptDetail;
import com.ace.entity.Staff;

public interface ReceiptService {

    void dataTable(Staff staff, ReceiptCriteria criteria, DataTable<ReceiptDetail> dataTable);

    void create(Receipt receipt);

    Receipt findById(Integer id);
}
