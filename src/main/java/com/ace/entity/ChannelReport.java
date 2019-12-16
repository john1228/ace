package com.ace.entity;

import com.ace.controller.admin.concerns.AdminView;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;

import java.math.BigDecimal;

/**
 * 渠道报表
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChannelReport {
    /**
     * 项目名
     */
    @JsonView(AdminView.Table.class)
    private String pName;
    /**
     * 会议室名
     */
    @JsonView(AdminView.Table.class)
    private String rName;
    /**
     * 线上订单数量
     */
    @JsonView(AdminView.Table.class)
    private Long olAmt;
    /**
     * 线下订单数量
     */
    @JsonView(AdminView.Table.class)
    private Long oflAmt;
    /**
     * 订单总数
     */
    @JsonView(AdminView.Table.class)
    private Long aoAmt;
    /**
     * 线上订单会议室收入
     */
    @JsonView(AdminView.Table.class)
    private BigDecimal olmIncome;
    /**
     * 线下订单会议室收入
     */
    @JsonView(AdminView.Table.class)
    private BigDecimal oflmIncome;
    /**
     * 线上订单服务收入
     */
    @JsonView(AdminView.Table.class)
    private BigDecimal olsIncome;
    /**
     * 线下订单服务收入
     */
    @JsonView(AdminView.Table.class)
    private BigDecimal oflsIncome;
    /**
     * 线上订单收入
     */
    @JsonView(AdminView.Table.class)
    private BigDecimal olIncome;
    /**
     * 线上订单收入
     */
    @JsonView(AdminView.Table.class)
    private BigDecimal oflIncome;
}
