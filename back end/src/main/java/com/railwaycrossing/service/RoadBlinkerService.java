package com.railwaycrossing.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.railwaycrossing.entity.Roadblinker;
import com.railwaycrossing.exception.UpdateException;
import com.railwaycrossing.utils.PageUtils;

public interface RoadBlinkerService extends IService<Roadblinker> {

    PageUtils queryPage(JSONObject message);

    PageUtils queryPageByCondition(JSONObject message);

    void updateStatusByBaluster(Integer crossingId, Integer balusterStatus) throws UpdateException;

}
