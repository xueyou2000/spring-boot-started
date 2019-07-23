package com.xueyou.rabbitmq;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

/**
 * 创建 by xueyo on 2019/7/22
 * 消息生产者
 */
@Component
public class Producer {

    private final AmqpTemplate template;
    private final AmqpAdmin admin;

    public Producer(AmqpTemplate template, AmqpAdmin admin) {
        this.template = template;
        this.admin = admin;
    }

    /**
     * 发送消息 - 根据路由
     * @param routingKey    路由键
     * @param msg   消息
     */
    public void sendDirectMsg(String routingKey, Object msg) {
        template.convertAndSend(routingKey, msg);
    }


    /**
     * 发送消息 - 根据路由和指定交换器
     * @param exchange  交换器名称
     * @param routingKey    路由键
     * @param msg   消息
     */
    public void sendExchangeMsg(String exchange, String routingKey, Object msg) {
        template.convertAndSend(exchange, routingKey, msg);
    }


}
