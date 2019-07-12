package com.ace.controller.admin.concerns;

import com.ace.entity.Account;
import com.ace.entity.Staff;
import com.ace.util.Aliyun;
import com.ace.util.remote.Data;
import com.ace.util.remote.DataUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;
import java.util.List;


@ControllerAdvice("com.ace.controller.admin")
@Log4j2
public class AdminUserAdvice {
    private static final String CURRENT_OPERATOR = "CURRENT::OPERATOR";

    @ModelAttribute("current_account")
    public Account getAdminUser(Authentication authentication) {
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
            Account account = (Account) authentication.getCredentials();
            if (account.isAdmin()) {
                session.setAttribute(CURRENT_OPERATOR, null);
                return null;
            } else {
                Staff staff = (Staff) session.getAttribute(CURRENT_OPERATOR);
                if (staff == null) {
                    staff = account.getStaffList().get(0);
                    session.setAttribute(CURRENT_OPERATOR, staff);
                }
                return staff;
            }
        }
    }

    @ModelAttribute("current_project")
    public List<Data> projectList(Authentication authentication) {
        log.info("获取账户下项目");
        Account account = (Account) authentication.getCredentials();
        return DataUtils.proList(account.getAccountId());
    }


    @ModelAttribute("image")
    public String getImgHost() {
        return Aliyun.Instance.imgHost;
    }
}
