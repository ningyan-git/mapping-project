package com.aaa.eleven.status;

/**
 * 状态码
 * @Company
 * @Author ftt
 * @Date Create in 2020/7/8 21:04
 * @Description
 **/
public enum  Status {
    /**
     * 状态码：登录成功
     */
    LOGIN_SUCCESS("2001","登录成功"),
    /**
     * 状态码：新增成功
     */
    DELETE_SUCCESS("2002","新增成功"),
    /**
     * 状态码：删除成功
     */
    INSERT_SUCCESS("2003","删除成功"),
    /**
     * 状态码：修改成功
     */
    UPDATE_SUCCESS("2004","修改成功"),
    /**
     * 状态码：查询成功
     */
    SELECT_SUCCESS("2005","查询成功"),
    /**
     * 状态码：路由过滤成功
     */
    ZUUL_FILTER_SUCCESS("2006", "路由过滤成功"),
    /**
     * 状态码：token值存在
     */
    TOKEN_EXIST("2007", "token值存在"),
    /**
     * 状态码：登录失败,系统异常
     */
    LOGIN_FAILED("4001","登录失败,系统异常"),
    /**
     * 状态码：用户已存在
     */
    USER_EXIST("4002","用户已存在"),
    /**
     * 状态码：用户不存在
     */
    USER_NOT_EXIST("4003","用户不存在"),
    /**
     * 状态码：密码错误
     */
    PASSWORD_WRONG("4004","密码错误"),
    /**
     * 状态码：账号被锁定
     */
    USER_LOCKED("4005","账号被锁定"),
    /**
     * 状态码：用户异常退出
     */
    LOGOUT_WRONG("4006","用户异常退出"),
    /**
     * 状态码：新增失败
     */
    DELETE_FAILED("4007","新增失败"),
    /**
     * 状态码：删除失败
     */
    INSERT_FAILED("4008","删除失败"),
    /**
     * 状态码：修改失败
     */
    UPDATE_FAILED("4009","修改失败"),
    /**
     * 状态码：查询失败
     */
    SELECT_FAILED("4010","查询失败"),
    /**
     * 状态码：token值不存在
     */
    TOKEN_NOT_EXIST("4012", "token值不存在"),
    /**
     * 状态码：路由过滤失败
     */
    ZUUL_FILTER_FAILED("4011", "路由过滤失败"),
    /**
     * 状态码：request对象为null
     */
    REQUEST_IS_NULL("10", "request对象为null");


    Status(String code,String msg){
        this.code=code;
        this.msg=msg;
    }
    private String code;
    private String msg;

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
