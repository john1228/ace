package com.ace.controller.admin;

import com.ace.annotation.Recordable;
import com.ace.controller.admin.concerns.AdminView;
import com.ace.controller.admin.concerns.DataTable;
import com.ace.controller.admin.concerns.RoomCriteria;
import com.ace.controller.api.concerns.Result;
import com.ace.controller.api.concerns.Success;
import com.ace.entity.Staff;
import com.ace.entity.Room;
import com.ace.service.admin.RoomService;
import com.ace.service.admin.SupportService;
import com.ace.util.Aliyun;
import com.ace.util.StringUtils;
import com.ace.util.remote.DataUtils;
import com.fasterxml.jackson.annotation.JsonView;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/admin/rooms")
public class RoomsController extends BaseController {
    static String viewPath = "/admin/rooms/";
    @Resource
    private RoomService roomService;
    @Resource
    private SupportService supportService;


    @GetMapping({"", "/"})
    public String index() {
        return viewPath + "index";
    }

    @ResponseBody
    @PostMapping("/dataList")
    @JsonView(AdminView.Table.class)
    public DataTable<Room> dataList(@SessionAttribute(CURRENT_OPERATOR) Staff staff, DataTable<Room> dataTable, RoomCriteria criteria) {
        roomService.data(staff, dataTable, criteria);
        return dataTable;
    }

    @GetMapping("/new")
    public String add(@SessionAttribute(CURRENT_OPERATOR) Staff staff, Model model) {
        model.addAttribute("room", new Room());
        model.addAttribute("current_orgs", DataUtils.orgList(staff.getProjectId()));
        model.addAttribute("supports", supportService.supportList(staff));
        return viewPath + "new";
    }


    @PostMapping("")
    @Recordable
    public String create(
            @SessionAttribute(CURRENT_OPERATOR) Staff staff,
            @RequestParam("coverFile") MultipartFile avatar,
            @RequestParam("imageFiles") MultipartFile[] image,
            @Valid Room room,
            BindingResult result,
            Model model
    ) {
        try {
            String fileName = Aliyun.Instance.upload(avatar);
            room.setCover(fileName);
        } catch (Exception exp) {
            result.addError(new ObjectError("cover", "上传列表图片失败"));
        }
        try {
            List<String> images = new ArrayList<>();
            for (MultipartFile tmp : image) {
                images.add(Aliyun.Instance.upload(tmp));
            }
            room.setImage(images);
        } catch (Exception exp) {
            result.addError(new ObjectError("image", "上传图片失败"));
        }
        if (result.hasErrors()) {
            model.addAttribute("room", room);
            model.addAttribute("errors", result.getAllErrors());
            model.addAttribute("current_orgs", DataUtils.orgList(staff.getProjectId()));
            model.addAttribute("supports", supportService.supportList(staff));
            return viewPath + "new";
        } else {
            roomService.create(staff, room);
            return "redirect:" + viewPath;
        }
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        Room room = roomService.findById(id);
        model.addAttribute("room", room);
        return viewPath + "show";
    }

    @GetMapping("/{id}/edit")
    public String edit(@SessionAttribute(CURRENT_OPERATOR) Staff staff, @PathVariable("id") Long id, Model model) {
        Room room = roomService.findById(id);
        model.addAttribute("room", room);
        model.addAttribute("current_orgs", DataUtils.orgList(staff.getProjectId()));
        model.addAttribute("supports", supportService.supportList(staff));
        model.addAttribute("has_supports", room.getSupportList().stream().mapToLong(support -> support.getId()));
        return viewPath + "edit";
    }

    @PutMapping("/{id}/edit")
    @Recordable
    public String update(
            @SessionAttribute(CURRENT_OPERATOR) Staff staff,
            @PathVariable("id") Long id,
            @RequestParam("coverFile") MultipartFile cover,
            @RequestParam("imageFiles") MultipartFile[] image,
            @Valid Room room, BindingResult result,
            Model model
    ) {
        if (!cover.isEmpty()) {
            try {
                room.setCover(Aliyun.Instance.upload(cover));
            } catch (Exception exp) {
                result.addError(new FieldError("会议室", "封面", "更新列表图出错"));
            }
        }
        try {
            boolean changed = false;
            List<String> imageList = new ArrayList<>();
            for (MultipartFile tmp : image) {
                if (!tmp.isEmpty()) {
                    changed = true;
                    imageList.add(Aliyun.Instance.upload(tmp));
                }
            }
            if (changed)
                room.setImage(imageList);
        } catch (Exception exp) {
            result.addError(new FieldError("会议室", "图片", "更新场地图失败"));
        }
        if (result.hasErrors()) {
            model.addAttribute("room", room);
            model.addAttribute("errors", result.getAllErrors());
            model.addAttribute("current_orgs", DataUtils.orgList(staff.getProjectId()));
            model.addAttribute("supports", supportService.supportList(staff));
            return viewPath + "edit";
        } else {
            roomService.update(staff, room);
            return "redirect:" + viewPath;
        }
    }

    @PostMapping("/{id}/enable")
    @Recordable
    @ResponseBody
    public String enable(@SessionAttribute(CURRENT_OPERATOR) Staff staff, @PathVariable("id") Long id) {
        roomService.enable(staff, id);
        return "SUCCESS";
    }


    @PostMapping("/{id}/disable")
    @Recordable
    @ResponseBody
    public String disable(@SessionAttribute(CURRENT_OPERATOR) Staff staff, @PathVariable("id") Long id) {
        roomService.disable(staff, id);
        return "SUCCESS";
    }

    @ResponseBody
    @JsonView(AdminView.Table.class)
    @PostMapping("/supportList")
    public Result supports(@RequestParam("roomId") Long roomId) {
        return new Success(roomService.roomSupports(roomId));
    }
}
