package com.ace.controller.admin;

import com.ace.controller.admin.concerns.DataTable;
import com.ace.entity.MemberCoupon;
import com.ace.service.admin.MemberCouponService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 用户优惠券
 */
@Controller
@RequestMapping("/admin/")
public class MemberCouponsController extends BaseController {
    static String viewPath = "/admin/member_coupons/";
    @Resource
    private MemberCouponService mcService;


    @GetMapping({"member_coupons", "member_coupons/"})
    public String index(Model model) {
        return viewPath + "index";
    }

    /**
     * 用户优惠券列表
     *
     * @param couponId
     * @param draw
     * @param start
     * @param length
     * @param keyword
     * @return
     */

    @ResponseBody
    @GetMapping({"system_coupons/{id}/member_coupons/dataList", "member_coupons/dataList"})
    public DataTable<MemberCoupon> dataList(
            @PathVariable("id") Long couponId,
            @RequestParam(value = "draw", defaultValue = "1") int draw,
            @RequestParam(value = "start", defaultValue = "0") int start,
            @RequestParam(value = "length", defaultValue = "10") int length,
            @RequestParam(value = "search[value]", defaultValue = "") String keyword
    ) {
        DataTable<MemberCoupon> dataTable = mcService.dataTable(couponId, start, length, keyword);
        dataTable.setDraw(draw);
        return dataTable;
    }
}
