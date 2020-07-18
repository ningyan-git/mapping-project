package com.aaa.eleven.controller;

import com.aaa.eleven.base.BaseService;
import com.aaa.eleven.base.CommonController;
import com.aaa.eleven.base.ResultData;
import com.aaa.eleven.model.CheckPerson;
import com.aaa.eleven.model.Equipment;
import com.aaa.eleven.service.CheckPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Company
 * @Author ftt
 * @Date Create in 2020/7/17 18:46
 * @Description
 */
@RestController
public class CheckPersonController extends CommonController<CheckPerson> {
    @Autowired
    private CheckPersonService checkPersonService;
    @Override
    public BaseService<CheckPerson> getBaseService() {
        return checkPersonService;
    }
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
        CheckPerson checkPerson = checkPersonService.selectCheckPersonOne(id);
        if(checkPerson != null){
            return selectSuccess(checkPerson);
        }else {
            return selectFailed();
        }
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
    public ResultData insertCheckPerson(@RequestBody  CheckPerson checkPerson){
        Boolean flag = checkPersonService.insertCheckPersonOne(checkPerson);
        if(flag){
            return insertSuccess();
        }else {
            return insertFailed();
        }
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
        Boolean flag = checkPersonService.updateCheckPersonOne(checkPerson);
        if(flag){
            return updateSuccess();
        }else {
            return updateFailed();
        }
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
        Boolean flag = checkPersonService.deleteCheckPersonOne(checkPerson);
        if(flag){
            return deleteSuccess();
        }else {
            return deleteFailed();
        }
    }
}
