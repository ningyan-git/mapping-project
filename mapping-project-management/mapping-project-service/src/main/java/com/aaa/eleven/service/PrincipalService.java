package com.aaa.eleven.service;

import com.aaa.eleven.base.BaseService;
import com.aaa.eleven.mapper.PrincipalMapper;
import com.aaa.eleven.model.Principal;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
     * 分页查询负责人信息列表
     *     把userId放入一个对象中，根据这个对象查询负责人信息
     * @Date 2020/7/14 21:27
     * @Param [userId]
     * @return com.aaa.eleven.model.Principal
     */
    public PageInfo selectOne(Integer pageNo,Integer pageSize,Long userId){
        PageHelper.startPage(pageNo,pageSize);
        Principal principal = new Principal();
        if(null!=userId && !"".equals(userId)){
            principal.setUserId(userId);
            List<Principal> select = principalMapper.select(principal);
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
     * 根据id查询负责人具体信息
     * 附件包含多个文件
     * @Date 2020/7/15 20:54
     * @Param [id]
     * @return com.aaa.eleven.model.Principal
     */
    public List<Map<String, Object>> selectPrincipalDetail(Long id){
        Principal principal = new Principal();
        if(null != id){
            List<Map<String, Object>> list = principalMapper.selectPrincipalDetail(id);
            if(list.size() > 0){
                return list;
            }
        }
        return null;
    }
}
