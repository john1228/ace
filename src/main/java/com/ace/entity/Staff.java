package com.ace.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class Staff implements Serializable {
    private static final long serialVersionUID = 1L;

    private String accountId;
    private String accountName;
    private String projectId;
    private String projectName;
    private String orgId;
    private String orgName;
    private String empId;
    private String empName;
    //保存用户操作的错误信息
    private StringBuilder errMsg;

    public Staff(Account account, String projectId, String projectName, String orgId, String orgName, String empId, String empName) {
        this.accountId = account.getAccountId();
        this.accountName = account.getAccountName();
        this.projectId = projectId;
        this.projectName = projectName;
        this.orgId = orgId;
        this.orgName = orgName;
        this.empId = empId;
        this.empName = empName;
    }

    @Override
    public String toString() {
        return this.orgName + "@" + this.projectName;
    }
}
