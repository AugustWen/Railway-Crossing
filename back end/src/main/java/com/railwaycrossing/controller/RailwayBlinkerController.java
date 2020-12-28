package com.railwaycrossing.controller;

import com.alibaba.fastjson.JSONObject;
import com.railwaycrossing.exception.UpdateException;
import com.railwaycrossing.service.RailwayBlinkerService;
import com.railwaycrossing.utils.JSONUtil;
import com.railwaycrossing.utils.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 铁路信号灯
 */
@CrossOrigin
@RestController
@RequestMapping("/railwayBlinker")
@Slf4j
public class RailwayBlinkerController {

    @Resource
    RailwayBlinkerService railwayBlinkerService;

    @PostMapping("/listAll")
    public JSONObject listAll(@RequestBody JSONObject message) {
        PageUtils pageUtils = railwayBlinkerService.queryPage(message);
        return JSONUtil.successJSON(pageUtils);
    }

    @PostMapping("/listByCondition")
    public JSONObject listByCondition(@RequestBody JSONObject message) {
        PageUtils pageUtils = railwayBlinkerService.queryPageByCondition(message);
        return JSONUtil.successJSON(pageUtils);
    }

}
