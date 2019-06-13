package com.ace.controller.admin;

import com.ace.controller.admin.concerns.AdminView;
import com.ace.controller.admin.concerns.DataTable;
import com.ace.controller.admin.concerns.OperLogCriteria;
import com.ace.controller.admin.concerns.OrderCriteria;
import com.ace.entity.OperLog;
import com.ace.entity.Order;
import com.ace.entity.Staff;
import com.ace.entity.concern.enums.OrderStatus;
import com.ace.service.admin.OperLogService;
import com.ace.service.admin.OrderService;
import com.ace.service.admin.RoomService;
import com.ace.util.CollectionUtil;
import com.fasterxml.jackson.annotation.JsonView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

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
