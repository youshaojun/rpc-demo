package com.example.rpc.registry.config;

import org.springframework.data.redis.connection.RedisStandaloneConfiguration;

import java.util.Optional;

/**
 * @author yousj
 * @date 2021/8/7
 */
public class MyRedisStandaloneConfiguration extends RedisStandaloneConfiguration {

    public MyRedisStandaloneConfiguration(String hostName, String password, Integer port, Integer database) {
        Optional.ofNullable(hostName).ifPresent(e -> super.setHostName(hostName));
        Optional.ofNullable(password).ifPresent(e -> super.setPassword(password));
        Optional.ofNullable(port).ifPresent(e -> super.setPort(port));
        Optional.ofNullable(database).ifPresent(e -> super.setDatabase(database));
    }
}
