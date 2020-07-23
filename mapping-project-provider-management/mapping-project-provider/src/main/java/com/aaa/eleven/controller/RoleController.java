package com.aaa.eleven.controller;

import com.aaa.eleven.base.BaseService;
import com.aaa.eleven.base.CommonController;
import com.aaa.eleven.base.ResultData;
import com.aaa.eleven.model.Role;
import com.aaa.eleven.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.aaa.eleven.status.Status.*;

@RestController
public class RoleController extends CommonController<Role> {
    @Autowired
    private RoleService roleService;
    @Override
    public BaseService<Role> getBaseService() {
        return roleService;
    }
    /**
     * 功能描述: <br>
     *@Description
     * 查询所有角色信息附带条件查询
     * @Param: [roleName, startTime, endTime]
     * @Return: com.aaa.eleven.base.ResultData
     * @Author: zh
     * @Date: 2020/7/18 0018 9:23
     */
    @GetMapping("/selectAllRoleByCondition")
    public ResultData selectAllRoleByCondition(@RequestParam String roleName,@RequestParam String startTime,@RequestParam String endTime){
        List<Map<String, Object>> maps = roleService.selectAllRoleByCondition(roleName, startTime, endTime);
        if(maps.size()>0)
        {
            return super.selectSuccess(SELECT_SUCCESS.getMsg(),maps);
        }
        return super.selectFailed(SELECT_FAILED.getMsg());
    }
    /**
     * 功能描述: <br>
     *@Description
     * 点开操作按钮之后获取所拥有的权限
     * @Param: [roleId]
     * @Return: com.aaa.eleven.base.ResultData
     * @Author: zh
     * @Date: 2020/7/18 0018 10:50
     */
    @GetMapping("/selectMenuByRoleId")
    public ResultData selectMenuByRoleId(@RequestParam("roleId") Long roleId){
        List<Map<String, Object>> maps = roleService.selectMenuByRoleId(roleId);
        if(maps.size()>0)
        {
            return super.selectSuccess(SELECT_SUCCESS.getMsg(),maps);
        }
        return null;
    }
    /**
     * 功能描述: <br>
     *@Description
     * 修改权限
     * @Param: [roleId, menuIds]
     * @Return: com.aaa.eleven.base.ResultData
     * @Author: zh
     * @Date: 2020/7/18 0018 16:26
     */
    @PostMapping("/updateRoleJurisdiction")
    public ResultData updateRoleJurisdiction(Long roleId, Integer[] menuIds){
        Map map = roleService.updateRoleJurisdiction(roleId, menuIds);
        if(menuIds.length == map.size())
        {
            return super.updateSuccess(UPDATE_SUCCESS.getMsg());
        }
        return super.updateFailed(UPDATE_FAILED.getMsg());
    }
    /**
     * 功能描述: <br>
     *@Description
     * 增加角色 并给中间表添加数据
     * @Param: [role, ids]
     * @Return: com.aaa.eleven.base.ResultData
     * @Author: zh
     * @Date: 2020/7/19 0019 11:44
     */
    @PostMapping("/insertRole")
    public ResultData insertRole(@RequestBody Role role, Integer[] ids)
    {
        Map map = roleService.insertRole(role, ids);
        if (map.size()>0)
        {
            return super.insertSuccess(INSERT_SUCCESS.getMsg());
        }
        return insertFailed(INSERT_FAILED.getMsg());
    }
    /**
     * 功能描述: <br>
     *@Description
     * 删除角色
     * @Param: [roleId]
     * @Return: com.aaa.eleven.base.ResultData
     * @Author: zh
     * @Date: 2020/7/19 0019 14:00
     */
    @GetMapping  ("/deleteRole")
    public ResultData deleteRole(@RequestParam("roleId") Long roleId){
        Integer integer = roleService.deleteRole(roleId);
        if(integer>0)
        {
           return super.deleteSuccess(DELETE_SUCCESS.getMsg());
        }
        return super.deleteFailed(DELETE_FAILED.getMsg());
    }
}
