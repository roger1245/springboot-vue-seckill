package com.hhj.seckill.entry;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SeckillProductExample {
    
    protected String orderByClause;

    
    protected boolean distinct;

    
    protected List<Criteria> oredCriteria;

    
    public SeckillProductExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    
    public String getOrderByClause() {
        return orderByClause;
    }

    
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    
    public boolean isDistinct() {
        return distinct;
    }

    
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andSeckillIdIsNull() {
            addCriterion("seckill_id is null");
            return (Criteria) this;
        }

        public Criteria andSeckillIdIsNotNull() {
            addCriterion("seckill_id is not null");
            return (Criteria) this;
        }

        public Criteria andSeckillIdEqualTo(Long value) {
            addCriterion("seckill_id =", value, "seckillId");
            return (Criteria) this;
        }

        public Criteria andSeckillIdNotEqualTo(Long value) {
            addCriterion("seckill_id <>", value, "seckillId");
            return (Criteria) this;
        }

        public Criteria andSeckillIdGreaterThan(Long value) {
            addCriterion("seckill_id >", value, "seckillId");
            return (Criteria) this;
        }

        public Criteria andSeckillIdGreaterThanOrEqualTo(Long value) {
            addCriterion("seckill_id >=", value, "seckillId");
            return (Criteria) this;
        }

        public Criteria andSeckillIdLessThan(Long value) {
            addCriterion("seckill_id <", value, "seckillId");
            return (Criteria) this;
        }

        public Criteria andSeckillIdLessThanOrEqualTo(Long value) {
            addCriterion("seckill_id <=", value, "seckillId");
            return (Criteria) this;
        }

        public Criteria andSeckillIdIn(List<Long> values) {
            addCriterion("seckill_id in", values, "seckillId");
            return (Criteria) this;
        }

        public Criteria andSeckillIdNotIn(List<Long> values) {
            addCriterion("seckill_id not in", values, "seckillId");
            return (Criteria) this;
        }

        public Criteria andSeckillIdBetween(Long value1, Long value2) {
            addCriterion("seckill_id between", value1, value2, "seckillId");
            return (Criteria) this;
        }

        public Criteria andSeckillIdNotBetween(Long value1, Long value2) {
            addCriterion("seckill_id not between", value1, value2, "seckillId");
            return (Criteria) this;
        }

        public Criteria andProductIdIsNull() {
            addCriterion("product_id is null");
            return (Criteria) this;
        }

        public Criteria andProductIdIsNotNull() {
            addCriterion("product_id is not null");
            return (Criteria) this;
        }

        public Criteria andProductIdEqualTo(Long value) {
            addCriterion("product_id =", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotEqualTo(Long value) {
            addCriterion("product_id <>", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThan(Long value) {
            addCriterion("product_id >", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThanOrEqualTo(Long value) {
            addCriterion("product_id >=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThan(Long value) {
            addCriterion("product_id <", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThanOrEqualTo(Long value) {
            addCriterion("product_id <=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdIn(List<Long> values) {
            addCriterion("product_id in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotIn(List<Long> values) {
            addCriterion("product_id not in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdBetween(Long value1, Long value2) {
            addCriterion("product_id between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotBetween(Long value1, Long value2) {
            addCriterion("product_id not between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andSeckillPriceIsNull() {
            addCriterion("seckill_price is null");
            return (Criteria) this;
        }

        public Criteria andSeckillPriceIsNotNull() {
            addCriterion("seckill_price is not null");
            return (Criteria) this;
        }

        public Criteria andSeckillPriceEqualTo(BigDecimal value) {
            addCriterion("seckill_price =", value, "seckillPrice");
            return (Criteria) this;
        }

        public Criteria andSeckillPriceNotEqualTo(BigDecimal value) {
            addCriterion("seckill_price <>", value, "seckillPrice");
            return (Criteria) this;
        }

        public Criteria andSeckillPriceGreaterThan(BigDecimal value) {
            addCriterion("seckill_price >", value, "seckillPrice");
            return (Criteria) this;
        }

        public Criteria andSeckillPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("seckill_price >=", value, "seckillPrice");
            return (Criteria) this;
        }

        public Criteria andSeckillPriceLessThan(BigDecimal value) {
            addCriterion("seckill_price <", value, "seckillPrice");
            return (Criteria) this;
        }

        public Criteria andSeckillPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("seckill_price <=", value, "seckillPrice");
            return (Criteria) this;
        }

        public Criteria andSeckillPriceIn(List<BigDecimal> values) {
            addCriterion("seckill_price in", values, "seckillPrice");
            return (Criteria) this;
        }

        public Criteria andSeckillPriceNotIn(List<BigDecimal> values) {
            addCriterion("seckill_price not in", values, "seckillPrice");
            return (Criteria) this;
        }

        public Criteria andSeckillPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("seckill_price between", value1, value2, "seckillPrice");
            return (Criteria) this;
        }

        public Criteria andSeckillPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("seckill_price not between", value1, value2, "seckillPrice");
            return (Criteria) this;
        }

        public Criteria andStockCountIsNull() {
            addCriterion("stock_count is null");
            return (Criteria) this;
        }

        public Criteria andStockCountIsNotNull() {
            addCriterion("stock_count is not null");
            return (Criteria) this;
        }

        public Criteria andStockCountEqualTo(Integer value) {
            addCriterion("stock_count =", value, "stockCount");
            return (Criteria) this;
        }

        public Criteria andStockCountNotEqualTo(Integer value) {
            addCriterion("stock_count <>", value, "stockCount");
            return (Criteria) this;
        }

        public Criteria andStockCountGreaterThan(Integer value) {
            addCriterion("stock_count >", value, "stockCount");
            return (Criteria) this;
        }

        public Criteria andStockCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("stock_count >=", value, "stockCount");
            return (Criteria) this;
        }

        public Criteria andStockCountLessThan(Integer value) {
            addCriterion("stock_count <", value, "stockCount");
            return (Criteria) this;
        }

        public Criteria andStockCountLessThanOrEqualTo(Integer value) {
            addCriterion("stock_count <=", value, "stockCount");
            return (Criteria) this;
        }

        public Criteria andStockCountIn(List<Integer> values) {
            addCriterion("stock_count in", values, "stockCount");
            return (Criteria) this;
        }

        public Criteria andStockCountNotIn(List<Integer> values) {
            addCriterion("stock_count not in", values, "stockCount");
            return (Criteria) this;
        }

        public Criteria andStockCountBetween(Integer value1, Integer value2) {
            addCriterion("stock_count between", value1, value2, "stockCount");
            return (Criteria) this;
        }

        public Criteria andStockCountNotBetween(Integer value1, Integer value2) {
            addCriterion("stock_count not between", value1, value2, "stockCount");
            return (Criteria) this;
        }

        public Criteria andStartDateIsNull() {
            addCriterion("start_date is null");
            return (Criteria) this;
        }

        public Criteria andStartDateIsNotNull() {
            addCriterion("start_date is not null");
            return (Criteria) this;
        }

        public Criteria andStartDateEqualTo(Date value) {
            addCriterion("start_date =", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotEqualTo(Date value) {
            addCriterion("start_date <>", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateGreaterThan(Date value) {
            addCriterion("start_date >", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateGreaterThanOrEqualTo(Date value) {
            addCriterion("start_date >=", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateLessThan(Date value) {
            addCriterion("start_date <", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateLessThanOrEqualTo(Date value) {
            addCriterion("start_date <=", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateIn(List<Date> values) {
            addCriterion("start_date in", values, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotIn(List<Date> values) {
            addCriterion("start_date not in", values, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateBetween(Date value1, Date value2) {
            addCriterion("start_date between", value1, value2, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotBetween(Date value1, Date value2) {
            addCriterion("start_date not between", value1, value2, "startDate");
            return (Criteria) this;
        }

        public Criteria andEndDateIsNull() {
            addCriterion("end_date is null");
            return (Criteria) this;
        }

        public Criteria andEndDateIsNotNull() {
            addCriterion("end_date is not null");
            return (Criteria) this;
        }

        public Criteria andEndDateEqualTo(Date value) {
            addCriterion("end_date =", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotEqualTo(Date value) {
            addCriterion("end_date <>", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateGreaterThan(Date value) {
            addCriterion("end_date >", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateGreaterThanOrEqualTo(Date value) {
            addCriterion("end_date >=", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLessThan(Date value) {
            addCriterion("end_date <", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLessThanOrEqualTo(Date value) {
            addCriterion("end_date <=", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateIn(List<Date> values) {
            addCriterion("end_date in", values, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotIn(List<Date> values) {
            addCriterion("end_date not in", values, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateBetween(Date value1, Date value2) {
            addCriterion("end_date between", value1, value2, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotBetween(Date value1, Date value2) {
            addCriterion("end_date not between", value1, value2, "endDate");
            return (Criteria) this;
        }
    }

    
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}