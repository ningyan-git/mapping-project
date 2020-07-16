package com.aaa.eleven.mapper;

import com.aaa.eleven.model.Principal;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface PrincipalMapper extends Mapper<Principal> {
    /***
     * @Author ftt
     * @Description
     * 查询Principal 和resource表的详细信息
     * @Date 2020/7/16 11:17
     * @Param [id]
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    List<Map<String,Object>> selectPrincipalDetail(@Param("id") Long id);
}