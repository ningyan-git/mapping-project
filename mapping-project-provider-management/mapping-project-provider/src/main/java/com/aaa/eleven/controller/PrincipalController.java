package com.aaa.eleven.controller;

import com.aaa.eleven.base.BaseService;
import com.aaa.eleven.base.CommonController;
import com.aaa.eleven.base.ResultData;
import com.aaa.eleven.mapper.PrincipalMapper;
import com.aaa.eleven.model.Principal;
import com.aaa.eleven.model.SpecialPost;
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
        if(null != principal){
            if(principal.getType() != null && principal.getName() != null && principal.getIdType() != null && principal.getIdNumber() != null){
                principal.setId(Long.parseLong(FileNameUtils.getFileName()));
                principal.setCreateTime(new Date());
                Integer i = principalService.insert(principal);
                if(i > 0){
                    return  insertSuccess();
                }
            }
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
        if(null != principal){
            if(principal.getId() != null){
                principal.setModifyTime(new Date());
                Integer i = principalService.update(principal);
                if(i > 0){
                    return  updateSuccess();
                }
            }
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
        if(null != principal){
            if(principal.getId() != null){
                Integer i = principalService.delete(principal);
                if(i > 0){
                    return  deleteSuccess();
                }
            }
        }
        return deleteFailed();
    }
}
