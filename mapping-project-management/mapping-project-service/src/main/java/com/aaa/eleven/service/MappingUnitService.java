package com.aaa.eleven.service;

import com.aaa.eleven.base.BaseService;
import com.aaa.eleven.mapper.MappingUnitMapper;
import com.aaa.eleven.model.MappingUnit;
import com.aaa.eleven.utils.FileNameUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;


/**
 * @Company
 * @Author ftt
 * @Date Create in 2020/7/14 15:37
 * @Description
 */
@Service
public class MappingUnitService extends BaseService<MappingUnit> {
    @Autowired
    private MappingUnitMapper mappingUnitMapper;

    /***
     * @Author ftt
     * @Description
     * 实现多条件的分页查询
     * 单位审核的单位列表的展示
     *
     * @Date 2020/7/14 16:00
     * @Param [pageNo, pageSize, orderByFiled, orderWord, fileds]
     * @return com.github.pagehelper.PageInfo<com.aaa.eleven.model.MappingUnit>
     */
    public PageInfo<List> selectByConditions(Integer pageNo, Integer pageSize,String unitName  ){
        PageHelper.startPage(pageNo,pageSize);
        List<Map<String, Object>> list = mappingUnitMapper.selectByConditions(unitName);
        if(list.size()>0){
            PageInfo pageInfo = new PageInfo(list);
            return pageInfo;
        }
        return null;

    }

    /***
     * @Author ftt
     * @Description
     * 查询单个单位信息
     * 把userId放入一个对象中，根据这个对象查询单位信息
     * @Date 2020/7/14 16:17
     * @Param [userId]
     * @return com.aaa.eleven.model.MappingUnit
     */
    public MappingUnit  selectOne(Long userId){
        MappingUnit mappingUnit = new MappingUnit();
        if(null!=userId && !"".equals(userId)){
            mappingUnit.setUserId(userId);
            MappingUnit selectOne = mappingUnitMapper.selectOne(mappingUnit);
            return selectOne;
        }
        return null;
    }
    /***
     * @Author ftt
     * @Description
     * 根据主键进行修改
     * @Date 2020/7/16 15:41
     * @Param [mappingUnit]
     * @return java.lang.Boolean
     */
    public Boolean updateMappingUnit(MappingUnit mappingUnit){
        if(null != mappingUnit){
            if(null != mappingUnit.getId()){
                int i = mappingUnitMapper.updateByPrimaryKey(mappingUnit);
                if(i > 0){
                    return true;
                }
            }
        }
        return  false;
    }
    /***
     * @Author ftt
     * @Description
     * 增加
     * @Date 2020/7/16 15:42
     * @Param [mappingUnit]
     * @return java.lang.Boolean
     */
    public Boolean insertMappingUnit(MappingUnit mappingUnit){
        if(null != mappingUnit){
            if(mappingUnit.getUsedName() != null && mappingUnit.getUserId() != null){
                mappingUnit.setId(Long.parseLong(FileNameUtils.getFileName()));
                int i = mappingUnitMapper.insert(mappingUnit);
                if(i > 0){
                    return true;
                }
            }
        }
        return  false;
    }
    /***
     * @Author ftt
     * @Description
     * 删除
     * @Date 2020/7/16 15:44
     * @Param [mappingUnit]
     * @return java.lang.Boolean
     */
    public Boolean deleteMappingUnit(MappingUnit mappingUnit){
        if(null != mappingUnit){
            if(null != mappingUnit.getId()) {
                int i = delete(mappingUnit);
                if (i > 0) {
                    return true;
                }
            }
        }
        return  false;
    }
}
