package com.hhj.seckill.mq;

import com.hhj.seckill.config.MqConfig;
import com.hhj.seckill.service.SecKillService;
import com.hhj.seckill.vo.SecKillOrder;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author virtual
 * @Date 2021/6/5 23:15
 * @Version 1.0
 */
@Component
@Slf4j
@RocketMQMessageListener(topic = MqConfig.SEC_TOPIC, consumerGroup = "mall-fake")
public class MqConsumer implements RocketMQListener<SecKillOrder> {
    @Autowired
    SecKillService secKillService;

    @Override
    public void onMessage(SecKillOrder secKillOrder) {
        log.info("receive delay message with secId:{} userId:{}", secKillOrder.getSecId(), secKillOrder.getUserId());
        secKillService.seckill(secKillOrder);
    }
}
