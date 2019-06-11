package com.ace.entity;

import com.ace.controller.admin.concerns.AdminView;
import com.ace.controller.api.concerns.ApiView;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Appointment {
    private Long id;
    @JsonView(AdminView.Table.class)
    private Long orderId;
    @JsonView({ApiView.Base.class, ApiView.Detail.class})
    private Long roomId;
    @JsonView(ApiView.Detail.class)
    private String meetingName;
    @JsonView({ApiView.Base.class, ApiView.Detail.class, AdminView.Table.class})
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private Timestamp startTime;
    @JsonView({ApiView.Base.class, ApiView.Detail.class, AdminView.Table.class})
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private Timestamp endTime;
    @JsonView({ApiView.Detail.class, AdminView.Table.class})
    private String contactName;
    @JsonView({ApiView.Detail.class, AdminView.Table.class})
    private String contactMobile;
    @JsonView({ApiView.Detail.class})
    private String remark;
    private String createdAt;
    private String updatedAt;
    @JsonView(ApiView.Detail.class)
    @JsonProperty("service")
    private List<OrderSupport> service = new ArrayList<>();

    //用于显示字段
    @JsonView({ApiView.Base.class, ApiView.Detail.class})
    private String roomName;
    @JsonView({ApiView.Base.class, ApiView.Detail.class})
    private String roomCover;
    @JsonView(ApiView.Detail.class)
    private String roomMobile;
    @JsonView(ApiView.Detail.class)
    private Integer roomRefund;
    @JsonView(ApiView.Detail.class)
    private Integer roomRenew;
    @JsonView({ApiView.Base.class, ApiView.Detail.class})
    private String address;
}
