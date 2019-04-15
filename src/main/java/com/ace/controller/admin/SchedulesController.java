package com.ace.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/schedules")
public class SchedulesController {
    static String viewPath = "/admin/schedules/";

    @GetMapping({"", "/"})
    public String index() {
        return viewPath + "index";
    }
}
