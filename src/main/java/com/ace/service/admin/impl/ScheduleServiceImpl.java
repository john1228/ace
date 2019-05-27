package com.ace.service.admin.impl;

import com.ace.controller.admin.concerns.ScheduleCriteria;
import com.ace.dao.AppointmentMapper;
import com.ace.entity.Appointment;
import com.ace.service.admin.ScheduleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author john
 * @date 19-5-13 下午5:53
 */
@Service("admin_schedule_service")
public class ScheduleServiceImpl implements ScheduleService {
    @Resource
    AppointmentMapper appointMapper;

    @Override
    public List<Appointment> scheduleList(ScheduleCriteria criteria) {
        if (criteria.getDate() == null || criteria.getRoomId() == null) {
            return new ArrayList<>();
        } else {
            return appointMapper.appointList(criteria);
        }

    }
}
