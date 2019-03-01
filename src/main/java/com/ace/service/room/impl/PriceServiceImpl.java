package com.ace.service.room.impl;

import com.ace.controller.admin.concerns.DataTable;
import com.ace.dao.PriceMapper;
import com.ace.entity.room.Price;
import com.ace.service.room.PriceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("priceService")
public class PriceServiceImpl implements PriceService {

    @Resource
    private PriceMapper priceMapper;

    @Override
    public DataTable<Price> dataTable(int start, int length, String keyword) {
        DataTable<Price> dataTable = new DataTable<>();
        dataTable.setStart(start);
        dataTable.setLength(length);
        dataTable.setRecordsTotal(priceMapper.recordsTotal(keyword));
        dataTable.setData(priceMapper.dataList(start, length, keyword));
        return dataTable;
    }

    @Override
    public Price findById(int id) {
        return priceMapper.findById(id);
    }

    @Override
    public void create(Price price) {
        priceMapper.create(price);
    }

    @Override
    public void update(Price price) {
        priceMapper.update(price);
    }

    @Override
    public void delete(int id) {
        priceMapper.destroy(id);
    }
}
