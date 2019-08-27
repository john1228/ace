package com.ace.entity;

import com.ace.controller.admin.concerns.AdminView;
import com.ace.entity.concern.enums.RefundStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author john
 * @date 19-7-6 下午6:46
 */
@Getter
@Setter
public class RefundApplication {
    @JsonView(AdminView.Table.class)
    private Long id;
    private String accountId;
    private String accountName;
    private Long orderId;
    @JsonView(AdminView.Table.class)
    private String orderNo;
    @JsonView(AdminView.Table.class)
    private String projectName;
    @JsonView(AdminView.Table.class)
    private String roomName;
    @JsonView(AdminView.Table.class)
    private String contactName;
    @JsonView(AdminView.Table.class)
    private String contactMobile;
    @JsonView(AdminView.Table.class)
    private BigDecimal amount;
    @JsonView(AdminView.Table.class)
    private BigDecimal confirmAmount;
    @JsonView(AdminView.Table.class)
    private RefundStatus status;
    @JsonView(AdminView.Table.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;
    @JsonView(AdminView.Table.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedAt;

    public static RefundApplication build(Order order) {
        RefundApplication ra = new RefundApplication();
        ra.accountId = order.getAccountId();
        ra.accountName = order.getAccountName();
        ra.orderId = order.getId();
        ra.amount = order.getPayAmount();
        ra.confirmAmount = order.getPayAmount();
        return ra;
    }
}
