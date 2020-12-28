package com.railwaycrossing.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.railwaycrossing.entity.Railwayblinker;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RailwayBlinkerDao extends BaseMapper<Railwayblinker> {

    int updateStatusByBaluster(Integer crossingId, Integer status);
}
