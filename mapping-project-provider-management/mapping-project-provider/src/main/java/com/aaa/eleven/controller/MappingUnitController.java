package com.aaa.eleven.controller;

import com.aaa.eleven.base.BaseService;
import com.aaa.eleven.base.CommonController;
import com.aaa.eleven.base.ResultData;
import com.aaa.eleven.model.MappingUnit;
import com.aaa.eleven.service.MappingUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Company
 * @Author ftt
 * @Date Create in 2020/7/14 16:22
 * @Description
 */
@RestController
public class MappingUnitController extends CommonController<MappingUnit> {
    @Autowired
    private MappingUnitService mappingUnitService;

    @Override
    public BaseService<MappingUnit> getBaseService() {
        return mappingUnitService;
    }
    /***
     * @Author ftt
     * @Description
     * 查询单个单位信息
     * @Date 2020/7/14 16:36
     * @Param [userId]
     * @return com.aaa.eleven.base.ResultData
     */
    @GetMapping("/mappingUnit/selectOneByUserId")
    public ResultData selectOneByUserId(@RequestParam("userId") long userId){
        MappingUnit mappingUnit = mappingUnitService.selectOne(userId);
        if(null !=mappingUnit){
            return selectSuccess(mappingUnit);
        }
        return selectFailed();

    }

}
