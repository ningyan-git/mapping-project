package com.aaa.eleven.utils;

import java.util.Random;

/**
 * @Company
 * @Author ftt
 * @Date Create in 2020/7/10 19:02
 * @Description
 */
public class FileNameUtils {
    private FileNameUtils(){}

    /***
     * @Author ftt
     * @Description
     * 文件名的生成
     * @Date 2020/7/10 19:08
     * @Param []
     * @return java.lang.String
     */
    public static String getFileName() {
        //获取当前系统时间的毫秒数
        long currentTimeMillis = System.currentTimeMillis();
        //创建随机数对象
        Random random = new Random();
        //随机
        int num = random.nextInt(999);
        //生成最终文件名
        return currentTimeMillis + String.format("%03d",num);
    }
}
