package com.aaa.eleven.controller;

import com.aaa.eleven.base.BaseService;
import com.aaa.eleven.base.CommonController;
import com.aaa.eleven.base.ResultData;
import com.aaa.eleven.model.SpecialPost;
import com.aaa.eleven.model.Technicist;
import com.aaa.eleven.service.TechnicistService;
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
 * @Date Create in 2020/7/14 21:57
 * @Description
 */
@RestController
public class TechnicistController extends CommonController<Technicist> {
    @Autowired
    private TechnicistService technicistService;
    @Override
    public BaseService<Technicist> getBaseService() {
        return technicistService;
    }
    /***
     * @Author ftt
     * @Description
     * 查询技术人员列表
     * @Date 2020/7/14 21:46
     * @Param [userId]
     * @return com.aaa.eleven.base.ResultData
     */
    @GetMapping("/technicist/selectTechnicistListByUserId")
    public ResultData selectTechnicistListByUserId(@RequestParam(value = "curpage",required = false,defaultValue = "1")int curpage, @RequestParam(value = "pagesize",required = false,defaultValue = "5")int pagesize,@RequestParam("userId") long userId){
        PageInfo pageInfo = technicistService.selectOne(curpage, pagesize, userId);
        if(pageInfo !=null){
            return selectSuccess(pageInfo);
        }
        return selectFailed();

    }
    /***
     * @Author ftt
     * @Description
     * 根据id查询Technicist表的信息
     * @Date 2020/7/16 8:39
     * @Param [id]
     * @return com.aaa.eleven.base.ResultData
     */
    @GetMapping("/technicist/selectTechnicistDetail")
    public ResultData selectTechnicistDetail(@RequestParam("id") Long id ){
        List<Map<String, Object>> maps = technicistService.selectTechnicistDetail(id);
        if(null != maps ){
            return selectSuccess(maps);
        }else {
            return selectFailed();
        }
    }
    /***
     * @Author ftt
     * @Description
     * 新增技术人员信息
     * @Date 2020/7/16 18:39
     * @Param [technicist]
     * @return com.aaa.eleven.base.ResultData
     */
    @PostMapping("/technicist/insertTechnicist")
    public ResultData insertTechnicist(@RequestBody Technicist technicist){
        Boolean i = technicistService.insertTechnicist(technicist);
        if(i ){
            return insertSuccess();
        }
        return insertFailed();
    }
    /***
     * @Author ftt
     * @Description
     * 修改技术人员信息
     * @Date 2020/7/16 18:39
     * @Param [technicist]
     * @return com.aaa.eleven.base.ResultData
     */
    @PostMapping("/technicist/updateTechnicist")
    public ResultData updateTechnicist(@RequestBody Technicist technicist){
        Boolean i = technicistService.updateTechnicist(technicist);
        if(i){
            return updateSuccess();
        }
        return updateFailed();
    }
    /***
     * @Author ftt
     * @Description
     * 删除技术人员信息
     * @Date 2020/7/16 18:39
     * @Param [technicist]
     * @return com.aaa.eleven.base.ResultData
     */
    @PostMapping("/technicist/deleteTechnicist")
    public ResultData deleteTechnicist(@RequestBody Technicist technicist){
        Boolean i = technicistService.deleteTechnicist(technicist);
        if(i){
            return deleteSuccess();
        }
        return deleteFailed();
    }
}
