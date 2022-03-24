package com.hhj.seckill.mq;

import com.hhj.seckill.config.MqConfig;
import com.hhj.seckill.vo.SecKillOrder;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Component
@Slf4j
public class MqSender {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    public void sendOrder(SecKillOrder secKillOrder){
        rocketMQTemplate.asyncSend(MqConfig.SEC_TOPIC, secKillOrder, new SendCallback() {
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
