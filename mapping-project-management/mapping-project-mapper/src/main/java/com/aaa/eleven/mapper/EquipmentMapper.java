package com.aaa.eleven.mapper;

import com.aaa.eleven.model.Equipment;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface EquipmentMapper extends Mapper<Equipment> {
    /***
     * @Author ftt
     * @Description
     * 根据id查询具体信息
     * @Date 2020/7/16 11:39
     * @Param [id]
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    List<Map<String,Object>> selectEquipmentdetail(@Param("id") Long id);
}