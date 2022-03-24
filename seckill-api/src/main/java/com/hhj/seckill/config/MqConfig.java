package com.hhj.seckill.config;

import org.springframework.context.annotation.Configuration;


@Configuration
public class MqConfig {

    // 秒杀订单队列
    public static final String SEC_QUEUE_ORDER="SEC_QUEUE_ORDER";
    public static final String SEC_TOPIC="seckill";





//    @Bean
//    public MessageConverter messageConverter(){
//        return new Jackson2JsonMessageConverter();
//    }
//
//    // 声明队列
//    @Bean
//    public Queue queue(){
//        // 配置：持久化此队列
//        return QueueBuilder.durable(SEC_QUEUE_ORDER).build();
//    }
//
//    // 声明交换机
//    @Bean
//    public Exchange exchange(){
//        return ExchangeBuilder.directExchange(SEC_EXCHANGE).durable(true).autoDelete().build();
//    }
//
//    // 绑定队列和交换机
//    @Bean
//    public Binding binding(@Qualifier("queue")Queue queue,@Qualifier("exchange")Exchange exchange){
//        return  BindingBuilder.bind(queue).to(exchange).with("").noargs();
//    }

}
