package com.ace.entity;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * @author john
 * @date 19-5-18 下午11:48
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
