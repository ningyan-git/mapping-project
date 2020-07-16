package com.aaa.eleven;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Company
 * @Author ftt
 * @Date Create in 2020/7/14 14:38
 * @Description
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ApplicationConsumer7081 {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationConsumer7081.class,args);
    }
}
