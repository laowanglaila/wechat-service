package com.hualala.app.wechat.model.card;

import java.util.ArrayList;
import java.util.List;

public class MemberModelQuery {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer pageNo = 1;

    protected Integer startRow;

    protected Integer pageSize = 10;

    protected String fields;

    public MemberModelQuery() {
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

        public Criteria andBackgroundPicUrlIsNull() {
            addCriterion("backgroundPicUrl is null");
            return (Criteria) this;
        }

        public Criteria andBackgroundPicUrlIsNotNull() {
            addCriterion("backgroundPicUrl is not null");
            return (Criteria) this;
        }

        public Criteria andBackgroundPicUrlEqualTo(String value) {
            addCriterion("backgroundPicUrl =", value, "backgroundPicUrl");
            return (Criteria) this;
        }

        public Criteria andBackgroundPicUrlNotEqualTo(String value) {
            addCriterion("backgroundPicUrl <>", value, "backgroundPicUrl");
            return (Criteria) this;
        }

        public Criteria andBackgroundPicUrlGreaterThan(String value) {
            addCriterion("backgroundPicUrl >", value, "backgroundPicUrl");
            return (Criteria) this;
        }

        public Criteria andBackgroundPicUrlGreaterThanOrEqualTo(String value) {
            addCriterion("backgroundPicUrl >=", value, "backgroundPicUrl");
            return (Criteria) this;
        }

        public Criteria andBackgroundPicUrlLessThan(String value) {
            addCriterion("backgroundPicUrl <", value, "backgroundPicUrl");
            return (Criteria) this;
        }

        public Criteria andBackgroundPicUrlLessThanOrEqualTo(String value) {
            addCriterion("backgroundPicUrl <=", value, "backgroundPicUrl");
            return (Criteria) this;
        }

        public Criteria andBackgroundPicUrlLike(String value) {
            addCriterion("backgroundPicUrl like", value, "backgroundPicUrl");
            return (Criteria) this;
        }

        public Criteria andBackgroundPicUrlNotLike(String value) {
            addCriterion("backgroundPicUrl not like", value, "backgroundPicUrl");
            return (Criteria) this;
        }

        public Criteria andBackgroundPicUrlIn(List<String> values) {
            addCriterion("backgroundPicUrl in", values, "backgroundPicUrl");
            return (Criteria) this;
        }

        public Criteria andBackgroundPicUrlNotIn(List<String> values) {
            addCriterion("backgroundPicUrl not in", values, "backgroundPicUrl");
            return (Criteria) this;
        }

        public Criteria andBackgroundPicUrlBetween(String value1, String value2) {
            addCriterion("backgroundPicUrl between", value1, value2, "backgroundPicUrl");
            return (Criteria) this;
        }

        public Criteria andBackgroundPicUrlNotBetween(String value1, String value2) {
            addCriterion("backgroundPicUrl not between", value1, value2, "backgroundPicUrl");
            return (Criteria) this;
        }

        public Criteria andPrerogativeIsNull() {
            addCriterion("prerogative is null");
            return (Criteria) this;
        }

        public Criteria andPrerogativeIsNotNull() {
            addCriterion("prerogative is not null");
            return (Criteria) this;
        }

        public Criteria andPrerogativeEqualTo(String value) {
            addCriterion("prerogative =", value, "prerogative");
            return (Criteria) this;
        }

        public Criteria andPrerogativeNotEqualTo(String value) {
            addCriterion("prerogative <>", value, "prerogative");
            return (Criteria) this;
        }

        public Criteria andPrerogativeGreaterThan(String value) {
            addCriterion("prerogative >", value, "prerogative");
            return (Criteria) this;
        }

        public Criteria andPrerogativeGreaterThanOrEqualTo(String value) {
            addCriterion("prerogative >=", value, "prerogative");
            return (Criteria) this;
        }

        public Criteria andPrerogativeLessThan(String value) {
            addCriterion("prerogative <", value, "prerogative");
            return (Criteria) this;
        }

        public Criteria andPrerogativeLessThanOrEqualTo(String value) {
            addCriterion("prerogative <=", value, "prerogative");
            return (Criteria) this;
        }

        public Criteria andPrerogativeLike(String value) {
            addCriterion("prerogative like", value, "prerogative");
            return (Criteria) this;
        }

        public Criteria andPrerogativeNotLike(String value) {
            addCriterion("prerogative not like", value, "prerogative");
            return (Criteria) this;
        }

        public Criteria andPrerogativeIn(List<String> values) {
            addCriterion("prerogative in", values, "prerogative");
            return (Criteria) this;
        }

        public Criteria andPrerogativeNotIn(List<String> values) {
            addCriterion("prerogative not in", values, "prerogative");
            return (Criteria) this;
        }

        public Criteria andPrerogativeBetween(String value1, String value2) {
            addCriterion("prerogative between", value1, value2, "prerogative");
            return (Criteria) this;
        }

        public Criteria andPrerogativeNotBetween(String value1, String value2) {
            addCriterion("prerogative not between", value1, value2, "prerogative");
            return (Criteria) this;
        }

        public Criteria andAutoActivateIsNull() {
            addCriterion("autoActivate is null");
            return (Criteria) this;
        }

        public Criteria andAutoActivateIsNotNull() {
            addCriterion("autoActivate is not null");
            return (Criteria) this;
        }

        public Criteria andAutoActivateEqualTo(Boolean value) {
            addCriterion("autoActivate =", value, "autoActivate");
            return (Criteria) this;
        }

        public Criteria andAutoActivateNotEqualTo(Boolean value) {
            addCriterion("autoActivate <>", value, "autoActivate");
            return (Criteria) this;
        }

        public Criteria andAutoActivateGreaterThan(Boolean value) {
            addCriterion("autoActivate >", value, "autoActivate");
            return (Criteria) this;
        }

        public Criteria andAutoActivateGreaterThanOrEqualTo(Boolean value) {
            addCriterion("autoActivate >=", value, "autoActivate");
            return (Criteria) this;
        }

        public Criteria andAutoActivateLessThan(Boolean value) {
            addCriterion("autoActivate <", value, "autoActivate");
            return (Criteria) this;
        }

        public Criteria andAutoActivateLessThanOrEqualTo(Boolean value) {
            addCriterion("autoActivate <=", value, "autoActivate");
            return (Criteria) this;
        }

        public Criteria andAutoActivateIn(List<Boolean> values) {
            addCriterion("autoActivate in", values, "autoActivate");
            return (Criteria) this;
        }

        public Criteria andAutoActivateNotIn(List<Boolean> values) {
            addCriterion("autoActivate not in", values, "autoActivate");
            return (Criteria) this;
        }

        public Criteria andAutoActivateBetween(Boolean value1, Boolean value2) {
            addCriterion("autoActivate between", value1, value2, "autoActivate");
            return (Criteria) this;
        }

        public Criteria andAutoActivateNotBetween(Boolean value1, Boolean value2) {
            addCriterion("autoActivate not between", value1, value2, "autoActivate");
            return (Criteria) this;
        }

        public Criteria andWxActivateIsNull() {
            addCriterion("wxActivate is null");
            return (Criteria) this;
        }

        public Criteria andWxActivateIsNotNull() {
            addCriterion("wxActivate is not null");
            return (Criteria) this;
        }

        public Criteria andWxActivateEqualTo(Boolean value) {
            addCriterion("wxActivate =", value, "wxActivate");
            return (Criteria) this;
        }

        public Criteria andWxActivateNotEqualTo(Boolean value) {
            addCriterion("wxActivate <>", value, "wxActivate");
            return (Criteria) this;
        }

        public Criteria andWxActivateGreaterThan(Boolean value) {
            addCriterion("wxActivate >", value, "wxActivate");
            return (Criteria) this;
        }

        public Criteria andWxActivateGreaterThanOrEqualTo(Boolean value) {
            addCriterion("wxActivate >=", value, "wxActivate");
            return (Criteria) this;
        }

        public Criteria andWxActivateLessThan(Boolean value) {
            addCriterion("wxActivate <", value, "wxActivate");
            return (Criteria) this;
        }

        public Criteria andWxActivateLessThanOrEqualTo(Boolean value) {
            addCriterion("wxActivate <=", value, "wxActivate");
            return (Criteria) this;
        }

        public Criteria andWxActivateIn(List<Boolean> values) {
            addCriterion("wxActivate in", values, "wxActivate");
            return (Criteria) this;
        }

        public Criteria andWxActivateNotIn(List<Boolean> values) {
            addCriterion("wxActivate not in", values, "wxActivate");
            return (Criteria) this;
        }

        public Criteria andWxActivateBetween(Boolean value1, Boolean value2) {
            addCriterion("wxActivate between", value1, value2, "wxActivate");
            return (Criteria) this;
        }

        public Criteria andWxActivateNotBetween(Boolean value1, Boolean value2) {
            addCriterion("wxActivate not between", value1, value2, "wxActivate");
            return (Criteria) this;
        }

        public Criteria andSupplyBonusIsNull() {
            addCriterion("supplyBonus is null");
            return (Criteria) this;
        }

        public Criteria andSupplyBonusIsNotNull() {
            addCriterion("supplyBonus is not null");
            return (Criteria) this;
        }

        public Criteria andSupplyBonusEqualTo(Boolean value) {
            addCriterion("supplyBonus =", value, "supplyBonus");
            return (Criteria) this;
        }

        public Criteria andSupplyBonusNotEqualTo(Boolean value) {
            addCriterion("supplyBonus <>", value, "supplyBonus");
            return (Criteria) this;
        }

        public Criteria andSupplyBonusGreaterThan(Boolean value) {
            addCriterion("supplyBonus >", value, "supplyBonus");
            return (Criteria) this;
        }

        public Criteria andSupplyBonusGreaterThanOrEqualTo(Boolean value) {
            addCriterion("supplyBonus >=", value, "supplyBonus");
            return (Criteria) this;
        }

        public Criteria andSupplyBonusLessThan(Boolean value) {
            addCriterion("supplyBonus <", value, "supplyBonus");
            return (Criteria) this;
        }

        public Criteria andSupplyBonusLessThanOrEqualTo(Boolean value) {
            addCriterion("supplyBonus <=", value, "supplyBonus");
            return (Criteria) this;
        }

        public Criteria andSupplyBonusIn(List<Boolean> values) {
            addCriterion("supplyBonus in", values, "supplyBonus");
            return (Criteria) this;
        }

        public Criteria andSupplyBonusNotIn(List<Boolean> values) {
            addCriterion("supplyBonus not in", values, "supplyBonus");
            return (Criteria) this;
        }

        public Criteria andSupplyBonusBetween(Boolean value1, Boolean value2) {
            addCriterion("supplyBonus between", value1, value2, "supplyBonus");
            return (Criteria) this;
        }

        public Criteria andSupplyBonusNotBetween(Boolean value1, Boolean value2) {
            addCriterion("supplyBonus not between", value1, value2, "supplyBonus");
            return (Criteria) this;
        }

        public Criteria andBonusUrlIsNull() {
            addCriterion("bonusUrl is null");
            return (Criteria) this;
        }

        public Criteria andBonusUrlIsNotNull() {
            addCriterion("bonusUrl is not null");
            return (Criteria) this;
        }

        public Criteria andBonusUrlEqualTo(String value) {
            addCriterion("bonusUrl =", value, "bonusUrl");
            return (Criteria) this;
        }

        public Criteria andBonusUrlNotEqualTo(String value) {
            addCriterion("bonusUrl <>", value, "bonusUrl");
            return (Criteria) this;
        }

        public Criteria andBonusUrlGreaterThan(String value) {
            addCriterion("bonusUrl >", value, "bonusUrl");
            return (Criteria) this;
        }

        public Criteria andBonusUrlGreaterThanOrEqualTo(String value) {
            addCriterion("bonusUrl >=", value, "bonusUrl");
            return (Criteria) this;
        }

        public Criteria andBonusUrlLessThan(String value) {
            addCriterion("bonusUrl <", value, "bonusUrl");
            return (Criteria) this;
        }

        public Criteria andBonusUrlLessThanOrEqualTo(String value) {
            addCriterion("bonusUrl <=", value, "bonusUrl");
            return (Criteria) this;
        }

        public Criteria andBonusUrlLike(String value) {
            addCriterion("bonusUrl like", value, "bonusUrl");
            return (Criteria) this;
        }

        public Criteria andBonusUrlNotLike(String value) {
            addCriterion("bonusUrl not like", value, "bonusUrl");
            return (Criteria) this;
        }

        public Criteria andBonusUrlIn(List<String> values) {
            addCriterion("bonusUrl in", values, "bonusUrl");
            return (Criteria) this;
        }

        public Criteria andBonusUrlNotIn(List<String> values) {
            addCriterion("bonusUrl not in", values, "bonusUrl");
            return (Criteria) this;
        }

        public Criteria andBonusUrlBetween(String value1, String value2) {
            addCriterion("bonusUrl between", value1, value2, "bonusUrl");
            return (Criteria) this;
        }

        public Criteria andBonusUrlNotBetween(String value1, String value2) {
            addCriterion("bonusUrl not between", value1, value2, "bonusUrl");
            return (Criteria) this;
        }

        public Criteria andSupplyBalanceIsNull() {
            addCriterion("supplyBalance is null");
            return (Criteria) this;
        }

        public Criteria andSupplyBalanceIsNotNull() {
            addCriterion("supplyBalance is not null");
            return (Criteria) this;
        }

        public Criteria andSupplyBalanceEqualTo(Boolean value) {
            addCriterion("supplyBalance =", value, "supplyBalance");
            return (Criteria) this;
        }

        public Criteria andSupplyBalanceNotEqualTo(Boolean value) {
            addCriterion("supplyBalance <>", value, "supplyBalance");
            return (Criteria) this;
        }

        public Criteria andSupplyBalanceGreaterThan(Boolean value) {
            addCriterion("supplyBalance >", value, "supplyBalance");
            return (Criteria) this;
        }

        public Criteria andSupplyBalanceGreaterThanOrEqualTo(Boolean value) {
            addCriterion("supplyBalance >=", value, "supplyBalance");
            return (Criteria) this;
        }

        public Criteria andSupplyBalanceLessThan(Boolean value) {
            addCriterion("supplyBalance <", value, "supplyBalance");
            return (Criteria) this;
        }

        public Criteria andSupplyBalanceLessThanOrEqualTo(Boolean value) {
            addCriterion("supplyBalance <=", value, "supplyBalance");
            return (Criteria) this;
        }

        public Criteria andSupplyBalanceIn(List<Boolean> values) {
            addCriterion("supplyBalance in", values, "supplyBalance");
            return (Criteria) this;
        }

        public Criteria andSupplyBalanceNotIn(List<Boolean> values) {
            addCriterion("supplyBalance not in", values, "supplyBalance");
            return (Criteria) this;
        }

        public Criteria andSupplyBalanceBetween(Boolean value1, Boolean value2) {
            addCriterion("supplyBalance between", value1, value2, "supplyBalance");
            return (Criteria) this;
        }

        public Criteria andSupplyBalanceNotBetween(Boolean value1, Boolean value2) {
            addCriterion("supplyBalance not between", value1, value2, "supplyBalance");
            return (Criteria) this;
        }

        public Criteria andBalanceUrlIsNull() {
            addCriterion("balanceUrl is null");
            return (Criteria) this;
        }

        public Criteria andBalanceUrlIsNotNull() {
            addCriterion("balanceUrl is not null");
            return (Criteria) this;
        }

        public Criteria andBalanceUrlEqualTo(String value) {
            addCriterion("balanceUrl =", value, "balanceUrl");
            return (Criteria) this;
        }

        public Criteria andBalanceUrlNotEqualTo(String value) {
            addCriterion("balanceUrl <>", value, "balanceUrl");
            return (Criteria) this;
        }

        public Criteria andBalanceUrlGreaterThan(String value) {
            addCriterion("balanceUrl >", value, "balanceUrl");
            return (Criteria) this;
        }

        public Criteria andBalanceUrlGreaterThanOrEqualTo(String value) {
            addCriterion("balanceUrl >=", value, "balanceUrl");
            return (Criteria) this;
        }

        public Criteria andBalanceUrlLessThan(String value) {
            addCriterion("balanceUrl <", value, "balanceUrl");
            return (Criteria) this;
        }

        public Criteria andBalanceUrlLessThanOrEqualTo(String value) {
            addCriterion("balanceUrl <=", value, "balanceUrl");
            return (Criteria) this;
        }

        public Criteria andBalanceUrlLike(String value) {
            addCriterion("balanceUrl like", value, "balanceUrl");
            return (Criteria) this;
        }

        public Criteria andBalanceUrlNotLike(String value) {
            addCriterion("balanceUrl not like", value, "balanceUrl");
            return (Criteria) this;
        }

        public Criteria andBalanceUrlIn(List<String> values) {
            addCriterion("balanceUrl in", values, "balanceUrl");
            return (Criteria) this;
        }

        public Criteria andBalanceUrlNotIn(List<String> values) {
            addCriterion("balanceUrl not in", values, "balanceUrl");
            return (Criteria) this;
        }

        public Criteria andBalanceUrlBetween(String value1, String value2) {
            addCriterion("balanceUrl between", value1, value2, "balanceUrl");
            return (Criteria) this;
        }

        public Criteria andBalanceUrlNotBetween(String value1, String value2) {
            addCriterion("balanceUrl not between", value1, value2, "balanceUrl");
            return (Criteria) this;
        }

        public Criteria andCustomField1IsNull() {
            addCriterion("customField1 is null");
            return (Criteria) this;
        }

        public Criteria andCustomField1IsNotNull() {
            addCriterion("customField1 is not null");
            return (Criteria) this;
        }

        public Criteria andCustomField1EqualTo(String value) {
            addCriterion("customField1 =", value, "customField1");
            return (Criteria) this;
        }

        public Criteria andCustomField1NotEqualTo(String value) {
            addCriterion("customField1 <>", value, "customField1");
            return (Criteria) this;
        }

        public Criteria andCustomField1GreaterThan(String value) {
            addCriterion("customField1 >", value, "customField1");
            return (Criteria) this;
        }

        public Criteria andCustomField1GreaterThanOrEqualTo(String value) {
            addCriterion("customField1 >=", value, "customField1");
            return (Criteria) this;
        }

        public Criteria andCustomField1LessThan(String value) {
            addCriterion("customField1 <", value, "customField1");
            return (Criteria) this;
        }

        public Criteria andCustomField1LessThanOrEqualTo(String value) {
            addCriterion("customField1 <=", value, "customField1");
            return (Criteria) this;
        }

        public Criteria andCustomField1Like(String value) {
            addCriterion("customField1 like", value, "customField1");
            return (Criteria) this;
        }

        public Criteria andCustomField1NotLike(String value) {
            addCriterion("customField1 not like", value, "customField1");
            return (Criteria) this;
        }

        public Criteria andCustomField1In(List<String> values) {
            addCriterion("customField1 in", values, "customField1");
            return (Criteria) this;
        }

        public Criteria andCustomField1NotIn(List<String> values) {
            addCriterion("customField1 not in", values, "customField1");
            return (Criteria) this;
        }

        public Criteria andCustomField1Between(String value1, String value2) {
            addCriterion("customField1 between", value1, value2, "customField1");
            return (Criteria) this;
        }

        public Criteria andCustomField1NotBetween(String value1, String value2) {
            addCriterion("customField1 not between", value1, value2, "customField1");
            return (Criteria) this;
        }

        public Criteria andCustomField2IsNull() {
            addCriterion("customField2 is null");
            return (Criteria) this;
        }

        public Criteria andCustomField2IsNotNull() {
            addCriterion("customField2 is not null");
            return (Criteria) this;
        }

        public Criteria andCustomField2EqualTo(String value) {
            addCriterion("customField2 =", value, "customField2");
            return (Criteria) this;
        }

        public Criteria andCustomField2NotEqualTo(String value) {
            addCriterion("customField2 <>", value, "customField2");
            return (Criteria) this;
        }

        public Criteria andCustomField2GreaterThan(String value) {
            addCriterion("customField2 >", value, "customField2");
            return (Criteria) this;
        }

        public Criteria andCustomField2GreaterThanOrEqualTo(String value) {
            addCriterion("customField2 >=", value, "customField2");
            return (Criteria) this;
        }

        public Criteria andCustomField2LessThan(String value) {
            addCriterion("customField2 <", value, "customField2");
            return (Criteria) this;
        }

        public Criteria andCustomField2LessThanOrEqualTo(String value) {
            addCriterion("customField2 <=", value, "customField2");
            return (Criteria) this;
        }

        public Criteria andCustomField2Like(String value) {
            addCriterion("customField2 like", value, "customField2");
            return (Criteria) this;
        }

        public Criteria andCustomField2NotLike(String value) {
            addCriterion("customField2 not like", value, "customField2");
            return (Criteria) this;
        }

        public Criteria andCustomField2In(List<String> values) {
            addCriterion("customField2 in", values, "customField2");
            return (Criteria) this;
        }

        public Criteria andCustomField2NotIn(List<String> values) {
            addCriterion("customField2 not in", values, "customField2");
            return (Criteria) this;
        }

        public Criteria andCustomField2Between(String value1, String value2) {
            addCriterion("customField2 between", value1, value2, "customField2");
            return (Criteria) this;
        }

        public Criteria andCustomField2NotBetween(String value1, String value2) {
            addCriterion("customField2 not between", value1, value2, "customField2");
            return (Criteria) this;
        }

        public Criteria andCustomField3IsNull() {
            addCriterion("customField3 is null");
            return (Criteria) this;
        }

        public Criteria andCustomField3IsNotNull() {
            addCriterion("customField3 is not null");
            return (Criteria) this;
        }

        public Criteria andCustomField3EqualTo(String value) {
            addCriterion("customField3 =", value, "customField3");
            return (Criteria) this;
        }

        public Criteria andCustomField3NotEqualTo(String value) {
            addCriterion("customField3 <>", value, "customField3");
            return (Criteria) this;
        }

        public Criteria andCustomField3GreaterThan(String value) {
            addCriterion("customField3 >", value, "customField3");
            return (Criteria) this;
        }

        public Criteria andCustomField3GreaterThanOrEqualTo(String value) {
            addCriterion("customField3 >=", value, "customField3");
            return (Criteria) this;
        }

        public Criteria andCustomField3LessThan(String value) {
            addCriterion("customField3 <", value, "customField3");
            return (Criteria) this;
        }

        public Criteria andCustomField3LessThanOrEqualTo(String value) {
            addCriterion("customField3 <=", value, "customField3");
            return (Criteria) this;
        }

        public Criteria andCustomField3Like(String value) {
            addCriterion("customField3 like", value, "customField3");
            return (Criteria) this;
        }

        public Criteria andCustomField3NotLike(String value) {
            addCriterion("customField3 not like", value, "customField3");
            return (Criteria) this;
        }

        public Criteria andCustomField3In(List<String> values) {
            addCriterion("customField3 in", values, "customField3");
            return (Criteria) this;
        }

        public Criteria andCustomField3NotIn(List<String> values) {
            addCriterion("customField3 not in", values, "customField3");
            return (Criteria) this;
        }

        public Criteria andCustomField3Between(String value1, String value2) {
            addCriterion("customField3 between", value1, value2, "customField3");
            return (Criteria) this;
        }

        public Criteria andCustomField3NotBetween(String value1, String value2) {
            addCriterion("customField3 not between", value1, value2, "customField3");
            return (Criteria) this;
        }

        public Criteria andCustomCell1IsNull() {
            addCriterion("customCell1 is null");
            return (Criteria) this;
        }

        public Criteria andCustomCell1IsNotNull() {
            addCriterion("customCell1 is not null");
            return (Criteria) this;
        }

        public Criteria andCustomCell1EqualTo(String value) {
            addCriterion("customCell1 =", value, "customCell1");
            return (Criteria) this;
        }

        public Criteria andCustomCell1NotEqualTo(String value) {
            addCriterion("customCell1 <>", value, "customCell1");
            return (Criteria) this;
        }

        public Criteria andCustomCell1GreaterThan(String value) {
            addCriterion("customCell1 >", value, "customCell1");
            return (Criteria) this;
        }

        public Criteria andCustomCell1GreaterThanOrEqualTo(String value) {
            addCriterion("customCell1 >=", value, "customCell1");
            return (Criteria) this;
        }

        public Criteria andCustomCell1LessThan(String value) {
            addCriterion("customCell1 <", value, "customCell1");
            return (Criteria) this;
        }

        public Criteria andCustomCell1LessThanOrEqualTo(String value) {
            addCriterion("customCell1 <=", value, "customCell1");
            return (Criteria) this;
        }

        public Criteria andCustomCell1Like(String value) {
            addCriterion("customCell1 like", value, "customCell1");
            return (Criteria) this;
        }

        public Criteria andCustomCell1NotLike(String value) {
            addCriterion("customCell1 not like", value, "customCell1");
            return (Criteria) this;
        }

        public Criteria andCustomCell1In(List<String> values) {
            addCriterion("customCell1 in", values, "customCell1");
            return (Criteria) this;
        }

        public Criteria andCustomCell1NotIn(List<String> values) {
            addCriterion("customCell1 not in", values, "customCell1");
            return (Criteria) this;
        }

        public Criteria andCustomCell1Between(String value1, String value2) {
            addCriterion("customCell1 between", value1, value2, "customCell1");
            return (Criteria) this;
        }

        public Criteria andCustomCell1NotBetween(String value1, String value2) {
            addCriterion("customCell1 not between", value1, value2, "customCell1");
            return (Criteria) this;
        }

        public Criteria andBonusRuleIsNull() {
            addCriterion("bonusRule is null");
            return (Criteria) this;
        }

        public Criteria andBonusRuleIsNotNull() {
            addCriterion("bonusRule is not null");
            return (Criteria) this;
        }

        public Criteria andBonusRuleEqualTo(String value) {
            addCriterion("bonusRule =", value, "bonusRule");
            return (Criteria) this;
        }

        public Criteria andBonusRuleNotEqualTo(String value) {
            addCriterion("bonusRule <>", value, "bonusRule");
            return (Criteria) this;
        }

        public Criteria andBonusRuleGreaterThan(String value) {
            addCriterion("bonusRule >", value, "bonusRule");
            return (Criteria) this;
        }

        public Criteria andBonusRuleGreaterThanOrEqualTo(String value) {
            addCriterion("bonusRule >=", value, "bonusRule");
            return (Criteria) this;
        }

        public Criteria andBonusRuleLessThan(String value) {
            addCriterion("bonusRule <", value, "bonusRule");
            return (Criteria) this;
        }

        public Criteria andBonusRuleLessThanOrEqualTo(String value) {
            addCriterion("bonusRule <=", value, "bonusRule");
            return (Criteria) this;
        }

        public Criteria andBonusRuleLike(String value) {
            addCriterion("bonusRule like", value, "bonusRule");
            return (Criteria) this;
        }

        public Criteria andBonusRuleNotLike(String value) {
            addCriterion("bonusRule not like", value, "bonusRule");
            return (Criteria) this;
        }

        public Criteria andBonusRuleIn(List<String> values) {
            addCriterion("bonusRule in", values, "bonusRule");
            return (Criteria) this;
        }

        public Criteria andBonusRuleNotIn(List<String> values) {
            addCriterion("bonusRule not in", values, "bonusRule");
            return (Criteria) this;
        }

        public Criteria andBonusRuleBetween(String value1, String value2) {
            addCriterion("bonusRule between", value1, value2, "bonusRule");
            return (Criteria) this;
        }

        public Criteria andBonusRuleNotBetween(String value1, String value2) {
            addCriterion("bonusRule not between", value1, value2, "bonusRule");
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