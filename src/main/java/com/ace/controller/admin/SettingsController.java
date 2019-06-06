package com.ace.controller.admin;

import com.ace.entity.Alipay;
import com.ace.entity.Staff;
import com.ace.entity.Wxpay;
import com.ace.service.admin.SettingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author john
 * @date 19-6-5 下午7:00
 */
@Controller
@RequestMapping("/admin/settings")
public class SettingsController extends BaseController {
    static final String viewPath = "/admin/settings/";
    @Resource
    SettingService settingService;

    @GetMapping("/alipay")
    public String alipay(@SessionAttribute(CURRENT_OPERATOR) Staff staff, Model model) {
        Alipay alipay = settingService.alipay(staff);
        if (alipay == null)
            alipay = new Alipay();
        model.addAttribute("alipay", alipay);
        return viewPath + "alipay";
    }

    @PutMapping("/alipay")
    public String updateAlipay(@SessionAttribute(CURRENT_OPERATOR) Staff staff, Alipay alipay) {
        settingService.updateAlipay(staff, alipay);
        return "redirect:" + viewPath + "alipay";
    }

    @GetMapping("/wxpay")
    public String wxpay(@SessionAttribute(CURRENT_OPERATOR) Staff staff, Model model) {
        Wxpay wxpay = settingService.wxpay(staff);
        if (wxpay == null)
            wxpay = new Wxpay();
        model.addAttribute("wxpay", wxpay);
        return viewPath + "wxpay";
    }

    @PutMapping("/wxpay")
    public String updateWxpay(@SessionAttribute(CURRENT_OPERATOR) Staff staff, Wxpay wxpay) {
        settingService.updateWxpay(staff, wxpay);
        return "redirect:" + viewPath + "wxpay";
    }

    @GetMapping("/protocol")
    public String protocol(@SessionAttribute(CURRENT_OPERATOR) Staff staff, Model model) {
        model.addAttribute("protocol", settingService.protocol(staff));
        return viewPath + "protocol";
    }

    @PutMapping("/protocol")
    public String updateProtocol(@SessionAttribute(CURRENT_OPERATOR) Staff staff, @RequestParam("protocol") String protocol) {
        settingService.updateProtocol(staff, protocol);
        return "redirect:" + viewPath + "protocol";
    }
}
