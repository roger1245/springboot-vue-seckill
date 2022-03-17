package com.hhj.seckill.controller;

import com.hhj.seckill.common.Result;
import com.hhj.seckill.entry.ShoppingCartDetail;
import com.hhj.seckill.service.ShoppingCartService;
import com.hhj.seckill.vo.ShoppingCartVo;
import com.hhj.seckill.vo.UserIdVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

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

    @PostMapping("allByUserId")
    @ApiOperation("通过id查询秒杀商品")
    public Result selectById(@RequestBody UserIdVo userId) {
        List<ShoppingCartDetail> list = service.getShoppingCarts(userId.getUserId());
        return Result.success(list, "操作成功");
    }
}

