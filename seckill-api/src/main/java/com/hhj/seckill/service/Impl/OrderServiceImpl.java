package com.hhj.seckill.service.Impl;

import com.github.pagehelper.PageInfo;
import com.hhj.seckill.entry.OrderDetail;
import com.hhj.seckill.entry.SecOrder;
import com.hhj.seckill.entry.UniOrder;
import com.hhj.seckill.entry.UniOrderExample;
import com.hhj.seckill.mapper.OrderMapper;
import com.hhj.seckill.mapper.UniOrderMapper;
import com.hhj.seckill.service.OrderService;
import com.hhj.seckill.vo.SecKillOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper orderDetailMapper;

    @Autowired
    UniOrderMapper orderMapper;

    @Override
    public PageInfo<SecOrder> selectPage(int curPage, int size) {
//        PageHelper.startPage(curPage,size);
//        List<SecOrder> goods = secOrderMapper.selectList();
//        Page page = PageHelper.startPage(curPage, size);
//        PageInfo<SecOrder> info = new PageInfo<>(goods);

        return null;
    }

    @Override
    public List<UniOrder> newSelectPage(int userId) {
        UniOrderExample example = new UniOrderExample();
        example.createCriteria().andUserIdEqualTo(userId);
        return orderMapper.selectByExample(example);
    }

    @Override
    public boolean generateOrder(SecKillOrder secKillOrder) {
//        return secOrderMapper.generateOrder(secKillOrder);
        return false;
    }

    public boolean newGenerateOrder(UniOrder order) {
        int ret  =  orderMapper.insert(order);
        return ret != 0;
    }

    @Override
    public boolean payById(int id, Date payTime) {
//        boolean b = secOrderMapper.payById(id, payTime);
        return false;
    }

    @Override
    public Long selectBySecIdAndUserId(int secId, int userId) {
//        Long l = secOrderMapper.selectBySecIdAndUserId(secId, userId);
        return 1L;
    }

    public List<OrderDetail> selectOrderDetailByUserId(int userId) {
        return orderDetailMapper.selectUniOrderAndProductDetailByUserId(userId);
    }
}
