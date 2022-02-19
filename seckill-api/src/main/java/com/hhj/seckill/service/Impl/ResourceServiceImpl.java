package com.hhj.seckill.service.Impl;

import com.hhj.seckill.entry.Carousel;
import com.hhj.seckill.mapper.CarouselMapper;
import com.hhj.seckill.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ResourceServiceImpl implements ResourceService {
    @Autowired
    CarouselMapper carouselMapper;
    @Override
    public List<Carousel> selectList() {
        return carouselMapper.selectList();
    }
}
