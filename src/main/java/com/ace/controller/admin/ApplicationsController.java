package com.ace.controller.admin;

import com.ace.annotation.Recordable;
import com.ace.controller.admin.concerns.DataTable;
import com.ace.controller.admin.concerns.InvoiceCriteria;
import com.ace.controller.admin.concerns.RefundCriteria;
import com.ace.entity.InvoiceOrder;
import com.ace.entity.Order;
import com.ace.entity.RefundApplication;
import com.ace.entity.Staff;
import com.ace.entity.concern.invoice.InvoiceStatus;
import com.ace.service.admin.RefundService;
import com.ace.util.CollectionUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author john
 * @date 19-7-29 下午2:07
 */
@Controller
@RequestMapping("/admin/applications")
@Log4j2
public class ApplicationsController extends BaseController {
    static String viewPath = "/admin/applications/";

    @Resource
    RefundService refundService;

    @GetMapping({"", "/"})
    public String index(Model model) {
        model.addAttribute("statuses", CollectionUtil.toCollection(InvoiceStatus.class));
        return viewPath + "index";
    }

    @ResponseBody
    @PostMapping("/dataList")
    public DataTable<RefundApplication> dataList(
            @SessionAttribute(value = CURRENT_OPERATOR, required = false) Staff staff,
            RefundCriteria criteria,
            DataTable<RefundApplication> dataTable
    ) {
        refundService.dataTable(staff, criteria, dataTable);
        return dataTable;
    }

    @PostMapping("/{id}/reject")
    @Recordable
    @ResponseBody
    public String confirm(@SessionAttribute(value = CURRENT_OPERATOR, required = false) Staff staff, @PathVariable("id") Long id) {
        refundService.reject(staff, id);
        return "SUCCESS";
    }

    @PostMapping("/{id}/agree")
    @Recordable
    public String agree(
            @SessionAttribute(value = CURRENT_OPERATOR, required = false) Staff staff,
            @PathVariable("id") Long id,
            @RequestParam("confirmAmount") BigDecimal confirmAmt
    ) {
        refundService.agree(staff, id, confirmAmt);
        return "redirect:" + viewPath;
    }
}
