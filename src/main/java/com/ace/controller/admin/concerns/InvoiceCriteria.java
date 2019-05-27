package com.ace.controller.admin.concerns;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

/**
 * @author john
 * @date 19-5-14 下午2:53
 */
@Getter
@Setter
@NoArgsConstructor
public class InvoiceCriteria extends Criteria {
    private String orderNo;
    private Date startDate;
    private Date endDate;
    private String mobile;
}
