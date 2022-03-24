package com.hhj.seckill.service;

import com.hhj.seckill.common.Result;
import com.hhj.seckill.common.enums.SeckillEnum;
import com.hhj.seckill.vo.Exposer;
import com.hhj.seckill.vo.SecKillOrder;


public interface SecKillService {

    
    public SeckillEnum doSecKill(SecKillOrder secKillOrder);

    
    public Exposer exposerSecAddress(int id);

    
    public boolean verifyMd5(String md5,int secId);

    public SeckillEnum seckill(SecKillOrder secKillOrder);
}
