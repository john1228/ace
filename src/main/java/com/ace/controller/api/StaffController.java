package com.ace.controller.api;

import com.ace.annotation.Authorization;
import com.ace.controller.api.concerns.Result;
import com.ace.controller.api.concerns.Success;
import com.ace.controller.api.concerns.ApiView;
import com.ace.controller.api.concerns.ApiView.Detail;
import com.ace.entity.Staff;
import com.ace.service.TokenService;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "用户登录")
@RestController("api_staff")
@RequestMapping("/api")
public class StaffController {

    @Resource
    TokenService tokenService;

    @GetMapping({"", "/"})
    @JsonView(ApiView.Base.class)
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
