package com.ace.entity;

import com.ace.controller.admin.concerns.AdminView;
import com.ace.controller.api.concerns.ApiView;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * 会议室关联服务
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
    @JsonView({ApiView.Base.class, ApiView.Detail.class, AdminView.Table.class})
    @NotBlank(message = "选择服务价格必须设置")
    @Min(value = 0, message = "服务价格不能小于0")
    private BigDecimal price;

    public void copyTo(OrderSupport orderSupport) {
        orderSupport.setName(getName());
        orderSupport.setCover(getCover());
        orderSupport.setUnit(getUnit());
        orderSupport.setPrice(getPrice());
    }
}
