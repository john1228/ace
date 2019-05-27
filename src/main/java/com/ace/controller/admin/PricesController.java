package com.ace.controller.admin;

import com.ace.controller.admin.concerns.DataTable;
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
        model.addAttribute("rentals", CollectionUtil.toCollection(RoomRental.class));
        model.addAttribute("weeks", Week.toOptions());
        return viewPath + "new";
    }


    @PostMapping({"", "/"})
    public String create(HttpSession session, @Valid Price price, BindingResult result, Model model) {
        model.addAttribute("price", price);
        if (result.hasErrors()) {
            Staff staff = (Staff) session.getAttribute(CURRENT_OPERATOR);
            model.addAttribute("rooms", roomService.roomList(staff).stream().collect(Collectors.toMap(room -> String.valueOf(room.getId()), Room::getName)));
            model.addAttribute("rentals", CollectionUtil.toCollection(RoomRental.class));
            model.addAttribute("weeks", Week.toOptions());
            return viewPath + "new";
        } else {
            priceService.create(price);
            return "redirect:" + "/admin/prices/" + price.getId();
        }
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        Price price = priceService.findById(id);
        logger.info(String.valueOf(price.getWday().get(0)));
        model.addAttribute("price", price);
        return viewPath + "show";
    }

    @GetMapping("/{id}/edit")
    public String edit(HttpSession session, @PathVariable("id") Long id, Model model) {
        Staff staff = (Staff) session.getAttribute(CURRENT_OPERATOR);
        Price price = priceService.findById(id);
        model.addAttribute("price", price);
        model.addAttribute("rooms", roomService.roomList(staff).stream().collect(Collectors.toMap(room -> String.valueOf(room.getId()), Room::getName)));
        model.addAttribute("rentals", CollectionUtil.toCollection(RoomRental.class));
        model.addAttribute("weeks", Week.toOptions());
        return viewPath + "edit";
    }

    @PutMapping({"/{id}/", "/{id}"})
    public String update(HttpSession session, @Valid Price price, BindingResult result, HttpServletRequest request, @PathVariable("id") int id, Model model) {
        Staff staff = (Staff) session.getAttribute(CURRENT_OPERATOR);
        model.addAttribute("price", price);
        if (result.hasErrors()) {
            model.addAttribute("rooms", roomService.roomList(staff).stream().collect(Collectors.toMap(room -> String.valueOf(room.getId()), Room::getName)));
            model.addAttribute("rentals", CollectionUtil.toCollection(RoomRental.class));
            model.addAttribute("weeks", Week.toOptions());
            return viewPath + "edit";
        } else {
            priceService.update(price);
            return viewPath + "show";
        }

    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        priceService.delete(id);
    }
}
