package com.ace.entity;

import com.ace.controller.admin.concerns.AdminView;
import com.ace.controller.api.concerns.ApiView;
import com.ace.entity.concern.Base;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Support extends Base {
    @JsonView({ApiView.Base.class, ApiView.Detail.class, AdminView.Table.class})
    private Long id;
    @NotEmpty(message = "不能为空")
    @JsonView({ApiView.Base.class, ApiView.Detail.class, AdminView.Table.class})
    private String name;
    @JsonView({ApiView.Base.class, ApiView.Detail.class, AdminView.Table.class})
    private String cover;
    @NotEmpty(message = "不能为空")
    @JsonView({ApiView.Base.class, ApiView.Detail.class, AdminView.Table.class})
    private String unit;
    private Date createdAt;
    private Date updatedAt;
}
