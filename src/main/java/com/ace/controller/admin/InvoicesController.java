package com.ace.controller.admin;

import com.ace.controller.admin.concerns.DataTable;
import com.ace.entity.Invoice;
import com.ace.entity.Order;
import com.ace.entity.concern.InvoiceUtil;
import com.ace.entity.concern.OrderUtil;
import com.ace.service.InvoiceService;
import com.ace.service.OrderService;
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
    public String index() {
        return viewPath + "index";
    }

    @ResponseBody
    @GetMapping("/dataList")
    public DataTable<Invoice> dataList(
            @RequestParam(value = "draw", defaultValue = "1") int draw,
            @RequestParam(value = "start", defaultValue = "0") int start,
            @RequestParam(value = "length", defaultValue = "10") int length,
            @RequestParam(value = "search[value]", defaultValue = "") String keyword
    ) {
        DataTable<Invoice> dataTable = invoiceService.dataTable(start, length, keyword);
        dataTable.setDraw(draw);
        return dataTable;
    }
    @GetMapping("/new")
    public String add(Model model) {
        model.addAttribute("type", CollectionUtil.toCollection(InvoiceUtil.Type.class));
        model.addAttribute("invoice", new Invoice());
        return viewPath + "new";
    }


    @PostMapping(value = {"", "/"})
    public String create(@Valid Invoice invoice, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return viewPath + "new";
        } else {
            model.addAttribute("invoice", invoice);
            return viewPath + "show";
        }
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        Invoice invoice = invoiceService.findById(id);
        model.addAttribute("invoice", invoice);
        return viewPath + "show";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        Invoice invoice = invoiceService.findById(id);
        model.addAttribute("invoice", invoice);
        return viewPath + "edit";
    }

    @PutMapping("/{id}/update")
    public String update(@Valid Invoice invoice, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return viewPath + "edit";
        } else {
            invoiceService.update(invoice);
            model.addAttribute("invoice", invoice);
            return viewPath + "show";
        }

    }
}
