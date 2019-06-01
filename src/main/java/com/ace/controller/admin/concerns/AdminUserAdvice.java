package com.ace.controller.admin.concerns;

import com.ace.entity.Account;
import com.ace.entity.Staff;
import com.ace.security.AdminUserDetails;
import com.ace.util.Aliyun;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;


@ControllerAdvice("com.ace.controller.admin")
public class AdminUserAdvice {
    private static final String CURRENT_OPERATOR = "CURRENT::OPERATOR";

    @ModelAttribute("current_account")
    public Account getAdminUser(Authentication authentication, Model model) {
        if (authentication == null) {
            return null;
        } else {
            AdminUserDetails userDetails = (AdminUserDetails) authentication.getPrincipal();
            return userDetails.getAccount();
        }
    }

    @ModelAttribute("current_operator")
    public Staff getOperator(Authentication authentication, HttpSession session) {
        if (authentication == null) {
            return null;
        } else {
            Staff staff = (Staff) session.getAttribute(CURRENT_OPERATOR);
            if (staff == null) {
                AdminUserDetails userDetails = (AdminUserDetails) authentication.getPrincipal();
                staff = userDetails.getAccount().getStaffList().get(0);
                session.setAttribute(CURRENT_OPERATOR, staff);
            }
            return staff;
        }
    }

    @ModelAttribute("image")
    public String getImgHost() {
        return Aliyun.Instance.imgHost;
    }
}
