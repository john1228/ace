package com.ace.entity;


import com.ace.entity.concern.OrderUtil;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Table;
import java.util.Date;

@Table(name = "bb_orders")
@Setter
@Getter
public class Order {
    private long id;
    private String accountId;
    private String accountName;
    private String orderNo;
    private long total;
    private double payAmount;
    private double coupon;
    private OrderUtil.Status status;
    private Date createdAt;
    private Date updatedAt;

}
