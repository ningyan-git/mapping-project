package com.aaa.eleven.controller;

import com.aaa.eleven.base.ResultData;
import com.aaa.eleven.model.MappingUnit;
import com.aaa.eleven.service.MappingProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
     * @Date 2020/7/14 16:48
     * @Param [userId]
     * @return com.aaa.eleven.base.ResultData
     */
    @GetMapping("/mappingUnit/selectOneByUserId")
    public ResultData selectOne(@RequestParam long userId){
        return mappingProjectService.selectOneByUserId(userId);
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
    public ResultData selectMappingUnitByConditions(@RequestParam(value = "curpage",required = false,defaultValue = "1")int curpage, @RequestParam(value = "pagesize",required = false,defaultValue = "5")int pagesize,@RequestParam("unitName") String unitName) {
        return mappingProjectService.selectMappingUnitByConditions(curpage,pagesize,unitName);
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
    ResultData updateMappingUnit(@RequestBody MappingUnit mappingUnit){
        return mappingProjectService.updateMappingUnit(mappingUnit);
    }
    /***
     * @Author ftt
     * @Description
     * 新增 单位信息
     * @Date 2020/7/16 16:48
     * @Param [mappingUnit]
     * @return com.aaa.eleven.base.ResultData
     */
    @PostMapping("/mappingUnit/insertMappingUnit")
    ResultData insertMappingUnit(@RequestBody MappingUnit mappingUnit){
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
    ResultData deleteMappingUnit(@RequestBody MappingUnit mappingUnit){
        return mappingProjectService.deleteMappingUnit(mappingUnit);
    }

}
