package com.aaa.eleven.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Company
 * @Author ftt
 * @Date Create in 2020/7/15 16:16
 * @Description
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginAnnotation {
    /**
     * 执行操作的类型
     * @return
     */
    String operationType() ;

    /**
     * 执行操作的内容
     * @return
     */
    String operationName() ;
}
