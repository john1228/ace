package com.ace.controller.admin.concerns;

import com.ace.entity.Account;
import com.ace.entity.Staff;
import com.ace.util.Aliyun;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;


@ControllerAdvice("com.ace.controller.admin")
@Log4j2
public class AdminUserAdvice {
    private static final String CURRENT_OPERATOR = "CURRENT::OPERATOR";

    @ModelAttribute("current_account")
    public Account getAdminUser(Authentication authentication, Model model) {
        if (authentication == null) {
            return null;
        } else {
            return (Account) authentication.getCredentials();
        }
    }

    @ModelAttribute("current_operator")
    public Staff getOperator(Authentication authentication, HttpSession session) {
        if (authentication == null) {
            return null;
        } else {
            Staff staff = (Staff) session.getAttribute(CURRENT_OPERATOR);
            if (staff == null) {
                Account account = (Account) authentication.getCredentials();
                staff = account.getStaffList().get(0);
                session.setAttribute(CURRENT_OPERATOR, staff);
                log.info(account.getAccountName() + "::" + staff.getEmpName());
            }
            return staff;
        }
    }


    @ModelAttribute("image")
    public String getImgHost() {
        return Aliyun.Instance.imgHost;
    }
}
