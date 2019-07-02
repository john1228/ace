package com.ace.controller;


import com.ace.service.api.OrderService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;


@RestController
@RequestMapping("/check")
public class IndexController {

    @Resource
    private OrderService orderService;

    @RequestMapping("/{order}")
    @ResponseBody
    public BigDecimal index(@PathVariable("order") String order) {
        return orderService.check(order);
    }

}
