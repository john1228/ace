package com.ace.entity.coupon;


import com.ace.entity.concern.Base;
import com.ace.entity.concern.Organize;
import com.ace.entity.coupon.concern.CouponUtil;
import com.ace.entity.concern.Site;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public abstract class Coupon extends Base {
    private Integer id;
    private CouponUtil.Type type;
    @NotEmpty(message = "不能为空")
    private String name;
    @NotNull(message = "不能为空")
    @Min(value = 1, message = "优惠金额不能小于１元")
    private Integer discount;
    @NotNull(message = "不能为空")
    @Min(value = 0, message = "订单金额不能小于０元")
    private Integer min;
    private String resume;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date startDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date endDate;
    private List<Organize> limitOrg;
    private List<Site> limitRoom;
}
