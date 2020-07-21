package com.aaa.eleven.controller;

import com.aaa.eleven.base.ResultData;
import com.aaa.eleven.model.Equipment;
import com.aaa.eleven.service.MappingProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
     * 查询设备列表
     * @Date 2020/7/14 21:46
     * @Param [userId]
     * @return com.aaa.eleven.base.ResultData
     */
    @GetMapping("/equipment/selectEquipmentListByuserId")
    public ResultData selectEquipmentListByuserId(@RequestParam(value = "curpage",required = false,defaultValue = "1")int curpage, @RequestParam(value = "pagesize",required = false,defaultValue = "5")int pagesize,@RequestParam("userId") long userId){
        return mappingProjectService.selectEquipmentListByuserId(curpage,pagesize,userId);

    }
    /***
     * @Author ftt
     * @Description
     * 根据id 查询设备
     * @Date 2020/7/16 9:41
     * @Param [id]
     * @return com.aaa.eleven.base.ResultData
     */
    @GetMapping("/equipment/selectEquipmentdetail")
    public ResultData selectEquipmentdetail(@RequestParam("id") Long id){
        return mappingProjectService.selectEquipmentdetail(id);
    }
    /***
     * @Author ftt
     * @Description
     * 新增设备信息
     * @Date 2020/7/16 17:18
     * @Param [equipment]
     * @return com.aaa.eleven.base.ResultData
     */
    @PostMapping("/equipment/insertEquipment")
    public ResultData insertEquipment(@RequestBody Equipment equipment){
        return mappingProjectService.insertEquipment(equipment);
    }
    /***
     * @Author ftt
     * @Description
     * 修改设备信息
     * @Date 2020/7/16 17:18
     * @Param [equipment]
     * @return com.aaa.eleven.base.ResultData
     */
    @PostMapping("/equipment/updateEquipment")
    public ResultData updateEquipment(@RequestBody Equipment equipment){
        return mappingProjectService.updateEquipment(equipment);
    }
    /***
     * @Author ftt
     * @Description
     * 删除设备信息
     * @Date 2020/7/16 17:18
     * @Param [equipment]
     * @return com.aaa.eleven.base.ResultData
     */
    @PostMapping("/equipment/deleteEquipment")
    public ResultData deleteEquipment(@RequestBody Equipment equipment){
        return mappingProjectService.deleteEquipment(equipment);
    }

}
