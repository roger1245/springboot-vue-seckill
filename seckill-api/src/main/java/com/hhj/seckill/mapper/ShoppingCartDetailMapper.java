package com.hhj.seckill.mapper;

import com.hhj.seckill.entry.ShoppingCartDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author virtual
 * @Date 2021/5/29 15:48
 * @Version 1.0
 */
@Mapper
public interface ShoppingCartDetailMapper {

    public List<ShoppingCartDetail> selectShoppingCartDetailByUserId(@Param("userId") int userId);
}
