package com.aaa.eleven.controller;

import com.aaa.eleven.annotation.LoginAnnotation;
import com.aaa.eleven.base.BaseController;
import com.aaa.eleven.base.ResultData;
import com.aaa.eleven.model.User;
import com.aaa.eleven.service.MappingProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Company
 * @Author ftt
 * @Date Create in 2020/7/15 16:13
 * @Description
 */
@RestController
public class LoginController extends BaseController {
    @Autowired
    private MappingProjectService mappingProjectService;
    /***
     * @Author ftt
     * @Description
     * 登录操作
     * @Date 2020/7/15 19:24
     * @Param [user]
     * @return com.aaa.eleven.base.ResultData
     */
    @LoginAnnotation(operationType = "登录操作",operationName = "新增日志记录")
    @PostMapping("/doLogin")
    public ResultData doLogin(@RequestBody User user){
        return mappingProjectService.doLogin(user);
    }
}
