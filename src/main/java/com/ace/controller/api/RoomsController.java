package com.ace.controller.api;

import com.ace.annotation.Authorization;
import com.ace.controller.api.concerns.ApiView;
import com.ace.controller.api.concerns.Query;
import com.ace.controller.api.concerns.Result;
import com.ace.controller.api.concerns.Success;
import com.ace.entity.Account;
import com.ace.entity.Schedule;
import com.ace.service.api.RoomService;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Api(tags = "会议室列表")
@RestController("api_room")
@RequestMapping("/api/rooms")
public class RoomsController extends BaseController {

    @Resource
    RoomService roomService;

    @GetMapping({""})
    @Authorization
    @JsonView(ApiView.Base.class)
    @ApiOperation(value = "查询会议室")
    public Result list(
            @ApiParam(hidden = true)
            @RequestAttribute("ACCOUNT") Account account,
            Query query
    ) {
        if (account.getStaffList().size() == 0) {
            return new Success(new ArrayList<>());
        } else {
            return new Success(roomService.query(account, query));
        }
    }

    @JsonView(ApiView.Detail.class)
    @GetMapping("/{id}")
    @Authorization
    @ApiOperation(value = "会议室详情")
    public Result show(@PathVariable("id") Long id) {
        return new Success(roomService.show(id));
    }

    @JsonView(ApiView.Base.class)
    @GetMapping("/{id}/schedule")
    @Authorization
    @ApiOperation(value = "会议室排期")
    public Result schedule(@PathVariable("id") Long id, @RequestParam("date") Date date) {
        return new Success(roomService.schedule(id, date));
    }

    @JsonView(ApiView.Base.class)
    @GetMapping("/{id}/protocol")
    @Authorization
    @ApiOperation(value = "会议室协议")
    public Result protocol(@PathVariable("id") Long id) {
        return new Success(roomService.protocol(id));
    }
}
