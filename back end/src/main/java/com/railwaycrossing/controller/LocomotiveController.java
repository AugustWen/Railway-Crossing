package com.railwaycrossing.controller;

import com.alibaba.fastjson.JSONObject;
import com.railwaycrossing.exception.DeleteException;
import com.railwaycrossing.exception.InsertException;
import com.railwaycrossing.exception.UpdateException;
import com.railwaycrossing.service.LocomotiveService;
import com.railwaycrossing.utils.JSONUtil;
import com.railwaycrossing.utils.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 机车
 */
@CrossOrigin
@RestController
@RequestMapping("/locomotive")
@Slf4j
public class LocomotiveController {

    @Resource
    LocomotiveService locomotiveService;

    @PostMapping("/listAll")
    public JSONObject listAll(@RequestBody JSONObject message) {
        PageUtils pageUtils = locomotiveService.queryPage(message);
        return JSONUtil.successJSON(pageUtils);
    }

    @PostMapping("/listByCondition")
    public JSONObject listByCondition(@RequestBody JSONObject message) {
        PageUtils pageUtils = locomotiveService.queryPageByCondition(message);
        return JSONUtil.successJSON(pageUtils);
    }

    @PostMapping("/insert")
    public JSONObject insert(@RequestBody JSONObject message) throws InsertException {
        return locomotiveService.insert(message);
    }

    @PostMapping("/delete")
    public JSONObject delete(@RequestBody Integer locomotiveId) throws DeleteException {
        return locomotiveService.delete(locomotiveId);
    }

    @PostMapping("/deleteBatch")
    public JSONObject deleteBatch(@RequestBody List<Integer> locomotiveIds) throws DeleteException {
        return locomotiveService.deleteBatch(locomotiveIds);
    }

    @PostMapping("/updateById")
    public JSONObject updateById(@RequestBody JSONObject message) throws UpdateException {
        return locomotiveService.updateById(message);
    }
}
