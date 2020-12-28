package com.railwaycrossing.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.railwaycrossing.entity.Warning;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WarningDao extends BaseMapper<Warning> {
    int deleteWarningById(Integer warningId);
}
