package com.aaa.eleven.controller;

import com.aaa.eleven.base.BaseService;
import com.aaa.eleven.base.CommonController;
import com.aaa.eleven.base.ResultData;
import com.aaa.eleven.model.MappingUnit;
import com.aaa.eleven.service.TotalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Company
 * @Author ljy
 * @Date Create in 2020/7/17 15:39
 * @Description
 */
@RestController
public class TotalController extends CommonController {

    @Autowired
    private TotalService totalService;

    /**
     * 查询初中高技术人员数量
     * @param id
     * @return
     */

    @RequestMapping("/total/getUnitTotal")
    public ResultData getUnitTotal(@RequestParam String id){
        List<Map<String,Object>> unitTotal = totalService.getUnitTotal(id);
        if (unitTotal !=null){
            return selectSuccess(unitTotal);
        }else {
            return selectFailed();
        }
    }

    /**
     * 查询特殊人员数量
     * @param id
     * @return
     */
    @RequestMapping("/total/getUnitspecial")
    public ResultData getUnitspecial(@RequestParam String id){

        if (id != null){
        List<Map<String, Object>> unitspecial = totalService.getUnitspecial(id);
        return selectSuccess(unitspecial);
        }else {
            return selectFailed();
        }
    }
    /**
     * 单位的项目数量
     */
    @RequestMapping("/total/getUnitProjectNum")
    public ResultData getUnitProjectNum(@RequestParam String id){
        if (id != null) {
            List<Map<String, Object>> unitProjectNum = totalService.getUnitProjectNum(id);
            return selectSuccess(unitProjectNum);
        }else {
            return selectFailed();
        }
    }


    /**
     * 第一个统计的单位资质统计
     */
    @GetMapping("/total/selectZizhi")
    public ResultData selectZizhi(){
        List<Map<String, Object>> maps = totalService.selectZizhi();
        if (maps !=null){
            return selectSuccess(maps);
        }else {
            return selectFailed();
        }
    }

    /**
     * 第一个统计的类型统计
     */
    @GetMapping("/total/selectType")
    public ResultData selectType(){
        Map maps = totalService.selectType();
        if (maps !=null){
            return selectSuccess(maps);
        }else {
            return selectFailed();
        }
    }
    /**
     * 查询各个等级对应的各种技术人员和设备的数量
     * @param ownedDistrict
     * @param createTime
     * @return
     */
    @GetMapping("/total/selectUnit")
    public ResultData selectUnit(@RequestParam(value = "ownedDistrict",required = false,defaultValue = "全部") String ownedDistrict,@RequestParam(value = "createTime",required = false,defaultValue = "全部") String createTime){
        HashMap maps = totalService.selectUnit(ownedDistrict, createTime);

        if (maps !=null){
            return selectSuccess(maps);
        }else {
            return selectFailed();
        }
    }


    @Override
    public BaseService getBaseService() {
        return null;
    }
}
