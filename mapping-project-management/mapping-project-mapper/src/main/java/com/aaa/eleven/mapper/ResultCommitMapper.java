package com.aaa.eleven.mapper;

import com.aaa.eleven.model.ResultCommit;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface ResultCommitMapper extends Mapper<ResultCommit> {
    /**
     * 功能描述: <br>
     *@Description
     * 项目管理中的操作
     * @Param: [ref_id]
     * @Return: com.aaa.eleven.model.ResultCommit
     * @Author: zh
     * @Date: 2020/7/17 0017 15:18
     */
    @Select("select * from t_result_commit where ref_id=#{ref_id}")
    ResultCommit selectResultCommit(String ref_id);
    @Update("update t_result_commit set plotting_scale=#{plottingScale},medium_type=#{mediumType},result_date=#{resultDate},name=#{name},create_date=#{createDate} where ref_id=#{refId}")
    Integer updateResultCommit(ResultCommit resultCommit);
    @Delete("delete from t_result_commit where ref_id=#{ref_id}")
    Integer deleteResultCommitByRefId(Long ref_id);
    @Insert("insert into t_result_commit (id,plotting_scale,medium_type,result_date,name,create_date,ref_id) values (#{id},#{plottingScale},#{mediumType},#{resultDate},#{name},#{createDate},#{refId})")
    Integer insertResultCommit(ResultCommit resultCommit);
    /**
     * 功能描述: <br>
     *@Description
     * 以下是主页中的操作
     * @Param:
     * @Return:
     * @Author: zh
     * @Date: 2020/7/17 0017 15:18
     */
    List<Map<String,Object>> selectNameAndProjectTypeAndResultDate(@Param("name") String name, @Param("projectType") String projectType, @Param("resultDate") String resultDate);

}