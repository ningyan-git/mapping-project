package com.aaa.eleven.controller;

import com.aaa.eleven.base.ResultData;
import com.aaa.eleven.model.MappingProject;
import com.aaa.eleven.service.MappingProjectService;
import com.aaa.eleven.vo.MappingProjectAndResultCommitVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.aaa.eleven.status.Status.*;

/**
 * @Company
 * @Author ftt
 * @Date Create in 2020/7/16 10:55
 * @Description
 */
@RestController
public class MappingProjectController {
    @Autowired
    private MappingProjectService mappingProjectService;
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
        return mappingProjectService.selectMappingProjectList(curpage,pagesize);
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
        return mappingProjectService.getMappingProject(pageNo,pageSize,projectType);
    }
    /**
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
        return mappingProjectService.queryAllProject_type();
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
    public ResultData updateMappingProject(@RequestParam MappingProjectAndResultCommitVo mappingProjectAndResultCommitVo){
       return mappingProjectService.updateMappingProject(mappingProjectAndResultCommitVo);
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
    public ResultData insertMappingProject(@RequestBody MappingProjectAndResultCommitVo mappingProjectAndResultCommitVo){
        return mappingProjectService.insertMappingProject(mappingProjectAndResultCommitVo);
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
    public ResultData deleteMappingProjectAndResult(@RequestParam("id") Long id){
        return mappingProjectService.deleteMappingProjectAndResult(id);
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
    public ResultData selectMappingProjectByProjectNameAndProjectTypeAndStartDate(@RequestParam("projectName") String projectName,@RequestParam("projectType") String projectType,@RequestParam("startDate") String startDate){
        return mappingProjectService.selectMappingProjectByProjectNameAndProjectTypeAndStartDate(projectName, projectType, startDate);
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
        return mappingProjectService.selectProjectType();

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
    public ResultData selectMappingProjectByProjectName(@RequestParam MappingProject mappingProject) {
        return mappingProjectService.selectMappingProjectByProjectName(mappingProject);

    }
    /**
     * 根据项目 id查询项目详细
     */
    @GetMapping("/mappingProject/selectProjectByID")
    public ResultData selectProjectByID(@RequestParam String id) {
        return mappingProjectService.selectProjectByID(id);
    }

    /**
     * 根据项目名字模糊查询并分页
     *
     * @param projectName
     * @return
     */
    @GetMapping("/mappingProject/selectAllMappingProjectByprojectName")
    public ResultData selectAllMappingProject(@RequestParam("projectName") String projectName, @RequestParam(value = "curpage", required = false, defaultValue = "1") int curpage, @RequestParam(value = "pagesize", required = false, defaultValue = "5") int pagesize) {
        return mappingProjectService.selectAllMappingProject(projectName, curpage, pagesize);
    }

    /**
     * 根据项目id查询审核记录
     */
    @GetMapping("/mappingProject/selectAuditRecords")
    public ResultData selectAuditRecords(@RequestParam("id") String id, @RequestParam(value = "curpage", required = false, defaultValue = "1") int curpage, @RequestParam(value = "pagesize", required = false, defaultValue = "5") int pagesize) {
        return mappingProjectService.selectAuditRecords(id, curpage, pagesize);
    }


    /**
     * 首先查询出项目的汇交成果状态为0通过的项目，并模糊查询
     * @param curpage
     * @param pagesize
     * @return
     */
    @GetMapping("/mappingProject/selectResultZeroPass")
    public ResultData selectResultZeroPass(@RequestParam(value = "curpage", required = false, defaultValue = "1") int curpage, @RequestParam(value = "pagesize", required = false, defaultValue = "5") int pagesize,@RequestParam(value = "projectName", required = false, defaultValue = "")String projectName){
        return mappingProjectService.selectResultZeroPass( curpage, pagesize, projectName);
    }

    /**
     * 汇交成果信息，的详情，根据项目id查询相应的项目信息，资源表，汇交结果表
     * @param id
     * @return
     */

    @GetMapping("/mappingProject/selectResourceAndResult")
    public ResultData selectResourceAndResult(@RequestParam("id") String id){
        return mappingProjectService.selectResourceAndResult( id);

    }

    /**
     * 项目审核，首先查出项目审核状态为已提交audit_status=2的项目
     */
    @GetMapping("/mappingProject/selectAuditCommit")
    public ResultData selectAuditCommit(@RequestParam(value = "curpage", required = false, defaultValue = "1") int curpage, @RequestParam(value = "pagesize", required = false, defaultValue = "5") int pagesize,@RequestParam(value = "projectName", required = false, defaultValue = "")String projectName) {
        return mappingProjectService.selectAuditCommit( curpage, pagesize, projectName);

    }

    /**
     * 项目审核
     */
    @GetMapping("/mappingProject/projectAudit")
    public ResultData projectAudit(@RequestParam HashMap hashMap){
        return mappingProjectService.projectAudit(hashMap);

    }

    /**
     * 成果汇交审核
     * 首先查出项目成果状态为已提交results_status=2的项目
     */
    @GetMapping("/mappingProject/selectAuditResult")
    public ResultData selectAuditResult(@RequestParam(value = "curpage", required = false, defaultValue = "1") int curpage, @RequestParam(value = "pagesize", required = false, defaultValue = "5") int pagesize,@RequestParam(value = "projectName", required = false, defaultValue = "")String projectName){
        return mappingProjectService.selectAuditResult(curpage, pagesize, projectName);
    }

    /**
     * 汇交审核
     */
    @GetMapping("/mappingProject/resultAudit")
    public ResultData resultAudit(@RequestParam HashMap hashMap){
        return mappingProjectService.resultAudit(hashMap);
    }


}
