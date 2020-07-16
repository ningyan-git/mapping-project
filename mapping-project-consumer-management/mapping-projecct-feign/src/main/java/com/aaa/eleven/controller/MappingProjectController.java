package com.aaa.eleven.controller;

import com.aaa.eleven.base.ResultData;
import com.aaa.eleven.service.MappingProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public ResultData selectMappingProjectList(@RequestParam(value = "curpage",required = false,defaultValue = "1")int curpage, @RequestParam(value = "pagesize",required = false,defaultValue = "5")int pagesize) {
        return mappingProjectService.selectMappingProjectList(curpage,pagesize);
    }
}
