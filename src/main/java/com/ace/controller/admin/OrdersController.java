package com.ace.controller.admin;

import com.ace.annotation.Recordable;
import com.ace.controller.admin.concerns.AdminView;
import com.ace.controller.admin.concerns.DataTable;
import com.ace.controller.admin.concerns.OrderCriteria;
import com.ace.controller.api.concerns.Result;
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
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/admin/orders")
public class OrdersController extends BaseController {
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
    @Recordable
    public String create(@SessionAttribute(CURRENT_OPERATOR) Staff staff, @Valid Order order, Model model) {
        orderService.create(staff, order);
        if (staff.getErrMsg().length() != 0) {
            List<ObjectError> errors = Arrays.asList(new ObjectError[]{new ObjectError("订单", staff.getErrMsg().toString())});
            model.addAttribute("errors", errors);
            return viewPath + "new";
        } else {
            return "redirect:" + viewPath;
        }
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") String orderNo, Model model) {
        Order order = orderService.findByOrderNo(orderNo);
        model.addAttribute("order", order);
        return viewPath + "show";
    }

    @PostMapping("/{id}/confirm")
    @Recordable
    @ResponseBody
    public String confirm(@SessionAttribute(CURRENT_OPERATOR) Staff staff, @PathVariable("id") String orderNo) {
        orderService.confirm(staff, orderNo);
        return "SUCCESS";
    }


    @DeleteMapping("/{id}")
    @Recordable
    public String destroy(@PathVariable("id") String id) {
        orderService.delete(id);
        return "redirect:" + viewPath;
    }

    @ModelAttribute
    public void globalAttribute(@SessionAttribute(CURRENT_OPERATOR) Staff staff, Model model) {
        model.addAttribute("orderStatus", CollectionUtil.toCollection(OrderStatus.class));
        model.addAttribute("rooms", roomService.roomList(staff));
    }
}
