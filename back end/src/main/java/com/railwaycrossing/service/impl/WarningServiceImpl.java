package com.railwaycrossing.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.railwaycrossing.constants.Constants;
import com.railwaycrossing.dao.WarningDao;
import com.railwaycrossing.entity.Warning;
import com.railwaycrossing.exception.DeleteException;
import com.railwaycrossing.exception.InsertException;
import com.railwaycrossing.service.WarningService;
import com.railwaycrossing.utils.JSONUtil;
import com.railwaycrossing.utils.PageUtils;
import com.railwaycrossing.utils.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service("warningService")
public class WarningServiceImpl extends ServiceImpl<WarningDao, Warning> implements WarningService {
    @Override
    public PageUtils queryPage(JSONObject message) {
        QueryWrapper<Warning> queryWrapper = new QueryWrapper<>();

        IPage<Warning> page = this.page(
                new Query<Warning>().getPage(message),
                queryWrapper
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPageByCondition(JSONObject message) {
        //1.获取key
        Integer crossingId = message.getInteger("crossingId");
        QueryWrapper<Warning> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(crossingId)) {
            queryWrapper.or().eq("crossingId", crossingId);
        }
        IPage<Warning> page = this.page(
                new Query<Warning>().getPage(message),
                queryWrapper
        );
        return new PageUtils(page);
    }

    @Override
    public JSONObject deleteById(JSONObject message) throws DeleteException {
        Integer warningId = message.getInteger("warningId");
        int result = baseMapper.deleteWarningById(warningId);
        if (result == 1) {
            return JSONUtil.successJSON(Constants.DELETE_SUCCESS);
        } else {
            throw new DeleteException();
        }
    }

    @Override
    public JSONObject insert(JSONObject message) throws InsertException {
        Warning warning = new Warning();
        warning.setWarningContent(message.getString("warningContent"));
        warning.setCrossingId(message.getInteger("crossingId"));
        int result = baseMapper.insert(warning);
        if (result == 1) {
            return JSONUtil.successJSON(Constants.INSERT_SUCCESS);
        } else {
            throw new InsertException();
        }
    }
}
