package com.hhj.seckill.service.Impl;

import cn.hutool.core.util.RandomUtil;
import com.hhj.seckill.common.enums.ErrorEnum;
import com.hhj.seckill.common.enums.SeckillEnum;
import com.hhj.seckill.common.excetion.CommonException;
import com.hhj.seckill.common.util.MdUtil;
import com.hhj.seckill.common.util.RedisUtil;
import com.hhj.seckill.entry.SeckillProduct;
import com.hhj.seckill.entry.UniOrder;
import com.hhj.seckill.mq.MqSender;
import com.hhj.seckill.service.SecKillService;
import com.hhj.seckill.service.OrderService;
import com.hhj.seckill.service.SecProductService;
import com.hhj.seckill.vo.Exposer;
import com.hhj.seckill.vo.SecKillOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Slf4j
public class SecKillServiceImpl implements SecKillService {

    @Autowired
    SecProductService secGoodService;

    @Autowired
    OrderService orderService;

    @Autowired
    MdUtil util;

    @Autowired
    MqSender sender;

    @Autowired
    RedisUtil redisUtil;

    private final String SEC_SALT="dfalskfjalsjfalk3485309-`-324348sfjaerj";

    private final String SEC_KILL_EXPOSER="seckill:exposer:";

    private final String SEC_KILL_STOCK="seckill:stock:";

    private final String SEC_kill_USER="seckill:secId:user:";


    @Override
    public SeckillEnum doSecKill (SecKillOrder secKillOrder) throws  RuntimeException{

        // 重复消费判断
        // 默认过期时间为两天
        // 这里过期时间应该要修改 但是又怕会影响性能 TODO
        StringBuilder append = new StringBuilder().append(SEC_kill_USER).append(secKillOrder.getSecId()).append(":").append(secKillOrder.getUserId());
        // TODO 每次都要从redis中拿实在是浪费时间，应该搞个库存预热从本地拿
//        Long exire = redisUtil.getExire(SEC_KILL_STOCK + secKillOrder.getSecId());
        if(!redisUtil.setnx(append.toString(), 1, 2 )){
            // 重复购买了
            throw new CommonException(ErrorEnum.REPEAT);
        };

        // 查看redis中库存是否大于0 lua脚本保证原子性
        long stock = redisUtil.luaStock(SEC_KILL_STOCK + secKillOrder.getSecId());
        log.info("当前库存为{}",stock);

        if(stock < 0)    throw new CommonException(ErrorEnum.STOCK_ZERT);

        // 订单放入消息队列
        sender.sendOrder(secKillOrder);

        return SeckillEnum.WAIT_QUEUE;


    }

    @Override
    @Transactional(rollbackFor = CommonException.class)
    public SeckillEnum seckill(SecKillOrder secKillOrder){
        // 生成订单
        UniOrder order = new UniOrder();
        order.setOrderId("seckill:" + String.valueOf(RandomUtil.randomInt(1, 1000000000)));
        order.setUserId(secKillOrder.getUserId());
        order.setProductId(secKillOrder.getProductId());
        order.setProductNum(secKillOrder.getProduct_num());
        order.setProductPrice((double) secKillOrder.getProduct_price());
        order.setOrderTime(secKillOrder.getCreateTime());

        boolean res2 = orderService.newGenerateOrder(order);
        if(!res2){
            // 生成订单失败
            throw new CommonException(ErrorEnum.REPEAT);
        }

        // 减库存
        int res = secGoodService.reduceSecStock(secKillOrder.getSecId());
        if(res < 1){// 减库存失败
            throw new CommonException(ErrorEnum.STOCK_ZERT);
        }
        return SeckillEnum.SUCCESS;
    }

    @Override
    public Exposer exposerSecAddress(int product_id) {
        SeckillProduct secGood = secGoodService.selectSecByProductId(product_id);
        if(secGood==null){
            // 没有该商品，秒杀信息被篡改
            throw new CommonException(SeckillEnum.DATE_REWRITE.getMsg());
        }else{
            // 查看redis中有没有缓存
            if(redisUtil.hasKey(SEC_KILL_EXPOSER + secGood.getSeckillId())){
                log.info("商品{}秒杀暴露接口命中缓存",product_id);
                return redisUtil.getObj(SEC_KILL_EXPOSER + secGood.getSeckillId(), Exposer.class);
            }

            long now = System.currentTimeMillis();
            long start = secGood.getStartDate().getTime();
            long end = secGood.getEndDate().getTime();

            if(now < start || end < now){
                return new Exposer(false,null,secGood.getSeckillId(),now,start,end);
            }else {
                // 返回真正地址
                String md5 = util.md5(secGood.getSeckillId() + "", SEC_SALT);
                Exposer exposer = new Exposer(true, md5, secGood.getSeckillId(), now, start, end);
                redisUtil.set(SEC_KILL_EXPOSER+secGood.getSeckillId(),
                                exposer,(end-now)/1000
                        );

                // 秒杀商品库存预热，不这里设置lua脚本会出错
                redisUtil.set(SEC_KILL_STOCK+secGood.getSeckillId(),
                        secGood.getStockCount(),
                        (end-now)/1000);

                return exposer;
            }

        }
    }

    @Override
    public boolean verifyMd5(String md5,int secId) {
        String verify = util.md5(secId + "", SEC_SALT);
        if (md5.equals(verify)){
            return true;
        }
        return false;
    }


}
