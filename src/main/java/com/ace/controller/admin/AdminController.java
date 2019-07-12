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
        return "redirect:/admin/orders";
    }

    @PostMapping({"", "/"})
    public String change(Authentication authentication, HttpSession session) {
        Account account = (Account) authentication.getCredentials();
        if (account.isAdmin()) {
            session.setAttribute(CURRENT_OPERATOR, null);
        } else {
            List<Staff> staffList = account.getStaffList();
            session.setAttribute(CURRENT_OPERATOR, staffList.get(0));
        }
        return "redirect:/admin/orders";
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
