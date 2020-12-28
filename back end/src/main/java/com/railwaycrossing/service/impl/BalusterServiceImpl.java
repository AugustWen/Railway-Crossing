package com.railwaycrossing.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.railwaycrossing.constants.Constants;
import com.railwaycrossing.dao.BalusterDao;
import com.railwaycrossing.entity.Baluster;
import com.railwaycrossing.exception.UpdateException;
import com.railwaycrossing.service.BalusterService;
import com.railwaycrossing.service.RailwayBlinkerService;
import com.railwaycrossing.service.RoadBlinkerService;
import com.railwaycrossing.service.WarningService;
import com.railwaycrossing.utils.JSONUtil;
import com.railwaycrossing.utils.PageUtils;
import com.railwaycrossing.utils.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

import static java.util.concurrent.TimeUnit.SECONDS;

@Service("balusterService")
public class BalusterServiceImpl extends ServiceImpl<BalusterDao, Baluster> implements BalusterService {

    @Resource
    RailwayBlinkerService railwayBlinkerService;

    @Resource
    RoadBlinkerService roadBlinkerService;

    @Override
    public PageUtils queryPage(JSONObject message) {
        QueryWrapper<Baluster> queryWrapper = new QueryWrapper<>();

        IPage<Baluster> page = this.page(
                new Query<Baluster>().getPage(message),
                queryWrapper
        );
        return new PageUtils(page);
    }

    @Transactional(rollbackFor=UpdateException.class)
    @Override
    public JSONObject updateStatusByBalusterId(JSONObject message) throws UpdateException {
        Integer balusterId = message.getInteger("balusterId");
        Integer balusterStatus = message.getInteger("balusterStatus");
        if (balusterStatus < 0 || balusterStatus > 3) {//状态不对
            throw new UpdateException();
        }
        Integer result = baseMapper.updateStatusByBalusterId(balusterId, balusterStatus);
        if (result == 1) {// 修改成功！
            return JSONUtil.successJSON(Constants.UPDATE_SUCCESS);
        } else {
            throw new UpdateException();
        }
    }

    @Override
    public JSONObject updateStatusByCrossingId(JSONObject message) throws UpdateException {
        Integer crossingId = message.getInteger("crossingId");
        Integer balusterStatus = message.getInteger("balusterStatus");
        if (balusterStatus < 0 || balusterStatus > 3) {//状态不对
            throw new UpdateException();
        }
        //根据道口状态修改栏木机状态
        Integer result = baseMapper.updateStatusByCrossingId(crossingId, balusterStatus);
        //获取数目
        Integer count = baseMapper.selectCount(new QueryWrapper<Baluster>().eq("crossingId",crossingId));
        if (!result.equals(count)) {//若没有全部修改成功
            throw new UpdateException();
        } else {    // 修改成功！
            return JSONUtil.successJSON(Constants.UPDATE_SUCCESS);
        }
    }

    @Transactional(rollbackFor=UpdateException.class)
    @Override
    public void updateStatusByCrossingMode(Integer crossingId, Boolean crossingMode) throws UpdateException {
        Boolean flag = crossingMode;
        int count = baseMapper.selectCount(new QueryWrapper<Baluster>().eq("crossingId",crossingId));
        changeStatusByCrossingMode(crossingId, count);
        int result = 0;
        if (crossingMode) {//道口要关闭
            //则放下栏木机，联动调节信号灯
            result = baseMapper.updateStatusByCrossingId(crossingId, Constants.CLOSE);
            railwayBlinkerService.updateStatusByBaluster(crossingId, Constants.CLOSE);
            roadBlinkerService.updateStatusByBaluster(crossingId, Constants.CLOSE);
        } else {//开启
            //则升起栏木机，联动调节信号灯
            result = baseMapper.updateStatusByCrossingId(crossingId, Constants.OPEN);
            railwayBlinkerService.updateStatusByBaluster(crossingId, Constants.OPEN);
            roadBlinkerService.updateStatusByBaluster(crossingId, Constants.OPEN);
        }
        if (result != count) {

            throw new UpdateException();
        }
    }

    public void changeStatusByCrossingMode(Integer crossingId, Integer count) throws UpdateException {
        int result = baseMapper.updateStatusByCrossingId(crossingId, Constants.CHANGING);
        if (result != count) {
            throw new UpdateException();
        }
        //模拟关闭时间---10s
        //try { SECONDS.sleep(10);} catch (InterruptedException e) {e.printStackTrace();}
    }

    @Override
    public PageUtils queryPageByCondition(JSONObject message) {
        QueryWrapper<Baluster> wrapper = new QueryWrapper<>();
        if (message.containsKey("balusterName") && !StringUtils.isEmpty(message.getString("balusterName"))) {
            wrapper.and(w->{
               w.like("balusterName", message.getString("balusterName"));
            });
        }
        if (message.containsKey("balusterStatus") && !StringUtils.isEmpty(message.getInteger("balusterStatus"))) {
            wrapper.and(w->{
                w.eq("balusterStatus", message.getInteger("balusterStatus"));
            });
        }
        if (message.containsKey("crossingId") && !StringUtils.isEmpty(message.getInteger("crossingId"))) {
            wrapper.and(w->{
                w.eq("crossingId", message.getInteger("crossingId"));
            });
        }
        IPage<Baluster> page = this.page(
                new Query<Baluster>().getPage(message),
                wrapper
        );
        return new PageUtils(page);
    }

}
