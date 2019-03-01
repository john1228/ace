package com.ace.service.impl;

import com.ace.dao.StaffMapper;
import com.ace.entity.Staff;
import com.ace.service.StaffService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("staffService")
public class StaffServiceImpl implements StaffService {

    @Resource
    StaffMapper staffMapper;

    @Override
    public void create(List<Staff> staffList) {
        staffMapper.create(staffList);
    }

    @Override
    public List<Staff> relatedStaffs(String projectId, String orgId) {
        return staffMapper.relatedStaffList(projectId, orgId);
    }
}
