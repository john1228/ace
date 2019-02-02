package com.ace.service.room.impl;

import com.ace.controller.admin.concerns.DataTable;
import com.ace.dao.DeviceMapper;
import com.ace.dao.RoomMapper;
import com.ace.entity.room.Device;
import com.ace.entity.room.Room;
import com.ace.service.room.DeviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("deviceService")
public class DeviceServiceImpl implements DeviceService {
    Logger logger = LoggerFactory.getLogger(DeviceServiceImpl.class);

    @Resource
    private DeviceMapper deviceMapper;

    @Override
    public DataTable<Device> dataTable(int start, int length, String keyword) {
        DataTable<Device> dataTable = new DataTable<>();
        dataTable.setStart(start);
        dataTable.setLength(length);
        dataTable.setRecordsTotal(deviceMapper.recordsTotal(keyword));
        dataTable.setData(deviceMapper.dataList(start, length, keyword));
        return dataTable;
    }

    @Override
    public Device findById(int id) {
        return deviceMapper.findById(id);
    }

    @Override
    public void create(Device device) {
        deviceMapper.create(device);
    }

    @Override
    public void update(Device device) { deviceMapper.update(device); }

    @Override
    public void delete(int id) {
        deviceMapper.destroy(id);
    }
}
