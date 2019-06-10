package com.ace.controller.api.admin;

import com.ace.annotation.Authorization;
import com.ace.controller.api.concerns.Result;
import com.ace.controller.api.concerns.Success;
import com.ace.entity.Account;
import com.ace.entity.RoomReport;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

/**
 * @author john
 * @date 19-5-18 下午1:36
 */
@Api(tags = "管理端-财务管理")
@RestController
@RequestMapping("/api/admin/reports")
public class ReportsController {


    @GetMapping
    @Authorization
    public Result index(@RequestAttribute("ACCOUNT") Account account, @RequestParam(value = "from", defaultValue = "2019-05-01") Date from, @RequestParam(value = "to", defaultValue = "2019-05-06") Date to) {
        List<RoomReport> reportList = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            RoomReport report = new RoomReport();
            report.setId(Long.valueOf(i));
            report.setName("会议室" + i);
            report.setOnline(new BigDecimal((i + 3) * 10));
            report.setOffline(new BigDecimal((i + 2) * 10));
            report.setOrderAmount(i);
            report.setRoomAmount(i);
            report.setRentedAmount(i);
            report.setIdleAmount(i);
            reportList.add(report);
        }
        return new Success(reportList);
    }

}
