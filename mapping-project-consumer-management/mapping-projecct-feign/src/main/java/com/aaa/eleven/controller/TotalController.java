package com.aaa.eleven.controller;

import com.aaa.eleven.base.ResultData;
import com.aaa.eleven.service.MappingProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Company
 * @Author ftt
 * @Date Create in 2020/7/21 20:09
 * @Description
 */
@RestController
public class TotalController {
    @Autowired
    private MappingProjectService mappingProjectService;
    /**
     * 查询初中高技术人员数量
     * @param id
     * @return
     */

    @RequestMapping("/total/getUnitTotal")
    public ResultData getUnitTotal(@RequestParam String id){
       return mappingProjectService.getUnitTotal(id);
    }

    /**
     * 查询特殊人员数量
     * @param id
     * @return
     */
    @RequestMapping("/total/getUnitspecial")
    public ResultData getUnitspecial(@RequestParam String id){
        return mappingProjectService.getUnitspecial(id);

    }
    /**
     * 单位的项目数量
     */
    @RequestMapping("/total/getUnitProjectNum")
    public ResultData getUnitProjectNum(@RequestParam String id){
        return mappingProjectService.getUnitProjectNum(id);

    }


    /**
     * 第一个统计的单位资质统计
     */
    @GetMapping("/total/selectZizhi")
    public ResultData selectZizhi(){
        return mappingProjectService.selectZizhi();

    }

    /**
     * 第一个统计的类型统计
     */
    @GetMapping("/total/selectType")
    public ResultData selectType(){
        return mappingProjectService.selectType();

    }
    /**
     * 查询各个等级对应的各种技术人员和设备的数量
     * @param ownedDistrict
     * @param createTime
     * @return
     */
    @GetMapping("/total/selectUnit")
    public ResultData selectUnit(@RequestParam(value = "ownedDistrict",required = false,defaultValue = "全部") String ownedDistrict,@RequestParam(value = "createTime",required = false,defaultValue = "全部") String createTime){
        return mappingProjectService.selectUnit(ownedDistrict, createTime);

    }

}
