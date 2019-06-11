package com.ace.entity;


import com.ace.controller.admin.concerns.AdminView;
import com.ace.controller.api.concerns.ApiView;
import com.ace.entity.concern.Base;
import com.ace.entity.concern.enums.CouponExpired;
import com.ace.entity.concern.enums.CouponType;
import com.ace.entity.concern.enums.Week;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public abstract class Coupon extends Base {
    @JsonView({ApiView.Base.class, ApiView.Detail.class, AdminView.Table.class})
    private Long id;
    private CouponType type = CouponType.Cash;
    @NotEmpty(message = "不能为空")
    @JsonView({ApiView.Base.class, ApiView.Detail.class, AdminView.Table.class})
    private String name;
    @NotNull(message = "不能为空")
    @Min(value = 1, message = "优惠金额不能小于１元")
    @JsonView({ApiView.Base.class, ApiView.Detail.class, AdminView.Table.class})
    private Integer discount;
    @NotNull(message = "不能为空")
    @Min(value = 0, message = "订单金额不能小于０元")
    @JsonView({ApiView.Base.class, ApiView.Detail.class, AdminView.Table.class})
    private Integer min;
    @JsonView({ApiView.Base.class, ApiView.Detail.class})
    private String resume;
    private CouponExpired expiredType = CouponExpired.CONVENTION;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonView({ApiView.Base.class, ApiView.Detail.class, AdminView.Table.class})
    private Date startDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonView({ApiView.Base.class, ApiView.Detail.class, AdminView.Table.class})
    private Date endDate;
    @JsonView({ApiView.Detail.class, AdminView.Table.class})
    private List<Week> limitWday = new ArrayList<>();
    @JsonView(ApiView.Detail.class)
    private List<Integer> limitRoom = new ArrayList<>();
}
