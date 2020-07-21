package com.aaa.eleven.service;

import com.aaa.eleven.base.ResultData;
import com.aaa.eleven.model.*;
import com.aaa.eleven.vo.MappingProjectAndResultCommitVo;
import com.aaa.eleven.vo.TokenVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.aaa.eleven.status.Status.*;
import static com.aaa.eleven.status.Status.LOGIN_FAILED;

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
     * 查看审核记录
     *  单位审核  根据 unitName 查看审核记录
     * @Date 2020/7/17 15:24
     * @Param [map]
     * @return com.aaa.eleven.base.ResultData
     */
    @GetMapping("/audit/selectAuditRecordList")
    public ResultData selectAuditRecordList(@RequestParam Map map);

    /***
     * @Author ftt
     * @Description
     * 分页 + 单位查询 + 模糊
     *      单位审核
     *          单位列表(已审核)
     * @Date 2020/7/17 15:24
     * @Param [map]
     * @return com.aaa.eleven.base.ResultData
     */
    @GetMapping("/audit/selectMappingUnitAuditList")
    public ResultData selectMappingUnitAuditList(@RequestParam Map map);
    /***
     * @Author ftt
     * @Description
     * 分页 + 单位查询 + 模糊
     *      单位审核
     *          单位列表(已审核)
     * @Date 2020/7/17 15:24
     * @Param [map]
     * @return com.aaa.eleven.base.ResultData
     */
    @GetMapping("/audit/selectMappingUnitAuditListByStatus")
    public ResultData selectMappingUnitAuditListByStatus(@RequestParam Map map);
    /***
     * @Author ftt
     * @Description
     * 单位注册待审核
     *  列表
     *   模糊 + 分页
     * @Date 2020/7/17 15:24
     * @Param [map]
     * @return com.aaa.eleven.base.ResultData
     */
    @GetMapping("/audit/selectAuditForUnitInfo")
    public ResultData selectAuditForUnitInfo(@RequestParam Map map);
    /***
     * @Author ftt
     * @Description
     * 修改单位审核状态
     *   单位修改待审核的操作
     * @Date 2020/7/17 21:59
     * @Param [map]
     * @return com.aaa.eleven.base.ResultData
     */
    @GetMapping("/audit/updateUnitAuditStatus")
    public ResultData updateUnitAuditStatus(@RequestParam Map map);
    /***
     * @Author ftt
     * @Description
     * 单位注册待审核
     *      单位注册待审核 的操作
     * @Date 2020/7/18 8:57
     * @Param [map]
     * @return com.aaa.eleven.base.ResultData
     */
    @GetMapping("/audit/updateUnitRegisterStatus")
    public ResultData updateUnitRegisterStatus(@RequestParam Map map);

    /***
     * @Author ftt
     * @Description
     * 查询抽查人员具体信息
     * @Date 2020/7/17 18:53
     * @Param [id]
     * @return com.aaa.eleven.base.ResultData
     */
    @GetMapping("/checkPerson/selectCheckPerson")
    public ResultData selectCheckPerson(@RequestParam("id") Long id);
    /***
     * @Author ftt
     * @Description
     * 新增抽查人员
     * @Date 2020/7/17 18:49
     * @Param [checkPerson]
     * @return com.aaa.eleven.base.ResultData
     */
    @PostMapping("/checkPerson/insertCheckPerson")
    public ResultData insertCheckPerson(@RequestBody  CheckPerson checkPerson);
    /***
     * @Author ftt
     * @Description
     * 修改抽查人员
     * @Date 2020/7/17 18:49
     * @Param [checkPerson]
     * @return com.aaa.eleven.base.ResultData
     */
    @PostMapping("/checkPerson/updateCheckPerson")
    ResultData updateCheckPerson(@RequestBody  CheckPerson checkPerson);
    /***
     * @Author ftt
     * @Description
     * 删除抽查人员
     * @Date 2020/7/17 18:49
     * @Param [checkPerson]
     * @return com.aaa.eleven.base.ResultData
     */
    @PostMapping("/checkPerson/deleteCheckPerson")
    ResultData deleteCheckPerson(@RequestBody  CheckPerson checkPerson);
    /**
     * 批量删除部门信息
     * @param ids
     * @return
     */
    @PostMapping("/dept/deleteBatchByIds")
    public ResultData deleteBatchByIds(@RequestBody List<Integer> ids);
    /**
     * 查询所有部门信息
     * @param curpage
     * @param pagesize
     * @param deptName
     * @return
     */
    @GetMapping("/dept/selectAll")
    public ResultData selectAllDept(@RequestParam(value = "curpage",required = false,defaultValue = "1")int curpage , @RequestParam(value = "pagesize",required = false,defaultValue = "5")int pagesize,@RequestParam(value = "deptName",required = false,defaultValue = "") String deptName);

    /**
     * 新增部门信息
     * @param dept
     * @return
     */
    @PostMapping("/dept/addDept")
    public ResultData addDept(@RequestBody Dept dept);

    /**
     * 修改部门信息
     * @param dept
     * @return
     */
    @RequestMapping("/dept/updateDept")
    public ResultData updateDetp(@RequestBody Dept dept);

    /**
     * 修改部门信息
     * @param dept
     * @return
     */
    @RequestMapping("/dept/deleteDept")
    public ResultData deleteDept(@RequestBody Dept dept);
    /**
     * 批量删除字典信息
     * @param ids
     * @return
     */
    @PostMapping("/dict/deleteBatchDictByIds")
    public ResultData deleteBatchDictByIds(@RequestBody List<Integer> ids);

    /**
     * 分页 + 模糊 + 查询所有
     * @param curpage
     * @param pagesize
     * @param tableName
     * @param fieldName
     * @param keyy
     * @return
     */
    @GetMapping("/dict/selectAll")
    public ResultData selectAllDict(@RequestParam(value = "curpage",required = false,defaultValue = "1")int curpage , @RequestParam(value = "pagesize",required = false,defaultValue = "5")int pagesize, @RequestParam String tableName, @RequestParam String fieldName, @RequestParam String keyy);

    /**
     * 新增字典表信息
     * @param dict
     * @return
     */
    @PostMapping("/dict/addDict")
    public ResultData addDict(@RequestBody Dict dict);

    /**
     * 修改字典表
     * @param dict
     * @return
     */
    @RequestMapping("/dict/updateDict")
    public ResultData updateDict(@RequestBody Dict dict);

    /**
     * 删除字典表信息
     * @param dict
     * @return
     */
    @RequestMapping("/dict/deleteDict")
    public ResultData deleteDict(@RequestBody Dict dict);
    /***
     * @Author ftt
     * @Description
     * 查询设备列表
     * @Date 2020/7/14 21:46
     * @Param [userId]
     * @return com.aaa.eleven.base.ResultData
     */
    @GetMapping("/equipment/selectEquipmentListByuserId")
    public ResultData selectEquipmentListByuserId(@RequestParam(value = "curpage",required = false,defaultValue = "1")int curpage, @RequestParam(value = "pagesize",required = false,defaultValue = "5")int pagesize,@RequestParam("userId") long userId);

    /***
     * @Author ftt
     * @Description
     * 根据id 查询设备
     * @Date 2020/7/16 9:41
     * @Param [id]
     * @return com.aaa.eleven.base.ResultData
     */
    @GetMapping("/equipment/selectEquipmentdetail")
    public ResultData selectEquipmentdetail(@RequestParam("id") Long id);
    /***
     * @Author ftt
     * @Description
     * 新增设备信息
     * @Date 2020/7/16 17:18
     * @Param [equipment]
     * @return com.aaa.eleven.base.ResultData
     */
    @PostMapping("/equipment/insertEquipment")
    public ResultData insertEquipment(@RequestBody Equipment equipment);
    /***
     * @Author ftt
     * @Description
     * 修改设备信息
     * @Date 2020/7/16 17:18
     * @Param [equipment]
     * @return com.aaa.eleven.base.ResultData
     */
    @PostMapping("/equipment/updateEquipment")
    public ResultData updateEquipment(@RequestBody Equipment equipment);
    /***
     * @Author ftt
     * @Description
     * 删除设备信息
     * @Date 2020/7/16 17:18
     * @Param [equipment]
     * @return com.aaa.eleven.base.ResultData
     */
    @PostMapping("/equipment/deleteEquipment")
    public ResultData deleteEquipment(@RequestBody Equipment equipment);
    /***
     * @Author ftt
     * @Description
     * 执行登录操作
     * @Date 2020/7/15 16:10
     * @Param [user]
     * @return com.aaa.eleven.base.ResultData
     */
    @PostMapping("/doLogin")
    public ResultData doLogin(@RequestBody User user);
    /***
     * @Author ftt
     * @Description
     * 新增日志
     * @Date 2020/7/15 17:11
     * @Param [loginLog]
     * @return java.lang.Integer
     */
    @PostMapping("/addLoginLog")
    public Integer addLoginLog(@RequestBody LoginLog loginLog);
    /***
     * @Author ftt
     * @Description
     * 分页查询MappingProject 列表
     * @Date 2020/7/16 10:33
     * @Param [curpage, pagesize]
     * @return com.github.pagehelper.PageInfo
     */
    @GetMapping("/mappingProject/selectMappingProjectList")
    public ResultData selectMappingProjectList(@RequestParam(value = "curpage",required = false,defaultValue = "1")int curpage, @RequestParam(value = "pagesize",required = false,defaultValue = "5")int pagesize);
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
    public ResultData getMappingProject(@RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo, @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize, String projectType);
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
    public ResultData queryAllProject_type();
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
    public ResultData updateMappingProject(MappingProjectAndResultCommitVo mappingProjectAndResultCommitVo);
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
    public ResultData insertMappingProject(MappingProjectAndResultCommitVo mappingProjectAndResultCommitVo);
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
    public ResultData deleteMappingProjectAndResult(Long id);
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
    public ResultData selectMappingProjectByProjectNameAndProjectTypeAndStartDate(String projectName,String projectType,String startDate);

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
    public ResultData selectProjectType();
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
    public ResultData selectMappingProjectByProjectName(MappingProject mappingProject) ;
    /**
     * 根据项目 id查询项目详细
     */
    @GetMapping("/mappingProject/selectProjectByID")
    public ResultData selectProjectByID(@RequestParam String id) ;

    /**
     * 根据项目名字模糊查询并分页
     *
     * @param projectName
     * @return
     */
    @GetMapping("/mappingProject/selectAllMappingProjectByprojectName")
    public ResultData selectAllMappingProject(@RequestParam String projectName, @RequestParam(value = "curpage", required = false, defaultValue = "1") int curpage, @RequestParam(value = "pagesize", required = false, defaultValue = "5") int pagesize) ;


    /**
     * 根据项目id查询审核记录
     */
    @GetMapping("/mappingProject/selectAuditRecords")
    public ResultData selectAuditRecords(@RequestParam String id, @RequestParam(value = "curpage", required = false, defaultValue = "1") int curpage, @RequestParam(value = "pagesize", required = false, defaultValue = "5") int pagesize);
    /**
     * 首先查询出项目的汇交成果状态为0通过的项目，并模糊查询
     * @param curpage
     * @param pagesize
     * @return
     */
    @GetMapping("/mappingProject/selectResultZeroPass")
    public ResultData selectResultZeroPass(@RequestParam(value = "curpage", required = false, defaultValue = "1") int curpage, @RequestParam(value = "pagesize", required = false, defaultValue = "5") int pagesize,@RequestParam(value = "projectName", required = false, defaultValue = "")String projectName);
    /**
     * 汇交成果信息，的详情，根据项目id查询相应的项目信息，资源表，汇交结果表
     * @param id
     * @return
     */

    @GetMapping("/mappingProject/selectResourceAndResult")
    public ResultData selectResourceAndResult(@RequestParam String id);

    /**
     * 项目审核，首先查出项目审核状态为已提交audit_status=2的项目
     */
    @GetMapping("/mappingProject/selectAuditCommit")
    public ResultData selectAuditCommit(@RequestParam(value = "curpage", required = false, defaultValue = "1") int curpage, @RequestParam(value = "pagesize", required = false, defaultValue = "5") int pagesize,@RequestParam(value = "projectName", required = false, defaultValue = "")String projectName) ;

    /**
     * 项目审核
     */
    @GetMapping("/mappingProject/projectAudit")
    public ResultData projectAudit(@RequestParam HashMap hashMap);

    /**
     * 成果汇交审核
     * 首先查出项目成果状态为已提交results_status=2的项目
     */
    @GetMapping("/mappingProject/selectAuditResult")
    public ResultData selectAuditResult(@RequestParam(value = "curpage", required = false, defaultValue = "1") int curpage, @RequestParam(value = "pagesize", required = false, defaultValue = "5") int pagesize,@RequestParam(value = "projectName", required = false, defaultValue = "")String projectName);

    /**
     * 汇交审核
     */
    @GetMapping("/mappingProject/resultAudit")
    public ResultData resultAudit(@RequestParam HashMap hashMap);

    /***
     * @Author ftt
     * @Description
     * 查询单个单位信息
     *  根据 userid  用于单位基本信息
     * @Date 2020/7/14 16:36
     * @Param [userId]
     * @return com.aaa.eleven.base.ResultData
     */
    @GetMapping("/mappingUnit/selectOneByUserId")
    public ResultData selectOneByUserId(@RequestParam("userId") long userId);
    /***
     * @Author ftt
     * @Description
     * 查询单个单位信息
     *   根据unitID 用于单位审核的查看
     * @Date 2020/7/14 16:36
     * @Param [userId]
     * @return com.aaa.eleven.base.ResultData
     */
    @GetMapping("/mappingUnit/selectOneById")
    public ResultData selectOneById(@RequestParam("id") long id);
    /***
     * @Author ftt
     * @Description
     * 单位列表 根据 单位名称进行模糊查询
     * @Date 2020/7/16 14:59
     * @Param [curpage, pagesize, unitName]
     * @return com.aaa.eleven.base.ResultData
     */
    @GetMapping("/mappingUnit/selectMappingUnitByConditions")
    public ResultData selectMappingUnitByConditions(@RequestParam(value = "curpage",required = false,defaultValue = "1")int curpage, @RequestParam(value = "pagesize",required = false,defaultValue = "5")int pagesize,@RequestParam("unitName") String unitName);

    /***
     * @Author ftt
     * @Description
     * 修改 单位信息
     * @Date 2020/7/16 16:46
     * @Param [mappingUnit]
     * @return com.aaa.eleven.base.ResultData
     */
    @PostMapping("/mappingUnit/updateMappingUnit")
    public ResultData updateMappingUnit(@RequestBody MappingUnit mappingUnit);
    /***
     * @Author ftt
     * @Description
     * 新增 单位信息
     *  也就是说是注册单位信息
     * @Date 2020/7/16 16:48
     * @Param [mappingUnit]
     * @return com.aaa.eleven.base.ResultData
     */
    @PostMapping("/mappingUnit/insertMappingUnit")
    public ResultData insertMappingUnit(@RequestBody MappingUnit mappingUnit);
    /***
     * @Author ftt
     * @Description
     * 删除单位信息
     * @Date 2020/7/16 16:50
     * @Param [mappingUnit]
     * @return com.aaa.eleven.base.ResultData
     */
    @PostMapping("/mappingUnit/deleteMappingUnit")
    public ResultData deleteMappingUnit(@RequestBody MappingUnit mappingUnit);
    /***
     * @Author ftt
     * @Description
     * 黑白名单
     * @Date 2020/7/17 17:10
     * @Param [map]
     * @return com.aaa.eleven.base.ResultData
     */
    @GetMapping("/mappingUnit/blackAndWhitelist")
    public ResultData blackAndWhitelist(@RequestParam Map map);
    /***
     * @Author ftt
     * @Description
     * 随机按照比例并且按照区抽查单位
     * @Date 2020/7/17 17:43
     * @Param [map]
     * @return com.aaa.eleven.base.ResultData
     */
    @GetMapping("/mappingUnit/unitSpotCheckList")
    public ResultData unitSpotCheckList(@RequestParam Map map);
    /***
     * @Author ftt
     * @Description
     * 抽查人员
     * @Date 2020/7/17 18:24
     * @Param [map]
     * @return com.aaa.eleven.base.ResultData
     */
    @GetMapping("/mappingUnit/selectPersonCheck")
    public ResultData selectPersonCheck(@RequestParam Map map);
    /**
     * 功能描述: <br>
     *@Description
     * 主页的模糊查询
     * @Param:
     * @Return:
     * @Author: zh
     * @Date: 2020/7/17 0017 10:08
     */
    @GetMapping("/selectByUnitNameAndOwnedDistrictAndQualificationLevel")
    public ResultData selectByUnitNameAndOwnedDistrictAndQualificationLevel(String unitName,String ownedDistrict,String qualificationLevel);

    /**
     * 功能描述: <br>
     *@Description
     * 模糊查询的下拉框数据  单位资质
     * @Param: []
     * @Return: com.aaa.eleven.base.ResultData
     * @Author: zh
     * @Date: 2020/7/17 0017 10:40
     */
    @PostMapping("/selectMappingUnitQualificationLevel")
    public ResultData selectMappingUnitQualificationLevel();
    /**
     * 功能描述: <br>
     *@Description
     * 模糊查询的下拉框数据  单位地域
     * @Param: []
     * @Return: com.aaa.eleven.base.ResultData
     * @Author: zh
     * @Date: 2020/7/17 0017 10:44
     */
    @PostMapping("/selectMappingUnitOwnedDistrict")
    public ResultData selectMappingUnitOwnedDistrict();
    /***
     * @Author ftt
     * @Description
     * 提交审核
     * @Date 2020/7/20 19:14
     * @Param [id]
     * @return com.aaa.eleven.base.ResultData
     */
    @GetMapping("/mappingUnit/submitAudit")
    public ResultData submitAudit(@RequestParam("id") Long id);

    /**
     * 功能描述: <br>
     *@Description
     * 一级菜单查询
     * @Param: []
     * @Return: com.aaa.eleven.base.ResultData
     * @Author: zh
     * @Date: 2020/7/17 0017 17:00
     */
    @PostMapping("/selectFirstMenu")
    public ResultData selectFirstMenu(String menuName, String startTime, String endTime);
    /**
     * 功能描述: <br>
     *@Description
     * 获得的二级菜单
     * @Param: [menu]
     * @Return: com.aaa.eleven.base.ResultData
     * @Author: zh
     * @Date: 2020/7/17 0017 17:15
     */
    @PostMapping("/selectSecondMenu")
    public ResultData selectSecond(Menu menu);
    /**
     * 功能描述: <br>
     *@Description
     * 增加菜单的方法
     * @Param: [menu]
     * @Return: com.aaa.eleven.base.ResultData
     * @Author: zh
     * @Date: 2020/7/17 0017 19:45
     */
    @PostMapping("/insertMenu")
    public ResultData insertMenu(Menu menu);
    /**
     * 功能描述: <br>
     *@Description
     * 修改菜单的方法
     * @Param: [menu]
     * @Return: com.aaa.eleven.base.ResultData
     * @Author: zh
     * @Date: 2020/7/17 0017 19:53
     */
    @PutMapping("/updateMenu")
    public ResultData updateMenu(Menu menu);
    @DeleteMapping("/deleteMenu")
    public ResultData deleteMenu(Menu menu);
    /**
     * 功能描述: <br>
     *@Description
     * 查询主节点
     * @Param: []
     * @Return: com.aaa.eleven.base.ResultData
     * @Author: zh
     * @Date: 2020/7/18 0018 16:11
     */
    @PostMapping("/selectMasterNode")
    public ResultData selectMasterNode();
    /**
     * 功能描述: <br>
     *@Description
     * 查询子节点
     * @Param: [menuId]
     * @Return: com.aaa.eleven.base.ResultData
     * @Author: zh
     * @Date: 2020/7/18 0018 16:30
     */
    @PostMapping("/selectChildNode")
    public ResultData selectChildNode(Long menuId);
    /**
     * 功能描述: <br>
     *@Description
     * 查询全部的权限
     * @Param: []
     * @Return: com.aaa.eleven.base.ResultData
     * @Author: zh
     * @Date: 2020/7/19 0019 14:00
     */
    @GetMapping("/selectAllMenu")
    public ResultData selectAllMenu();
    /**
     * 功能描述: <br>
     *@Description
     * 根据menuID 获取父类信息
     * @Param: [menu]
     * @Return: com.aaa.eleven.base.ResultData
     * @Author: zh
     * @Date: 2020/7/19 0019 14:54
     */
    @GetMapping("/selectParentMenu")
    public ResultData selectParentMenu(Menu menu);
    /**
     * 批量删除新闻信息
     * @param ids
     * @return
     */
    @PostMapping("/news/deleteBatchNewsByIds")
    public ResultData deleteBatchNewsByIds(@RequestBody List<Integer> ids);
    @GetMapping("/news/selectAll")
    public ResultData selectAllNews(@RequestParam(value = "curpage",required = false,defaultValue = "1")int curpage , @RequestParam(value = "pagesize",required = false,defaultValue = "5")int pagesize,@RequestParam String title);

    @PostMapping("/news/addNews")
    public ResultData addNews(@RequestBody News news);
    @RequestMapping("/news/updateNews")
    public ResultData updateNews(@RequestBody News news);
    @RequestMapping("/news/deleteNews")
    public ResultData deleteNews(@RequestBody News news);
    /***
     * @Author ftt
     * @Description
     * 查询负责人列表
     * @Date 2020/7/14 21:55
     * @Param [userId]
     * @return com.aaa.eleven.base.ResultData
     */
    @GetMapping("/principal/selectPrincipalListByUserId")
    public ResultData selectPrincipalListByUserId(@RequestParam(value = "curpage",required = false,defaultValue = "1")int curpage, @RequestParam(value = "pagesize",required = false,defaultValue = "5")int pagesize,@RequestParam("userId") long userId);
    /***
     * @Author ftt
     * @Description
     * 根据id查询负责人详细信息
     * @Date 2020/7/15 20:57
     * @Param [id]
     * @return com.aaa.eleven.base.ResultData
     */
    @GetMapping("/principal/selectPrincipalDetail")
    public ResultData selectPrincipalDetail(@RequestParam("id") Long id);
    /***
     * @Author ftt
     * @Description
     * 新增负责人信息
     * @Date 2020/7/16 18:25
     * @Param [principal]
     * @return com.aaa.eleven.base.ResultData
     */
    @PostMapping("/principal/insertPrincipal")
    public ResultData insertPrincipal(@RequestBody Principal principal);
    /***
     * @Author ftt
     * @Description
     * 修改负责人信息
     * @Date 2020/7/16 18:25
     * @Param [principal]
     * @return com.aaa.eleven.base.ResultData
     */
    @PostMapping("/principal/updatePrincipal")
    public ResultData updatePrincipal(@RequestBody Principal principal);
    /***
     * @Author ftt
     * @Description
     * 删除负责人信息
     * @Date 2020/7/16 18:25
     * @Param [principal]
     * @return com.aaa.eleven.base.ResultData
     */
    @PostMapping("/principal/deletePrincipal")
    public ResultData deletePrincipal(@RequestBody Principal principal);
    /**
     *  根据 refBizId 查询信息 返回 测绘成果及档案管理、质量体系、通用材料汇总
     * @param map
     * @return
     */
    @GetMapping("/resource/selectResourceByrefBizId")
    public ResultData selectResourceByrefBizId(@RequestParam Map map);
    /**
     * 功能描述: <br>
     *@Description
     * 查询所有角色信息附带条件查询
     * @Param: [roleName, startTime, endTime]
     * @Return: com.aaa.eleven.base.ResultData
     * @Author: zh
     * @Date: 2020/7/18 0018 9:23
     */
    @GetMapping("/selectAllRoleByCondition")
    public ResultData selectAllRoleByCondition(String roleName, String startTime, String endTime);
    /**
     * 功能描述: <br>
     *@Description
     * 点开操作按钮之后获取所拥有的权限
     * @Param: [roleId]
     * @Return: com.aaa.eleven.base.ResultData
     * @Author: zh
     * @Date: 2020/7/18 0018 10:50
     */
    @GetMapping("/selectMenuByRoleId")
    public ResultData selectMenuByRoleId(@RequestParam("roleId") Long roleId);
    /**
     * 功能描述: <br>
     *@Description
     * 修改权限
     * @Param: [roleId, menuIds]
     * @Return: com.aaa.eleven.base.ResultData
     * @Author: zh
     * @Date: 2020/7/18 0018 16:26
     */
    @PostMapping("/updateRoleJurisdiction")
    public ResultData updateRoleJurisdiction(Long roleId, Integer[] menuIds);
    /**
     * 功能描述: <br>
     *@Description
     * 增加角色 并给中间表添加数据
     * @Param: [role, ids]
     * @Return: com.aaa.eleven.base.ResultData
     * @Author: zh
     * @Date: 2020/7/19 0019 11:44
     */
    @PostMapping("/insertRole")
    public ResultData insertRole(Role role, Integer[] ids);
    /**
     * 功能描述: <br>
     *@Description
     * 删除角色
     * @Param: [roleId]
     * @Return: com.aaa.eleven.base.ResultData
     * @Author: zh
     * @Date: 2020/7/19 0019 14:00
     */
    @GetMapping  ("/deleteRole")
    public ResultData deleteRole(@RequestParam("roleId") Long roleId);
    /***
     * @Author ftt
     * @Description
     * 查看评分记录
     *      在单位审核的 单位列表的查看评分记录
     * @Date 2020/7/17 14:57
     * @Param []
     * @return com.aaa.eleven.base.ResultData
     */
    @GetMapping("/score/selectScoreList")
    public ResultData selectScoreList(@RequestParam Map map);

    /***
     * @Author ftt
     * @Description
     * 调整分值
     *      在单位审核的 单位列表的调整分值
     * @Date 2020/7/17 16:31
     * @Param [map]
     * @return com.aaa.eleven.base.ResultData
     */
    @GetMapping("/score/updateScoreMultiTables")
    public ResultData updateScoreMultiTables(@RequestParam Map map, MultipartFile file, String filename);
    /***
     * @Author ftt
     * @Description
     * 查询特殊岗位人员列表
     * @Date 2020/7/14 21:46
     * @Param [userId]
     * @return com.aaa.eleven.base.ResultData
     */
    @GetMapping("/specialPost/selectSpecialPostListByUserId")
    public ResultData selectSpecialPostListByUserId(@RequestParam(value = "curpage",required = false,defaultValue = "1")int curpage, @RequestParam(value = "pagesize",required = false,defaultValue = "5")int pagesize,@RequestParam("userId") long userId);

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
     * 新增特殊岗位人员信息
     * @Date 2020/7/16 18:32
     * @Param [specialPost]
     * @return com.aaa.eleven.base.ResultData
     */
    @PostMapping("/specialPost/insertSpecialPost")
    public ResultData insertSpecialPost(@RequestBody SpecialPost specialPost);
    /***
     * @Author ftt
     * @Description
     * 修改特殊岗位人员信息
     * @Date 2020/7/16 18:32
     * @Param [specialPost]
     * @return com.aaa.eleven.base.ResultData
     */
    @PostMapping("/specialPost/updateSpecialPost")
    public ResultData updateSpecialPost(@RequestBody SpecialPost specialPost);
    /***
     * @Author ftt
     * @Description
     * 删除特殊岗位人员信息
     * @Date 2020/7/16 18:32
     * @Param [specialPost]
     * @return com.aaa.eleven.base.ResultData
     */
    @PostMapping("/specialPost/deleteSpecialPost")
    public ResultData deleteSpecialPost(@RequestBody SpecialPost specialPost);
    /***
     * @Author ftt
     * @Description
     * 查询技术人员列表
     * @Date 2020/7/14 21:46
     * @Param [userId]
     * @return com.aaa.eleven.base.ResultData
     */
    @GetMapping("/technicist/selectTechnicistListByUserId")
    public ResultData selectTechnicistListByUserId(@RequestParam(value = "curpage",required = false,defaultValue = "1")int curpage, @RequestParam(value = "pagesize",required = false,defaultValue = "5")int pagesize,@RequestParam("userId") long userId);

    /***
     * @Author ftt
     * @Description
     * 根据id查询Technicist表的信息
     * @Date 2020/7/16 8:39
     * @Param [id]
     * @return com.aaa.eleven.base.ResultData
     */
    @GetMapping("/technicist/selectTechnicistDetail")
    public ResultData selectTechnicistDetail(@RequestParam("id") Long id );
    /***
     * @Author ftt
     * @Description
     * 新增技术人员信息
     * @Date 2020/7/16 18:39
     * @Param [technicist]
     * @return com.aaa.eleven.base.ResultData
     */
    @PostMapping("/technicist/insertTechnicist")
    public ResultData insertTechnicist(@RequestBody Technicist technicist);
    /***
     * @Author ftt
     * @Description
     * 修改技术人员信息
     * @Date 2020/7/16 18:39
     * @Param [technicist]
     * @return com.aaa.eleven.base.ResultData
     */
    @PostMapping("/technicist/updateTechnicist")
    public ResultData updateTechnicist(@RequestBody Technicist technicist);
    /***
     * @Author ftt
     * @Description
     * 删除技术人员信息
     * @Date 2020/7/16 18:39
     * @Param [technicist]
     * @return com.aaa.eleven.base.ResultData
     */
    @PostMapping("/technicist/deleteTechnicist")
    public ResultData deleteTechnicist(@RequestBody Technicist technicist);
    /**
     * 查询初中高技术人员数量
     * @param id
     * @return
     */

    @RequestMapping("/total/getUnitTotal")
    public ResultData getUnitTotal(@RequestParam String id);
    /**
     * 查询特殊人员数量
     * @param id
     * @return
     */
    @RequestMapping("/total/getUnitspecial")
    public ResultData getUnitspecial(@RequestParam String id);
    /**
     * 单位的项目数量
     */
    @RequestMapping("/total/getUnitProjectNum")
    public ResultData getUnitProjectNum(@RequestParam String id);


    /**
     * 第一个统计的单位资质统计
     */
    @GetMapping("/total/selectZizhi")
    public ResultData selectZizhi();

    /**
     * 第一个统计的类型统计
     */
    @GetMapping("/total/selectType")
    public ResultData selectType();
    /**
     * 查询各个等级对应的各种技术人员和设备的数量
     * @param ownedDistrict
     * @param createTime
     * @return
     */
    @GetMapping("/total/selectUnit")
    public ResultData selectUnit(@RequestParam(value = "ownedDistrict",required = false,defaultValue = "全部") String ownedDistrict,@RequestParam(value = "createTime",required = false,defaultValue = "全部") String createTime);
}