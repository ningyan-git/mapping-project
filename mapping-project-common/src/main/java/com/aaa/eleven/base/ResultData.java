package com.aaa.eleven.base;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 *统一controller返回值
 * @Company
 * @Author ftt
 * @Date Create in 2020/7/8 20:59
 * @Description
 **/
@Data
@Accessors(chain =  true)
public class ResultData<T> implements Serializable {
    private String code;
    private String msg;
    private String detail;
    private T data;

}
