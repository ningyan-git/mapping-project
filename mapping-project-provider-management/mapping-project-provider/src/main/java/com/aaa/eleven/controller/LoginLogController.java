package com.aaa.eleven.controller;

import com.aaa.eleven.base.BaseService;
import com.aaa.eleven.base.CommonController;
import com.aaa.eleven.model.LoginLog;
import com.aaa.eleven.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Company
 * @Author ftt
 * @Date Create in 2020/7/15 17:08
 * @Description
 */
@RestController
public class LoginLogController extends CommonController<LoginLog> {
    @Autowired
    private LoginLogService loginLogService;
    @Override
    public BaseService<LoginLog> getBaseService() {
        return loginLogService;
    }
    /***
     * @Author ftt
     * @Description
     * 新增日志
     * @Date 2020/7/15 17:11
     * @Param [loginLog]
     * @return java.lang.Integer
     */
    @PostMapping("/addLoginLog")
    public Integer addLoginLog(@RequestBody LoginLog loginLog){
        return loginLogService.insert(loginLog);
    }
}
