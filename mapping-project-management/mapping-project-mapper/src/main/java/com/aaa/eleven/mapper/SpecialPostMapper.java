package com.aaa.eleven.mapper;

import com.aaa.eleven.model.SpecialPost;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface SpecialPostMapper extends Mapper<SpecialPost> {
    List<Map<String,Object>> selectSpecialPostDetail(@Param("id") Long id);
}