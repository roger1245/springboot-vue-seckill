package com.hhj.seckill.service;

import com.github.pagehelper.PageInfo;
import com.hhj.seckill.entry.SeckillProduct;

import java.util.List;

/**
 * @Author virtual
 * @Date 2021/5/29 22:48
 * @Version 1.0
 */
public interface SecProductService {
    /**
     * 添加一个秒杀商品
     */
    public int add(SeckillProduct seckillProduct);

    public List<SeckillProduct> selectAllSecList();


    /**
     * 分页查询所有的商品
     * @return
     */
    public PageInfo<SeckillProduct> selectSecPage(int curPage, int size);

    /**
     * 通过id查询秒杀商品
     * 连表查询
     */
    public SeckillProduct selectSecByProductId(int id);

    /**
     * 通过秒杀流水号减库存
     * @param id
     */
    public int reduceSecStock(int id);

    /**
     * 秒杀商品库存预热
     */
//    boolean prepare(int id);
}
