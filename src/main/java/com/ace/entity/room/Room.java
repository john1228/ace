package com.ace.entity.room;

import com.ace.controller.api.concerns.View;
import com.ace.entity.concern.Base;
import com.ace.entity.room.concern.RoomUtil;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Room extends Base {
    @JsonView({View.Base.class})
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
    @NotEmpty(message = "幢号不能为空")
    private String buildingNo;
    @JsonView(View.Base.class)
    @NotEmpty(message = "楼层编号不能为空")
    private String floorNo;
    @JsonView(View.Base.class)
    @NotEmpty(message = "房间编号不能为空")
    private String roomNo;
    private double layerHeight;
    private double layerArea;
    @JsonView(View.Base.class)
    private RoomUtil.Type type;
    private RoomUtil.Publish publish;
    private Integer quota;
    private boolean free;
    private JSONObject freeOrg = new JSONObject();
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date openDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date closeDate;
    private Integer unit;
    private Integer renew;
    private RoomUtil.Rental rental;
    private String supervisor;
    private String supervisorMobile;
    private String supervisorEmail;
    private boolean payable;
    private RoomUtil.CFM confirmation;
    private Integer rlt;
    private String resume;

    public static void main(String[] args) {
        System.err.println(new Room());
    }
}
