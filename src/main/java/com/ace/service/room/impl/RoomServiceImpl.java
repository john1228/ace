package com.ace.service.room.impl;

import com.ace.controller.admin.concerns.DataTable;
import com.ace.dao.RoomMapper;
import com.ace.entity.room.Room;
import com.ace.service.room.RoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("roomService")
public class RoomServiceImpl implements RoomService {
    Logger logger = LoggerFactory.getLogger(RoomServiceImpl.class);

    @Resource
    private RoomMapper roomMapper;

    @Override
    public DataTable<Room> dataTable(int start, int length, String keyword) {
        DataTable<Room> dataTable = new DataTable<>();
        dataTable.setStart(start);
        dataTable.setLength(length);
        dataTable.setRecordsTotal(roomMapper.recordsTotal(keyword));
        dataTable.setData(roomMapper.dataList(start, length, keyword));
        return dataTable;
    }

    @Override
    public Room findById(int id) {
        return roomMapper.findById(id);
    }

    @Override
    public void create(Room room) {
        roomMapper.create(room);
    }

    @Override
    public void update(Room room) {
        logger.info("更新数据");
        roomMapper.update(room);
    }

    @Override
    public void delete(int id) {
        roomMapper.destroy(id);
    }
}
