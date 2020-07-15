package com.aaa.eleven.service;

import com.aaa.eleven.annotation.LoginAnnotation;
import com.aaa.eleven.base.BaseService;
import com.aaa.eleven.model.User;
import com.aaa.eleven.vo.TokenVo;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @Company
 * @Author ftt
 * @Date Create in 2020/7/15 15:06
 * @Description
 */
@Service
public class LoginService extends BaseService<User> {
    /***
     * @Author ftt
     * @Description
     * 执行登录操作
     *      pojo ：实体类
     *      povo : 封装类型(多表联查，封装一个实体类view object)
     * @Date 2020/7/15 15:07
     * @Param [user]
     * @return com.aaa.eleven.vo.TokenVo
     */
    @LoginAnnotation(operationName = "添加登录日志",operationType = "登录操作")
    public TokenVo doLogin(User user){
        TokenVo tokenVo = new TokenVo();
        User user1 = new User();
        //判断user 是否为空
        if(null != user){
            user1.setUsername(user.getUsername());
            User user2 = selectOne(user1);
            //判断user2 是否为空
            if(null != user2){
                user1.setPassword(user.getPassword());
                User user3 = selectOne(user1);
                if(user3 != null){
                    //登录成功
                    //mybatis 会对连接符进行转义，所以把连接符替换掉
                    String token = UUID.randomUUID().toString().replaceAll("-","");
                    user3.setToken(token);
                    Integer updateResult = update(user3);
                    if(updateResult > 0){
                        tokenVo.setIfSuccess(true).setToken(token);
                    }else {
                        tokenVo.setIfSuccess(false).setType(4);
                        return tokenVo;
                    }

                }else {
                    tokenVo.setIfSuccess(false).setType(2);
                    return tokenVo;
                }
            }else {
                tokenVo.setIfSuccess(false).setType(1);
                return tokenVo;
            }
        }else {
            tokenVo.setIfSuccess(false).setType(2);
            return tokenVo;
        }
        return tokenVo;
    }
}
