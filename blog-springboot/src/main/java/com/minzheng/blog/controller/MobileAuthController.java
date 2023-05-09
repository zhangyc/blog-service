package com.minzheng.blog.controller;

import com.minzheng.blog.service.UserAuthService;
import com.minzheng.blog.vo.Result;
import com.minzheng.blog.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(tags = "移动用户账号模块")
@RestController
public class MobileAuthController {
    @Autowired
    private UserAuthService userAuthService;
    @ApiOperation(value = "用户注册")
    @PostMapping("/mobile/register")
    public Result<?> register(@Valid @RequestBody UserVO user) {
        userAuthService.register(user);
        return Result.ok();
    }
}
