package com.aaa.eleven.service;

import com.aaa.eleven.base.BaseService;
import com.aaa.eleven.mapper.MappingUnitMapper;
import com.aaa.eleven.model.MappingUnit;
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
     * TODO bug
     * @Date 2020/7/14 16:00
     * @Param [pageNo, pageSize, orderByFiled, orderWord, fileds]
     * @return com.github.pagehelper.PageInfo<com.aaa.eleven.model.MappingUnit>
     */
    public PageInfo<List> selectByConditions(Integer pageNo, Integer pageSize,String unitName  ){
        PageInfo pageInfo = new PageInfo();
        PageHelper.startPage(pageNo,pageSize);
        List<Map<String, Object>> list = mappingUnitMapper.selectByConditions(unitName);
        if(list.size()>0){
            pageInfo.setList(list);
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

}
