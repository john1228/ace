package com.ace.entity;

import com.ace.controller.api.concerns.ApiView;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 预订服务信息
 */
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderSupport extends Support {
    /**
     * 关联服务编号
     */
    private Long supportId;
    /**
     * 关联订单
     */
    private Long orderId;
    /**
     * 价格
     */
    @JsonView(ApiView.Detail.class)
    private BigDecimal price;
    /**
     * 数量
     */
    @JsonView(ApiView.Detail.class)
    private Integer amount;
}
