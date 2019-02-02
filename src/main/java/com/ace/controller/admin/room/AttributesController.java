package com.ace.controller.admin.room;

import com.ace.controller.admin.BaseController;
import com.ace.controller.admin.concerns.DataTable;
import com.ace.entity.room.Attribute;
import com.ace.service.room.AttributeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@Controller
@RequestMapping("/admin/sites/attributes")
public class AttributesController extends BaseController {
    Logger logger = LoggerFactory.getLogger(AttributesController.class);
    static String viewPath = "/admin/sites/attributes/";
    @Resource
    private AttributeService attributeService;


    @GetMapping
    public String index(@RequestParam(value = "page", defaultValue = "1") int page) {
        return viewPath + "index";
    }

    @ResponseBody
    @GetMapping("/dataList")
    public DataTable<Attribute> dataList(
            @RequestParam(value = "draw", defaultValue = "1") int draw,
            @RequestParam(value = "start", defaultValue = "0") int start,
            @RequestParam(value = "length", defaultValue = "10") int length,
            @RequestParam(value = "search[value]", defaultValue = "") String keyword
    ) {
        DataTable<Attribute> dataTable = attributeService.dataTable(start, length, keyword);
        dataTable.setDraw(draw);
        return dataTable;
    }

    @GetMapping("/new")
    public String add() {
        return viewPath + "new";
    }


    @PostMapping("")
    public String create(Attribute attribute, Model model) {
        attributeService.create(attribute);
        model.addAttribute("attribute", attribute);
        return viewPath + "show";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        Attribute attribute = attributeService.findById(id);
        model.addAttribute("attribute", attribute);
        return viewPath + "show";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        Attribute attribute = attributeService.findById(id);
        model.addAttribute("attribute", attribute);
        return viewPath + "edit";
    }

    @PutMapping("/{id}/update")
    public String update(@Valid Attribute attribute, BindingResult result, HttpServletRequest request, @PathVariable("id") int id, Model model) {
        if (result.hasErrors()) {
            return viewPath + "edit";
        } else {
            attributeService.update(attribute);
            model.addAttribute("attribute", attribute);
            return viewPath + "show";
        }

    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        return viewPath + "index";
    }
}
