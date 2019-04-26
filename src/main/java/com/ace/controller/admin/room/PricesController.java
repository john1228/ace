package com.ace.controller.admin.room;

import com.ace.controller.admin.BaseController;
import com.ace.controller.admin.concerns.DataTable;
import com.ace.entity.Staff;
import com.ace.entity.concern.EnumUtils;
import com.ace.entity.room.Price;
import com.ace.entity.room.Room;
import com.ace.entity.room.concern.RoomUtil;
import com.ace.service.room.PriceService;
import com.ace.service.room.RoomService;
import com.ace.util.CollectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/admin/prices")
public class PricesController extends BaseController {
    Logger logger = LoggerFactory.getLogger(PricesController.class);
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
    @GetMapping("/dataList")
    public DataTable<Price> dataList(
            HttpSession session,
            @RequestParam(value = "draw", defaultValue = "1") int draw,
            @RequestParam(value = "start", defaultValue = "0") int start,
            @RequestParam(value = "length", defaultValue = "10") int length,
            @RequestParam(value = "search[value]", defaultValue = "") String keyword
    ) {
        Staff staff = (Staff) session.getAttribute(CURRENT_OPERATOR);
        DataTable<Price> dataTable = priceService.dataTable(staff, start, length, keyword);
        dataTable.setDraw(draw);
        return dataTable;
    }

    @GetMapping("/new")
    public String add(HttpSession session, Model model) {
        Staff staff = (Staff) session.getAttribute(CURRENT_OPERATOR);
        model.addAttribute("price", new Price());
        model.addAttribute("rooms", roomService.roomList(staff).stream().collect(Collectors.toMap(room -> String.valueOf(room.getId()), Room::getName)));
        model.addAttribute("rentals", CollectionUtil.toCollection(RoomUtil.Rental.class));
        model.addAttribute("weeks", CollectionUtil.toCollection(EnumUtils.Week.class));
        return viewPath + "new";
    }


    @PostMapping({"", "/"})
    public String create(HttpSession session, @Valid Price price, BindingResult result, Model model, @RequestParam("parent") String parent) {
        model.addAttribute("price", price);
        if (result.hasErrors()) {
            Staff staff = (Staff) session.getAttribute(CURRENT_OPERATOR);
            model.addAttribute("rooms", roomService.roomList(staff).stream().collect(Collectors.toMap(room -> String.valueOf(room.getId()), Room::getName)));
            model.addAttribute("rentals", CollectionUtil.toCollection(RoomUtil.Rental.class));
            model.addAttribute("weeks", CollectionUtil.toCollection(EnumUtils.Week.class));
            return viewPath + "new";
        } else {
            priceService.create(price);
            return "redirect:" + "/admin/prices/" + price.getId() + "?parent=" + parent;
        }
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        Price price = priceService.findById(id);
        logger.info(String.valueOf(price.getWday().get(0)));
        model.addAttribute("price", price);
        return viewPath + "show";
    }

    @GetMapping("/{id}/edit")
    public String edit(HttpSession session, @PathVariable("id") int id, Model model) {
        Staff staff = (Staff) session.getAttribute(CURRENT_OPERATOR);
        Price price = priceService.findById(id);
        model.addAttribute("price", price);
        model.addAttribute("rooms", roomService.roomList(staff).stream().collect(Collectors.toMap(room -> String.valueOf(room.getId()), Room::getName)));
        model.addAttribute("rentals", CollectionUtil.toCollection(RoomUtil.Rental.class));
        model.addAttribute("weeks", CollectionUtil.toCollection(EnumUtils.Week.class));
        return viewPath + "edit";
    }

    @PutMapping("/{id}/update")
    public String update(@Valid Price price, BindingResult result, HttpServletRequest request, @PathVariable("id") int id, Model model) {
        model.addAttribute("price", price);
        if (result.hasErrors()) {
            model.addAttribute("rentals", CollectionUtil.toCollection(RoomUtil.Rental.class));
            return viewPath + "edit";
        } else {
            priceService.update(price);
            return viewPath + "show";
        }

    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        priceService.delete(id);
    }
}
