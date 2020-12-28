package com.railwaycrossing.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.railwaycrossing.constants.Constants;
import com.railwaycrossing.dao.RoadBlinkerDao;
import com.railwaycrossing.entity.Roadblinker;
import com.railwaycrossing.exception.UpdateException;
import com.railwaycrossing.service.RoadBlinkerService;
import com.railwaycrossing.utils.PageUtils;
import com.railwaycrossing.utils.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service("roadBlinkerService")
public class RoadBlinkerServiceImpl extends ServiceImpl<RoadBlinkerDao, Roadblinker> implements RoadBlinkerService {
    @Override
    public PageUtils queryPage(JSONObject message) {
        QueryWrapper<Roadblinker> queryWrapper = new QueryWrapper<>();

        IPage<Roadblinker> page = this.page(
                new Query<Roadblinker>().getPage(message),
                queryWrapper
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPageByCondition(JSONObject message) {
        //1.获取key
        String roadBlinkerName = message.getString("roadBlinkerName");
        Integer roadBlinkerId = message.getInteger("roadBlinkerId");
        QueryWrapper<Roadblinker> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(roadBlinkerName)) {
            queryWrapper.like("roadBlinkerName", roadBlinkerName);
        }
        if (!StringUtils.isEmpty(roadBlinkerId)) {
            queryWrapper.or().eq("roadBlinkerId", roadBlinkerId);
        }
        IPage<Roadblinker> page = this.page(
                new Query<Roadblinker>().getPage(message),
                queryWrapper
        );
        return new PageUtils(page);
    }

    /**
     * 根据栏目机状态修改信号灯状态
     * @param crossingId
     * @param balusterStatus
     * @throws UpdateException
     */
    @Override
    public void updateStatusByBaluster(Integer crossingId, Integer balusterStatus) throws UpdateException {
        int count = baseMapper.selectCount(new QueryWrapper<Roadblinker>().eq("crossingId",crossingId));
        int result = 0;
        if (balusterStatus.equals(Constants.OPEN)) {
            result = baseMapper.updateStatusByBaluster(crossingId, Constants.CLOSE);
        } else {
            result = baseMapper.updateStatusByBaluster(crossingId, Constants.OPEN);
        }
        if (result != count) {
            throw new UpdateException();
        }
    }
}
