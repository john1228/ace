package com.ace.controller.api.admin;

import com.ace.annotation.Authorization;
import com.ace.controller.api.BaseController;
import com.ace.controller.api.concerns.ApiView;
import com.ace.controller.api.concerns.Failure;
import com.ace.controller.api.concerns.Result;
import com.ace.controller.api.concerns.Success;
import com.ace.entity.Account;
import com.ace.entity.Appointment;
import com.ace.entity.Order;
import com.ace.entity.concern.enums.OrderStatus;
import com.ace.service.api.OrderService;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author john
 * @date 19-5-7 下午6:23
 */
@Api(tags = "管理端-订单管理")
@RestController("api_admin_orders")
@RequestMapping("/api/admin/orders")
public class OrdersController extends BaseController {
    @Resource
    OrderService orderService;

    @JsonView(ApiView.Base.class)
    @GetMapping("")
    @Authorization
    @ApiOperation(value = "查询订单")
    public Result index(
            @RequestAttribute("ACCOUNT") Account account,
            @RequestParam(value = "list", defaultValue = "") String status,
            @RequestParam(value = "page", defaultValue = "1") int page
    ) {
        return new Success(orderService.supplierOrder(account, status, page));
    }


    @JsonView(ApiView.Base.class)
    @PostMapping("/{id}")
    @Authorization
    @ApiOperation(value = "确认订单")
    public Result confirm(@PathVariable("id") String orderNo) {
        orderService.confirm(orderNo);
        return new Success(null);
    }

    @JsonView(ApiView.Base.class)
    @DeleteMapping("/{id}")
    @Authorization
    @ApiOperation(value = "取消订单")
    public Result cancel(@PathVariable("id") String orderNo) {
        orderService.cancel(orderNo);
        return new Success(null);
    }
}
