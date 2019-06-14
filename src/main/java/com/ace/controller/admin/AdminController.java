package com.ace.controller.admin;


import com.ace.entity.Account;
import com.ace.entity.Staff;
import lombok.extern.java.Log;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequestMapping("/admin")
@Log
public class AdminController extends BaseController {

    @GetMapping("/")
    public String index() {
        return INDEX;
    }

    @PostMapping({"", "/"})
    public String change(@RequestParam("operator") int operator, @RequestParam("redirectUri") String redirectUri, Authentication authentication, HttpSession session) {
        Account account = (Account) authentication.getCredentials();
        List<Staff> staffList = account.getStaffList();
        Staff selected = staffList.stream().filter(staff -> staff.getId() == operator).findAny().get();
        if (selected != null) {
            session.setAttribute(CURRENT_OPERATOR, selected);
            log.info("设置操作用户");
        }
        return "redirect:" + redirectUri;
    }

    @GetMapping("/login")
    public String login() {
        return LOGIN;
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:" + "http://bpmp.baobanwang.com/page/logout";
    }
}
