package com.aaa.eleven.service;

import com.aaa.eleven.base.BaseService;
import com.aaa.eleven.mapper.TotalMapper;
import com.aaa.eleven.model.MappingUnit;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Company
 * @Author ljy
 * @Date Create in 2020/7/17 14:35
 * @Description
 */
@Service
public class TotalService {

    @Autowired
    private TotalMapper totalMapper;

    /**
     * 查询初中高级技术人员数量
     * @param id
     * @return
     */
    public List<Map<String,Object>> getUnitTotal(String id){

        List<Map<String,Object>> unitTotal = totalMapper.getUnitTotal(id);
        if (unitTotal.size()>=0){
        return unitTotal;
        }
        return null;
    }
    /**
     * 查询特殊人才数量
     */
    public List<Map<String,Object>> getUnitspecial(String id){
        List<Map<String, Object>> unitspecial = totalMapper.getUnitspecial(id);
        if (unitspecial.size()>=0){
            return unitspecial;
        }
        return null;
    }
    /**
     * 单位的项目数量
     */
    public List<Map<String,Object>> getUnitProjectNum(String id){
        List<Map<String, Object>> unitProjectNum = totalMapper.getUnitProjectNum(id);
        if (unitProjectNum.size()>=0){
            return unitProjectNum;
        }
        return null;
    }
    /**
     * 第一个统计的单位资质统计
     */
    public List<Map<String,Object>> selectZizhi(){
        List<Map<String, Object>> maps = totalMapper.selectZizhi();
        if (maps !=null){
            return maps;
        }else {
            return null;
        }
    }
    /**
     * 第一个统计的类型统计
     */
    public Map<String,Object> selectType(){

        List<Map<String, Object>> maps = totalMapper.selectTypetwo();
        List<Map<String, Object>> maps1 = totalMapper.selectTypethree();
        if (maps !=null && maps1 !=null){
        HashMap hashMap = new HashMap();
        hashMap.put("未完成的各种项目类型数量",maps);
        hashMap.put("已完成的各种项目类型数量",maps1);
        return hashMap;
        }else {
            return null;
        }
    }

    /**
     * 查询各个等级对应的各种技术人员和设备的数量
     * @param ownedDistrict
     * @param createTime
     * @return
     */
    public HashMap selectUnit(String ownedDistrict,String createTime) {

        List<Map<String, Object>> maps1 = totalMapper.selectUnit(ownedDistrict, createTime);

        List<Map<String, Object>> maps = totalMapper.selectUnitNum(ownedDistrict, createTime);

        HashMap hashMap = new HashMap();
        hashMap.put("各级技术人员数量",maps1);
        hashMap.put("各级单位数量",maps);
        return hashMap;

    }
    

}
