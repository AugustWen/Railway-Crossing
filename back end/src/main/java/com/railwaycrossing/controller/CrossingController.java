package com.railwaycrossing.controller;

import com.alibaba.fastjson.JSONObject;
import com.railwaycrossing.exception.UpdateException;
import com.railwaycrossing.service.CrossingService;
import com.railwaycrossing.utils.JSONUtil;
import com.railwaycrossing.utils.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 道口
 */
@CrossOrigin
@RestController
@RequestMapping("/crossing")
@Slf4j
public class CrossingController {

    @Resource
    CrossingService crossingService;

    @PostMapping("/listAll")
    public JSONObject listAll(@RequestBody JSONObject message) {
        PageUtils pageUtils = crossingService.queryPage(message);
        return JSONUtil.successJSON(pageUtils);
    }

    @PostMapping("/updateStatus")
    public JSONObject updateStatus(@RequestBody JSONObject message) throws UpdateException {
        return crossingService.updateCrossingModeById(message);
    }
}

