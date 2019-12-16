package com.ace.entity;

import com.ace.controller.admin.concerns.AdminView;
import com.ace.entity.concern.enums.OrderStatus;
import com.ace.entity.concern.invoice.InvoiceStatus;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * 发布关联订单模型
 */

@Getter
@Setter
public class InvoiceOrder {
    /**
     * 订单号
     */
    @JsonView(AdminView.Table.class)
    private String orderNo;
    /**
     * 会议室
     */
    @JsonView(AdminView.Table.class)
    private String roomName;
    /**
     * 联系人
     */
    @JsonView(AdminView.Table.class)
    private String contactName;
    /**
     * 联系电话
     */
    @JsonView(AdminView.Table.class)
    private String contactMobile;
    /**
     * 预定时间
     */
    @JsonView(AdminView.Table.class)
    private String appointTime;
    /**
     * 订单金额
     */
    @JsonView(AdminView.Table.class)
    private BigDecimal total;
    /**
     * 支付金额
     */
    @JsonView(AdminView.Table.class)
    private BigDecimal payAmount;
    /**
     * 发票状态
     */
    @JsonView(AdminView.Table.class)
    private InvoiceStatus status;
    /**
     * 预定创建时间
     */
    @JsonView(AdminView.Table.class)
    private Date createdAt;
}
