package com.aaa.eleven.controller;

import com.aaa.eleven.base.ResultData;
import com.aaa.eleven.model.SpecialPost;
import com.aaa.eleven.service.MappingProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Company
 * @Author ftt
 * @Date Create in 2020/7/14 22:08
 * @Description
 */
@RestController
public class SpecialPostController {
    @Autowired
    private MappingProjectService mappingProjectService;
    /***
     * @Author ftt
     * @Description
     * 查询特殊岗位人员列表
     * @Date 2020/7/14 21:46
     * @Param [userId]
     * @return com.aaa.eleven.base.ResultData
     */
    @GetMapping("/specialPost/selectSpecialPostListByUserId")
    public ResultData selectSpecialPostListByUserId(@RequestParam(value = "curpage",required = false,defaultValue = "1")int curpage, @RequestParam(value = "pagesize",required = false,defaultValue = "5")int pagesize,@RequestParam("userId") long userId){
       return mappingProjectService.selectSpecialPostListByUserId(curpage, pagesize, userId);
    }

    /***
     * @Author ftt
     * @Description
     * 查询特殊岗位人员信息
     * @Date 2020/7/16 12:18
     * @Param [id]
     * @return com.aaa.eleven.base.ResultData
     */
    @GetMapping("/specialPost/selectSpecialPostDetail")
    public ResultData selectSpecialPostDetail(@RequestParam("id") Long id){
        return mappingProjectService.selectSpecialPostDetail(id);
    }
    /***
     * @Author ftt
     * @Description
     * 新增特殊岗位人员信息
     * @Date 2020/7/16 18:32
     * @Param [specialPost]
     * @return com.aaa.eleven.base.ResultData
     */
    @PostMapping("/specialPost/insertSpecialPost")
    public ResultData insertSpecialPost(@RequestBody SpecialPost specialPost){
        return mappingProjectService.insertSpecialPost(specialPost);
    }
    /***
     * @Author ftt
     * @Description
     * 修改特殊岗位人员信息
     * @Date 2020/7/16 18:32
     * @Param [specialPost]
     * @return com.aaa.eleven.base.ResultData
     */
    @PostMapping("/specialPost/updateSpecialPost")
    public ResultData updateSpecialPost(@RequestBody SpecialPost specialPost){
        return mappingProjectService.updateSpecialPost(specialPost);

    }
    /***
     * @Author ftt
     * @Description
     * 删除特殊岗位人员信息
     * @Date 2020/7/16 18:32
     * @Param [specialPost]
     * @return com.aaa.eleven.base.ResultData
     */
    @PostMapping("/specialPost/deleteSpecialPost")
    public ResultData deleteSpecialPost(@RequestBody SpecialPost specialPost){
        return mappingProjectService.deleteSpecialPost(specialPost);

    }
}
