package com.ace.service.room;

import com.ace.controller.admin.concerns.DataTable;
import com.ace.entity.room.Device;

public interface DeviceService {

    DataTable<Device> dataTable(int start, int length, String keyword);

    Device findById(int id);

    void create(Device device);

    void update(Device device);

    void delete(int id);
}
