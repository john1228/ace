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
    /**
     * 预约管理订单编号
     **/
    @JsonView(AdminView.Table.class)
    private Long orderId;
    /**
     * 预约管理订单号
     **/
    @JsonView(AdminView.Table.class)
    private String orderNo;
    /**
     * 预约会议室编号
     **/
    @JsonView({ApiView.Base.class, ApiView.Detail.class})
    private Long roomId;
    /**
     * 预约会议名字
     **/
    @JsonView(ApiView.Detail.class)
    private String meetingName;
    /**
     * 预约开始时间
     **/
    @JsonView({ApiView.Base.class, ApiView.Detail.class, AdminView.Table.class})
    private Timestamp startTime;
    /**
     * 预约结束时间
     **/
    @JsonView({ApiView.Base.class, ApiView.Detail.class, AdminView.Table.class})
    private Timestamp endTime;
    /**
     * 联系人
     **/
    @JsonView({ApiView.Detail.class, AdminView.Table.class})
    private String contactName;
    /**
     * 联系电话
     **/
    @JsonView({ApiView.Detail.class, AdminView.Table.class})
    private String contactMobile;
    /**
     * 备注
     **/
    @JsonView({ApiView.Detail.class})
    private String remark;
    /**
     * 预约创建时间
     **/
    private String createdAt;
    /**
     * 预约更新时间
     **/
    private String updatedAt;
    /**
     * 预约包含服务
     **/
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
