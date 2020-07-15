package com.aaa.eleven.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Company
 * @Author ftt
 * @Date Create in 2020/7/15 15:01
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class TokenVo implements Serializable {
    private String token;
    private Boolean ifSuccess;
    /**
     * type 的值
     * 1 账号不存在
     * 2 密码错误
     * 3 账号被锁定
     * 4 系统异常
     */
    private Integer type;
}
