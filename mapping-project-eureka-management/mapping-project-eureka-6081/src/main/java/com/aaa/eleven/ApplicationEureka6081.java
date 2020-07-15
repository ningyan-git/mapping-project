package com.aaa.eleven;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Company
 * @Author ftt
 * @Date Create in 2020/7/14 18:35
 * @Description
 */
@SpringBootApplication
@EnableEurekaServer
public class ApplicationEureka6081 {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationEureka6081.class,args);
    }
}
