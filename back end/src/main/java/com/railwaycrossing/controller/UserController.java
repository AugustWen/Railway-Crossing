package com.railwaycrossing.controller;

import com.alibaba.fastjson.JSONObject;
import com.railwaycrossing.exception.UpdateException;
import com.railwaycrossing.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/user")
@Slf4j
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping(value = "/updatePassword")
    public JSONObject updatePassword(@RequestBody JSONObject message) {
        return userService.updatePassword(message);
    }

    @PostMapping(value = "/updateUser")
    public JSONObject updateUser(@RequestBody JSONObject message) throws UpdateException {
        return userService.updateUser(message);
    }

    @PostMapping(value = "/login")
    public JSONObject login(@RequestBody JSONObject message) {
        return userService.login(message);
    }

    @PostMapping(value = "/logout")
    public JSONObject logout(@RequestBody JSONObject message) {
        return userService.logout(message);
    }

    @PostMapping(value = "/register")
    public JSONObject register(@RequestBody JSONObject message) {
        return userService.register(message);
    }

}
