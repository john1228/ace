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
    private boolean admin = false;
    private List<Staff> staffList = new ArrayList<>();
    //记录操作产生的错误信息
    private String errMsg;

    public Account(String accountId, String accountName) {
        this.accountId = accountId;
        this.accountName = accountName;
    }
}
