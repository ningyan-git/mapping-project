package com.aaa.eleven.service;

import com.aaa.eleven.base.BaseService;
import com.aaa.eleven.mapper.TechnicistMapper;
import com.aaa.eleven.model.Technicist;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Company
 * @Author ftt
 * @Date Create in 2020/7/14 21:29
 * @Description
 */
@Service
public class TechnicistService extends BaseService<Technicist> {
    @Autowired
    private TechnicistMapper technicistMapper;
    /***
     * @Author ftt
     * @Description
     * 查询技术人员信息
     *     把userId放入一个对象中，根据这个对象查询技术人员信息
     * @Date 2020/7/14 21:33
     * @Param [userId]
     * @return com.aaa.eleven.model.Technicist
     */
    public PageInfo selectOne(Integer pageNo,Integer pageSize,Long userId){
        PageHelper.startPage(pageNo,pageSize);
        Technicist technicist = new Technicist();
        if(null !=userId && !"".equals(userId)){
            technicist.setUserId(userId);
            List<Technicist> select = technicistMapper.select(technicist);
            if(0 < select.size()){
                PageInfo pageInfo = new PageInfo(select);
                return pageInfo;
            }
        }
        return null;
    }
    /***
     * @Author ftt
     * @Description
     * 根据id查询技术人员具体信息
     * @Date 2020/7/16 8:26
     * @Param [id]
     * @return com.aaa.eleven.model.Technicist
     */
    public List<Map<String, Object>> selectTechnicistDetail(Long id){
        if(null != id){
            List<Map<String, Object>> maps = technicistMapper.selectTechnicistDetail(id);
            if(null != maps && maps.size()> 0){
                return maps;
            }
        }
        return  null;
    }



}
