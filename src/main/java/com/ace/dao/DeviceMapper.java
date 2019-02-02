package com.ace.dao;

import com.ace.entity.room.Attribute;
import com.ace.entity.room.Device;
import com.ace.tk.TKMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface DeviceMapper extends TKMapper<Attribute> {
    List<Device> dataList(@Param("start") int start, @Param("length") int length, @Param("keyword") String keyword);

    Integer recordsTotal(@Param("keyword") String keyword);

    void create(Device device);

    Device findById(@Param("id") int id);

    void update(Device device);

    void destroy(int id);
}
