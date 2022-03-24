package com.hhj.seckill.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.github.pagehelper.PageInfo;
import com.hhj.seckill.common.Result;
import com.hhj.seckill.common.enums.ErrorEnum;
import com.hhj.seckill.entry.OrderDetail;
import com.hhj.seckill.entry.SecOrder;
import com.hhj.seckill.entry.UniOrder;
import com.hhj.seckill.service.OrderService;
import com.hhj.seckill.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/order")
@Api("订单")
public class OrderController {
    @Autowired
    OrderService service;

    @GetMapping("list")
    @ApiOperation("分页查询")
    public Result page(int curpage,int size){
        PageInfo<SecOrder> goodPageInfo = service.selectPage(curpage, size);
        return Result.success(goodPageInfo);
    }

    @PostMapping("listById")
    @ApiOperation("查询userId下所有订单")
    public Result list(@RequestBody UserIdVo userId){
        List<OrderDetail> res = service.selectOrderDetailByUserId(userId.getUserId());
        return Result.success(res);
    }


    @PutMapping("payById")
    @ApiOperation("用户付款")
    public Result payById(@RequestParam("id")int id){
        Date date = new Date();
        boolean b = service.payById(id, date);
        if(b){
            return Result.success(DateUtil.format(date,"yyyy-MM-dd HH:mm:ss"),"付款成功");
        }
        return Result.error("付款失败");
    }

    @PostMapping("selectEntry")
    public Result selectByForm(@RequestBody SecKillVo vo){
        Long l = service.selectBySecIdAndUserId(vo.getSecId(), vo.getUserId());
        if(l != null){
            return Result.success(null,"秒杀成功");
        }

        return Result.error(ErrorEnum.QUEUE_NOW.getMsg());
    }

    @PostMapping("insertList")
    public Result insertListOrder(@RequestBody UniOrderListVo list){
        if (!list.getList().isEmpty()) {
            for (UniOrderVo vo : list.getList()) {
                // 生成订单
                UniOrder order = new UniOrder();
                order.setOrderId("normal:" + String.valueOf(RandomUtil.randomInt(1, 1000000000)));
                order.setUserId(vo.getUser_id());
                order.setProductId(vo.getProduct_id());
                order.setProductNum(vo.getProduct_num());
                order.setProductPrice((double) vo.getProduct_price());
                order.setOrderTime(new Timestamp(System.currentTimeMillis()).getTime());
                service.newGenerateOrder(order);
            }
            return Result.success(null, "操作成功");
        } else {
            return Result.error(ErrorEnum.QUEUE_NOW.getMsg());
        }
    }

}
