package com.aaa.eleven.service;

import com.aaa.eleven.base.BaseService;
import com.aaa.eleven.mapper.MenuMapper;
import com.aaa.eleven.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MenuService extends BaseService<Menu> {
    @Autowired
    private MenuMapper menuMapper;
    /**
     * 功能描述: <br>
     *@Description
     * 查询一级菜单
     * @Param: []
     * @Return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @Author: zh
     * @Date: 2020/7/17 0017 17:04
     */
    public List<Map<String,Object>> selectFirstMenu(String menuName,String startTime,String endTime){
        List<Map<String, Object>> maps = menuMapper.selectMenu(menuName, startTime, endTime);
        if(maps.size()>0)
        {
            return maps;
        }else
        {
            return null;
        }
    };
    /**
     * 功能描述: <br>
     *@Description
     * 查询二级菜单
     * @Param: [menu]
     * @Return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @Author: zh
     * @Date: 2020/7/17 0017 19:26
     */
    public List<Map<String,Object>> selectSecondMenu(Menu menu){
        List<Map<String, Object>> maps = menuMapper.selectSecondMenu(menu);
        if(maps.size()>0)
        {
            return maps;
        }
        return null;
    }
    /**
     * 功能描述: <br>
     *@Description
     * 查询主节点
     * @Param: []
     * @Return: java.util.List<java.util.Map>
     * @Author: zh
     * @Date: 2020/7/18 0018 16:09
     */
    public List<Menu> selectMasterNode(){
        return  menuMapper.selectMasterNode();

    };
    /**
     * 功能描述: <br>
     *@Description
     * 通过主节点的menu_id获取从节点
     * @Param: [menuId]
     * @Return: java.util.List<java.util.Map>
     * @Author: zh
     * @Date: 2020/7/18 0018 16:56
     */
    public List<Menu> selectChildNode(Long menuId){
        return menuMapper.selectChildNode(menuId);
    }
      /**
     * 功能描述: <br>
     *@Description
     * 查询所有的权限
     * @Param: []
     * @Return: java.util.List<com.aaa.eleven.model.Menu>
     * @Author: zh
     * @Date: 2020/7/19 0019 9:12
     */
    public List<Menu> selectAllMenu(){
        //一级菜单
        List<Menu> menus = selectMasterNode();
        if (menus.size() > 0){
            for (Menu menu : menus) {
                //二级菜单
                List<Menu> menuList = menuMapper.selectChildNode(menu.getMenuId());
                if (menuList.size()>0 && menuList != null){
                    //把二级菜单加入到一级菜单里边
                    menu.setSubMenuSecond(menuList);
                    for (Menu menu1 : menuList) {
                        //三级菜单
                        List<Menu> selectChildNode = menuMapper.selectChildNode(menu1.getMenuId());
                        if (selectChildNode.size()>0 && selectChildNode != null){
                            //把三级菜单加入到二级菜单里边
                            menu1.setSubMenuSecond(selectChildNode);
                        }
                    }
                }
            }
            return menus;
        }
        return null;
    }
    /**
     * 功能描述: <br>
     *@Description
     * 根据子类menuId查询父类信息
     * @Param: [menu]
     * @Return: com.aaa.eleven.model.Menu
     * @Author: zh
     * @Date: 2020/7/19 0019 14:51
     */
        public List<Map<String,Object>> selectParentMenu(Menu menu){
            //根据id查询对象
            Menu menu1 = menuMapper.selectByPrimaryKey(menu.getMenuId());
            System.out.println(menu1.toString());
            //
            List<Map<String,Object>> maps = menuMapper.selectParentMenu(menu1.getParentId());
            return maps;
        }

}
