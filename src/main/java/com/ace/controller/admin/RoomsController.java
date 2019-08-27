package com.ace.controller.admin;

import com.ace.annotation.Recordable;
import com.ace.controller.admin.concerns.AdminView;
import com.ace.controller.admin.concerns.DataTable;
import com.ace.controller.admin.concerns.RoomCriteria;
import com.ace.controller.api.concerns.Result;
import com.ace.controller.api.concerns.Success;
import com.ace.entity.*;
import com.ace.service.admin.RoomService;
import com.ace.service.admin.SupportService;
import com.ace.util.Aliyun;
import com.ace.util.StringUtils;
import com.ace.util.remote.Data;
import com.ace.util.remote.DataUtils;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.core.Authentication;
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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/admin/rooms")
@Log4j2
public class RoomsController extends BaseController {
    static String viewPath = "/admin/rooms/";
    @Resource
    private RoomService roomService;
    @Resource
    private SupportService supportService;


    @GetMapping({"", "/"})
    public String index(Authentication authentication, Model model) {
        Account account = (Account) authentication.getCredentials();
        model.addAttribute("current_project", DataUtils.proList(account.getAccountId()));
        return viewPath + "index";
    }

    @ResponseBody
    @PostMapping("/dataList")
    @JsonView(AdminView.Table.class)
    public DataTable<Room> dataList(@SessionAttribute(value = CURRENT_OPERATOR, required = false) Staff staff, DataTable<Room> dataTable, RoomCriteria criteria) {
        roomService.data(staff, dataTable, criteria);
        return dataTable;
    }

    @GetMapping("/new")
    public String add(@SessionAttribute(CURRENT_OPERATOR) Staff staff, Model model) {
        Room room = new Room();
        List<Support> supports = supportService.supportList(staff);
        List<RoomSupport> roomSupports = supports.stream().map(Support::toRoomSupport).collect(Collectors.toList());
        List<Data> orgList = DataUtils.orgList(staff.getProjectId());
        model.addAttribute("room", room);
        model.addAttribute("supports", roomSupports);
        model.addAttribute("current_orgs", buildFreeOrg(orgList, room));
        return viewPath + "new";
    }


    @PostMapping("")
    @Recordable
    public String create(
            @SessionAttribute(CURRENT_OPERATOR) Staff staff,
            @RequestParam("coverFile") MultipartFile avatar,
            @RequestParam("img[]") MultipartFile[] image,
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
            for (MultipartFile file : image) {
                if (file.isEmpty()) {
                    images.add(Aliyun.Instance.upload(file));
                }
            }
            room.setImage(images);
        } catch (Exception exp) {
            result.addError(new ObjectError("image", "上传图片失败"));
        }
        if (result.hasErrors()) {
            List<Data> orgList = DataUtils.orgList(staff.getProjectId());
            model.addAttribute("room", room);
            model.addAttribute("errors", result.getAllErrors());
            model.addAttribute("current_orgs", buildFreeOrg(orgList, room));
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
        List<Support> supports = supportService.supportList(staff);
        List<Data> orgList = DataUtils.orgList(staff.getProjectId());
        model.addAttribute("room", room);
        model.addAttribute("supports", buildSupport(supports, room));
        model.addAttribute("current_orgs", buildFreeOrg(orgList, room));
        return viewPath + "edit";
    }

    @PutMapping("/{id}/edit")
    @Recordable
    public String update(
            @SessionAttribute(CURRENT_OPERATOR) Staff staff,
            @PathVariable("id") Long id,
            @RequestParam("coverFile") MultipartFile cover,
            @RequestParam("img[]") MultipartFile[] image,
            @RequestParam("remove") List<String> removeList,
            @Valid Room room, BindingResult result,
            Model model
    ) {
        Room old = roomService.findById(id);
        if (!cover.isEmpty()) {
            try {
                room.setCover(Aliyun.Instance.upload(cover));
            } catch (Exception exp) {
                result.addError(new FieldError("会议室", "封面", "更新列表图出错"));
            }
        }
        //图片处理
        List<String> nImgList = new ArrayList<>();
        List<String> oImgList = old.getImage();
        for (int i = 0; i < 3; i++) {
            MultipartFile file = image[i];
            if (file.isEmpty()) {
                if (oImgList.size() > i) {
                    String pImg = oImgList.get(i);
                    if (!removeList.contains(String.valueOf(i))) {
                        nImgList.add(pImg);
                    }
                }
            } else {
                try {
                    String imgPath = Aliyun.Instance.upload(file);
                    nImgList.add(i, imgPath);
                } catch (Exception exp) {
                    log.info("错误：" + exp.getMessage());
                    result.addError(new FieldError("会议室", "图片" + i, "更新场地图失败"));
                }
            }
        }
        room.setImage(nImgList);

        if (result.hasErrors()) {
            List<Support> supports = supportService.supportList(staff);
            List<Data> orgList = DataUtils.orgList(staff.getProjectId());
            model.addAttribute("room", room);
            model.addAttribute("errors", result.getAllErrors());
            model.addAttribute("supports", buildSupport(supports, old));
            model.addAttribute("current_orgs", buildFreeOrg(orgList, room));
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

    private List<RoomSupport> buildSupport(List<Support> supports, Room room) {
        return supports.stream().map(support -> {
            Optional<RoomSupport> roomSupport = room.getSupportList().stream().filter(rs -> rs.getSupportId() == support.getId()).findFirst();
            if (roomSupport.isPresent()) {
                return roomSupport.get();
            } else {
                return support.toRoomSupport();
            }
        }).collect(Collectors.toList());
    }

    private List<RoomFreeOrg> buildFreeOrg(List<Data> orgList, Room room) {
        List<String> rfoList = room.getFreeOrg();
        return orgList.stream().map(data -> {
            if (rfoList.contains(data.getId())) {
                return new RoomFreeOrg(room.getId(), data.getId(), data.getText());
            } else {
                return new RoomFreeOrg(data.getId(), data.getText());
            }
        }).collect(Collectors.toList());
    }
}
