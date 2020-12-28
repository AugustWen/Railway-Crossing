package com.railwaycrossing.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.railwaycrossing.entity.Baluster;
import com.railwaycrossing.exception.UpdateException;
import com.railwaycrossing.utils.PageUtils;


public interface BalusterService extends IService<Baluster> {

    PageUtils queryPage(JSONObject message);

    JSONObject updateStatusByBalusterId(JSONObject message) throws UpdateException;

    JSONObject updateStatusByCrossingId(JSONObject message) throws UpdateException;

    void updateStatusByCrossingMode(Integer crossingId, Boolean crossingMode) throws UpdateException;

    PageUtils queryPageByCondition(JSONObject message);
}
