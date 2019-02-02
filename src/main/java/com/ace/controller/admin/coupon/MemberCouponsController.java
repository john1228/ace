package com.ace.controller.admin.coupon;

import com.ace.controller.admin.BaseController;
import com.ace.controller.admin.concerns.DataTable;
import com.ace.entity.coupon.Coupon;
import com.ace.entity.coupon.MemberCoupon;
import com.ace.entity.coupon.concern.CouponUtil;
import com.ace.service.coupon.CouponService;
import com.ace.service.coupon.MemberCouponService;
import com.ace.util.CollectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;


@Controller
@RequestMapping("/admin/coupons")
public class MemberCouponsController extends BaseController {
    Logger logger = LoggerFactory.getLogger(MemberCouponsController.class);
    static String viewPath = "/admin/member_coupons/";
    @Resource
    private MemberCouponService mcService;


    @GetMapping({"/0/member_coupons", "/0/member_coupons"})
    public String index() {
        return viewPath + "index";
    }

    @ResponseBody
    @GetMapping("/0/member_coupons/dataList")
    public DataTable<MemberCoupon> dataList(
            @RequestParam(value = "draw", defaultValue = "1") int draw,
            @RequestParam(value = "start", defaultValue = "0") int start,
            @RequestParam(value = "length", defaultValue = "10") int length,
            @RequestParam(value = "search[value]", defaultValue = "") String keyword
    ) {
        DataTable<MemberCoupon> dataTable = mcService.dataTable(start, length, keyword);
        dataTable.setDraw(draw);
        return dataTable;
    }


    @GetMapping("/0/member_coupons/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        MemberCoupon coupon = mcService.findById(id);
        model.addAttribute("coupon", coupon);
        return viewPath + "show";
    }

    @GetMapping("/0/member_coupons/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        MemberCoupon coupon = mcService.findById(id);
        model.addAttribute("coupon", coupon);
        return viewPath + "edit";
    }

    @PutMapping("/0/member_coupons/{id}/update")
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
