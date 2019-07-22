package com.xueyou;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RBucket;
import org.redisson.api.RList;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.concurrent.TimeUnit;

/**
 * 创建 by xueyo on 2019/7/12
 */
@RunWith(SpringRunner.class)
@Slf4j
@SpringBootTest
public class ApplicationTest {

    @Autowired
    private RedissonClient redissonClient;

    @Test
    public void uriComponents() {
        URI uri = UriComponentsBuilder
                    .fromUriString("http://example.com/hotels/{hotel}?q={q}")
                .build("Westin", "123");
        log.info("URI: {}", uri);
        log.info("Host: {}", uri.getHost());
        log.info("Path: {}", uri.getPath());
        log.info("Query: {}", uri.getQuery());
    }

    @Test
    public void redissonTest() {
        RBucket<String> bucket =  redissonClient.getBucket("name");
        bucket.set("XueYou2");
//        bucket.expire(30, TimeUnit.SECONDS);
        log.info("name={}", bucket.get());

        RList<String> list = redissonClient.getList("languages", StringCodec.INSTANCE);
        list.add("C++");
        list.add("C#");
        list.add("Java");
    }

}
