package com.railwaycrossing.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.railwaycrossing.entity.Crossing;
import com.railwaycrossing.exception.UpdateException;
import com.railwaycrossing.utils.PageUtils;


public interface CrossingService extends IService<Crossing> {
    PageUtils queryPage(JSONObject message);

    JSONObject updateCrossingModeById(JSONObject message) throws UpdateException;
}
