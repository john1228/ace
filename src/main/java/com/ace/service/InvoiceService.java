package com.ace.service;

import com.ace.controller.admin.concerns.DataTable;
import com.ace.entity.Invoice;

public interface InvoiceService {

    DataTable<Invoice> dataTable(int start, int length, String keyword);

    Invoice findById(int id);

    void create(Invoice invoice);

    void update(Invoice invoice);
}
