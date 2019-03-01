package com.ace.controller.api;

import com.ace.controller.api.concerns.Result;
import com.ace.controller.api.concerns.Success;
import com.ace.controller.api.concerns.View;
import com.ace.entity.room.Room;
import com.ace.service.room.RoomService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController("apiRoom")
@RequestMapping("/api/rooms")
public class RoomsController {

    @Resource
    RoomService roomService;


    @GetMapping({"", "/"})
    @JsonView(View.Base.class)
    public Result list() {
        Room room = new Room();
        room.setId(1);
        room.setName("a");
        List<Room> roomList = new ArrayList<>();
        roomList.add(room);
        return new Success(roomList);
    }
}
