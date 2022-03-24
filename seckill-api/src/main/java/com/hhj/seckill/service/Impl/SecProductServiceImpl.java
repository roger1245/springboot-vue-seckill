package com.hhj.seckill.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hhj.seckill.common.util.MdUtil;
import com.hhj.seckill.common.util.RedisUtil;
import com.hhj.seckill.entry.SeckillProduct;
import com.hhj.seckill.entry.SeckillProductExample;
import com.hhj.seckill.mapper.SeckillProductMapper;
import com.hhj.seckill.service.SecProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;


@Service
public class SecProductServiceImpl implements SecProductService {

    private final String SEC_SALT="dfalskfjalsjfalk3485309-`-324348sfjaerj";

    private final String SEC_KILL_EXPOSER="seckill:exposer:";

    private final String SEC_KILL_STOCK="seckill:stock:";

    @Autowired
    SeckillProductMapper seckillProductMapper;

    @Autowired
    MdUtil util;

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    HashMap map;


    public int add(SeckillProduct seckillProduct) {
        return seckillProductMapper.insert(seckillProduct);
    }

    public List<SeckillProduct> selectAllSecList() {
        SeckillProductExample seckillProductExample = new SeckillProductExample();
        return seckillProductMapper.selectByExample(seckillProductExample);
    }


    public PageInfo<SeckillProduct> selectSecPage(int curPage, int size) {
        PageHelper.startPage(curPage,size);
        List<SeckillProduct> goods = selectAllSecList();
        PageInfo<SeckillProduct> info = new PageInfo<>(goods);
        return info;
    }

    public SeckillProduct selectSecByProductId(int id) {
        SeckillProductExample seckillProductExample = new SeckillProductExample();
        seckillProductExample.createCriteria().andProductIdEqualTo((long) id);
        List<SeckillProduct> ret =  seckillProductMapper.selectByExample(seckillProductExample);
        if (ret.size() >= 1) {
            return ret.get(0);
        } else {
            return null;
        }
    }

    public int reduceSecStock(int id) {
        SeckillProduct product = selectSecByProductId(id);
        int count = product.getStockCount();
        if (count >= 1) {
            product.setStockCount(count - 1);
        }
        return seckillProductMapper.updateByPrimaryKey(product);
    }

//    @Override
//    public boolean prepare(int id) {
//        // redis中存储秒杀暴露接口和秒杀商品库存
//        SeckillProduct secGood = selectSecById(id);
//        long now = System.currentTimeMillis();
//        long end = secGood.getEndDate().getTime();
//
//        if(end < now){
//            throw new CommonException(ErrorEnum.END);
//        }else {
//            //秒杀地址预热
//            String md5 = util.md5(id + "", SEC_SALT);
//            Exposer exposer = new Exposer(true, md5, secGood.getSeckillId(), 0, 0, 0);
//            redisUtil.set(SEC_KILL_EXPOSER+id,
//                    exposer,(end-now)/1000
//            );
//
//        }
//
//        // 秒杀商品库存预热
//        redisUtil.set(SEC_KILL_STOCK+secGood.getSeckillId(),
//                secGood.getStockCount(),
//                (end-now)/1000);
//        return true;
//
//    }
}
