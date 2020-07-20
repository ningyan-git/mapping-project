package com.aaa.eleven.controller;

import com.aaa.eleven.base.BaseService;
import com.aaa.eleven.base.CommonController;
import com.aaa.eleven.base.ResultData;
import com.aaa.eleven.model.MappingProject;
import com.aaa.eleven.service.MappingProjectService;
import com.aaa.eleven.vo.MappingProjectAndResultCommitVo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.aaa.eleven.status.Status.*;

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
    @Override
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
    /**
     * 功能描述: <br>
     *@Description
     * 项目管理的操作
     * @Param:
     * @Return:
     * @Author: zh
     * @Date: 2020/7/17 0017 10:57
     */
    /**
     * 功能描述: <br>
     *@Description
     * 查询t_mapping_project表 项目管理
     * @Param: [projectType]
     * @Return: com.github.pagehelper.PageInfo
     * @Author: zh
     * @Date: 2020/7/16 0016 11:06
     */
    @GetMapping("/getMappingProject")
    public ResultData getMappingProject(@RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo, @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize, String projectType){
        PageInfo query = mappingProjectService.query(pageNo, pageSize, projectType);
        if(query != null)
        {
            return selectSuccess(SELECT_SUCCESS.getMsg(),query);
        }else
        {
            return selectFailed(SELECT_FAILED.getMsg());
        }
    }/**
     * 功能描述: <br>
     *@Description
     * 获取条件查询的下拉框的数据
     * @Param: []
     * @Return: com.aaa.eleven.base.ResultData
     * @Author: zh
     * @Date: 2020/7/16 0016 18:27
     */
    @GetMapping("/queryAllProject_type")
    public ResultData queryAllProject_type(){
        List<Map> list = mappingProjectService.queryAllProject_Type();
        System.out.println(list);
        if(list.size()>0)
        {
            return super.selectSuccess(SELECT_SUCCESS.getMsg(),list);
        }
        return super.selectFailed(SELECT_FAILED.getMsg());
    }
    /**
     * 功能描述: <br>
     *@Description
     * 修改项目管理中的数据
     * @Param: [mappingProject]
     * @Return: com.aaa.eleven.base.ResultData
     * @Author: zh
     * @Date: 2020/7/16 0016 18:35
     */
    @PutMapping("/updateMappingProject")
    public ResultData updateMappingProject(MappingProjectAndResultCommitVo mappingProjectAndResultCommitVo){
        System.out.println(mappingProjectAndResultCommitVo);
        List list = mappingProjectService.updateMappingProjectById(mappingProjectAndResultCommitVo);
        if(list.size()==2)
        {
            return super.updateSuccess(UPDATE_SUCCESS.getMsg());
        }else
        {
            super.updateFailed(UPDATE_FAILED.getMsg());
        }
        return null;
    }
    /**
     * 功能描述: <br>
     *@Description
     * 增加项目管理
     * @Param: [mappingProjectAndResultCommitVo]
     * @Return: com.aaa.eleven.base.ResultData
     * @Author: zh
     * @Date: 2020/7/17 0017 8:52
     */
    @PostMapping("/insertMappingProject")
    public ResultData insertMappingProject(MappingProjectAndResultCommitVo mappingProjectAndResultCommitVo){
        System.out.println(mappingProjectAndResultCommitVo);
        List list = mappingProjectService.insertMappingProjectAndResultCommit(mappingProjectAndResultCommitVo);
        if(list.size() == 2)
        {
            return super.insertSuccess(INSERT_SUCCESS.getMsg());
        }else{
            return super.insertFailed(INSERT_FAILED.getMsg());
        }
    }
    /**
     * 功能描述: <br>
     *@Description
     * 删除t_mapping_project并把关联的t_result_commit的数据删掉
     * @Param: [id]
     * @Return: com.aaa.eleven.base.ResultData
     * @Author: zh
     * @Date: 2020/7/16 0016 21:11
     */
    @DeleteMapping("deleteMappingProjectAndResult")
    public ResultData deleteMappingProjectAndResult(Long id){
        List list = mappingProjectService.deleteMappingProjectAndResult(id);
        if(list.size()>2)
        {
            return super.deleteSuccess(DELETE_SUCCESS.getMsg());
        }
        else
        {
            return super.deleteFailed(DELETE_FAILED.getMsg());
        }
    }
    /**
     * 功能描述: <br>
     *@Description
     * 以下是主页里的模糊查询
     * @Param:
     * @Return:
     * @Author: zh
     * @Date: 2020/7/17 0017 10:57
     */
    /**
     * 功能描述: <br>
     *@Description
     * 模糊查询
     * @Param: []
     * @Return: com.aaa.eleven.base.ResultData
     * @Author: zh
     * @Date: 2020/7/17 0017 11:14
     */
    @GetMapping("/selectMappingProjectByProjectNameAndProjectTypeAndStartDate")
    public ResultData selectMappingProjectByProjectNameAndProjectTypeAndStartDate(String projectName,String projectType,String startDate){
        List<Map<String, Object>> maps = mappingProjectService.selectMappingProjectByProjectNameAndProjectTypeAndStartDate(projectName, projectType, startDate);
        if(maps.size()>0)
        {
            return super.selectSuccess(SELECT_SUCCESS.getMsg(),maps);
        }
        return super.selectFailed(SELECT_FAILED.getMsg());
    }
    /**
     * 功能描述: <br>
     *@Description
     * 模糊查询中下拉框的数据  测绘类型
     * @Param: []
     * @Return: com.aaa.eleven.base.ResultData
     * @Author: zh
     * @Date: 2020/7/17 0017 14:56
     */
    @GetMapping("/selectProjectType")
    public ResultData selectProjectType(){
        List<Map<String, Object>> maps = mappingProjectService.selectProjectType();
        if(maps.size()>0)
        {
            return super.selectSuccess(SELECT_SUCCESS.getMsg(),maps);
        }
        else
        {
            return super.selectFailed(SELECT_FAILED.getMsg());
        }
    }
    /**
     * 功能描述: <br>
     *@Description
     * 模糊查询之后获得项目的名称  然后通过名称获取具体信息
     * @Param: [mappingProject]
     * @Return: com.aaa.eleven.base.ResultData
     * @Author: zh
     * @Date: 2020/7/17 0017 15:15
     */
    @GetMapping("/selectMappingProjectByProjectName")
    public ResultData selectMappingProjectByProjectName(MappingProject mappingProject) {
        /*
         * 功能描述: <br>
         *@Description
         * 这边由于数据库有的名字有两个 所以查出来的结果是列表
         * @Param: [mappingProject]
         * @Return: com.aaa.eleven.base.ResultData
         * @Author: zh
         * @Date: 2020/7/17 0017 15:16
         */
        List<MappingProject> mappingProjects = mappingProjectService.selectList(mappingProject);
        if (mappingProjects.size() > 0) {
            return super.selectSuccess(SELECT_SUCCESS.getMsg(), mappingProjects);
        } else {
            return null;
        }
    }

}
