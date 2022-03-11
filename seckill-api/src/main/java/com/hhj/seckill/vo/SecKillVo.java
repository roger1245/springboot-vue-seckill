package com.hhj.seckill.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @Author virtual
 * @Date 2021/6/1 16:39
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SecKillVo {
    // md5,验证地址，通过exposer接口拿到
    private String md5;

    // 秒杀product的primary key
    private int secId;

    private int productId;
    // 用户id
    private int userId;

    private int productNum;

    private int productPrice;
}
