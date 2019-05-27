package com.ace.service.admin;

import com.ace.controller.admin.concerns.ScheduleCriteria;
import com.ace.entity.Appointment;

import java.util.List;

/**
 * @author john
 * @date 19-5-13 下午5:51
 */
public interface ScheduleService {
    List<Appointment> scheduleList(ScheduleCriteria criteria);
}
