package com.railwaycrossing.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.railwaycrossing.constants.Constants;
import com.railwaycrossing.dao.InstructionDao;
import com.railwaycrossing.entity.Instruction;
import com.railwaycrossing.exception.DeleteException;
import com.railwaycrossing.exception.InsertException;
import com.railwaycrossing.exception.UpdateException;
import com.railwaycrossing.service.InstructionService;
import com.railwaycrossing.utils.JSONUtil;
import com.railwaycrossing.utils.PageUtils;
import com.railwaycrossing.utils.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service("instructionService")
public class InstructionServiceImpl extends ServiceImpl<InstructionDao, Instruction> implements InstructionService {

    @Override
    public PageUtils queryPage(JSONObject message) {
        QueryWrapper<Instruction> queryWrapper = new QueryWrapper<>();

        IPage<Instruction> page = this.page(
                new Query<Instruction>().getPage(message),
                queryWrapper
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPageByCondition(JSONObject message) {
        QueryWrapper<Instruction> wrapper = new QueryWrapper<>();
        //各种属性
        if (message.containsKey("userId") && !StringUtils.isEmpty(message.getInteger("userId"))) {
            wrapper.and(w->{
                w.eq("userId", message.getInteger("userId"));
            });
        }
        if (message.containsKey("crossingId") && !StringUtils.isEmpty(message.getInteger("crossingId"))) {
            wrapper.and(w->{
                w.eq("crossingId", message.getInteger("crossingId"));
            });
        }
        if (message.containsKey("locomotiveId") && !StringUtils.isEmpty(message.getInteger("locomotiveId"))) {
            wrapper.and(w->{
                w.eq("locomotiveId", message.getInteger("locomotiveId"));
            });
        }
        if (message.containsKey("instucrtionContent") && !StringUtils.isEmpty(message.getString("instucrtionContent"))) {
            wrapper.and(w->{
                w.like("instucrtionContent", message.getString("instucrtionContent"));
            });
        }
        if (message.containsKey("instructionTime") && !StringUtils.isEmpty(message.getDate("instructionTime"))) {
            wrapper.and(w->{
                w.eq("instructionTime", message.getDate("instructionTime"));
            });
        }
        if (message.containsKey("passTime") && !StringUtils.isEmpty(message.getInteger("passTime"))) {
            wrapper.and(w->{
                w.eq("passTime", message.getInteger("passTime"));
            });
        }
        if (message.containsKey("valid") && !StringUtils.isEmpty(message.getBoolean("valid"))) {
            wrapper.and(w->{
                w.eq("valid", message.getBoolean("valid"));
            });
        }
        IPage<Instruction> page = this.page(
                new Query<Instruction>().getPage(message),
                wrapper
        );
        return new PageUtils(page);
    }

    @Transactional(rollbackFor=InsertException.class)
    @Override
    public JSONObject insert(JSONObject message) throws InsertException {
        Instruction instruction = new Instruction();
        //各种属性
        instruction.setCrossingId(message.getInteger("userId"));
        instruction.setCrossingId(message.getInteger("crossingId"));
        instruction.setLocomotiveId(message.getString("locomotiveId"));
        instruction.setInstructionContent(message.getString("instruction"));
        instruction.setInstructionTime(message.getDate("instructionTime"));
        instruction.setPassTime(message.getInteger("passTime"));
        instruction.setValid(message.getBoolean("valid"));
        int result = baseMapper.insert(instruction);
        if (result == 1) {
            return JSONUtil.successJSON(Constants.INSERT_SUCCESS);
        } else {
            throw new InsertException();
        }
    }

    @Override
    public JSONObject delete(Integer instructionId) throws DeleteException {
        int result = baseMapper.deleteById(instructionId);
        if (result != 1) {
            return JSONUtil.successJSON(Constants.DELETE_SUCCESS);
        } else {
            throw new DeleteException();
        }
    }

    @Override
    public JSONObject deleteBatch(List<Integer> instructionId) throws DeleteException {
        int result = baseMapper.deleteBatchIds(instructionId);
        if (result == instructionId.size()) {
            return JSONUtil.successJSON(Constants.DELETE_SUCCESS);
        } else {
            throw new DeleteException();
        }
    }

    @Override
    public JSONObject updateById(JSONObject message) throws UpdateException {
        Instruction instruction = new Instruction();
        //各种属性
        instruction.setInstructionId(message.getInteger("instructionId"));
        instruction.setInstructionId(message.getInteger("userId"));
        instruction.setCrossingId(message.getInteger("crossingId"));
        instruction.setLocomotiveId(message.getString("locomotiveId"));
        instruction.setInstructionContent(message.getString("instructionContent"));
        instruction.setInstructionTime(message.getDate("instructionTime"));
        instruction.setPassTime(message.getInteger("passTime"));
        instruction.setValid(message.getBoolean("valid"));
        int result = baseMapper.update(instruction, new UpdateWrapper<Instruction>().eq("instructionId",instruction.getInstructionId()));
        if (result == 1) {
            return JSONUtil.successJSON(Constants.UPDATE_SUCCESS);
        } else {
            throw new UpdateException();
        }
    }
}
