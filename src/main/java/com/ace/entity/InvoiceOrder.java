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
 * @author john
 * @date 19-5-14 下午2:35
 */

@Getter
@Setter
public class InvoiceOrder {
    @JsonView(AdminView.Table.class)
    private String orderNo;
    @JsonView(AdminView.Table.class)
    private String roomName;

    @JsonView(AdminView.Table.class)
    private String contactName;

    @JsonView(AdminView.Table.class)
    private String contactMobile;

    @JsonView(AdminView.Table.class)
    private String appointTime;

    @JsonView(AdminView.Table.class)
    private BigDecimal total;

    @JsonView(AdminView.Table.class)
    private BigDecimal payAmount;

    @JsonView(AdminView.Table.class)
    private InvoiceStatus status;

    @JsonView(AdminView.Table.class)
    private Date createdAt;
}
