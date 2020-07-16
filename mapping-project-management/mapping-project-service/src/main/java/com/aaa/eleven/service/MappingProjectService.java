package com.aaa.eleven.service;

import com.aaa.eleven.base.BaseService;
import com.aaa.eleven.mapper.MappingProjectMapper;
import com.aaa.eleven.model.MappingProject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
