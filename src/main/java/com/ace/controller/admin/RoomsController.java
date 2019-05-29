package com.ace.controller.admin;

import com.ace.annotation.Recordable;
import com.ace.controller.admin.concerns.AdminView;
import com.ace.controller.admin.concerns.DataTable;
import com.ace.controller.admin.concerns.RoomCriteria;
import com.ace.controller.api.concerns.Result;
import com.ace.controller.api.concerns.Success;
import com.ace.entity.RoomSupport;
import com.ace.entity.Staff;
import com.ace.entity.concern.enums.RoomCFM;
import com.ace.entity.concern.enums.RoomPublish;
import com.ace.entity.concern.enums.RoomRental;
import com.ace.entity.Room;
import com.ace.service.admin.RoomService;
import com.ace.service.admin.SupportService;
import com.ace.util.Aliyun;
import com.ace.util.CollectionUtil;
import com.fasterxml.jackson.annotation.JsonView;
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
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/admin/rooms")
public class RoomsController extends BaseController {
    Logger logger = LoggerFactory.getLogger(RoomsController.class);
    static String viewPath = "/admin/rooms/";
    @Resource
    private RoomService roomService;
    @Resource
    private SupportService supportService;


    @GetMapping({"", "/"})
    public String index() {
        logger.info("111");
        return viewPath + "index";
    }

    @ResponseBody
    @PostMapping("/dataList")
    @JsonView(AdminView.Table.class)
    public DataTable<Room> dataList(@SessionAttribute(CURRENT_OPERATOR) Staff staff, DataTable<Room> dataTable, RoomCriteria criteria) {
        logger.info("请求数据");
        roomService.data(staff, dataTable, criteria);
        return dataTable;
    }

    @GetMapping("/new")
    public String add(@SessionAttribute(CURRENT_OPERATOR) Staff staff, Model model) {
        model.addAttribute("room", new Room());
        model.addAttribute("types", CollectionUtil.toCollection("室内", "室外"));
        model.addAttribute("publish", CollectionUtil.toCollection(RoomPublish.class));
        model.addAttribute("free", CollectionUtil.trueOrFalseCollection("免费", "收费"));
        model.addAttribute("rentals", CollectionUtil.toCollection(RoomRental.class));
        model.addAttribute("confirmations", CollectionUtil.toCollection(RoomCFM.class));
        model.addAttribute("payable", CollectionUtil.trueOrFalseCollection("是", "否"));
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
            model.addAttribute("types", CollectionUtil.toCollection("室内", "室外"));
            model.addAttribute("publish", CollectionUtil.toCollection(RoomPublish.class));
            model.addAttribute("free", CollectionUtil.trueOrFalseCollection("免费", "收费"));
            model.addAttribute("rentals", CollectionUtil.toCollection(RoomRental.class));
            model.addAttribute("confirmations", CollectionUtil.toCollection(RoomCFM.class));
            model.addAttribute("payable", CollectionUtil.trueOrFalseCollection("是", "否"));
            model.addAttribute("room", room);
            return viewPath + "new";
        } else {
            roomService.create(staff, room);
            model.addAttribute("room", room);
            return "redirect:" + "/admin/rooms/" + room.getId();

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
        model.addAttribute("types", CollectionUtil.toCollection("室内", "室外"));
        model.addAttribute("publish", CollectionUtil.toCollection(RoomPublish.class));
        model.addAttribute("free", CollectionUtil.trueOrFalseCollection("免费", "收费"));
        model.addAttribute("rentals", CollectionUtil.toCollection(RoomRental.class));
        model.addAttribute("confirmations", CollectionUtil.toCollection(RoomCFM.class));
        model.addAttribute("payable", CollectionUtil.trueOrFalseCollection("是", "否"));
        model.addAttribute("supports", supportService.supportList(staff));
        return viewPath + "edit";
    }

    @PutMapping({"/{id}/", "/{id}"})
    public String update(@SessionAttribute(CURRENT_OPERATOR) Staff staff, @Valid Room room, BindingResult result, @PathVariable("id") int id, Model model) {
        model.addAttribute("room", room);
        if (result.hasErrors()) {
            model.addAttribute("rentals", CollectionUtil.toCollection(RoomRental.class));
            model.addAttribute("confirmations", CollectionUtil.toCollection(RoomCFM.class));
            model.addAttribute("payments", CollectionUtil.trueOrFalseCollection("是", "否"));
            return viewPath + "edit";
        } else {
            roomService.update(staff, room);
            return viewPath + "show";
        }

    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        return viewPath + "index";
    }


    @ResponseBody
    @JsonView(AdminView.Table.class)
    @PostMapping("/supportList")
    public Result supports(@RequestParam("roomId") Long roomId) {
        return new Success(roomService.roomSupports(roomId));
    }
}
