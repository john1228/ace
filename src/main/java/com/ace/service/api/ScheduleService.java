package com.ace.service.api;

import com.ace.entity.Appointment;

import java.sql.Date;
import java.util.List;

/**
 * @author john
 * @date 19-5-13 下午5:51
 */
public interface ScheduleService {
    List<Appointment> scheduleList(Long room, Date date);
}
