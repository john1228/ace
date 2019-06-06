package com.ace.service.api.impl;

import com.ace.controller.api.concerns.Query;
import com.ace.dao.AppointmentMapper;
import com.ace.dao.RoomMapper;
import com.ace.entity.Account;
import com.ace.entity.Room;
import com.ace.entity.RoomReport;
import com.ace.service.api.ReportService;
import com.ace.service.concerns.OrderTools;
import com.ace.service.concerns.RoomTools;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service("api-report-service")
public class ReportServiceImpl implements ReportService {

    @Resource
    RoomMapper roomMapper;
    @Resource
    AppointmentMapper appointmentMapper;
    @Resource
    OrderTools orderTools;


    @Override
    public List<RoomReport> reportList(Account account, Date from, Date to) {
        List<Room> roomList = roomMapper.query(account, new Query());
        List<RoomReport> reportList = roomList.stream().map(room -> report(room, from, to)).collect(Collectors.toList());
        //计算总和
        RoomReport total = new RoomReport();
        total.setId(0L);
        total.setName("总收入");
        total.setRoomAmount(reportList.size());
        reportList.forEach(item -> {
                    total.setOnline(total.getOnline().add(item.getOnline()));
                    total.setOffline(total.getOffline().add(item.getOnline()));
                }
        );

        return reportList;
    }


    private RoomReport report(Room room, Date from, Date to) {
        RoomReport report = new RoomReport();
        report.setId(room.getId());
        return null;
    }
}
