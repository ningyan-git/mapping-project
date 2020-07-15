package com.aaa.eleven.config;

import com.aaa.eleven.properties.RedisClusterProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

/**
 * @Company
 * @Author ftt
 * @Date Create in 2020/7/10 15:18
 * @Description
 */
//@Configuration
public class RedisClusterConfig {
    @Autowired
    private RedisClusterProperties redisClusterProperties;

    /***
     * @Author ftt
     * @Description
     * 配置redis集群
     * @Date 2020/7/10 16:13
     * @Param []
     * @return redis.clients.jedis.JedisCluster
     */
    @Bean
    public JedisCluster getJedisCluster(){
        String nodes = redisClusterProperties.getNodes();
        String[] split = nodes.split(",");
        Set<HostAndPort> hostAndPorts = new HashSet<HostAndPort>();
        for (String hostPort : split){
            String[] split1 = hostPort.split(":");
            HostAndPort hostAndPort = new HostAndPort(split1[0],Integer.parseInt(split1[1]));
            hostAndPorts.add(hostAndPort);
        }
        return new JedisCluster(hostAndPorts,redisClusterProperties.getCommandTimeout(),redisClusterProperties.getMaxAttempts());
    }

}
