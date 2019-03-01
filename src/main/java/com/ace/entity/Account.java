package com.ace.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Account implements Serializable {

    private static final long serialVersionUID = -2636547406752810026L;
    private String accountId;
    private String accountName;
    private List<Staff> staffList;

    //账号内所有员工的编号
    public List<Integer> allStaff() {
        List<Integer> idList = new ArrayList<>();
        staffList.forEach(staff -> idList.add(staff.getId()));
        return idList;
    }

    //项目内所有账户
    public List<Integer> projectStaff(String projectId) {
        List<Integer> idList = new ArrayList<>();
        staffList.forEach(staff -> {
            if (staff.getProjectId().equals(projectId))
                idList.add(staff.getId());
        });
        return idList;
    }

    //组织内所有账户
    public List<Integer> orgStaff(String orgId) {
        List<Integer> idList = new ArrayList<>();
        staffList.forEach(staff -> {
            if (staff.getOrgId().equals(orgId))
                idList.add(staff.getId());
        });
        return idList;
    }
}
