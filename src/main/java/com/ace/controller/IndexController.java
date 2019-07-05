package com.ace.controller;


import com.ace.service.api.OrderService;
import com.ace.service.concerns.JobTools;
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
    public String schedule(@PathVariable("order") String order) {
        jobTools.cancelOrder(order, 0L);
        return "sss";
    }
}
