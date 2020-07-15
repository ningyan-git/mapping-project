package com.aaa.eleven.service;

import com.aaa.eleven.base.BaseService;
import com.aaa.eleven.mapper.PrincipalMapper;
import com.aaa.eleven.model.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Company
 * @Author ftt
 * @Date Create in 2020/7/14 21:24
 * @Description
 */
@Service
public class PrincipalService extends BaseService<Principal> {
    @Autowired
    private PrincipalMapper principalMapper;
    /***
     * @Author ftt
     * @Description
     * 查询单个负责人信息
     *     把userId放入一个对象中，根据这个对象查询负责人信息
     * @Date 2020/7/14 21:27
     * @Param [userId]
     * @return com.aaa.eleven.model.Principal
     */
    public List<Principal> selectOne(Long userId){
        Principal principal = new Principal();
        if(null!=userId && !"".equals(userId)){
            principal.setUserId(userId);
            List<Principal> select = principalMapper.select(principal);
            return select;
        }
        return null;
    }
}
