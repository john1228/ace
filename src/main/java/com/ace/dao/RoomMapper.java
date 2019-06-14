package com.ace.dao;

import com.ace.controller.admin.concerns.RoomCriteria;
import com.ace.controller.api.concerns.Query;
import com.ace.entity.Account;
import com.ace.entity.Staff;
import com.ace.entity.Room;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface RoomMapper {
    Long recordsTotal(@Param("staff") Staff staff, @Param("criteria") RoomCriteria criteria);

    List<Room> dataList(@Param("staff") Staff staff, @Param("start") int start, @Param("length") int length, @Param("criteria") RoomCriteria criteria);

    List<Room> roomList(@Param("staff") Staff staff);

    void create(@Param("staff") Staff staff, @Param("room") Room room);

    Room findById(@Param("id") Long id);

    void update(Room room);

    void destroy(Long id);

    List<Room> query(@Param("account") Account account, @Param("query") Query query);
}
