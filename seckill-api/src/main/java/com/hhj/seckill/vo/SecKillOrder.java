package com.hhj.seckill.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SecKillOrder implements Serializable {
    // 秒杀product的primary key
    private int secId;

    private int productId;
    // 用户id
    private int userId;
    // 创建时间
    private Long createTime;

    private int product_num;
    private int product_price;


}
