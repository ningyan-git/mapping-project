package com.aaa.eleven.controller;

import com.aaa.eleven.base.ResultData;
import com.aaa.eleven.model.Role;
import com.aaa.eleven.service.MappingProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import static com.aaa.eleven.status.Status.*;
import static com.aaa.eleven.status.Status.DELETE_FAILED;

/**
 * @Company
 * @Author ftt
 * @Date Create in 2020/7/21 17:11
 * @Description
 */
@RestController
public class RoleController {
    @Autowired
    private MappingProjectService mappingProjectService;
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
    public ResultData selectAllRoleByCondition(String roleName, String startTime, String endTime){
       return mappingProjectService.selectAllRoleByCondition(roleName, startTime, endTime);
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
        return mappingProjectService.selectMenuByRoleId(roleId);

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
        return mappingProjectService.updateRoleJurisdiction(roleId, menuIds);

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
    public ResultData insertRole(Role role, Integer[] ids)
    {
        return mappingProjectService.insertRole(role, ids);

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
        return mappingProjectService.deleteRole(roleId);

    }
}
