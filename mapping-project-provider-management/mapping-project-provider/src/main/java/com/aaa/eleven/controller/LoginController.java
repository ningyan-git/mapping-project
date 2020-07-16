package com.aaa.eleven.controller;

import com.aaa.eleven.base.BaseService;
import com.aaa.eleven.base.CommonController;
import com.aaa.eleven.base.ResultData;
import com.aaa.eleven.model.User;
import com.aaa.eleven.service.LoginService;
import com.aaa.eleven.vo.TokenVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.aaa.eleven.status.Status.*;

/**
 * @Company
 * @Author ftt
 * @Date Create in 2020/7/15 15:23
 * @Description
 */
@RestController
public class LoginController extends CommonController<User> {
    @Autowired
    private LoginService loginService;
    @Override
    public BaseService<User> getBaseService() {
        return loginService;
    }
    /***
     * @Author ftt
     * @Description
     * 执行登录操作
     * @Date 2020/7/15 16:10
     * @Param [user]
     * @return com.aaa.eleven.base.ResultData
     */
    @PostMapping("/doLogin")
    public ResultData doLogin(@RequestBody User user){
        TokenVo tokenVo = loginService.doLogin(user);
        if(tokenVo.getIfSuccess()){
            return loginSuccess((Object)(tokenVo.getToken()));
        }else {
            switch (tokenVo.getType()){
                case 1:
                    return loginFailed(USER_NOT_EXIST.getMsg());
                case 2:
                    return loginFailed(PASSWORD_WRONG.getMsg());
                case 3:
                    return loginFailed(USER_LOCKED.getMsg());
                default:return loginFailed(LOGIN_FAILED.getMsg());
            }
        }
    }
}
