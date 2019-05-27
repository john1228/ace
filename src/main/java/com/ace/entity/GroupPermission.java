package com.ace.entity;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupPermission {
    private Integer id;
    private Integer groupId;
    private String permissionId;
}
