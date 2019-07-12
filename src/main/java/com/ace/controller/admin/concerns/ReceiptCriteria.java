package com.ace.controller.admin.concerns;

import com.ace.entity.concern.enums.OrderStatus;
import com.ace.util.remote.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * @author john
 * @date 19-5-13 上午9:39
 */
@Getter
@Setter
public class ReceiptCriteria extends Criteria {
    private String keyword;
    private OrderStatus status;
    private Date from;
    private Date to;
    private BigDecimal payAmount;
    private BigDecimal total;
}
