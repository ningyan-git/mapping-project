package com.aaa.eleven.controller;

import com.aaa.eleven.base.ResultData;
import com.aaa.eleven.service.MappingProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
