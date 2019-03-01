package com.ace.service.room.impl;

import com.ace.controller.admin.concerns.DataTable;
import com.ace.dao.AttributeMapper;
import com.ace.entity.room.Attribute;
import com.ace.service.room.AttributeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("attributeService")
public class AttributeServiceImpl implements AttributeService {
    Logger logger = LoggerFactory.getLogger(AttributeServiceImpl.class);

    @Resource
    private AttributeMapper attributeMapper;

    @Override
    public DataTable<Attribute> dataTable(int start, int length, String keyword) {
        DataTable<Attribute> dataTable = new DataTable<>();
        dataTable.setStart(start);
        dataTable.setLength(length);
        dataTable.setRecordsTotal(attributeMapper.recordsTotal(keyword));
        dataTable.setData(attributeMapper.dataList(start, length, keyword));
        return dataTable;
    }

    @Override
    public Attribute findById(int id) {
        return attributeMapper.findById(id);
    }

    @Override
    public void create(Attribute attribute) {
        attributeMapper.create(attribute);
    }

    @Override
    public void update(Attribute attribute) {
        logger.info("更新数据");
        attributeMapper.update(attribute);
    }

    @Override
    public void delete(int id) {
        attributeMapper.destroy(id);
    }
}
