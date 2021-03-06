package com.ace.controller.admin.concerns;

import lombok.Getter;
import lombok.Setter;

/**
 * @author john
 * @date 19-5-21 下午1:40
 */
@Setter
@Getter
public class RoomCriteria extends Criteria {
    private String name;
    private String proId;
    private String orgId;
    private Integer layerAreaFrom;
    private Integer layerAreaTo;
    private Integer quotaFrom;
    private Integer quotaTo;
}
