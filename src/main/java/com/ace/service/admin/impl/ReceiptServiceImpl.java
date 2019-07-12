package com.ace.service.admin.impl;

import com.ace.controller.admin.concerns.DataTable;
import com.ace.controller.admin.concerns.ReceiptCriteria;
import com.ace.dao.ReceiptMapper;
import com.ace.entity.Receipt;
import com.ace.entity.ReceiptDetail;
import com.ace.entity.Staff;
import com.ace.service.admin.ReceiptService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service("receiptService")
public class ReceiptServiceImpl implements ReceiptService {
    @Resource
    private ReceiptMapper receiptMapper;

    @Override
    public void dataTable(Staff staff, ReceiptCriteria criteria, DataTable<ReceiptDetail> dataTable) {
        dataTable.setRecordsFiltered(receiptMapper.recordsTotal(staff, criteria));
        dataTable.setData(receiptMapper.dataList(staff, criteria));
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
