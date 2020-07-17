package com.aaa.eleven.service;

import com.aaa.eleven.base.BaseService;
import com.aaa.eleven.mapper.EquipmentMapper;
import com.aaa.eleven.model.Equipment;
import com.aaa.eleven.utils.FileNameUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.io.FilenameUtils;
import org.apache.http.client.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.aaa.eleven.staticproperties.TimeForatProperties.DATE_FORMAT;

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
    public PageInfo selectOne(Integer pageNo,Integer pageSize,Long userId){
        PageHelper.startPage(pageNo,pageSize);
        Equipment equipment = new Equipment();
        if(null !=userId && !"".equals(userId)){
            equipment.setUserId(userId);
            List<Equipment> select = equipmentMapper.select(equipment);
            if(0 < select.size()){
                PageInfo pageInfo = new PageInfo(select);
                return pageInfo;
            }
        }
        return null;
    }
    /***
     * @Author ftt
     * @Description
     * 查询设备具体信息
     * @Date 2020/7/16 9:08
     * @Param [id]
     * @return com.aaa.eleven.model.Equipment
     */
    public List<Map<String, Object>> selectEquipmentdetail(Long id){
        if(null != id){
            List<Map<String, Object>> maps = equipmentMapper.selectEquipmentdetail(id);
            if(null != maps && maps.size() >0 ){
                return maps;
            }
        }
        return null;
    }
    /***
     * @Author ftt
     * @Description
     * 新增 仪器信息
     * @Date 2020/7/16 17:08
     * @Param [equipment]
     * @return java.lang.Boolean
     */
    public Boolean insertEquipment(Equipment equipment){
        if(null != equipment){
            equipment.setId(Long.parseLong(FileNameUtils.getFileName()));
            equipment.setCreateTime(new Date());
            int i = equipmentMapper.insert(equipment);
            if(i > 0){
                return true;
            }
        }
        return false;
    }
    /***
     * @Author ftt
     * @Description
     * 根据 id 删除仪器信息
     * @Date 2020/7/16 17:10
     * @Param [equipment]
     * @return java.lang.Boolean
     */
    public  Boolean deleteEquipment(Equipment equipment){
        if(equipment != null){
            if(equipment.getId() != null) {
                int i = delete(equipment);
                if (i > 0) {
                    return true;
                }
            }
        }
        return false;
    }
    /***
     * @Author ftt
     * @Description
     * 根据id 进行 修改
     * @Date 2020/7/16 17:15
     * @Param [equipment]
     * @return java.lang.Boolean
     */
    public Boolean updateEquipment(Equipment equipment){
        if(equipment != null){
            if(equipment.getId() != null){
                equipment.setModifyTime(new Date());
                int i = equipmentMapper.updateByPrimaryKey(equipment);
                if(i > 0 ){
                    return  true;
                }
            }
        }
        return  false;
    }
}
