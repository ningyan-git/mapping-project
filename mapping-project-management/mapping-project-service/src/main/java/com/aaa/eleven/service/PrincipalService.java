package com.aaa.eleven.service;

import com.aaa.eleven.base.BaseService;
import com.aaa.eleven.base.ResultData;
import com.aaa.eleven.mapper.PrincipalMapper;
import com.aaa.eleven.model.MappingUnit;
import com.aaa.eleven.model.Principal;
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
 * @Date Create in 2020/7/14 21:24
 * @Description
 */
@Service
public class PrincipalService extends BaseService<Principal> {
    @Autowired
    private PrincipalMapper principalMapper;
    /***
     * @Author ftt
     * @Description
     * 分页查询负责人信息列表
     *     把userId放入一个对象中，根据这个对象查询负责人信息
     * @Date 2020/7/14 21:27
     * @Param [userId]
     * @return com.aaa.eleven.model.Principal
     */
    public PageInfo selectOne(Integer pageNo,Integer pageSize,Long userId){
        PageHelper.startPage(pageNo,pageSize);
        Principal principal = new Principal();
        if(null!=userId && !"".equals(userId)){
            principal.setUserId(userId);
            List<Principal> select = principalMapper.select(principal);
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
     * 根据id查询负责人具体信息
     * 附件包含多个文件
     * @Date 2020/7/15 20:54
     * @Param [id]
     * @return com.aaa.eleven.model.Principal
     */
    public List<Map<String, Object>> selectPrincipalDetail(Long id){
        Principal principal = new Principal();
        if(null != id){
            List<Map<String, Object>> list = principalMapper.selectPrincipalDetail(id);
            if(list.size() > 0){
                return list;
            }
        }
        return null;
    }
    /***
     * @Author ftt
     * @Description
     * 新增负责人信息
     * @Date 2020/7/16 18:25
     * @Param [principal]
     * @return com.aaa.eleven.base.ResultData
     */
    public Boolean insertPrincipal( Principal principal,MappingUnitService mappingUnitService){
        if(null != principal){
            if(principal.getType() != null && principal.getName() != null && principal.getIdType() != null && principal.getIdNumber() != null){
                principal.setId(Long.parseLong(FileNameUtils.getFileName()));
                principal.setCreateTime(new Date());
                // 新增 负责人信息
                Integer i = insert(principal);
                if(principal.getUserId() != null) {
                    //修改mappingUnit表的auditstatus为3 未提交
                    MappingUnit mappingUnit = new MappingUnit();
                    mappingUnit.setUserId(principal.getUserId());
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
     * 修改负责人信息
     * @Date 2020/7/16 18:25
     * @Param [principal]
     * @return com.aaa.eleven.base.ResultData
     */
    public Boolean updatePrincipal( Principal principal,MappingUnitService mappingUnitService){
        if(null != principal){
            if(principal.getId() != null){
                //修改负责人信息
                principal.setModifyTime(new Date());
                Integer i = update(principal);
                if(principal.getUserId() != null) {
                    //修改mappingUnit表的auditstatus为3 未提交
                    MappingUnit mappingUnit = new MappingUnit();
                    mappingUnit.setUserId(principal.getUserId());
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
     * 删除负责人信息
     * @Date 2020/7/16 18:25
     * @Param [principal]
     * @return com.aaa.eleven.base.ResultData
     */
    public Boolean deletePrincipal( Principal principal,MappingUnitService mappingUnitService){
        if(null != principal){
            if(principal.getId() != null){
                //删除负责人信息
                Integer i = delete(principal);
                if(principal.getUserId() != null) {
                    //修改mappingUnit表的auditstatus为3 未提交
                    MappingUnit mappingUnit = new MappingUnit();
                    mappingUnit.setUserId(principal.getUserId());
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
