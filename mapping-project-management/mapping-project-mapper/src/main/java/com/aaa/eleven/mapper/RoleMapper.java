package com.aaa.eleven.mapper;

import com.aaa.eleven.model.Role;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface RoleMapper extends Mapper<Role> {
    /**
     * 功能描述: <br>
     *@Description
     * 查询role的信息
     * @Param: [roleName, startTime, endTime]
     * @Return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @Author: zh
     * @Date: 2020/7/18 0018 10:28
     */
    List<Map<String,Object>> selectAllRoleByCondition(@Param("roleName") String roleName, @Param("startTime")String startTime, @Param("endTime") String endTime);

    /**
     * 功能描述: <br>
     *@Description
     * 查询用户所拥有角色菜单
     * @Param: [roleId]
     * @Return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @Author: zh
     * @Date: 2020/7/18 0018 10:42
     */
    @Select("select tm.MENU_NAME,tm.MENU_ID,tm.PARENT_ID from t_role tr left join t_role_menu trm on tr.ROLE_ID=trm.ROLE_ID right join t_menu tm on trm.menu_id=tm.MENU_ID where tr.ROLE_ID=#{roleId}")
    List<Map<String,Object>> selectMenuByRoleId(Long roleId);
    /**
     * 功能描述: <br>
     *@Description
     * 修改角色权限 先删除t_menu_role表中的数据然后在增加
     * @Param:
     * @Return:
     * @Author: zh
     * @Date: 2020/7/18 0018 11:27
     */
    Integer deleteMenuRoleByRoleId(Long roleId);
    /**
     * 功能描述: <br>
     *@Description
     * 给中间表添加数据
     *
     * @Param: [roleId, menuId]
     * @Return: java.lang.Integer
     * @Author: zh
     * @Date: 2020/7/18 0018 15:04
     */
    @Insert("insert into t_role_menu (role_id,menu_id) values (#{roleId},#{menuId})")
    Integer insertRoleMenu(@Param("roleId") Long roleId,@Param("menuId")Integer menuId);
    /**
     * 功能描述: <br>
     *@Description
     * 删除角色
     * @Param: [id]
     * @Return: java.lang.Integer
     * @Author: zh
     * @Date: 2020/7/19 0019 12:06
     */
    Integer deleteRoleById(Long roleId);
}