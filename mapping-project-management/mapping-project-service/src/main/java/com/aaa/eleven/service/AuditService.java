package com.aaa.eleven.service;

import com.aaa.eleven.base.BaseService;
import com.aaa.eleven.mapper.AuditMapper;
import com.aaa.eleven.model.Audit;
import com.aaa.eleven.model.MappingUnit;
import com.aaa.eleven.model.Score;
import com.aaa.eleven.model.User;
import com.aaa.eleven.utils.FileNameUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.http.client.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.aaa.eleven.staticproperties.TimeForatProperties.TIME_FORMAT;

/**
 * @Company
 * @Author ftt
 * @Date Create in 2020/7/17 15:16
 * @Description
 */
@Service
public class AuditService extends BaseService<Audit> {
    @Autowired
    private AuditMapper auditMapper;
    /***
     * @Author ftt
     * @Description
     * 查看审核记录
     * @Date 2020/7/17 15:19
     * @Param [map]
     * @return com.github.pagehelper.PageInfo<com.aaa.eleven.model.Audit>
     */
    public PageInfo<Audit> selectAuditRecordList(Map map){
        if(null != map.get("unitName")){
            int  pageNum = Integer.parseInt(map.get("pageNum").toString());
            int  pageSize = Integer.parseInt(map.get("pageSize").toString());
            PageHelper.startPage(pageNum,pageSize);
            String unitName = map.get("unitName").toString();
            List<Audit> list = auditMapper.selectAuditRecordList(unitName);
            if(list != null && list.size() > 0){
                PageInfo<Audit> pageInfo = new PageInfo<Audit>(list);
                return pageInfo;
            }
        }
        return null;
    }
    /***
     * @Author ftt
     * @Description
     * 分页 + 单位未审核查询 + 模糊
     * @Date 2020/7/17 20:48
     * @Param [map]
     * @return com.github.pagehelper.PageInfo
     */
    public PageInfo selectMappingUnitAuditList(Map map){
        int  pageNum = Integer.parseInt(map.get("pageNum").toString());
        int  pageSize = Integer.parseInt(map.get("pageSize").toString());
        PageHelper.startPage(pageNum,pageSize);
        String unitName = null;
        if(null != map.get("unitName")){
             unitName = map.get("unitName").toString();
        }
        List<MappingUnit> list = auditMapper.selectMappingUnitAuditList(unitName);
        if(list != null && list.size() > 0){
            PageInfo<MappingUnit> pageInfo = new PageInfo<MappingUnit>(list);
            return pageInfo;
        }
        return null;
    }
    /***
     * @Author ftt
     * @Description
     * 查询单位注册待审核
     * @Date 2020/7/17 20:48
     * @Param [map]
     * @return com.github.pagehelper.PageInfo
     */
    public PageInfo selectAuditForUnitInfo(Map map){
        int  pageNum = Integer.parseInt(map.get("pageNum").toString());
        int  pageSize = Integer.parseInt(map.get("pageSize").toString());
        PageHelper.startPage(pageNum,pageSize);
        String unitName = null;
        if(null != map.get("unitName")){
            unitName = map.get("unitName").toString();
        }
        List<MappingUnit> list = auditMapper.selectAuditForUnitInfo(unitName);
        if(list != null && list.size() > 0){
            PageInfo<MappingUnit> pageInfo = new PageInfo<MappingUnit>(list);
            return pageInfo;
        }
        return null;
    }
    /***
     * @Author ftt
     * @Description
     * 修改单位审核状态
     *    mappingUnit
     *    audit_status
     *    audit id name 单位信息审核 type 1  status audit_time refid id
     * @Date 2020/7/17 21:46
     * @Param [map]
     * @return java.lang.Boolean
     */
    public Boolean updateUnitAuditStatus(Map map,MappingUnitService mappingUnitService){
        if(map.get("id") != null){
            long id = Long.parseLong(map.get("id").toString());
            //修改单位审核状态
            MappingUnit mappingUnit = new MappingUnit();
            int status = Integer.parseInt(map.get("status").toString());
            mappingUnit.setId(id);
            mappingUnit.setAuditStatus(status);
            mappingUnit.setModifyTime(new Date());
            int i1 = mappingUnitService.update(mappingUnit);
            //新增审核记录
            Audit audit = new Audit();
            audit.setRefId(id);
            audit.setId(Long.parseLong(FileNameUtils.getFileName()));
            audit.setStatus(status);
            audit.setName("单位信息审核");
            audit.setCreateTime(new Date());
            Integer i2 = insert(audit);
            if(i1 >0 && i2 > 0){
                return true;
            }
        }
        return false;
    }
    /***
     * @Author ftt
     * @Description
     * 单位注册待审核
     * user modifyTime  status
     * audit id name 单位信息审核 type 1  status audit_time refid id
     * @Date 2020/7/18 8:42
     * @Param [map]
     * @return java.lang.Boolean
     */
    public Boolean updateUnitRegisterStatus(Map map){
        if(map.get("userId") != null){
            long id = Long.parseLong(map.get("userId").toString());
            //修改user审核状态
            int status = Integer.parseInt(map.get("status").toString());
            Integer i1 = auditMapper.updateUserAuditStatus(id,status);
            //新增审核记录
            Audit audit = new Audit();
            audit.setRefId(id);
            audit.setId(Long.parseLong(FileNameUtils.getFileName()));
            audit.setStatus(status);
            audit.setCreateTime(new Date());
            Integer i2 = insert(audit);
            if(i1 > 0 && i2 > 0){
                return true;
            }
        }
        return false;
    }
    
}
