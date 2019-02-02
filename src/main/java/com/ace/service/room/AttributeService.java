package com.ace.service.room;

import com.ace.controller.admin.concerns.DataTable;
import com.ace.entity.room.Attribute;

public interface AttributeService {

    DataTable<Attribute> dataTable(int start, int length, String keyword);

    Attribute findById(int id);

    void create(Attribute attribute);

    void update(Attribute attribute);

    void delete(int id);
}
