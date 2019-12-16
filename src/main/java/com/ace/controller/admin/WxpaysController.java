package com.ace.controller.admin;

import com.ace.annotation.Recordable;
import com.ace.controller.admin.concerns.AdminView;
import com.ace.controller.admin.concerns.DataTable;
import com.ace.controller.admin.concerns.PayCriteria;
import com.ace.entity.Account;
import com.ace.entity.Wxpay;
import com.ace.service.admin.WxpayService;
import com.ace.util.remote.DataUtils;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 微信支付配置管理
 */
@Controller
@RequestMapping("/admin/wxpays")
@Log4j2
public class WxpaysController extends BaseController {
    static String viewPath = "/admin/wxpays/";
    @Resource
    private WxpayService wxpayService;


    @GetMapping({"", "/"})
    public String index(Authentication authentication, Model model) {
        Account account = (Account) authentication.getCredentials();
        model.addAttribute("current_project", DataUtils.proList(account.getToken()));
        return viewPath + "index";
    }

    @ResponseBody
    @PostMapping("/dataList")
    @JsonView(AdminView.Table.class)
    public DataTable<Wxpay> dataList(DataTable<Wxpay> dataTable, PayCriteria criteria) {
        wxpayService.data(dataTable, criteria);
        return dataTable;
    }

    @GetMapping("/new")
    public String add(Authentication authentication, Model model) {
        Account account = (Account) authentication.getCredentials();
        model.addAttribute("current_project", DataUtils.proList(account.getToken()));
        model.addAttribute("wxpay", new Wxpay());
        return viewPath + "new";
    }

    /**
     * 添加
     **/
    @PostMapping({"", "/"})
    @Recordable
    public String create(Authentication authentication, @Valid Wxpay wxpay, BindingResult result, Model model) {
        if (result.hasErrors()) {
            Account account = (Account) authentication.getCredentials();
            model.addAttribute("current_project", DataUtils.proList(account.getToken()));
            model.addAttribute("errors", result.getAllErrors());
            model.addAttribute("wxpay", wxpay);
            return viewPath + "new";
        } else {
            wxpayService.create(wxpay);
            return "redirect:" + viewPath;
        }
    }

    @GetMapping("/{project_id}")
    public String show(@PathVariable("project_id") String projectId, Model model) {
        model.addAttribute("wxpay", wxpayService.findById(projectId));
        return viewPath + "show";
    }

    @GetMapping("/{project_id}/edit")
    public String edit(Authentication authentication, @PathVariable("project_id") String projectId, Model model) {
        Account account = (Account) authentication.getCredentials();
        model.addAttribute("current_project", DataUtils.proList(account.getToken()));
        model.addAttribute("wxpay", wxpayService.findById(projectId));
        return viewPath + "edit";
    }

    /**
     * 更新
     **/
    @PutMapping("/{project_id}")
    public String update(Authentication authentication, @Valid Wxpay wxpay, BindingResult result, Model model) {
        if (result.hasErrors()) {
            Account account = (Account) authentication.getCredentials();
            model.addAttribute("current_project", DataUtils.proList(account.getToken()));
            model.addAttribute("errors", result.getAllErrors());
            model.addAttribute("wxpay", wxpay);
            return viewPath + "edit";
        } else {
            wxpayService.update(wxpay);
            return "redirect:" + viewPath;
        }
    }

    /**
     * 删除
     **/
    @DeleteMapping("/{project_id}")
    @Recordable
    @ResponseBody
    public String delete(@PathVariable("project_id") String projectId) {
        wxpayService.delete(projectId);
        return "SUCCESS";
    }
}
