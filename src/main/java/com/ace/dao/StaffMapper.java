package com.ace.dao;

import com.ace.entity.Staff;
import com.ace.tk.TKMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface StaffMapper extends TKMapper<Staff> {

    void create(List<Staff> staffList);

    List<Staff> relatedStaffList(@Param("project") String projectId, @Param("org") String orgId);

}
