package com.ace.controller.admin;

import com.ace.annotation.Recordable;
import com.ace.controller.admin.concerns.AdminView;
import com.ace.controller.admin.concerns.CouponCriteria;
import com.ace.controller.admin.concerns.DataTable;
import com.ace.entity.*;
import com.ace.entity.concern.enums.CouponStatus;
import com.ace.entity.concern.enums.CouponType;
import com.ace.entity.concern.enums.Week;
import com.ace.service.admin.CouponService;
import com.ace.service.admin.RoomService;
import com.ace.util.CollectionUtil;
import com.ace.util.remote.DataUtils;
import com.fasterxml.jackson.annotation.JsonView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Controller("admin_coupons_controller")
@RequestMapping("/admin/system_coupons")
public class SystemCouponsController extends BaseController {
    Logger logger = LoggerFactory.getLogger(SystemCouponsController.class);
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
    @PostMapping("/dataList")
    @JsonView(AdminView.Table.class)
    public DataTable<SystemCoupon> dataList(
            @SessionAttribute(value = CURRENT_OPERATOR, required = false) Staff staff,
            DataTable<SystemCoupon> dataTable,
            CouponCriteria criteria
    ) {
        couponService.data(staff, dataTable, criteria);
        return dataTable;
    }

    @GetMapping("/new")
    public String add(@SessionAttribute(CURRENT_OPERATOR) Staff staff, Model model) {
        model.addAttribute("coupon", new SystemCoupon());
        model.addAttribute("couponType", CollectionUtil.toCollection(CouponType.class));
        model.addAttribute("weeks", Week.toOptions());
        model.addAttribute("rooms", roomService.roomList(staff).stream().collect(Collectors.toMap(room -> String.valueOf(room.getId()), Room::getName)));
        return viewPath + "new";
    }


    @PostMapping(value = {"", "/"})
    @Recordable
    public String create(@SessionAttribute(CURRENT_OPERATOR) Staff staff, @Valid SystemCoupon systemCoupon, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            model.addAttribute("couponType", CollectionUtil.toCollection(CouponType.class));
            model.addAttribute("weeks", Week.toOptions());
            model.addAttribute("coupon", systemCoupon);
            return viewPath + "new";
        } else {
            couponService.create(staff, systemCoupon);
            return "redirect:" + viewPath;
        }
    }

    @GetMapping("/{id}")
    public String show(@SessionAttribute(CURRENT_OPERATOR) Staff staff, @PathVariable("id") int id, Model model) {
        SystemCoupon coupon = couponService.findById(id);
        model.addAttribute("coupon", coupon);
        model.addAttribute("grant", new Grant());
        Map<String, String> orgs = new HashMap<>();
        orgs.put(staff.getOrgId(), staff.getOrgName());
        List<Staff> staffList = new ArrayList<>();
        staffList.add(staff);
        model.addAttribute("orgs", DataUtils.orgList(staff.getProjectId()));
        model.addAttribute("staffs", staffList);
        return viewPath + "show";
    }

    @GetMapping("/{id}/edit")
    public String edit(@SessionAttribute(CURRENT_OPERATOR) Staff staff, @PathVariable("id") int id, Model model) {
        SystemCoupon coupon = couponService.findById(id);
        model.addAttribute("coupon", coupon);
        model.addAttribute("couponType", CollectionUtil.toCollection(CouponStatus.class));
        model.addAttribute("weeks", Week.toOptions());
        model.addAttribute("rooms", roomService.roomList(staff).stream().collect(Collectors.toMap(room -> String.valueOf(room.getId()), Room::getName)));
        return viewPath + "edit";
    }

    @PutMapping("/{id}")
    @Recordable
    public String update(@PathVariable("id") int id, @Valid SystemCoupon coupon, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            return viewPath + "edit";
        } else {
            couponService.update(coupon);
            model.addAttribute("coupon", coupon);
            return "redirect:" + viewPath;
        }

    }

    @PostMapping("/{id}/grant")
    @Recordable
    public String grant(@PathVariable("id") int id, Grant grant, Model model) {
        logger.info("发放优惠券");
        SystemCoupon coupon = couponService.findById(id);
        couponService.grant(coupon, grant);
        model.addAttribute("coupon", coupon);
        model.addAttribute("grant", new Grant());
        return viewPath + "show";
    }

    @DeleteMapping("/{id}")
    @Recordable
    public String destroy(@PathVariable("id") int id) {
        return viewPath + "index";
    }
}
