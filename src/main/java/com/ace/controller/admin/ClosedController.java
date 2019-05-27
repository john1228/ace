package com.ace.controller.admin;

import com.ace.controller.admin.concerns.DataTable;
import com.ace.entity.RoomClosed;
import com.ace.service.admin.RoomClosedService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author john
 * @date 19-5-20 上午9:53
 */
@RestController
@RequestMapping("/admin/rooms/{id}/closed")
public class ClosedController {
    static final String viewPath = "/admin/rooms/";
    @Resource
    RoomClosedService rcService;

    @ResponseBody
    @GetMapping("/dataList")
    public DataTable<RoomClosed> dataList(@PathVariable("id") Long roomId, DataTable<RoomClosed> dataTable) {
        rcService.data(roomId, dataTable);
        return dataTable;
    }

    @PostMapping(value = {"", "/"})
    public RedirectView create(@Valid RoomClosed closed) {
        rcService.create(closed);
        return new RedirectView(viewPath + closed.getRoomId());
    }
}
