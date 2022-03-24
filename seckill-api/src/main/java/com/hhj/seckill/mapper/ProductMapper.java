package com.hhj.seckill.mapper;

import com.hhj.seckill.entry.Product;
import com.hhj.seckill.entry.ProductExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProductMapper {
    
    int countByExample(ProductExample example);

    
    int deleteByExample(ProductExample example);

    
    int deleteByPrimaryKey(Integer productId);

    
    int insert(Product record);

    
    int insertSelective(Product record);

    
    List<Product> selectByExampleWithBLOBs(ProductExample example);

    
    List<Product> selectByExample(ProductExample example);

    
    Product selectByPrimaryKey(Integer productId);

    
    int updateByExampleSelective(@Param("record") Product record, @Param("example") ProductExample example);

    
    int updateByExampleWithBLOBs(@Param("record") Product record, @Param("example") ProductExample example);

    
    int updateByExample(@Param("record") Product record, @Param("example") ProductExample example);

    
    int updateByPrimaryKeySelective(Product record);

    
    int updateByPrimaryKeyWithBLOBs(Product record);

    
    int updateByPrimaryKey(Product record);
}