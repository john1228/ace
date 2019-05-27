package com.ace.service.admin;

import com.ace.controller.admin.concerns.DataTable;
import com.ace.entity.Staff;
import com.ace.entity.Support;

import java.util.List;

public interface SupportService {

    DataTable<Support> dataTable(Staff staff, int start, int length, String keyword);

    Support findById(int id);

    void create(Support device);

    void update(Support device);

    void delete(int id);

    List<Support> supportList(Staff staff);
}
