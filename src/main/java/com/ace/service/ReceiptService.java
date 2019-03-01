package com.ace.service;

import com.ace.controller.admin.concerns.DataTable;
import com.ace.entity.Receipt;

public interface ReceiptService {

    DataTable<Receipt> dataTable(int start, int length, String keyword);

    void create(Receipt receipt);

    Receipt findById(Integer id);
}
