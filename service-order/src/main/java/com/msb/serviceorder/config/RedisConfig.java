package com.msb.serviceorder.config;

import com.msb.dao.ResponseResult;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RedisConfig {
    @Bean
    public RedissonClient RedisClient(){
        Config config = new Config();
        config.useSingleServer().setAddress("redis://192.168.121.134:6379").setDatabase(0);
        return Redisson.create(config);
    }
}
