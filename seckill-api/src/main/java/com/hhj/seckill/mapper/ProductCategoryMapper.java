package com.hhj.seckill.mapper;

import com.hhj.seckill.entry.ProductCategory;
import com.hhj.seckill.entry.ProductCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProductCategoryMapper {
    
    int countByExample(ProductCategoryExample example);

    
    int deleteByExample(ProductCategoryExample example);

    
    int deleteByPrimaryKey(Integer id);

    
    int insert(ProductCategory record);

    
    int insertSelective(ProductCategory record);

    
    List<ProductCategory> selectByExample(ProductCategoryExample example);

    
    ProductCategory selectByPrimaryKey(Integer id);

    
    int updateByExampleSelective(@Param("record") ProductCategory record, @Param("example") ProductCategoryExample example);

    
    int updateByExample(@Param("record") ProductCategory record, @Param("example") ProductCategoryExample example);

    
    int updateByPrimaryKeySelective(ProductCategory record);

    
    int updateByPrimaryKey(ProductCategory record);
}