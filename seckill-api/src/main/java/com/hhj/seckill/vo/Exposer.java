package com.hhj.seckill.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Exposer {
    
    private boolean exposed;

    
    private String md5;

    
    private long seckillId;

    
    private long now;

    
    private long start;

    
    private long end;
}
