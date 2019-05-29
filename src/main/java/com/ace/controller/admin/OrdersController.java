package com.ace.controller.admin;

import com.ace.controller.admin.concerns.AdminView;
import com.ace.controller.admin.concerns.DataTable;
import com.ace.controller.admin.concerns.OrderCriteria;
import com.ace.entity.Appointment;
import com.ace.entity.Order;
import com.ace.entity.Staff;
import com.ace.entity.concern.enums.OrderStatus;
import com.ace.service.admin.OrderService;
import com.ace.service.admin.RoomService;
import com.ace.util.CollectionUtil;
import com.fasterxml.jackson.annotation.JsonView;
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
    @Resource
    private RoomService roomService;


    @GetMapping({"", "/"})
    public String index() {
        return viewPath + "index";
    }

    @ResponseBody
    @PostMapping("/dataList")
    @JsonView(AdminView.Table.class)
    public DataTable<Order> dataList(
            @SessionAttribute(CURRENT_OPERATOR) Staff staff,
            OrderCriteria criteria,
            DataTable<Order> dataTable
    ) {

        orderService.dataTable(staff, criteria, dataTable);
        return dataTable;
    }

    @GetMapping("/new")
    public String add(Model model) {
        model.addAttribute("order", new Order());
        return viewPath + "new";
    }


    @PostMapping(value = {"", "/"})
    public String create(@SessionAttribute(CURRENT_OPERATOR) Staff staff, @Valid Order order, Model model) {
        orderService.create(staff, order);
        if (staff.getErrMsg().length() != 0) {
            return viewPath + "new";
        } else {
            return "redirect:" + viewPath + order.getOrderNo();
        }
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") String orderNo, Model model) {
        Order order = orderService.findById(orderNo);
        model.addAttribute("order", order);
        return viewPath + "show";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") String orderNo, Model model) {
        Order order = orderService.findById(orderNo);
        model.addAttribute("order", order);
        return viewPath + "edit";
    }

    @PutMapping("/{id}/update")
    public String update(@PathVariable("id") String orderNo, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return viewPath + "edit";
        } else {
            orderService.update(orderNo, OrderStatus.CONFIRMING);
            return "redirect:" + viewPath + orderNo;
        }

    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") String id) {
        orderService.delete(id);
        return "redirect:" + viewPath;
    }

    @ModelAttribute("a")
    public void globalAttribute(@SessionAttribute(CURRENT_OPERATOR) Staff staff, Model model) {
        model.addAttribute("orderStatus", CollectionUtil.toCollection(OrderStatus.class));
        model.addAttribute("rooms", roomService.roomList(staff));
    }
}
