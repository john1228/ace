package com.ace.controller.admin;

import com.ace.entity.Account;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 管理员资料查看
 *
 * @author john
 * @date 19-7-23 上午11:44
 */
@Controller
@RequestMapping("/admin/profile")
public class ProfileController {
    static String viewPath = "/admin/profile/";

    @GetMapping("/")
    public String show(Authentication authentication, Model model) {
        Account account = (Account) authentication.getCredentials();
        model.addAttribute("account", account);
        return viewPath + "show";
    }
}
