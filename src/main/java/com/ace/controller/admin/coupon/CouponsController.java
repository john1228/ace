package com.ace.controller.admin.coupon;

import com.ace.controller.admin.BaseController;
import com.ace.controller.admin.concerns.DataTable;
import com.ace.entity.Grant;
import com.ace.entity.concern.EnumUtils;
import com.ace.entity.coupon.SystemCoupon;
import com.ace.entity.coupon.concern.CouponUtil;
import com.ace.entity.Staff;
import com.ace.service.coupon.CouponService;
import com.ace.service.room.RoomService;
import com.ace.util.CollectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/admin/coupons")
public class CouponsController extends BaseController {
    Logger logger = LoggerFactory.getLogger(CouponsController.class);
    static String viewPath = "/admin/system_coupons/";
    @Resource
    private CouponService couponService;
    @Resource
    private RoomService roomService;


    @GetMapping({"", "/"})
    public String index() {
        return viewPath + "index";
    }

    @ResponseBody
    @GetMapping("/dataList")
    public DataTable<SystemCoupon> dataList(
            HttpSession session,
            @RequestParam(value = "draw", defaultValue = "1") int draw,
            @RequestParam(value = "start", defaultValue = "0") int start,
            @RequestParam(value = "length", defaultValue = "10") int length,
            @RequestParam(value = "search[value]", defaultValue = "") String keyword
    ) {
        Staff staff = (Staff) session.getAttribute(CURRENT_OPERATOR);
        DataTable<SystemCoupon> dataTable = couponService.dataTable(staff, start, length, keyword);
        dataTable.setDraw(draw);
        return dataTable;
    }

    @GetMapping("/new")
    public String add(HttpSession session, Model model) {
        Staff staff = (Staff) session.getAttribute(CURRENT_OPERATOR);
        model.addAttribute("coupon", new SystemCoupon());
        model.addAttribute("couponType", CollectionUtil.toCollection(CouponUtil.Type.class));
        model.addAttribute("weeks", CollectionUtil.toCollection(EnumUtils.Week.class));
        model.addAttribute("rooms", roomService.roomList(staff).stream().collect(Collectors.toMap(x -> x.getId(), x -> x.getName())));
        return viewPath + "new";
    }


    @PostMapping(value = {"", "/"})
    public String create(@Valid SystemCoupon systemCoupon, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("couponType", CollectionUtil.toCollection(CouponUtil.Type.class));
            model.addAttribute("weeks", CollectionUtil.toCollection(EnumUtils.Week.class));
            model.addAttribute("coupon", systemCoupon);
            return viewPath + "new";
        } else {
            couponService.create(systemCoupon);
            model.addAttribute("coupon", systemCoupon);
            return "redirect:" + viewPath + systemCoupon.getId() + "/show";
        }
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        SystemCoupon coupon = couponService.findById(id);
        model.addAttribute("coupon", coupon);
        model.addAttribute("grant", new Grant());
        return viewPath + "show";
    }

    @GetMapping("/{id}/edit")
    public String edit(HttpSession session, @PathVariable("id") int id, Model model) {
        Staff staff = (Staff) session.getAttribute(CURRENT_OPERATOR);
        SystemCoupon coupon = couponService.findById(id);
        model.addAttribute("coupon", coupon);
        model.addAttribute("couponType", CollectionUtil.toCollection(CouponUtil.Status.class));
        model.addAttribute("weeks", CollectionUtil.toCollection(EnumUtils.Week.class));
        model.addAttribute("rooms", roomService.roomList(staff).stream().collect(Collectors.toMap(x -> x.getId(), x -> x.getName())));
        return viewPath + "edit";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable("id") int id, @RequestParam("parent") String parent, @Valid SystemCoupon coupon, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return viewPath + "edit";
        } else {
            couponService.update(coupon);
            model.addAttribute("coupon", coupon);
            return "redirect:" + "/admin/coupons/" + id + "?parent=" + parent;
        }

    }

    @PostMapping("/{id}/grant")
    public String grant(@PathVariable("id") int id, Grant grant, Model model) {
        SystemCoupon coupon = couponService.findById(id);
        couponService.grant(coupon, grant);
        model.addAttribute("coupon", coupon);
        model.addAttribute("grant", new Grant());
        return viewPath + "show";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        return viewPath + "index";
    }
}
