package com.aaa.eleven.service;

import com.aaa.eleven.base.ResultData;
import com.aaa.eleven.model.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Company
 * @Author ftt
 * @Date Create in 2020/7/14 16:40
 * @Description
 */
@FeignClient(value = "mapping-project")
public interface MappingProjectService {
    /***
     * @Author ftt
     * @Description
     * 查询单位信息
     * @Date 2020/7/14 22:03
     * @Param [userId]
     * @return com.aaa.eleven.base.ResultData
     */
    @GetMapping("/mappingUnit/selectOneByUserId")
    ResultData selectOneByUserId(@RequestParam("userId") long userId);

    /***
     * @Author ftt
     * @Description
     * 查询设备信息
     * @Date 2020/7/14 22:04
     * @Param [userId]
     * @return com.aaa.eleven.base.ResultData
     */
    @GetMapping("/equipment/selectEquipmentListByuserId")
    ResultData selectEquipmentListByuserId(@RequestParam(value = "curpage", required = false, defaultValue = "1") int curpage, @RequestParam(value = "pagesize", required = false, defaultValue = "5") int pagesize, @RequestParam("userId") long userId);

    /***
     * @Author ftt
     * @Description
     * 查询负责人信息
     * @Date 2020/7/14 22:04
     * @Param [userId]
     * @return com.aaa.eleven.base.ResultData
     */
    @GetMapping("/principal/selectPrincipalListByUserId")
    ResultData selectPrincipalListByUserId(@RequestParam(value = "curpage", required = false, defaultValue = "1") int curpage, @RequestParam(value = "pagesize", required = false, defaultValue = "5") int pagesize, @RequestParam("userId") long userId);

    /***
     * @Author ftt
     * @Description
     * 查询特殊岗位人员信息
     * @Date 2020/7/14 22:05
     * @Param [userId]
     * @return com.aaa.eleven.base.ResultData
     */
    @GetMapping("/specialPost/selectSpecialPostListByUserId")
    ResultData selectSpecialPostListByUserId(@RequestParam(value = "curpage", required = false, defaultValue = "1") int curpage, @RequestParam(value = "pagesize", required = false, defaultValue = "5") int pagesize, @RequestParam("userId") long userId);

    /***
     * @Author ftt
     * @Description
     * 查询技术人员信息
     * @Date 2020/7/14 22:06
     * @Param [userId]
     * @return com.aaa.eleven.base.ResultData
     */
    @GetMapping("/technicist/selectTechnicistListByUserId")
    ResultData selectTechnicistListByUserId(@RequestParam(value = "curpage", required = false, defaultValue = "1") int curpage, @RequestParam(value = "pagesize", required = false, defaultValue = "5") int pagesize, @RequestParam("userId") long userId);

    /**
     * @return com.aaa.eleven.base.ResultData
     * @Author ftt
     * @Description 执行登录操作
     * @Date 2020/7/15 16:11
     * @Param [user]
     */
    @PostMapping("/doLogin")
    ResultData doLogin(@RequestBody User user);

    /***
     * @Author ftt
     * @Description
     *  新增登录日志
     * @Date 2020/7/15 17:12
     * @Param [loginLog]
     * @return java.lang.Integer
     */
    @PostMapping("/addLoginLog")
    Integer addLoginLog(@RequestBody LoginLog loginLog);

    /***
     * @Author ftt
     * @Description
     * 根据id查询负责人详细信息
     * @Date 2020/7/15 20:57
     * @Param [id]
     * @return com.aaa.eleven.base.ResultData
     */
    @GetMapping("/principal/selectPrincipalDetail")
    ResultData selectPrincipalDetail(@RequestParam("id") Long id);

    /***
     * @Author ftt
     * @Description
     * 根据id查询Technicist表的信息
     * @Date 2020/7/16 8:39
     * @Param [id]
     * @return com.aaa.eleven.base.ResultData
     */
    @GetMapping("/technicist/selectTechnicistDetail")
    ResultData selectTechnicistDetail(@RequestParam("id") Long id );

    /***
     * @Author ftt
     * @Description
     * 根据id 查询设备
     * @Date 2020/7/16 9:41
     * @Param [id]
     * @return com.aaa.eleven.base.ResultData
     */
    @GetMapping("/equipment/selectEquipmentdetail")
    ResultData selectEquipmentdetail(@RequestParam("id") Long id);
    /***
     * @Author ftt
     * @Description
     * 查询特殊岗位人员信息
     * @Date 2020/7/16 12:18
     * @Param [id]
     * @return com.aaa.eleven.base.ResultData
     */
    @GetMapping("/specialPost/selectSpecialPostDetail")
    public ResultData selectSpecialPostDetail(@RequestParam("id") Long id);
    /***
     * @Author ftt
     * @Description
     * 分页查询MappingProject 列表
     * @Date 2020/7/16 10:33
     * @Param [curpage, pagesize]
     * @return com.github.pagehelper.PageInfo
     */
    @GetMapping("/mappingProject/selectMappingProjectList")
    ResultData selectMappingProjectList(@RequestParam(value = "curpage", required = false, defaultValue = "1") int curpage, @RequestParam(value = "pagesize", required = false, defaultValue = "5") int pagesize);

    /***
     * @Author ftt
     * @Description
     * 单位列表 根据 单位名称进行模糊查询
     * @Date 2020/7/16 14:59
     * @Param [curpage, pagesize, unitName]
     * @return com.aaa.eleven.base.ResultData
     */
    @GetMapping("/mappingUnit/selectMappingUnitByConditions")
    ResultData selectMappingUnitByConditions(@RequestParam(value = "curpage",required = false,defaultValue = "1")int curpage, @RequestParam(value = "pagesize",required = false,defaultValue = "5")int pagesize,@RequestParam("unitName") String unitName);
    /***
     * @Author ftt
     * @Description
     * 修改 单位信息
     * @Date 2020/7/16 16:46
     * @Param [mappingUnit]
     * @return com.aaa.eleven.base.ResultData
     */
    @PostMapping("/mappingUnit/updateMappingUnit")
    ResultData updateMappingUnit(@RequestBody MappingUnit mappingUnit);
    /***
     * @Author ftt
     * @Description
     * 新增 单位信息
     * @Date 2020/7/16 16:48
     * @Param [mappingUnit]
     * @return com.aaa.eleven.base.ResultData
     */
    @PostMapping("/mappingUnit/insertMappingUnit")
    ResultData insertMappingUnit(@RequestBody MappingUnit mappingUnit);
    /***
     * @Author ftt
     * @Description
     * 删除单位信息
     * @Date 2020/7/16 16:50
     * @Param [mappingUnit]
     * @return com.aaa.eleven.base.ResultData
     */
    @PostMapping("/mappingUnit/deleteMappingUnit")
    ResultData deleteMappingUnit(@RequestBody MappingUnit mappingUnit);

    /***
     * @Author ftt
     * @Description
     * 新增设备信息
     * @Date 2020/7/16 17:18
     * @Param [equipment]
     * @return com.aaa.eleven.base.ResultData
     */
    @PostMapping("/equipment/insertEquipment")
    ResultData insertEquipment(@RequestBody Equipment equipment);
    /***
     * @Author ftt
     * @Description
     * 修改设备信息
     * @Date 2020/7/16 17:18
     * @Param [equipment]
     * @return com.aaa.eleven.base.ResultData
     */
    @PostMapping("/equipment/updateEquipment")
    ResultData updateEquipment(@RequestBody Equipment equipment);
    /***
     * @Author ftt
     * @Description
     * 删除设备信息
     * @Date 2020/7/16 17:18
     * @Param [equipment]
     * @return com.aaa.eleven.base.ResultData
     */
    @PostMapping("/equipment/deleteEquipment")
    ResultData deleteEquipment(@RequestBody Equipment equipment);
    /***
     * @Author ftt
     * @Description
     * 新增负责人信息
     * @Date 2020/7/16 18:25
     * @Param [principal]
     * @return com.aaa.eleven.base.ResultData
     */
    @PostMapping("/principal/insertPrincipal")
    ResultData insertPrincipal(@RequestBody Principal principal);
    /***
     * @Author ftt
     * @Description
     * 修改负责人信息
     * @Date 2020/7/16 18:25
     * @Param [principal]
     * @return com.aaa.eleven.base.ResultData
     */
    @PostMapping("/principal/updatePrincipal")
    ResultData updatePrincipal(@RequestBody Principal principal);
    /***
     * @Author ftt
     * @Description
     * 删除负责人信息
     * @Date 2020/7/16 18:25
     * @Param [principal]
     * @return com.aaa.eleven.base.ResultData
     */
    @PostMapping("/principal/deletePrincipal")
    ResultData deletePrincipal(@RequestBody Principal principal);
    /***
     * @Author ftt
     * @Description
     * 新增特殊岗位人员信息
     * @Date 2020/7/16 18:32
     * @Param [specialPost]
     * @return com.aaa.eleven.base.ResultData
     */
    @PostMapping("/specialPost/insertSpecialPost")
    ResultData insertSpecialPost(@RequestBody SpecialPost specialPost);
    /***
     * @Author ftt
     * @Description
     * 修改特殊岗位人员信息
     * @Date 2020/7/16 18:32
     * @Param [specialPost]
     * @return com.aaa.eleven.base.ResultData
     */
    @PostMapping("/specialPost/updateSpecialPost")
    ResultData updateSpecialPost(@RequestBody SpecialPost specialPost);
    /***
     * @Author ftt
     * @Description
     * 删除特殊岗位人员信息
     * @Date 2020/7/16 18:32
     * @Param [specialPost]
     * @return com.aaa.eleven.base.ResultData
     */
    @PostMapping("/specialPost/deleteSpecialPost")
    ResultData deleteSpecialPost(@RequestBody SpecialPost specialPost);
    /***
     * @Author ftt
     * @Description
     * 新增技术人员信息
     * @Date 2020/7/16 18:39
     * @Param [technicist]
     * @return com.aaa.eleven.base.ResultData
     */
    @PostMapping("/technicist/insertTechnicist")
    ResultData insertTechnicist(@RequestBody Technicist technicist);
    /***
     * @Author ftt
     * @Description
     * 修改技术人员信息
     * @Date 2020/7/16 18:39
     * @Param [technicist]
     * @return com.aaa.eleven.base.ResultData
     */
    @PostMapping("/technicist/updateTechnicist")
    ResultData updateTechnicist(@RequestBody Technicist technicist);
    /***
     * @Author ftt
     * @Description
     * 删除技术人员信息
     * @Date 2020/7/16 18:39
     * @Param [technicist]
     * @return com.aaa.eleven.base.ResultData
     */
    @PostMapping("/technicist/deleteTechnicist")
    ResultData deleteTechnicist(@RequestBody Technicist technicist);
}