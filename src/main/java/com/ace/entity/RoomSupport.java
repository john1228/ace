package com.ace.entity;

import com.ace.controller.api.concerns.ApiView;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author john
 * @date 19-5-9 下午4:48
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class RoomSupport extends Support {
    @JsonView(ApiView.Detail.class)
    private Long roomId;
    private Long supportId;
    private String remark;
    @JsonView({ApiView.Base.class, ApiView.Detail.class})
    private BigDecimal price;

    public void copyTo(OrderSupport orderSupport) {
        orderSupport.setName(getName());
        orderSupport.setCover(getCover());
        orderSupport.setUnit(getUnit());
        orderSupport.setPrice(getPrice());
    }
}
