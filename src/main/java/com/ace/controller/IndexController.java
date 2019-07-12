package com.ace.controller;


import com.ace.entity.Receipt;
import com.ace.service.api.OrderService;
import com.ace.service.concerns.JobTools;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;


@RestController
@RequestMapping("/check")
public class IndexController {

    @Resource
    private OrderService orderService;
    @Resource
    private JobTools jobTools;

    @RequestMapping("/{order}")
    @ResponseBody
    public BigDecimal index(@PathVariable("order") String order) {
        return orderService.check(order);
    }

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
}
