package com.hhj.seckill.mq;

import com.hhj.seckill.config.RabbitMqConfig;
import com.hhj.seckill.vo.SecKillOrder;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author virtual
 * @Date 2021/6/5 22:12
 * @Version 1.0
 */
@Component
@Slf4j
public class MqSender {

//    @Autowired
//    RabbitTemplate template;
//
//    @Autowired
//    RedisUtil util;
//
//    @PostConstruct
//    public void init(){
//        template.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
//            @Override
//            public void confirm(CorrelationData correlationData, boolean b, String s) {
//                if (b){
//                    log.info("消息发送成功，订单请求进入MQ");
//                }else {
//                    log.info("消息发送失败confirm，失败原因：{},库存回滚",s);
////                    System.out.println(correlationData.getId());
//                    String id = correlationData.getId();
//                    util.incr("seckill:stock:"+id);
//
//
//                }
//            }
//        });
//
//        template.setReturnCallback(new RabbitTemplate.ReturnCallback() {
//
//            @Override
//            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
//                log.info("returnCallback被退回的信息为：{}",message);
//                log.info("replayText:{}",replyText);
//                // 重新发送被退回的信息
////                template.convertAndSend(RabbitMqConfig.SEC_EXCHANGE,"",message.getBody().toString());
//            }
//        });
//    }
//
//
//
//    public void sendOrder(SecKillOrder secKillOrder){
//
//        template.convertAndSend(RabbitMqConfig.SEC_EXCHANGE, "", secKillOrder, new MessagePostProcessor() {
//                    @Override
//                    public Message postProcessMessage(Message message) throws AmqpException {
//                        return message;
//                    }
//                },
//                new CorrelationData(secKillOrder.getSecId() + ""));
//        log.info("成功发送订单{}到消息队列",secKillOrder);
//    }

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    public void sendOrder(SecKillOrder secKillOrder){
        rocketMQTemplate.asyncSend(RabbitMqConfig.SEC_TOPIC, secKillOrder, new SendCallback() {
            @Override
            public void onSuccess(SendResult var1) {
                log.info("成功发送订单 secId:{} userId:{} 到消息队列", secKillOrder.getSecId(), secKillOrder.getUserId());
            }

            @Override
            public void onException(Throwable var1) {
                log.info("发送订单出错" + var1.toString());
            }

        });
    }
}
