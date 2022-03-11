package com.hhj.seckill.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hhj.seckill.entry.SecOrder;
import com.hhj.seckill.entry.UniOrder;
import com.hhj.seckill.entry.UniOrderExample;
import com.hhj.seckill.mapper.SecOrderMapper;
import com.hhj.seckill.mapper.UniOrderMapper;
import com.hhj.seckill.service.SecOrderService;
import com.hhj.seckill.vo.SecKillOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author virtual
 * @Date 2021/5/31 22:46
 * @Version 1.0
 */
@Service
public class SecOrderServiceImpl implements SecOrderService {
    @Autowired
    SecOrderMapper secOrderMapper;

    @Autowired
    UniOrderMapper orderMapper;

    @Override
    public PageInfo<SecOrder> selectPage(int curPage, int size) {
        PageHelper.startPage(curPage,size);
        List<SecOrder> goods = secOrderMapper.selectList();
        Page page = PageHelper.startPage(curPage, size);
        PageInfo<SecOrder> info = new PageInfo<>(goods);

        return info;
    }

    @Override
    public List<UniOrder> newSelectPage(int userId) {
        UniOrderExample example = new UniOrderExample();
        example.createCriteria().andUserIdEqualTo(userId);
        return orderMapper.selectByExample(example);
    }

    @Override
    public boolean generateOrder(SecKillOrder secKillOrder) {
        return secOrderMapper.generateOrder(secKillOrder);
    }

    public boolean newGenerateOrder(UniOrder order) {
        int ret  =  orderMapper.insert(order);
        return ret != 0;
    }

    @Override
    public boolean payById(int id, Date payTime) {
        boolean b = secOrderMapper.payById(id, payTime);
        return b;
    }

    @Override
    public Long selectBySecIdAndUserId(int secId, int userId) {
        Long l = secOrderMapper.selectBySecIdAndUserId(secId, userId);
        return l;
    }
}
