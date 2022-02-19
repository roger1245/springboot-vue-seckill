package com.hhj.seckill.mapper;

import com.hhj.seckill.entry.Carousel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CarouselMapper {

    List<Carousel> selectList();
}
