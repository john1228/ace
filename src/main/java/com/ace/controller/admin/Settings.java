package com.ace.controller.admin;

import com.ace.entity.Alipay;
import com.ace.entity.Staff;
import com.ace.entity.Wxpay;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author john
 * @date 19-6-5 下午7:00
 */
@Controller
@RequestMapping("/admin")
public class Settings extends BaseController {
    static final String viewPath = "/admin/settings/";

    @GetMapping("/alipay")
    public String alipay(@SessionAttribute(CURRENT_OPERATOR) Staff staff, Model model) {
        model.addAttribute("alipay", new Alipay());
        return viewPath + "alipay";
    }

    @PutMapping("/alipay")
    public String updateAlipay(@SessionAttribute(CURRENT_OPERATOR) Staff staff, Model model) {
        return viewPath + "alipay";
    }

    @GetMapping("/wxpay")
    public String wxpay(@SessionAttribute(CURRENT_OPERATOR) Staff staff, Model model) {
        model.addAttribute("wxpay", new Wxpay());
        return viewPath + "alipay";
    }

    @PutMapping("/wxpay")
    public String updateWxpay(@SessionAttribute(CURRENT_OPERATOR) Staff staff, Model model) {
        return viewPath + "alipay";
    }

    @GetMapping("/protocol")
    public String protocol(@SessionAttribute(CURRENT_OPERATOR) Staff staff, Model model) {
        model.addAttribute("protocol", "");
        return viewPath + "alipay";
    }

    @PutMapping("/protocol")
    public String updateProtocol(@SessionAttribute(CURRENT_OPERATOR) Staff staff, Model model) {
        return viewPath + "alipay";
    }
}
