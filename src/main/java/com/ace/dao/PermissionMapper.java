package com.ace.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ace.entity.Permission;
import com.ace.tk.TKMapper;

public interface PermissionMapper extends TKMapper<Permission> {
    List<Permission> getParentMenu();

    List<Permission> getChildPermissionByGroupId(@Param("groupId") Integer groupId);

    List<Permission> getChildPermissionByUserId(@Param("userId") Integer userId);

    List<Permission> getParentPermission();

    List<Permission> getPermissionByParentId(@Param("parentId") String parentId);

    Integer getCountByParentId(@Param("parentId") String parentId);
}
