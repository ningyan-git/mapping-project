package com.aaa.eleven.service;

import com.aaa.eleven.base.BaseService;
import com.aaa.eleven.mapper.MappingProjectMapper;
import com.aaa.eleven.model.MappingProject;
import com.aaa.eleven.utils.FileNameUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.http.client.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.aaa.eleven.staticproperties.TimeForatProperties.TIME_FORMAT;

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
    /***
     * @Author ftt
     * @Description
     * 新增项目
     * @Date 2020/7/18 10:24
     * @Param [mappingProject]
     * @return java.lang.Boolean
     */
    public Boolean insertMappingProject(MappingProject mappingProject){
        if(mappingProject != null){
           mappingProject.setId(Long.parseLong(FileNameUtils.getFileName()));
           mappingProject.setCreateTime(DateUtils.formatDate(new Date(),TIME_FORMAT));
            Integer flag = insert(mappingProject);
            if(flag > 0){
                return true;
            }
        }
        return false;
    }
    /***
     * @Author ftt
     * @Description
     * 修改项目
     * @Date 2020/7/18 10:24
     * @Param [mappingProject]
     * @return java.lang.Boolean
     */
    public Boolean updateMappingProject(MappingProject mappingProject){
        if(mappingProject != null){
            if(mappingProject.getId() != null){
                mappingProject.setModifyTime(DateUtils.formatDate(new Date(),TIME_FORMAT));
                Integer flag = update(mappingProject);
                if(flag > 0){
                    return true;
                }
            }
        }
        return false;
    }
    /***
     * @Author ftt
     * @Description
     * 删除项目
     * @Date 2020/7/18 10:24
     * @Param [mappingProject]
     * @return java.lang.Boolean
     */
    public Boolean deleteMappingProject(MappingProject mappingProject){
        if(mappingProject != null){
            if(mappingProject.getId() != null){
                Integer flag = delete(mappingProject);
                if(flag > 0){
                    return true;
                }
            }
        }
        return false;
    }

}
