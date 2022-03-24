package com.hhj.seckill.service;

import com.github.pagehelper.PageInfo;
import com.hhj.seckill.entry.SeckillProduct;

import java.util.List;


public interface SecProductService {
    
    public int add(SeckillProduct seckillProduct);

    public List<SeckillProduct> selectAllSecList();


    
    public PageInfo<SeckillProduct> selectSecPage(int curPage, int size);

    
    public SeckillProduct selectSecByProductId(int id);

    
    public int reduceSecStock(int id);

    
//    boolean prepare(int id);
}
