package com.aaa.eleven.service;

import com.aaa.eleven.base.BaseService;
import com.aaa.eleven.mapper.TechnicistMapper;
import com.aaa.eleven.model.Technicist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
     * 查询单个技术人员信息
     *     把userId放入一个对象中，根据这个对象查询技术人员信息
     * @Date 2020/7/14 21:33
     * @Param [userId]
     * @return com.aaa.eleven.model.Technicist
     */
    public List<Technicist> selectOne(Long userId){
        Technicist technicist = new Technicist();
        if(null !=userId && !"".equals(userId)){
            technicist.setUserId(userId);
            List<Technicist> select = technicistMapper.select(technicist);
            return select;
        }
        return null;
    }

}
