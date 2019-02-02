package com.ace.controller.admin.room;

import com.ace.controller.admin.BaseController;
import com.ace.controller.admin.concerns.DataTable;
import com.ace.entity.coupon.concern.CouponUtil;
import com.ace.entity.room.Device;
import com.ace.entity.room.Room;
import com.ace.entity.room.concern.DeviceUtil;
import com.ace.service.room.DeviceService;
import com.ace.service.room.RoomService;
import com.ace.util.CollectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@Controller
@RequestMapping("/admin/rooms")
public class DevicesController extends BaseController {
    Logger logger = LoggerFactory.getLogger(DevicesController.class);
    static String viewPath = "/admin/devices/";
    @Resource
    private DeviceService deviceService;


    @GetMapping({"/0/devices", "/0/devices/"})
    public String index() {
        return viewPath + "index";
    }

    @ResponseBody
    @GetMapping("/0/devices/dataList")
    public DataTable<Device> dataList(
            @RequestParam(value = "draw", defaultValue = "1") int draw,
            @RequestParam(value = "start", defaultValue = "0") int start,
            @RequestParam(value = "length", defaultValue = "10") int length,
            @RequestParam(value = "search[value]", defaultValue = "") String keyword
    ) {
        DataTable<Device> dataTable = deviceService.dataTable(start, length, keyword);
        dataTable.setDraw(draw);
        return dataTable;
    }

    @GetMapping("/0/devices/new")
    public String add(Model model) {
        model.addAttribute("device", new Device());
        model.addAttribute("status", CollectionUtil.toCollection(DeviceUtil.Status.class));
        return viewPath + "new";
    }


    @PostMapping({"/0/devices", "/0/devices/"})
    public String create(@Valid Device device, BindingResult result, Model model) {
        model.addAttribute("device", device);
        if (result.hasErrors()) {
            return viewPath + "new";
        } else {
            deviceService.create(device);
            return viewPath + "show";
        }
    }

    @GetMapping("/0/devices/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        Device device = deviceService.findById(id);
        model.addAttribute("device", device);
        return viewPath + "show";
    }

    @GetMapping("/0/devices/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        Device device = deviceService.findById(id);
        model.addAttribute("device", device);
        return viewPath + "edit";
    }

    @PutMapping("/0/devices/{id}/update")
    public String update(@Valid Device device, BindingResult result, HttpServletRequest request, @PathVariable("id") int id, Model model) {
        model.addAttribute("device", device);
        if (result.hasErrors()) {
            return viewPath + "edit";
        } else {
            deviceService.update(device);
            return viewPath + "show";
        }

    }

    @DeleteMapping("/0/devices/{id}")
    public String delete(@PathVariable("id") int id) {
        return viewPath + "index";
    }
}