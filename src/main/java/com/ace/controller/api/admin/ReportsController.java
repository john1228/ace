package com.ace.controller.api.admin;

import com.ace.controller.api.concerns.Result;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author john
 * @date 19-5-18 下午1:36
 */
@Api(tags = "管理端-财务管理")
@RestController("api_admin_reports")
@RequestMapping("/api/admin/reports")
public class ReportsController {

    @GetMapping
    public Result index(@RequestParam("") Long room){
        return null;
    }

}
