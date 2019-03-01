package com.ace.entity;


import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
public class Receipt {
    private Integer id;
    private String no;
    private String orderNo;
    private String buyer;
    private BigDecimal price;
    private Date createdAt;
    private Date updatedAt;
}
