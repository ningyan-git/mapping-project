package com.aaa.eleven.controller;

import com.aaa.eleven.base.BaseService;
import com.aaa.eleven.base.CommonController;
import com.aaa.eleven.base.ResultData;
import com.aaa.eleven.model.Equipment;
import com.aaa.eleven.model.Technicist;
import com.aaa.eleven.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @Company
 * @Author ftt
 * @Date Create in 2020/7/14 22:00
 * @Description
 */
@RestController
public class EquipmentController extends CommonController<Equipment> {
    @Autowired
    private EquipmentService equipmentService;
    @Override
    public BaseService<Equipment> getBaseService() {
        return equipmentService;
    }
    /***
     * @Author ftt
     * @Description
     * 查询设备列表
     * @Date 2020/7/14 21:46
     * @Param [userId]
     * @return com.aaa.eleven.base.ResultData
     */
    @GetMapping("/equipment/selectEquipmentListByuserId")
    public ResultData selectEquipmentListByuserId(@RequestParam("userId") long userId){
        List<Equipment> equipment = equipmentService.selectOne(userId);
        if(equipment.size()>0){
            return selectSuccess(equipment);
        }
        return selectFailed();

    }
}
