package com.xueyou.config;

import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.IOException;


/**
 * 创建 by xueyo on 2019/7/22
 */
//@Configuration
//@ComponentScan
//@EnableCaching
//@Slf4j
//public class RedisConfig {
//
//    @Bean(destroyMethod="shutdown")
//    public RedissonClient redissonClient(@Value("classpath:/redisson.yaml") Resource configFile) throws IOException {
//        Config config = Config.fromYAML(configFile.getInputStream());
//        // config.setCodec(StringCodec.INSTANCE);
//        log.info("========从配置文件读取redisson客户端=================");
//        return Redisson.create(config);
//    }
//
//}
