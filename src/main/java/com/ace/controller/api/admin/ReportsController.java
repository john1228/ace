package com.ace.controller.api.admin;

import com.ace.annotation.Authorization;
import com.ace.controller.api.concerns.Result;
import com.ace.controller.api.concerns.Success;
import com.ace.entity.Account;
import com.ace.service.api.ReportService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Date;

/**
 * @author john
 * @date 19-5-18 下午1:36
 */
@Api(tags = "管理端-财务管理")
@RestController
@RequestMapping("/api/admin/reports")
public class ReportsController {

    @Resource
    ReportService reportService;

    @GetMapping
    @Authorization
    public Result index(@RequestAttribute("ACCOUNT") Account account, @RequestParam(value = "from") Date from, @RequestParam(value = "to") Date to) {
        return new Success(reportService.reportList(account, from, to));
    }

}
