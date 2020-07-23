package com.aaa.eleven.controller;

import com.aaa.eleven.base.BaseService;
import com.aaa.eleven.base.CommonController;
import com.aaa.eleven.base.ResultData;
import com.aaa.eleven.model.Menu;
import com.aaa.eleven.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.aaa.eleven.status.Status.*;

@RestController
public class MenuController extends CommonController<Menu> {
    @Autowired
    private MenuService menuService;
    @Override
    public BaseService<Menu> getBaseService() {
        return menuService;
    }
    /**
     * 功能描述: <br>
     *@Description
     * 一级菜单查询
     * @Param: []
     * @Return: com.aaa.eleven.base.ResultData
     * @Author: zh
     * @Date: 2020/7/17 0017 17:00
     */
    @PostMapping("/selectFirstMenu")
    public ResultData selectFirstMenu(@RequestParam String menuName,@RequestParam String startTime,@RequestParam String endTime){
        List<Map<String, Object>> maps = menuService.selectFirstMenu(menuName, startTime, endTime);
        if(maps.size()>0)
        {
            return super.selectSuccess(SELECT_SUCCESS.getMsg(),maps);
        }
        else {
            return super.selectFailed(SELECT_FAILED.getMsg());
        }
    }
    /**
     * 功能描述: <br>
     *@Description
     * 获得的二级菜单
     * @Param: [menu]
     * @Return: com.aaa.eleven.base.ResultData
     * @Author: zh
     * @Date: 2020/7/17 0017 17:15
     */
    @PostMapping("/selectSecondMenu")
    public ResultData selectSecond(Menu menu){
        List<Map<String, Object>> maps = menuService.selectSecondMenu(menu);
        if(maps.size()>0)
        {
            return super.selectSuccess(SELECT_SUCCESS.getMsg(),maps);
        }else
        {
            return super.selectFailed(SELECT_FAILED.getMsg());
        }
    }
    /**
     * 功能描述: <br>
     *@Description
     * 增加菜单的方法
     * @Param: [menu]
     * @Return: com.aaa.eleven.base.ResultData
     * @Author: zh
     * @Date: 2020/7/17 0017 19:45
     */
    @PostMapping("/insertMenu")
    public ResultData insertMenu(Menu menu){
        Integer insert = menuService.insert(menu);
        if(insert>0)
        {
            return super.insertSuccess(INSERT_SUCCESS.getMsg());
        }else {
            return super.insertFailed(INSERT_FAILED.getMsg());
        }
    }
    /**
     * 功能描述: <br>
     *@Description
     * 修改菜单的方法
     * @Param: [menu]
     * @Return: com.aaa.eleven.base.ResultData
     * @Author: zh
     * @Date: 2020/7/17 0017 19:53
     */
    @PutMapping("/updateMenu")
    public ResultData updateMenu(Menu menu){
        Integer update = menuService.update(menu);
        if(update>0)
        {
            return super.updateSuccess(UPDATE_SUCCESS.getMsg());
        }
        else {
            return super.updateFailed(UPDATE_FAILED.getMsg());
        }
    }
    @DeleteMapping("/deleteMenu")
    public ResultData deleteMenu(Menu menu){
        Integer delete = menuService.delete(menu);
        if(delete>0)
        {
            return super.deleteSuccess(DELETE_SUCCESS.getMsg());
        }
        return super.deleteFailed(DELETE_FAILED.getMsg());
    }
    /**
     * 功能描述: <br>
     *@Description
     * 查询主节点
     * @Param: []
     * @Return: com.aaa.eleven.base.ResultData
     * @Author: zh
     * @Date: 2020/7/18 0018 16:11
     */
    @PostMapping("/selectMasterNode")
    public ResultData selectMasterNode(){
        List<Menu> maps = menuService.selectMasterNode();
        if(maps.size()>0)
        {
            return selectSuccess(SELECT_SUCCESS.getMsg(),maps);
        }
        return selectFailed(SELECT_FAILED.getMsg());
    }
    /**
     * 功能描述: <br>
     *@Description
     * 查询子节点
     * @Param: [menuId]
     * @Return: com.aaa.eleven.base.ResultData
     * @Author: zh
     * @Date: 2020/7/18 0018 16:30
     */
    @PostMapping("/selectChildNode")
    public ResultData selectChildNode(Long menuId){
        List<Menu> maps = menuService.selectChildNode(menuId);
        if (maps.size()>0)
        {
            return   super.selectSuccess(SELECT_SUCCESS.getMsg(),maps);
        }
        return super.selectFailed(SELECT_FAILED.getMsg());
    }
    /**
     * 功能描述: <br>
     *@Description
     * 查询全部的权限
     * @Param: []
     * @Return: com.aaa.eleven.base.ResultData
     * @Author: zh
     * @Date: 2020/7/19 0019 14:00
     */
    @GetMapping("/selectAllMenu")
    public ResultData selectAllMenu(){
        List<Menu> menuList = menuService.selectAllMenu();
        if (menuList.size()>0)
        {
            return super.selectSuccess(SELECT_SUCCESS.getMsg(),menuList);
        }
        return super.selectFailed(SELECT_FAILED.getMsg());
    }
    /**
     * 功能描述: <br>
     *@Description
     * 根据menuID 获取父类信息
     * @Param: [menu]
     * @Return: com.aaa.eleven.base.ResultData
     * @Author: zh
     * @Date: 2020/7/19 0019 14:54
     */
    @GetMapping("/selectParentMenu")
    public ResultData selectParentMenu(Menu menu){
        System.out.println(menu.toString());
        List<Map<String, Object>> maps = menuService.selectParentMenu(menu);
        if(maps != null)
        {
            return super.selectSuccess(SELECT_SUCCESS.getMsg(),maps);
        }
        return null;
    }
}
