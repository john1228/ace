package com.ace.dao.handler;

import com.ace.controller.admin.concerns.ReportCriteria;
import com.ace.entity.*;
import org.apache.ibatis.annotations.Param;

import java.sql.Date;
import java.util.List;
import java.util.Map;


public interface RoomReportMapper {
    List<Room> roomList();

    void create(@Param("reports") List<RoomReport> reports);

    List<DataReport> reports(@Param("account") Account account, @Param("from") Date from, @Param("to") Date to);

    List<Map<String, Object>> onlineAmt(@Param("report_date") String reportDate);

    List<Map<String, Object>> offlineAmt(@Param("report_date") String reportDate);

    List<Map<String, Object>> onlineIncome(@Param("report_date") String reportDate);

    List<Map<String, Object>> offlineIncome(@Param("report_date") String reportDate);

    List<Map<String, Object>> onlineServiceIncome(@Param("report_date") String reportDate);

    List<Map<String, Object>> offlineServiceIncome(@Param("report_date") String reportDate);

    List<Map<String, Object>> refundAmt(@Param("report_date") String reportDate);

    List<Map<String, Object>> discountAmt(@Param("report_date") String reportDate);

    List<Map<String, Object>> actualIncome(@Param("report_date") String reportDate);

    List<Map<String, Object>> rentedAmt(@Param("report_date") String reportDate);

    Long recordsTotal(@Param("staff") Staff staff, @Param("criteria") ReportCriteria criteria);

    List<OperReport> operationReport(@Param("staff") Staff staff, @Param("criteria") ReportCriteria criteria);

    List<ChannelReport> channelReport(@Param("staff") Staff staff, @Param("criteria") ReportCriteria criteria);
}
