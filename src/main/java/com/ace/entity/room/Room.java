package com.ace.entity.room;

import com.ace.controller.api.concerns.View;
import com.ace.entity.room.concern.RoomUtil;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Setter
@Getter
public class Room {
    @JsonView(View.Base.class)
    private long id;
    private Integer staffId;
    @NotEmpty(message = "场地名字不能为空")
    @JsonView(View.Base.class)
    private String name;
    @JsonView(View.Base.class)
    private String cover;
    private List<String> image;
    private String serialNo;
    @JsonView(View.Base.class)
    @NotEmpty(message = "场地地址不能为空")
    private String address;
    @JsonView(View.Base.class)
    @JsonProperty("building_no")
    private String buildingNo;
    @JsonView(View.Base.class)
    private String floorNo;
    @JsonView(View.Base.class)
    private String roomNo;
    private double layerHeight;
    private double layerArea;
    @JsonView(View.Base.class)
    private Integer quota;
    private RoomUtil.Rental rental;
    private String supervisor;
    private String supervisorMobile;
    private String supervisorEmail;
    private RoomUtil.Confirmation confirmation;
    private RoomUtil.Payment payment;
    private RoomUtil.Layout layout;
    private String resume;
}
