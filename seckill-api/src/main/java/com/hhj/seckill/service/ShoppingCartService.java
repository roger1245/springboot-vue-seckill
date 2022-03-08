package com.hhj.seckill.service;

import com.hhj.seckill.entry.ShoppingCart;
import com.hhj.seckill.vo.ShoppingCartVo;

import java.util.List;

public interface ShoppingCartService {
    public List<ShoppingCart> getShoppingCarts(int userId);

    public int addShoppingCart(ShoppingCartVo cart);

    public int deleteCart(int id);
}
