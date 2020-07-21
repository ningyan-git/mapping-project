package com.aaa.eleven.controller;

import com.aaa.eleven.base.ResultData;
import com.aaa.eleven.model.Dept;
import com.aaa.eleven.service.MappingProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Company
 * @Author ljy
 * @Date Create in 2020/7/16 9:56
 * @Description
 */
@RestController
public class DeptController {
    @Autowired
    private MappingProjectService mappingProjectService;
    /**
     * 批量删除部门信息
     * @param ids
     * @return
     */
    @PostMapping("/dept/deleteBatchByIds")
    public ResultData deleteBatchByIds(@RequestBody List<Integer> ids){
        return mappingProjectService.deleteBatchByIds(ids);
    }

    /**
     * 查询所有部门信息
     * @param curpage
     * @param pagesize
     * @param deptName
     * @return
     */
    @GetMapping("/dept/selectAll")
    public ResultData selectAllDept(@RequestParam(value = "curpage",required = false,defaultValue = "1")int curpage , @RequestParam(value = "pagesize",required = false,defaultValue = "5")int pagesize,@RequestParam(value = "deptName",required = false,defaultValue = "") String deptName){
        return mappingProjectService.selectAllDept(curpage,pagesize,deptName);
    }

    /**
     * 新增部门信息
     * @param dept
     * @return
     */
    @PostMapping("/dept/addDept")
    public ResultData addDept(@RequestBody Dept dept){
        return mappingProjectService.addDept(dept);
    }

    /**
     * 修改部门信息
     * @param dept
     * @return
     */
    @RequestMapping("/dept/updateDept")
    public ResultData updateDetp(@RequestBody Dept dept){
        return mappingProjectService.updateDetp(dept);
    }

    /**
     * 修改部门信息
     * @param dept
     * @return
     */
    @RequestMapping("/dept/deleteDept")
    public ResultData deleteDept(@RequestBody Dept dept){
        return mappingProjectService.deleteDept(dept);
    }





}
