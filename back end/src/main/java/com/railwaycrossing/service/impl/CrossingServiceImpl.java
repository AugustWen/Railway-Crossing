package com.railwaycrossing.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.railwaycrossing.constants.Constants;
import com.railwaycrossing.dao.CrossingDao;
import com.railwaycrossing.entity.Crossing;
import com.railwaycrossing.exception.UpdateException;
import com.railwaycrossing.service.BalusterService;
import com.railwaycrossing.service.CrossingService;
import com.railwaycrossing.utils.JSONUtil;
import com.railwaycrossing.utils.PageUtils;
import com.railwaycrossing.utils.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Service("crossingService")
public class CrossingServiceImpl extends ServiceImpl<CrossingDao, Crossing> implements CrossingService {

    @Resource
    BalusterService balusterService;

    @Override
    public PageUtils queryPage(JSONObject message) {

        QueryWrapper<Crossing> queryWrapper = new QueryWrapper<>();

        IPage<Crossing> page = this.page(
                new Query<Crossing>().getPage(message),
                queryWrapper
        );
        return new PageUtils(page);
    }

    @Transactional(rollbackFor=UpdateException.class)
    @Override
    public JSONObject updateCrossingModeById(JSONObject message) throws UpdateException {
        Crossing crossing = new Crossing();
        crossing.setCrossingId(message.getInteger("crossingId"));
        if (message.containsKey("crossingMode")) {
            crossing.setCrossingMode(message.getBoolean("crossingMode"));
            balusterService.updateStatusByCrossingMode(crossing.getCrossingId(), crossing.getCrossingMode());
        }
        int result = baseMapper.update(crossing, new UpdateWrapper<Crossing>().eq("crossingId",crossing.getCrossingId()));
        if (result == 1) {
            return JSONUtil.successJSON(Constants.UPDATE_SUCCESS);
        } else {    // 修改成功！
            throw new UpdateException();
        }
    }
}
