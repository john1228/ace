package com.ace.service.room;

import com.ace.controller.admin.concerns.DataTable;
import com.ace.entity.Staff;
import com.ace.entity.room.Support;

public interface SupportService {

    DataTable<Support> dataTable(Staff staff, int start, int length, String keyword);

    Support findById(int id);

    void create(Support device);

    void update(Support device);

    void delete(int id);
}
