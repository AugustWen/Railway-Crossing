package com.railwaycrossing.controller;

import com.alibaba.fastjson.JSONObject;
import com.railwaycrossing.exception.UpdateException;
import com.railwaycrossing.service.RoadBlinkerService;
import com.railwaycrossing.utils.JSONUtil;
import com.railwaycrossing.utils.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 公路信号灯
 */
@CrossOrigin
@RestController
@RequestMapping("/roadBlinker")
@Slf4j
public class RoadBlinkerController {

    @Resource
    RoadBlinkerService roadBlinkerService;

    @PostMapping("/listAll")
    public JSONObject listAll(@RequestBody JSONObject message) {
        PageUtils pageUtils = roadBlinkerService.queryPage(message);
        return JSONUtil.successJSON(pageUtils);
    }

    @PostMapping("/listByCondition")
    public JSONObject listByCondition(@RequestBody JSONObject message) {
        PageUtils pageUtils = roadBlinkerService.queryPageByCondition(message);
        return JSONUtil.successJSON(pageUtils);
    }

}
