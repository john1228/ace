package com.ace.controller.api;

import com.ace.annotation.Authorization;
import com.ace.controller.api.concerns.ApiView;
import com.ace.controller.api.concerns.Failure;
import com.ace.controller.api.concerns.Result;
import com.ace.controller.api.concerns.Success;
import com.ace.entity.Account;
import com.ace.entity.Appointment;
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
@Api(tags = "订单管理")
@RestController("api_orders")
@RequestMapping("/api/orders")
public class OrdersController extends BaseController {
    @Resource
    OrderService orderService;

    @JsonView(ApiView.Base.class)
    @GetMapping("")
    @Authorization
    @ApiOperation(value = "查询订单")
    public Result index(
            @RequestAttribute("ACCOUNT") Account account,
            @RequestParam(value = "list", defaultValue = "") OrderStatus status,
            @RequestParam(value = "page", defaultValue = "1") int page
    ) {
        return new Success(orderService.customerOrder(account, status, page));
    }

    @JsonView(ApiView.Base.class)
    @PostMapping("")
    @Authorization
    @ApiOperation(value = "创建订单")
    public Result create(@RequestAttribute("ACCOUNT") Account account, Appointment appointment, @RequestParam(value = "coupon", defaultValue = "0") Long couponId) {
        if (orderService.create(account, appointment, couponId)) {
            return new Success(null);
        } else {
            return new Failure(account.getErrMsg());
        }
    }

    @JsonView(ApiView.Detail.class)
    @GetMapping("/{id}")
    @Authorization
    @ApiOperation(value = "查看订单")
    public Result show(
            @RequestAttribute("ACCOUNT") Account account,
            @PathVariable("id") String orderNo
    ) {

        return new Success(orderService.show(account, orderNo));
    }


    @JsonView(ApiView.Base.class)
    @DeleteMapping("/{id}")
    @Authorization
    @ApiOperation(value = "取消订单")
    public Result delete(@PathVariable("id") String orderNo) {
        return new Success(null);
    }
}
