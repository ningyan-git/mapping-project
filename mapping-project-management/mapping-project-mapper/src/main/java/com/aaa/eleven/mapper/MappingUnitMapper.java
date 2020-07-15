package com.aaa.eleven.mapper;

import com.aaa.eleven.model.MappingUnit;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface MappingUnitMapper extends Mapper<MappingUnit> {
    List<Map<String,Object>> selectByConditions(@Param("unitName") String unitName);
}