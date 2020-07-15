package com.aaa.eleven.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Company
 * @Author ftt
 * @Date Create in 2020/7/9 20:31
 * @Description
 */
public class SpringContextUtils implements ApplicationContextAware {
    private static ApplicationContext APPLICATION_CONTEXT = null;
    private static final ReadWriteLock READ_WRITE_LOCK = new ReentrantReadWriteLock();

    private SpringContextUtils() {

    }
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    /**
     * 虽然已经把自己写的spring上下文代替了spring自带的
     * 当spring开始运行加载的时候，仍然会去把spring配置文件覆盖自定义编写的
     * 此时需要把自己的配置文件上锁，加载的时候被覆盖
     */
        SpringContextUtils.APPLICATION_CONTEXT = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        Lock lock = READ_WRITE_LOCK.readLock();
        lock.lock();
        try {
            if(null != APPLICATION_CONTEXT) {
                return APPLICATION_CONTEXT;
            } else {
                return null;
            }
        } finally {
            //配置文件上锁后。需要解锁，不然自己也不能用
            lock.unlock();
        }
    }
}
