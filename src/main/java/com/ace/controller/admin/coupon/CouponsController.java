package com.ace.controller.admin.coupon;

import com.ace.controller.admin.BaseController;
import com.ace.controller.admin.concerns.DataTable;
import com.ace.entity.coupon.Coupon;
import com.ace.entity.coupon.concern.CouponUtil;
import com.ace.service.coupon.CouponService;
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
public class CouponsController extends BaseController {
    Logger logger = LoggerFactory.getLogger(CouponsController.class);
    static String viewPath = "/admin/coupons/";
    @Resource
    private CouponService couponService;


    @GetMapping({"", "/"})
    public String index() {
        return viewPath + "index";
    }

    @ResponseBody
    @GetMapping("/dataList")
    public DataTable<Coupon> dataList(
            @RequestParam(value = "draw", defaultValue = "1") int draw,
            @RequestParam(value = "start", defaultValue = "0") int start,
            @RequestParam(value = "length", defaultValue = "10") int length,
            @RequestParam(value = "search[value]", defaultValue = "") String keyword
    ) {
        DataTable<Coupon> dataTable = couponService.dataTable(start, length, keyword);
        dataTable.setDraw(draw);
        return dataTable;
    }

    @GetMapping("/new")
    public String add(Model model) {
        model.addAttribute("couponType", CollectionUtil.toCollection(CouponUtil.Status.class));
        model.addAttribute("expiredType", CollectionUtil.toCollection(CouponUtil.Expired.class));
        model.addAttribute("coupon", new Coupon());
        return viewPath + "new";
    }


    @PostMapping(value = {"", "/"})
    public String create(@Valid Coupon coupon, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("couponType", CollectionUtil.toCollection(CouponUtil.Status.class));
            model.addAttribute("expiredType", CollectionUtil.toCollection(CouponUtil.Expired.class));
            model.addAttribute("coupon", coupon);
            return viewPath + "new";
        } else {
            logger.info("创建记录");
            couponService.create(coupon);
            model.addAttribute("coupon", coupon);
            return viewPath + "show";
        }
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        Coupon coupon = couponService.findById(id);
        model.addAttribute("coupon", coupon);
        return viewPath + "show";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        Coupon coupon = couponService.findById(id);
        model.addAttribute("coupon", coupon);
        return viewPath + "edit";
    }

    @PutMapping("/{id}/update")
    public String update(@Valid Coupon coupon, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return viewPath + "edit";
        } else {
            couponService.update(coupon);
            model.addAttribute("coupon", coupon);
            return viewPath + "show";
        }

    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        return viewPath + "index";
    }
}
