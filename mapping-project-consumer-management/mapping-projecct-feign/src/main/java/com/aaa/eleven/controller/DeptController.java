package com.aaa.eleven.controller;

import com.aaa.eleven.base.ResultData;
import com.aaa.eleven.model.Dept;
import com.aaa.eleven.service.MappingProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Company
 * @Author ljy
 * @Date Create in 2020/7/16 9:56
 * @Description
 */
@RestController
@RequestMapping("/dept")
public class DeptController {
   /* @Autowired
    private MappingProjectService mappingProjectService;

    *//**
     * 模糊查询分页，部门
     * @param curpage
     * @param pagesize
     * @param deptName
     * @return
     *//*
    @GetMapping("/selectAll")
    public ResultData selectAllDept(@RequestParam(value = "curpage",required = false,defaultValue = "1")int curpage , @RequestParam(value = "pagesize",required = false,defaultValue = "5")int pagesize,@RequestParam(value = "deptName",required = false,defaultValue = "") String deptName){
        ResultData depts = mappingProjectService.selectAllDept(curpage,pagesize,deptName);
        System.out.println(depts);
        return depts;
    }

    *//**
     * 添加部门
     * @param dept
     * @return
     *//*
    @PostMapping("/addDept")
    public ResultData addDept(@RequestBody Dept dept){
        ResultData aBoolean = mappingProjectService.addDept(dept);
        return aBoolean;
    }

    *//**
     * 更新部门
     * @param dept
     * @return
     *//*
    @RequestMapping("/updateDept")
    public ResultData updateDetp(@RequestBody Dept dept){
        ResultData aBoolean = mappingProjectService.updateDetp(dept);
        return aBoolean;
    }

    *//**
     * 删除部门
     * @param dept
     * @return
     *//*
    @RequestMapping("/deleteDept")
    public ResultData deleteDept(@RequestBody Dept dept){
        ResultData aBoolean = mappingProjectService.deleteDept(dept);
        return aBoolean;
    }
*/





}
