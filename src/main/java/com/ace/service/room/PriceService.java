package com.ace.service.room;

import com.ace.controller.admin.concerns.DataTable;
import com.ace.entity.room.Price;

public interface PriceService {

    DataTable<Price> dataTable(int start, int length, String keyword);

    Price findById(int id);

    void create(Price price);

    void update(Price price);

    void delete(int id);
}
