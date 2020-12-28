package com.railwaycrossing.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.railwaycrossing.constants.Constants;
import com.railwaycrossing.dao.RailwayBlinkerDao;
import com.railwaycrossing.entity.Railwayblinker;
import com.railwaycrossing.exception.UpdateException;
import com.railwaycrossing.service.RailwayBlinkerService;
import com.railwaycrossing.utils.PageUtils;
import com.railwaycrossing.utils.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


@Service("blinkerService")
public class RailwayBlinkerServiceImpl extends ServiceImpl<RailwayBlinkerDao, Railwayblinker> implements RailwayBlinkerService {
    @Override
    public PageUtils queryPage(JSONObject message) {
        QueryWrapper<Railwayblinker> queryWrapper = new QueryWrapper<>();

        IPage<Railwayblinker> page = this.page(
                new Query<Railwayblinker>().getPage(message),
                queryWrapper
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPageByCondition(JSONObject message) {
        //各种条件
        String railwayBlinkerName = message.getString("railwayBlinkerName");
        Integer railwayBlinkerId = message.getInteger("railwayBlinkerId");
        QueryWrapper<Railwayblinker> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(railwayBlinkerName)) {
            queryWrapper.like("railwayBlinkerName", railwayBlinkerName);
        }
        if (!StringUtils.isEmpty(railwayBlinkerId)) {
            queryWrapper.or().eq("railwayBlinkerId", railwayBlinkerId);
        }
        IPage<Railwayblinker> page = this.page(
                new Query<Railwayblinker>().getPage(message),
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
        int count = baseMapper.selectCount(new QueryWrapper<Railwayblinker>().eq("crossingId",crossingId));
        int result = 0;
        if (balusterStatus.equals(Constants.OPEN)) {
            result = baseMapper.updateStatusByBaluster(crossingId, Constants.OPEN);
        } else {
            result = baseMapper.updateStatusByBaluster(crossingId, Constants.CLOSE);
        }
        if (result != count) {
            throw new UpdateException();
        }
    }


}
