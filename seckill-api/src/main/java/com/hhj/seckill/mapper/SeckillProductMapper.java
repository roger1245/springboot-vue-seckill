package com.hhj.seckill.mapper;

import com.hhj.seckill.entry.SeckillProduct;
import com.hhj.seckill.entry.SeckillProductExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SeckillProductMapper {
    
    int countByExample(SeckillProductExample example);

    
    int deleteByExample(SeckillProductExample example);

    
    int deleteByPrimaryKey(Long seckillId);

    
    int insert(SeckillProduct record);

    
    int insertSelective(SeckillProduct record);

    
    List<SeckillProduct> selectByExample(SeckillProductExample example);

    
    SeckillProduct selectByPrimaryKey(Long seckillId);

    
    int updateByExampleSelective(@Param("record") SeckillProduct record, @Param("example") SeckillProductExample example);

    
    int updateByExample(@Param("record") SeckillProduct record, @Param("example") SeckillProductExample example);

    
    int updateByPrimaryKeySelective(SeckillProduct record);

    
    int updateByPrimaryKey(SeckillProduct record);
}