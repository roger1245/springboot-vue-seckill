package com.hhj.seckill.service.Impl;

import com.hhj.seckill.entry.ShoppingCart;
import com.hhj.seckill.entry.ShoppingCartExample;
import com.hhj.seckill.mapper.ShoppingCartMapper;
import com.hhj.seckill.service.ShoppingCartService;
import com.hhj.seckill.vo.ShoppingCartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ShoppingCartServiceImpl  implements ShoppingCartService {
    @Autowired
    ShoppingCartMapper shoppingCartMapper;

    public List<ShoppingCart> getShoppingCarts(int userId) {
        ShoppingCartExample example = new ShoppingCartExample();
        example.createCriteria().andUserIdEqualTo(userId);
        List<ShoppingCart> list = shoppingCartMapper.selectByExample(example);
        return list;
    }

    //添加到购物车
    public int addShoppingCart(ShoppingCartVo cart) {
        ShoppingCartExample example = new ShoppingCartExample();
        example.createCriteria().andUserIdEqualTo(cart.getUserId()).andProductIdEqualTo(cart.getProductId()).andProductTypeEqualTo(cart.getType());
        List<ShoppingCart> list = shoppingCartMapper.selectByExample(example);
        if (list.isEmpty()) {
            ShoppingCart local_cart = new ShoppingCart();
            local_cart.setNum(1);
            local_cart.setProductId(cart.getProductId());
            local_cart.setUserId(cart.getUserId());
            local_cart.setProductType(cart.getType());
            return shoppingCartMapper.insert(local_cart);
        } else {
            ShoppingCart local_cart = list.get(0);
            local_cart.setNum(local_cart.getNum() + 1);
            return shoppingCartMapper.updateByExample(local_cart, example);
        }
    }

    public int deleteCart(int id) {
        ShoppingCartExample example = new ShoppingCartExample();
        example.createCriteria().andIdEqualTo(id);
        return shoppingCartMapper.deleteByExample(example);
    }
}
