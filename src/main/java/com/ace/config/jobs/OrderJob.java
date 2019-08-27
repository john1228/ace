package com.ace.config.jobs;

import com.ace.entity.Appointment;
import com.ace.entity.Order;
import com.ace.entity.concern.Period;
import com.ace.entity.concern.enums.OrderStatus;
import com.ace.service.admin.OrderService;
import lombok.extern.log4j.Log4j2;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Optional;

/**
 * @author john
 * @date 19-7-3 上午10:57
 */
@Component
@Log4j2
public class OrderJob extends QuartzJobBean {

    @Resource
    private OrderService orderService;
    @Resource
    private RedisTemplate<String, Period> redisTemplate;

    @Override
    @Transactional
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        log.info("执行任务:{}", context.getJobDetail().getKey());
        JobDataMap dataMap = context.getMergedJobDataMap();
        String orderNo = dataMap.getString("order_no");
        Order order = orderService.findByOrderNo(orderNo);
        if (order.getStatus() == OrderStatus.CONFIRM2PAID) {
            orderService.delete(orderNo);
            Appointment appointment = order.getAppointment();
            String appointDate = new Date(appointment.getStartTime().getTime()).toString();
            String key = "ROOM::" + order.getAppointment().getRoomId() + "::APPOINTED::" + appointDate;
            redisTemplate.opsForHash().delete(key, order.getId());
        }
    }
}
