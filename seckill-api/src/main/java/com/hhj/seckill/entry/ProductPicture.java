package com.hhj.seckill.entry;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.io.Serializable;
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ProductPicture implements Serializable {
    
    private Integer id;

    
    private Integer productId;

    
    private String productPicture;

    
    private String intro;

    
    private static final long serialVersionUID = 1L;

    
    public Integer getId() {
        return id;
    }

    
    public void setId(Integer id) {
        this.id = id;
    }

    
    public Integer getProductId() {
        return productId;
    }

    
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    
    public String getProductPicture() {
        return productPicture;
    }

    
    public void setProductPicture(String productPicture) {
        this.productPicture = productPicture;
    }

    
    public String getIntro() {
        return intro;
    }

    
    public void setIntro(String intro) {
        this.intro = intro;
    }

    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", productId=").append(productId);
        sb.append(", productPicture=").append(productPicture);
        sb.append(", intro=").append(intro);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}