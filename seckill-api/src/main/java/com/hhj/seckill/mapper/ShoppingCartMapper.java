package com.hhj.seckill.mapper;

import com.hhj.seckill.entry.ShoppingCart;
import com.hhj.seckill.entry.ShoppingCartExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShoppingCartMapper {
    
    int countByExample(ShoppingCartExample example);

    
    int deleteByExample(ShoppingCartExample example);

    
    int deleteByPrimaryKey(Integer id);

    
    int insert(ShoppingCart record);

    
    int insertSelective(ShoppingCart record);

    
    List<ShoppingCart> selectByExample(ShoppingCartExample example);

    
    ShoppingCart selectByPrimaryKey(Integer id);

    
    int updateByExampleSelective(@Param("record") ShoppingCart record, @Param("example") ShoppingCartExample example);

    
    int updateByExample(@Param("record") ShoppingCart record, @Param("example") ShoppingCartExample example);

    
    int updateByPrimaryKeySelective(ShoppingCart record);

    
    int updateByPrimaryKey(ShoppingCart record);
}