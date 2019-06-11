package com.ace.controller.api.display;

import com.ace.controller.api.concerns.Result;
import com.ace.controller.api.concerns.Success;
import com.ace.service.api.ScheduleService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Date;

/**
 * @author john
 * @date 19-6-11 下午3:23
 */
@Api(tags = "平板-预约列表")
@RestController
@RequestMapping("/api/display/")
public class ScheduleController {
    @Resource
    private ScheduleService scheduleService;

    @GetMapping("{room}/schedule")
    @ResponseBody
    public Result index(@PathVariable("room") Long room, @RequestParam("date") Date date) {
        return new Success(scheduleService.scheduleList(room, date));
    }
}
