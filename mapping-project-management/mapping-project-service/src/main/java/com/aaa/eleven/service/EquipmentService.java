package com.aaa.eleven.service;

import com.aaa.eleven.base.BaseService;
import com.aaa.eleven.mapper.EquipmentMapper;
import com.aaa.eleven.model.Equipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Company
 * @Author ftt
 * @Date Create in 2020/7/14 21:32
 * @Description
 */
@Service
public class EquipmentService extends BaseService<Equipment> {
    @Autowired
    private EquipmentMapper equipmentMapper;
    /***
     * @Author ftt
     * @Description
     * 查询单个仪器信息
     *    把userId放入一个对象中，根据这个对象查询仪器信息
     * @Date 2020/7/14 21:35
     * @Param [userId]
     * @return com.aaa.eleven.model.Equipment
     */
    public List<Equipment> selectOne(Long userId){
        Equipment equipment = new Equipment();
        if(null !=userId && !"".equals(userId)){
            equipment.setUserId(userId);
            List<Equipment> select = equipmentMapper.select(equipment);
            return select;
        }
        return null;
    }
}
