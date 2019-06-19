package com.ace.controller.admin;

import com.ace.annotation.Recordable;
import com.ace.controller.admin.concerns.DataTable;
import com.ace.controller.admin.concerns.SupportCriteria;
import com.ace.entity.Staff;
import com.ace.entity.Support;
import com.ace.service.admin.SupportService;
import com.ace.util.Aliyun;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.Valid;


@Controller
@RequestMapping("/admin/supports")
public class SupportsController extends BaseController {
    Logger logger = LoggerFactory.getLogger(SupportsController.class);
    static String viewPath = "/admin/supports/";
    @Resource
    private SupportService supportService;


    @GetMapping({"", "/"})
    public String index() {
        return viewPath + "index";
    }

    @ResponseBody
    @PostMapping("/dataList")
    public DataTable<Support> dataList(
            @SessionAttribute(CURRENT_OPERATOR) Staff staff,
            SupportCriteria criteria,
            DataTable<Support> dataTable
    ) {
        supportService.dataTable(staff, criteria, dataTable);
        return dataTable;
    }

    @GetMapping("/new")
    public String add(Model model) {
        model.addAttribute("support", new Support());
        return viewPath + "new";
    }


    @PostMapping({"", "/"})
    @Recordable
    public String create(@SessionAttribute(CURRENT_OPERATOR) Staff staff, @RequestParam("coverFile") MultipartFile cover, @Valid Support support, BindingResult result, Model model) {
        model.addAttribute("support", support);
        upload(support, cover);
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            return viewPath + "new";
        } else {
            supportService.create(staff, support);
            return "redirect:" + viewPath;
        }
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        Support support = supportService.findById(id);
        model.addAttribute("support", support);
        return viewPath + "show";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        Support support = supportService.findById(id);
        model.addAttribute("support", support);
        return viewPath + "edit";
    }

    @PutMapping({"/{id}", "/{id}/"})
    @Recordable
    public String update(
            @PathVariable("id") int id,
            @SessionAttribute(CURRENT_OPERATOR) Staff staff,
            @RequestParam("coverFile") MultipartFile cover,
            @Valid Support support,
            BindingResult result,
            Model model
    ) {
        Support old = supportService.findById(id);
        model.addAttribute("support", support);
        if (cover != null) {
            upload(support, cover);
        } else {
            support.setCover(old.getCover());
        }
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            return "redirect:" + viewPath + id + "edit";
        } else {
            supportService.update(staff, support);
            return "redirect:" + viewPath;
        }

    }

    @DeleteMapping("/{id}")
    @Recordable
    @ResponseBody
    public String destroy(@PathVariable("id") int id) {
        supportService.delete(id);
        return "SUCCESS";
    }

    private void upload(Support support, MultipartFile file) {
        try {
            String fileName = Aliyun.Instance.upload(file);
            support.setCover(fileName);
        } catch (Exception exp) {
            logger.info(exp.getMessage());
        }
    }
}
