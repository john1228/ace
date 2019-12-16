package com.ace.entity;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * 会议室不开放日期设置
 */
@Setter
@Getter
public class RoomClosed {
    private Long id;
    private Long roomId;
    private Date startDate;
    private Date endDate;
    private String startTime;
    private String endTime;
    private String remark;
}
