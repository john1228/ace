package com.ace.service.room.impl;

import com.ace.controller.admin.concerns.DataTable;
import com.ace.dao.SupportMapper;
import com.ace.entity.Staff;
import com.ace.entity.room.Support;
import com.ace.service.room.SupportService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("supportService")
public class SupportServiceImpl implements SupportService {

    @Resource
    private SupportMapper deviceMapper;

    @Override
    public DataTable<Support> dataTable(Staff staff, int start, int length, String keyword) {
        DataTable<Support> dataTable = new DataTable<>();
        dataTable.setStart(start);
        dataTable.setLength(length);
        dataTable.setRecordsTotal(deviceMapper.recordsTotal(staff, keyword));
        dataTable.setData(deviceMapper.dataList(staff, start, length, keyword));
        return dataTable;
    }

    @Override
    public Support findById(int id) {
        return deviceMapper.findById(id);
    }

    @Override
    public void create(Support device) {
        deviceMapper.create(device);
    }

    @Override
    public void update(Support device) {
        deviceMapper.update(device);
    }

    @Override
    public void delete(int id) {
        deviceMapper.destroy(id);
    }
}
