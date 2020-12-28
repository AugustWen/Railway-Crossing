package com.railwaycrossing.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.railwaycrossing.entity.Baluster;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BalusterDao extends BaseMapper<Baluster> {

    Integer updateStatusByBalusterId(Integer balusterId, Integer balusterStatus);

    Integer updateStatusByCrossingId(Integer crossingId, Integer balusterStatus);

}
