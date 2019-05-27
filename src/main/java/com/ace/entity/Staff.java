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

    private Long id;
    private String accountId;
    private String accountName;
    private String projectId;
    private String projectName;
    private String orgId;
    private String orgName;
    private String empId;
    private String empName;

    @Override
    public String toString() {
        return this.orgName + "@" + this.projectName;
    }
}
