package com.ace.interceptor;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ace.entity.Account;
import com.ace.entity.Staff;
import com.ace.service.concerns.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


public class AdminInterceptor implements HandlerInterceptor {
    Logger logger = LoggerFactory.getLogger(AdminInterceptor.class);
    @Resource
    TokenService tokenService;
    @Resource
    private RedisTemplate<String, Staff> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        //TODO: 登录验证
        logger.info("处理登录");
        Account account = new Account();
        account.setAccountId("001");
        account.setAccountName("001-NAME");
        List<Staff> staffList = new ArrayList<>();
        for (int i = 1; i <= 11; i++) {
            Staff staff = new Staff();
            staff.setId(Long.valueOf(i));
            staff.setAccountId("001");
            staff.setAccountName("001-NAME");
            staff.setProjectId("001-P-" + i);
            staff.setProjectName("001-PN-" + i);
            staff.setOrgId("001-O-" + i);
            staff.setOrgName("001-ON" + i);
            staff.setEmpId("001-E-" + i);
            staff.setEmpName("001-EM-" + i);
            staffList.add(staff);
            redisTemplate.opsForList().leftPush(account.getAccountId(), staff);
        }
        account.setStaffList(staffList);
        HttpSession session = request.getSession();
        session.setAttribute("CURRENT::OPERATOR", staffList.get(0));
        session.setAttribute("CURRENT::RELATED::STAFF", staffList.get(1));
        //请求处理
        request.setAttribute("company", "爱测试");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {
        //View渲染前调用
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
    }
}
