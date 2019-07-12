package com.ace.controller.admin.concerns;

import com.ace.entity.concern.invoice.InvoiceStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * @author john
 * @date 19-5-14 下午2:53
 */
@Getter
@Setter
@NoArgsConstructor
public class InvoiceCriteria extends Criteria {
    private String keyword;
    private InvoiceStatus status;
    private Date createdAt;
    private Integer total;
    private Integer payAmount;
}
