package com.ace.service.impl;

import com.ace.controller.admin.concerns.DataTable;
import com.ace.dao.InvoiceMapper;
import com.ace.dao.OrderMapper;
import com.ace.entity.Invoice;
import com.ace.entity.Order;
import com.ace.service.InvoiceService;
import com.ace.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("invoiceService")
public class InvoiceServiceImpl implements InvoiceService {
    Logger logger = LoggerFactory.getLogger(InvoiceServiceImpl.class);

    @Resource
    private InvoiceMapper invoiceMapper;

    @Override
    public DataTable<Invoice> dataTable(int start, int length, String keyword) {
        DataTable<Invoice> dataTable = new DataTable<>();
        dataTable.setStart(start);
        dataTable.setLength(length);
        dataTable.setRecordsTotal(invoiceMapper.recordsTotal(keyword));
        dataTable.setData(invoiceMapper.dataList(start, length, keyword));
        return dataTable;
    }

    @Override
    public Invoice findById(int id) {
        return invoiceMapper.findById(id);
    }

    @Override
    public void create(Invoice invoice) {
        invoiceMapper.create(invoice);
    }

    @Override
    public void update(Invoice invoice) {
        invoiceMapper.update(invoice);
    }
}
