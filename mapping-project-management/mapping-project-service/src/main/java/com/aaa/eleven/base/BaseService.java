package com.aaa.eleven.base;

import com.aaa.eleven.utils.Map2BeanUtils;
import com.aaa.eleven.utils.SpringContextUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.aaa.eleven.staticproperties.StaticProperties.*;

/**
 * @Company
 * @Author ftt
 * @Date Create in 2020/7/9 19:46
 * @Description
 * 通用service
 */
public abstract class BaseService<T> {
    /*
    全局变量，存储子类泛型数据类型
     */
    private Class<T> cache=null;
    //这里是实体类的mapper
    @Autowired
    private Mapper<T> mapper;
    /***
     * @Author ftt
     * @Description
     * getMapper() 返回的是通用mapper  和实现泛型的mapper 集成了，以下使用的所有mapper都是通用mapper的方法
     * @Date 2020/7/9 19:52
     * @Param []
     * @return tk.mybatis.mapper.common.Mapper
     */
    protected Mapper getMapper(){
        return mapper;
    }
    /***
     * @Author ftt
     * @Description
     * 新增数据
     * @Date 2020/7/9 19:54
     * @Param [t]
     * @return java.lang.Integer
     */
    public Integer insert(T t){
        return mapper.insert(t);
    }
    /***
     * @Author ftt
     * @Description
     * 根据主键删除
     * @Date 2020/7/9 20:02
     * @Param [t]
     * @return java.lang.Integer
     */
    public Integer delete(T t){
        return mapper.deleteByPrimaryKey(t);
    }
    /***
     * @Author ftt
     * @Description
     * Sqls.custom().andIn("id",ids)
     * Sqls.custom()如果有条件，则where 条件 and id in [,,,]
     * 如果没条件 where 1=1 and id in [,,,]
     * builder(传的是类)
     *
     * 根据id批量删除
     *
     * @Date 2020/7/9 20:08
     * @Param [ids]
     * @return java.lang.Integer
     */
    public Integer batchDelete(List<Integer> ids){
        Example example = Example.builder(getTypeArguement()).where(Sqls.custom().andIn("id", ids)).build();
        return mapper.deleteByExample(example);
    }
    /***
     * @Author ftt
     * @Description
     * 更新数据
     * @Date 2020/7/9 20:01
     * @Param [t]
     * @return java.lang.Integer
     */
    public Integer update(T t){
        return mapper.updateByPrimaryKeySelective(t);
    }
    /***
     * @Author ftt
     * @Description
     * 批量修改
     * @Date 2020/7/9 20:48
     * @Param [t, ids]
     * @return java.lang.Integer
     */
    public Integer batchUpdate(T t, Integer[] ids) {
        Example example = Example.builder(getTypeArguement()).where(Sqls.custom().andIn("id", Arrays.asList(ids))).build();
        return mapper.updateByExample(t, example);
    }
    /***
     * @Author ftt
     * @Description
     * 根据 形参 t 查询一条数据
     * @Date 2020/7/9 20:52
     * @Param [t]
     * @return T
     */
    public T selectOne(T t){
       return mapper.selectOne(t);
    }

    /***
     * @Author ftt
     * @Description
     * 根据一个条件查询一个列表
     * @Date 2020/7/9 21:03
     * @Param [where, orderByField, fields]
     * @return java.util.List<T>
     */
    public List<T> selectListByFiled(Sqls where, String orderByField, String... fields) {
        return selectByFileds(null, null, where, orderByField, null, fields);
    }

    /***
     * @Author ftt
     * @Description
     * 根据条件实现分页查询
     * @Date 2020/7/9 21:04
     * @Param [pageNo, pageSize, where, orderFiled, fileds]
     * @return com.github.pagehelper.PageInfo<T>
     */
    public PageInfo<T> selectListByPageAndFiled(Integer pageNo, Integer pageSize, Sqls where, String orderFiled, String... fileds) {
        return new PageInfo<T>(selectByFileds(pageNo, pageSize, where, orderFiled, null, fileds));
    }
    /***
     * @Author ftt
     * @Description
     * 查询集合，条件查询
     * @Date 2020/7/9 21:05
     * @Param [t]
     * @return java.util.List<T>
     */
    public List<T> selectList(T t) {
        return mapper.select(t);
    }

    /**
     * 分页查询
     * @param t
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PageInfo<T> selectListByPage(T t, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<T> select = mapper.select(t);
        PageInfo<T> pageInfo = new PageInfo<T>(select);
        return pageInfo;
    }
    /***
     * @Author ftt
     * @Description 
     * 实现查询通用
     *    不但可以作用于分页，还可以作用于排序，还能作用于多条件查询
     *    fileds 是作用于排序的字段
     * @Date 2020/7/9 21:01
     * @Param [pageNo, pageSize, where, orderByFiled, orderWord, fileds]
     * @return java.util.List<T>
     */
    private List<T> selectByFileds(Integer pageNo, Integer pageSize, Sqls where, String orderByFiled, String orderWord, String... fileds) {
        Example.Builder builder = null;
        if(null == fileds || fileds.length == 0) {
            // 查询所有数据
            builder = Example.builder(getTypeArguement());
        } else {
            // 进行条件查询
            builder = Example.builder(getTypeArguement()).select(fileds);
        }
        if(where != null) {
            // 自定义的where语句条件
            builder = builder.where(where);
        }
        if(orderByFiled != null) {
            // 对某个字段进行排序
            if(DESC.equals(orderWord.toUpperCase())) {
                // 说明需要倒序
                builder = builder.orderByDesc(orderByFiled);
            } else if(ASC.equals(orderWord.toUpperCase())) {
                builder = builder.orderByAsc(orderByFiled);
            } else {
                builder = builder.orderByDesc(orderByFiled);
            }
        }
        Example example = builder.build();
        // 实现分页
        if(pageNo != null && pageSize != null) {
            PageHelper.startPage(pageNo, pageSize);
        }
        return getMapper().selectByExample(example);
    }

    /***
     * @Author ftt
     * @Description 
     * 获取子类泛型
     * @Date 2020/7/9 20:46
     * @Param []
     * @return java.lang.Class<T>
     */
    public Class<T> getTypeArguement() {
        if(null == cache) {
            cache = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        }
        return cache;
    }
    /***
     * @Author ftt
     * @Description
     * map 转 实体
     * @Date 2020/7/9 21:07
     * @Param [map]
     * @return T
     */
    public T newInstance(Map map) {
        return (T) Map2BeanUtils.map2Bean(map, getTypeArguement());
    }
    /***
     * @Author ftt
     * @Description
     *获取上下文/spring容器
     * @Date 2020/7/9 20:44
     * @Param []
     * @return org.springframework.context.ApplicationContext
     */
    public ApplicationContext getApplicationContext() {
        return SpringContextUtils.getApplicationContext();
    }

}
