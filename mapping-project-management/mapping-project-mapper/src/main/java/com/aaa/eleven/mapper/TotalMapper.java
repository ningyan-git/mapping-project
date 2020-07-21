package com.aaa.eleven.mapper;

import com.aaa.eleven.model.MappingUnit;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @Company
 * @Author ljy
 * @Date Create in 2020/7/17 15:49
 * @Description
 */
public interface TotalMapper {
    /**
     * 查询初中高级技术人员数量
     * @param id
     * @return
     */
     List<Map<String,Object>> getUnitTotal(@Param("id") String id);
/**
 * 查询特殊人才数量
 */
      List<Map<String,Object>> getUnitspecial(@Param("id") String id);
    /**
     * 单位的项目数量
     */
    List<Map<String,Object>> getUnitProjectNum(@Param("id") String id);



    /**
     * 第一个统计的单位资质统计
     */
    List<Map<String,Object>> selectZizhi();

    /**
     * 第一个统计的类型统计
     */
    List<Map<String,Object>> selectTypetwo();
    List<Map<String,Object>> selectTypethree();


     List<Map<String,Object>> selectUnit(@Param("ownedDistrict") String ownedDistrict, @Param("createTime") String createTime);
    //查询单位数量
     List<Map<String,Object>> selectUnitNum(@Param("ownedDistrict") String ownedDistrict, @Param("createTime") String createTime);

}
