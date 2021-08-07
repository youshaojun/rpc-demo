package com.example.rpc.registry.config;

import org.springframework.data.redis.connection.RedisStandaloneConfiguration;

/**
 * @author yousj
 * @date 2021/8/7
 */
public class MyRedisStandaloneConfiguration extends RedisStandaloneConfiguration {
    public MyRedisStandaloneConfiguration(String hostName,String password,int port, int database){
        super.setHostName(hostName);
        super.setPassword(password);
        super.setPort(port);
        super.setDatabase(database);
    }
}
