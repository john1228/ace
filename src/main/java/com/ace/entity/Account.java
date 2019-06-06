package com.ace.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Account implements Serializable {

    private static final long serialVersionUID = -2636547406752810026L;
    private String accountId;
    private String accountName;
    private List<Staff> staffList = new ArrayList<>();
    //记录操作产生的错误信息
    private String errMsg;

    public Account(String accountId, String accountName) {
        this.accountId = accountId;
        this.accountName = accountName;
    }

    //账号内所有员工的编号
    public List<Long> allStaff() {
        List<Long> idList = new ArrayList<>();
        staffList.forEach(staff -> idList.add(staff.getId()));
        return idList;
    }

    //项目内所有账户
    public List<Long> projectStaff(String projectId) {
        List<Long> idList = new ArrayList<>();
        staffList.forEach(staff -> {
            if (staff.getProjectId().equals(projectId))
                idList.add(staff.getId());
        });
        return idList;
    }

    //组织内所有账户
    public List<Long> orgStaff(String orgId) {
        List<Long> idList = new ArrayList<>();
        staffList.forEach(staff -> {
            if (staff.getOrgId().equals(orgId))
                idList.add(staff.getId());
        });
        return idList;
    }
}
