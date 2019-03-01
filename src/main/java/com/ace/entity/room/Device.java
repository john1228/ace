package com.ace.entity.room;

import com.ace.entity.concern.Base;
import com.ace.entity.room.concern.DeviceUtil;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

@Setter
@Getter
@OverrideC
public class Device extends Base {
    private Integer id;
    @NotEmpty(message = "不能为空")
    private String name;
    @Min(value = 0, message = "优惠金额不能小于0元")
    private BigDecimal price;
    @NotEmpty(message = "不能为空")
    private String unit;
    private DeviceUtil.Status status;

    public static void main(String[] args) {
        Device device = new Device(1);
    }
}
