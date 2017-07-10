package com.hualala.app.wechat.model.card;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CouponModelQuery {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer pageNo = 1;

    protected Integer startRow;

    protected Integer pageSize = 10;

    protected String fields;

    public CouponModelQuery() {
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

    public void setPageNo(Integer pageNo) {
        this.pageNo=pageNo;
        this.startRow = (pageNo-1)*this.pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setStartRow(Integer startRow) {
        this.startRow=startRow;
    }

    public Integer getStartRow() {
        return startRow;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize=pageSize;
        this.startRow = (pageNo-1)*this.pageSize;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setFields(String fields) {
        this.fields=fields;
    }

    public String getFields() {
        return fields;
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

        public Criteria andCardKeyIsNull() {
            addCriterion("cardKey is null");
            return (Criteria) this;
        }

        public Criteria andCardKeyIsNotNull() {
            addCriterion("cardKey is not null");
            return (Criteria) this;
        }

        public Criteria andCardKeyEqualTo(Long value) {
            addCriterion("cardKey =", value, "cardKey");
            return (Criteria) this;
        }

        public Criteria andCardKeyNotEqualTo(Long value) {
            addCriterion("cardKey <>", value, "cardKey");
            return (Criteria) this;
        }

        public Criteria andCardKeyGreaterThan(Long value) {
            addCriterion("cardKey >", value, "cardKey");
            return (Criteria) this;
        }

        public Criteria andCardKeyGreaterThanOrEqualTo(Long value) {
            addCriterion("cardKey >=", value, "cardKey");
            return (Criteria) this;
        }

        public Criteria andCardKeyLessThan(Long value) {
            addCriterion("cardKey <", value, "cardKey");
            return (Criteria) this;
        }

        public Criteria andCardKeyLessThanOrEqualTo(Long value) {
            addCriterion("cardKey <=", value, "cardKey");
            return (Criteria) this;
        }

        public Criteria andCardKeyIn(List<Long> values) {
            addCriterion("cardKey in", values, "cardKey");
            return (Criteria) this;
        }

        public Criteria andCardKeyNotIn(List<Long> values) {
            addCriterion("cardKey not in", values, "cardKey");
            return (Criteria) this;
        }

        public Criteria andCardKeyBetween(Long value1, Long value2) {
            addCriterion("cardKey between", value1, value2, "cardKey");
            return (Criteria) this;
        }

        public Criteria andCardKeyNotBetween(Long value1, Long value2) {
            addCriterion("cardKey not between", value1, value2, "cardKey");
            return (Criteria) this;
        }

        public Criteria andDefaultDetailIsNull() {
            addCriterion("defaultDetail is null");
            return (Criteria) this;
        }

        public Criteria andDefaultDetailIsNotNull() {
            addCriterion("defaultDetail is not null");
            return (Criteria) this;
        }

        public Criteria andDefaultDetailEqualTo(String value) {
            addCriterion("defaultDetail =", value, "defaultDetail");
            return (Criteria) this;
        }

        public Criteria andDefaultDetailNotEqualTo(String value) {
            addCriterion("defaultDetail <>", value, "defaultDetail");
            return (Criteria) this;
        }

        public Criteria andDefaultDetailGreaterThan(String value) {
            addCriterion("defaultDetail >", value, "defaultDetail");
            return (Criteria) this;
        }

        public Criteria andDefaultDetailGreaterThanOrEqualTo(String value) {
            addCriterion("defaultDetail >=", value, "defaultDetail");
            return (Criteria) this;
        }

        public Criteria andDefaultDetailLessThan(String value) {
            addCriterion("defaultDetail <", value, "defaultDetail");
            return (Criteria) this;
        }

        public Criteria andDefaultDetailLessThanOrEqualTo(String value) {
            addCriterion("defaultDetail <=", value, "defaultDetail");
            return (Criteria) this;
        }

        public Criteria andDefaultDetailLike(String value) {
            addCriterion("defaultDetail like", value, "defaultDetail");
            return (Criteria) this;
        }

        public Criteria andDefaultDetailNotLike(String value) {
            addCriterion("defaultDetail not like", value, "defaultDetail");
            return (Criteria) this;
        }

        public Criteria andDefaultDetailIn(List<String> values) {
            addCriterion("defaultDetail in", values, "defaultDetail");
            return (Criteria) this;
        }

        public Criteria andDefaultDetailNotIn(List<String> values) {
            addCriterion("defaultDetail not in", values, "defaultDetail");
            return (Criteria) this;
        }

        public Criteria andDefaultDetailBetween(String value1, String value2) {
            addCriterion("defaultDetail between", value1, value2, "defaultDetail");
            return (Criteria) this;
        }

        public Criteria andDefaultDetailNotBetween(String value1, String value2) {
            addCriterion("defaultDetail not between", value1, value2, "defaultDetail");
            return (Criteria) this;
        }

        public Criteria andDealDetailIsNull() {
            addCriterion("dealDetail is null");
            return (Criteria) this;
        }

        public Criteria andDealDetailIsNotNull() {
            addCriterion("dealDetail is not null");
            return (Criteria) this;
        }

        public Criteria andDealDetailEqualTo(String value) {
            addCriterion("dealDetail =", value, "dealDetail");
            return (Criteria) this;
        }

        public Criteria andDealDetailNotEqualTo(String value) {
            addCriterion("dealDetail <>", value, "dealDetail");
            return (Criteria) this;
        }

        public Criteria andDealDetailGreaterThan(String value) {
            addCriterion("dealDetail >", value, "dealDetail");
            return (Criteria) this;
        }

        public Criteria andDealDetailGreaterThanOrEqualTo(String value) {
            addCriterion("dealDetail >=", value, "dealDetail");
            return (Criteria) this;
        }

        public Criteria andDealDetailLessThan(String value) {
            addCriterion("dealDetail <", value, "dealDetail");
            return (Criteria) this;
        }

        public Criteria andDealDetailLessThanOrEqualTo(String value) {
            addCriterion("dealDetail <=", value, "dealDetail");
            return (Criteria) this;
        }

        public Criteria andDealDetailLike(String value) {
            addCriterion("dealDetail like", value, "dealDetail");
            return (Criteria) this;
        }

        public Criteria andDealDetailNotLike(String value) {
            addCriterion("dealDetail not like", value, "dealDetail");
            return (Criteria) this;
        }

        public Criteria andDealDetailIn(List<String> values) {
            addCriterion("dealDetail in", values, "dealDetail");
            return (Criteria) this;
        }

        public Criteria andDealDetailNotIn(List<String> values) {
            addCriterion("dealDetail not in", values, "dealDetail");
            return (Criteria) this;
        }

        public Criteria andDealDetailBetween(String value1, String value2) {
            addCriterion("dealDetail between", value1, value2, "dealDetail");
            return (Criteria) this;
        }

        public Criteria andDealDetailNotBetween(String value1, String value2) {
            addCriterion("dealDetail not between", value1, value2, "dealDetail");
            return (Criteria) this;
        }

        public Criteria andLeastCostIsNull() {
            addCriterion("leastCost is null");
            return (Criteria) this;
        }

        public Criteria andLeastCostIsNotNull() {
            addCriterion("leastCost is not null");
            return (Criteria) this;
        }

        public Criteria andLeastCostEqualTo(Integer value) {
            addCriterion("leastCost =", value, "leastCost");
            return (Criteria) this;
        }

        public Criteria andLeastCostNotEqualTo(Integer value) {
            addCriterion("leastCost <>", value, "leastCost");
            return (Criteria) this;
        }

        public Criteria andLeastCostGreaterThan(Integer value) {
            addCriterion("leastCost >", value, "leastCost");
            return (Criteria) this;
        }

        public Criteria andLeastCostGreaterThanOrEqualTo(Integer value) {
            addCriterion("leastCost >=", value, "leastCost");
            return (Criteria) this;
        }

        public Criteria andLeastCostLessThan(Integer value) {
            addCriterion("leastCost <", value, "leastCost");
            return (Criteria) this;
        }

        public Criteria andLeastCostLessThanOrEqualTo(Integer value) {
            addCriterion("leastCost <=", value, "leastCost");
            return (Criteria) this;
        }

        public Criteria andLeastCostIn(List<Integer> values) {
            addCriterion("leastCost in", values, "leastCost");
            return (Criteria) this;
        }

        public Criteria andLeastCostNotIn(List<Integer> values) {
            addCriterion("leastCost not in", values, "leastCost");
            return (Criteria) this;
        }

        public Criteria andLeastCostBetween(Integer value1, Integer value2) {
            addCriterion("leastCost between", value1, value2, "leastCost");
            return (Criteria) this;
        }

        public Criteria andLeastCostNotBetween(Integer value1, Integer value2) {
            addCriterion("leastCost not between", value1, value2, "leastCost");
            return (Criteria) this;
        }

        public Criteria andReduceCostIsNull() {
            addCriterion("reduceCost is null");
            return (Criteria) this;
        }

        public Criteria andReduceCostIsNotNull() {
            addCriterion("reduceCost is not null");
            return (Criteria) this;
        }

        public Criteria andReduceCostEqualTo(Integer value) {
            addCriterion("reduceCost =", value, "reduceCost");
            return (Criteria) this;
        }

        public Criteria andReduceCostNotEqualTo(Integer value) {
            addCriterion("reduceCost <>", value, "reduceCost");
            return (Criteria) this;
        }

        public Criteria andReduceCostGreaterThan(Integer value) {
            addCriterion("reduceCost >", value, "reduceCost");
            return (Criteria) this;
        }

        public Criteria andReduceCostGreaterThanOrEqualTo(Integer value) {
            addCriterion("reduceCost >=", value, "reduceCost");
            return (Criteria) this;
        }

        public Criteria andReduceCostLessThan(Integer value) {
            addCriterion("reduceCost <", value, "reduceCost");
            return (Criteria) this;
        }

        public Criteria andReduceCostLessThanOrEqualTo(Integer value) {
            addCriterion("reduceCost <=", value, "reduceCost");
            return (Criteria) this;
        }

        public Criteria andReduceCostIn(List<Integer> values) {
            addCriterion("reduceCost in", values, "reduceCost");
            return (Criteria) this;
        }

        public Criteria andReduceCostNotIn(List<Integer> values) {
            addCriterion("reduceCost not in", values, "reduceCost");
            return (Criteria) this;
        }

        public Criteria andReduceCostBetween(Integer value1, Integer value2) {
            addCriterion("reduceCost between", value1, value2, "reduceCost");
            return (Criteria) this;
        }

        public Criteria andReduceCostNotBetween(Integer value1, Integer value2) {
            addCriterion("reduceCost not between", value1, value2, "reduceCost");
            return (Criteria) this;
        }

        public Criteria andDiscountIsNull() {
            addCriterion("discount is null");
            return (Criteria) this;
        }

        public Criteria andDiscountIsNotNull() {
            addCriterion("discount is not null");
            return (Criteria) this;
        }

        public Criteria andDiscountEqualTo(Integer value) {
            addCriterion("discount =", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountNotEqualTo(Integer value) {
            addCriterion("discount <>", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountGreaterThan(Integer value) {
            addCriterion("discount >", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountGreaterThanOrEqualTo(Integer value) {
            addCriterion("discount >=", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountLessThan(Integer value) {
            addCriterion("discount <", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountLessThanOrEqualTo(Integer value) {
            addCriterion("discount <=", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountIn(List<Integer> values) {
            addCriterion("discount in", values, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountNotIn(List<Integer> values) {
            addCriterion("discount not in", values, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountBetween(Integer value1, Integer value2) {
            addCriterion("discount between", value1, value2, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountNotBetween(Integer value1, Integer value2) {
            addCriterion("discount not between", value1, value2, "discount");
            return (Criteria) this;
        }

        public Criteria andGiftIsNull() {
            addCriterion("gift is null");
            return (Criteria) this;
        }

        public Criteria andGiftIsNotNull() {
            addCriterion("gift is not null");
            return (Criteria) this;
        }

        public Criteria andGiftEqualTo(String value) {
            addCriterion("gift =", value, "gift");
            return (Criteria) this;
        }

        public Criteria andGiftNotEqualTo(String value) {
            addCriterion("gift <>", value, "gift");
            return (Criteria) this;
        }

        public Criteria andGiftGreaterThan(String value) {
            addCriterion("gift >", value, "gift");
            return (Criteria) this;
        }

        public Criteria andGiftGreaterThanOrEqualTo(String value) {
            addCriterion("gift >=", value, "gift");
            return (Criteria) this;
        }

        public Criteria andGiftLessThan(String value) {
            addCriterion("gift <", value, "gift");
            return (Criteria) this;
        }

        public Criteria andGiftLessThanOrEqualTo(String value) {
            addCriterion("gift <=", value, "gift");
            return (Criteria) this;
        }

        public Criteria andGiftLike(String value) {
            addCriterion("gift like", value, "gift");
            return (Criteria) this;
        }

        public Criteria andGiftNotLike(String value) {
            addCriterion("gift not like", value, "gift");
            return (Criteria) this;
        }

        public Criteria andGiftIn(List<String> values) {
            addCriterion("gift in", values, "gift");
            return (Criteria) this;
        }

        public Criteria andGiftNotIn(List<String> values) {
            addCriterion("gift not in", values, "gift");
            return (Criteria) this;
        }

        public Criteria andGiftBetween(String value1, String value2) {
            addCriterion("gift between", value1, value2, "gift");
            return (Criteria) this;
        }

        public Criteria andGiftNotBetween(String value1, String value2) {
            addCriterion("gift not between", value1, value2, "gift");
            return (Criteria) this;
        }

        public Criteria andActionIsNull() {
            addCriterion("action is null");
            return (Criteria) this;
        }

        public Criteria andActionIsNotNull() {
            addCriterion("action is not null");
            return (Criteria) this;
        }

        public Criteria andActionEqualTo(Integer value) {
            addCriterion("action =", value, "action");
            return (Criteria) this;
        }

        public Criteria andActionNotEqualTo(Integer value) {
            addCriterion("action <>", value, "action");
            return (Criteria) this;
        }

        public Criteria andActionGreaterThan(Integer value) {
            addCriterion("action >", value, "action");
            return (Criteria) this;
        }

        public Criteria andActionGreaterThanOrEqualTo(Integer value) {
            addCriterion("action >=", value, "action");
            return (Criteria) this;
        }

        public Criteria andActionLessThan(Integer value) {
            addCriterion("action <", value, "action");
            return (Criteria) this;
        }

        public Criteria andActionLessThanOrEqualTo(Integer value) {
            addCriterion("action <=", value, "action");
            return (Criteria) this;
        }

        public Criteria andActionIn(List<Integer> values) {
            addCriterion("action in", values, "action");
            return (Criteria) this;
        }

        public Criteria andActionNotIn(List<Integer> values) {
            addCriterion("action not in", values, "action");
            return (Criteria) this;
        }

        public Criteria andActionBetween(Integer value1, Integer value2) {
            addCriterion("action between", value1, value2, "action");
            return (Criteria) this;
        }

        public Criteria andActionNotBetween(Integer value1, Integer value2) {
            addCriterion("action not between", value1, value2, "action");
            return (Criteria) this;
        }

        public Criteria andActionStampIsNull() {
            addCriterion("actionStamp is null");
            return (Criteria) this;
        }

        public Criteria andActionStampIsNotNull() {
            addCriterion("actionStamp is not null");
            return (Criteria) this;
        }

        public Criteria andActionStampEqualTo(Date value) {
            addCriterion("actionStamp =", value, "actionStamp");
            return (Criteria) this;
        }

        public Criteria andActionStampNotEqualTo(Date value) {
            addCriterion("actionStamp <>", value, "actionStamp");
            return (Criteria) this;
        }

        public Criteria andActionStampGreaterThan(Date value) {
            addCriterion("actionStamp >", value, "actionStamp");
            return (Criteria) this;
        }

        public Criteria andActionStampGreaterThanOrEqualTo(Date value) {
            addCriterion("actionStamp >=", value, "actionStamp");
            return (Criteria) this;
        }

        public Criteria andActionStampLessThan(Date value) {
            addCriterion("actionStamp <", value, "actionStamp");
            return (Criteria) this;
        }

        public Criteria andActionStampLessThanOrEqualTo(Date value) {
            addCriterion("actionStamp <=", value, "actionStamp");
            return (Criteria) this;
        }

        public Criteria andActionStampIn(List<Date> values) {
            addCriterion("actionStamp in", values, "actionStamp");
            return (Criteria) this;
        }

        public Criteria andActionStampNotIn(List<Date> values) {
            addCriterion("actionStamp not in", values, "actionStamp");
            return (Criteria) this;
        }

        public Criteria andActionStampBetween(Date value1, Date value2) {
            addCriterion("actionStamp between", value1, value2, "actionStamp");
            return (Criteria) this;
        }

        public Criteria andActionStampNotBetween(Date value1, Date value2) {
            addCriterion("actionStamp not between", value1, value2, "actionStamp");
            return (Criteria) this;
        }

        public Criteria andCreateStampIsNull() {
            addCriterion("createStamp is null");
            return (Criteria) this;
        }

        public Criteria andCreateStampIsNotNull() {
            addCriterion("createStamp is not null");
            return (Criteria) this;
        }

        public Criteria andCreateStampEqualTo(Date value) {
            addCriterion("createStamp =", value, "createStamp");
            return (Criteria) this;
        }

        public Criteria andCreateStampNotEqualTo(Date value) {
            addCriterion("createStamp <>", value, "createStamp");
            return (Criteria) this;
        }

        public Criteria andCreateStampGreaterThan(Date value) {
            addCriterion("createStamp >", value, "createStamp");
            return (Criteria) this;
        }

        public Criteria andCreateStampGreaterThanOrEqualTo(Date value) {
            addCriterion("createStamp >=", value, "createStamp");
            return (Criteria) this;
        }

        public Criteria andCreateStampLessThan(Date value) {
            addCriterion("createStamp <", value, "createStamp");
            return (Criteria) this;
        }

        public Criteria andCreateStampLessThanOrEqualTo(Date value) {
            addCriterion("createStamp <=", value, "createStamp");
            return (Criteria) this;
        }

        public Criteria andCreateStampIn(List<Date> values) {
            addCriterion("createStamp in", values, "createStamp");
            return (Criteria) this;
        }

        public Criteria andCreateStampNotIn(List<Date> values) {
            addCriterion("createStamp not in", values, "createStamp");
            return (Criteria) this;
        }

        public Criteria andCreateStampBetween(Date value1, Date value2) {
            addCriterion("createStamp between", value1, value2, "createStamp");
            return (Criteria) this;
        }

        public Criteria andCreateStampNotBetween(Date value1, Date value2) {
            addCriterion("createStamp not between", value1, value2, "createStamp");
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