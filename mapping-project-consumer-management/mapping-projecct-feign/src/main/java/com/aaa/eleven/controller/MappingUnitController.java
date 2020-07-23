package com.aaa.eleven.controller;

import com.aaa.eleven.base.ResultData;
import com.aaa.eleven.model.MappingUnit;
import com.aaa.eleven.service.MappingProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.aaa.eleven.status.Status.SELECT_FAILED;
import static com.aaa.eleven.status.Status.SELECT_SUCCESS;

/**
 * @Company
 * @Author ftt
 * @Date Create in 2020/7/14 16:45
 * @Description
 */
@RestController
public class MappingUnitController {
    @Autowired
    private MappingProjectService mappingProjectService;
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
       return mappingProjectService.selectOneByUserId(userId);
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
        return mappingProjectService.selectOneById(id);

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
        return mappingProjectService.selectMappingUnitByConditions(curpage, pagesize, unitName);

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
        return mappingProjectService.updateMappingUnit(mappingUnit);

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
        return mappingProjectService.insertMappingUnit(mappingUnit);

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
        return mappingProjectService.deleteMappingUnit(mappingUnit);


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
        return mappingProjectService.blackAndWhitelist(map);

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
        return mappingProjectService.unitSpotCheckList(map);

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
        return mappingProjectService.selectPersonCheck(map);

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
    public ResultData selectByUnitNameAndOwnedDistrictAndQualificationLevel(@RequestParam("unitName") String unitName,@RequestParam("ownedDistrict") String ownedDistrict,@RequestParam("qualificationLevel") String qualificationLevel){
        return mappingProjectService.selectByUnitNameAndOwnedDistrictAndQualificationLevel(unitName, ownedDistrict, qualificationLevel);

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
        return mappingProjectService.selectMappingUnitQualificationLevel();
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
        return mappingProjectService.selectMappingUnitOwnedDistrict();
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
        return mappingProjectService.submitAudit(id);

    }
}
