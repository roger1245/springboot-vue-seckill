package com.hhj.seckill.entry;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class SeckillProduct implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column seckill_product.seckill_id
     *
     * @mbggenerated Fri Mar 04 12:39:42 CST 2022
     */
    private Long seckillId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column seckill_product.product_id
     *
     * @mbggenerated Fri Mar 04 12:39:42 CST 2022
     */
    private Long productId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column seckill_product.seckill_price
     *
     * @mbggenerated Fri Mar 04 12:39:42 CST 2022
     */
    private BigDecimal seckillPrice;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column seckill_product.stock_count
     *
     * @mbggenerated Fri Mar 04 12:39:42 CST 2022
     */
    private Integer stockCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column seckill_product.start_date
     *
     * @mbggenerated Fri Mar 04 12:39:42 CST 2022
     */
    private Date startDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column seckill_product.end_date
     *
     * @mbggenerated Fri Mar 04 12:39:42 CST 2022
     */
    private Date endDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table seckill_product
     *
     * @mbggenerated Fri Mar 04 12:39:42 CST 2022
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column seckill_product.seckill_id
     *
     * @return the value of seckill_product.seckill_id
     *
     * @mbggenerated Fri Mar 04 12:39:42 CST 2022
     */
    public Long getSeckillId() {
        return seckillId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column seckill_product.seckill_id
     *
     * @param seckillId the value for seckill_product.seckill_id
     *
     * @mbggenerated Fri Mar 04 12:39:42 CST 2022
     */
    public void setSeckillId(Long seckillId) {
        this.seckillId = seckillId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column seckill_product.product_id
     *
     * @return the value of seckill_product.product_id
     *
     * @mbggenerated Fri Mar 04 12:39:42 CST 2022
     */
    public Long getProductId() {
        return productId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column seckill_product.product_id
     *
     * @param productId the value for seckill_product.product_id
     *
     * @mbggenerated Fri Mar 04 12:39:42 CST 2022
     */
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column seckill_product.seckill_price
     *
     * @return the value of seckill_product.seckill_price
     *
     * @mbggenerated Fri Mar 04 12:39:42 CST 2022
     */
    public BigDecimal getSeckillPrice() {
        return seckillPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column seckill_product.seckill_price
     *
     * @param seckillPrice the value for seckill_product.seckill_price
     *
     * @mbggenerated Fri Mar 04 12:39:42 CST 2022
     */
    public void setSeckillPrice(BigDecimal seckillPrice) {
        this.seckillPrice = seckillPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column seckill_product.stock_count
     *
     * @return the value of seckill_product.stock_count
     *
     * @mbggenerated Fri Mar 04 12:39:42 CST 2022
     */
    public Integer getStockCount() {
        return stockCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column seckill_product.stock_count
     *
     * @param stockCount the value for seckill_product.stock_count
     *
     * @mbggenerated Fri Mar 04 12:39:42 CST 2022
     */
    public void setStockCount(Integer stockCount) {
        this.stockCount = stockCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column seckill_product.start_date
     *
     * @return the value of seckill_product.start_date
     *
     * @mbggenerated Fri Mar 04 12:39:42 CST 2022
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column seckill_product.start_date
     *
     * @param startDate the value for seckill_product.start_date
     *
     * @mbggenerated Fri Mar 04 12:39:42 CST 2022
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column seckill_product.end_date
     *
     * @return the value of seckill_product.end_date
     *
     * @mbggenerated Fri Mar 04 12:39:42 CST 2022
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column seckill_product.end_date
     *
     * @param endDate the value for seckill_product.end_date
     *
     * @mbggenerated Fri Mar 04 12:39:42 CST 2022
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table seckill_product
     *
     * @mbggenerated Fri Mar 04 12:39:42 CST 2022
     */
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