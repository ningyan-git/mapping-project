package com.aaa.eleven.controller;

import com.aaa.eleven.base.BaseService;
import com.aaa.eleven.base.CommonController;
import com.aaa.eleven.base.ResultData;
import com.aaa.eleven.model.MappingProject;
import com.aaa.eleven.service.MappingProjectService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Company
 * @Author ftt
 * @Date Create in 2020/7/16 10:31
 * @Description
 */
@RestController
public class MappingProjectController extends CommonController<MappingProject> {
    @Autowired
    private MappingProjectService mappingProjectService;
    public BaseService<MappingProject> getBaseService() {
        return mappingProjectService;
    }
    /***
     * @Author ftt
     * @Description
     * 分页查询MappingProject 列表
     * @Date 2020/7/16 10:33
     * @Param [curpage, pagesize]
     * @return com.github.pagehelper.PageInfo
     */
    @GetMapping("/mappingProject/selectMappingProjectList")
    public ResultData selectMappingProjectList(@RequestParam(value = "curpage",required = false,defaultValue = "1")int curpage, @RequestParam(value = "pagesize",required = false,defaultValue = "5")int pagesize){
        PageInfo pageInfo = mappingProjectService.selectMappingProjectList(curpage, pagesize);
        if(null != pageInfo){
            return selectSuccess(pageInfo);
        }else {
            return selectFailed();
        }
    }
    /***
     * @Author ftt
     * @Description
     * 根据userid查询项目 + 项目类型
     * @Date 2020/7/18 10:20
     * @Param [map]
     * @return com.aaa.eleven.base.ResultData
     */
    @GetMapping("/mappingProject/selectByUserId")
    public ResultData selectByUserId(@RequestParam Map map){
        PageInfo<MappingProject> pageInfo = mappingProjectService.selectByUserId(map);
        if(null != pageInfo){
            return selectSuccess(pageInfo);
        }else {
            return selectFailed();
        }
    }
    /***
     * @Author ftt
     * @Description
     * 新增mappingProject
     * @Date 2020/7/18 10:31
     * @Param [mappingProject]
     * @return com.aaa.eleven.base.ResultData
     */
    @PostMapping("/mappingProject/insertMappingProject")
    public ResultData insertMappingProject(@RequestBody MappingProject mappingProject){
        Boolean flag = mappingProjectService.insertMappingProject(mappingProject);
        if(flag){
            return insertSuccess();
        }else {
            return insertFailed();
        }
    }
    /***
     * @Author ftt
     * @Description
     * 修改mappingProject
     * @Date 2020/7/18 10:31
     * @Param [mappingProject]
     * @return com.aaa.eleven.base.ResultData
     */
    @PostMapping("/mappingProject/updateMappingProject")
    public ResultData updateMappingProject(@RequestBody MappingProject mappingProject){
        Boolean flag = mappingProjectService.updateMappingProject(mappingProject);
        if(flag){
            return updateSuccess();
        }else {
            return updateFailed();
        }
    }
    /***
     * @Author ftt
     * @Description
     * 删除mappingProject
     * @Date 2020/7/18 10:31
     * @Param [mappingProject]
     * @return com.aaa.eleven.base.ResultData
     */
    @PostMapping("/mappingProject/deleteMappingProject")
    public ResultData deleteMappingProject(@RequestBody MappingProject mappingProject){
        Boolean flag = mappingProjectService.deleteMappingProject(mappingProject);
        if(flag){
            return deleteSuccess();
        }else {
            return deleteFailed();
        }
    }
}
