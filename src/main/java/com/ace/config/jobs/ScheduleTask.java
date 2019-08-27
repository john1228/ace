package com.ace.config.jobs;

import com.ace.dao.OrderMapper;
import com.ace.dao.handler.RoomReportMapper;
import com.ace.entity.Room;
import com.ace.entity.RoomReport;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author john
 * @date 19-7-3 下午4:56
 */
@Component
@Log4j2
public class ScheduleTask {

    @Resource
    OrderMapper orderMapper;
    @Resource
    RoomReportMapper reportMapper;

    @Scheduled(cron = "0 */1 * * * ?")
    public void confirmOrder() {
        log.info("定时处理订单更改为已使用");
//        orderMapper.using();
    }

    @Scheduled(cron = "0 10 01 * * ?")
    public void generateReport() {
        log.info("定时统计会议室使用情况");
        List<Room> roomList = reportMapper.roomList();
        List<Map<String, BigDecimal>> online = reportMapper.online();
        List<Map<String, BigDecimal>> offline = reportMapper.offline();
        List<Map<String, BigDecimal>> orderAmt = reportMapper.offline();
        List<Map<String, BigDecimal>> appointedAmt = reportMapper.offline();

        List<RoomReport> reports = roomList.stream().map(room -> {
            RoomReport report = new RoomReport();
            report.setId(room.getId());
            report.setName(room.getName());
            //线上订单
            Optional<Map<String, BigDecimal>> fon = online.stream().filter(item -> item.get("id").longValue() == room.getId()).findFirst();
            if (fon.isPresent()) {
                report.setOnline(fon.get().get("amount"));
            } else {
                report.setOnline(new BigDecimal(0));
            }
            //线下订单
            Optional<Map<String, BigDecimal>> fof = offline.stream().filter(item -> item.get("id").longValue() == room.getId()).findFirst();
            if (fof.isPresent()) {
                report.setOffline(fof.get().get("amount"));
            } else {
                report.setOffline(new BigDecimal(0));
            }
            //订单数
            Optional<Map<String, BigDecimal>> foa = orderAmt.stream().filter(item -> item.get("id").longValue() == room.getId()).findFirst();
            if (foa.isPresent()) {
                report.setOrderAmount(foa.get().get("amount").intValue());
            } else {
                report.setOrderAmount(0);
            }
            //租用时间
            Optional<Map<String, BigDecimal>> faa = appointedAmt.stream().filter(item -> item.get("id").longValue() == room.getId()).findFirst();
            if (faa.isPresent()) {
                BigDecimal rented = faa.get().get("amount").divide(new BigDecimal(60), 2, BigDecimal.ROUND_HALF_DOWN);
                BigDecimal idle = new BigDecimal(12).subtract(rented);
                report.setRentedAmount(rented);
                report.setIdleAmount(idle);
            } else {
                report.setRentedAmount(new BigDecimal(0));
                report.setIdleAmount(new BigDecimal(12));
            }
            return report;
        }).collect(Collectors.toList());
        reportMapper.create(reports);
    }
}
