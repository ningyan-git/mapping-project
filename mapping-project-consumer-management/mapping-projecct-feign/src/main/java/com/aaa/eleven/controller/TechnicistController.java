package com.aaa.eleven.controller;

import com.aaa.eleven.base.ResultData;
import com.aaa.eleven.model.Technicist;
import com.aaa.eleven.service.MappingProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Company
 * @Author ftt
 * @Date Create in 2020/7/14 22:07
 * @Description
 */
@RestController
public class TechnicistController {
    @Autowired
    private MappingProjectService mappingProjectService;
    /***
     * @Author ftt
     * @Description
     * 查询技术人员列表
     * @Date 2020/7/14 21:46
     * @Param [userId]
     * @return com.aaa.eleven.base.ResultData
     */
    @GetMapping("/technicist/selectTechnicistListByUserId")
    public ResultData selectOneTechnicist(@RequestParam("userId") long userId){
        return mappingProjectService.selectTechnicistListByUserId(userId);
    }
}
