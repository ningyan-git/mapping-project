package com.aaa.eleven.controller;

import com.aaa.eleven.base.BaseService;
import com.aaa.eleven.base.CommonController;
import com.aaa.eleven.base.ResultData;
import com.aaa.eleven.ftp.UploadService;
import com.aaa.eleven.model.MappingProject;
import com.aaa.eleven.service.MappingProjectService;
import com.aaa.eleven.service.ResourceService;
import com.aaa.eleven.vo.MappingProjectAndResultCommitVo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
    @Autowired
    private UploadService uploadService;
    @Autowired
    private ResourceService resourceService;
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
    @PostMapping("/updateMappingProject")
    public ResultData updateMappingProject(MappingProjectAndResultCommitVo mappingProjectAndResultCommitVo){
        System.out.println(mappingProjectAndResultCommitVo);
        int list = mappingProjectService.updateMappingProjectById(mappingProjectAndResultCommitVo,uploadService,resourceService);
        if(list > 0)
        {
            return super.updateSuccess(UPDATE_SUCCESS.getMsg());
        }else
        {
            return super.updateFailed(UPDATE_FAILED.getMsg());
        }

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
        Integer i = mappingProjectService.insertMappingProjectAndResultCommit(mappingProjectAndResultCommitVo,uploadService,resourceService);
        if( i > 0)
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
        int list = mappingProjectService.deleteMappingProjectAndResult(id,resourceService);
        if(list > 0)
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
    public ResultData selectMappingProjectByProjectNameAndProjectTypeAndStartDate(@RequestParam String projectName,@RequestParam String projectType,@RequestParam String startDate){
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
    /**
     * 根据项目 id查询项目详细
     */
    @GetMapping("/mappingProject/selectProjectByID")
    public ResultData selectProjectByID(@RequestParam String id) {
        MappingProject mappingProject = mappingProjectService.selectProjectByID(id);
        if (mappingProject != null) {
            return selectSuccess(mappingProject);
        }
        return selectFailed();
    }

    /**
     * 根据项目名字模糊查询并分页
     *
     * @param projectName
     * @return
     */
    @GetMapping("/mappingProject/selectAllMappingProjectByprojectName")
    public ResultData selectAllMappingProject(@RequestParam String projectName, @RequestParam(value = "curpage", required = false, defaultValue = "1") int curpage, @RequestParam(value = "pagesize", required = false, defaultValue = "5") int pagesize) {
        PageInfo<Map> mapPageInfo = mappingProjectService.selectAllMappingProject(projectName, curpage, pagesize);
        if (mapPageInfo != null) {
            return selectSuccess(mapPageInfo);
        } else {
            return selectFailed();
        }
    }

    /**
     * 根据项目id查询审核记录
     */
    @GetMapping("/mappingProject/selectAuditRecords")
    public ResultData selectAuditRecords(@RequestParam String id, @RequestParam(value = "curpage", required = false, defaultValue = "1") int curpage, @RequestParam(value = "pagesize", required = false, defaultValue = "5") int pagesize) {
        PageInfo<Map> mapPageInfo = mappingProjectService.selectAuditRecords(id, curpage, pagesize);
        if (mapPageInfo != null) {
            return selectSuccess(mapPageInfo);
        } else {
            return selectFailed();
        }
    }


    /**
     * 首先查询出项目的汇交成果状态为0通过的项目，并模糊查询
     * @param curpage
     * @param pagesize
     * @return
     */
    @GetMapping("/mappingProject/selectResultZeroPass")
    public ResultData selectResultZeroPass(@RequestParam(value = "curpage", required = false, defaultValue = "1") int curpage, @RequestParam(value = "pagesize", required = false, defaultValue = "5") int pagesize,@RequestParam(value = "projectName", required = false, defaultValue = "")String projectName){

        PageInfo<Map> mapPageInfo = mappingProjectService.selectResultZeroPass(curpage, pagesize,projectName);
        if (mapPageInfo !=null){
            return selectSuccess(mapPageInfo);
        }else {
            return selectFailed();
        }
    }



    /**
     * 汇交成果信息，的详情，根据项目id查询相应的项目信息，资源表，汇交结果表
     * @param id
     * @return
     */

    @GetMapping("/mappingProject/selectResourceAndResult")
    public ResultData selectResourceAndResult(@RequestParam String id){
        HashMap hashMap = mappingProjectService.selectResourceAndResult(id);
        if (hashMap !=null){
            return selectSuccess(hashMap);
        }else {
            return selectFailed();
        }
    }

    /**
     * 项目审核，首先查出项目审核状态为已提交audit_status=2的项目
     */
    @GetMapping("/mappingProject/selectAuditCommit")
    public ResultData selectAuditCommit(@RequestParam(value = "curpage", required = false, defaultValue = "1") int curpage, @RequestParam(value = "pagesize", required = false, defaultValue = "5") int pagesize,@RequestParam(value = "projectName", required = false, defaultValue = "")String projectName) {

        PageInfo<Map> mapPageInfo = mappingProjectService.selectAuditCommit(curpage, pagesize, projectName);
        if (mapPageInfo != null) {
            return selectSuccess(mapPageInfo);
        }
        return selectFailed();
    }

    /**
     * 项目审核
     */
    @GetMapping("/mappingProject/projectAudit")
    public ResultData projectAudit(@RequestParam HashMap hashMap){
        boolean b = mappingProjectService.projectAudit(hashMap);
        if (b){
            return updateSuccess(b);
        }
        return updateFailed();
    }

    /**
     * 成果汇交审核
     * 首先查出项目成果状态为已提交results_status=2的项目
     */
    @GetMapping("/mappingProject/selectAuditResult")
    public ResultData selectAuditResult(@RequestParam(value = "curpage", required = false, defaultValue = "1") int curpage, @RequestParam(value = "pagesize", required = false, defaultValue = "5") int pagesize,@RequestParam(value = "projectName", required = false, defaultValue = "")String projectName){
        PageInfo<Map> mapPageInfo = mappingProjectService.selectAuditResult(curpage, pagesize, projectName);
        if (mapPageInfo != null) {
            return selectSuccess(mapPageInfo);
        }
        return selectFailed();
    }

    /**
     * 汇交审核
     */
    @GetMapping("/mappingProject/resultAudit")
    public ResultData resultAudit(@RequestParam HashMap hashMap){
        boolean b = mappingProjectService.resultAudit(hashMap);
        if (b){
            return updateSuccess(b);
        }
        return updateFailed();
    }



}
