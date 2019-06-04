package com.ace.interceptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ace.entity.Account;
import com.ace.entity.Staff;
import com.ace.service.concerns.TokenService;
import com.ace.util.Menu;
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
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //TODO: 登录验证
        logger.info("处理登录");
        //处理菜单
        String path = request.getServletPath();
        Optional<Menu> selected = Arrays.stream(Menu.values()).filter(menu -> menu.contain(path)).findFirst();
        if (selected.isPresent()) {
            request.setAttribute("menu", selected.get().name());
        } else {
            request.setAttribute("menu", "room");
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
