package com.ace.controller.admin;

import com.ace.controller.admin.concerns.DataTable;
import com.ace.entity.Order;
import com.ace.entity.Receipt;
import com.ace.service.OrderService;
import com.ace.service.ReceiptService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@Controller
@RequestMapping("/admin/receipts")
public class ReceiptsController extends BaseController {
    static String viewPath = "/admin/receipts/";
    @Resource
    private ReceiptService receiptService;


    @GetMapping({"", "/"})
    public String index() {
        return viewPath + "index";
    }

    @ResponseBody
    @GetMapping("/dataList")
    public DataTable<Receipt> dataList(
            @RequestParam(value = "draw", defaultValue = "1") int draw,
            @RequestParam(value = "start", defaultValue = "0") int start,
            @RequestParam(value = "length", defaultValue = "10") int length,
            @RequestParam(value = "search[value]", defaultValue = "") String keyword
    ) {
        DataTable<Receipt> dataTable = receiptService.dataTable(start, length, keyword);
        dataTable.setDraw(draw);
        return dataTable;
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        Receipt receipt = receiptService.findById(id);
        model.addAttribute("receipt", receipt);
        return viewPath + "show";
    }
}
