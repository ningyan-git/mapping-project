package com.aaa.eleven.controller;

import com.aaa.eleven.base.ResultData;
import com.aaa.eleven.model.Menu;
import com.aaa.eleven.service.MappingProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.aaa.eleven.status.Status.*;

/**
 * @Company
 * @Author ftt
 * @Date Create in 2020/7/21 16:57
 * @Description
 */
@RestController
public class MenuController {

    @Autowired
    private MappingProjectService mappingProjectService;
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
    public ResultData selectFirstMenu(String menuName, String startTime, String endTime){
       return mappingProjectService.selectFirstMenu(menuName, startTime, endTime);
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
        return mappingProjectService.selectSecond(menu);

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
        return mappingProjectService.insertMenu(menu);

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
        return mappingProjectService.updateMenu(menu);

    }
    @DeleteMapping("/deleteMenu")
    public ResultData deleteMenu(Menu menu){
        return mappingProjectService.deleteMenu(menu);

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
        return mappingProjectService.selectMasterNode();

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
        return mappingProjectService.selectChildNode(menuId);

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
        return mappingProjectService.selectAllMenu();

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
        return mappingProjectService.selectParentMenu(menu);

    }
}
