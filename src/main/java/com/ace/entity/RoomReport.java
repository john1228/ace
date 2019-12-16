package com.ace.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * @author john
 * @date 19-6-5 下午7:05
 */
@Getter
@Setter
public class RoomReport {
    private String projectId;
    private String reportDate;
    private String projectName;
    private Long roomId;
    private String roomName;
    private Long onlineOrderAmount;
    private Long offlineOrderAmount;
    private BigDecimal onlineRoomIncome;
    private BigDecimal offlineRoomIncome;
    private BigDecimal onlineServiceIncome;
    private BigDecimal offlineServiceIncome;
    private BigDecimal refundAmount;
    private BigDecimal discountAmount;
    private BigDecimal actualIncome;
    private Long rentedAmount;
    private Long idleAmount;
}
