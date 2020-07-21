package com.aaa.eleven.controller;

import com.aaa.eleven.base.ResultData;
import com.aaa.eleven.model.Technicist;
import com.aaa.eleven.service.MappingProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Company
 * @Author ftt
 * @Date Create in 2020/7/14 22:07
 * @Description
 */
@RestController
public class TechnicistController {
    @Autowired
    private MappingProjectService mappingProjectService;
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
        return mappingProjectService.selectTechnicistListByUserId(curpage, pagesize, userId);

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
        return mappingProjectService.selectTechnicistDetail(id);
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
        return mappingProjectService.insertTechnicist(technicist);
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
        return mappingProjectService.updateTechnicist(technicist);

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
        return mappingProjectService.deleteTechnicist(technicist);
    }
}
