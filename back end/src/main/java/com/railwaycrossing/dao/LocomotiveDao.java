package com.railwaycrossing.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.railwaycrossing.entity.Instruction;
import com.railwaycrossing.entity.Locomotive;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LocomotiveDao extends BaseMapper<Locomotive> {

}
