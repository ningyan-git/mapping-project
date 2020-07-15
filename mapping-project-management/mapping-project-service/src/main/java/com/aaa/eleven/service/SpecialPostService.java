package com.aaa.eleven.service;

import com.aaa.eleven.base.BaseService;
import com.aaa.eleven.mapper.SpecialPostMapper;
import com.aaa.eleven.model.SpecialPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Company
 * @Author ftt
 * @Date Create in 2020/7/14 21:36
 * @Description
 */
@Service
public class SpecialPostService extends BaseService<SpecialPost> {
    @Autowired
    private SpecialPostMapper specialPostMapper;
    /***
     * @Author ftt
     * @Description
     * 查询特殊岗位人员
     *       把userId放入一个对象中，根据这个对象查询特殊岗位人员
     * @Date 2020/7/14 21:38
     * @Param [userId]
     * @return com.aaa.eleven.model.SpecialPost
     */
    public List<SpecialPost> selectOne(Long userId){
        SpecialPost specialPost = new SpecialPost();
        if(null !=userId && !"".equals(userId)){
            specialPost.setUserId(userId);
            List<SpecialPost> select = specialPostMapper.select(specialPost);
            return select;
        }
        return null;
    }
}
