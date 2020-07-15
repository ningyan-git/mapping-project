package com.aaa.eleven.controller;

import com.aaa.eleven.base.BaseService;
import com.aaa.eleven.base.CommonController;
import com.aaa.eleven.base.ResultData;
import com.aaa.eleven.mapper.PrincipalMapper;
import com.aaa.eleven.model.Principal;
import com.aaa.eleven.model.SpecialPost;
import com.aaa.eleven.service.PrincipalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public ResultData selectPrincipalListByUserId(@RequestParam("userId") long userId){
        List<Principal> principalList = principalService.selectOne(userId);
        if(principalList.size()>0){
            return selectSuccess(principalList);
        }
        return selectFailed();

    }
}
