package com.railwaycrossing.controller;

import com.alibaba.fastjson.JSONObject;
import com.railwaycrossing.exception.DeleteException;
import com.railwaycrossing.exception.InsertException;
import com.railwaycrossing.service.WarningService;
import com.railwaycrossing.utils.JSONUtil;
import com.railwaycrossing.utils.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 警告
 */
@CrossOrigin
@RestController
@RequestMapping("/warning")
@Slf4j
public class WarningController {
    @Resource
    WarningService warningService;

    @PostMapping("/listAll")
    JSONObject listAll(JSONObject message) {
        PageUtils pageUtils = warningService.queryPage(message);
        return JSONUtil.successJSON(pageUtils);
    }

    @PostMapping("/listByCondition")
    public JSONObject listByCondition(JSONObject message) {
        PageUtils pageUtils = warningService.queryPageByCondition(message);
        return JSONUtil.successJSON(pageUtils);
    }

    @PostMapping("/deleteById")
    public JSONObject deleteById(JSONObject message) throws DeleteException {
        return warningService.deleteById(message);
    }

    @PostMapping("/insert")
    public JSONObject insert(JSONObject message) throws InsertException {
        return warningService.insert(message);
    }
}
