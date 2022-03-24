package com.hhj.seckill.entry;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ShoppingCartDetail {
    
    private Integer id;

    
    private Integer userId;

    
    private Integer productId;

    
    private Integer productType;

    
    private Integer num;

    private String productName;

    private String productImg;

    private Integer price;

    private Boolean check = false;
}
