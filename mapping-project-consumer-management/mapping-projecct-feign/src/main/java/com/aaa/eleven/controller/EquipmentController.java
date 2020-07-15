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
 * @Date Create in 2020/7/14 22:08
 * @Description
 */
@RestController
public class EquipmentController {
    @Autowired
    private MappingProjectService mappingProjectService;
    /***
     * @Author ftt
     * @Description
     * 查询设备信息
     * @Date 2020/7/14 16:48
     * @Param [userId]
     * @return com.aaa.eleven.base.ResultData
     */
    @GetMapping("/equipment/selectEquipmentListByuserId")
    public ResultData selectOneEquipment(@RequestParam("userId") long userId){
        return mappingProjectService.selectEquipmentListByuserId(userId);
    }
}
