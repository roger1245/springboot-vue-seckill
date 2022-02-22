package com.hhj.seckill.controller;

import com.github.pagehelper.PageInfo;
import com.hhj.seckill.common.Result;
import com.hhj.seckill.entry.Good;
import com.hhj.seckill.entry.Product;
import com.hhj.seckill.service.GoodService;
import com.hhj.seckill.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author virtual
 * @Date 2021/5/29 17:23
 * @Version 1.0
 */
@RestController
@RequestMapping("/good")
@Api("操作商品表")
public class GoodController {

    @Autowired
    GoodService service;
    @Autowired
    ProductService productService;

    @GetMapping("list")
    @ApiOperation("分页查询")
    public Result page(@RequestParam("curpage") int curPage, @RequestParam("size") int size){
        PageInfo<Good> goodPageInfo = service.selectPage(curPage, size);
        return Result.success(goodPageInfo);
    }
    @RequestMapping(value="/getProduct", method = RequestMethod.POST)
    public Result selectProductByCategoryId(@RequestBody Map<String, String> map) {
        String categoryId = map.get("category_ids");
        if (categoryId.isEmpty()) {
            return Result.error("no category ids");
        }
        List<Product> list = productService.getProductByCategoryId(Integer.parseInt(categoryId));
        return Result.success(list);
    }

    @RequestMapping(value="/getHotProduct", method =  RequestMethod.POST)
    public Result getHotProduct(@RequestBody Map<String, List<String>> map) {
        List<String> categoryIds = map.get("category_ids");
        if (categoryIds == null) {
            return Result.error("no category ids");
        }
        List<Product> list = productService.getHotProductByCategoryIds(categoryIds);
        return Result.success(list);
    }
}
