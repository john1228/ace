package com.ace.controller.admin;

import com.ace.controller.admin.concerns.DataTable;
import com.ace.controller.admin.concerns.OrderCriteria;
import com.ace.controller.admin.concerns.ReceiptCriteria;
import com.ace.entity.Order;
import com.ace.entity.Receipt;
import com.ace.entity.ReceiptDetail;
import com.ace.entity.Staff;
import com.ace.service.admin.ReceiptService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;


@Controller
@RequestMapping("/admin/receipts")
public class ReceiptsController extends BaseController {
    static String viewPath = "/admin/receipts/";
    @Resource
    private ReceiptService receiptService;


    @GetMapping({"", "/"})
    public String index(Model model) {
        return viewPath + "index";
    }

    @ResponseBody
    @PostMapping("/dataList")
    public DataTable<ReceiptDetail> dataList(
            @SessionAttribute(value = CURRENT_OPERATOR, required = false) Staff staff,
            ReceiptCriteria criteria,
            DataTable<ReceiptDetail> dataTable
    ) {
        receiptService.dataTable(staff, criteria, dataTable);
        return dataTable;
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        Receipt receipt = receiptService.findById(id);
        model.addAttribute("receipt", receipt);
        return viewPath + "show";
    }
}
