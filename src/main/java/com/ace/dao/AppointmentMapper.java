package com.ace.dao;

import com.ace.controller.admin.concerns.ScheduleCriteria;
import com.ace.entity.Appointment;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author john
 * @date 19-5-8 下午5:33
 */
public interface AppointmentMapper {

    boolean isExists(@Param("room") Long roomId, @Param("start") Timestamp start, @Param("end") Timestamp end);

    void create(Appointment appointment);

    Appointment selectByOrder(@Param("order") Long order);

    List<Appointment> appointList(@Param("criteria") ScheduleCriteria criteria);
}
