package com.railwaycrossing.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.railwaycrossing.entity.Crossing;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CrossingDao extends BaseMapper<Crossing> {

}
