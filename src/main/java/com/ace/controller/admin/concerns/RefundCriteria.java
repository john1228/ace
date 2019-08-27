package com.ace.controller.admin.concerns;

import lombok.Getter;
import lombok.Setter;

/**
 * @author john
 * @date 19-7-30 上午9:57
 */
@Getter
@Setter
public class RefundCriteria extends Criteria {
    private String orderNo;
    private String roomName;
    private String contactName;
    private String contactMobile;
}
