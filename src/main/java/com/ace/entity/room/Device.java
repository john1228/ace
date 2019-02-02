package com.ace.entity.room;

import com.ace.entity.room.concern.DeviceUtil;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class Device {
    private Integer id;
    private String name;
    private BigDecimal price;
    private String unit;
    private DeviceUtil.Status status;
}
