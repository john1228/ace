package com.ace.dao;

import com.ace.entity.room.Room;
import com.ace.tk.TKMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface RoomMapper extends TKMapper<Room> {
    List<Room> dataList(@Param("staff") int staff, @Param("start") int start, @Param("length") int length, @Param("keyword") String keyword);

    Integer recordsTotal(@Param("staff") int staff, @Param("keyword") String keyword);

    void create(Room room);

    Room findById(@Param("id") int id);

    void update(Room room);

    void destroy(int id);
}
