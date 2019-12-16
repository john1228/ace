package com.ace.controller.admin.concerns;

import com.ace.entity.concern.enums.OrderStatus;
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
public class OrderCriteria extends Criteria {
    private String keyword;
    private String proId;
    private String orgId;
    private BigDecimal total;
    private BigDecimal payAmount;
    private OrderStatus status;
    private Date from;
    private Date to;
    private String projectName;
}
