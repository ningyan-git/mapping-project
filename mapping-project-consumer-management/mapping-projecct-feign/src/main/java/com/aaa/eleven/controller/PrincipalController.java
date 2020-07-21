package com.aaa.eleven.controller;

import com.aaa.eleven.base.ResultData;
import com.aaa.eleven.model.Principal;
import com.aaa.eleven.service.MappingProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Company
 * @Author ftt
 * @Date Create in 2020/7/14 22:09
 * @Description
 */
@RestController
public class PrincipalController {
    @Autowired
    private MappingProjectService mappingProjectService;
    /***
     * @Author ftt
     * @Description
     * 查询负责人列表
     * @Date 2020/7/14 21:55
     * @Param [userId]
     * @return com.aaa.eleven.base.ResultData
     */
    @GetMapping("/principal/selectPrincipalListByUserId")
    public ResultData selectPrincipalListByUserId(@RequestParam(value = "curpage",required = false,defaultValue = "1")int curpage, @RequestParam(value = "pagesize",required = false,defaultValue = "5")int pagesize,@RequestParam("userId") long userId){
       return mappingProjectService.selectPrincipalListByUserId(curpage, pagesize, userId);
    }
    /***
     * @Author ftt
     * @Description
     * 根据id查询负责人详细信息
     * @Date 2020/7/15 20:57
     * @Param [id]
     * @return com.aaa.eleven.base.ResultData
     */
    @GetMapping("/principal/selectPrincipalDetail")
    public ResultData selectPrincipalDetail(@RequestParam("id") Long id){
        return mappingProjectService.selectPrincipalDetail(id);

    }
    /***
     * @Author ftt
     * @Description
     * 新增负责人信息
     * @Date 2020/7/16 18:25
     * @Param [principal]
     * @return com.aaa.eleven.base.ResultData
     */
    @PostMapping("/principal/insertPrincipal")
    public ResultData insertPrincipal(@RequestBody Principal principal){
        return mappingProjectService.insertPrincipal(principal);

    }
    /***
     * @Author ftt
     * @Description
     * 修改负责人信息
     * @Date 2020/7/16 18:25
     * @Param [principal]
     * @return com.aaa.eleven.base.ResultData
     */
    @PostMapping("/principal/updatePrincipal")
    public ResultData updatePrincipal(@RequestBody Principal principal){
        return mappingProjectService.updatePrincipal(principal);

    }
    /***
     * @Author ftt
     * @Description
     * 删除负责人信息
     * @Date 2020/7/16 18:25
     * @Param [principal]
     * @return com.aaa.eleven.base.ResultData
     */
    @PostMapping("/principal/deletePrincipal")
    public ResultData deletePrincipal(@RequestBody Principal principal){
        return mappingProjectService.deletePrincipal(principal);

    }
}
