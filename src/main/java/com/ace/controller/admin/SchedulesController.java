package com.ace.controller.admin;

import com.ace.entity.Staff;
import com.ace.entity.room.Room;
import com.ace.service.room.RoomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin/schedules")
public class SchedulesController extends BaseController {
    static String viewPath = "/admin/schedules/";
    @Resource
    private RoomService roomService;

    @GetMapping({"", "/"})
    public String index(HttpSession session, Model model) {
        Staff staff = (Staff) session.getAttribute(CURRENT_OPERATOR);
        model.addAttribute("rooms", roomService.roomList(staff));
        return viewPath + "index";
    }
}
