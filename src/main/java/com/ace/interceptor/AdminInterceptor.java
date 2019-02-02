package com.ace.interceptor;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ace.config.ServerConfig;
import com.ace.controller.admin.bo.PermissionBO;
import com.ace.entity.Permission;
import com.ace.entity.User;
import com.ace.service.PermissionService;
import com.ace.service.UserService;
import com.ace.util.Resource;

public class AdminInterceptor implements HandlerInterceptor {
    Logger logger = LoggerFactory.getLogger(AdminInterceptor.class);

    @Autowired
    private ServerConfig serverConfig;

    @Autowired
    private UserService userService;
    @Autowired
    private PermissionService permissionService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        HttpSession session = request.getSession();
        //处理请求内容前
        String parentId = request.getParameter("parentId");
        if (parentId == null) parentId = "";
        session.setAttribute("parentId", parentId);
        //菜单组装
        String loginName = request.getRemoteUser();
        if (loginName != null && !loginName.equals("")) {
            List<PermissionBO> pbos = new ArrayList<PermissionBO>();
            User user = userService.getUserToLoginName(loginName);
            if (null != user) {
                int groupId = user.getGroupId();
                if (groupId > 0) {
                    List<Permission> parentMenu = permissionService.getParentMenu();
                    List<Permission> pers = permissionService
                            .getChildPermissionByUserId(user.getId());
                    if (pers == null || (pers != null && pers.size() == 0)) {
                        pers = permissionService
                                .getChildPermissionByGroupId(groupId);
                    }
                    for (Permission menu : parentMenu) {
                        PermissionBO pbo = new PermissionBO();
                        pbo.setPermission(menu);
                        pbo.setPers(new ArrayList<Permission>());
                        pbos.add(pbo);
                    }
                    for (PermissionBO pbo : pbos) {
                        for (Permission per : pers) {
                            if (per.getParentId().equals(
                                    pbo.getPermission().getId())
                                    && per.getIsMenu().equals("Y")) {
                                pbo.getPers().add(per);
                            }
                        }
                    }
                    // 反向遍历清除空元素
                    for (int i = pbos.size() - 1; i >= 0; i--) {
                        PermissionBO pbo = pbos.get(i);
                        if (pbo.getPers() == null || pbo.getPers().size() == 0) {
                            pbos.remove(pbo);
                        }
                    }
                    // 用户设权
                    user.setPers(pers);
                }
            }
            request.setAttribute("user", user);
            request.setAttribute("menus", pbos);
        }
        //请求处理
        request.setAttribute("company", serverConfig.getCompany());

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {
        //View渲染前调用
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        //在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
    }


}
