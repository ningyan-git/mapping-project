package com.aaa.eleven.service;

import com.aaa.eleven.base.ResultData;
import com.aaa.eleven.model.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

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

   /* *//**
     * 查询所有字典
     * @param curpage
     * @param pagesize
     * @param tableName
     * @param fieldName
     * @param keyy
     * @return
     *//*
    @GetMapping("/dict/selectAll")
    public ResultData selectAllDict(@RequestParam(value = "curpage",required = false,defaultValue = "1")int curpage , @RequestParam(value = "pagesize",required = false,defaultValue = "5")int pagesize, @RequestParam("tableName") String tableName, @RequestParam("fieldName") String fieldName, @RequestParam("keyy") String keyy);
    *//**
     * 添加字典
     * @param dict
     * @return
     *//*
    @PostMapping("/dict/addDict")
    public ResultData addDict(@RequestBody Dict dict);
    *//**
     * 根据id更新字典
     * @param dict
     * @return
     *//*
    @RequestMapping("/dict/updateDict")
    public ResultData updateDict(@RequestBody Dict dict);
    *//**
     * 删除
     * @param dict
     * @return
     *//*
    @RequestMapping("/dict/deleteDict")
    public ResultData deleteDict(@RequestBody Dict dict);

    *//**
     * 模糊查询分页，部门
     * @param curpage
     * @param pagesize
     * @param deptName
     * @return
     *//*
    @GetMapping("/dept/selectAll")
    public ResultData selectAllDept(@RequestParam(value = "curpage",required = false,defaultValue = "1")int curpage , @RequestParam(value = "pagesize",required = false,defaultValue = "5")int pagesize,@RequestParam(value = "deptName",required = false,defaultValue = "") String deptName);

    *//**
     * 添加部门
     * @param dept
     * @return
     *//*
    @PostMapping("/dept/addDept")
    public ResultData addDept(@RequestBody Dept dept);

    *//**
     * 更新部门
     * @param dept
     * @return
     *//*
    @RequestMapping("/dept/updateDept")
    public ResultData updateDetp(@RequestBody Dept dept);

    *//**
     * 删除部门
     * @param dept
     * @return
     *//*
    @RequestMapping("/dept/deleteDept")
    public ResultData deleteDept(@RequestBody Dept dept);

    *//**
     * 模糊查询，分页新闻
     * @param curpage
     * @param pagesize
     * @param title
     * @return
     *//*
    @GetMapping("/news/selectAll")
    public ResultData selectAllNews(@RequestParam(value = "curpage",required = false,defaultValue = "1")int curpage , @RequestParam(value = "pagesize",required = false,defaultValue = "5")int pagesize,@RequestParam("title") String title);
    *//**
     * 添加
     * @param news
     * @return
     *//*
    @PostMapping("/news/addNews")
    public ResultData addNews(@RequestBody News news);
    *//**
     * 更新
     * @param news
     * @return
     *//*
    @RequestMapping("/news/updateNews")
    public ResultData updateNews(@RequestBody News news);
    *//**
     * 删除
     * @param news
     * @return
     *//*
    @RequestMapping("/news/deleteNews")
    public ResultData deleteNews(@RequestBody News news);*/



}
