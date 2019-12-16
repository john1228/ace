package com.ace.controller.api.admin;

import com.ace.annotation.Authorization;
import com.ace.controller.api.BaseController;
import com.ace.controller.api.concerns.ApiView;
import com.ace.controller.api.concerns.Result;
import com.ace.controller.api.concerns.Success;
import com.ace.entity.Account;
import com.ace.service.api.OrderService;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 管理端订单管理
 */
@Api(tags = "管理端-订单管理")
@RestController("api_admin_orders")
@RequestMapping("/api/admin/orders")
@Log4j2
public class OrdersController extends BaseController {
    @Resource
    OrderService orderService;

    /**
     * 管理方订单
     * <br/>
     * account - 账号 {@link Account}
     * <br/>
     * list - 订单状态
     * <br/>
     * page - 页码
     **/
    @JsonView(ApiView.Base.class)
    @GetMapping("")
    @Authorization
    @ApiOperation(value = "查询订单")
    public Result index(
            @RequestAttribute("ACCOUNT") Account account,
            @RequestParam(value = "list", defaultValue = "unconfirmed") String status,
            @RequestParam(value = "page", defaultValue = "1") int page
    ) {
        log.info("当前请求列表:" + status + "::");
        return new Success(orderService.supplierOrder(account, status, page));
    }

    /**
     * 查看
     * <br/>
     * orderNo - 订单号
     */
    @JsonView(ApiView.Detail.class)
    @GetMapping("/{id}")
    @Authorization
    @ApiOperation(value = "查看订单")
    public Result show(@PathVariable("id") String orderNo) {
        return new Success(orderService.show(orderNo));
    }

    /**
     * 确认
     * <br/>
     * orderNo - 订单号
     */
    @JsonView(ApiView.Base.class)
    @PostMapping("/{id}")
    @Authorization
    @ApiOperation(value = "确认订单")
    public Result confirm(@PathVariable("id") String orderNo) {
        orderService.confirm(orderNo);
        return new Success(null);
    }


    /**
     * 取消
     * <br/>
     * orderNo - 订单号
     */
    @JsonView(ApiView.Base.class)
    @PostMapping("/{id}/delete")
    @Authorization
    public Result cancel(@PathVariable("id") String orderNo) {
        orderService.cancel(orderNo, false);
        return new Success(null);
    }
}
