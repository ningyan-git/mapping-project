package com.aaa.eleven.mapper;

import com.aaa.eleven.model.Technicist;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface TechnicistMapper extends Mapper<Technicist> {
    List<Map<String,Object>> selectTechnicistDetail(@Param("id") Long id);
}