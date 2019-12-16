package com.ace.service.admin;

import com.ace.controller.admin.concerns.DataTable;
import com.ace.controller.admin.concerns.InvoiceCriteria;
import com.ace.entity.Invoice;
import com.ace.entity.InvoiceOrder;
import com.ace.entity.Staff;

public interface InvoiceService {

    void dataTable(Staff staff, InvoiceCriteria criteria, DataTable<InvoiceOrder> dataTable);

    Invoice findByOrderNo(String orderNo);

    void create(String orderNo, Invoice invoice);

    void update(Invoice invoice);

    InvoiceOrder findOrder(String orderNo);

    void mail(String orderNo, Invoice invoice);
}
