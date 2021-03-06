package com.ace.service.admin;

import com.ace.controller.admin.concerns.DataTable;
import com.ace.controller.admin.concerns.RoomCriteria;
import com.ace.entity.RoomSupport;
import com.ace.entity.Staff;
import com.ace.entity.Room;

import java.util.List;

public interface RoomService {

    void data(Staff staff, DataTable<Room> dataTable, RoomCriteria criteria);

    List<Room> roomList(Staff staff);

    Room findById(Long id);

    void create(Staff staff, Room room);

    void update(Staff staff, Room room);

    void enable(Staff staff, Long id);

    void disable(Staff staff, Long id);

    List<RoomSupport> roomSupports(Long roomId);

    boolean isExists(Staff staff, String serialNo, Long selfId);
}
