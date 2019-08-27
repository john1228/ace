package com.ace.controller.admin;

import com.ace.annotation.Recordable;
import com.ace.controller.admin.concerns.AdminView;
import com.ace.controller.admin.concerns.DataTable;
import com.ace.controller.admin.concerns.PayCriteria;
import com.ace.entity.*;
import com.ace.service.admin.AlipayService;
import com.ace.service.admin.SupportService;
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


@Controller
@RequestMapping("/admin/alipays")
@Log4j2
public class AlipaysController extends BaseController {
    static String viewPath = "/admin/alipays/";
    @Resource
    private AlipayService alipayService;


    @GetMapping({"", "/"})
    public String index(Authentication authentication, Model model) {
        Account account = (Account) authentication.getCredentials();
        model.addAttribute("current_project", DataUtils.proList(account.getAccountId()));
        return viewPath + "index";
    }

    @ResponseBody
    @PostMapping("/dataList")
    @JsonView(AdminView.Table.class)
    public DataTable<Alipay> dataList(DataTable<Alipay> dataTable, PayCriteria criteria) {
        alipayService.data(dataTable, criteria);
        return dataTable;
    }

    @GetMapping("/new")
    public String add(Authentication authentication, Model model) {
        Account account = (Account) authentication.getCredentials();
        model.addAttribute("current_project", DataUtils.proList(account.getAccountId()));
        model.addAttribute("alipay", new Alipay());
        return viewPath + "new";
    }


    @PostMapping({"", "/"})
    @Recordable
    public String create(@Valid Alipay alipay, BindingResult result, Model model) {
        model.addAttribute("alipay", alipay);
        if (result.hasErrors()) {
            return viewPath + "new";
        } else {
            alipayService.create(alipay);
            return "redirect:" + viewPath;
        }
    }

    @GetMapping("/{project_id}")
    public String show(@PathVariable("project_id") String projectId, Model model) {
        model.addAttribute("alipay", alipayService.findById(projectId));
        return viewPath + "show";
    }

    @GetMapping("/{project_id}/edit")
    public String edit(Authentication authentication, @PathVariable("project_id") String projectId, Model model) {
        Account account = (Account) authentication.getCredentials();
        model.addAttribute("current_project", DataUtils.proList(account.getAccountId()));
        model.addAttribute("alipay", alipayService.findById(projectId));
        return viewPath + "edit";
    }

    @PutMapping("/{project_id}")
    public String update(Authentication authentication, @Valid Alipay alipay, BindingResult result, Model model) {
        if (result.hasErrors()) {
            Account account = (Account) authentication.getCredentials();
            model.addAttribute("current_project", DataUtils.proList(account.getAccountId()));
            model.addAttribute("alipay", alipay);
            return viewPath + "edit";
        } else {
            alipayService.update(alipay);
            return "redirect:" + viewPath;
        }
    }

    @DeleteMapping("/{project_id}")
    @Recordable
    @ResponseBody
    public String delete(@PathVariable("project_id") String projectId) {
        alipayService.delete(projectId);
        return "SUCCESS";
    }
}
