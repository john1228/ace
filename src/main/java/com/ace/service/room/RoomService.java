package com.ace.service.room;

import com.ace.controller.admin.concerns.DataTable;
import com.ace.entity.Staff;
import com.ace.entity.room.Room;

public interface RoomService {

    DataTable<Room> dataTable(Staff staff, int start, int length, String keyword);

    Room findById(int id);

    void create(Room room);

    void update(Room room);

    void delete(int id);
}
