package com.ace.entity;

import com.ace.controller.admin.concerns.AdminView;
import com.ace.entity.concern.enums.RefundStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 退款申请
 */
@Getter
@Setter
public class RefundApplication {
    @JsonView(AdminView.Table.class)
    private Long id;
    /**
     * 申请账户编号
     */
    private String accountId;
    /**
     * 申请账户名
     */
    private String accountName;
    /**
     * 申请关联订单编号
     */
    private Long orderId;
    @JsonView(AdminView.Table.class)
    /**
     * 申请关联订单号
     */
    private String orderNo;
    @JsonView(AdminView.Table.class)
    /**
     * 申请会议室所属项目
     */
    private String projectName;
    /**
     * 申请会议室
     */
    @JsonView(AdminView.Table.class)
    private String roomName;
    /**
     * 申请联系人
     */
    @JsonView(AdminView.Table.class)
    private String contactName;
    /**
     * 申请联系电话
     */
    @JsonView(AdminView.Table.class)
    private String contactMobile;
    /**
     * 申请金额
     */
    @JsonView(AdminView.Table.class)
    private BigDecimal amount;
    /**
     * 确认退款金额
     */
    @JsonView(AdminView.Table.class)
    private BigDecimal confirmAmount;
    /**
     * 申请处理状态
     */
    @JsonView(AdminView.Table.class)
    private RefundStatus status;
    /**
     * 申请时间
     */
    @JsonView(AdminView.Table.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;
    /**
     * 申请更新时间
     */
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

    public String getRefundNo() {
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        return df.format(updatedAt) + String.format("%08d", id);
    }
}
