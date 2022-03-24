package com.hhj.seckill.service;

import com.github.pagehelper.PageInfo;
import com.hhj.seckill.entry.Good;

import java.util.List;


public interface GoodService {
    
    public List<Good> selectList();

    
    public PageInfo<Good> selectPage(int curPage, int size);
}
