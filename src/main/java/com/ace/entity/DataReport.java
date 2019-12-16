package com.ace.entity;

import com.ace.controller.api.concerns.ApiView;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;

/**
 * 管理端报表模型
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataReport {
    /**
     * 会议室
     */
    @JsonView(ApiView.Base.class)
    private String name;
    /**
     * 订单数量
     */
    @JsonView(ApiView.Base.class)
    private Integer orderAmount;
    /**
     * 线上收入
     */
    @JsonView(ApiView.Base.class)
    private Integer online;
    /**
     * 线下收入
     */
    @JsonView(ApiView.Base.class)
    private Integer offline;
    /**
     * 租用时长
     */
    @JsonView(ApiView.Base.class)
    private Integer rentedAmount;
    /**
     * 空闲时长
     */
    @JsonView(ApiView.Base.class)
    private Integer idleAmount;
}
