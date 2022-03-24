package com.hhj.seckill.entry;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class OrderDetail implements Serializable {
    
    private Integer id;

    
    private String orderId;

    
    private Integer userId;

    
    private Integer productId;

    
    private Integer productNum;

    
    private Double productPrice;

    
    private Long orderTime;

    private String productName;

    private String productPicture;

    
    private static final long serialVersionUID = 1L;


}