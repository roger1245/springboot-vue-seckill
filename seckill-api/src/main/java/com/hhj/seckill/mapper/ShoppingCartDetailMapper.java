package com.hhj.seckill.mapper;

import com.hhj.seckill.entry.ShoppingCartDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface ShoppingCartDetailMapper {

    public List<ShoppingCartDetail> selectShoppingCartDetailByUserId(@Param("userId") int userId);
}
