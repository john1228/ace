package com.ace.controller.api;

import com.ace.annotation.Authorization;
import com.ace.controller.api.concerns.Result;
import com.ace.controller.api.concerns.Success;
import com.ace.controller.api.concerns.View;
import com.ace.controller.api.concerns.View.Detail;
import com.ace.entity.Staff;
import com.ace.entity.room.Room;
import com.ace.service.TokenService;
import com.ace.service.room.RoomService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController("api_staff")
@RequestMapping("/api")
public class StaffController {

    @Resource
    TokenService tokenService;

    @GetMapping({"", "/"})
    @JsonView(View.Base.class)
    public Result login(Staff staff) {
        String authToken = tokenService.createToken(staff);
        return new Success(authToken);

    }

    @DeleteMapping({"", "/"})
    @JsonView(Detail.class)
    @Authorization
    public Result detail(@RequestHeader("authorize") String authToken) {
        tokenService.deleteToken(authToken);
        return new Success(null);
    }
}
