package com.aaa.eleven.service;

import com.aaa.eleven.base.BaseService;
import com.aaa.eleven.mapper.RoleMapper;
import com.aaa.eleven.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleService extends BaseService<Role> {
    @Autowired
    private RoleMapper roleMapper;
    /**
     * 功能描述: <br>
     *@Description
     * 查询所有的角色信息
     * @Param: [roleName, startTime, endTime]
     * @Return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @Author: zh
     * @Date: 2020/7/18 0018 10:42
     */
    public List<Map<String,Object>> selectAllRoleByCondition(String roleName,String startTime,String endTime){
        List<Map<String, Object>> maps = roleMapper.selectAllRoleByCondition(roleName, startTime, endTime);
        if (maps.size()>0)
        {
            return maps;
        }
        return null;
    }


    /**
     * 功能描述: <br>
     *@Description
     * 点开操作按钮 查看角色所拥有的权限
     * @Param: [roleId]
     * @Return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @Author: zh
     * @Date: 2020/7/18 0018 10:47
     */
    public List<Map<String,Object>> selectMenuByRoleId(Long roleId){
        List<Map<String, Object>> maps = roleMapper.selectMenuByRoleId(roleId);
        if(maps.size()>0)
        {
            return maps;
        }
        return null;
    }
    /**
     * 功能描述: <br>
     *@Description
     * 修改角色权限
     * @Param: [roleId, menuIds]
     * @Return: java.util.Map
     * @Author: zh
     * @Date: 2020/7/18 0018 15:09
     */
    public Map updateRoleJurisdiction(Long roleId,Integer[] menuIds){
        Map map=new HashMap();
        if(menuIds != null)
        {

                //先删除原来的全部权限
                Integer integer = roleMapper.deleteMenuRoleByRoleId(roleId);
                if(integer != 0)
                {
                    //添加权限
                    for(int i=0;i<menuIds.length;i++)
                    {
                        Integer integer1 = roleMapper.insertRoleMenu(roleId, menuIds[i]);
                        map.put(i,integer1);
                    }
                    return map;
                }
        }
        return null;
    }
    /**
    * 功能描述: <br>
    *@Description
    * 添加角色 并给中间表添加数据
    * @Param: [role, ids]
    * @Return: java.util.Map
    * @Author: zh
    * @Date: 2020/7/19 0019 11:35
    */
    public Map insertRole(Role role, Integer[] ids){
        Map map=new HashMap();
        //先增加角色信息
        int insert = roleMapper.insert(role);
        // 需要加一个查询用户 获得该用户的id
        Role role1 = roleMapper.selectOne(role);
        if(insert>0)
        {
            for (Integer id : ids) {
                //通过角色id给中间表添加信息
                roleMapper.insertRoleMenu(role1.getRoleId(),id);
                map.put(role.getRoleId(),id);
            }
        }
        return map;
    }
    /**
     * 功能描述: <br>
     *@Description
     * 删除角色信息
     * @Param: [role]
     * @Return: boolean
     * @Author: zh
     * @Date: 2020/7/19 0019 11:55
     */
    public Integer deleteRole(Long roleId){

        //先删除role表中的信息
        Integer integer1 = roleMapper.deleteRoleById(roleId);
        System.out.println(integer1);
        if(integer1 > 0)
        {
            //删除中间表中的信息
            Integer integer = roleMapper.deleteMenuRoleByRoleId(roleId);
            if (integer>0){
                return integer;
            }

        }
        return null;
    }
}
