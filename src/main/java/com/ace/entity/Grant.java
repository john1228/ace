package com.ace.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Grant {
    private String orgId;
    private String empId;
    private Integer amount;
}
