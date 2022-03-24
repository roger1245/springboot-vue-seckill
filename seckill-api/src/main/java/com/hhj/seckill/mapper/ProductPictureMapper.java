package com.hhj.seckill.mapper;

import com.hhj.seckill.entry.ProductPicture;
import com.hhj.seckill.entry.ProductPictureExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProductPictureMapper {
    
    int countByExample(ProductPictureExample example);

    
    int deleteByExample(ProductPictureExample example);

    
    int deleteByPrimaryKey(Integer id);

    
    int insert(ProductPicture record);

    
    int insertSelective(ProductPicture record);

    
    List<ProductPicture> selectByExample(ProductPictureExample example);

    
    ProductPicture selectByPrimaryKey(Integer id);

    
    int updateByExampleSelective(@Param("record") ProductPicture record, @Param("example") ProductPictureExample example);

    
    int updateByExample(@Param("record") ProductPicture record, @Param("example") ProductPictureExample example);

    
    int updateByPrimaryKeySelective(ProductPicture record);

    
    int updateByPrimaryKey(ProductPicture record);
}