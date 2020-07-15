package com.aaa.eleven.service;

import com.aaa.eleven.base.ResultData;
import com.aaa.eleven.model.LoginLog;
import com.aaa.eleven.model.User;
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
    public ResultData selectOneByUserId(@RequestParam("userId") long userId);
    /***
     * @Author ftt
     * @Description
     * 查询设备信息
     * @Date 2020/7/14 22:04
     * @Param [userId]
     * @return com.aaa.eleven.base.ResultData
     */
    @GetMapping("/equipment/selectEquipmentListByuserId")
    public ResultData selectEquipmentListByuserId(@RequestParam("userId") long userId);
    /***
     * @Author ftt
     * @Description
     * 查询负责人信息
     * @Date 2020/7/14 22:04
     * @Param [userId]
     * @return com.aaa.eleven.base.ResultData
     */
    @GetMapping("/principal/selectPrincipalListByUserId")
    public ResultData selectPrincipalListByUserId(@RequestParam("userId") long userId);
    /***
     * @Author ftt
     * @Description
     * 查询特殊岗位人员信息
     * @Date 2020/7/14 22:05
     * @Param [userId]
     * @return com.aaa.eleven.base.ResultData
     */
    @GetMapping("/specialPost/selectSpecialPostListByUserId")
    public ResultData selectSpecialPostListByUserId(@RequestParam("userId") long userId);
    /***
     * @Author ftt
     * @Description
     * 查询技术人员信息
     * @Date 2020/7/14 22:06
     * @Param [userId]
     * @return com.aaa.eleven.base.ResultData
     */
    @GetMapping("/technicist/selectTechnicistListByUserId")
    public ResultData selectTechnicistListByUserId(@RequestParam("userId") long userId);
    /**
     * @Author ftt
     * @Description
     * 执行登录操作
     * @Date 2020/7/15 16:11
     * @Param [user]
     * @return com.aaa.eleven.base.ResultData
     */
    @PostMapping("/doLogin")
    public ResultData doLogin(@RequestBody User user);
    /***
     * @Author ftt
     * @Description
     *  新增登录日志
     * @Date 2020/7/15 17:12
     * @Param [loginLog]
     * @return java.lang.Integer
     */
    @PostMapping("/addLoginLog")
    public Integer addLoginLog(@RequestBody LoginLog loginLog);
}
