package com.ace.service.admin.impl;

import com.ace.controller.admin.concerns.DataTable;
import com.ace.dao.PriceMapper;
import com.ace.entity.Staff;
import com.ace.entity.Price;
import com.ace.entity.Room;
import com.ace.service.admin.PriceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service("priceService")
public class PriceServiceImpl implements PriceService {

    @Resource
    private PriceMapper priceMapper;

    @Override
    public DataTable<Price> dataTable(Staff staff, int start, int length, String keyword) {
        DataTable<Price> dataTable = new DataTable<>();
        dataTable.setStart(start);
        dataTable.setLength(length);
        dataTable.setRecordsFiltered(priceMapper.recordsTotal(staff, keyword));
        dataTable.setData(priceMapper.dataList(staff, start, length, keyword));
        return dataTable;
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
        priceMapper.deleteRef(price.getId());
        priceMapper.update(price);
        priceMapper.createRef(price.getId(), price.getRoomId());
    }

    @Override
    public void delete(Long id) {
        priceMapper.delete(id);
    }
}
