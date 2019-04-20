package com.ace.service.room;

import com.ace.controller.admin.concerns.DataTable;
import com.ace.entity.Staff;
import com.ace.entity.room.Room;

import java.util.List;

public interface RoomService {

    DataTable<Room> dataTable(Staff staff, int start, int length, String keyword);

    List<Room> roomList(Staff staff);

    Room findById(int id);

    void create(Room room);

    void update(Room room);

    void delete(int id);
}
