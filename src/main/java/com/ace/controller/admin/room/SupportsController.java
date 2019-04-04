package com.ace.controller.admin.room;

import com.ace.controller.admin.BaseController;
import com.ace.controller.admin.concerns.DataTable;
import com.ace.entity.Staff;
import com.ace.entity.room.Support;
import com.ace.entity.room.concern.DeviceUtil;
import com.ace.service.room.SupportService;
import com.ace.util.CollectionUtil;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/dataList")
    public DataTable<Support> dataList(
            HttpSession session,
            @RequestParam(value = "draw", defaultValue = "1") int draw,
            @RequestParam(value = "start", defaultValue = "0") int start,
            @RequestParam(value = "length", defaultValue = "10") int length,
            @RequestParam(value = "search[value]", defaultValue = "") String keyword
    ) {
        Staff staff = (Staff) session.getAttribute(CURRENT_OPERATOR);
        DataTable<Support> dataTable = supportService.dataTable(staff, start, length, keyword);
        dataTable.setDraw(draw);
        return dataTable;
    }

    @GetMapping("/new")
    public String add(Model model) {
        model.addAttribute("support", new Support());
        return viewPath + "new";
    }


    @PostMapping({"", "/"})
    public String create(@Param("parent") String parent, @Valid Support support, BindingResult result, Model model) {
        model.addAttribute("support", support);
        if (result.hasErrors()) {
            return viewPath + "new";
        } else {
            supportService.create(support);
            return "redirect:" + viewPath + support.getId() + "?parent=" + parent;
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
    public String update(@PathVariable("id") int id, @Param("parent") String parent, @Valid Support support, BindingResult result, Model model) {
        model.addAttribute("support", support);
        if (result.hasErrors()) {
            return viewPath + "edit";
        } else {
            supportService.update(support);
            return "redirect:" + viewPath + id + "?parent=" + parent;
        }

    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        return viewPath + "index";
    }
}
