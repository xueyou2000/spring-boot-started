package com.xueyou.controller;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 创建 by xueyo on 2019/7/22
 */
@RestController
@RequestMapping("/test")
public class TestController {

    private final AmqpTemplate amqpTemplate;

    private final AmqpAdmin amqpAdmin;

    public TestController(AmqpTemplate amqpTemplate, AmqpAdmin amqpAdmin) {
        this.amqpTemplate = amqpTemplate;
        this.amqpAdmin = amqpAdmin;
    }

    @GetMapping("/mq-send")
    public void testMqSend() {

    }

}
