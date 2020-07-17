package com.aaa.eleven.controller;

import com.aaa.eleven.base.BaseService;
import com.aaa.eleven.base.CommonController;
import com.aaa.eleven.base.ResultData;
import com.aaa.eleven.model.Dept;
import com.aaa.eleven.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Company
 * @Author ljy
 * @Date Create in 2020/7/16 9:43
 * @Description
 */
@RequestMapping("/dept")
@RestController
public class DeptController extends CommonController<Dept> {

    @Autowired
    DeptService deptService;
    @Override
    public BaseService<Dept> getBaseService() {
        return deptService;
    }

    @GetMapping("/selectAll")
    public ResultData selectAllDept(@RequestParam(value = "curpage",required = false,defaultValue = "1")int curpage , @RequestParam(value = "pagesize",required = false,defaultValue = "5")int pagesize,@RequestParam(value = "deptName",required = false,defaultValue = "") String deptName){
        List<Map<String, Object>> depts = deptService.selectAllDept(curpage,pagesize,deptName);
        if (depts.size()>0){
            return selectSuccess(depts);
        }
        return selectFailed();
    }
    @PostMapping("/addDept")
    public ResultData addDept(@RequestBody Dept dept){
        if (dept.getParentId() == null){
            return insertFailed("parentId 必须要传参");
        }
        if (dept.getDeptName() == null){
            return insertFailed("deptName 必须要传参");
        }
        Boolean aBoolean = deptService.addDept(dept);
        if (aBoolean){
            return insertSuccess(aBoolean);
        }
        return insertFailed();
    }
    @RequestMapping("/updateDept")
    public ResultData updateDetp(@RequestBody Dept dept){
        Boolean aBoolean = deptService.updateDept(dept);
        if (aBoolean){
            return updateSuccess(aBoolean);
        }
        return updateFailed();
    }
    @RequestMapping("/deleteDept")
    public ResultData deleteDept(@RequestBody Dept dept){
        Boolean aBoolean = deptService.deleteDept(dept);
        if (aBoolean){
            return deleteSuccess(aBoolean);
        }
        return deleteFailed();
    }



}
