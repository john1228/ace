package com.ace.controller.admin;


import com.ace.entity.Account;
import com.ace.entity.Staff;
import com.ace.service.StaffService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {
    @Resource
    private StaffService staffService;

    @RequestMapping("/")
    public String index(HttpSession session, @SessionAttribute(CURRENT_OPERATOR) Staff staff) {
        List<Staff> relatedStaffList = staffService.relatedStaffs(staff.getProjectId(), staff.getOrgId());
        session.setAttribute(CURRENT_RELATED_STAFF, relatedStaffList);
        return INDEX;
    }

    @PostMapping({"", "/"})
    public String change(@RequestParam("operator") int operator, @RequestParam("redirectUri") String redirectUri, Authentication authentication, HttpSession session) {
        Account account = (Account) authentication.getCredentials();
        List<Staff> staffList = account.getStaffList();
        Staff selected = staffList.stream().filter(staff -> staff.getId() == operator).findAny().get();
        if (selected != null) {
            session.setAttribute(CURRENT_OPERATOR, selected);
            List<Staff> relatedStaffList = staffService.relatedStaffs(selected.getProjectId(), selected.getOrgId());
            session.setAttribute(CURRENT_RELATED_STAFF, relatedStaffList);
        }
        return "redirect:" + redirectUri;
    }

    @RequestMapping("/login")
    public String login() {
        return LOGIN;
    }
}
