package com.ace.entity;

import com.ace.entity.room.Room;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Appointment {
    private Order order;
    private Room room;
    private Date startTime;
    private Date endTime;
    private String contactName;
    private String contactMobile;
}
