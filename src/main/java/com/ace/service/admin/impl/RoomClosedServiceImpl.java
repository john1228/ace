package com.ace.service.admin.impl;

import com.ace.controller.admin.concerns.DataTable;
import com.ace.dao.RoomClosedMapper;
import com.ace.entity.RoomClosed;
import com.ace.service.admin.RoomClosedService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author john
 * @date 19-5-20 上午11:48
 */
@Service("admin_room_closed_service")
public class RoomClosedServiceImpl implements RoomClosedService {
    @Resource
    RoomClosedMapper rcMapper;

    @Override
    public void data(Long roomId, DataTable<RoomClosed> dataTable) {
        dataTable.setRecordsFiltered(rcMapper.recordsTotal(roomId));
        dataTable.setData(rcMapper.dataList(roomId, dataTable.getStart(), dataTable.getLength()));
    }

    @Override
    public void create(RoomClosed roomClosed) {
        rcMapper.create(roomClosed);
    }

    @Override
    public void delete(Long roomId) {
        rcMapper.delete(roomId);
    }
}
