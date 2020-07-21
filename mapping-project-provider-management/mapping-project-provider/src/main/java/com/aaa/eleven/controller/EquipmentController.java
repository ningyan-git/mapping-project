package com.aaa.eleven.controller;

import com.aaa.eleven.base.BaseService;
import com.aaa.eleven.base.CommonController;
import com.aaa.eleven.base.ResultData;
import com.aaa.eleven.model.Equipment;
import com.aaa.eleven.model.Technicist;
import com.aaa.eleven.service.EquipmentService;
import com.aaa.eleven.service.MappingUnitService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


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
    @Autowired
    private MappingUnitService mappingUnitService;
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
    public ResultData selectEquipmentListByuserId(@RequestParam(value = "curpage",required = false,defaultValue = "1")int curpage, @RequestParam(value = "pagesize",required = false,defaultValue = "5")int pagesize,@RequestParam("userId") long userId){
        PageInfo pageInfo = equipmentService.selectOne(curpage, pagesize, userId);
        if(pageInfo !=null){
            return selectSuccess(pageInfo);
        }
        return selectFailed();

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
        List<Map<String, Object>> maps = equipmentService.selectEquipmentdetail(id);
        if(null != maps){
            return  selectSuccess(maps);
        }else {
            return  selectFailed();
        }
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
        Boolean flag = equipmentService.insertEquipment(equipment,mappingUnitService);
        if(flag){
            return insertSuccess();
        }else {
            return insertFailed();
        }
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
        Boolean flag = equipmentService.updateEquipment(equipment, mappingUnitService);
        if(flag){
            return updateSuccess();
        }else {
            return updateFailed();
        }
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
        Boolean flag = equipmentService.deleteEquipment(equipment,mappingUnitService);
        if(flag){
            return deleteSuccess();
        }else {
            return deleteFailed();
        }
    }

}
