package com.ace.dao.handler;

import com.ace.entity.Account;
import com.ace.entity.Room;
import com.ace.entity.RoomReport;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Map;


public interface RoomReportMapper {
    List<Room> roomList();

    void create(@Param("reports") List<RoomReport> reports);

    List<RoomReport> reports(@Param("account") Account account, @Param("from") Date from, @Param("to") Date to);

    List<Map<String, BigDecimal>> online();

    List<Map<String, BigDecimal>> offline();

    List<Map<String, BigDecimal>> orderAmt();

    List<Map<String, BigDecimal>> appointedAmt();
}
