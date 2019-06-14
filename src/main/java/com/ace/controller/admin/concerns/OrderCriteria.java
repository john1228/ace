package com.ace.controller.admin.concerns;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

/**
 * @author john
 * @date 19-5-13 上午9:39
 */
@Getter
@Setter
public class OrderCriteria extends Criteria {
    private String no;
    private Date from;
    private Date to;
    private String mobile;
}
