package com.hhj.seckill.controller;

import com.github.pagehelper.PageInfo;
import com.hhj.seckill.common.Result;
import com.hhj.seckill.entry.Carousel;
import com.hhj.seckill.entry.Good;
import com.hhj.seckill.service.ResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resources")
@Api("资源操作")
public class ResourceController {

    @Autowired
    ResourceService service;

    @PostMapping("carousel")
    @ApiOperation("获取轮播列表")
    public Result getCarousel(){
        List<Carousel> list= service.selectList();
        return Result.success(list);
    }


}
