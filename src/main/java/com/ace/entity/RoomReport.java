package com.ace.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author john
 * @date 19-6-5 下午7:05
 */
@Getter
@Setter
public class RoomReport {
    private Long id;
    private String name;
    private BigDecimal online;
    private BigDecimal offline;
    private int orderAmount;
    private int roomAmount;
    private int rentedAmount;
    private int idleAmount;
}
