package com.ace.service.admin;

import com.ace.controller.admin.concerns.DataTable;
import com.ace.entity.RoomClosed;

/**
 * @author john
 * @date 19-5-20 上午11:44
 */
public interface RoomClosedService {
    void data(Long roomId, DataTable<RoomClosed> dataTable);

    void create(RoomClosed roomClosed);

    void delete(Long roomId);
}
