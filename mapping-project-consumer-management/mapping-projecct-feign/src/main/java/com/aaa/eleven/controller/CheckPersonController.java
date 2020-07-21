package com.aaa.eleven.controller;

import com.aaa.eleven.base.ResultData;
import com.aaa.eleven.model.CheckPerson;
import com.aaa.eleven.service.MappingProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Company
 * @Author ftt
 * @Date Create in 2020/7/21 15:53
 * @Description
 */
@RestController
public class CheckPersonController {
    @Autowired
    private MappingProjectService mappingProjectService;
    /***
     * @Author ftt
     * @Description
     * 查询抽查人员具体信息
     * @Date 2020/7/17 18:53
     * @Param [id]
     * @return com.aaa.eleven.base.ResultData
     */
    @GetMapping("/checkPerson/selectCheckPerson")
    public ResultData selectCheckPerson(@RequestParam("id") Long id){
        return mappingProjectService.selectCheckPerson(id);
    }
    /***
     * @Author ftt
     * @Description
     * 新增抽查人员
     * @Date 2020/7/17 18:49
     * @Param [checkPerson]
     * @return com.aaa.eleven.base.ResultData
     */
    @PostMapping("/checkPerson/insertCheckPerson")
    public ResultData insertCheckPerson(@RequestBody CheckPerson checkPerson){
        return mappingProjectService.insertCheckPerson(checkPerson);
    }
    /***
     * @Author ftt
     * @Description
     * 修改抽查人员
     * @Date 2020/7/17 18:49
     * @Param [checkPerson]
     * @return com.aaa.eleven.base.ResultData
     */
    @PostMapping("/checkPerson/updateCheckPerson")
    public ResultData updateCheckPerson(@RequestBody  CheckPerson checkPerson){
        return mappingProjectService.updateCheckPerson(checkPerson);
    }
    /***
     * @Author ftt
     * @Description
     * 删除抽查人员
     * @Date 2020/7/17 18:49
     * @Param [checkPerson]
     * @return com.aaa.eleven.base.ResultData
     */
    @PostMapping("/checkPerson/deleteCheckPerson")
    public ResultData deleteCheckPerson(@RequestBody  CheckPerson checkPerson){
        return mappingProjectService.deleteCheckPerson(checkPerson);
    }
}
