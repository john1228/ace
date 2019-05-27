package com.ace.service.admin.impl;

import com.ace.controller.admin.concerns.DataTable;
import com.ace.controller.admin.concerns.RoomCriteria;
import com.ace.dao.RoomMapper;
import com.ace.dao.RoomSupportMapper;
import com.ace.entity.Staff;
import com.ace.entity.Room;
import com.ace.entity.RoomSupport;
import com.ace.service.admin.RoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service("admin_room_service")
public class RoomServiceImpl implements RoomService {
    Logger logger = LoggerFactory.getLogger(RoomServiceImpl.class);

    @Resource
    private RoomMapper roomMapper;

    @Resource
    private RoomSupportMapper roomSupportMapper;

    @Override
    public void data(Staff staff, DataTable<Room> dataTable, RoomCriteria criteria) {
        dataTable.setRecordsFiltered(roomMapper.recordsTotal(staff, criteria));
        dataTable.setData(roomMapper.dataList(staff, dataTable.getStart(), dataTable.getLength(), criteria));
    }

    @Override
    public List<Room> roomList(Staff staff) {
        return roomMapper.roomList(staff);
    }

    @Override
    public Room findById(Long id) {
        return roomMapper.findById(id);
    }

    @Override
    @Transactional
    public void create(Staff staff, Room room) {
        roomMapper.create(room);
        List<RoomSupport> selectedSupport = room.getSupportList().stream().filter(item -> item.getSupportId() != null).collect(Collectors.toList());
        selectedSupport.forEach(item -> item.setRoomId(room.getId()));
        roomSupportMapper.create(staff, selectedSupport);
    }

    @Override
    public void update(Staff staff, Room room) {
        logger.info("更新数据");
        roomMapper.update(room);
        List<RoomSupport> selectedSupport = room.getSupportList().stream().filter(item -> item.getSupportId() != null).collect(Collectors.toList());
        selectedSupport.forEach(item -> item.setRoomId(room.getId()));
        roomSupportMapper.create(staff, selectedSupport);
    }

    @Override
    public void delete(Staff staff, Long id) {
        roomMapper.destroy(id);
    }

}
