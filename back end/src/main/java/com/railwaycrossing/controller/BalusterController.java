package com.railwaycrossing.controller;

import com.alibaba.fastjson.JSONObject;
import com.railwaycrossing.exception.UpdateException;
import com.railwaycrossing.service.BalusterService;
import com.railwaycrossing.utils.JSONUtil;
import com.railwaycrossing.utils.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 栏木机
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/baluster")
@Slf4j
public class BalusterController {

    @Resource
    BalusterService balusterService;

    /**
     * 分页列举全部
     * @param message
     * @return
     */
    @PostMapping("/listAll")
    public JSONObject listAll(@RequestBody JSONObject message) {
        PageUtils pageUtils = balusterService.queryPage(message);
        return JSONUtil.successJSON(pageUtils);
    }

    /**
     * 根据栏目即id更新状态
     * @param message
     * @return
     * @throws UpdateException
     */
    @PostMapping("/updateStatusByBalusterId")
    public JSONObject updateStatusByBalusterId(@RequestBody JSONObject message) throws UpdateException {
        return balusterService.updateStatusByBalusterId(message);
    }

    /**
     * 根据道口id更新状态
     * @param message
     * @return
     * @throws UpdateException
     */
    @PostMapping("/updateStatusByCrossingId")
    public JSONObject updateStatusByCrossingId(@RequestBody JSONObject message) throws UpdateException {
        return balusterService.updateStatusByCrossingId(message);
    }

    /**
     * 根据条件查询
     * @param message
     * @return
     */
    @PostMapping("/queryPageByCondition")
    public JSONObject queryPageByCondition(@RequestBody JSONObject message) {
        PageUtils pageUtils = balusterService.queryPageByCondition(message);
        return JSONUtil.successJSON(pageUtils);
    }
}
