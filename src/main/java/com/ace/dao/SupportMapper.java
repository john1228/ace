package com.ace.dao;

import com.ace.controller.admin.concerns.SupportCriteria;
import com.ace.entity.Staff;
import com.ace.entity.Support;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface SupportMapper {
    List<Support> dataList(@Param("staff") Staff staff, @Param("criteria") SupportCriteria criteria);

    Long recordsTotal(@Param("staff") Staff staff, @Param("criteria") SupportCriteria criteria);

    void create(@Param("staff") Staff staff, @Param("support") Support support);

    Support findById(@Param("id") int id);

    void update(@Param("staff") Staff staff, @Param("support") Support support);

    void destroy(int id);

    List<Support> all(@Param("staff") Staff staff);
}
