package com.ace.entity.room;

import com.ace.entity.concern.Base;
import com.ace.entity.room.concern.DeviceUtil;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class Support extends Base {
    private Integer id;
    @NotEmpty(message = "不能为空")
    private String name;
    private String cover;
    @NotEmpty(message = "不能为空")
    private String unit;
    private Date createdAt;
    private Date updatedAt;
}
