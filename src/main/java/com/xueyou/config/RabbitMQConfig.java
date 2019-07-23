package com.xueyou.config;

import com.rabbitmq.client.AMQP;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.RabbitConnectionFactoryBean;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.Console;

/**
 * 创建 by xueyo on 2019/7/23
 */
@Configuration
public class RabbitMQConfig {

    // 单播交换器名
    private static final String directExchangeName = "direct-exchange";
    // 多播交换器名
    private static final String fanoutExchangeName = "fanout-exchange";
    // 选择性多播交换器名
    private static final String topicExchangeName = "topic-exchange";

    /**
     * 配置RabbitTemplate, 增加json转换器
     * @param factoryBean
     * @return
     */
    @Bean
    public RabbitTemplate rabbitTemplate(CachingConnectionFactory factoryBean) {
        RabbitTemplate template = new RabbitTemplate(factoryBean);
        template.setMessageConverter(jsonMessageConverter());
        return template;
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    /**
     * 声明消息队列
     * @return 返回消息队列
     */
    @Bean
    public Queue queue() {
        return new Queue("xueyou");
    }

    /**
     * 声明消息队列
     * @return 返回消息队列
     */
    @Bean
    public Queue queueB() {
        return new Queue("fanout.b");
    }


//    /**
//     * 声明direct单播交换器
//     * @return 返回单播交换器
//     */
//    @Bean
//    DirectExchange directExchange() {
//        return new DirectExchange(directExchangeName);
//    }
//
//    /**
//     * 绑定消息队列和direct交换器, 并指定路由键 "xueyou"
//     * @param queue 消息队列
//     * @param directExchange direct交换器,
//     * @return 绑定器
//     */
//    @Bean
//    Binding directBinding(Queue queue, DirectExchange directExchange) {
//        return BindingBuilder.bind(queue).to(directExchange).with("xueyou");
//    }

    /**
     * 声明topic选择性多播交换器
     * @return 返回选择性多播交换器
     */
    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange(topicExchangeName);
    }

    /**
     * 绑定消息队列和topic交换器, 并指定路由键 "org.xueyou.#"
     * @param queue 消息队列
     * @param topicExchange topic交换器
     * @return 绑定器
     */
    @Bean
    Binding topicBinding(Queue queue, TopicExchange topicExchange) {
        return BindingBuilder.bind(queue).to(topicExchange).with("org.xueyou.#");
    }

    /**
     * 声明多播交换器
     * @return 返回多播交换器
     */
    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange(fanoutExchangeName);
    }

    /**
     * 绑定消息队列和fanout交换器
     * @param queueB 消息队列
     * @param fanoutExchange fanout交换器
     * @return 绑定器
     */
    @Bean
    Binding fanoutBinding(Queue queueB, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queueB).to(fanoutExchange);
    }

}
