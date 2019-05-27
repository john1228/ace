package com.ace.interceptor;

import com.ace.annotation.Authorization;
import com.ace.entity.Account;
import com.ace.entity.Staff;
import com.ace.service.TokenService;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ApiInterceptor implements HandlerInterceptor {
    Logger logger = LoggerFactory.getLogger(ApiInterceptor.class);
    @Resource
    TokenService tokenManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        } else {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            //得到该处理器是否需要认证
            Authorization annotation = method.getAnnotation(Authorization.class);
            if (annotation == null) {
                return true;
            } else {

                Account account = new Account();
                account.setAccountId("001");
                account.setAccountName("001-NAME");
                List<Staff> staffList = new ArrayList<>();
                for (int i = 1; i <= 5; i++) {
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
                }
                account.setStaffList(staffList);

                request.setAttribute("ACCOUNT", account);
                logger.info(request.getAttribute("ACCOUNT").getClass().getName());
                return true;

//                String authorization = request.getHeader("");
//                if (!StringUtils.isEmpty(authorization)) {
//                    boolean checkToken = tokenManager.checkToken(authorization);
//                    if (checkToken) {
//                        return true;
//                    } else {
//                        returnJson(response, "授权失败");
//                        return false;
//                    }
//
//                } else {
//                    returnJson(response, "该接口未认证或者未签名或者不存在此资源");
//                    return false;
//                }

            }
        }
    }


    private void returnJson(HttpServletResponse response, String message) throws Exception {
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        try {
            JSONObject object = new JSONObject();
            object.put("code", 0);
            object.put("message", message);
            writer = response.getWriter();
            writer.write(object.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null)
                writer.close();
        }
    }
}
