package com.aaa.eleven;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Company
 * @Author ftt
 * @Date Create in 2020/7/14 14:26
 * @Description
 *
 * EnableDiscoveryClient eureka的客户端注解
 * EnableDiscoveryClient 熔断注解
 */
@SpringBootApplication
@MapperScan("com.aaa.eleven")
@EnableDiscoveryClient
@EnableCircuitBreaker
public class ApplicationProvider8081 {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationProvider8081.class,args);
    }
}
