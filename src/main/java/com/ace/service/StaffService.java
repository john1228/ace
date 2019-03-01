package com.ace.service;

import com.ace.entity.Staff;

import java.util.List;


public interface StaffService {
    void create(List<Staff> staffList);

    List<Staff> relatedStaffs(String projectId, String orgId);
}
