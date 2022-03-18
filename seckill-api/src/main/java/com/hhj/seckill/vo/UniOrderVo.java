package com.hhj.seckill.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UniOrderVo implements Serializable {
    private int product_id;
    // 用户id
    private int user_id;

    private int product_num;

    private int product_price;


}
