package com.ace.entity.room;

import com.ace.controller.api.concerns.View;
import com.ace.entity.concern.Base;
import com.ace.entity.room.concern.RoomUtil;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class Room extends Base {
    @JsonView(View.Base.class)
    private long id;
    @JsonView(View.Base.class)
    @NotEmpty(message = "场地名字不能为空")
    private String name;
    @JsonView(View.Base.class)
    private String cover;
    private List<String> image;
    @NotEmpty(message = "场地编号不能为空")
    private String serialNo;
    @JsonView(View.Base.class)
    @NotEmpty(message = "场地地址不能为空")
    private String address;
    @JsonView(View.Base.class)
    @JsonProperty("building_no")
    @NotEmpty(message = "幢号不能为空")
    private String buildingNo;
    @JsonView(View.Base.class)
    @NotEmpty(message = "楼层编号不能为空")
    private String floorNo;
    @JsonView(View.Base.class)
    @NotEmpty(message = "房间编号不能为空")
    private String roomNo;
    @NotEmpty(message = "场地地址不能为空")
    private double layerHeight;
    @NotEmpty(message = "场地面积不能为空")
    private double layerArea;
    @JsonView(View.Base.class)
    private Integer quota;
    private boolean free;
    private Date openDate;
    private Date closeDate;
    private Integer unit;
    private Integer renew;
    private RoomUtil.Rental rental;
    private String supervisor;
    private String supervisorMobile;
    private String supervisorEmail;
    private RoomUtil.Confirmation confirmation;
    private RoomUtil.Payment payable;
    private String resume;
}
