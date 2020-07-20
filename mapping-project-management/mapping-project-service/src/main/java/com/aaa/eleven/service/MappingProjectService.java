package com.aaa.eleven.service;

import com.aaa.eleven.base.BaseService;
import com.aaa.eleven.mapper.AuditMapper;
import com.aaa.eleven.mapper.MappingProjectMapper;
import com.aaa.eleven.mapper.ResultCommitMapper;
import com.aaa.eleven.model.Audit;
import com.aaa.eleven.model.MappingProject;
import com.aaa.eleven.model.ResultCommit;
import com.aaa.eleven.vo.MappingProjectAndResultCommitVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Company
 * @Author ftt
 * @Date Create in 2020/7/16 10:25
 * @Description
 */
@Service
public class MappingProjectService extends BaseService<MappingProject> {
    @Autowired
    private MappingProjectMapper mappingProjectMapper;
    @Autowired
    private ResultCommitMapper resultCommitMapper;
    @Autowired
    private AuditMapper auditMapper;
    /***
     * @Author ftt
     * @Description
     * 分页查询 MappingProject列表
     * @Date 2020/7/16 10:30
     * @Param [pageNo, pageSize]
     * @return com.github.pagehelper.PageInfo
     */
    public PageInfo selectMappingProjectList(Integer pageNo,Integer pageSize){
        PageHelper.startPage(pageNo,pageSize);
        List<MappingProject> mappingProjects = mappingProjectMapper.selectAll();
        if(0 < mappingProjects.size()){
            PageInfo pageInfo = new PageInfo(mappingProjects);
            return pageInfo;
        }
        return null;
    }
    /***
     * @Author ftt
     * @Description
     * 根据userid查询项目 + 项目类型
     * @Date 2020/7/18 10:11
     * @Param [map]
     * @return com.github.pagehelper.PageInfo<com.aaa.eleven.model.MappingProject>
     */
    public PageInfo<MappingProject> selectByUserId(Map map) {
        //获取分页信息
        Integer pageNum = Integer.parseInt(map.get("pageNum").toString());
        Integer pageSize = Integer.parseInt(map.get("pageSize").toString());
        PageHelper.startPage(pageNum,pageSize);
        //获取查询条件
        MappingProject project = new MappingProject();
        Long userId = Long.parseLong(map.get("userId").toString());
        String projectType = "";
        if(map.get("projectType") != null ){
            projectType =map.get("projectType").toString();
        }
        if (projectType == null && "".equals(projectType)) {
            project.setUserId(userId);
        }else {
            project.setUserId(userId).setProjectType(projectType);
        }
        //进行查询
        List<MappingProject> projects = mappingProjectMapper.select(project);
        //判断查询结果是否为空
        if (projects.size() > 0) {
            return new PageInfo(projects);
        }
        return null;
    }
    /**
     * 功能描述: <br>
     *@Description
     * 项目管理中的操作
     * @Param:
     * @Return:
     * @Author: zh
     * @Date: 2020/7/17 0017 10:57
     */
    /**
     * 功能描述: <br>
     *@Description
     * 条件 分页查询
     * @Param: [currentPage, pageSize, project_type]
     * @Return: com.github.pagehelper.PageInfo
     * @Author: zh
     * @Date: 2020/7/16 0016 16:59
     */
    public PageInfo query(Integer currentPage, Integer pageSize, String project_type) {
        PageHelper.startPage(currentPage,pageSize);
        List<Map> query = mappingProjectMapper.query(project_type);
        PageInfo<Map> pageInfo=new PageInfo<Map>(query);
        return pageInfo;
    }
    /**
     * 功能描述: <br>
     *@Description
     * 查询所有类型
     * @Param: []
     * @Return: java.util.List
     * @Author: zh
     * @Date: 2020/7/16 0016 16:59
     */
    public List<Map> queryAllProject_Type(){
        return mappingProjectMapper.queryAllProject_Type();
    };
    /**
     * 功能描述: <br>
     *@Description
     * 同时增加t_mapping_project和t_result_commit表的数据
     * @Param: [mappingProjectAndResultCommitVo]
     * @Return: java.util.List
     * @Author: zh
     * @Date: 2020/7/16 0016 22:29
     */
    public List insertMappingProjectAndResultCommit(MappingProjectAndResultCommitVo mappingProjectAndResultCommitVo){
        List list=new ArrayList();
        MappingProject mappingProject=new MappingProject();
        ResultCommit resultCommit=new ResultCommit();
        if(mappingProjectAndResultCommitVo !=null)
        {
            mappingProject.setId(mappingProjectAndResultCommitVo.getMapping_id());
            mappingProject.setProjectType(mappingProjectAndResultCommitVo.getMapping_projectType());
            mappingProject.setProjectName(mappingProjectAndResultCommitVo.getMapping_projectName());
            mappingProject.setProjectLeader(mappingProjectAndResultCommitVo.getMapping_projectLeader());
            mappingProject.setStartDate(mappingProjectAndResultCommitVo.getMapping_startDate());
            mappingProject.setEndDate(mappingProjectAndResultCommitVo.getMapping_endDate());
            mappingProject.setAcceptanceDepartment(mappingProjectAndResultCommitVo.getMapping_acceptanceDepartment());
            mappingProject.setProjectAmount(mappingProjectAndResultCommitVo.getMapping_projectAmount());
            mappingProject.setSchedule(mappingProjectAndResultCommitVo.getMapping_schedule());
            Integer integer = mappingProjectMapper.insertMappingProject(mappingProject);
            if (integer>0)
            {
                list.add(integer);
                resultCommit.setPlottingScale(mappingProjectAndResultCommitVo.getResult_plotting_scale());
                resultCommit.setMediumType(mappingProjectAndResultCommitVo.getResult_medium_type());
                resultCommit.setResultDate(mappingProjectAndResultCommitVo.getResult_date());
                resultCommit.setName(mappingProjectAndResultCommitVo.getResult_name());
                resultCommit.setCreateDate(mappingProjectAndResultCommitVo.getResult_create_date());
                resultCommit.setId(mappingProjectAndResultCommitVo.getResult_id());
                resultCommit.setRefId(mappingProjectAndResultCommitVo.getMapping_id());
                Integer integer1 = resultCommitMapper.insertResultCommit(resultCommit);
                if (integer1>0)
                {
                    list.add(integer1);
                    Audit audit=new Audit();
                    audit.setSubmitTime(new Date());
                    audit.setRefId(mappingProjectAndResultCommitVo.getMapping_id());
                    audit.setStatus(1);
                    audit.setName("111111");
                    audit.setType(1);
                    audit.setAuditTime(new Date());
                    auditMapper.insert(audit);
                    return list;
                }
            }

        }
        return null;

    }
    /**
     * 功能描述: <br>
     *@Description
     * 修改mappingProject数据
     * @Param: [mappingProject]
     * @Return: java.lang.Integer
     * @Author: zh
     * @Date: 2020/7/16 0016 20:59
     */
    public List updateMappingProjectById(MappingProjectAndResultCommitVo mappingProjectAndResultCommitVo){
        List list=new ArrayList();
        MappingProject mappingProject=new MappingProject();
        ResultCommit resultCommit=new ResultCommit();
        if (mappingProjectAndResultCommitVo!=null)
        {
            mappingProject.setId(mappingProjectAndResultCommitVo.getMapping_id());
            mappingProject.setProjectType(mappingProjectAndResultCommitVo.getMapping_projectType());
            mappingProject.setProjectName(mappingProjectAndResultCommitVo.getMapping_projectName());
            mappingProject.setProjectLeader(mappingProjectAndResultCommitVo.getMapping_projectLeader());
            mappingProject.setStartDate(mappingProjectAndResultCommitVo.getMapping_startDate());
            mappingProject.setEndDate(mappingProjectAndResultCommitVo.getMapping_endDate());
            mappingProject.setAcceptanceDepartment(mappingProjectAndResultCommitVo.getMapping_acceptanceDepartment());
            mappingProject.setProjectAmount(mappingProjectAndResultCommitVo.getMapping_projectAmount());
            mappingProject.setSchedule(mappingProjectAndResultCommitVo.getMapping_schedule());
            Integer integer = mappingProjectMapper.updateMappingProjectById(mappingProject);
            if(integer>0)
            {
                list.add(integer);
                resultCommit.setPlottingScale(mappingProjectAndResultCommitVo.getResult_plotting_scale());
                resultCommit.setMediumType(mappingProjectAndResultCommitVo.getResult_medium_type());
                resultCommit.setResultDate(mappingProjectAndResultCommitVo.getResult_date());
                resultCommit.setName(mappingProjectAndResultCommitVo.getResult_name());
                resultCommit.setCreateDate(mappingProjectAndResultCommitVo.getResult_create_date());
                resultCommit.setRefId(mappingProjectAndResultCommitVo.getMapping_id());
                Integer integer1 = resultCommitMapper.updateResultCommit(resultCommit);
                if (integer1>0)
                {
                    list.add(integer1);

                    return list;
                }
            }

        }
        return null;

    }

    /**
     * 删除
     * @param id
     * @return
     */
    public List deleteMappingProjectAndResult(Long id){
            List list=new ArrayList();
            Integer insert = mappingProjectMapper.deleteMappingProjectById(id);
            if(insert>0)
            {
                list.add(insert);
                Integer integer = resultCommitMapper.deleteResultCommitByRefId(id);
                if(integer>0)
                {
                    list.add(integer);
                    return list;
                }
            }
            return null;
    }
    /**
     * 功能描述: <br>
     *@Description
     * 主页中的模糊查询
     * @Param:
     * @Return:
     * @Author: zh
     * @Date: 2020/7/17 0017 10:58
     */
    public List<Map<String,Object>> selectMappingProjectByProjectNameAndProjectTypeAndStartDate(String projectName,String projectType,String startDate){
        List<Map<String, Object>> maps = mappingProjectMapper.selectMappingProjectByProjectNameAndProjectTypeAndStartDate(projectName, projectType, startDate);
        if(maps.size()>0)
        {
            return maps;
        }
        else
        {
            return null;
        }
    }
    /**
     * 功能描述: <br>
     *@Description
     * 模糊查询中的下拉框数据 测绘类型
     * @Param: []
     * @Return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @Author: zh
     * @Date: 2020/7/17 0017 14:53
     */
    public List<Map<String,Object>> selectProjectType(){
        List<Map<String, Object>> maps = mappingProjectMapper.selectProjectType();
        if(maps.size()>0)
        {
            return maps;
        }
        else
        {
            return null;
        }
    }

}
