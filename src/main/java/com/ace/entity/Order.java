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
    @JsonView(AdminView.Table.class)
    private long id;
    @NonNull
    @JsonView(AdminView.Table.class)
    private String accountId;
    @NonNull
    @JsonView(AdminView.Table.class)
    private String accountName;
    @JsonView({ApiView.Base.class, ApiView.Detail.class, AdminView.Table.class})
    @NonNull
    private String orderNo;
    @JsonView({ApiView.Base.class, ApiView.Detail.class, AdminView.Table.class})
    @NonNull
    private BigDecimal total;
    @JsonView({ApiView.Base.class, ApiView.Detail.class, AdminView.Table.class})
    @NonNull
    private BigDecimal payAmount;
    @JsonView({ApiView.Base.class, ApiView.Detail.class, AdminView.Table.class})
    @NonNull
    private BigDecimal coupon = new BigDecimal(0);
    @JsonView({ApiView.Base.class, ApiView.Detail.class, AdminView.Table.class})
    @NonNull
    private OrderStatus status;
    @JsonView({ApiView.Base.class, ApiView.Detail.class, AdminView.Table.class})
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;
    @JsonView({ApiView.Base.class, ApiView.Detail.class})
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedAt;
    @JsonView({ApiView.Base.class, ApiView.Detail.class})
    private Appointment appointment;
    @JsonView({ApiView.Base.class, ApiView.Detail.class})
    private boolean invoice;

    public Order(String accountId, String accountName) {
        this.accountId = accountId;
        this.accountName = accountName;
    }

    @JsonView(ApiView.Detail.class)
    private Payment payment;
}
