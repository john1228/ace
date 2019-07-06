package com.ace.service.admin.impl;

import com.ace.controller.admin.concerns.DataTable;
import com.ace.controller.admin.concerns.PriceCriteria;
import com.ace.dao.PriceMapper;
import com.ace.entity.Staff;
import com.ace.entity.Price;
import com.ace.service.admin.PriceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("priceService")
public class PriceServiceImpl implements PriceService {

    @Resource
    private PriceMapper priceMapper;

    @Override
    public void dataTable(Staff staff, PriceCriteria criteria, DataTable<Price> dataTable) {
        dataTable.setRecordsFiltered(priceMapper.recordsTotal(staff, criteria));
        dataTable.setData(priceMapper.dataList(staff, criteria));
    }

    @Override
    public Price findById(Long id) {
        Price price = priceMapper.findById(id);
        return price;
    }

    @Override
    @Transactional
    public void create(Price price) {
        priceMapper.create(price);
        priceMapper.createRef(price.getId(), price.getRoomId());
    }

    @Override
    @Transactional
    public void update(Price price) {
        priceMapper.update(price);
        priceMapper.deleteRef(price.getId());
        priceMapper.createRef(price.getId(), price.getRoomId());
    }

    @Override
    public void delete(Long id) {
        priceMapper.delete(id);
        priceMapper.deleteRef(id);
    }
}
