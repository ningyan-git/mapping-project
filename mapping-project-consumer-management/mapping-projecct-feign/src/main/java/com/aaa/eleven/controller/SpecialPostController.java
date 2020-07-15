package com.aaa.eleven.controller;

import com.aaa.eleven.base.ResultData;
import com.aaa.eleven.model.SpecialPost;
import com.aaa.eleven.service.MappingProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Company
 * @Author ftt
 * @Date Create in 2020/7/14 22:08
 * @Description
 */
@RestController
public class SpecialPostController {
    @Autowired
    private MappingProjectService mappingProjectService;
    /***
     * @Author ftt
     * @Description
     * 查询特殊岗位人员列表
     * @Date 2020/7/14 21:46
     * @Param [userId]
     * @return com.aaa.eleven.base.ResultData
     */
    @GetMapping("/specialPost/selectSpecialPostListByUserId")
    public ResultData selectOneSpecialPost(@RequestParam("userId") long userId){
        return mappingProjectService.selectSpecialPostListByUserId(userId);


    }
}
