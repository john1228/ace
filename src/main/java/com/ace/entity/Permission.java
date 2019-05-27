package com.ace.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Permission implements Serializable {
    private String id;
    private String name;
    private String parentId;
    private String status;
    private Integer menuIndex;
    private String imageUrl;
    private String url;
    private Long createTime;
    private String isMenu;
    private String className;
    private int flag = -1;
}
