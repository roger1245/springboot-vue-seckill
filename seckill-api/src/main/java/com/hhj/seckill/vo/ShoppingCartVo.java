package com.hhj.seckill.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Author virtual
 * @Date 2021/6/1 16:39
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartVo {
    @NotNull
    private int productId;
    // 用户id
    @NotNull
    private int userId;
    @NotNull
    private int type;
}
