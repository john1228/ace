package com.ace.dao;

import com.ace.entity.Staff;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;


public interface StaffMapper {

    void create(List<Staff> staffList);

    List<Staff> relatedStaffList(@Param("project") String projectId, @Param("org") String orgId);

    Staff findById(@Param("id") Long staffId);

    Staff manager(@Param("room") Long roomId);
}
