package com.aaa.eleven.controller;

import com.aaa.eleven.base.ResultData;
import com.aaa.eleven.model.Resource;
import com.aaa.eleven.service.MappingProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Company
 * @Author ftt
 * @Date Create in 2020/7/21 17:09
 * @Description
 */
@RestController
public class ResourceController {
    @Autowired
    private MappingProjectService mappingProjectService;
    /**
     *  根据 refBizId 查询信息 返回 测绘成果及档案管理、质量体系、通用材料汇总
     * @param map
     * @return
     */
    @GetMapping("/resource/selectResourceByrefBizId")
    public ResultData selectResourceByrefBizId(@RequestParam Map map){
       return mappingProjectService.selectResourceByrefBizId(map);
    }
}
