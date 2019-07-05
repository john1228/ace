package com.ace.service.api;

import com.ace.controller.api.concerns.Query;
import com.ace.entity.Account;
import com.ace.entity.Room;
import com.ace.entity.RoomReport;
import com.ace.entity.Schedule;

import java.sql.Date;
import java.util.List;

public interface RoomService {
    List<Room> query(Account account, Query query);

    Room show(Long id);

    List<Schedule> schedule(Long room, Date date);
}
