package com.xueyou;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

/**
 * 创建 by xueyo on 2019/7/12
 */
@RunWith(SpringRunner.class)
@Slf4j
@SpringBootTest
public class ApplicationTest {

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

}
