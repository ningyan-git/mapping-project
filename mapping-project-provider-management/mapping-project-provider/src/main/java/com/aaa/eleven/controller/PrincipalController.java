package com.aaa.eleven.controller;

import com.aaa.eleven.base.BaseService;
import com.aaa.eleven.base.CommonController;
import com.aaa.eleven.base.ResultData;
import com.aaa.eleven.mapper.PrincipalMapper;
import com.aaa.eleven.model.Principal;
import com.aaa.eleven.model.SpecialPost;
import com.aaa.eleven.service.MappingUnitService;
import com.aaa.eleven.service.PrincipalService;
import com.aaa.eleven.utils.FileNameUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Company
 * @Author ftt
 * @Date Create in 2020/7/14 21:51
 * @Description
 */
@RestController
public class PrincipalController extends CommonController<Principal> {
    @Autowired
    private PrincipalService principalService;
    @Autowired
    private MappingUnitService mappingUnitService;
    @Override
    public BaseService<Principal> getBaseService() {
        return principalService;
    }
    /***
     * @Author ftt
     * @Description
     * 查询负责人列表
     * @Date 2020/7/14 21:55
     * @Param [userId]
     * @return com.aaa.eleven.base.ResultData
     */
    @GetMapping("/principal/selectPrincipalListByUserId")
    public ResultData selectPrincipalListByUserId(@RequestParam(value = "curpage",required = false,defaultValue = "1")int curpage, @RequestParam(value = "pagesize",required = false,defaultValue = "5")int pagesize,@RequestParam("userId") long userId){
        PageInfo pageInfo = principalService.selectOne(curpage, pagesize, userId);
        if(pageInfo !=null ){
            return selectSuccess(pageInfo);
        }
        return selectFailed();
    }
    /***
     * @Author ftt
     * @Description
     * 根据id查询负责人详细信息
     * @Date 2020/7/15 20:57
     * @Param [id]
     * @return com.aaa.eleven.base.ResultData
     */
    @GetMapping("/principal/selectPrincipalDetail")
    public ResultData selectPrincipalDetail(@RequestParam("id") Long id){
        List<Map<String, Object>> maps = principalService.selectPrincipalDetail(id);
        if(null != maps && maps.size()> 0 ){
            return selectSuccess(maps);
        }else {
            return selectFailed();
        }
    }
    /***
     * @Author ftt
     * @Description
     * 新增负责人信息
     * @Date 2020/7/16 18:25
     * @Param [principal]
     * @return com.aaa.eleven.base.ResultData
     */
    @PostMapping("/principal/insertPrincipal")
    public ResultData insertPrincipal(@RequestBody Principal principal){
        boolean i = principalService.insertPrincipal(principal,mappingUnitService);
        if(i ){
            return  insertSuccess();
        }
        return insertFailed();
    }
    /***
     * @Author ftt
     * @Description
     * 修改负责人信息
     * @Date 2020/7/16 18:25
     * @Param [principal]
     * @return com.aaa.eleven.base.ResultData
     */
    @PostMapping("/principal/updatePrincipal")
    public ResultData updatePrincipal(@RequestBody Principal principal){
        Boolean i = principalService.updatePrincipal(principal,mappingUnitService);
        if(i){
            return  updateSuccess();
        }
        return updateFailed();
    }
    /***
     * @Author ftt
     * @Description
     * 删除负责人信息
     * @Date 2020/7/16 18:25
     * @Param [principal]
     * @return com.aaa.eleven.base.ResultData
     */
    @PostMapping("/principal/deletePrincipal")
    public ResultData deletePrincipal(@RequestBody Principal principal){
        Boolean i = principalService.deletePrincipal(principal,mappingUnitService);
        if(i){
            return  deleteSuccess();
        }
        return deleteFailed();
    }
}
