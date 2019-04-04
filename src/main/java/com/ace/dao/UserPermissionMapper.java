package com.ace.dao;

import org.apache.ibatis.annotations.Param;

public interface UserPermissionMapper {
    int deletePermissionByUserId(@Param("userId") Integer userId);
}
