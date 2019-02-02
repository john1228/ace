package com.ace.entity.room;

import com.ace.entity.room.concern.RoomUtil;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Setter
@Getter
public class Room {
    private long id;
    private String accountId;
    private String accountName;
    private String brand;
    @NotEmpty(message = "场地名字不能为空")
    private String name;
    @NotEmpty(message = "场地地址不能为空")
    private String address;
    @NotEmpty(message = "场地类型不能为空")
    private RoomUtil.Type type;
    @NotEmpty(message = "高度不能为空")
    @Min(value = 0, message = "高度不能为0")
    private double height;
    @NotEmpty(message = "面积不能为空")
    @Min(value = 0, message = "面积不能小于0")
    private double area;
    @NotEmpty(message = "参考价格不能为空")
    @Min(value = 0, message = "参考价格不能小于0")
    private double indicativePrice;
    @NotEmpty(message = "最大人数不能")
    @Min(value = 0, message = "最大人数不能小于0")
    private long capacity;
    private boolean oneself;
    private long bookType;
    private long payType;
    private String description;
    private long layout;
}
