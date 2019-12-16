package com.ace.controller.admin;

import com.ace.annotation.Recordable;
import com.ace.entity.*;
import com.ace.service.admin.ProtocolService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 协议管理
 *
 * @author john
 * @date 19-6-5 下午7:00
 */
@Controller
@RequestMapping("/admin/protocol")
public class ProtocolController extends BaseController {
    static final String viewPath = "/admin/protocol/";
    @Resource
    ProtocolService protocolService;

    /**
     * 查看协议
     *
     * @param staff
     * @param model
     * @return
     */
    @GetMapping({"/", ""})
    public String protocol(@SessionAttribute(CURRENT_OPERATOR) Staff staff, Model model) {
        model.addAttribute("protocol", protocolService.protocol(staff));
        return viewPath + "show";
    }

    @GetMapping({"/edit", "edit/"})
    public String editProtocol(@SessionAttribute(CURRENT_OPERATOR) Staff staff, Model model) {
        model.addAttribute("protocol", protocolService.protocol(staff));
        return viewPath + "edit";
    }

    /**
     * 更新协议
     *
     * @param staff
     * @param protocol
     * @return
     */
    @PutMapping
    @Recordable
    public String updateProtocol(@SessionAttribute(CURRENT_OPERATOR) Staff staff, @RequestParam("protocol") String protocol) {
        protocolService.updateProtocol(staff, protocol);
        return "redirect:" + viewPath;
    }
}
