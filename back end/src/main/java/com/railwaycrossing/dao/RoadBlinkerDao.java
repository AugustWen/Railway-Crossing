package com.railwaycrossing.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.railwaycrossing.entity.Roadblinker;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoadBlinkerDao extends BaseMapper<Roadblinker> {

    int updateStatusByBaluster(Integer crossingId, Integer status);
}
