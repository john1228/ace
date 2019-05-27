package com.ace.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class User implements Serializable {
    private Integer id;
    private Integer groupId;
    private String userName;
    private String loginName;
    private String password;
    private String portrait;
    private String remark;
    private String isAdmin;
    private Long createTime;
    private List<Permission> pers;

    public User(User user) {
        this.id = user.getId();
        this.groupId = user.getGroupId();
        this.userName = user.getUserName();
        this.loginName = user.getLoginName();
        this.password = user.getPassword();
        this.portrait = user.getPortrait();
        this.remark = user.getRemark();
        this.isAdmin = user.getIsAdmin();
        this.createTime = user.getCreateTime();
    }
}
