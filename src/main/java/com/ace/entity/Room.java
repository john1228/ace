package com.ace.entity;

import com.ace.controller.admin.concerns.AdminView;
import com.ace.controller.api.concerns.ApiView;
import com.ace.entity.concern.Base;
import com.ace.entity.concern.Period;
import com.ace.entity.concern.enums.RoomCFM;
import com.ace.entity.concern.enums.RoomPublish;
import com.ace.entity.concern.enums.RoomRental;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Room extends Base {
    @JsonView({ApiView.Base.class, ApiView.Detail.class, AdminView.Table.class})
    private long id;
    @JsonView({ApiView.Base.class, ApiView.Detail.class, AdminView.Table.class})
    @NotEmpty(message = "场地名字不能为空")
    private String name;
    @JsonView({ApiView.Base.class, ApiView.Detail.class})
    private String cover;
    @JsonView(ApiView.Detail.class)
    private List<String> image;
    @NotEmpty(message = "场地编号不能为空")
    @JsonView(AdminView.Table.class)
    private String serialNo;
    @JsonView({ApiView.Detail.class, AdminView.Table.class})
    @NotEmpty(message = "幢号不能为空")
    private String buildingNo;
    @JsonView({ApiView.Detail.class, AdminView.Table.class})
    @NotEmpty(message = "楼层编号不能为空")
    private String floorNo;
    @JsonView({ApiView.Detail.class, AdminView.Table.class})
    @NotEmpty(message = "房间编号不能为空")
    private String roomNo;
    @JsonView(ApiView.Detail.class)
    private double layerHeight;
    @JsonView({ApiView.Base.class, ApiView.Detail.class, AdminView.Table.class})
    private double layerArea;
    @JsonView({ApiView.Base.class, ApiView.Detail.class})
    private String type;
    @JsonView({ApiView.Base.class, ApiView.Detail.class, AdminView.Table.class})
    private RoomPublish publish;
    @JsonView({ApiView.Base.class, ApiView.Detail.class, AdminView.Table.class})
    private Integer quota;
    @JsonView({ApiView.Base.class, ApiView.Detail.class})
    private boolean free;
    private JSONObject freeOrg = new JSONObject();
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date openDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date closeDate;
    private Integer unit;
    private Integer renew;
    @JsonView({ApiView.Base.class, ApiView.Detail.class, AdminView.Table.class})
    private RoomRental rental;
    @JsonView({AdminView.Table.class})
    private String supervisor;
    @JsonView({ApiView.Detail.class, AdminView.Table.class})
    private String supervisorMobile;
    @JsonView({AdminView.Table.class})
    private String supervisorEmail;
    private boolean payable;
    private RoomCFM cfm;
    private Integer rlt;
    @JsonView({ApiView.Base.class, ApiView.Detail.class})
    private String freeService;
    private String chargeService;
    @JsonView(ApiView.Detail.class)
    private String resume;
    @JsonView({ApiView.Base.class, ApiView.Detail.class})
    private String price;
    @JsonView(ApiView.Detail.class)
    @JsonProperty("charge")
    List<RoomSupport> supportList = new ArrayList<>(10);
    //附加字段，用于app显示使用
    @JsonView(ApiView.Base.class)
    private List<Period> open = new ArrayList<>();
    @JsonView(ApiView.Base.class)
    private List<Period> appointed = new ArrayList<>();
}
