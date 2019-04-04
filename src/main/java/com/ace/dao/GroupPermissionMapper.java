package com.ace.dao;

import org.apache.ibatis.annotations.Param;

import com.ace.entity.GroupPermission;

public interface GroupPermissionMapper {
    int deletePermissionByGroupId(@Param("groupId") Integer groupId);
}
