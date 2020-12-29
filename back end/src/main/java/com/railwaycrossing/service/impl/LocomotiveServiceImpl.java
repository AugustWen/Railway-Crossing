package com.railwaycrossing.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.railwaycrossing.dao.LocomotiveDao;
import com.railwaycrossing.entity.Locomotive;
import com.railwaycrossing.exception.DeleteException;
import com.railwaycrossing.exception.InsertException;
import com.railwaycrossing.exception.UpdateException;
import com.railwaycrossing.service.LocomotiveService;
import com.railwaycrossing.utils.JSONUtil;
import com.railwaycrossing.utils.PageUtils;
import com.railwaycrossing.utils.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service("locomotiveService")
public class LocomotiveServiceImpl extends ServiceImpl<LocomotiveDao, Locomotive> implements LocomotiveService {

    @Override
    public JSONObject insert(JSONObject message) throws InsertException {
        Locomotive locomotive = new Locomotive();
        //各种属性
        locomotive.setLocomotiveName(message.getString("locomotiveId"));
        locomotive.setLocomotiveGPS(message.getString("locomotiveGPS"));
        locomotive.setTrackId(message.getString("trackId"));
        locomotive.setResponseStatus(message.getBoolean("responseStatus"));
        int result = baseMapper.insert(locomotive);

        if (result != 1) {
            throw new InsertException();
        } else {
            return JSONUtil.successJSON();
        }
    }

    @Override
    public JSONObject delete(JSONObject message) throws DeleteException {
        Integer locomotiveId = message.getInteger("locomotiveId");
        int result = baseMapper.deleteById(locomotiveId);

        if (result != 1) {
            throw new DeleteException();
        } else {
            return JSONUtil.successJSON();
        }
    }

    @Override
    public JSONObject deleteBatch(JSONObject message) throws DeleteException {
        JSONArray array = message.getJSONArray("locomotiveId");
        List<Integer> locomotiveIds = JSONObject.parseArray(array.toJSONString(), Integer.class);

        int result = baseMapper.deleteBatchIds(locomotiveIds);

        if (result != locomotiveIds.size()) {
            throw new DeleteException();
        } else {
            return JSONUtil.successJSON();
        }
    }

    @Override
    public JSONObject updateById(JSONObject message) throws UpdateException {
        Locomotive locomotive = new Locomotive();
        //各种属性
        locomotive.setLocomotiveId(message.getInteger("locomotiveId"));
        locomotive.setLocomotiveName(message.getString("locomotiveId"));
        locomotive.setLocomotiveGPS(message.getString("locomotiveGPS"));
        locomotive.setTrackId(message.getString("trackId"));
        locomotive.setResponseStatus(message.getBoolean("responseStatus"));
        int result = baseMapper.update(locomotive, new UpdateWrapper<Locomotive>().eq("locomotiveId",locomotive.getLocomotiveId()));
        if (result != 1) {
            throw new UpdateException();
        } else {
            return JSONUtil.successJSON();
        }
    }

    @Override
    public PageUtils queryPage(JSONObject message) {
        QueryWrapper<Locomotive> queryWrapper = new QueryWrapper<>();

        IPage<Locomotive> page = this.page(
                new Query<Locomotive>().getPage(message),
                queryWrapper
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPageByCondition(JSONObject message) {
        //1.获取key
        String locomotiveName = message.getString("locomotiveName");
        Integer locomotiveId = message.getInteger("locomotiveId");
        String trackId = message.getString("trackId");
        Boolean responseStatus = message.getBoolean("responseStatus");
        QueryWrapper<Locomotive> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(locomotiveName)) {
            queryWrapper.like("locomotiveName", locomotiveName);
        }
        if (!StringUtils.isEmpty(locomotiveId)) {
            queryWrapper.or().eq("locomotiveId", locomotiveId);
        }
        if (!StringUtils.isEmpty(trackId)) {
            queryWrapper.or().eq("trackId", trackId);
        }
        if (!StringUtils.isEmpty(responseStatus)) {
            queryWrapper.or().eq("responseStatus", responseStatus);
        }
        IPage<Locomotive> page = this.page(
                new Query<Locomotive>().getPage(message),
                queryWrapper
        );
        return new PageUtils(page);
    }

}
