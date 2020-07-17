package com.aaa.eleven.mapper;

import com.aaa.eleven.model.Dept;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface DeptMapper extends Mapper<Dept> {
    public List<Map<String, Object>> selectAllDept(@Param("deptName") String deptName);
}