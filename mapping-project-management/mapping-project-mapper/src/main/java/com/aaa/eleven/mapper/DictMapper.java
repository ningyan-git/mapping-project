package com.aaa.eleven.mapper;

import com.aaa.eleven.model.Dict;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface DictMapper extends Mapper<Dict> {
    public List<Dict> selectAllDict(@Param("tableName") String tableName,@Param("fieldName") String fieldName,@Param("keyy") String keyy);
}