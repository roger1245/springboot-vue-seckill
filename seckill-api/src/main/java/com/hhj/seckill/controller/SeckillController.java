package com.hhj.seckill.controller;

import com.hhj.seckill.common.Result;
import com.hhj.seckill.common.enums.ErrorEnum;
import com.hhj.seckill.common.enums.SeckillEnum;
import com.hhj.seckill.common.excetion.CommonException;
import com.hhj.seckill.service.SecKillService;
import com.hhj.seckill.vo.Exposer;
import com.hhj.seckill.vo.SecKillOrder;
import com.hhj.seckill.vo.SecKillVo;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;


/**
 * @Author virtual
 * @Date 2021/6/1 16:57
 * @Version 1.0
 */
@RestController
@Slf4j
@Api("秒杀操作")
public class SeckillController{

    @Autowired
    SecKillService secKillService;

    @GetMapping("/exposer/{secProductId}")
    public Result getExposer(@PathVariable("secProductId")int product_id){
        Exposer exposer = secKillService.exposerSecAddress(product_id);
        if (! exposer.isExposed()){
            return Result.error(exposer);
        }
        return Result.success(exposer);
    }



    @PostMapping("/seckill")
    public Result doSecKill(@RequestBody SecKillVo vo){

        boolean b1 = secKillService.verifyMd5(vo.getMd5(), vo.getSecId());
        if (!b1){
            // 秒杀接口地址错误
            throw new CommonException(ErrorEnum.DATE_REWRITE.getMsg());
        }

        SecKillOrder secKillOrder = new SecKillOrder(vo.getSecId(), vo.getProductId(), vo.getUserId(), new Timestamp(System.currentTimeMillis()).getTime(), vo.getProductNum(), vo.getProductPrice());
        SeckillEnum seckillEnum = secKillService.doSecKill(secKillOrder);
        return Result.success(null, "订单成功生成");
    }
}
