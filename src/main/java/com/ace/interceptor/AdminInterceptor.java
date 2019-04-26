package com.ace.interceptor;

import java.security.Principal;
import java.util.concurrent.atomic.AtomicReference;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


public class AdminInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        HttpSession session = request.getSession();
        //处理请求内容前
        AtomicReference<String> parent = new AtomicReference<>(request.getParameter("parent"));
        if (parent.get() == null) parent.set(request.getHeader("PARENT"));
        session.setAttribute("parent", parent);
        //菜单组装
        Principal principal = request.getUserPrincipal();
        if (principal != null) {
            request.setAttribute("userName", request.getRemoteUser());
        }
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
