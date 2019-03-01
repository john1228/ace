package com.ace.controller.admin.room;

import com.ace.controller.admin.BaseController;
import com.ace.controller.admin.concerns.DataTable;
import com.ace.entity.Staff;
import com.ace.entity.room.Room;
import com.ace.entity.room.concern.RoomUtil;
import com.ace.service.room.RoomService;
import com.ace.util.CollectionUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@Controller
@RequestMapping("/admin/rooms")
public class RoomsController extends BaseController {
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
            HttpSession session,
            @RequestParam(value = "draw", defaultValue = "1") int draw,
            @RequestParam(value = "start", defaultValue = "0") int start,
            @RequestParam(value = "length", defaultValue = "10") int length,
            @RequestParam(value = "search[value]", defaultValue = "") String keyword
    ) {
        Staff staff = (Staff) session.getAttribute(CURRENT_OPERATOR);
        DataTable<Room> dataTable = roomService.dataTable(staff, start, length, keyword);
        dataTable.setDraw(draw);
        return dataTable;
    }

    @GetMapping("/new")
    public String add(Model model) {
        model.addAttribute("room", new Room());
        model.addAttribute("rentals", CollectionUtil.toCollection(RoomUtil.Rental.class));
        model.addAttribute("confirmations", CollectionUtil.toCollection(RoomUtil.Confirmation.class));
        model.addAttribute("payments", CollectionUtil.toCollection(RoomUtil.Payment.class));
        return viewPath + "new";
    }


    @PostMapping("")
    public String create(@Valid Room room, BindingResult result, Model model) {
        model.addAttribute("room", room);
        if (result.hasErrors()) {
            model.addAttribute("rentals", CollectionUtil.toCollection(RoomUtil.Rental.class));
            model.addAttribute("confirmations", CollectionUtil.toCollection(RoomUtil.Confirmation.class));
            model.addAttribute("payments", CollectionUtil.toCollection(RoomUtil.Payment.class));
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
        model.addAttribute("rentals", CollectionUtil.toCollection(RoomUtil.Rental.class));
        model.addAttribute("confirmations", CollectionUtil.toCollection(RoomUtil.Confirmation.class));
        model.addAttribute("payments", CollectionUtil.toCollection(RoomUtil.Payment.class));
        return viewPath + "edit";
    }

    @PutMapping("/{id}/update")
    public String update(@Valid Room room, BindingResult result, HttpServletRequest request, @PathVariable("id") int id, Model model) {
        model.addAttribute("room", room);
        if (result.hasErrors()) {
            model.addAttribute("rentals", CollectionUtil.toCollection(RoomUtil.Rental.class));
            model.addAttribute("confirmations", CollectionUtil.toCollection(RoomUtil.Confirmation.class));
            model.addAttribute("payments", CollectionUtil.toCollection(RoomUtil.Payment.class));
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
