package com.aaa.eleven.mapper;

import com.aaa.eleven.model.Dict;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface DictMapper extends Mapper<Dict> {
    /**
     * 批量删除字典信息
     * @return
     */
    public Integer deleteBachById(List<Integer> ids);
    public List<Dict> selectAllDict(@Param("tableName") String tableName,@Param("fieldName") String fieldName,@Param("keyy") String keyy);
}