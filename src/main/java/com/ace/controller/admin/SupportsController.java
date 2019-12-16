package com.ace.controller.admin;

import com.ace.annotation.Recordable;
import com.ace.controller.admin.concerns.DataTable;
import com.ace.controller.admin.concerns.SupportCriteria;
import com.ace.entity.Staff;
import com.ace.entity.Support;
import com.ace.service.admin.SupportService;
import com.ace.util.Aliyun;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 服务管理
 */
@Controller
@RequestMapping("/admin/supports")
@Log4j2
public class SupportsController extends BaseController {
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
            @SessionAttribute(value = CURRENT_OPERATOR, required = false) Staff staff,
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


    /**
     * 添加
     **/
    @PostMapping({"", "/"})
    @Recordable
    public String create(@SessionAttribute(CURRENT_OPERATOR) Staff staff, @RequestParam("coverFile") MultipartFile cover, @Valid Support support, BindingResult result, Model model) {
        model.addAttribute("support", support);
        upload(support, cover);
        if (Strings.isBlank(support.getCover())) {
            result.getAllErrors().add(new ObjectError("cover", "封面不能为空"));
        }
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

    /**
     * 更新
     **/
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
        model.addAttribute("support", support);
        if (!cover.isEmpty()) upload(support, cover);
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            return "redirect:" + viewPath + id + "/edit";
        } else {
            supportService.update(staff, support);
            return "redirect:" + viewPath;
        }

    }

    /**
     * 删除
     **/
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
            log.info(exp.getMessage());
        }
    }
}
