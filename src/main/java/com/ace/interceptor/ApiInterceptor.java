package com.ace.interceptor;

import com.ace.annotation.Authorization;
import com.ace.entity.Staff;
import com.ace.service.TokenService;
import com.alibaba.druid.util.StringUtils;
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
                Staff staff = new Staff();
                staff.setId(1);
                staff.setAccountId("001");
                staff.setAccountName("001-NAME");
                staff.setProjectId("001-P-1");
                staff.setProjectName("001-PN-1");
                staff.setOrgId("001-O-1");
                staff.setOrgName("001-ON-1");
                staff.setEmpId("001-E-1");
                staff.setEmpName("001-EM-1");
                request.setAttribute("STAFF", staff);
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

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        httpServletResponse.setHeader("Access-Control-Max-Age", "3600");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", "x-requested-with");
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
