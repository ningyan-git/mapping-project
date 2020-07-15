package com.aaa.eleven.controller;

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
    @PostMapping("/doLogin")
    public ResultData doLogin(@RequestBody User user){
        return mappingProjectService.doLogin(user);
    }
}
