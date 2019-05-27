package com.ace.dao;

import com.ace.entity.Staff;
import com.ace.entity.RoomSupport;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface RoomSupportMapper {
    void create(@Param("staff") Staff staff, @Param("supports") List<RoomSupport> supports);

    List<RoomSupport> supportList(@Param("room") Long room);
}
