package com.hhj.seckill.entry;

import java.io.Serializable;

public class ShoppingCart implements Serializable {
    
    private Integer id;

    
    private Integer userId;

    
    private Integer productId;

    
    private Integer productType;

    
    private Integer num;

    
    private static final long serialVersionUID = 1L;

    
    public Integer getId() {
        return id;
    }

    
    public void setId(Integer id) {
        this.id = id;
    }

    
    public Integer getUserId() {
        return userId;
    }

    
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    
    public Integer getProductId() {
        return productId;
    }

    
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    
    public Integer getProductType() {
        return productType;
    }

    
    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    
    public Integer getNum() {
        return num;
    }

    
    public void setNum(Integer num) {
        this.num = num;
    }

    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", productId=").append(productId);
        sb.append(", productType=").append(productType);
        sb.append(", num=").append(num);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}