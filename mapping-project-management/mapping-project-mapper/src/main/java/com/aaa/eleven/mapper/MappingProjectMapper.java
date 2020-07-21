package com.aaa.eleven.mapper;

import com.aaa.eleven.model.MappingProject;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface MappingProjectMapper extends Mapper<MappingProject> {
    /**
     * 功能描述: <br>
     *@Description
     * 项目管理中的操作
     * @Param: [project_type]
     * @Return: java.util.List<java.util.Map>
     * @Author: zh
     * @Date: 2020/7/17 0017 10:59
     */
    List<Map> query(String project_type);
    @Select("select project_type from t_mapping_project GROUP BY project_type")
    List<Map> queryAllProject_Type();
    @Update("update t_mapping_project set project_type=#{projectType},project_name=#{projectName},project_amount=#{projectAmount},project_leader=#{projectLeader},start_date=#{startDate},end_date=#{endDate},project_amount=#{projectAmount},acceptance_department=#{acceptanceDepartment},schedule=#{schedule} where id=#{id}")
    Integer updateMappingProjectById( MappingProject mappingProject);
    @Delete("delete from t_mapping_project where id=#{id}")
    Integer deleteMappingProjectById(long id);
    @Insert("insert into t_mapping_project (id,project_type,project_name,project_leader,start_date,end_date,acceptance_department,project_amount,schedule) values (#{id},#{projectType},#{projectName},#{projectLeader},#{startDate},#{endDate},#{acceptanceDepartment},#{projectAmount},#{schedule})")
    Integer insertMappingProject(MappingProject mappingProject);
    /**
     * 功能描述: <br>
     *@Description
     * 以下是主页中的模糊查询操作
     * @Param:
     * @Return:
     * @Author: zh
     * @Date: 2020/7/17 0017 10:59
     */
    /**
     * 功能描述: <br>
     *@Description
     * 模糊查询
     * @Param: [projectName, projectType, startDate]
     * @Return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @Author: zh
     * @Date: 2020/7/17 0017 11:11
     */
    List<Map<String,Object>> selectMappingProjectByProjectNameAndProjectTypeAndStartDate(@Param("projectName") String projectName,@Param("projectType") String projectType,@Param("startDate")String startDate);
    /**
     * 功能描述: <br>
     *@Description
     * 模糊查询的下拉框数据  测绘类型
     * @Param:
     * @Return:
     * @Author: zh
     * @Date: 2020/7/17 0017 14:49
     */
    @Select("select project_type from t_mapping_project GROUP BY project_type")
    List<Map<String,Object>> selectProjectType();
    @Select("select * from t_mapping_project where project_name=#{projectName}")
    MappingProject selectMappingProjectByProjectName(MappingProject mappingProject);

    /**
     * 根据项目名字模糊查询并分页
     * @param projectName
     * @return
     */
    List<MappingProject> selectAllMappingProject(@Param("projectName") String projectName);


    /**
     * 首先查询出项目的汇交成果状态为0通过的项目并且根据项目 名字模糊查询
     */

    List<Map<String,Object>> selectResultZeroPass(@Param("projectName") String projectName);
    /**
     * 汇交成果信息，的详情，根据项目id查询相应的项目信息，资源表，汇交结果表
     */

    List<Map<String,Object>> selectResource(@Param("id") String id);
    List<Map<String,Object>> selectResult(@Param("id") String id);




    /**
     * 项目审核，首先查出项目审核状态为已提交audit_status=2的项目
     */
    List<Map<String,Object>> selectAuditCommit(@Param("projectName") String projectName);



    /**
     * 成果汇交审核
     * 首先查出项目成果状态为已提交results_status=2的项目
     */
    List<Map<String,Object>> selectAuditResult(@Param("projectName") String projectName);
}