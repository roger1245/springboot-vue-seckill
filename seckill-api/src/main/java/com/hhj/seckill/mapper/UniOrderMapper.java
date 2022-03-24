package com.hhj.seckill.mapper;

import com.hhj.seckill.entry.UniOrder;
import com.hhj.seckill.entry.UniOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UniOrderMapper {
    
    int countByExample(UniOrderExample example);

    
    int deleteByExample(UniOrderExample example);

    
    int deleteByPrimaryKey(Integer id);

    
    int insert(UniOrder record);

    
    int insertSelective(UniOrder record);

    
    List<UniOrder> selectByExample(UniOrderExample example);

    
    UniOrder selectByPrimaryKey(Integer id);

    
    int updateByExampleSelective(@Param("record") UniOrder record, @Param("example") UniOrderExample example);

    
    int updateByExample(@Param("record") UniOrder record, @Param("example") UniOrderExample example);

    
    int updateByPrimaryKeySelective(UniOrder record);

    
    int updateByPrimaryKey(UniOrder record);
}