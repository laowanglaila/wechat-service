package com.hualala.app.wechat.model.card;

import java.util.ArrayList;
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

        public Criteria andCardKeyEqualTo(String value) {
            addCriterion("cardKey =", value, "cardKey");
            return (Criteria) this;
        }

        public Criteria andCardKeyNotEqualTo(String value) {
            addCriterion("cardKey <>", value, "cardKey");
            return (Criteria) this;
        }

        public Criteria andCardKeyGreaterThan(String value) {
            addCriterion("cardKey >", value, "cardKey");
            return (Criteria) this;
        }

        public Criteria andCardKeyGreaterThanOrEqualTo(String value) {
            addCriterion("cardKey >=", value, "cardKey");
            return (Criteria) this;
        }

        public Criteria andCardKeyLessThan(String value) {
            addCriterion("cardKey <", value, "cardKey");
            return (Criteria) this;
        }

        public Criteria andCardKeyLessThanOrEqualTo(String value) {
            addCriterion("cardKey <=", value, "cardKey");
            return (Criteria) this;
        }

        public Criteria andCardKeyLike(String value) {
            addCriterion("cardKey like", value, "cardKey");
            return (Criteria) this;
        }

        public Criteria andCardKeyNotLike(String value) {
            addCriterion("cardKey not like", value, "cardKey");
            return (Criteria) this;
        }

        public Criteria andCardKeyIn(List<String> values) {
            addCriterion("cardKey in", values, "cardKey");
            return (Criteria) this;
        }

        public Criteria andCardKeyNotIn(List<String> values) {
            addCriterion("cardKey not in", values, "cardKey");
            return (Criteria) this;
        }

        public Criteria andCardKeyBetween(String value1, String value2) {
            addCriterion("cardKey between", value1, value2, "cardKey");
            return (Criteria) this;
        }

        public Criteria andCardKeyNotBetween(String value1, String value2) {
            addCriterion("cardKey not between", value1, value2, "cardKey");
            return (Criteria) this;
        }

        public Criteria andCardIDIsNull() {
            addCriterion("cardID is null");
            return (Criteria) this;
        }

        public Criteria andCardIDIsNotNull() {
            addCriterion("cardID is not null");
            return (Criteria) this;
        }

        public Criteria andCardIDEqualTo(String value) {
            addCriterion("cardID =", value, "cardID");
            return (Criteria) this;
        }

        public Criteria andCardIDNotEqualTo(String value) {
            addCriterion("cardID <>", value, "cardID");
            return (Criteria) this;
        }

        public Criteria andCardIDGreaterThan(String value) {
            addCriterion("cardID >", value, "cardID");
            return (Criteria) this;
        }

        public Criteria andCardIDGreaterThanOrEqualTo(String value) {
            addCriterion("cardID >=", value, "cardID");
            return (Criteria) this;
        }

        public Criteria andCardIDLessThan(String value) {
            addCriterion("cardID <", value, "cardID");
            return (Criteria) this;
        }

        public Criteria andCardIDLessThanOrEqualTo(String value) {
            addCriterion("cardID <=", value, "cardID");
            return (Criteria) this;
        }

        public Criteria andCardIDLike(String value) {
            addCriterion("cardID like", value, "cardID");
            return (Criteria) this;
        }

        public Criteria andCardIDNotLike(String value) {
            addCriterion("cardID not like", value, "cardID");
            return (Criteria) this;
        }

        public Criteria andCardIDIn(List<String> values) {
            addCriterion("cardID in", values, "cardID");
            return (Criteria) this;
        }

        public Criteria andCardIDNotIn(List<String> values) {
            addCriterion("cardID not in", values, "cardID");
            return (Criteria) this;
        }

        public Criteria andCardIDBetween(String value1, String value2) {
            addCriterion("cardID between", value1, value2, "cardID");
            return (Criteria) this;
        }

        public Criteria andCardIDNotBetween(String value1, String value2) {
            addCriterion("cardID not between", value1, value2, "cardID");
            return (Criteria) this;
        }

        public Criteria andGroupIDIsNull() {
            addCriterion("groupID is null");
            return (Criteria) this;
        }

        public Criteria andGroupIDIsNotNull() {
            addCriterion("groupID is not null");
            return (Criteria) this;
        }

        public Criteria andGroupIDEqualTo(Long value) {
            addCriterion("groupID =", value, "groupID");
            return (Criteria) this;
        }

        public Criteria andGroupIDNotEqualTo(Long value) {
            addCriterion("groupID <>", value, "groupID");
            return (Criteria) this;
        }

        public Criteria andGroupIDGreaterThan(Long value) {
            addCriterion("groupID >", value, "groupID");
            return (Criteria) this;
        }

        public Criteria andGroupIDGreaterThanOrEqualTo(Long value) {
            addCriterion("groupID >=", value, "groupID");
            return (Criteria) this;
        }

        public Criteria andGroupIDLessThan(Long value) {
            addCriterion("groupID <", value, "groupID");
            return (Criteria) this;
        }

        public Criteria andGroupIDLessThanOrEqualTo(Long value) {
            addCriterion("groupID <=", value, "groupID");
            return (Criteria) this;
        }

        public Criteria andGroupIDIn(List<Long> values) {
            addCriterion("groupID in", values, "groupID");
            return (Criteria) this;
        }

        public Criteria andGroupIDNotIn(List<Long> values) {
            addCriterion("groupID not in", values, "groupID");
            return (Criteria) this;
        }

        public Criteria andGroupIDBetween(Long value1, Long value2) {
            addCriterion("groupID between", value1, value2, "groupID");
            return (Criteria) this;
        }

        public Criteria andGroupIDNotBetween(Long value1, Long value2) {
            addCriterion("groupID not between", value1, value2, "groupID");
            return (Criteria) this;
        }

        public Criteria andMpIDIsNull() {
            addCriterion("mpID is null");
            return (Criteria) this;
        }

        public Criteria andMpIDIsNotNull() {
            addCriterion("mpID is not null");
            return (Criteria) this;
        }

        public Criteria andMpIDEqualTo(String value) {
            addCriterion("mpID =", value, "mpID");
            return (Criteria) this;
        }

        public Criteria andMpIDNotEqualTo(String value) {
            addCriterion("mpID <>", value, "mpID");
            return (Criteria) this;
        }

        public Criteria andMpIDGreaterThan(String value) {
            addCriterion("mpID >", value, "mpID");
            return (Criteria) this;
        }

        public Criteria andMpIDGreaterThanOrEqualTo(String value) {
            addCriterion("mpID >=", value, "mpID");
            return (Criteria) this;
        }

        public Criteria andMpIDLessThan(String value) {
            addCriterion("mpID <", value, "mpID");
            return (Criteria) this;
        }

        public Criteria andMpIDLessThanOrEqualTo(String value) {
            addCriterion("mpID <=", value, "mpID");
            return (Criteria) this;
        }

        public Criteria andMpIDLike(String value) {
            addCriterion("mpID like", value, "mpID");
            return (Criteria) this;
        }

        public Criteria andMpIDNotLike(String value) {
            addCriterion("mpID not like", value, "mpID");
            return (Criteria) this;
        }

        public Criteria andMpIDIn(List<String> values) {
            addCriterion("mpID in", values, "mpID");
            return (Criteria) this;
        }

        public Criteria andMpIDNotIn(List<String> values) {
            addCriterion("mpID not in", values, "mpID");
            return (Criteria) this;
        }

        public Criteria andMpIDBetween(String value1, String value2) {
            addCriterion("mpID between", value1, value2, "mpID");
            return (Criteria) this;
        }

        public Criteria andMpIDNotBetween(String value1, String value2) {
            addCriterion("mpID not between", value1, value2, "mpID");
            return (Criteria) this;
        }

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andCardTypeIsNull() {
            addCriterion("cardType is null");
            return (Criteria) this;
        }

        public Criteria andCardTypeIsNotNull() {
            addCriterion("cardType is not null");
            return (Criteria) this;
        }

        public Criteria andCardTypeEqualTo(String value) {
            addCriterion("cardType =", value, "cardType");
            return (Criteria) this;
        }

        public Criteria andCardTypeNotEqualTo(String value) {
            addCriterion("cardType <>", value, "cardType");
            return (Criteria) this;
        }

        public Criteria andCardTypeGreaterThan(String value) {
            addCriterion("cardType >", value, "cardType");
            return (Criteria) this;
        }

        public Criteria andCardTypeGreaterThanOrEqualTo(String value) {
            addCriterion("cardType >=", value, "cardType");
            return (Criteria) this;
        }

        public Criteria andCardTypeLessThan(String value) {
            addCriterion("cardType <", value, "cardType");
            return (Criteria) this;
        }

        public Criteria andCardTypeLessThanOrEqualTo(String value) {
            addCriterion("cardType <=", value, "cardType");
            return (Criteria) this;
        }

        public Criteria andCardTypeLike(String value) {
            addCriterion("cardType like", value, "cardType");
            return (Criteria) this;
        }

        public Criteria andCardTypeNotLike(String value) {
            addCriterion("cardType not like", value, "cardType");
            return (Criteria) this;
        }

        public Criteria andCardTypeIn(List<String> values) {
            addCriterion("cardType in", values, "cardType");
            return (Criteria) this;
        }

        public Criteria andCardTypeNotIn(List<String> values) {
            addCriterion("cardType not in", values, "cardType");
            return (Criteria) this;
        }

        public Criteria andCardTypeBetween(String value1, String value2) {
            addCriterion("cardType between", value1, value2, "cardType");
            return (Criteria) this;
        }

        public Criteria andCardTypeNotBetween(String value1, String value2) {
            addCriterion("cardType not between", value1, value2, "cardType");
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

        public Criteria andCardStatusIsNull() {
            addCriterion("cardStatus is null");
            return (Criteria) this;
        }

        public Criteria andCardStatusIsNotNull() {
            addCriterion("cardStatus is not null");
            return (Criteria) this;
        }

        public Criteria andCardStatusEqualTo(Integer value) {
            addCriterion("cardStatus =", value, "cardStatus");
            return (Criteria) this;
        }

        public Criteria andCardStatusNotEqualTo(Integer value) {
            addCriterion("cardStatus <>", value, "cardStatus");
            return (Criteria) this;
        }

        public Criteria andCardStatusGreaterThan(Integer value) {
            addCriterion("cardStatus >", value, "cardStatus");
            return (Criteria) this;
        }

        public Criteria andCardStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("cardStatus >=", value, "cardStatus");
            return (Criteria) this;
        }

        public Criteria andCardStatusLessThan(Integer value) {
            addCriterion("cardStatus <", value, "cardStatus");
            return (Criteria) this;
        }

        public Criteria andCardStatusLessThanOrEqualTo(Integer value) {
            addCriterion("cardStatus <=", value, "cardStatus");
            return (Criteria) this;
        }

        public Criteria andCardStatusIn(List<Integer> values) {
            addCriterion("cardStatus in", values, "cardStatus");
            return (Criteria) this;
        }

        public Criteria andCardStatusNotIn(List<Integer> values) {
            addCriterion("cardStatus not in", values, "cardStatus");
            return (Criteria) this;
        }

        public Criteria andCardStatusBetween(Integer value1, Integer value2) {
            addCriterion("cardStatus between", value1, value2, "cardStatus");
            return (Criteria) this;
        }

        public Criteria andCardStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("cardStatus not between", value1, value2, "cardStatus");
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

        public Criteria andActionTimeIsNull() {
            addCriterion("actionTime is null");
            return (Criteria) this;
        }

        public Criteria andActionTimeIsNotNull() {
            addCriterion("actionTime is not null");
            return (Criteria) this;
        }

        public Criteria andActionTimeEqualTo(Long value) {
            addCriterion("actionTime =", value, "actionTime");
            return (Criteria) this;
        }

        public Criteria andActionTimeNotEqualTo(Long value) {
            addCriterion("actionTime <>", value, "actionTime");
            return (Criteria) this;
        }

        public Criteria andActionTimeGreaterThan(Long value) {
            addCriterion("actionTime >", value, "actionTime");
            return (Criteria) this;
        }

        public Criteria andActionTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("actionTime >=", value, "actionTime");
            return (Criteria) this;
        }

        public Criteria andActionTimeLessThan(Long value) {
            addCriterion("actionTime <", value, "actionTime");
            return (Criteria) this;
        }

        public Criteria andActionTimeLessThanOrEqualTo(Long value) {
            addCriterion("actionTime <=", value, "actionTime");
            return (Criteria) this;
        }

        public Criteria andActionTimeIn(List<Long> values) {
            addCriterion("actionTime in", values, "actionTime");
            return (Criteria) this;
        }

        public Criteria andActionTimeNotIn(List<Long> values) {
            addCriterion("actionTime not in", values, "actionTime");
            return (Criteria) this;
        }

        public Criteria andActionTimeBetween(Long value1, Long value2) {
            addCriterion("actionTime between", value1, value2, "actionTime");
            return (Criteria) this;
        }

        public Criteria andActionTimeNotBetween(Long value1, Long value2) {
            addCriterion("actionTime not between", value1, value2, "actionTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("createTime is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("createTime is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Long value) {
            addCriterion("createTime =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Long value) {
            addCriterion("createTime <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Long value) {
            addCriterion("createTime >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("createTime >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Long value) {
            addCriterion("createTime <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Long value) {
            addCriterion("createTime <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Long> values) {
            addCriterion("createTime in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Long> values) {
            addCriterion("createTime not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Long value1, Long value2) {
            addCriterion("createTime between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Long value1, Long value2) {
            addCriterion("createTime not between", value1, value2, "createTime");
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