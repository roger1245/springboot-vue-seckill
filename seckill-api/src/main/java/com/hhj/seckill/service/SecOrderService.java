package com.hhj.seckill.service;

import com.github.pagehelper.PageInfo;
import com.hhj.seckill.entry.OrderDetail;
import com.hhj.seckill.entry.SecOrder;
import com.hhj.seckill.entry.UniOrder;
import com.hhj.seckill.vo.SecKillOrder;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @Author virtual
 * @Date 2021/5/31 22:46
 * @Version 1.0
 */
public interface SecOrderService {

    public PageInfo<SecOrder> selectPage(int curPage, int size);

    public List<UniOrder> newSelectPage(int userId);

    public boolean generateOrder(SecKillOrder secKillOrder);

    public boolean payById(@Param("id") int id, @Param("payTime") Date payTime);

    Long selectBySecIdAndUserId(@Param("secId") int secId, @Param("userId") int userId);

    public boolean newGenerateOrder(UniOrder order);

    public List<OrderDetail> selectOrderDetailByUserId(int userId);
}
