package com.hhj.seckill.entry;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.io.Serializable;
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ProductCategory implements Serializable {
    
    private Integer id;

    
    private Integer categoryId;

    
    private String categoryName;

    
    private static final long serialVersionUID = 1L;

    
    public Integer getId() {
        return id;
    }

    
    public void setId(Integer id) {
        this.id = id;
    }

    
    public Integer getCategoryId() {
        return categoryId;
    }

    
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    
    public String getCategoryName() {
        return categoryName;
    }

    
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", categoryId=").append(categoryId);
        sb.append(", categoryName=").append(categoryName);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}