package com.ace.entity;

import com.ace.controller.api.concerns.ApiView;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author john
 * @date 19-5-20 下午7:07
 */
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderSupport extends Support {
    private Long supportId;
    private Long orderId;
    @JsonView(ApiView.Detail.class)
    private BigDecimal price;
    @JsonView(ApiView.Detail.class)
    private int amount;
}
