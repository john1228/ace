package com.ace.controller.admin;

import com.ace.controller.admin.concerns.AdminView;
import com.ace.controller.admin.concerns.DataTable;
import com.ace.controller.admin.concerns.OperLogCriteria;
import com.ace.entity.OperLog;
import com.ace.service.admin.OperLogService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 操作日志
 */
@Controller
@RequestMapping("/admin/oper_logs")
public class OperLogsController extends BaseController {
    static String viewPath = "/admin/oper_logs/";
    @Resource
    private OperLogService logService;


    @GetMapping({"", "/"})
    public String index() {
        return viewPath + "index";
    }

    @ResponseBody
    @PostMapping("/dataList")
    @JsonView(AdminView.Table.class)
    public DataTable<OperLog> dataList(
            OperLogCriteria criteria,
            DataTable<OperLog> dataTable
    ) {
        logService.dataTable(criteria, dataTable);
        return dataTable;
    }
}
