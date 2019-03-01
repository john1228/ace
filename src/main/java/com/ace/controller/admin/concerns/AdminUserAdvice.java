package com.ace.controller.admin.concerns;

import com.ace.entity.Account;
import com.ace.entity.Staff;
import com.ace.security.AdminUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;


@ControllerAdvice("com.ace.controller.admin")
public class AdminUserAdvice {
    private static final Logger logger = LoggerFactory.getLogger(AdminUserAdvice.class);
    private static final String CURRENT_OPERATOR = "CURRENT::OPERATOR";
    @Resource
    RedisTemplate<String, Staff> redisTemplate;


    @ModelAttribute("current_account")
    public Account getAdminUser(Authentication authentication) {
        if (authentication == null) {
            return null;
        } else {
            AdminUserDetails userDetails = (AdminUserDetails) authentication.getPrincipal();
            return userDetails.getAccount();
        }
    }

    @ModelAttribute("current_operator")
    public Staff getOperator(HttpSession session, Authentication authentication) {
        Staff staff = (Staff) session.getAttribute(CURRENT_OPERATOR);
//        if (staff == null && authentication != null) {
//            AdminUserDetails userDetails = (AdminUserDetails) authentication.getPrincipal();
//            String accountId = userDetails.getAccount().getAccountId();
//            staff = redisTemplate.opsForList().leftPop(accountId);
//            session.setAttribute(CURRENT_OPERATOR, staff);
//        }
        return staff;
    }
}
