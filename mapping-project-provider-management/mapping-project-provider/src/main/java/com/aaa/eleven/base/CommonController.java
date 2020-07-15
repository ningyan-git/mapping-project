package com.aaa.eleven.base;

import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @Company
 * @Author ftt
 * @Date Create in 2020/7/9 21:12
 * @Description
 */
public abstract class CommonController<T> extends BaseController {
    /***
     * @Author ftt
     * @Description
     * 钩子函数
     * 在新增方法之前执行的操作
     * @Date 2020/7/9 21:14
     * @Param [map]
     * @return void
     */
    protected void beforeInsert(Map map){
        //TODO
    }
    /***
     * @Author ftt
     * @Description
     * 钩子函数
     *  在新增方法之后执行的操作
     * @Date 2020/7/9 21:16
     * @Param [map]
     * @return void
     */
    protected  void afterInsert(Map map){
        //TODO
    }

    /***
     * @Author ftt
     * @Description
     * 钩子函数
     * 在修改方法之前执行的操作
     * @Date 2020/7/9 21:14
     * @Param [map]
     * @return void
     */
    protected void beforeUpdate(Map map){
        //TODO
    }
    /***
     * @Author ftt
     * @Description
     * 钩子函数
     *  在修改方法之后执行的操作
     * @Date 2020/7/9 21:16
     * @Param [map]
     * @return void
     */
    protected  void afterUpdate(Map map){
        //TODO
    }
    /***
     * @Author ftt
     * @Description
     * 钩子函数
     * 在删除方法之前执行的操作
     * @Date 2020/7/9 21:14
     * @Param [map]
     * @return void
     */
    protected void beforeDelete(Map map){
        //TODO
    }
    /***
     * @Author ftt
     * @Description
     * 钩子函数
     *  在删除方法之后执行的操作
     * @Date 2020/7/9 21:16
     * @Param [map]
     * @return void
     */
    protected  void afterDelete(Map map){
        //TODO
    }

    public abstract BaseService<T> getBaseService();

    /***
     * @Author ftt
     * @Description 
     * 新增
     * @Date 2020/7/9 21:37
     * @Param [map]
     * @return com.aaa.eleven.base.ResultData
     */
    public ResultData insert(@RequestBody Map map) {
        //钩子函数的使用
        beforeInsert(map);
        // 1.Map转实体类
        T instance = getBaseService().newInstance(map);
        if(null != instance && "".equals(instance)) {
            // 2.通用service
            Integer addResult = getBaseService().insert(instance);
            if (addResult > 0) {
                //钩子函数的使用
                afterInsert(map);
                return insertSuccess();
            }
        }
        return insertFailed();
    }
    /***
     * @Author ftt
     * @Description
     * 删除操作
     * @Date 2020/7/9 21:39
     * @Param [map]
     * @return com.aaa.eleven.base.ResultData
     */
    public ResultData delete(@RequestBody Map map) {
        T instance = getBaseService().newInstance(map);
        if(null != instance && "".equals(instance)){
            Integer deleteResult = getBaseService().delete(instance);
            if(deleteResult > 0) {
                return super.deleteSuccess();
            }
        }
        return super.deleteFailed();
    }
    /***
     * @Author ftt
     * @Description
     * 批量删除
     * @Date 2020/7/9 21:40
     * @Param [ids]
     * @return com.aaa.eleven.base.ResultData
     */
    public ResultData batchDelete(@RequestParam("ids[]") Integer[] ids) {
        Integer deleteResult = getBaseService().batchDelete(Arrays.asList(ids));
        if(deleteResult > 0) {
            return super.deleteSuccess();
        }
        return super.deleteFailed();
    }
    /***
     * @Author ftt
     * @Description
     * 修改操作
     * @Date 2020/7/9 21:41
     * @Param [map]
     * @return com.aaa.eleven.base.ResultData
     */
    public ResultData update(@RequestBody Map map) {
        T instance = getBaseService().newInstance(map);
        if(null != instance && "".equals(instance)) {
            Integer updateResult = getBaseService().update(instance);
            if (updateResult > 0) {
                return super.updateSuccess();
            }
        }
        return super.updateFailed();
    }
    /***
     * @Author ftt
     * @Description
     * 批量修改
     * @Date 2020/7/9 21:45
     * @Param [map, ids]
     * @return com.aaa.eleven.base.ResultData
     */
    public ResultData batchUpdate(@RequestBody Map map,@RequestParam("ids[]") Integer[] ids) {
        T instance = getBaseService().newInstance(map);
        if(null != instance && "".equals(instance)) {
            Integer updateResult = getBaseService().batchUpdate(instance, ids);
            if (updateResult > 0) {
                return super.updateSuccess();
            }
        }
        return super.updateFailed();
    }
    /***
     * @Author ftt
     * @Description
     * 查询一条数据
     * @Date 2020/7/9 21:50
     * @Param [map]
     * @return com.aaa.eleven.base.ResultData
     */
    public ResultData selectOne(@RequestBody Map map){
        T instance = getBaseService().newInstance(map);
        if(null != instance && "".equals(instance)) {
            T result = getBaseService().selectOne(instance);
            if (null != result) {
                return super.selectSuccess(result);
            }
        }
        return super.selectFailed();

    }
    /***
     * @Author ftt
     * @Description
     * 根据一个条件查询一个列表
     * @Date 2020/7/9 21:56
     * @Param []
     * @return com.aaa.eleven.base.ResultData
     */
    public  ResultData selectListByFiled(@RequestBody Map map,@RequestParam("orderByField") String orderByField,@RequestParam("fields") String... fields){
        //TODO
        return null;
    }
    /***
     * @Author ftt
     * @Description
     * 根据条件实现分页查询
     * @Date 2020/7/9 21:58
     * @Param []
     * @return com.aaa.eleven.base.ResultData
     */
    public  ResultData selectListByPageAndFiled(@RequestBody Map map,@RequestParam("pageNo") Integer pageNo,@RequestParam("pageSize") Integer pageSize,@RequestParam("orderField") String orderField,@RequestParam("fields") String... fields){
        //TODO
        return null;
    }
    /***
     * @Author ftt
     * @Description
     *查询集合，条件查询
     * @Date 2020/7/9 22:02
     * @Param [map]
     * @return com.aaa.eleven.base.ResultData
     */
    public ResultData selectList(@RequestBody Map map){
        T instance = getBaseService().newInstance(map);
        if(null != instance && "".equals(instance)){
            List<T> result = getBaseService().selectList(instance);
            if(null!=result) {
                return super.selectSuccess(result);
            }
        }
        return super.selectFailed();

    }
    /***
     * @Author ftt
     * @Description
     * 分页查询
     * @Date 2020/7/9 22:05
     * @Param [map, pageNo, pageSize]
     * @return com.aaa.eleven.base.ResultData
     */
    public ResultData selectListByPage(@RequestBody Map map,@RequestParam("pageNo") Integer pageNo,@RequestParam("pageSize") Integer pageSize){
        T instance = getBaseService().newInstance(map);
        if(null != instance && "".equals(instance)){
            PageInfo<T> result = getBaseService().selectListByPage(instance,pageNo,pageSize);
            if(null!=result) {
                return super.selectSuccess(result);
            }
        }
        return super.selectFailed();
    }
    /***
     * @Author ftt
     * @Description 
     * 防止数据不安全，所以不能直接在controller某个方法中直接接收HttpServletRequest对象
     *      必须要从本地当前线程中获取对象
     * @Date 2020/7/11 8:55
     * @Param []
     * @return javax.servlet.http.HttpServletRequest
     */
    public HttpServletRequest getServletRequest(){
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes;
        if (requestAttributes instanceof ServletRequestAttributes) {
            servletRequestAttributes = (ServletRequestAttributes)requestAttributes;
            return servletRequestAttributes.getRequest();
        }
        return null;
    }
    /***
     * @Author ftt
     * @Description
     * 获取当前session对象，如果不存在，就会再创建一个
     * @Date 2020/7/11 8:56
     * @Param []
     * @return javax.servlet.http.HttpSession
     */
    public HttpSession getSession(){
        return getServletRequest().getSession();
    }
    /***
     * @Author ftt
     * @Description
     * 获取当前session对象，如果不存在，就会直接返回null
     * @Date 2020/7/11 8:58
     * @Param []
     * @return javax.servlet.http.HttpSession
     */
    public HttpSession getExistSession(){
        return getServletRequest().getSession(false);
    }


}
