package com.ace.service.api.impl;

import com.ace.dao.AppointmentMapper;
import com.ace.entity.Appointment;
import com.ace.service.api.ScheduleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.List;

/**
 * @author john
 * @date 19-5-13 下午5:53
 */
@Service("api-schedule-service")
public class ScheduleServiceImpl implements ScheduleService {
    @Resource
    AppointmentMapper appointMapper;

    @Override
    public List<Appointment> scheduleList(Long room, Date date) {
        return appointMapper.daily(room, date);
    }
}
