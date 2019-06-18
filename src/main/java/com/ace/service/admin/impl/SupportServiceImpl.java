package com.ace.service.admin.impl;

import com.ace.controller.admin.concerns.DataTable;
import com.ace.controller.admin.concerns.SupportCriteria;
import com.ace.dao.SupportMapper;
import com.ace.entity.Staff;
import com.ace.entity.Support;
import com.ace.service.admin.SupportService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("supportService")
public class SupportServiceImpl implements SupportService {

    @Resource
    private SupportMapper supportMapper;

    @Override
    public void dataTable(Staff staff, SupportCriteria criteria, DataTable<Support> dataTable) {
        dataTable.setRecordsFiltered(supportMapper.recordsTotal(staff, criteria));
        dataTable.setData(supportMapper.dataList(staff, criteria));
    }

    @Override
    public Support findById(int id) {
        return supportMapper.findById(id);
    }

    @Override
    public void create(Staff staff, Support support) {
        supportMapper.create(staff, support);
    }

    @Override
    public void update(Staff staff, Support support) {
        supportMapper.update(staff, support);
    }

    @Override
    public void delete(int id) {
        supportMapper.destroy(id);
    }

    @Override
    public List<Support> supportList(Staff staff) {
        return supportMapper.all(staff);
    }
}
