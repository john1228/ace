package com.ace.controller.api;

import com.ace.annotation.Authorization;
import com.ace.controller.api.concerns.ApiView;
import com.ace.controller.api.concerns.Result;
import com.ace.controller.api.concerns.Success;
import com.ace.entity.Account;
import com.ace.service.api.CouponService;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Timestamp;

/**
 * @author john
 * @date 19-6-5 上午11:52
 */
@Api(tags = "优惠券")
@RestController
@RequestMapping("/api/coupons")
public class CouponsController {

    @Resource
    CouponService couponService;

    @GetMapping("")
    @Authorization
    @JsonView(ApiView.Base.class)
    @ApiOperation(value = "查询可用的优惠券")
    public Result list(
            @ApiParam(hidden = true)
            @RequestAttribute("ACCOUNT") Account account,
            @RequestParam("roomId") Long roomId,
            @RequestParam("appointStartTime") Timestamp appointStartTime,
            @RequestParam("appointEndTime") Timestamp appointEndTime
    ) {
        return new Success<>(couponService.couponList(account, roomId, appointStartTime, appointEndTime));
    }
}
