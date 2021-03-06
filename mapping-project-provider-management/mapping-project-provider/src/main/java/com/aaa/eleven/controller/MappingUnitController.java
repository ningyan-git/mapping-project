package com.aaa.eleven.controller;

import com.aaa.eleven.base.BaseService;
import com.aaa.eleven.base.CommonController;
import com.aaa.eleven.base.ResultData;
import com.aaa.eleven.model.MappingUnit;
import com.aaa.eleven.service.AuditService;
import com.aaa.eleven.service.MappingUnitService;
import com.aaa.eleven.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.aaa.eleven.status.Status.SELECT_FAILED;
import static com.aaa.eleven.status.Status.SELECT_SUCCESS;


/**
 * @Company
 * @Author ftt
 * @Date Create in 2020/7/14 16:22
 * @Description
 */
@RestController
public class MappingUnitController extends CommonController<MappingUnit> {
    @Autowired
    private MappingUnitService mappingUnitService;
    @Autowired
    private UserService userService;
    @Autowired
    private AuditService auditService;
    @Override
    public BaseService<MappingUnit> getBaseService() {
        return mappingUnitService;
    }
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
    public ResultData selectOneByUserId(@RequestParam("userId") long userId){
        MappingUnit mappingUnit = mappingUnitService.selectOne(userId);
        if(null !=mappingUnit){
            return selectSuccess(mappingUnit);
        }
        return selectFailed();
    }
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
    public ResultData selectOneById(@RequestParam("id") long id){
        MappingUnit mappingUnit = mappingUnitService.selectOneById(id);
        if(null !=mappingUnit){
            return selectSuccess(mappingUnit);
        }
        return selectFailed();
    }
    /***
     * @Author ftt
     * @Description
     * 单位列表 根据 单位名称进行模糊查询
     * @Date 2020/7/16 14:59
     * @Param [curpage, pagesize, unitName]
     * @return com.aaa.eleven.base.ResultData
     */
    @GetMapping("/mappingUnit/selectMappingUnitByConditions")
    public ResultData selectMappingUnitByConditions(@RequestParam(value = "curpage",required = false,defaultValue = "1")int curpage, @RequestParam(value = "pagesize",required = false,defaultValue = "5")int pagesize,@RequestParam("unitName") String unitName){
        PageInfo<List> pageInfo = mappingUnitService.selectByConditions(curpage, pagesize, unitName);
        if(pageInfo != null ){
            return selectSuccess(pageInfo);
        }else {
            return selectFailed();
        }
    }
    /***
     * @Author ftt
     * @Description
     * 修改 单位信息
     * @Date 2020/7/16 16:46
     * @Param [mappingUnit]
     * @return com.aaa.eleven.base.ResultData
     */
    @PostMapping("/mappingUnit/updateMappingUnit")
    public ResultData updateMappingUnit(@RequestBody MappingUnit mappingUnit){
        Boolean flag = mappingUnitService.updateMappingUnit(mappingUnit,auditService);
        if(flag){
            return updateSuccess();
        }else {
            return updateFailed();
        }

    }
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
    public ResultData insertMappingUnit(@RequestBody MappingUnit mappingUnit){
        Boolean flag = mappingUnitService.insertMappingUnit(mappingUnit,userService,auditService);
        if(flag){
            return  insertSuccess();
        }else {
            return insertFailed();
        }
    }
    /***
     * @Author ftt
     * @Description
     * 删除单位信息
     * @Date 2020/7/16 16:50
     * @Param [mappingUnit]
     * @return com.aaa.eleven.base.ResultData
     */
    @PostMapping("/mappingUnit/deleteMappingUnit")
    public ResultData deleteMappingUnit(@RequestBody MappingUnit mappingUnit){
        Boolean flag = mappingUnitService.deleteMappingUnit(mappingUnit);
        if(flag){
            return deleteSuccess();
        }else {
            return deleteFailed();
        }

    }
    /***
     * @Author ftt
     * @Description
     * 黑白名单
     * @Date 2020/7/17 17:10
     * @Param [map]
     * @return com.aaa.eleven.base.ResultData
     */
    @GetMapping("/mappingUnit/blackAndWhitelist")
    public ResultData blackAndWhitelist(@RequestParam Map map){
        PageInfo<MappingUnit> pageInfo = mappingUnitService.blackAndWhitelist(map);
        if(pageInfo != null){
            return selectSuccess(pageInfo);
        }
        return selectFailed();
    }
    /***
     * @Author ftt
     * @Description
     * 随机按照比例并且按照区抽查单位
     * @Date 2020/7/17 17:43
     * @Param [map]
     * @return com.aaa.eleven.base.ResultData
     */
    @GetMapping("/mappingUnit/unitSpotCheckList")
    public ResultData unitSpotCheckList(@RequestParam Map map){
        PageInfo<Map<String, Object>> pageInfo = mappingUnitService.unitSpotCheck(map);
        if(pageInfo != null){
            return selectSuccess(pageInfo);
        }
        return selectFailed();
    }
    /***
     * @Author ftt
     * @Description
     * 抽查人员
     * @Date 2020/7/17 18:24
     * @Param [map]
     * @return com.aaa.eleven.base.ResultData
     */
    @GetMapping("/mappingUnit/selectPersonCheck")
    public ResultData selectPersonCheck(@RequestParam Map map){
        PageInfo<Map<String, Object>> pageInfo = mappingUnitService.selectPersonCheck(map);
        if(pageInfo != null){
            return selectSuccess(pageInfo);
        }
        return selectFailed();
    }
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
    public ResultData selectByUnitNameAndOwnedDistrictAndQualificationLevel(String unitName,String ownedDistrict,String qualificationLevel){
        List<Map<String, Object>> maps = mappingUnitService.selectByUnitNameAndOwnedDistrictAndQualificationLevel(unitName, ownedDistrict, qualificationLevel);
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
     * 模糊查询的下拉框数据  单位资质
     * @Param: []
     * @Return: com.aaa.eleven.base.ResultData
     * @Author: zh
     * @Date: 2020/7/17 0017 10:40
     */
    @PostMapping("/selectMappingUnitQualificationLevel")
    public ResultData selectMappingUnitQualificationLevel(){
        List<Map<String, Object>> maps = mappingUnitService.selectMappingUnitQualificationLevel();
        if (maps.size()>0)
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
     * 模糊查询的下拉框数据  单位地域
     * @Param: []
     * @Return: com.aaa.eleven.base.ResultData
     * @Author: zh
     * @Date: 2020/7/17 0017 10:44
     */
    @PostMapping("/selectMappingUnitOwnedDistrict")
    public ResultData selectMappingUnitOwnedDistrict(){
        List<Map<String, Object>> maps = mappingUnitService.selectMappingUnitOwnedDistrict();
        if(maps.size()>0)
        {
            return super.selectSuccess(SELECT_SUCCESS.getMsg(),maps);
        }
        else
        {
            return super.selectFailed(SELECT_FAILED.getMsg());
        }
    }
    /***
     * @Author ftt
     * @Description
     * 提交审核
     * @Date 2020/7/20 19:14
     * @Param [id]
     * @return com.aaa.eleven.base.ResultData
     */
    @GetMapping("/mappingUnit/submitAudit")
    public ResultData submitAudit(@RequestParam("id") Long id){
        Boolean flag = mappingUnitService.submitAudit(id, auditService);
        if(flag){
            return updateSuccess("提交成功");
        }
        return updateFailed("提交失败");
    }
}
