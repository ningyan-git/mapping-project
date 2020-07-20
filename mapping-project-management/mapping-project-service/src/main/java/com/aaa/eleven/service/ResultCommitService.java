package com.aaa.eleven.service;

import com.aaa.eleven.base.BaseService;
import com.aaa.eleven.mapper.ResultCommitMapper;
import com.aaa.eleven.model.ResultCommit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ResultCommitService extends BaseService<ResultCommit> {
    @Autowired
    private ResultCommitMapper resultCommitMapper;
    public ResultCommit selectResultCommitByRefId(String ref_id){
        return resultCommitMapper.selectResultCommit(ref_id);
    }
    public Integer deleteResultCommitByRefId(Long ref_id){
        return  resultCommitMapper.deleteResultCommitByRefId(ref_id);
    }
    /**
     * 功能描述: <br>
     *@Description
     * 主页中模糊查询
     * @Param:
     * @Return:
     * @Author: zh
     * @Date: 2020/7/17 0017 15:40
     */
    public List<Map<String,Object>> selectNameAndProjectTypeAndResultDate(String name,String projectType,String resultDate){
        List<Map<String, Object>> maps = resultCommitMapper.selectNameAndProjectTypeAndResultDate(name, projectType, resultDate);
        if(maps.size()>0)
        {
            return maps;
        }
        return null;
    };
}
