package com.ace.controller.admin.coupon;

import com.ace.controller.admin.BaseController;
import com.ace.controller.admin.concerns.DataTable;
import com.ace.entity.coupon.MemberCoupon;
import com.ace.service.coupon.MemberCouponService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;


@Controller
@RequestMapping("/admin/")
public class MemberCouponsController extends BaseController {
    Logger logger = LoggerFactory.getLogger(MemberCouponsController.class);
    static String viewPath = "/admin/member_coupons/";
    @Resource
    private MemberCouponService mcService;


    @GetMapping({"member_coupons", "member_coupons/"})
    public String index(Model model) {
        return viewPath + "index";
    }

    @ResponseBody
    @GetMapping({"coupons/{coupon_id}/member_coupons/dataList", "member_coupons/dataList"})
    public DataTable<MemberCoupon> dataList(
            @PathVariable("coupon_id") int couponId,
            @RequestParam(value = "draw", defaultValue = "1") int draw,
            @RequestParam(value = "start", defaultValue = "0") int start,
            @RequestParam(value = "length", defaultValue = "10") int length,
            @RequestParam(value = "search[value]", defaultValue = "") String keyword
    ) {
        DataTable<MemberCoupon> dataTable = mcService.dataTable(couponId, start, length, keyword);
        dataTable.setDraw(draw);
        return dataTable;
    }


    @GetMapping("/{coupon_id}/member_coupons/{id}")
    public String show(@PathVariable("coupon_id") int couponId, @PathVariable("id") int id, Model model) {
        MemberCoupon coupon = mcService.findById(id);
        model.addAttribute("coupon", coupon);
        return viewPath + "show";
    }

    @GetMapping("/{coupon_id}/member_coupons/{id}/edit")
    public String edit(@PathVariable("coupon_id") int couponId, @PathVariable("id") int id, Model model) {
        MemberCoupon coupon = mcService.findById(id);
        model.addAttribute("coupon", coupon);
        return viewPath + "edit";
    }

    @PutMapping("/{coupon_id}/member_coupons/{id}/update")
    public String update(@Valid MemberCoupon memberCoupon, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return viewPath + "edit";
        } else {
            mcService.update(memberCoupon);
            model.addAttribute("mc", memberCoupon);
            return viewPath + "show";
        }

    }
}
