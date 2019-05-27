package com.ace.controller.admin.concerns;

import com.ace.annotation.Recordable;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author john
 * @date 19-5-23 下午4:29
 */
@Aspect
@Component

public class OperationAspect {
    Logger logger = LoggerFactory.getLogger(OperationAspect.class);

    @AfterReturning("@annotation(recordable)")
    public void record(JoinPoint jp, Recordable recordable) {
        //
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        //获取session
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("STAFF");
        logger.info("拦截切面");
        if (username != null && !"".equals(username)) {
            String ip = request.getRemoteAddr();
            try {
                Object[] args = jp.getArgs();

//                List<String> arglist = new ArrayList<String>();
//                //获取第一个参数值
//                String str = args[0].tostring();
//                String content = log.msg() + "， 关键字："+str;
//                SystemOplog sol = new SystemOplog();
//                sol.setAct_method(log.desc());
//                sol.setIpaddress(ip);
//                sol.setDate_created(new Date());
//                sol.setUsername(username);
//                sol.setRetroaction(content);
//                systemlogservice.savetoSystemlog(sol);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
