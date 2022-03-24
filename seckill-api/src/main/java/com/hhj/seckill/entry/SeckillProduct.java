package com.hhj.seckill.entry;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class SeckillProduct implements Serializable {
    
    private Long seckillId;

    
    private Long productId;

    
    private BigDecimal seckillPrice;

    
    private Integer stockCount;

    
    private Date startDate;

    
    private Date endDate;

    
    private static final long serialVersionUID = 1L;

    
    public Long getSeckillId() {
        return seckillId;
    }

    
    public void setSeckillId(Long seckillId) {
        this.seckillId = seckillId;
    }

    
    public Long getProductId() {
        return productId;
    }

    
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    
    public BigDecimal getSeckillPrice() {
        return seckillPrice;
    }

    
    public void setSeckillPrice(BigDecimal seckillPrice) {
        this.seckillPrice = seckillPrice;
    }

    
    public Integer getStockCount() {
        return stockCount;
    }

    
    public void setStockCount(Integer stockCount) {
        this.stockCount = stockCount;
    }

    
    public Date getStartDate() {
        return startDate;
    }

    
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    
    public Date getEndDate() {
        return endDate;
    }

    
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", seckillId=").append(seckillId);
        sb.append(", productId=").append(productId);
        sb.append(", seckillPrice=").append(seckillPrice);
        sb.append(", stockCount=").append(stockCount);
        sb.append(", startDate=").append(startDate);
        sb.append(", endDate=").append(endDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}