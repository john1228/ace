package com.ace.controller.admin.room;

import com.ace.controller.admin.BaseController;
import com.ace.controller.admin.concerns.DataTable;
import com.ace.entity.room.Device;
import com.ace.entity.room.Price;
import com.ace.entity.room.concern.DeviceUtil;
import com.ace.entity.room.concern.RoomUtil;
import com.ace.service.room.PriceService;
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
import java.util.LinkedHashMap;


@Controller
@RequestMapping("/admin/prices")
public class PricesController extends BaseController {
    Logger logger = LoggerFactory.getLogger(PricesController.class);
    static String viewPath = "/admin/prices/";
    @Resource
    private PriceService priceService;


    @GetMapping({"", "/"})
    public String index() {
        return viewPath + "index";
    }

    @ResponseBody
    @GetMapping("/dataList")
    public DataTable<Price> dataList(
            @RequestParam(value = "draw", defaultValue = "1") int draw,
            @RequestParam(value = "start", defaultValue = "0") int start,
            @RequestParam(value = "length", defaultValue = "10") int length,
            @RequestParam(value = "search[value]", defaultValue = "") String keyword
    ) {
        DataTable<Price> dataTable = priceService.dataTable(start, length, keyword);
        dataTable.setDraw(draw);
        return dataTable;
    }

    @GetMapping("/new")
    public String add(Model model) {
        model.addAttribute("price", new Price());
        model.addAttribute("rooms", new LinkedHashMap<>());
        model.addAttribute("rentals", CollectionUtil.toCollection(RoomUtil.Rental.class));
        model.addAttribute("weeks", CollectionUtil.toCollection(RoomUtil.Week.class));
        return viewPath + "new";
    }


    @PostMapping({"", "/"})
    public String create(@Valid Price price, BindingResult result, Model model) {
        model.addAttribute("price", price);
        if (result.hasErrors()) {
            model.addAttribute("rooms", new LinkedHashMap<>());
            model.addAttribute("rentals", CollectionUtil.toCollection(RoomUtil.Rental.class));
            model.addAttribute("weeks", CollectionUtil.toCollection(RoomUtil.Week.class));
            return viewPath + "new";
        } else {
            priceService.create(price);
            return viewPath + "show";
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
    public String edit(@PathVariable("id") int id, Model model) {
        Price price = priceService.findById(id);
        model.addAttribute("price", price);
        model.addAttribute("rentals", CollectionUtil.toCollection(RoomUtil.Rental.class));
        model.addAttribute("weeks", CollectionUtil.toCollection(RoomUtil.Week.class));
        return viewPath + "edit";
    }

    @PutMapping("/{id}/update")
    public String update(@Valid Price price, BindingResult result, HttpServletRequest request, @PathVariable("id") int id, Model model) {
        model.addAttribute("price", price);
        if (result.hasErrors()) {
            model.addAttribute("rentals", CollectionUtil.toCollection(RoomUtil.Rental.class));
            model.addAttribute("weeks", CollectionUtil.toCollection(RoomUtil.Week.class));
            return viewPath + "edit";
        } else {
            priceService.update(price);
            return viewPath + "show";
        }

    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        return viewPath + "index";
    }
}
