package com.ace.interceptor;

import com.ace.annotation.Authorization;
import com.ace.entity.Account;
import com.ace.entity.Staff;
import com.ace.service.concerns.TokenService;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.util.Strings;
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

@Log4j2
public class ApiInterceptor implements HandlerInterceptor {
    @Resource
    TokenService tokenService;

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
                String authorization = request.getHeader("token");
                log.info("移动端请求::" + authorization);
                if (Strings.isEmpty(authorization)) {
                    returnJson(response, "该接口未认证或者未签名或者不存在此资源");
                } else if (authorization.equals("test-123")) {
                    Account account = new Account();
                    account.setAccountId("001");
                    account.setAccountName("001-NAME");
                    List<Staff> staffList = new ArrayList<>();
                    for (int i = 1; i <= 5; i++) {
                        staffList.add(new Staff(account, "001-P-" + i, "001-PN-" + i, "001-O-" + i, "001-ON" + i, "001-E-" + i, "001-EM-" + i));
                    }
                    account.setStaffList(staffList);
                    request.setAttribute("ACCOUNT", account);
                    return true;
                } else {
                    Account account = tokenService.account(authorization);
                    request.setAttribute("ACCOUNT", account);
                    return true;
                }

            }
        }
        return false;
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
