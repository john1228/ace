package com.ace.service.admin;

import com.ace.controller.admin.concerns.DataTable;
import com.ace.entity.Staff;
import com.ace.entity.Price;
import com.ace.entity.Room;

import java.util.Date;
import java.util.List;


public interface PriceService {

    DataTable<Price> dataTable(Staff staff, int start, int length, String keyword);

    Price findById(Long id);

    void create(Price price);

    void update(Price price);

    void delete(Long id);
}
