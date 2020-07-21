package com.aaa.eleven.service;

import com.aaa.eleven.base.BaseService;
import com.aaa.eleven.base.ResultData;
import com.aaa.eleven.mapper.TechnicistMapper;
import com.aaa.eleven.model.MappingUnit;
import com.aaa.eleven.model.Technicist;
import com.aaa.eleven.utils.FileNameUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Company
 * @Author ftt
 * @Date Create in 2020/7/14 21:29
 * @Description
 */
@Service
public class TechnicistService extends BaseService<Technicist> {
    @Autowired
    private TechnicistMapper technicistMapper;
    /***
     * @Author ftt
     * @Description
     * 查询技术人员信息
     *     把userId放入一个对象中，根据这个对象查询技术人员信息
     * @Date 2020/7/14 21:33
     * @Param [userId]
     * @return com.aaa.eleven.model.Technicist
     */
    public PageInfo selectOne(Integer pageNo,Integer pageSize,Long userId){
        PageHelper.startPage(pageNo,pageSize);
        Technicist technicist = new Technicist();
        if(null !=userId && !"".equals(userId)){
            technicist.setUserId(userId);
            List<Technicist> select = technicistMapper.select(technicist);
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
     * 根据id查询技术人员具体信息
     * @Date 2020/7/16 8:26
     * @Param [id]
     * @return com.aaa.eleven.model.Technicist
     */
    public List<Map<String, Object>> selectTechnicistDetail(Long id){
        if(null != id){
            List<Map<String, Object>> maps = technicistMapper.selectTechnicistDetail(id);
            if(null != maps && maps.size()> 0){
                return maps;
            }
        }
        return  null;
    }

    /***
     * @Author ftt
     * @Description
     * 新增技术人员信息
     * @Date 2020/7/16 18:39
     * @Param [technicist]
     * @return com.aaa.eleven.base.ResultData
     */
    public Boolean insertTechnicist(Technicist technicist,MappingUnitService mappingUnitService){
        if(technicist != null){
            if(technicist.getName() != null && technicist.getMajorType() != null && technicist.getIdType() != null && technicist.getIdNumber() != null){
                technicist.setId(Long.parseLong(FileNameUtils.getFileName()));
                technicist.setCreateTime(new Date());
                Integer i = insert(technicist);
                if(technicist.getUserId() != null) {
                    //修改mappingUnit表的auditstatus为3 未提交
                    MappingUnit mappingUnit = new MappingUnit();
                    mappingUnit.setUserId(technicist.getUserId());
                    mappingUnit.setAuditStatus(3);
                    Integer i1 = mappingUnitService.update(mappingUnit);
                    if (i > 0 && i1 > 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    /***
     * @Author ftt
     * @Description
     * 修改技术人员信息
     * @Date 2020/7/16 18:39
     * @Param [technicist]
     * @return com.aaa.eleven.base.ResultData
     */
    public Boolean updateTechnicist( Technicist technicist,MappingUnitService mappingUnitService){
        if(technicist != null){
            if(technicist.getId() != null){
                technicist.setModifyTime(new Date());
                Integer i = update(technicist);
                if(technicist.getUserId() != null) {
                    //修改mappingUnit表的auditstatus为3 未提交
                    MappingUnit mappingUnit = new MappingUnit();
                    mappingUnit.setUserId(technicist.getUserId());
                    mappingUnit.setAuditStatus(3);
                    Integer i1 = mappingUnitService.update(mappingUnit);
                    if (i > 0 && i1 > 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    /***
     * @Author ftt
     * @Description
     * 删除技术人员信息
     * @Date 2020/7/16 18:39
     * @Param [technicist]
     * @return com.aaa.eleven.base.ResultData
     */
    public Boolean deleteTechnicist(Technicist technicist,MappingUnitService mappingUnitService){
        if(technicist != null){
            if(technicist.getId() != null){
                Integer i = delete(technicist);
                if(technicist.getUserId() != null) {
                    //修改mappingUnit表的auditstatus为3 未提交
                    MappingUnit mappingUnit = new MappingUnit();
                    mappingUnit.setUserId(technicist.getUserId());
                    mappingUnit.setAuditStatus(3);
                    Integer i1 = mappingUnitService.update(mappingUnit);
                    if (i > 0 && i1 > 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
