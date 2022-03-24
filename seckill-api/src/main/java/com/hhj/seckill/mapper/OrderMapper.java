package com.hhj.seckill.mapper;

import com.hhj.seckill.entry.OrderDetail;
import com.hhj.seckill.entry.SecOrder;
import com.hhj.seckill.vo.SecKillOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;


@Mapper
public interface OrderMapper {

    public List<OrderDetail> selectUniOrderAndProductDetailByUserId(@Param("userId") int userId);
}
