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
public class OperLogCriteria extends Criteria {
    private String empName;
    private Date from;
    private Date to;
}
