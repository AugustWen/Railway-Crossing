package com.railwaycrossing.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.railwaycrossing.entity.User;
import com.railwaycrossing.exception.UpdateException;

public interface UserService extends IService<User> {

    JSONObject login(JSONObject message);

    JSONObject logout(JSONObject message);

    JSONObject notLogin();

    JSONObject updatePassword(JSONObject message);

    JSONObject unauthorized(String message);

    JSONObject register(JSONObject message);

    JSONObject updateUser(JSONObject message) throws UpdateException;

    void updateLoginTime(String userAccount);

    void updateLoginLength(String userAccount);

}
