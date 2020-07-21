package com.aaa.eleven.controller;

import com.aaa.eleven.base.BaseService;
import com.aaa.eleven.base.CommonController;
import com.aaa.eleven.base.ResultData;
import com.aaa.eleven.model.Audit;
import com.aaa.eleven.service.AuditService;
import com.aaa.eleven.service.MappingUnitService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Company
 * @Author ftt
 * @Date Create in 2020/7/17 15:21
 * @Description
 */
@RestController
public class AuditController extends CommonController<Audit> {
    @Autowired
    private AuditService auditService;
    @Autowired
    private MappingUnitService mappingUnitService;
    @Override
    public BaseService<Audit> getBaseService() {
        return auditService;
    }
    /***
     * @Author ftt
     * @Description
     * 查看审核记录
     *  单位审核  根据 unitName 查看审核记录
     * @Date 2020/7/17 15:24
     * @Param [map]
     * @return com.aaa.eleven.base.ResultData
     */
    @GetMapping("/audit/selectAuditRecordList")
    public ResultData selectAuditRecordList(@RequestParam Map map){
        PageInfo<Audit> pageInfo = auditService.selectAuditRecordList(map);
        if(pageInfo != null){
            return selectSuccess(pageInfo);
        }else {
            return selectFailed();
        }
    }

    /***
     * @Author ftt
     * @Description
     * 分页 + 单位查询 + 模糊
     *      单位审核
     *          单位列表(已审核)
     * @Date 2020/7/17 15:24
     * @Param [map]
     * @return com.aaa.eleven.base.ResultData
     */
    @GetMapping("/audit/selectMappingUnitAuditList")
    public ResultData selectMappingUnitAuditList(@RequestParam Map map){
        PageInfo<Audit> pageInfo = auditService.selectMappingUnitAuditList(map);
        if(pageInfo != null){
            return selectSuccess(pageInfo);
        }else {
            return selectFailed();
        }
    }
    /***
     * @Author ftt
     * @Description
     * 分页 + 单位查询 + 模糊
     *      单位审核
     *          单位列表(已审核)
     * @Date 2020/7/17 15:24
     * @Param [map]
     * @return com.aaa.eleven.base.ResultData
     */
    @GetMapping("/audit/selectMappingUnitAuditListByStatus")
    public ResultData selectMappingUnitAuditListByStatus(@RequestParam Map map){
        //todo
        PageInfo<Audit> pageInfo = auditService.selectMappingUnitAuditListbyStatus(map);
        if(pageInfo != null){
            return selectSuccess(pageInfo);
        }else {
            return selectFailed();
        }
    }
    /***
     * @Author ftt
     * @Description
     * 单位注册待审核
     *  列表
     *   模糊 + 分页
     * @Date 2020/7/17 15:24
     * @Param [map]
     * @return com.aaa.eleven.base.ResultData
     */
    @GetMapping("/audit/selectAuditForUnitInfo")
    public ResultData selectAuditForUnitInfo(@RequestParam Map map){
        PageInfo<Audit> pageInfo = auditService.selectAuditForUnitInfo(map);
        if(pageInfo != null){
            return selectSuccess(pageInfo);
        }else {
            return selectFailed();
        }
    }
    /***
     * @Author ftt
     * @Description
     * 修改单位审核状态
     *   单位修改待审核的操作
     * @Date 2020/7/17 21:59
     * @Param [map]
     * @return com.aaa.eleven.base.ResultData
     */
    @GetMapping("/audit/updateUnitAuditStatus")
    public ResultData updateUnitAuditStatus(@RequestParam Map map){
        Boolean flag = auditService.updateUnitAuditStatus(map,mappingUnitService);
        if(flag != null){
            return updateSuccess();
        }else {
            return updateFailed();
        }
    }
    /***
     * @Author ftt
     * @Description
     * 单位注册待审核
     *      单位注册待审核 的操作
     * @Date 2020/7/18 8:57
     * @Param [map]
     * @return com.aaa.eleven.base.ResultData
     */
    @GetMapping("/audit/updateUnitRegisterStatus")
    public ResultData updateUnitRegisterStatus(@RequestParam Map map){
        Boolean flag = auditService.updateUnitRegisterStatus(map);
        if(flag != null){
            return updateSuccess();
        }else {
            return updateFailed();
        }
    }
}
