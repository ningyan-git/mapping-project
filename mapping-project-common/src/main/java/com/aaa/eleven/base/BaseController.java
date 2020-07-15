package com.aaa.eleven.base;

import static com.aaa.eleven.status.Status.*;

/**
 *统一controller
 *    也就是说所有的controller都需要继承这个controller，进行统一返回
 * @Company
 * @Author ftt
 * @Date Create in 2020/7/8 21:21
 * @Description
 **/
public class BaseController {
    /***
    * 登录成功
    *  使用系统消息
    * @author ftt
    * @date 2020/7/8 
    * @returns com.aaa.eleven.base.ResultData
    */
    protected ResultData loginSuccess(){
        ResultData resultData = new ResultData();
        resultData.setCode(LOGIN_SUCCESS.getCode());
        resultData.setMsg(LOGIN_SUCCESS.getMsg());
        return resultData;
    }
    /***
     * 登录成功
     *  使用自定义消息
     * @author ftt
     * @date 2020/7/8
     * @returns com.aaa.eleven.base.ResultData
     */
    protected ResultData loginSuccess(String msg){
        ResultData resultData = new ResultData();
        resultData.setCode(LOGIN_SUCCESS.getCode());
        resultData.setMsg(msg);
        return resultData;
    }
    /***
     * 登录成功
     *  使用系统消息,返回数据
     * @author ftt
     * @date 2020/7/8
     * @returns com.aaa.eleven.base.ResultData
     */
    protected ResultData loginSuccess(Object data){
        ResultData resultData = new ResultData();
        resultData.setCode(LOGIN_SUCCESS.getCode());
        resultData.setData(data);
        resultData.setMsg(LOGIN_SUCCESS.getMsg());
        return resultData;
    }
    /***
     * 登录成功
     *  使用自定义消息，返回数据
     * @author ftt
     * @date 2020/7/8
     * @returns com.aaa.eleven.base.ResultData
     */
    protected ResultData loginSuccess(String msg,Object data){
        ResultData resultData = new ResultData();
        resultData.setCode(LOGIN_SUCCESS.getCode());
        resultData.setMsg(msg);
        resultData.setData(data);
        return resultData;
    }
    /**
    * 登录失败
    * 使用系统消息
    * @author ftt
    * @date 2020/7/8
    * @returns com.aaa.eleven.base.ResultData
    */
    protected ResultData loginFailed(){
        ResultData resultData = new ResultData();
        resultData.setCode(LOGIN_FAILED.getCode());
        resultData.setMsg(LOGIN_FAILED.getMsg());
        return resultData;
    }
    /**
     * 登录失败
     * 使用系统消息,返回数据
     * @author ftt
     * @date 2020/7/8
     * @returns com.aaa.eleven.base.ResultData
     */
    protected ResultData loginFailed(Object data ){
        ResultData resultData = new ResultData();
        resultData.setCode(LOGIN_FAILED.getCode());
        resultData.setMsg(LOGIN_FAILED.getMsg());
        resultData.setData(data);
        return resultData;
    }
    /**
     * 登录失败
     * 使用系统消息
     *  返回详细信息
     * @author ftt
     * @date 2020/7/8
     * @returns com.aaa.eleven.base.ResultData
     */
    protected ResultData loginFailed(String detail){
        ResultData resultData = new ResultData();
        resultData.setCode(LOGIN_FAILED.getCode());
        resultData.setMsg(LOGIN_FAILED.getMsg());
        resultData.setDetail(detail);
        return resultData;
    }

    /**
     * 登录失败
     * 使用自定义消息,返回数据
     * @author ftt
     * @date 2020/7/8
     * @returns com.aaa.eleven.base.ResultData
     */
    protected ResultData loginFailed(String msg,Object data){
        ResultData resultData = new ResultData();
        resultData.setCode(LOGIN_FAILED.getCode());
        resultData.setMsg(msg);
        resultData.setData(data);
        return resultData;
    }
    /**
     * @Date 2020/7/9
     * @Author ftt
     * @Description
     * 新增失败 使用系统消息
     * @Param
     * @return
     */
    protected ResultData insertFailed(){
        ResultData resultData = new ResultData();
        resultData.setCode(INSERT_FAILED.getCode());
        resultData.setMsg(INSERT_FAILED.getMsg());
        return resultData;
    }
    /***
     * @Author ftt
     * @Description 
     *  新增失败
     *  使用系统消息
     *    返回自定义数据
     * @Date 2020/7/9 18:19
     * @Param [data]
     * @return com.aaa.eleven.base.ResultData
     */
    protected ResultData insertFailed(Object data){
        ResultData resultData = new ResultData();
        resultData.setCode(INSERT_FAILED.getCode());
        resultData.setMsg(INSERT_FAILED.getMsg());
        resultData.setData(data);
        return resultData;
    }

    /**
     * @Author ftt
     * @Description 
     * 新增失败
     *    使用系统消息，返回详情
     * @Date 2020/7/9 17:41
     * @Param [detail]
     * @return com.aaa.eleven.base.ResultData
     */
    protected ResultData insertFailed(String detail){
        ResultData resultData = new ResultData();
        resultData.setCode(INSERT_FAILED.getCode());
        resultData.setMsg(INSERT_FAILED.getMsg());
        resultData.setDetail(detail);
        return resultData;
    }
    /***
     * @Author ftt
     * @Description
     *新增失败
     *   返回详情 返回数据
     * @Date 2020/7/9 18:27
     * @Param [detail, data]
     * @return com.aaa.eleven.base.ResultData
     */
    protected ResultData insertFailed(String detail,Object data){
        ResultData resultData = new ResultData();
        resultData.setCode(INSERT_FAILED.getCode());
        resultData.setMsg(INSERT_FAILED.getMsg());
        resultData.setDetail(detail);
        resultData.setData(data);
        return resultData;
    }
    /***
     * @Author ftt
     * @Description
     *新增成功
     *  使用自定义消息 返回数据
     * @Date 2020/7/9 18:28
     * @Param [msg, data]
     * @return com.aaa.eleven.base.ResultData
     */
    protected ResultData insertSuccess(String msg,Object data){
        ResultData resultData = new ResultData();
        resultData.setCode(INSERT_SUCCESS.getCode());
        resultData.setMsg(msg);
        resultData.setData(data);
        return resultData;
    }
    /***
     * @Author ftt
     * @Description
     *      新增成功
     *          自定义消息
     * @Date 2020/7/9 18:30
     * @Param [msg]
     * @return com.aaa.eleven.base.ResultData
     */
    protected ResultData insertSuccess(String msg){
        ResultData resultData = new ResultData();
        resultData.setCode(INSERT_SUCCESS.getCode());
        resultData.setMsg(msg);
        return resultData;
    }
    /***
     * @Author ftt
     * @Description
     *  新增成功
     *       使用系统消息
     * @Date 2020/7/9 18:34
     * @Param []
     * @return com.aaa.eleven.base.ResultData
     */
    protected ResultData insertSuccess(){
        ResultData resultData = new ResultData();
        resultData.setCode(INSERT_SUCCESS.getCode());
        resultData.setMsg(INSERT_SUCCESS.getMsg());
        return resultData;
    }
    /***
     * @Author ftt
     * @Description
     * 新增成功
     *      使用系统消息
     *      返回数据
     * @Date 2020/7/9 18:35
     * @Param [data]
     * @return com.aaa.eleven.base.ResultData
     */
    protected ResultData insertSuccess(Object data){
        ResultData resultData = new ResultData();
        resultData.setCode(INSERT_SUCCESS.getCode());
        resultData.setMsg(INSERT_SUCCESS.getMsg());
        resultData.setData(data);
        return resultData;
    }

    /***
     * @Author ftt
     * @Description
     * 删除成功
     *  使用系统消息
     * @Date 2020/7/9 18:49
     * @Param []
     * @return com.aaa.eleven.base.ResultData
     */
    protected  ResultData deleteSuccess(){
        ResultData resultData = new ResultData();
        resultData.setCode(DELETE_SUCCESS.getCode());
        resultData.setMsg(DELETE_SUCCESS.getMsg());
        return resultData;
    }
    /***
     * @Author ftt
     * @Description
     * 删除成功
     *      使用自定义消息
     * @Date 2020/7/9 18:55
     * @Param [msg]
     * @return com.aaa.eleven.base.ResultData
     */
    protected  ResultData deleteSuccess(String msg){
        ResultData resultData = new ResultData();
        resultData.setCode(DELETE_SUCCESS.getCode());
        resultData.setMsg(msg);
        return resultData;
    }
    /***
     * @Author ftt
     * @Description
     * 删除成功
     *  使用系统消息 返回数据
     * @Date 2020/7/9 18:56
     * @Param [data]
     * @return com.aaa.eleven.base.ResultData
     */
    protected  ResultData deleteSuccess(Object data){
        ResultData resultData = new ResultData();
        resultData.setCode(DELETE_SUCCESS.getCode());
        resultData.setMsg(DELETE_SUCCESS.getMsg());
        return resultData;
    }
    /***
     * @Author ftt
     * @Description
     * 删除成功
     *  使用自定义消息 返回数据
     * @Date 2020/7/9 19:15
     * @Param [msg, data]
     * @return com.aaa.eleven.base.ResultData
     */
    protected  ResultData deleteSuccess(String msg,Object data){
        ResultData resultData = new ResultData();
        resultData.setCode(DELETE_SUCCESS.getCode());
        resultData.setMsg(msg);
        resultData.setData(data);
        return resultData;
    }
    /***
     * @Author ftt
     * @Description
     * 删除失败
     *  使用系统消息
     * @Date 2020/7/9 19:16
     * @Param []
     * @return com.aaa.eleven.base.ResultData
     */
    protected  ResultData deleteFailed(){
        ResultData resultData = new ResultData();
        resultData.setCode(DELETE_FAILED.getCode());
        resultData.setMsg(DELETE_FAILED.getMsg());
        return resultData;
    }
    /***
     * @Author ftt
     * @Description
     * 删除失败
     * 返回显示详情
     * @Date 2020/7/9 19:18
     * @Param [detail]
     * @return com.aaa.eleven.base.ResultData
     */
    protected  ResultData deleteFailed(String detail){
        ResultData resultData = new ResultData();
        resultData.setCode(DELETE_FAILED.getCode());
        resultData.setMsg(DELETE_FAILED.getMsg());
        resultData.setDetail(detail);
        return resultData;
    }
    /***
     * @Author ftt
     * @Description
     * 删除失败
     *  使用系统消息，返回数据
     * @Date 2020/7/9 19:18
     * @Param [data]
     * @return com.aaa.eleven.base.ResultData
     */
    protected  ResultData deleteFailed(Object data){
        ResultData resultData = new ResultData();
        resultData.setCode(DELETE_FAILED.getCode());
        resultData.setMsg(DELETE_FAILED.getMsg());
        resultData.setData(data);
        return resultData;
    }
    /***
     * @Author ftt
     * @Description
     * 删除失败
     *  返回数据，和详情
     * @Date 2020/7/9 19:20
     * @Param [detail, data]
     * @return com.aaa.eleven.base.ResultData
     */
    protected  ResultData deleteFailed(String detail ,Object data){
        ResultData resultData = new ResultData();
        resultData.setCode(DELETE_FAILED.getCode());
        resultData.setMsg(DELETE_FAILED.getMsg());
        resultData.setDetail(detail);
        resultData.setData(data);
        return resultData;
    }
    /***
     * @Author ftt
     * @Description
     * 更新成功
     * 使用系统消息
     * @Date 2020/7/9 19:22
     * @Param []
     * @return com.aaa.eleven.base.ResultData
     */
    protected  ResultData updateSuccess(){
        ResultData resultData = new ResultData();
        resultData.setCode(UPDATE_SUCCESS.getCode());
        resultData.setMsg(UPDATE_SUCCESS.getMsg());
        return resultData;
    }
    /***
     * @Author ftt
     * @Description
     * 更新成功
     *  使用系统消息 返回数据
     * @Date 2020/7/9 19:23
     * @Param [data]
     * @return com.aaa.eleven.base.ResultData
     */
    protected  ResultData updateSuccess(Object data){
        ResultData resultData = new ResultData();
        resultData.setCode(UPDATE_SUCCESS.getCode());
        resultData.setMsg(UPDATE_SUCCESS.getMsg());
        resultData.setData(data);
        return resultData;
    }
    /***
     * @Author ftt
     * @Description
     * 更新成功
     * 使用自定义消息
     * @Date 2020/7/9 19:24
     * @Param [msg]
     * @return com.aaa.eleven.base.ResultData
     */
    protected  ResultData updateSuccess(String msg){
        ResultData resultData = new ResultData();
        resultData.setCode(UPDATE_SUCCESS.getCode());
        resultData.setMsg(msg);
        return resultData;
    }
    /***
     * @Author ftt
     * @Description
     * 更新成功
     * 使用自定义消息 返回数据
     * @Date 2020/7/9 19:26
     * @Param [msg, data]
     * @return com.aaa.eleven.base.ResultData
     */
    protected  ResultData updateSuccess(String msg,Object data){
        ResultData resultData = new ResultData();
        resultData.setCode(UPDATE_SUCCESS.getCode());
        resultData.setMsg(msg);
        resultData.setData(data);
        return resultData;
    }
    /***
     * @Author ftt
     * @Description
     * 更新失败
     *  使用系统消息
     * @Date 2020/7/9 19:27
     * @Param []
     * @return com.aaa.eleven.base.ResultData
     */
    protected  ResultData updateFailed(){
        ResultData resultData = new ResultData();
        resultData.setCode(UPDATE_FAILED.getCode());
        resultData.setMsg(UPDATE_FAILED.getMsg());
        return resultData;
    }
    /***
     * @Author ftt
     * @Description
     * 更新失败
     * 返回详情
     * @Date 2020/7/9 19:28
     * @Param [detail]
     * @return com.aaa.eleven.base.ResultData
     */
    protected  ResultData updateFailed(String detail ){
        ResultData resultData = new ResultData();
        resultData.setCode(UPDATE_FAILED.getCode());
        resultData.setMsg(UPDATE_FAILED.getMsg());
        resultData.setDetail(detail);
        return resultData;
    }
    /***
     * @Author ftt
     * @Description
     * 更新失败
     *  返回数据
     * @Date 2020/7/9 19:29
     * @Param [detail]
     * @return com.aaa.eleven.base.ResultData
     */
    protected  ResultData updateFailed(Object data ){
        ResultData resultData = new ResultData();
        resultData.setCode(UPDATE_FAILED.getCode());
        resultData.setMsg(UPDATE_FAILED.getMsg());
        resultData.setData(data);
        return resultData;
    }
    /***
     * @Author ftt
     * @Description
     * 更新失败
     * 返回详情和数据
     * @Date 2020/7/9 19:30
     * @Param [detail, data]
     * @return com.aaa.eleven.base.ResultData
     */
    protected  ResultData updateFailed(String detail,Object data ){
        ResultData resultData = new ResultData();
        resultData.setCode(UPDATE_FAILED.getCode());
        resultData.setMsg(UPDATE_FAILED.getMsg());
        resultData.setDetail(detail);
        resultData.setData(data);
        return resultData;
    }
    /***
     * @Author ftt
     * @Description
     * 查询成功
     * 使用系统消息
     * @Date 2020/7/9 19:32
     * @Param []
     * @return com.aaa.eleven.base.ResultData
     */
    protected  ResultData selectSuccess(){
        ResultData resultData = new ResultData();
        resultData.setCode(SELECT_SUCCESS.getCode());
        resultData.setMsg(SELECT_SUCCESS.getMsg());
        return resultData;
    }
    /***
     * @Author ftt
     * @Description
     * 查询成功
     * 使用自定义消息
     * @Date 2020/7/9 19:33
     * @Param [msg]
     * @return com.aaa.eleven.base.ResultData
     */
    protected  ResultData selectSuccess(String msg){
        ResultData resultData = new ResultData();
        resultData.setCode(SELECT_SUCCESS.getCode());
        resultData.setMsg(msg);
        return resultData;
    }
    /***
     * @Author ftt
     * @Description
     * 查询成功
     *  返回数据
     * @Date 2020/7/9 19:34
     * @Param [data]
     * @return com.aaa.eleven.base.ResultData
     */
    protected  ResultData selectSuccess(Object data){
        ResultData resultData = new ResultData();
        resultData.setCode(SELECT_SUCCESS.getCode());
        resultData.setMsg(SELECT_SUCCESS.getMsg());
        resultData.setData(data);
        return resultData;
    }
    /***
     * @Author ftt
     * @Description
     * 查询成功
     *  使用自定义消息 返回数据
     * @Date 2020/7/9 19:35
     * @Param [msg, data]
     * @return com.aaa.eleven.base.ResultData
     */
    protected  ResultData selectSuccess(String msg,Object data){
        ResultData resultData = new ResultData();
        resultData.setCode(SELECT_SUCCESS.getCode());
        resultData.setMsg(msg);
        resultData.setData(data);
        return resultData;
    }
    /***
     * @Author ftt
     * @Description
     * 查询失败
     *  使用系统消息
     * @Date 2020/7/9 19:37
     * @Param []
     * @return com.aaa.eleven.base.ResultData
     */
    protected  ResultData selectFailed(){
        ResultData resultData = new ResultData();
        resultData.setCode(SELECT_FAILED.getCode());
        resultData.setMsg(SELECT_FAILED.getMsg());
        return resultData;
    }
    /***
     * @Author ftt
     * @Description
     * 查询失败
     *  返回详情
     * @Date 2020/7/9 19:37
     * @Param [detail]
     * @return com.aaa.eleven.base.ResultData
     */
    protected  ResultData selectFailed(String detail){
        ResultData resultData = new ResultData();
        resultData.setCode(SELECT_FAILED.getCode());
        resultData.setMsg(SELECT_FAILED.getMsg());
        resultData.setDetail(detail);
        return resultData;
    }
    /***
     * @Author ftt
     * @Description
     * 查询失败
     *  返回数据
     * @Date 2020/7/9 19:38
     * @Param []
     * @return com.aaa.eleven.base.ResultData
     */
    protected  ResultData selectFailed(Object data){
        ResultData resultData = new ResultData();
        resultData.setCode(SELECT_FAILED.getCode());
        resultData.setMsg(SELECT_FAILED.getMsg());
        resultData.setData(data);
        return resultData;
    }
    /***
     * @Author ftt
     * @Description
     * 查询失败
     *  返回详情和数据
     * @Date 2020/7/9 19:39
     * @Param [detail, data]
     * @return com.aaa.eleven.base.ResultData
     */
    protected  ResultData selectFailed(String detail,Object data){
        ResultData resultData = new ResultData();
        resultData.setCode(SELECT_FAILED.getCode());
        resultData.setMsg(SELECT_FAILED.getMsg());
        resultData.setDetail(detail);
        resultData.setData(data);
        return resultData;
    }

}
