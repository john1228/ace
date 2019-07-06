package com.ace.service.concerns;

import com.ace.config.jobs.OrderJob;
import lombok.extern.log4j.Log4j2;
import org.quartz.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * @author john
 * @date 19-7-3 下午12:12
 */
@Service("tools-job")
@Log4j2
public class JobTools {
    @Resource
    private Scheduler scheduler;

    public void cancelOrder(String orderNo) {
        JobDataMap dataMap = new JobDataMap();
        dataMap.put("order_no", orderNo);
        JobDetail jobDetail = JobBuilder.newJob(OrderJob.class)
                .withIdentity(UUID.randomUUID().toString(), "orders-job")
                .usingJobData(dataMap)
                .storeDurably()
                .build();
        Trigger trigger = TriggerBuilder.newTrigger().forJob(jobDetail)
                .withIdentity(jobDetail.getKey().getName(), "orders-trigger")
                .startAt(new Timestamp(System.currentTimeMillis() + 30 * 60 * 1000))
                .build();
        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public void cancelOrder(String orderNo, Long seconds) {
        log.info("到时间处理订单");
        JobDataMap dataMap = new JobDataMap();
        dataMap.put("order_no", orderNo);
        JobDetail jobDetail = JobBuilder.newJob(OrderJob.class)
                .withIdentity(UUID.randomUUID().toString(), "orders-job")
                .usingJobData(dataMap)
                .storeDurably()
                .build();
        Trigger trigger;
        if (seconds == 0) {
            trigger = TriggerBuilder.newTrigger().forJob(jobDetail)
                    .withIdentity(jobDetail.getKey().getName(), "orders-trigger")
                    .startNow()
                    .build();
        } else {
            trigger = TriggerBuilder.newTrigger().forJob(jobDetail)
                    .withIdentity(jobDetail.getKey().getName(), "orders-trigger")
                    .startAt(new Timestamp(System.currentTimeMillis() + seconds*1000))
                    .build();
        }
        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
