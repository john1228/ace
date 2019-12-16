package com.ace.controller.api;

import com.ace.annotation.Authorization;
import com.ace.controller.api.concerns.*;
import com.ace.entity.Account;
import com.ace.entity.Appointment;
import com.ace.entity.Order;
import com.ace.entity.concern.enums.OrderStatus;
import com.ace.service.api.OrderService;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.util.Strings;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 订单管理
 *
 * @author john
 * @date 19-5-7 下午6:23
 */
@RestController("api_orders")
@RequestMapping("/api/orders")
@Log4j2
public class OrdersController extends BaseController {
    @Resource
    OrderService orderService;

    /**
     * 用户订单
     * <br/>
     * list - 订单状态
     * <br/>
     * page - 页码
     **/
    @JsonView(ApiView.Base.class)
    @GetMapping("")
    @Authorization
    public Result index(
            @RequestAttribute("ACCOUNT") Account account,
            @RequestParam(value = "list", defaultValue = "") ListStatus status,
            @RequestParam(value = "page", defaultValue = "1") int page
    ) {
        return new Success(orderService.customerOrder(account, status, page));
    }

    /**
     * 用户下单
     * <br/>
     * appointment - 下单信息 {@link Appointment}
     * <br/>
     * coupon - 使用优惠券编号
     */
    @JsonView(ApiView.Detail.class)
    @PostMapping("")
    @Authorization
    @ApiOperation(value = "创建订单")
    public Result create(@RequestAttribute("ACCOUNT") Account account, Appointment appointment, @RequestParam(value = "coupon", defaultValue = "0") Long couponId) {
        Order order = orderService.create(account, appointment, couponId);
        if (order != null) {
            return new Success(order);
        } else {
            return new Failure(account.getErrMsg());
        }
    }

    /**
     * 查看订单
     * <br/>
     * orderNo - 订单号
     **/
    @JsonView(ApiView.Detail.class)
    @GetMapping("/{id}")
    @Authorization
    public Result show(@PathVariable("id") String orderNo) {
        return new Success(orderService.show(orderNo));
    }


    /**
     * 取消订单
     * <br/>
     * orderNo - 订单号
     */
    @JsonView(ApiView.Base.class)
    @PostMapping("/{id}/delete")
    @Authorization
    public Result delete(@PathVariable("id") String orderNo) {
        String msg = orderService.cancel(orderNo, true);
        if (Strings.isNotBlank(msg)) {
            return new Failure(msg);
        } else {
            return new Success(null);
        }
    }
}
