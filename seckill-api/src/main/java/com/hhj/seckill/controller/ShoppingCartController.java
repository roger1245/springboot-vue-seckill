package com.hhj.seckill.controller;

import com.hhj.seckill.common.Result;
import com.hhj.seckill.entry.ShoppingCart;
import com.hhj.seckill.service.ShoppingCartService;
import com.hhj.seckill.vo.ShoppingCartVo;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/shoppingcart")
@Api("购物车")
@Slf4j
public class ShoppingCartController {

    @Autowired
    ShoppingCartService service;

    @PostMapping("add")
    public Result add(@RequestBody @Valid ShoppingCartVo cart) {
        int add = service.addShoppingCart(cart);
        if (add > 0) {
            log.info("新增了一个购物车商品");
            return Result.success(null, "操作成功");
        }
        return Result.error("操作失败");
    }

//    @GetMapping("list")
//    @ApiOperation("分页查询")
//    public Result page(int curpage, int size) {
//        PageInfo<SeckillProduct> secGoodPageInfo = secGoodService.selectSecPage(curpage, size);
//        return Result.success(secGoodPageInfo);
//    }
//
//    @GetMapping("{id}")
//    @ApiOperation("通过id查询秒杀商品")
//    public Result selectById(@PathVariable("id") int id) {
//        SeckillProduct secGoodVo = secGoodService.selectSecByProductId(id);
//        if (secGoodVo == null) {
//            return Result.success(null, "not exist");
//        }
//        System.out.println(secGoodVo.toString());
//        return Result.success(secGoodVo, "exist");
//    }
}

