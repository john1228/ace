package com.ace.entity;

import com.ace.entity.concern.enums.RefundStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author john
 * @date 19-7-6 下午6:46
 */
@Getter
@Setter
public class RefundApplication {
    private String accountId;
    private String accountName;
    private Long orderId;
    private BigDecimal amount;
    private BigDecimal confirmAmount;
    private RefundStatus status;
    private Date createdAt;
    private Date updatedAt;
}
