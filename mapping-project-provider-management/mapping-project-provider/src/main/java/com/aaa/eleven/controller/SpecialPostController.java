package com.aaa.eleven.controller;

import com.aaa.eleven.base.BaseService;
import com.aaa.eleven.base.CommonController;
import com.aaa.eleven.base.ResultData;
import com.aaa.eleven.model.MappingUnit;
import com.aaa.eleven.model.SpecialPost;
import com.aaa.eleven.service.SpecialPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Company
 * @Author ftt
 * @Date Create in 2020/7/14 21:43
 * @Description
 */
@RestController
public class SpecialPostController extends CommonController<SpecialPost> {
    @Autowired
    private SpecialPostService specialPostService;
    @Override
    public BaseService<SpecialPost> getBaseService() {
        return specialPostService;
    }
    /***
     * @Author ftt
     * @Description
     * 查询特殊岗位人员列表
     * @Date 2020/7/14 21:46
     * @Param [userId]
     * @return com.aaa.eleven.base.ResultData
     */
    @GetMapping("/specialPost/selectSpecialPostListByUserId")
    public ResultData selectSpecialPostListByUserId(@RequestParam("userId") long userId){
        List<SpecialPost> specialPosts = specialPostService.selectOne(userId);
        if(specialPosts.size()>0){
            return selectSuccess(specialPosts);
        }
        return selectFailed();

    }
}
