package com.ace.entity;

import com.ace.controller.admin.concerns.AdminView;
import com.ace.controller.api.concerns.ApiView;
import com.ace.entity.concern.Base;
import com.ace.util.Aliyun;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

/**
 * 服务
 */
@Getter
@Setter
@NoArgsConstructor
public class Support extends Base {
    @JsonView({ApiView.Base.class, ApiView.Detail.class, AdminView.Table.class})
    private Long id;
    @NotEmpty(message = "服务名字不能为空")
    @JsonView({ApiView.Base.class, ApiView.Detail.class, AdminView.Table.class})
    private String name;
    @JsonView({ApiView.Base.class, ApiView.Detail.class, AdminView.Table.class})
    private String cover;
    @NotEmpty(message = "服务单位不能为空")
    @JsonView({ApiView.Base.class, ApiView.Detail.class, AdminView.Table.class})
    private String unit;
    private boolean online;
    private Date createdAt;
    private Date updatedAt;

    public RoomSupport toRoomSupport() {
        RoomSupport roomSupport = new RoomSupport();
        roomSupport.setSupportId(id);
        roomSupport.setName(name);
        roomSupport.setCover(Aliyun.Instance.imgHost + cover);
        return roomSupport;
    }
}
