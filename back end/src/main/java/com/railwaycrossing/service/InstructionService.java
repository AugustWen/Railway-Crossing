package com.railwaycrossing.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.railwaycrossing.entity.Instruction;
import com.railwaycrossing.exception.DeleteException;
import com.railwaycrossing.exception.InsertException;
import com.railwaycrossing.exception.UpdateException;
import com.railwaycrossing.utils.PageUtils;

import java.util.List;


public interface InstructionService extends IService<Instruction> {

    PageUtils queryPage(JSONObject message);

    PageUtils queryPageByCondition(JSONObject message);

    JSONObject insert(JSONObject message) throws InsertException;

    JSONObject delete(Integer instructionId) throws DeleteException;

    JSONObject deleteBatch(List<Integer> instructionIds) throws DeleteException;

    JSONObject updateById(JSONObject message) throws UpdateException;

}
