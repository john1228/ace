package com.ace.controller.api.display;

import com.ace.controller.api.concerns.Result;
import com.ace.controller.api.concerns.Success;
import com.ace.entity.Room;
import com.ace.service.api.RoomService;
import com.ace.service.api.ScheduleService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 平板预定信息
 *
 * @author john
 * @date 19-6-11 下午3:23
 */
@RestController
@RequestMapping("/api/display/")
public class ScheduleController {
    @Resource
    private ScheduleService scheduleService;
    @Resource
    private RoomService roomService;

    /**
     * 预定信息
     * <br/>
     * room - 会议室编号
     * <br/>
     * date - 日期
     */
    @GetMapping("{room}/schedule")
    @ResponseBody
    public Result index(@PathVariable("room") Long roomId, @RequestParam("date") Date date) {
        Room room = roomService.show(roomId);
        Map<String, Object> data = new HashMap<>();
        data.put("name", room.getName());
        data.put("schedule", scheduleService.scheduleList(roomId, date));
        return new Success(data);
    }
}
