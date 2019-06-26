package com.ace.controller.admin;

import com.ace.annotation.Recordable;
import com.ace.controller.admin.concerns.DataTable;
import com.ace.controller.admin.concerns.PriceCriteria;
import com.ace.entity.Staff;
import com.ace.entity.concern.enums.RoomRental;
import com.ace.entity.concern.enums.Week;
import com.ace.entity.Price;
import com.ace.entity.Room;
import com.ace.service.admin.PriceService;
import com.ace.service.admin.RoomService;
import com.ace.util.CollectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/admin/prices")
public class PricesController extends BaseController {
    static String viewPath = "/admin/prices/";
    @Resource
    private PriceService priceService;
    @Resource
    private RoomService roomService;

    @GetMapping({"", "/"})
    public String index() {
        return viewPath + "index";
    }

    @ResponseBody
    @PostMapping("/dataList")
    public DataTable<Price> dataList(
            @SessionAttribute(CURRENT_OPERATOR) Staff staff,
            PriceCriteria criteria,
            DataTable<Price> dataTable
    ) {
        priceService.dataTable(staff, criteria, dataTable);
        return dataTable;
    }

    @GetMapping("/new")
    public String add(@SessionAttribute(CURRENT_OPERATOR) Staff staff, Model model) {
        model.addAttribute("price", new Price());
        model.addAttribute("rooms", roomService.roomList(staff).stream().collect(Collectors.toMap(room -> String.valueOf(room.getId()), Room::getName)));
        model.addAttribute("rentals", CollectionUtil.toCollection(RoomRental.class));
        model.addAttribute("weeks", Week.toOptions());
        return viewPath + "new";
    }


    @PostMapping({"", "/"})
    @Recordable
    public String create(@SessionAttribute(CURRENT_OPERATOR) Staff staff, @Valid Price price, BindingResult result, Model model) {
        model.addAttribute("price", price);
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            model.addAttribute("rooms", roomService.roomList(staff).stream().collect(Collectors.toMap(room -> String.valueOf(room.getId()), Room::getName)));
            model.addAttribute("rentals", CollectionUtil.toCollection(RoomRental.class));
            model.addAttribute("weeks", Week.toOptions());
            return viewPath + "new";
        } else {
            priceService.create(price);
            return "redirect:" + viewPath;
        }
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        Price price = priceService.findById(id);
        model.addAttribute("price", price);
        return viewPath + "show";
    }

    @GetMapping("/{id}/edit")
    public String edit(@SessionAttribute(CURRENT_OPERATOR) Staff staff, @PathVariable("id") Long id, Model model) {
        Price price = priceService.findById(id);
        model.addAttribute("price", price);
        model.addAttribute("rooms", roomService.roomList(staff).stream().collect(Collectors.toMap(room -> String.valueOf(room.getId()), Room::getName)));
        model.addAttribute("rentals", CollectionUtil.toCollection(RoomRental.class));
        model.addAttribute("weeks", Week.toOptions());
        return viewPath + "edit";
    }

    @PutMapping({"/{id}/", "/{id}/edit"})
    @Recordable
    public String update(
            @PathVariable("id") Long id,
            @SessionAttribute(CURRENT_OPERATOR) Staff staff,
            @Valid Price price,
            BindingResult result,
            Model model
    ) {
        model.addAttribute("price", price);
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            model.addAttribute("rooms", roomService.roomList(staff).stream().collect(Collectors.toMap(room -> String.valueOf(room.getId()), Room::getName)));
            model.addAttribute("rentals", CollectionUtil.toCollection(RoomRental.class));
            model.addAttribute("weeks", Week.toOptions());
            return viewPath + "edit";
        } else {
            priceService.update(price);
            return "redirect:" + viewPath;
        }

    }

    @DeleteMapping("/{id}")
    @Recordable
    @ResponseBody
    public String delete(@PathVariable("id") Long id) {
        priceService.delete(id);
        return "SUCCESS";
    }
}
