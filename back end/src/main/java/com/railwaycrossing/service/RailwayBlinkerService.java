package com.railwaycrossing.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.railwaycrossing.entity.Railwayblinker;
import com.railwaycrossing.exception.UpdateException;
import com.railwaycrossing.utils.PageUtils;

public interface RailwayBlinkerService extends IService<Railwayblinker> {

    PageUtils queryPage(JSONObject message);

    PageUtils queryPageByCondition(JSONObject message);

    void updateStatusByBaluster(Integer crossingId, Integer balusterStatus) throws UpdateException;

}
