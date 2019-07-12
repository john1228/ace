package com.ace.controller.admin;

import com.ace.annotation.Recordable;
import com.ace.controller.admin.concerns.DataTable;
import com.ace.controller.admin.concerns.InvoiceCriteria;
import com.ace.entity.Invoice;
import com.ace.entity.InvoiceOrder;
import com.ace.entity.Staff;
import com.ace.entity.concern.invoice.InvoiceStatus;
import com.ace.service.admin.InvoiceService;
import com.ace.util.CollectionUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;


@Controller
@RequestMapping("/admin/invoices")
public class InvoicesController extends BaseController {
    static String viewPath = "/admin/invoices/";
    @Resource
    private InvoiceService invoiceService;


    @GetMapping({"", "/"})
    public String index(Model model) {
        model.addAttribute("statuses", CollectionUtil.toCollection(InvoiceStatus.class));
        return viewPath + "index";
    }

    @ResponseBody
    @PostMapping("/dataList")
    public DataTable<InvoiceOrder> dataList(
            @SessionAttribute(value = CURRENT_OPERATOR, required = false) Staff staff,
            InvoiceCriteria criteria
    ) {
        DataTable<InvoiceOrder> dataTable = invoiceService.dataTable(staff, criteria);
        return dataTable;
    }

    @GetMapping("/new")
    public String add(@RequestParam("orderNo") String orderNo, Model model) {
        model.addAttribute("orderNo", orderNo);
        model.addAttribute("invoice", new Invoice());
        return viewPath + "new";
    }


    @PostMapping(value = {"", "/"})
    @Recordable
    public String create(@RequestParam("orderNo") String orderNo, @Valid Invoice invoice, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            model.addAttribute("orderNo", orderNo);
            model.addAttribute("invoice", invoice);
            return viewPath + "new";
        } else {
            invoiceService.create(orderNo, invoice);
            model.addAttribute("invoice", invoice);
            return "redirect:" + viewPath + orderNo;
        }
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") String orderNo, Model model) {
        InvoiceOrder iOrder = invoiceService.findOrder(orderNo);
        Invoice invoice = invoiceService.findByOrderNo(orderNo);
        model.addAttribute("order", iOrder);
        model.addAttribute("invoice", invoice);
        return viewPath + "show";
    }

    @PostMapping("/{orderNo}/mail")
    @Recordable
    public String update(@PathVariable("orderNo") String orderNo, Invoice invoice) {
        invoiceService.mail(orderNo, invoice);
        return "redirect:" + viewPath + orderNo;
    }
}
