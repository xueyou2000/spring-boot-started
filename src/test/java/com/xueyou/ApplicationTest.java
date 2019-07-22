package com.xueyou;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RBucket;
import org.redisson.api.RList;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 创建 by xueyo on 2019/7/12
 */
@RunWith(SpringRunner.class)
@Slf4j
@SpringBootTest
public class ApplicationTest {

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private RabbitProperties rabbitProperties;

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

    @Test
    public void mqTest() throws Exception {
        mqRecv();
        
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(rabbitProperties.getHost());
        factory.setPort(rabbitProperties.getPort());
        factory.setUsername(rabbitProperties.getUsername());
        factory.setPassword(rabbitProperties.getPassword());
        factory.setVirtualHost(rabbitProperties.getVirtualHost());

        try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel()) {
            channel.queueDeclare("My_Queue", false, false, false, null);
            String message = "Hello World";
            channel.basicPublish("", "My_Queue", null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");

        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void mqRecv() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(rabbitProperties.getHost());
        factory.setPort(rabbitProperties.getPort());
        factory.setUsername(rabbitProperties.getUsername());
        factory.setPassword(rabbitProperties.getPassword());
        factory.setVirtualHost(rabbitProperties.getVirtualHost());

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("My_Queue", false, false, false, null);
        System.out.println("[*]等待消息。退出按CTRL + C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received " + message );
        };

        channel.basicConsume("My_Queue", true, deliverCallback, consumerTag -> {});
    }

}
