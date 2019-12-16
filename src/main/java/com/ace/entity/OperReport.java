package com.ace.entity;

import com.ace.controller.admin.concerns.AdminView;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;

import java.math.BigDecimal;

/**
 * 运营报表模型
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OperReport {
    /**
     * 项目
     */
    @JsonView(AdminView.Table.class)
    private String pName;
    /**
     * 会议室
     */
    @JsonView(AdminView.Table.class)
    private String rName;
    /**
     * 出租时长
     */
    @JsonView(AdminView.Table.class)
    private Long rtAmt;
    /**
     * 空闲时长
     */
    @JsonView(AdminView.Table.class)
    private Long idAmt;
    /**
     * 订单数量
     */
    @JsonView(AdminView.Table.class)
    private Long orAmt;
    /**
     * 服务收入
     */
    @JsonView(AdminView.Table.class)
    private BigDecimal serAmt;
    /**
     * 出租收人
     */
    @JsonView(AdminView.Table.class)
    private BigDecimal rfAmt;
    /**
     * 优惠额度
     */
    @JsonView(AdminView.Table.class)
    private BigDecimal disAmt;
    /**
     * 实际收入
     */
    @JsonView(AdminView.Table.class)
    private BigDecimal actAmt;

}
