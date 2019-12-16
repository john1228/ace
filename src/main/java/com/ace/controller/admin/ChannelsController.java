package com.ace.controller.admin;

import com.ace.controller.admin.concerns.AdminView;
import com.ace.controller.admin.concerns.DataTable;
import com.ace.controller.admin.concerns.ReportCriteria;
import com.ace.entity.Account;
import com.ace.entity.ChannelReport;
import com.ace.entity.Room;
import com.ace.entity.Staff;
import com.ace.service.admin.ChannelService;
import com.ace.util.remote.DataUtils;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @author john
 * @date 19-9-25 下午5:29
 */
@Controller
@RequestMapping("/admin/channels")
@Log4j2
public class ChannelsController extends BaseController {

    static String viewPath = "/admin/channels/";
    @Resource
    private ChannelService channelService;


    @GetMapping({"", "/"})
    public String index(Authentication authentication, Model model) {
        Account account = (Account) authentication.getCredentials();
        model.addAttribute("current_project", DataUtils.proList(account.getToken()));
        return viewPath + "index";
    }

    @ResponseBody
    @PostMapping("/dataList")
    @JsonView(AdminView.Table.class)
    public DataTable<ChannelReport> dataList(@SessionAttribute(name = CURRENT_OPERATOR, required = false) Staff staff, DataTable<ChannelReport> dataTable, ReportCriteria criteria) {
        channelService.data(staff, dataTable, criteria);
        return dataTable;
    }

}
