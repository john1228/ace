package com.ace.controller.admin;

import com.ace.controller.admin.concerns.DataTable;
import com.ace.entity.Staff;
import com.ace.entity.Support;
import com.ace.service.admin.SupportService;
import com.ace.util.Aliyun;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
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
            HttpSession session,
            @RequestParam(value = "draw", defaultValue = "1") int draw,
            @RequestParam(value = "start", defaultValue = "0") int start,
            @RequestParam(value = "length", defaultValue = "10") int length
    ) {
        Staff staff = (Staff) session.getAttribute(CURRENT_OPERATOR);
        DataTable<Support> dataTable = supportService.dataTable(staff, start, length, "");
        dataTable.setDraw(draw);
        return dataTable;
    }

    @GetMapping("/new")
    public String add(Model model) {
        model.addAttribute("support", new Support());
        return viewPath + "new";
    }


    @PostMapping({"", "/"})
    public String create(@RequestParam("coverFile") MultipartFile cover, @Valid Support support, BindingResult result, Model model) {
        model.addAttribute("support", support);
        upload(support, cover);
        if (result.hasErrors()) {
            return viewPath + "new";
        } else {
            supportService.create(support);
            return "redirect:" + viewPath + support.getId();
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
    public String update(@PathVariable("id") int id, @RequestParam("coverFile") MultipartFile cover, @Valid Support support, BindingResult result, Model model) {
        model.addAttribute("support", support);
        upload(support, cover);
        if (result.hasErrors()) {
            return viewPath + "edit";
        } else {
            supportService.update(support);
            return "redirect:" + viewPath + id;
        }

    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        return viewPath + "index";
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
