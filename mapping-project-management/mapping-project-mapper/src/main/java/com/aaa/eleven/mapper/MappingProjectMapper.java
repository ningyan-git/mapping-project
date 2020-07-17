package com.aaa.eleven.mapper;

import com.aaa.eleven.model.MappingProject;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface MappingProjectMapper extends Mapper<MappingProject> {
    public List<MappingProject> selectAllMappingProject(@Param("projectName") String projectName);
}