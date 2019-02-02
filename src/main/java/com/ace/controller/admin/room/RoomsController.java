package com.ace.controller.admin.room;

import com.ace.controller.admin.BaseController;
import com.ace.controller.admin.concerns.DataTable;
import com.ace.entity.coupon.concern.CouponUtil;
import com.ace.entity.room.Attribute;
import com.ace.entity.room.Room;
import com.ace.service.room.AttributeService;
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
public class RoomsController extends BaseController {
    Logger logger = LoggerFactory.getLogger(RoomsController.class);
    static String viewPath = "/admin/rooms/";
    @Resource
    private RoomService roomService;


    @GetMapping({"", "/"})
    public String index() {
        return viewPath + "index";
    }

    @ResponseBody
    @GetMapping("/dataList")
    public DataTable<Room> dataList(
            @RequestParam(value = "draw", defaultValue = "1") int draw,
            @RequestParam(value = "start", defaultValue = "0") int start,
            @RequestParam(value = "length", defaultValue = "10") int length,
            @RequestParam(value = "search[value]", defaultValue = "") String keyword
    ) {
        DataTable<Room> dataTable = roomService.dataTable(start, length, keyword);
        dataTable.setDraw(draw);
        return dataTable;
    }

    @GetMapping("/new")
    public String add(Model model) {
        model.addAttribute("room", new Room());
        model.addAttribute("roomType", CollectionUtil.toCollection(CouponUtil.Type.class));
        return viewPath + "new";
    }


    @PostMapping("")
    public String create(@Valid Room room, BindingResult result, Model model) {
        model.addAttribute("room", room);
        if (result.hasErrors()) {
            return viewPath + "new";
        } else {
            roomService.create(room);
            return viewPath + "show";
        }
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        Room room = roomService.findById(id);
        model.addAttribute("room", room);
        return viewPath + "show";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        Room room = roomService.findById(id);
        model.addAttribute("room", room);
        return viewPath + "edit";
    }

    @PutMapping("/{id}/update")
    public String update(@Valid Room room, BindingResult result, HttpServletRequest request, @PathVariable("id") int id, Model model) {
        model.addAttribute("room", room);
        if (result.hasErrors()) {
            return viewPath + "edit";
        } else {
            roomService.update(room);
            return viewPath + "show";
        }

    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        return viewPath + "index";
    }
}
