package com.example.rpc.registry.config;

import lombok.Data;

import java.util.Map;


/**
 * @author yousj
 * @date 2021/8/7
 */
@Data
public class RedisTemplateConfig {

    private Map<String, MyRedisStandaloneConfiguration> redisTemplateConfigs;

}
