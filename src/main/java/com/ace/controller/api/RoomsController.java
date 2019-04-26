package com.ace.controller.api;

import com.ace.annotation.Authorization;
import com.ace.controller.api.concerns.Result;
import com.ace.controller.api.concerns.Success;
import com.ace.controller.api.concerns.View;
import com.ace.controller.api.concerns.View.Detail;
import com.ace.entity.Staff;
import com.ace.entity.room.Room;
import com.ace.service.room.RoomService;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController("api_room")
@RequestMapping("/api/rooms")
public class RoomsController {

    @Resource
    RoomService roomService;

    @JsonView(View.Base.class)
    @GetMapping({"", "/"})
    @Authorization
    public Result list(@RequestAttribute("STAFF") Staff staff) {
        return new Success(roomService.roomList(staff));
    }

    @JsonView(View.Detail.class)
    @GetMapping({"/{id}", "/{id}/"})
    @Authorization
    public Result detail(@PathVariable("id") int id) {
        return new Success("abc");
    }
}
