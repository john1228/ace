package com.ace.controller.admin;

import com.ace.annotation.Recordable;
import com.ace.dao.handler.RoomReportMapper;
import com.ace.entity.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 内置命令
 */
@Controller("admin_system_controller")
@RequestMapping("/admin/system")
@Log4j2
public class SystemController extends BaseController {

    @Resource
    RoomReportMapper reportMapper;

    /**
     * 添加报告数据
     **/
    @GetMapping(value = {"", "/"})
    @ResponseBody
    public String create(@RequestParam("date") Date date) {
        log.info("定时统计会议室使用情况");


        List<Room> roomList = reportMapper.roomList();
        List<Map<String, Object>> onlineAmt = reportMapper.onlineAmt(date.toString());
        List<Map<String, Object>> offlineAmt = reportMapper.offlineAmt(date.toString());

        List<Map<String, Object>> onlineIncome = reportMapper.onlineIncome(date.toString());
        List<Map<String, Object>> offlineIncome = reportMapper.offlineIncome(date.toString());

        List<Map<String, Object>> onlineServiceIncome = reportMapper.onlineServiceIncome(date.toString());
        List<Map<String, Object>> offlineServiceIncome = reportMapper.offlineServiceIncome(date.toString());

        List<Map<String, Object>> refundAmt = reportMapper.refundAmt(date.toString());
        List<Map<String, Object>> discountAmt = reportMapper.offlineServiceIncome(date.toString());
        List<Map<String, Object>> actualIncome = reportMapper.actualIncome(date.toString());
        List<Map<String, Object>> rentedAmt = reportMapper.rentedAmt(date.toString());

        List<RoomReport> reports = roomList.stream().map(room -> {
            RoomReport report = new RoomReport();
            report.setReportDate(date.toString());
            report.setProjectId(room.getProjectId());
            report.setProjectName(room.getProjectName());
            report.setRoomId(room.getId());
            report.setRoomName(room.getName());
            //订单数量
            Optional<Map<String, Object>> rolAmt = onlineAmt.stream().filter(item -> ((BigInteger) item.get("id")).longValue() == room.getId()).findFirst();
            Optional<Map<String, Object>> roflAmt = offlineAmt.stream().filter(item -> ((BigInteger) item.get("id")).longValue() == room.getId()).findFirst();
            report.setOnlineOrderAmount(rolAmt.isPresent() ? (Long) rolAmt.get().get("amount") : Long.valueOf(0L));
            report.setOfflineOrderAmount(roflAmt.isPresent() ? (Long) roflAmt.get().get("amount") : Long.valueOf(0L));
            //服务收入
            Optional<Map<String, Object>> osIncome = onlineServiceIncome.stream().filter(item -> ((BigInteger) item.get("id")).longValue() == room.getId()).findFirst();
            Optional<Map<String, Object>> ofsIncome = offlineServiceIncome.stream().filter(item -> ((BigInteger) item.get("id")).longValue() == room.getId()).findFirst();
            report.setOnlineServiceIncome(osIncome.isPresent() ? (BigDecimal) osIncome.get().get("amount") : new BigDecimal(0));
            report.setOfflineServiceIncome(ofsIncome.isPresent() ? (BigDecimal) osIncome.get().get("amount") : new BigDecimal(0));
            //房间收入
            Optional<Map<String, Object>> ooIncome = onlineIncome.stream().filter(item -> ((BigInteger) item.get("id")).longValue() == room.getId()).findFirst();
            Optional<Map<String, Object>> ofoIncome = offlineIncome.stream().filter(item -> ((BigInteger) item.get("id")).longValue() == room.getId()).findFirst();
            report.setOnlineRoomIncome(ooIncome.isPresent() ? ((BigDecimal) ooIncome.get().get("amount")).subtract(report.getOnlineServiceIncome()) : new BigDecimal("0"));
            report.setOfflineRoomIncome(ofoIncome.isPresent() ? ((BigDecimal) ofoIncome.get().get("amount")).subtract(report.getOnlineServiceIncome()) : new BigDecimal("0"));
            //退款金额
            Optional<Map<String, Object>> rfAmt = refundAmt.stream().filter(item -> ((BigInteger) item.get("id")).longValue() == room.getId()).findFirst();
            report.setRefundAmount(rfAmt.isPresent() ? (BigDecimal) rfAmt.get().get("amount") : new BigDecimal(0));
            //折扣金额
            Optional<Map<String, Object>> dsAmt = discountAmt.stream().filter(item -> ((BigInteger) item.get("id")).longValue() == room.getId()).findFirst();
            report.setDiscountAmount(dsAmt.isPresent() ? (BigDecimal) dsAmt.get().get("amount") : new BigDecimal(0));
            //线下订单
            Optional<Map<String, Object>> atIncome = actualIncome.stream().filter(item -> ((BigInteger) item.get("id")).longValue() == room.getId()).findFirst();
            report.setActualIncome(atIncome.isPresent() ? (BigDecimal) atIncome.get().get("amount") : new BigDecimal(0));
            //租用时间
            Optional<Map<String, Object>> rtAmt = rentedAmt.stream().filter(item -> ((BigInteger) item.get("id")).longValue() == room.getId()).findFirst();
            report.setRentedAmount(rtAmt.isPresent() ? ((BigDecimal) rtAmt.get().get("amount")).longValue() : 0L);
            //空闲时间
            report.setIdleAmount(12 * 60 - report.getRentedAmount());
            return report;
        }).collect(Collectors.toList());
        reportMapper.create(reports);
        log.info("完成计算");
        return "生产报告完成";
    }
}
