package com.railwaycrossing.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.railwaycrossing.entity.Warning;
import com.railwaycrossing.exception.DeleteException;
import com.railwaycrossing.exception.InsertException;
import com.railwaycrossing.utils.PageUtils;

public interface WarningService extends IService<Warning> {
    PageUtils queryPage(JSONObject message);
    PageUtils queryPageByCondition(JSONObject message);
    JSONObject deleteById(JSONObject message) throws DeleteException;
    JSONObject insert(JSONObject message) throws InsertException;
}
