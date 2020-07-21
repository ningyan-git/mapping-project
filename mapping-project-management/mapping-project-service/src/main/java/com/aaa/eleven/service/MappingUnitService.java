package com.aaa.eleven.service;

import com.aaa.eleven.base.BaseService;
import com.aaa.eleven.mapper.MappingUnitMapper;
import com.aaa.eleven.model.Audit;
import com.aaa.eleven.model.MappingUnit;
import com.aaa.eleven.model.User;
import com.aaa.eleven.utils.FileNameUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.http.client.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.aaa.eleven.staticproperties.TimeForatProperties.TIME_FORMAT;


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
     *     修改 mappingUnit modify_time audit_status 3
     *     新增 audit id name 单位信息审核 type 1 submit_time ref_id (mappingUnit id)
     * @Date 2020/7/16 15:41
     * @Param [mappingUnit]
     * @return java.lang.Boolean
     */
    public Boolean updateMappingUnit(MappingUnit mappingUnit,AuditService auditService){
        if(null != mappingUnit){
            if(null != mappingUnit.getId()){
                //修改mappingUnit表
                mappingUnit.setModifyTime(new Date());
                mappingUnit.setAuditStatus(3);
                int i1 = mappingUnitMapper.updateByPrimaryKey(mappingUnit);
                //新增到audit表
                Audit audit = new Audit();
                audit.setId(Long.parseLong(FileNameUtils.getFileName()));
                audit.setName("单位信息审核");
                audit.setType(1);
                audit.setSubmitTime(new Date());
                audit.setRefId(mappingUnit.getId());
                Integer i2 = auditService.insert(audit);
                if(i1 > 0 && i2 > 0){
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
     * 注册单位信息
     *      注册要向user、mappingUnit、audit表添加数据
     *      user表 id username status--> 0(0锁定、1 有效) createTime type(0) token(设默认值0)
     *      mappingunit id createTime unitName userID auditstatus 2
     *      audit id name 单位信息审核 type 1 submit_time ref_id (user_id)
     * @Date 2020/7/16 15:42
     * @Param [mappingUnit]
     * @return java.lang.Boolean
     */
    public Boolean insertMappingUnit(MappingUnit mappingUnit,UserService userService,AuditService auditService){
        if(null != mappingUnit){
            if(mappingUnit.getUnitName() != null && mappingUnit.getUserId() != null){
                //新增到单位表
                mappingUnit.setId(Long.parseLong(FileNameUtils.getFileName()));
                mappingUnit.setCreateTime(new Date());
                //设置审核状态为未提交
                mappingUnit.setAuditStatus(3);
                int i1 = mappingUnitMapper.insert(mappingUnit);
                //注册到user表
                User user = new User();
                user.setUsername(mappingUnit.getUnitName());
                user.setStatus("0");
                user.setCreateTime(DateUtils.formatDate(new Date(),TIME_FORMAT));
                user.setToken("0");
                Integer i2 = userService.insert(user);
                //新增到审核表
                Audit audit = new Audit();
                audit.setId(Long.parseLong(FileNameUtils.getFileName()));
                audit.setName("单位信息审核");
                audit.setType(1);
                audit.setSubmitTime(new Date());
                audit.setRefId(user.getId());
                Integer i3 = auditService.insert(audit);
                if(i1 > 0 && i2 > 0 && i3 > 0){
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
    /***
     * @Author ftt
     * @Description
     * 黑白名单
     * @Date 2020/7/17 17:03
     * @Param [status]
     * @return java.util.List<com.aaa.eleven.model.MappingUnit>
     */
    public PageInfo<MappingUnit> blackAndWhitelist(Map map){
        if(map.get("status") != null){
            MappingUnit mappingUnit = new MappingUnit();
            mappingUnit.setUnitStatus(Integer.parseInt(map.get("status").toString()));
            int pageNum = Integer.parseInt(map.get("pageNum").toString());
            int pageSize = Integer.parseInt(map.get("pageSize").toString());
            PageHelper.startPage(pageNum,pageSize);
            List<MappingUnit> select = mappingUnitMapper.select(mappingUnit);
            if(select.size() > 0){
                PageInfo<MappingUnit> pageInfo = new PageInfo<MappingUnit>(select);
                return pageInfo;
            }
        }
        return null;
    }
    /***
     * @Author ftt
     * @Description
     * 随机按照比例并且按照区抽查单位
     * @Date 2020/7/17 17:17
     * @Param [map]
     * @return com.github.pagehelper.PageInfo<com.aaa.eleven.model.MappingUnit>
     */
    public PageInfo<Map<String, Object>> unitSpotCheck(Map map){
        int pageNum = Integer.parseInt(map.get("pageNum").toString());
        int pageSize = Integer.parseInt(map.get("pageSize").toString());
        PageHelper.startPage(pageNum,pageSize);
        float ran = Float.parseFloat(map.get("ran").toString());
        String ownedDistrict = null;
        if(map.get("ownedDistrict") != null){
            ownedDistrict = map.get("ownedDistrict").toString();
        }
        List<Map<String, Object>> list = null;
        if("全部单位".equals(ownedDistrict)){
            list = mappingUnitMapper.selectunitSpotCheckByRate(ran);
        }else {
            list = mappingUnitMapper.selectunitSpotCheck(ran,ownedDistrict);
        }
        if(list != null && list.size() > 0){
            PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(list);
            return pageInfo;
        }
        return null;
    }
    /***
     * @Author ftt
     * @Description
     * 抽查人员
     * @Date 2020/7/17 18:22
     * @Param [map]
     * @return com.github.pagehelper.PageInfo<java.util.Map<java.lang.String,java.lang.Object>>
     */
    public PageInfo<Map<String, Object>> selectPersonCheck(Map map){
        int pageNum = Integer.parseInt(map.get("pageNum").toString());
        int pageSize = Integer.parseInt(map.get("pageSize").toString());
        PageHelper.startPage(pageNum,pageSize);
        float ran = Float.parseFloat(map.get("ran").toString());
        List<Map<String, Object>> list = mappingUnitMapper.selectPersonCheck(ran);
        if(list != null && list.size() > 0){
            PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(list);
            return pageInfo;
        }
        return null;
    }
    /**
     * 功能描述: <br>
     *@Description
     * 主页的模糊查询
     * @Param:
     * @Return:
     * @Author: zh
     * @Date: 2020/7/17 0017 10:05
     */
    public List<Map<String,Object>> selectByUnitNameAndOwnedDistrictAndQualificationLevel(String unitName,String ownedDistrict,String qualificationLevel ){
        return mappingUnitMapper.selectByUnitNameAndOwnedDistrictAndQualificationLevel(unitName, ownedDistrict, qualificationLevel);
    }
    /**
     * 功能描述: <br>
     *@Description
     * 模糊查询的下拉框数据  单位资质
     * @Param: 1
     * @Return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @Author: zh
     * @Date: 2020/7/17 0017 10:34
     */
    public List<Map<String,Object>> selectMappingUnitQualificationLevel(){
        List<Map<String, Object>> maps = mappingUnitMapper.selectMappingUnitQualificationLevel();
        if(maps.size()>0)
        {
            return maps;
        }
        return null;
    };
    /**
     * 功能描述: <br>
     *@Description
     * 模糊查询的下拉框数据 单位地域
     * @Param: []
     * @Return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @Author: zh
     * @Date: 2020/7/17 0017 10:35
     */
    public List<Map<String,Object>> selectMappingUnitOwnedDistrict(){
        List<Map<String, Object>> maps = mappingUnitMapper.selectMappingUnitOwnedDistrict();
        if(maps.size()>0)
        {
            return maps;
        }
        else
        {
            return null;
        }
    }
    /***
     * @Author ftt
     * @Description
     * 单位信息提交审核
     *       mappingUnit表 auditStatus 为2
     *       新增audit表审核记录
     * @Date 2020/7/20 19:04
     * @Param [id]
     * @return java.lang.Boolean
     */
    public Boolean submitAudit(Long id,AuditService auditService){
        if(id != null){
            MappingUnit mappingUnit = new MappingUnit();
            mappingUnit.setAuditStatus(2);
            //修改单位表审核状态
            Integer i1 = update(mappingUnit);
            //新增一条审核记录
            Audit audit = new Audit();
            audit.setId(Long.parseLong(FileNameUtils.getFileName()));
            audit.setName("单位信息审核");
            audit.setSubmitTime(new Date());
            audit.setType(1);
            audit.setCreateTime(new Date());
            audit.setRefId(id);
            Integer i2 = auditService.insert(audit);
            if(i1 > 0 && i2 > 0 ){
                return true;
            }
        }
        return  false;
    }
}
