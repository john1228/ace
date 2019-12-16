package com.ace.entity;


import com.ace.controller.admin.concerns.AdminView;
import com.ace.controller.api.concerns.ApiView;
import com.ace.entity.concern.Payment;
import com.ace.entity.concern.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Order {
    /**
     * 订单编号
     */
    @JsonView(AdminView.Table.class)
    private Long id;
    /**
     * 账户编号
     */
    @NonNull
    @JsonView(AdminView.Table.class)
    private String accountId;
    /**
     * 账户名
     */
    @NonNull
    @JsonView(AdminView.Table.class)
    private String accountName;
    /**
     * 订单号
     */
    @JsonView({ApiView.Base.class, ApiView.Detail.class, AdminView.Table.class})
    @NonNull
    private String orderNo;
    /**
     * 订单金额
     */
    @JsonView({ApiView.Base.class, ApiView.Detail.class, AdminView.Table.class})
    @NonNull
    private BigDecimal total;
    /**
     * 实付金额
     */
    @JsonView({ApiView.Base.class, ApiView.Detail.class, AdminView.Table.class})
    @NonNull
    private BigDecimal payAmount;
    /**
     * 优惠金额
     */
    @JsonView({ApiView.Base.class, ApiView.Detail.class, AdminView.Table.class})
    @NonNull
    private BigDecimal coupon = new BigDecimal(0);
    /**
     * 订单状态 {@link OrderStatus}
     */
    @JsonView({ApiView.Base.class, ApiView.Detail.class, AdminView.Table.class})
    @NonNull
    private OrderStatus status;
    /**
     * 备注
     */
    private String remark;
    /**
     * 免费退款时限
     */
    @JsonView({ApiView.Base.class, ApiView.Detail.class, AdminView.Table.class})
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date freeRefundLimit;
    /**
     * 下单时间
     */
    @JsonView({ApiView.Base.class, ApiView.Detail.class, AdminView.Table.class})
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;
    /**
     * 更新时间
     */
    @JsonView({ApiView.Base.class, ApiView.Detail.class, AdminView.Table.class})
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedAt;
    /**
     * 关联预订信息　{@link Appointment}
     */
    @JsonView({ApiView.Base.class, ApiView.Detail.class})
    private Appointment appointment;
    /**
     * 标示是否已开发票
     */
    @JsonView({ApiView.Base.class, ApiView.Detail.class})
    private boolean invoice;

    public Order(String accountId, String accountName) {
        this.accountId = accountId;
        this.accountName = accountName;
    }

    /**
     * 订单关联支付信息
     */
    @JsonView(ApiView.Detail.class)
    private Payment payment;
}
