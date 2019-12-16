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
 * 用户优惠券
 *
 * @author john
 * @date 19-6-5 上午11:52
 */
@RestController
@RequestMapping("/api/coupons")
public class CouponsController {

    @Resource
    CouponService couponService;

    /**
     * 查看可用的优惠券
     * <br/>
     * account - 用户 {@link Account}
     * <br/>
     * roomId - 会议室编号
     * <br/>
     * appointStartTime - 预订开始时间
     * <br/>
     * appointEndTime - 预定结束时间
     */
    @GetMapping("")
    @Authorization
    @JsonView(ApiView.Base.class)
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
