package com.ace.controller.admin.concerns;

import com.ace.annotation.Recordable;
import com.ace.entity.Account;
import com.ace.entity.OperLog;
import com.ace.entity.Staff;
import com.ace.service.admin.OperLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.AbstractMap.*;

/**
 * @author john
 * @date 19-5-23 下午4:29
 */
@Aspect
@Component
public class OperationRecorder {
    Logger logger = LoggerFactory.getLogger(OperationRecorder.class);
    static final Map<String, String> controllers = Stream.of(
            new SimpleEntry<>("RoomsController", "会议室管理"),
            new SimpleEntry<>("ClosedController", "时间管理"),
            new SimpleEntry<>("SystemCouponsController", "优惠券管理"),
            new SimpleEntry<>("InvoicesController", "发票管理"),
            new SimpleEntry<>("MemberCouponsController", "用户优惠券管理"),
            new SimpleEntry<>("OrdersController", "订单管理"),
            new SimpleEntry<>("PricesController", "价格管理"),
            new SimpleEntry<>("SettingController", "设置管理"),
            new SimpleEntry<>("SupportsController", "服务管理"),
            new SimpleEntry<>("AlipaysController", "支付宝"),
            new SimpleEntry<>("WxpaysController", "微信")
    ).collect(Collectors.toMap(SimpleEntry::getKey, SimpleEntry::getValue));

    static final Map<String, String> operations = Stream.of(
            new SimpleEntry<>("dataList", "浏览数据"),
            new SimpleEntry<>("create", "添加"),
            new SimpleEntry<>("update", "更新"),
            new SimpleEntry<>("destroy", "删除"),
            new SimpleEntry<>("delete", "删除"),
            new SimpleEntry<>("grant", "发放"),
            new SimpleEntry<>("confirm", "确认订单"),
            new SimpleEntry<>("enable", "启用"),
            new SimpleEntry<>("disable", "停用")
    ).collect(Collectors.toMap(SimpleEntry::getKey, SimpleEntry::getValue));


    @Resource
    OperLogService operLogService;

    @AfterReturning("@annotation(recordable)")
    public void record(JoinPoint jp, Recordable recordable) {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        HttpSession session = request.getSession();
        Staff staff = (Staff) session.getAttribute("CURRENT::OPERATOR");
        String[] controllerPath = jp.getTarget().getClass().getName().split("\\.");
        String controller = controllerPath[controllerPath.length - 1];
        String operation = jp.getSignature().getName();
        String ip = request.getRemoteAddr();
        String controllerName = controllers.get(controller);
        String methodName = operations.get(operation);
        if (controllerName != null && methodName != null) {
            if (staff == null) {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                Account account = (Account) authentication.getCredentials();
                operLogService.create(new OperLog(account.getAccountId(), account.getAccountName(), controllerName, methodName, ip));
            } else {
                operLogService.create(new OperLog(staff.getEmpId(), staff.getEmpName(), controllerName, methodName, ip));
            }
        } else {
            logger.info("该操作未加入记录规则::" + controllerName + "::" + methodName);
        }
    }
}
