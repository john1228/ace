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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public abstract class Coupon extends Base {
    @JsonView({ApiView.Base.class, ApiView.Detail.class, AdminView.Table.class})
    private Long id;
    /**
     * 优惠券类型　{@link CouponType}
     */
    private CouponType type = CouponType.Cash;
    /**
     * 优惠券名字
     */
    @NotEmpty(message = "不能为空")
    @JsonView({ApiView.Base.class, ApiView.Detail.class, AdminView.Table.class})
    private String name;
    /**
     * 优惠额度
     */
    @NotNull(message = "不能为空")
    @Min(value = 0, message = "优惠金额不能小于0元")
    @JsonView({ApiView.Base.class, ApiView.Detail.class, AdminView.Table.class})
    private BigDecimal discount;
    /**
     * 订单满足金额
     */
    @NotNull(message = "不能为空")
    @Min(value = 0, message = "订单金额不能小于０元")
    @JsonView({ApiView.Base.class, ApiView.Detail.class, AdminView.Table.class})
    private BigDecimal min;
    /**
     * 说明
     */
    @JsonView({ApiView.Base.class, ApiView.Detail.class})
    private String resume;
    /**
     * 过期类型 {@link CouponExpired}
     */
    private CouponExpired expiredType = CouponExpired.CONVENTION;
    /**
     * 开始使用日期
     */
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonView({ApiView.Base.class, ApiView.Detail.class, AdminView.Table.class})
    private Date startDate;
    /**
     * 结束日期
     */
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonView({ApiView.Base.class, ApiView.Detail.class, AdminView.Table.class})
    private Date endDate;
    /**
     * 指定周
     */
    @JsonView({ApiView.Detail.class, AdminView.Table.class})
    private List<Week> limitWday = new ArrayList<>();
    /**
     * 指定会议室
     */
    @JsonView(ApiView.Detail.class)
    private List<Long> limitRoom = new ArrayList<>();
}
