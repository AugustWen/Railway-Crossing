package com.railwaycrossing.controller;

import com.alibaba.fastjson.JSONObject;
import com.railwaycrossing.exception.DeleteException;
import com.railwaycrossing.exception.InsertException;
import com.railwaycrossing.exception.UpdateException;
import com.railwaycrossing.service.InstructionService;
import com.railwaycrossing.utils.JSONUtil;
import com.railwaycrossing.utils.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 指令
 */
@CrossOrigin
@RestController
@RequestMapping("/instruction")
@Slf4j
public class InstructionController {

    @Resource
    InstructionService instructionService;

    @PostMapping("/listAll")
    public JSONObject listAll(@RequestBody JSONObject message) {
        PageUtils pageUtils = instructionService.queryPage(message);
        return JSONUtil.successJSON(message);
    }

    @PostMapping("/listByCondition")
    public JSONObject listByCondition(@RequestBody JSONObject message) {
        PageUtils pageUtils = instructionService.queryPageByCondition(message);
        return JSONUtil.successJSON(pageUtils);
    }

    @PostMapping("/insert")
    public JSONObject insert(@RequestBody JSONObject message) throws InsertException {
        return instructionService.insert(message);
    }

    @PostMapping("/delete")
    public JSONObject delete(@RequestBody Integer instructionId) throws DeleteException {
        return instructionService.delete(instructionId);
    }

    @PostMapping("/deleteBatch")
    public JSONObject deleteBatch(@RequestBody List<Integer> instructionIds) throws DeleteException {
        return instructionService.deleteBatch(instructionIds);
    }

    @PostMapping("/updateById")
    public JSONObject updateById(@RequestBody JSONObject message) throws UpdateException {
        return instructionService.updateById(message);
    }
}
