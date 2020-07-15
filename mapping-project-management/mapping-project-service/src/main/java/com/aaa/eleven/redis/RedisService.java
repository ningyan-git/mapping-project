package com.aaa.eleven.redis;

import com.aaa.eleven.utils.JSONUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import redis.clients.jedis.JedisCluster;

import javax.annotation.PostConstruct;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;


import static com.aaa.eleven.staticproperties.RedisProperties.*;

/**
 * @Company
 * @Author ftt
 * @Date Create in 2020/7/10 16:01
 * @Description
 */
//@Service
public class RedisService<T> {
    //redis的序列化接口
    private RedisSerializer keySerializer = null;

   /***
    * @Author ftt
    * @Description
    * 初始化redis的key序列化器
    *  spring配置加载完毕之后会自动加载@PostConstruct注解修饰的方法
    * @Date 2020/7/10 16:06
    * @Param []
    * @return void
    */
   @PostConstruct
    public void initRedisSerializer(){
        if(this.keySerializer == null){
            this.keySerializer = new JdkSerializationRedisSerializer(this.getClass().getClassLoader());

        }
    }

    @Autowired
    private JedisCluster jedisCluster;
   /***
    * @Author ftt
    * @Description
    * nxxx 值是固定的
    *  nx 如果redis中key不存在，则就可以存储，反之则不存，适用于不同模块设置的redis的key一样，
    *       比如，不能订单模块和购物车模块设置的key一样，进行覆盖，导致脏读数据的产生
    *  xx 如果redis中key存在，则覆盖，反之则不存 适用于增删改之后查询数据的覆盖
    *
    * expx 值是固定的
    *  ex 失效时间的单位是秒
    *  px 失效时间的单位是毫秒
    * seconds 是指失效时间
    * @Date 2020/7/10 16:14
    * @Param [key, value, nxxx, expx, seconds]
    * @return java.lang.String
    */
   public String set(String key, T value,String nxxx,String expx, Integer seconds){
       if(null != seconds && 0 < seconds
               && (EX.equals(expx)||PX.equals(expx))
               && (XX.equals(nxxx)||NX.equals(nxxx))){
           //在存入数据的时候就上失效时间
           return jedisCluster.set(key, JSONUtils.toJsonString(value),nxxx,expx,seconds);
       }else {
           //不需要设置失效时间，但是需要判断是nx还是xx
           if(NX.equals(nxxx)){
               return String.valueOf(jedisCluster.setnx(key,JSONUtils.toJsonString(value)));
           }else if(XX.equals(nxxx)){
               return jedisCluster.set(key,JSONUtils.toJsonString(value));
           }
           return NO;
       }
   }
   /***
    * @Author ftt
    * @Description
    * 从redis中查询一条数据 get(key)
    * @Date 2020/7/10 16:39
    * @Param [key]
    * @return T
    */
   public T getOne(String key){
       return  (T) JSONUtils.toObject(jedisCluster.get(key),Object.class);
   }
   /***
    * @Author ftt
    * @Description
    * 从redis查询数据，value是一个字符串
    * @Date 2020/7/10 16:42
    * @Param [key]
    * @return java.lang.String
    */
   public String getString(String key){
       return jedisCluster.get(key);
   }
   /***
    * @Author ftt
    * @Description
    * 获取集合数据
    * @Date 2020/7/10 16:44
    * @Param [key]
    * @return java.util.List<T>
    */
   public List<T> getList(String key){
       return (List<T>) JSONUtils.toList(jedisCluster.get(key),Object.class);
   }
   /***
    * @Author ftt
    * @Description
    * 删除单个
    * @Date 2020/7/10 17:06
    * @Param [key]
    * @return java.lang.Long
    */
   public Long delOne(Object key){
       /**
        * JedisCluster 只能接收string类型的key，无法实现通用，需要把object对象转换成字节数组
        */
       return jedisCluster.del(rawKey(key));
   }
   /***
    * @Author ftt
    * @Description
    * 删除多个
    * @Date 2020/7/10 18:13
    * @Param [keys]
    * @return java.lang.Long
    */
   public  Long delMany(Collection<T> keys){
       /**
        * 删除多个使用循环有安全隐患
        *  如果除了最后一个，其他的数据中间可能没有删除掉，会出现没有实现目标效果的情况
        */
       if(CollectionUtils.isEmpty(keys)){
           return 0L;
       }else {
           byte[][] bytes = this.rawkeys(keys);
           return jedisCluster.del(bytes);
       }
   }

   /***
    * @Author ftt
    * @Description
    * 把object对象转化成字节数组
    * @Date 2020/7/10 17:09
    * @Param [key]
    * @return byte[]
    */
   private byte[] rawKey(Object key){
       //1 判断
       /**
        * Assert 是断言 就是用来判断
        * 如果key有值则会执行下面代码
        * 如果key 没值，直接return
        */
       Assert.notNull(key,"non null key required");
       //2 进行转化
       /**
        * A instanceof  B AB若是同一实例则返回tru ，否则返回false
        */
       return this.keySerializer == null && key instanceof byte[] ? (byte[]) key : this.keySerializer.serialize(key);
       /**
        * 以上代码 等价于
        * if(key == null) {
        *             System.out.println("key不存在");
        *             return null;
        *         } else {
        *             if(keySerializer == null && key instanceof byte[]) {
        *                 // 直接转换
        *                 return (byte[]) key;
        *             } else {
        *                 // 说明条件不满足，需要进行转换
        *                 return keySerializer.serialize(key);
        *             }
        *         }
        */
   }
   /***
    * @Author ftt
    * @Description
    * 利用二维数组进行collection集合转化
    * @Date 2020/7/10 18:09
    * @Param [keys]
    * @return byte[][]
    */
   private byte[][] rawkeys(Collection<T> keys){
       byte[][]rks = new byte[keys.size()][];
       int i = 0;
       Object key;
       for(Iterator iterator = keys.iterator();iterator.hasNext();rks[i++] = this.rawKey(key)){
           key = iterator.next();
       }
       return rks;
   }














}
