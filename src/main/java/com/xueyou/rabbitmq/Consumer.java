package com.xueyou.rabbitmq;

import com.xueyou.model.pojo.RabbitBroker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * 创建 by xueyo on 2019/7/22
 * 消息消费者
 */
@Component
@Slf4j
public class Consumer {

    @RabbitListener(queues = "xueyou")
    public void processMessageA(String msg) {
        log.info("RebbitMQ接收消息(A): -----[{}]-----\n", msg);
    }

    @RabbitListener(queues = "fanout.b")
    public void processMessageB(@Payload String msg) {
        log.info("RebbitMQ接收消息(B): -----[{}]-----\n", msg);
    }

    @RabbitListener(queues = "fanout.b")
    public void processMessageC(@Payload String msg) {
        log.info("RebbitMQ接收消息(C): -----[{}]-----\n", msg);
    }

    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(value = "fanout.exchange", durable = "true", type = "fanout"),
            value = @Queue(value = "fanout_queue", durable = "true"),
            key = "com.xueyou"
    ))
    public void processMessageD(@Payload RabbitBroker rabbitBroker) {
        log.info("RebbitMQ接收消息(D): -----[{}]-----\n", rabbitBroker);
    }

}
