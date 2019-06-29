package com.ace.service.admin.impl;

import com.ace.controller.admin.concerns.DataTable;
import com.ace.controller.admin.concerns.RoomCriteria;
import com.ace.dao.RoomFreeOrgMapper;
import com.ace.dao.RoomMapper;
import com.ace.dao.RoomSupportMapper;
import com.ace.entity.RoomFreeOrg;
import com.ace.entity.Staff;
import com.ace.entity.Room;
import com.ace.entity.RoomSupport;
import com.ace.service.admin.RoomService;
import com.ace.util.remote.Data;
import com.ace.util.remote.DataUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service("admin_room_service")
public class RoomServiceImpl implements RoomService {
    Logger logger = LoggerFactory.getLogger(RoomServiceImpl.class);

    @Resource
    private RoomMapper roomMapper;
    @Resource
    private RoomSupportMapper roomSupportMapper;
    @Resource
    private RoomFreeOrgMapper freeOrgMapper;

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
        List<RoomFreeOrg> orgList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            orgList.add(new RoomFreeOrg(id, String.valueOf(i), String.valueOf(i)));
        }
        freeOrgMapper.create(orgList);
        return roomMapper.findById(id);
    }

    @Override
    @Transactional
    public void create(Staff staff, Room room) {
        roomMapper.create(staff, room);
        List<RoomSupport> selectedSupport = room.getSupportList().stream().filter(item -> item.getSupportId() != null).collect(Collectors.toList());
        if (selectedSupport.size() > 0) {
            selectedSupport.forEach(item -> item.setRoomId(room.getId()));
            roomSupportMapper.create(selectedSupport);
        }
        List<RoomFreeOrg> freeOrgs = new ArrayList<>();
        List<Data> orgList = DataUtils.orgList(staff.getProjectId());
        orgList.forEach(data -> {
            if (room.getFreeOrg().contains(data.getId())) {
                freeOrgs.add(new RoomFreeOrg(room.getId(), data.getId(), data.getText()));
            }
        });
        if (freeOrgs.size() != 0)
            freeOrgMapper.create(freeOrgs);
    }

    @Override
    public void update(Staff staff, Room room) {
        roomMapper.update(room);
        roomSupportMapper.removeList(room.getId());
        List<RoomSupport> selectedSupport = room.getSupportList().stream().filter(item -> item.getSupportId() != null).collect(Collectors.toList());
        if (selectedSupport.size() > 0) {
            selectedSupport.forEach(item -> item.setRoomId(room.getId()));
            roomSupportMapper.create(selectedSupport);
        }
        List<RoomFreeOrg> freeOrgs = new ArrayList<>();
        List<Data> orgList = DataUtils.orgList(staff.getProjectId());
        orgList.forEach(data -> {
            if (room.getFreeOrg().contains(data.getId())) {
                freeOrgs.add(new RoomFreeOrg(room.getId(), data.getId(), data.getText()));
            }
        });
        if (freeOrgs.size() != 0)
            freeOrgMapper.create(freeOrgs);
    }

    @Override
    public void enable(Staff staff, Long id) {
        roomMapper.enable(id);
    }

    @Override
    public void disable(Staff staff, Long id) {
        roomMapper.disable(id);
    }

    @Override
    public List<RoomSupport> roomSupports(Long roomId) {
        return roomSupportMapper.supportList(roomId);
    }

}
