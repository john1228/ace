package com.ace.controller.admin;

import com.ace.controller.admin.concerns.DataTable;
import com.ace.entity.Order;
import com.ace.entity.concern.OrderStatus;
import com.ace.service.OrderService;
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
@RequestMapping("/admin/orders")
public class OrdersController extends BaseController {
    Logger logger = LoggerFactory.getLogger(OrdersController.class);
    static String viewPath = "/admin/orders/";
    @Resource
    private OrderService orderService;


    @GetMapping({"", "/"})
    public String index() {
        return viewPath + "index";
    }

    @ResponseBody
    @GetMapping("/dataList")
    public DataTable<Order> dataList(
            @RequestParam(value = "draw", defaultValue = "1") int draw,
            @RequestParam(value = "start", defaultValue = "0") int start,
            @RequestParam(value = "length", defaultValue = "10") int length,
            @RequestParam(value = "search[value]", defaultValue = "") String keyword
    ) {
        DataTable<Order> dataTable = orderService.dataTable(start, length, keyword);
        dataTable.setDraw(draw);
        return dataTable;
    }

    @GetMapping("/new")
    public String add(Model model) {
        model.addAttribute("orderStatus", CollectionUtil.toCollection(OrderStatus.class));
        model.addAttribute("Order", new Order());
        return viewPath + "new";
    }


    @PostMapping(value = {"", "/"})
    public String create(@Valid Order order, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return viewPath + "new";
        } else {
            logger.info("创建记录");
            model.addAttribute("order", order);
            return viewPath + "show";
        }
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        Order order = orderService.findById(id);
        model.addAttribute("order", order);
        return viewPath + "show";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        Order order = orderService.findById(id);
        model.addAttribute("order", order);
        return viewPath + "edit";
    }

    @PutMapping("/{id}/update")
    public String update(@Valid Order order, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return viewPath + "edit";
        } else {
            orderService.update(order);
            model.addAttribute("order", order);
            return viewPath + "show";
        }

    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        return viewPath + "index";
    }
}
