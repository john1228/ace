package com.ace.service.admin.impl;

import com.ace.controller.admin.concerns.DataTable;
import com.ace.dao.ReceiptMapper;
import com.ace.entity.Receipt;
import com.ace.service.admin.ReceiptService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("receiptService")
public class ReceiptServiceImpl implements ReceiptService {
    @Resource
    private ReceiptMapper receiptMapper;

    @Override
    public DataTable<Receipt> dataTable(int start, int length, String keyword) {
        DataTable<Receipt> dataTable = new DataTable<>();
        dataTable.setStart(start);
        dataTable.setLength(length);
        dataTable.setRecordsFiltered(receiptMapper.recordsTotal(keyword));
        dataTable.setData(receiptMapper.dataList(start, length, keyword));
        return dataTable;
    }


    @Override
    public void create(Receipt receipt) {
        receiptMapper.create(receipt);
    }

    @Override
    public Receipt findById(Integer id) {
        return receiptMapper.findById(id);
    }
}
