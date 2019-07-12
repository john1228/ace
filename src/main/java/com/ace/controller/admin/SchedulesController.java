package com.ace.controller.admin;

import com.ace.controller.admin.concerns.AdminView;
import com.ace.controller.admin.concerns.ScheduleCriteria;
import com.ace.controller.api.concerns.Result;
import com.ace.controller.api.concerns.Success;
import com.ace.entity.Staff;
import com.ace.service.admin.ScheduleService;
import com.ace.service.admin.RoomService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin/schedules")
public class SchedulesController extends BaseController {
    static String viewPath = "/admin/schedules/";
    @Resource
    private RoomService roomService;
    @Resource
    private ScheduleService scheduleService;

    @GetMapping({"", "/"})
    public String index(@SessionAttribute(value = CURRENT_OPERATOR, required = false) Staff staff, Model model) {
        model.addAttribute("rooms", roomService.roomList(staff));
        return viewPath + "index";
    }

    @ResponseBody
    @PostMapping({"", "/"})
    @JsonView(AdminView.Table.class)
    public Result schedules(ScheduleCriteria criteria) {
        return new Success(scheduleService.scheduleList(criteria));
    }
}
