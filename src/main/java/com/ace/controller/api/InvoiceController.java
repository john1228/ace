package com.ace.controller.api;

import com.ace.annotation.Authorization;
import com.ace.controller.api.concerns.Failure;
import com.ace.controller.api.concerns.Result;
import com.ace.controller.api.concerns.Success;
import com.ace.entity.Account;
import com.ace.entity.Invoice;
import com.ace.service.api.InvoiceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author john
 * @date 19-5-14 下午3:08
 */
@Api(tags = "发票管理")
@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    @Resource
    private InvoiceService invoiceService;

    @ResponseBody
    @PostMapping({"", "/"})
    @ApiOperation(value = "新增发票和修改发票")
    @Authorization
    public Result create(@RequestAttribute("ACCOUNT") Account account, @RequestParam("orderNo") String orderNo, Invoice invoice) {
        invoiceService.create(account, orderNo, invoice);
        if (account.getErrMsg() != null) {
            return new Failure(account.getErrMsg());
        } else {
            return new Success(invoice);
        }
    }

    @ResponseBody
    @GetMapping("/{id}")
    @ApiOperation(value = "查看发票")
    @Authorization
    public Result show(@PathVariable("id") String orderNo) {
        Invoice invoice = invoiceService.show(orderNo);
        if (invoice == null) {
            return new Success("您还未申请发票");
        } else {
            return new Success(invoiceService.show(orderNo));
        }
    }
}

