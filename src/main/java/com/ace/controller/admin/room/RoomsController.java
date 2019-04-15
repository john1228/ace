package com.ace.controller.admin.room;

import com.ace.controller.admin.BaseController;
import com.ace.controller.admin.concerns.DataTable;
import com.ace.entity.Staff;
import com.ace.entity.room.Room;
import com.ace.entity.room.concern.RoomUtil;
import com.ace.service.room.RoomService;
import com.ace.util.Aliyun;
import com.ace.util.CollectionUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/admin/rooms")
public class RoomsController extends BaseController {
    static String viewPath = "/admin/rooms/";
    @Resource
    private RoomService roomService;


    @GetMapping({"", "/"})
    public String index() {
        return viewPath + "index";
    }

    @ResponseBody
    @GetMapping("/dataList")
    public DataTable<Room> dataList(
            HttpSession session,
            @RequestParam(value = "draw", defaultValue = "1") int draw,
            @RequestParam(value = "start", defaultValue = "0") int start,
            @RequestParam(value = "length", defaultValue = "10") int length,
            @RequestParam(value = "search[value]", defaultValue = "") String keyword
    ) {
        Staff staff = (Staff) session.getAttribute(CURRENT_OPERATOR);
        DataTable<Room> dataTable = roomService.dataTable(staff, start, length, keyword);
        dataTable.setDraw(draw);
        return dataTable;
    }

    @GetMapping("/new")
    public String add(Model model) {
        model.addAttribute("room", new Room());
        model.addAttribute("types", CollectionUtil.toCollection(RoomUtil.Type.class));
        model.addAttribute("publish", CollectionUtil.toCollection(RoomUtil.Publish.class));
        model.addAttribute("free", CollectionUtil.trueOrFalseCollection("免费", "收费"));
        model.addAttribute("rentals", CollectionUtil.toCollection(RoomUtil.Rental.class));
        model.addAttribute("confirmations", CollectionUtil.toCollection(RoomUtil.CFM.class));
        model.addAttribute("payable", CollectionUtil.trueOrFalseCollection("是", "否"));
        return viewPath + "new";
    }


    @PostMapping("")
    public String create(
            @RequestParam("parent") String parent,
            @RequestParam("cover") MultipartFile avatar,
            @RequestParam("image") MultipartFile[] image,
            @Valid Room room,
            BindingResult result,
            Model model
    ) {
        try {
            String fileName = Aliyun.Instance.upload("", avatar);
            room.setCover(fileName);
        } catch (Exception exp) {
            result.addError(new ObjectError("cover", "上传列表图片失败"));
        }

        try {
            List<String> images = new ArrayList<>();
            for (MultipartFile tmp : image) {
                images.add(Aliyun.Instance.upload("", tmp));
            }
        } catch (Exception exp) {
            result.addError(new ObjectError("image", "上传图片失败"));
        }
        if (result.hasErrors()) {
            model.addAttribute("types", CollectionUtil.toCollection(RoomUtil.Type.class));
            model.addAttribute("publish", CollectionUtil.toCollection(RoomUtil.Publish.class));
            model.addAttribute("free", CollectionUtil.trueOrFalseCollection("免费", "收费"));
            model.addAttribute("rentals", CollectionUtil.toCollection(RoomUtil.Rental.class));
            model.addAttribute("confirmations", CollectionUtil.toCollection(RoomUtil.CFM.class));
            model.addAttribute("payable", CollectionUtil.trueOrFalseCollection("是", "否"));
            model.addAttribute("room", room);
            return viewPath + "new";
        } else {
            roomService.create(room);
            model.addAttribute("room", room);
            return "redirect:" + "/admin/rooms/" + room.getId() + "/show?parent=" + parent;
        }
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        Room room = roomService.findById(id);
        model.addAttribute("room", room);
        return viewPath + "show";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        Room room = roomService.findById(id);
        model.addAttribute("room", room);
        model.addAttribute("rentals", CollectionUtil.toCollection(RoomUtil.Rental.class));
        model.addAttribute("confirmations", CollectionUtil.toCollection(RoomUtil.CFM.class));
        model.addAttribute("payments", CollectionUtil.toCollection(RoomUtil.Payable.class));
        return viewPath + "edit";
    }

    @PutMapping("/{id}/update")
    public String update(@Valid Room room, BindingResult result, HttpServletRequest request, @PathVariable("id") int id, Model model) {
        model.addAttribute("room", room);
        if (result.hasErrors()) {
            model.addAttribute("rentals", CollectionUtil.toCollection(RoomUtil.Rental.class));
            model.addAttribute("confirmations", CollectionUtil.toCollection(RoomUtil.CFM.class));
            model.addAttribute("payments", CollectionUtil.toCollection(RoomUtil.Payable.class));
            return viewPath + "edit";
        } else {
            roomService.update(room);
            return viewPath + "show";
        }

    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        return viewPath + "index";
    }
}
