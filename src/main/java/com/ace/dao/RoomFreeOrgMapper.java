package com.ace.dao;

import com.ace.entity.RoomFreeOrg;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface RoomFreeOrgMapper {
    void create(@Param("orgs") List<RoomFreeOrg> supports);

    List<RoomFreeOrg> freeOrgs(@Param("room") Long room);

    void deleteOrgs(@Param("room") Long room);
}
