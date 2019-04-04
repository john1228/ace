package com.ace.dao;

import org.apache.ibatis.annotations.Param;

import com.ace.entity.Group;

public interface GroupMapper {
    Group queryByName(@Param("name") String name);
}
