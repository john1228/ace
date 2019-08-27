package com.ace.controller;


import com.ace.dao.MemberCouponMapper;
import com.ace.entity.MemberCoupon;
import com.ace.entity.Receipt;
import com.ace.entity.concern.enums.Week;
import com.ace.service.api.OrderService;
import com.ace.service.concerns.JobTools;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


@RestController
@RequestMapping("/check")
@Log4j2
public class IndexController {

    @Resource
    private OrderService orderService;
    @Resource
    private JobTools jobTools;
    @Resource
    private MemberCouponMapper mcMapper;


    @GetMapping("/{order}/cancel")
    @ResponseBody
    public String schedule(@PathVariable("order") String order, @Param("duration") Long duration) {
        jobTools.cancelOrder(order, duration);
        return "sss";
    }

    @GetMapping("/{order}/pay")
    @ResponseBody
    public String pay(@PathVariable("order") String order) {
        Receipt receipt = new Receipt();
        receipt.setOrderNo(order);
        orderService.paying(receipt, "微信");
        return "sss";
    }

    @GetMapping("/{id}/coupon")
    @ResponseBody
    public String pay(@PathVariable("id") Long id) {
        SimpleDateFormat wf = new SimpleDateFormat("EEEE", Locale.US);
        MemberCoupon mc = mcMapper.findById(id);
        Week week = Week.valueOf(wf.format(new Date()).toUpperCase());
        return String.valueOf(mc.getLimitWday().contains(week));
    }
}
