package com.aaa.eleven.mapper;

import com.aaa.eleven.model.Menu;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface MenuMapper extends Mapper<Menu> {
    /**
     * 功能描述: <br>
     *@Description
     * 查询一级菜单
     * @Param: []
     * @Return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @Author: zh
     * @Date: 2020/7/17 0017 17:04
     */
    List<Map<String,Object>> selectMenu(@Param("menuName") String menuName, @Param("startTime") String startTime, @Param("endTime") String endTime);
    /**
     * 功能描述: <br>
     *@Description
     * 查询二级菜单
     * @Param: [menu]
     * @Return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @Author: zh
     * @Date: 2020/7/17 0017 17:04
     */
    @Select("select MENU_NAME  from t_menu where parent_id=#{menuId}")
    List<Map<String,Object>> selectSecondMenu(Menu menu);

    /**
     * 功能描述: <br>
     *@Description
     * 查询主节点
     * @Param: []
     * @Return: java.util.List<java.util.Map>
     * @Author: zh
     * @Date: 2020/7/18 0018 16:14
     */

    List<Menu> selectMasterNode();
    /**
     * 功能描述: <br>
     *@Description
     * 查询子节点
     * @Param: [menuId]
     * @Return: java.util.List<com.aaa.eleven.model.Menu>
     * @Author: zh
     * @Date: 2020/7/18 0018 17:25
     */
    List<Menu> selectChildNode(Long menuId);
    /**
     * 功能描述: <br>
     *@Description
     * 根据menuId获取父类MenuId
     * @Param: [menuId]
     * @Return: com.aaa.eleven.model.Menu
     * @Author: zh
     * @Date: 2020/7/19 0019 14:45
     */
    List<Map<String,Object>> selectParentMenu(Long parentId);
}