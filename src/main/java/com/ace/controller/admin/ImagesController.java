package com.ace.controller.admin;

import com.ace.annotation.Recordable;
import com.ace.controller.admin.concerns.AdminView;
import com.ace.controller.admin.concerns.DataTable;
import com.ace.controller.admin.concerns.RoomCriteria;
import com.ace.controller.api.concerns.Result;
import com.ace.controller.api.concerns.Success;
import com.ace.entity.Room;
import com.ace.entity.Staff;
import com.ace.service.admin.RoomService;
import com.ace.service.admin.SupportService;
import com.ace.util.Aliyun;
import com.ace.util.remote.DataUtils;
import com.fasterxml.jackson.annotation.JsonView;
import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/admin/images")
@Slf4j
public class ImagesController extends BaseController {
    @PostMapping({"", "/"})
    @Recordable
    @ResponseBody
    public Map<String, String> upload(@RequestParam("file") MultipartFile file) throws IOException {
        try {
            log.info("上传照片");
            String path = Aliyun.Instance.upload(file);
            return ImmutableMap.of("link", Aliyun.Instance.imgHost + path);
        } catch (Exception exp) {
            log.info("上传出错:" + exp.getMessage());
            return null;
        }

    }
}
