package com.hhj.seckill.service.Impl;

import com.hhj.seckill.entry.SecOrder;
import com.hhj.seckill.mapper.SecOrderMapper;
import com.hhj.seckill.service.SecOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author virtual
 * @Date 2021/5/31 22:46
 * @Version 1.0
 */
@Service
public class SecOrderServiceImpl implements SecOrderService {
    @Autowired
    SecOrderMapper secOrderMapper;

    @Override
    public boolean generateOrder(int goodId, int userId, Date createTime) {
        boolean i = secOrderMapper.generateOrder(goodId, userId, createTime);
        return i;
    }
}
