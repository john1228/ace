package com.ace.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Group implements Serializable {
    private Integer id;
    private String name;
    private Long createTime;
    private String remark;
}
