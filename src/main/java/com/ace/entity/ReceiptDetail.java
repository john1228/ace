package com.ace.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @author john
 * @date 19-7-12 下午6:00
 */
@Setter
@Getter
public class ReceiptDetail {
    private String orderNo;
    private String no;
    private String projectName;
    private String orgName;
    private String roomName;
    private BigDecimal total;
    private BigDecimal payAmount;
    private BigDecimal price;
    private Timestamp createdAt;
    private Timestamp paidAt;
    private String payType;
}
