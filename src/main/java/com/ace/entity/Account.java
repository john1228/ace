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
    /**
     * 当前登录用户TOKEN
     */
    private String token;
    /**
     * 账户id
     **/
    private String accountId;
    /**
     * 账户名
     **/
    private String accountName;
    /**
     * 是否是包办用户
     **/
    private boolean admin = false;
    /**
     * 管理操作员列表
     */
    private List<Staff> staffList = new ArrayList<>();
    /**
     * 临时记录操作产生的错误信息
     **/
    private String errMsg;

    public Account(String accountId, String accountName) {
        this.accountId = accountId;
        this.accountName = accountName;
    }
}
