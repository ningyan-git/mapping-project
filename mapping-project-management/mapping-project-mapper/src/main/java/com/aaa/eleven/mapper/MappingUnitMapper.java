package com.aaa.eleven.mapper;

import com.aaa.eleven.model.MappingUnit;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface MappingUnitMapper extends Mapper<MappingUnit> {
    /***
     * @Author ftt
     * @Description
     * 根据unitname 模糊查询
     * @Date 2020/7/17 17:33
     * @Param [unitName]
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    List<Map<String,Object>> selectByConditions(@Param("unitName") String unitName);
    /***
     * @Author ftt
     * @Description
     * 随机按照比例并且按照区抽查单位
     * @Date 2020/7/17 17:33
     * @Param [ran, ownedDistrict]
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("select @rownum := @rownum + 1, t_mapping_unit.*\n" +
            "from (select @rownum := 0) row,\n" +
            "     t_mapping_unit\n" +
            "where @rownum < (select round(count(*) * #{ran}) from t_mapping_unit)\n" +
            "  and owned_district = #{ownedDistrict}\n" +
            "order by rand()")
    List<Map<String,Object>> selectunitSpotCheck(@Param("ran") double ran,@Param("ownedDistrict") String ownedDistrict);

    /***
     * @Author ftt
     * @Description
     * 随机按照比例
     * @Date 2020/7/17 17:33
     * @Param [ran, ownedDistrict]
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("select @rownum := @rownum + 1, t_mapping_unit.*\n" +
            "from (select @rownum := 0) row,\n" +
            "     t_mapping_unit\n" +
            "where @rownum < (select round(count(*) * #{ran}) from t_mapping_unit)\n" +
            "order by rand()")
    List<Map<String,Object>> selectunitSpotCheckByRate(@Param("ran") double ran);


    /***
     * @Author ftt
     * @Description
     * 抽查人员
     * @Date 2020/7/17 18:21
     * @Param [ran]
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("select @rownum:=@rownum+1,t_check_person.* from (select @rownum:=0) row ,t_check_person where @rownum<(select round(count(1)*#{ran}) from t_check_person)   order by rand()")
    List<Map<String,Object>> selectPersonCheck(@Param("ran") double ran);
}