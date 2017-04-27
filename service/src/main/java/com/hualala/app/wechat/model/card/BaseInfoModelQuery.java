package com.hualala.app.wechat.model.card;

import java.util.ArrayList;
import java.util.List;

public class BaseInfoModelQuery {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer pageNo = 1;

    protected Integer startRow;

    protected Integer pageSize = 10;

    protected String fields;

    public BaseInfoModelQuery() {
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

        public Criteria andLogoUrlIsNull() {
            addCriterion("logoUrl is null");
            return (Criteria) this;
        }

        public Criteria andLogoUrlIsNotNull() {
            addCriterion("logoUrl is not null");
            return (Criteria) this;
        }

        public Criteria andLogoUrlEqualTo(String value) {
            addCriterion("logoUrl =", value, "logoUrl");
            return (Criteria) this;
        }

        public Criteria andLogoUrlNotEqualTo(String value) {
            addCriterion("logoUrl <>", value, "logoUrl");
            return (Criteria) this;
        }

        public Criteria andLogoUrlGreaterThan(String value) {
            addCriterion("logoUrl >", value, "logoUrl");
            return (Criteria) this;
        }

        public Criteria andLogoUrlGreaterThanOrEqualTo(String value) {
            addCriterion("logoUrl >=", value, "logoUrl");
            return (Criteria) this;
        }

        public Criteria andLogoUrlLessThan(String value) {
            addCriterion("logoUrl <", value, "logoUrl");
            return (Criteria) this;
        }

        public Criteria andLogoUrlLessThanOrEqualTo(String value) {
            addCriterion("logoUrl <=", value, "logoUrl");
            return (Criteria) this;
        }

        public Criteria andLogoUrlLike(String value) {
            addCriterion("logoUrl like", value, "logoUrl");
            return (Criteria) this;
        }

        public Criteria andLogoUrlNotLike(String value) {
            addCriterion("logoUrl not like", value, "logoUrl");
            return (Criteria) this;
        }

        public Criteria andLogoUrlIn(List<String> values) {
            addCriterion("logoUrl in", values, "logoUrl");
            return (Criteria) this;
        }

        public Criteria andLogoUrlNotIn(List<String> values) {
            addCriterion("logoUrl not in", values, "logoUrl");
            return (Criteria) this;
        }

        public Criteria andLogoUrlBetween(String value1, String value2) {
            addCriterion("logoUrl between", value1, value2, "logoUrl");
            return (Criteria) this;
        }

        public Criteria andLogoUrlNotBetween(String value1, String value2) {
            addCriterion("logoUrl not between", value1, value2, "logoUrl");
            return (Criteria) this;
        }

        public Criteria andCodeTypeIsNull() {
            addCriterion("codeType is null");
            return (Criteria) this;
        }

        public Criteria andCodeTypeIsNotNull() {
            addCriterion("codeType is not null");
            return (Criteria) this;
        }

        public Criteria andCodeTypeEqualTo(String value) {
            addCriterion("codeType =", value, "codeType");
            return (Criteria) this;
        }

        public Criteria andCodeTypeNotEqualTo(String value) {
            addCriterion("codeType <>", value, "codeType");
            return (Criteria) this;
        }

        public Criteria andCodeTypeGreaterThan(String value) {
            addCriterion("codeType >", value, "codeType");
            return (Criteria) this;
        }

        public Criteria andCodeTypeGreaterThanOrEqualTo(String value) {
            addCriterion("codeType >=", value, "codeType");
            return (Criteria) this;
        }

        public Criteria andCodeTypeLessThan(String value) {
            addCriterion("codeType <", value, "codeType");
            return (Criteria) this;
        }

        public Criteria andCodeTypeLessThanOrEqualTo(String value) {
            addCriterion("codeType <=", value, "codeType");
            return (Criteria) this;
        }

        public Criteria andCodeTypeLike(String value) {
            addCriterion("codeType like", value, "codeType");
            return (Criteria) this;
        }

        public Criteria andCodeTypeNotLike(String value) {
            addCriterion("codeType not like", value, "codeType");
            return (Criteria) this;
        }

        public Criteria andCodeTypeIn(List<String> values) {
            addCriterion("codeType in", values, "codeType");
            return (Criteria) this;
        }

        public Criteria andCodeTypeNotIn(List<String> values) {
            addCriterion("codeType not in", values, "codeType");
            return (Criteria) this;
        }

        public Criteria andCodeTypeBetween(String value1, String value2) {
            addCriterion("codeType between", value1, value2, "codeType");
            return (Criteria) this;
        }

        public Criteria andCodeTypeNotBetween(String value1, String value2) {
            addCriterion("codeType not between", value1, value2, "codeType");
            return (Criteria) this;
        }

        public Criteria andColorIsNull() {
            addCriterion("color is null");
            return (Criteria) this;
        }

        public Criteria andColorIsNotNull() {
            addCriterion("color is not null");
            return (Criteria) this;
        }

        public Criteria andColorEqualTo(String value) {
            addCriterion("color =", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorNotEqualTo(String value) {
            addCriterion("color <>", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorGreaterThan(String value) {
            addCriterion("color >", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorGreaterThanOrEqualTo(String value) {
            addCriterion("color >=", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorLessThan(String value) {
            addCriterion("color <", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorLessThanOrEqualTo(String value) {
            addCriterion("color <=", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorLike(String value) {
            addCriterion("color like", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorNotLike(String value) {
            addCriterion("color not like", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorIn(List<String> values) {
            addCriterion("color in", values, "color");
            return (Criteria) this;
        }

        public Criteria andColorNotIn(List<String> values) {
            addCriterion("color not in", values, "color");
            return (Criteria) this;
        }

        public Criteria andColorBetween(String value1, String value2) {
            addCriterion("color between", value1, value2, "color");
            return (Criteria) this;
        }

        public Criteria andColorNotBetween(String value1, String value2) {
            addCriterion("color not between", value1, value2, "color");
            return (Criteria) this;
        }

        public Criteria andBrandNameIsNull() {
            addCriterion("brandName is null");
            return (Criteria) this;
        }

        public Criteria andBrandNameIsNotNull() {
            addCriterion("brandName is not null");
            return (Criteria) this;
        }

        public Criteria andBrandNameEqualTo(String value) {
            addCriterion("brandName =", value, "brandName");
            return (Criteria) this;
        }

        public Criteria andBrandNameNotEqualTo(String value) {
            addCriterion("brandName <>", value, "brandName");
            return (Criteria) this;
        }

        public Criteria andBrandNameGreaterThan(String value) {
            addCriterion("brandName >", value, "brandName");
            return (Criteria) this;
        }

        public Criteria andBrandNameGreaterThanOrEqualTo(String value) {
            addCriterion("brandName >=", value, "brandName");
            return (Criteria) this;
        }

        public Criteria andBrandNameLessThan(String value) {
            addCriterion("brandName <", value, "brandName");
            return (Criteria) this;
        }

        public Criteria andBrandNameLessThanOrEqualTo(String value) {
            addCriterion("brandName <=", value, "brandName");
            return (Criteria) this;
        }

        public Criteria andBrandNameLike(String value) {
            addCriterion("brandName like", value, "brandName");
            return (Criteria) this;
        }

        public Criteria andBrandNameNotLike(String value) {
            addCriterion("brandName not like", value, "brandName");
            return (Criteria) this;
        }

        public Criteria andBrandNameIn(List<String> values) {
            addCriterion("brandName in", values, "brandName");
            return (Criteria) this;
        }

        public Criteria andBrandNameNotIn(List<String> values) {
            addCriterion("brandName not in", values, "brandName");
            return (Criteria) this;
        }

        public Criteria andBrandNameBetween(String value1, String value2) {
            addCriterion("brandName between", value1, value2, "brandName");
            return (Criteria) this;
        }

        public Criteria andBrandNameNotBetween(String value1, String value2) {
            addCriterion("brandName not between", value1, value2, "brandName");
            return (Criteria) this;
        }

        public Criteria andNoticeIsNull() {
            addCriterion("notice is null");
            return (Criteria) this;
        }

        public Criteria andNoticeIsNotNull() {
            addCriterion("notice is not null");
            return (Criteria) this;
        }

        public Criteria andNoticeEqualTo(String value) {
            addCriterion("notice =", value, "notice");
            return (Criteria) this;
        }

        public Criteria andNoticeNotEqualTo(String value) {
            addCriterion("notice <>", value, "notice");
            return (Criteria) this;
        }

        public Criteria andNoticeGreaterThan(String value) {
            addCriterion("notice >", value, "notice");
            return (Criteria) this;
        }

        public Criteria andNoticeGreaterThanOrEqualTo(String value) {
            addCriterion("notice >=", value, "notice");
            return (Criteria) this;
        }

        public Criteria andNoticeLessThan(String value) {
            addCriterion("notice <", value, "notice");
            return (Criteria) this;
        }

        public Criteria andNoticeLessThanOrEqualTo(String value) {
            addCriterion("notice <=", value, "notice");
            return (Criteria) this;
        }

        public Criteria andNoticeLike(String value) {
            addCriterion("notice like", value, "notice");
            return (Criteria) this;
        }

        public Criteria andNoticeNotLike(String value) {
            addCriterion("notice not like", value, "notice");
            return (Criteria) this;
        }

        public Criteria andNoticeIn(List<String> values) {
            addCriterion("notice in", values, "notice");
            return (Criteria) this;
        }

        public Criteria andNoticeNotIn(List<String> values) {
            addCriterion("notice not in", values, "notice");
            return (Criteria) this;
        }

        public Criteria andNoticeBetween(String value1, String value2) {
            addCriterion("notice between", value1, value2, "notice");
            return (Criteria) this;
        }

        public Criteria andNoticeNotBetween(String value1, String value2) {
            addCriterion("notice not between", value1, value2, "notice");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("description is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("description =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("description <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("description >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("description >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("description <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("description <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("description like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("description not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("description in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("description not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("description between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("description not between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andSkuIsNull() {
            addCriterion("sku is null");
            return (Criteria) this;
        }

        public Criteria andSkuIsNotNull() {
            addCriterion("sku is not null");
            return (Criteria) this;
        }

        public Criteria andSkuEqualTo(Integer value) {
            addCriterion("sku =", value, "sku");
            return (Criteria) this;
        }

        public Criteria andSkuNotEqualTo(Integer value) {
            addCriterion("sku <>", value, "sku");
            return (Criteria) this;
        }

        public Criteria andSkuGreaterThan(Integer value) {
            addCriterion("sku >", value, "sku");
            return (Criteria) this;
        }

        public Criteria andSkuGreaterThanOrEqualTo(Integer value) {
            addCriterion("sku >=", value, "sku");
            return (Criteria) this;
        }

        public Criteria andSkuLessThan(Integer value) {
            addCriterion("sku <", value, "sku");
            return (Criteria) this;
        }

        public Criteria andSkuLessThanOrEqualTo(Integer value) {
            addCriterion("sku <=", value, "sku");
            return (Criteria) this;
        }

        public Criteria andSkuIn(List<Integer> values) {
            addCriterion("sku in", values, "sku");
            return (Criteria) this;
        }

        public Criteria andSkuNotIn(List<Integer> values) {
            addCriterion("sku not in", values, "sku");
            return (Criteria) this;
        }

        public Criteria andSkuBetween(Integer value1, Integer value2) {
            addCriterion("sku between", value1, value2, "sku");
            return (Criteria) this;
        }

        public Criteria andSkuNotBetween(Integer value1, Integer value2) {
            addCriterion("sku not between", value1, value2, "sku");
            return (Criteria) this;
        }

        public Criteria andDateInfoIsNull() {
            addCriterion("dateInfo is null");
            return (Criteria) this;
        }

        public Criteria andDateInfoIsNotNull() {
            addCriterion("dateInfo is not null");
            return (Criteria) this;
        }

        public Criteria andDateInfoEqualTo(String value) {
            addCriterion("dateInfo =", value, "dateInfo");
            return (Criteria) this;
        }

        public Criteria andDateInfoNotEqualTo(String value) {
            addCriterion("dateInfo <>", value, "dateInfo");
            return (Criteria) this;
        }

        public Criteria andDateInfoGreaterThan(String value) {
            addCriterion("dateInfo >", value, "dateInfo");
            return (Criteria) this;
        }

        public Criteria andDateInfoGreaterThanOrEqualTo(String value) {
            addCriterion("dateInfo >=", value, "dateInfo");
            return (Criteria) this;
        }

        public Criteria andDateInfoLessThan(String value) {
            addCriterion("dateInfo <", value, "dateInfo");
            return (Criteria) this;
        }

        public Criteria andDateInfoLessThanOrEqualTo(String value) {
            addCriterion("dateInfo <=", value, "dateInfo");
            return (Criteria) this;
        }

        public Criteria andDateInfoLike(String value) {
            addCriterion("dateInfo like", value, "dateInfo");
            return (Criteria) this;
        }

        public Criteria andDateInfoNotLike(String value) {
            addCriterion("dateInfo not like", value, "dateInfo");
            return (Criteria) this;
        }

        public Criteria andDateInfoIn(List<String> values) {
            addCriterion("dateInfo in", values, "dateInfo");
            return (Criteria) this;
        }

        public Criteria andDateInfoNotIn(List<String> values) {
            addCriterion("dateInfo not in", values, "dateInfo");
            return (Criteria) this;
        }

        public Criteria andDateInfoBetween(String value1, String value2) {
            addCriterion("dateInfo between", value1, value2, "dateInfo");
            return (Criteria) this;
        }

        public Criteria andDateInfoNotBetween(String value1, String value2) {
            addCriterion("dateInfo not between", value1, value2, "dateInfo");
            return (Criteria) this;
        }

        public Criteria andUseCustomCodeIsNull() {
            addCriterion("useCustomCode is null");
            return (Criteria) this;
        }

        public Criteria andUseCustomCodeIsNotNull() {
            addCriterion("useCustomCode is not null");
            return (Criteria) this;
        }

        public Criteria andUseCustomCodeEqualTo(Boolean value) {
            addCriterion("useCustomCode =", value, "useCustomCode");
            return (Criteria) this;
        }

        public Criteria andUseCustomCodeNotEqualTo(Boolean value) {
            addCriterion("useCustomCode <>", value, "useCustomCode");
            return (Criteria) this;
        }

        public Criteria andUseCustomCodeGreaterThan(Boolean value) {
            addCriterion("useCustomCode >", value, "useCustomCode");
            return (Criteria) this;
        }

        public Criteria andUseCustomCodeGreaterThanOrEqualTo(Boolean value) {
            addCriterion("useCustomCode >=", value, "useCustomCode");
            return (Criteria) this;
        }

        public Criteria andUseCustomCodeLessThan(Boolean value) {
            addCriterion("useCustomCode <", value, "useCustomCode");
            return (Criteria) this;
        }

        public Criteria andUseCustomCodeLessThanOrEqualTo(Boolean value) {
            addCriterion("useCustomCode <=", value, "useCustomCode");
            return (Criteria) this;
        }

        public Criteria andUseCustomCodeIn(List<Boolean> values) {
            addCriterion("useCustomCode in", values, "useCustomCode");
            return (Criteria) this;
        }

        public Criteria andUseCustomCodeNotIn(List<Boolean> values) {
            addCriterion("useCustomCode not in", values, "useCustomCode");
            return (Criteria) this;
        }

        public Criteria andUseCustomCodeBetween(Boolean value1, Boolean value2) {
            addCriterion("useCustomCode between", value1, value2, "useCustomCode");
            return (Criteria) this;
        }

        public Criteria andUseCustomCodeNotBetween(Boolean value1, Boolean value2) {
            addCriterion("useCustomCode not between", value1, value2, "useCustomCode");
            return (Criteria) this;
        }

        public Criteria andBindOpenidIsNull() {
            addCriterion("bindOpenid is null");
            return (Criteria) this;
        }

        public Criteria andBindOpenidIsNotNull() {
            addCriterion("bindOpenid is not null");
            return (Criteria) this;
        }

        public Criteria andBindOpenidEqualTo(Boolean value) {
            addCriterion("bindOpenid =", value, "bindOpenid");
            return (Criteria) this;
        }

        public Criteria andBindOpenidNotEqualTo(Boolean value) {
            addCriterion("bindOpenid <>", value, "bindOpenid");
            return (Criteria) this;
        }

        public Criteria andBindOpenidGreaterThan(Boolean value) {
            addCriterion("bindOpenid >", value, "bindOpenid");
            return (Criteria) this;
        }

        public Criteria andBindOpenidGreaterThanOrEqualTo(Boolean value) {
            addCriterion("bindOpenid >=", value, "bindOpenid");
            return (Criteria) this;
        }

        public Criteria andBindOpenidLessThan(Boolean value) {
            addCriterion("bindOpenid <", value, "bindOpenid");
            return (Criteria) this;
        }

        public Criteria andBindOpenidLessThanOrEqualTo(Boolean value) {
            addCriterion("bindOpenid <=", value, "bindOpenid");
            return (Criteria) this;
        }

        public Criteria andBindOpenidIn(List<Boolean> values) {
            addCriterion("bindOpenid in", values, "bindOpenid");
            return (Criteria) this;
        }

        public Criteria andBindOpenidNotIn(List<Boolean> values) {
            addCriterion("bindOpenid not in", values, "bindOpenid");
            return (Criteria) this;
        }

        public Criteria andBindOpenidBetween(Boolean value1, Boolean value2) {
            addCriterion("bindOpenid between", value1, value2, "bindOpenid");
            return (Criteria) this;
        }

        public Criteria andBindOpenidNotBetween(Boolean value1, Boolean value2) {
            addCriterion("bindOpenid not between", value1, value2, "bindOpenid");
            return (Criteria) this;
        }

        public Criteria andServicePhoneIsNull() {
            addCriterion("servicePhone is null");
            return (Criteria) this;
        }

        public Criteria andServicePhoneIsNotNull() {
            addCriterion("servicePhone is not null");
            return (Criteria) this;
        }

        public Criteria andServicePhoneEqualTo(String value) {
            addCriterion("servicePhone =", value, "servicePhone");
            return (Criteria) this;
        }

        public Criteria andServicePhoneNotEqualTo(String value) {
            addCriterion("servicePhone <>", value, "servicePhone");
            return (Criteria) this;
        }

        public Criteria andServicePhoneGreaterThan(String value) {
            addCriterion("servicePhone >", value, "servicePhone");
            return (Criteria) this;
        }

        public Criteria andServicePhoneGreaterThanOrEqualTo(String value) {
            addCriterion("servicePhone >=", value, "servicePhone");
            return (Criteria) this;
        }

        public Criteria andServicePhoneLessThan(String value) {
            addCriterion("servicePhone <", value, "servicePhone");
            return (Criteria) this;
        }

        public Criteria andServicePhoneLessThanOrEqualTo(String value) {
            addCriterion("servicePhone <=", value, "servicePhone");
            return (Criteria) this;
        }

        public Criteria andServicePhoneLike(String value) {
            addCriterion("servicePhone like", value, "servicePhone");
            return (Criteria) this;
        }

        public Criteria andServicePhoneNotLike(String value) {
            addCriterion("servicePhone not like", value, "servicePhone");
            return (Criteria) this;
        }

        public Criteria andServicePhoneIn(List<String> values) {
            addCriterion("servicePhone in", values, "servicePhone");
            return (Criteria) this;
        }

        public Criteria andServicePhoneNotIn(List<String> values) {
            addCriterion("servicePhone not in", values, "servicePhone");
            return (Criteria) this;
        }

        public Criteria andServicePhoneBetween(String value1, String value2) {
            addCriterion("servicePhone between", value1, value2, "servicePhone");
            return (Criteria) this;
        }

        public Criteria andServicePhoneNotBetween(String value1, String value2) {
            addCriterion("servicePhone not between", value1, value2, "servicePhone");
            return (Criteria) this;
        }

        public Criteria andLocationIdListIsNull() {
            addCriterion("locationIdList is null");
            return (Criteria) this;
        }

        public Criteria andLocationIdListIsNotNull() {
            addCriterion("locationIdList is not null");
            return (Criteria) this;
        }

        public Criteria andLocationIdListEqualTo(String value) {
            addCriterion("locationIdList =", value, "locationIdList");
            return (Criteria) this;
        }

        public Criteria andLocationIdListNotEqualTo(String value) {
            addCriterion("locationIdList <>", value, "locationIdList");
            return (Criteria) this;
        }

        public Criteria andLocationIdListGreaterThan(String value) {
            addCriterion("locationIdList >", value, "locationIdList");
            return (Criteria) this;
        }

        public Criteria andLocationIdListGreaterThanOrEqualTo(String value) {
            addCriterion("locationIdList >=", value, "locationIdList");
            return (Criteria) this;
        }

        public Criteria andLocationIdListLessThan(String value) {
            addCriterion("locationIdList <", value, "locationIdList");
            return (Criteria) this;
        }

        public Criteria andLocationIdListLessThanOrEqualTo(String value) {
            addCriterion("locationIdList <=", value, "locationIdList");
            return (Criteria) this;
        }

        public Criteria andLocationIdListLike(String value) {
            addCriterion("locationIdList like", value, "locationIdList");
            return (Criteria) this;
        }

        public Criteria andLocationIdListNotLike(String value) {
            addCriterion("locationIdList not like", value, "locationIdList");
            return (Criteria) this;
        }

        public Criteria andLocationIdListIn(List<String> values) {
            addCriterion("locationIdList in", values, "locationIdList");
            return (Criteria) this;
        }

        public Criteria andLocationIdListNotIn(List<String> values) {
            addCriterion("locationIdList not in", values, "locationIdList");
            return (Criteria) this;
        }

        public Criteria andLocationIdListBetween(String value1, String value2) {
            addCriterion("locationIdList between", value1, value2, "locationIdList");
            return (Criteria) this;
        }

        public Criteria andLocationIdListNotBetween(String value1, String value2) {
            addCriterion("locationIdList not between", value1, value2, "locationIdList");
            return (Criteria) this;
        }

        public Criteria andSourceIsNull() {
            addCriterion("source is null");
            return (Criteria) this;
        }

        public Criteria andSourceIsNotNull() {
            addCriterion("source is not null");
            return (Criteria) this;
        }

        public Criteria andSourceEqualTo(String value) {
            addCriterion("source =", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotEqualTo(String value) {
            addCriterion("source <>", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceGreaterThan(String value) {
            addCriterion("source >", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceGreaterThanOrEqualTo(String value) {
            addCriterion("source >=", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLessThan(String value) {
            addCriterion("source <", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLessThanOrEqualTo(String value) {
            addCriterion("source <=", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLike(String value) {
            addCriterion("source like", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotLike(String value) {
            addCriterion("source not like", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceIn(List<String> values) {
            addCriterion("source in", values, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotIn(List<String> values) {
            addCriterion("source not in", values, "source");
            return (Criteria) this;
        }

        public Criteria andSourceBetween(String value1, String value2) {
            addCriterion("source between", value1, value2, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotBetween(String value1, String value2) {
            addCriterion("source not between", value1, value2, "source");
            return (Criteria) this;
        }

        public Criteria andCustomUrlNameIsNull() {
            addCriterion("customUrlName is null");
            return (Criteria) this;
        }

        public Criteria andCustomUrlNameIsNotNull() {
            addCriterion("customUrlName is not null");
            return (Criteria) this;
        }

        public Criteria andCustomUrlNameEqualTo(String value) {
            addCriterion("customUrlName =", value, "customUrlName");
            return (Criteria) this;
        }

        public Criteria andCustomUrlNameNotEqualTo(String value) {
            addCriterion("customUrlName <>", value, "customUrlName");
            return (Criteria) this;
        }

        public Criteria andCustomUrlNameGreaterThan(String value) {
            addCriterion("customUrlName >", value, "customUrlName");
            return (Criteria) this;
        }

        public Criteria andCustomUrlNameGreaterThanOrEqualTo(String value) {
            addCriterion("customUrlName >=", value, "customUrlName");
            return (Criteria) this;
        }

        public Criteria andCustomUrlNameLessThan(String value) {
            addCriterion("customUrlName <", value, "customUrlName");
            return (Criteria) this;
        }

        public Criteria andCustomUrlNameLessThanOrEqualTo(String value) {
            addCriterion("customUrlName <=", value, "customUrlName");
            return (Criteria) this;
        }

        public Criteria andCustomUrlNameLike(String value) {
            addCriterion("customUrlName like", value, "customUrlName");
            return (Criteria) this;
        }

        public Criteria andCustomUrlNameNotLike(String value) {
            addCriterion("customUrlName not like", value, "customUrlName");
            return (Criteria) this;
        }

        public Criteria andCustomUrlNameIn(List<String> values) {
            addCriterion("customUrlName in", values, "customUrlName");
            return (Criteria) this;
        }

        public Criteria andCustomUrlNameNotIn(List<String> values) {
            addCriterion("customUrlName not in", values, "customUrlName");
            return (Criteria) this;
        }

        public Criteria andCustomUrlNameBetween(String value1, String value2) {
            addCriterion("customUrlName between", value1, value2, "customUrlName");
            return (Criteria) this;
        }

        public Criteria andCustomUrlNameNotBetween(String value1, String value2) {
            addCriterion("customUrlName not between", value1, value2, "customUrlName");
            return (Criteria) this;
        }

        public Criteria andCustomUrlIsNull() {
            addCriterion("customUrl is null");
            return (Criteria) this;
        }

        public Criteria andCustomUrlIsNotNull() {
            addCriterion("customUrl is not null");
            return (Criteria) this;
        }

        public Criteria andCustomUrlEqualTo(String value) {
            addCriterion("customUrl =", value, "customUrl");
            return (Criteria) this;
        }

        public Criteria andCustomUrlNotEqualTo(String value) {
            addCriterion("customUrl <>", value, "customUrl");
            return (Criteria) this;
        }

        public Criteria andCustomUrlGreaterThan(String value) {
            addCriterion("customUrl >", value, "customUrl");
            return (Criteria) this;
        }

        public Criteria andCustomUrlGreaterThanOrEqualTo(String value) {
            addCriterion("customUrl >=", value, "customUrl");
            return (Criteria) this;
        }

        public Criteria andCustomUrlLessThan(String value) {
            addCriterion("customUrl <", value, "customUrl");
            return (Criteria) this;
        }

        public Criteria andCustomUrlLessThanOrEqualTo(String value) {
            addCriterion("customUrl <=", value, "customUrl");
            return (Criteria) this;
        }

        public Criteria andCustomUrlLike(String value) {
            addCriterion("customUrl like", value, "customUrl");
            return (Criteria) this;
        }

        public Criteria andCustomUrlNotLike(String value) {
            addCriterion("customUrl not like", value, "customUrl");
            return (Criteria) this;
        }

        public Criteria andCustomUrlIn(List<String> values) {
            addCriterion("customUrl in", values, "customUrl");
            return (Criteria) this;
        }

        public Criteria andCustomUrlNotIn(List<String> values) {
            addCriterion("customUrl not in", values, "customUrl");
            return (Criteria) this;
        }

        public Criteria andCustomUrlBetween(String value1, String value2) {
            addCriterion("customUrl between", value1, value2, "customUrl");
            return (Criteria) this;
        }

        public Criteria andCustomUrlNotBetween(String value1, String value2) {
            addCriterion("customUrl not between", value1, value2, "customUrl");
            return (Criteria) this;
        }

        public Criteria andCustomUrlSubTitleIsNull() {
            addCriterion("customUrlSubTitle is null");
            return (Criteria) this;
        }

        public Criteria andCustomUrlSubTitleIsNotNull() {
            addCriterion("customUrlSubTitle is not null");
            return (Criteria) this;
        }

        public Criteria andCustomUrlSubTitleEqualTo(String value) {
            addCriterion("customUrlSubTitle =", value, "customUrlSubTitle");
            return (Criteria) this;
        }

        public Criteria andCustomUrlSubTitleNotEqualTo(String value) {
            addCriterion("customUrlSubTitle <>", value, "customUrlSubTitle");
            return (Criteria) this;
        }

        public Criteria andCustomUrlSubTitleGreaterThan(String value) {
            addCriterion("customUrlSubTitle >", value, "customUrlSubTitle");
            return (Criteria) this;
        }

        public Criteria andCustomUrlSubTitleGreaterThanOrEqualTo(String value) {
            addCriterion("customUrlSubTitle >=", value, "customUrlSubTitle");
            return (Criteria) this;
        }

        public Criteria andCustomUrlSubTitleLessThan(String value) {
            addCriterion("customUrlSubTitle <", value, "customUrlSubTitle");
            return (Criteria) this;
        }

        public Criteria andCustomUrlSubTitleLessThanOrEqualTo(String value) {
            addCriterion("customUrlSubTitle <=", value, "customUrlSubTitle");
            return (Criteria) this;
        }

        public Criteria andCustomUrlSubTitleLike(String value) {
            addCriterion("customUrlSubTitle like", value, "customUrlSubTitle");
            return (Criteria) this;
        }

        public Criteria andCustomUrlSubTitleNotLike(String value) {
            addCriterion("customUrlSubTitle not like", value, "customUrlSubTitle");
            return (Criteria) this;
        }

        public Criteria andCustomUrlSubTitleIn(List<String> values) {
            addCriterion("customUrlSubTitle in", values, "customUrlSubTitle");
            return (Criteria) this;
        }

        public Criteria andCustomUrlSubTitleNotIn(List<String> values) {
            addCriterion("customUrlSubTitle not in", values, "customUrlSubTitle");
            return (Criteria) this;
        }

        public Criteria andCustomUrlSubTitleBetween(String value1, String value2) {
            addCriterion("customUrlSubTitle between", value1, value2, "customUrlSubTitle");
            return (Criteria) this;
        }

        public Criteria andCustomUrlSubTitleNotBetween(String value1, String value2) {
            addCriterion("customUrlSubTitle not between", value1, value2, "customUrlSubTitle");
            return (Criteria) this;
        }

        public Criteria andPromotionUrlNameIsNull() {
            addCriterion("promotionUrlName is null");
            return (Criteria) this;
        }

        public Criteria andPromotionUrlNameIsNotNull() {
            addCriterion("promotionUrlName is not null");
            return (Criteria) this;
        }

        public Criteria andPromotionUrlNameEqualTo(String value) {
            addCriterion("promotionUrlName =", value, "promotionUrlName");
            return (Criteria) this;
        }

        public Criteria andPromotionUrlNameNotEqualTo(String value) {
            addCriterion("promotionUrlName <>", value, "promotionUrlName");
            return (Criteria) this;
        }

        public Criteria andPromotionUrlNameGreaterThan(String value) {
            addCriterion("promotionUrlName >", value, "promotionUrlName");
            return (Criteria) this;
        }

        public Criteria andPromotionUrlNameGreaterThanOrEqualTo(String value) {
            addCriterion("promotionUrlName >=", value, "promotionUrlName");
            return (Criteria) this;
        }

        public Criteria andPromotionUrlNameLessThan(String value) {
            addCriterion("promotionUrlName <", value, "promotionUrlName");
            return (Criteria) this;
        }

        public Criteria andPromotionUrlNameLessThanOrEqualTo(String value) {
            addCriterion("promotionUrlName <=", value, "promotionUrlName");
            return (Criteria) this;
        }

        public Criteria andPromotionUrlNameLike(String value) {
            addCriterion("promotionUrlName like", value, "promotionUrlName");
            return (Criteria) this;
        }

        public Criteria andPromotionUrlNameNotLike(String value) {
            addCriterion("promotionUrlName not like", value, "promotionUrlName");
            return (Criteria) this;
        }

        public Criteria andPromotionUrlNameIn(List<String> values) {
            addCriterion("promotionUrlName in", values, "promotionUrlName");
            return (Criteria) this;
        }

        public Criteria andPromotionUrlNameNotIn(List<String> values) {
            addCriterion("promotionUrlName not in", values, "promotionUrlName");
            return (Criteria) this;
        }

        public Criteria andPromotionUrlNameBetween(String value1, String value2) {
            addCriterion("promotionUrlName between", value1, value2, "promotionUrlName");
            return (Criteria) this;
        }

        public Criteria andPromotionUrlNameNotBetween(String value1, String value2) {
            addCriterion("promotionUrlName not between", value1, value2, "promotionUrlName");
            return (Criteria) this;
        }

        public Criteria andPromotionUrlIsNull() {
            addCriterion("promotionUrl is null");
            return (Criteria) this;
        }

        public Criteria andPromotionUrlIsNotNull() {
            addCriterion("promotionUrl is not null");
            return (Criteria) this;
        }

        public Criteria andPromotionUrlEqualTo(String value) {
            addCriterion("promotionUrl =", value, "promotionUrl");
            return (Criteria) this;
        }

        public Criteria andPromotionUrlNotEqualTo(String value) {
            addCriterion("promotionUrl <>", value, "promotionUrl");
            return (Criteria) this;
        }

        public Criteria andPromotionUrlGreaterThan(String value) {
            addCriterion("promotionUrl >", value, "promotionUrl");
            return (Criteria) this;
        }

        public Criteria andPromotionUrlGreaterThanOrEqualTo(String value) {
            addCriterion("promotionUrl >=", value, "promotionUrl");
            return (Criteria) this;
        }

        public Criteria andPromotionUrlLessThan(String value) {
            addCriterion("promotionUrl <", value, "promotionUrl");
            return (Criteria) this;
        }

        public Criteria andPromotionUrlLessThanOrEqualTo(String value) {
            addCriterion("promotionUrl <=", value, "promotionUrl");
            return (Criteria) this;
        }

        public Criteria andPromotionUrlLike(String value) {
            addCriterion("promotionUrl like", value, "promotionUrl");
            return (Criteria) this;
        }

        public Criteria andPromotionUrlNotLike(String value) {
            addCriterion("promotionUrl not like", value, "promotionUrl");
            return (Criteria) this;
        }

        public Criteria andPromotionUrlIn(List<String> values) {
            addCriterion("promotionUrl in", values, "promotionUrl");
            return (Criteria) this;
        }

        public Criteria andPromotionUrlNotIn(List<String> values) {
            addCriterion("promotionUrl not in", values, "promotionUrl");
            return (Criteria) this;
        }

        public Criteria andPromotionUrlBetween(String value1, String value2) {
            addCriterion("promotionUrl between", value1, value2, "promotionUrl");
            return (Criteria) this;
        }

        public Criteria andPromotionUrlNotBetween(String value1, String value2) {
            addCriterion("promotionUrl not between", value1, value2, "promotionUrl");
            return (Criteria) this;
        }

        public Criteria andPromotionUrlSubTitleIsNull() {
            addCriterion("promotionUrlSubTitle is null");
            return (Criteria) this;
        }

        public Criteria andPromotionUrlSubTitleIsNotNull() {
            addCriterion("promotionUrlSubTitle is not null");
            return (Criteria) this;
        }

        public Criteria andPromotionUrlSubTitleEqualTo(String value) {
            addCriterion("promotionUrlSubTitle =", value, "promotionUrlSubTitle");
            return (Criteria) this;
        }

        public Criteria andPromotionUrlSubTitleNotEqualTo(String value) {
            addCriterion("promotionUrlSubTitle <>", value, "promotionUrlSubTitle");
            return (Criteria) this;
        }

        public Criteria andPromotionUrlSubTitleGreaterThan(String value) {
            addCriterion("promotionUrlSubTitle >", value, "promotionUrlSubTitle");
            return (Criteria) this;
        }

        public Criteria andPromotionUrlSubTitleGreaterThanOrEqualTo(String value) {
            addCriterion("promotionUrlSubTitle >=", value, "promotionUrlSubTitle");
            return (Criteria) this;
        }

        public Criteria andPromotionUrlSubTitleLessThan(String value) {
            addCriterion("promotionUrlSubTitle <", value, "promotionUrlSubTitle");
            return (Criteria) this;
        }

        public Criteria andPromotionUrlSubTitleLessThanOrEqualTo(String value) {
            addCriterion("promotionUrlSubTitle <=", value, "promotionUrlSubTitle");
            return (Criteria) this;
        }

        public Criteria andPromotionUrlSubTitleLike(String value) {
            addCriterion("promotionUrlSubTitle like", value, "promotionUrlSubTitle");
            return (Criteria) this;
        }

        public Criteria andPromotionUrlSubTitleNotLike(String value) {
            addCriterion("promotionUrlSubTitle not like", value, "promotionUrlSubTitle");
            return (Criteria) this;
        }

        public Criteria andPromotionUrlSubTitleIn(List<String> values) {
            addCriterion("promotionUrlSubTitle in", values, "promotionUrlSubTitle");
            return (Criteria) this;
        }

        public Criteria andPromotionUrlSubTitleNotIn(List<String> values) {
            addCriterion("promotionUrlSubTitle not in", values, "promotionUrlSubTitle");
            return (Criteria) this;
        }

        public Criteria andPromotionUrlSubTitleBetween(String value1, String value2) {
            addCriterion("promotionUrlSubTitle between", value1, value2, "promotionUrlSubTitle");
            return (Criteria) this;
        }

        public Criteria andPromotionUrlSubTitleNotBetween(String value1, String value2) {
            addCriterion("promotionUrlSubTitle not between", value1, value2, "promotionUrlSubTitle");
            return (Criteria) this;
        }

        public Criteria andGetLimitIsNull() {
            addCriterion("getLimit is null");
            return (Criteria) this;
        }

        public Criteria andGetLimitIsNotNull() {
            addCriterion("getLimit is not null");
            return (Criteria) this;
        }

        public Criteria andGetLimitEqualTo(Integer value) {
            addCriterion("getLimit =", value, "getLimit");
            return (Criteria) this;
        }

        public Criteria andGetLimitNotEqualTo(Integer value) {
            addCriterion("getLimit <>", value, "getLimit");
            return (Criteria) this;
        }

        public Criteria andGetLimitGreaterThan(Integer value) {
            addCriterion("getLimit >", value, "getLimit");
            return (Criteria) this;
        }

        public Criteria andGetLimitGreaterThanOrEqualTo(Integer value) {
            addCriterion("getLimit >=", value, "getLimit");
            return (Criteria) this;
        }

        public Criteria andGetLimitLessThan(Integer value) {
            addCriterion("getLimit <", value, "getLimit");
            return (Criteria) this;
        }

        public Criteria andGetLimitLessThanOrEqualTo(Integer value) {
            addCriterion("getLimit <=", value, "getLimit");
            return (Criteria) this;
        }

        public Criteria andGetLimitIn(List<Integer> values) {
            addCriterion("getLimit in", values, "getLimit");
            return (Criteria) this;
        }

        public Criteria andGetLimitNotIn(List<Integer> values) {
            addCriterion("getLimit not in", values, "getLimit");
            return (Criteria) this;
        }

        public Criteria andGetLimitBetween(Integer value1, Integer value2) {
            addCriterion("getLimit between", value1, value2, "getLimit");
            return (Criteria) this;
        }

        public Criteria andGetLimitNotBetween(Integer value1, Integer value2) {
            addCriterion("getLimit not between", value1, value2, "getLimit");
            return (Criteria) this;
        }

        public Criteria andCanShareIsNull() {
            addCriterion("canShare is null");
            return (Criteria) this;
        }

        public Criteria andCanShareIsNotNull() {
            addCriterion("canShare is not null");
            return (Criteria) this;
        }

        public Criteria andCanShareEqualTo(Boolean value) {
            addCriterion("canShare =", value, "canShare");
            return (Criteria) this;
        }

        public Criteria andCanShareNotEqualTo(Boolean value) {
            addCriterion("canShare <>", value, "canShare");
            return (Criteria) this;
        }

        public Criteria andCanShareGreaterThan(Boolean value) {
            addCriterion("canShare >", value, "canShare");
            return (Criteria) this;
        }

        public Criteria andCanShareGreaterThanOrEqualTo(Boolean value) {
            addCriterion("canShare >=", value, "canShare");
            return (Criteria) this;
        }

        public Criteria andCanShareLessThan(Boolean value) {
            addCriterion("canShare <", value, "canShare");
            return (Criteria) this;
        }

        public Criteria andCanShareLessThanOrEqualTo(Boolean value) {
            addCriterion("canShare <=", value, "canShare");
            return (Criteria) this;
        }

        public Criteria andCanShareIn(List<Boolean> values) {
            addCriterion("canShare in", values, "canShare");
            return (Criteria) this;
        }

        public Criteria andCanShareNotIn(List<Boolean> values) {
            addCriterion("canShare not in", values, "canShare");
            return (Criteria) this;
        }

        public Criteria andCanShareBetween(Boolean value1, Boolean value2) {
            addCriterion("canShare between", value1, value2, "canShare");
            return (Criteria) this;
        }

        public Criteria andCanShareNotBetween(Boolean value1, Boolean value2) {
            addCriterion("canShare not between", value1, value2, "canShare");
            return (Criteria) this;
        }

        public Criteria andCanGiveFriendIsNull() {
            addCriterion("canGiveFriend is null");
            return (Criteria) this;
        }

        public Criteria andCanGiveFriendIsNotNull() {
            addCriterion("canGiveFriend is not null");
            return (Criteria) this;
        }

        public Criteria andCanGiveFriendEqualTo(Boolean value) {
            addCriterion("canGiveFriend =", value, "canGiveFriend");
            return (Criteria) this;
        }

        public Criteria andCanGiveFriendNotEqualTo(Boolean value) {
            addCriterion("canGiveFriend <>", value, "canGiveFriend");
            return (Criteria) this;
        }

        public Criteria andCanGiveFriendGreaterThan(Boolean value) {
            addCriterion("canGiveFriend >", value, "canGiveFriend");
            return (Criteria) this;
        }

        public Criteria andCanGiveFriendGreaterThanOrEqualTo(Boolean value) {
            addCriterion("canGiveFriend >=", value, "canGiveFriend");
            return (Criteria) this;
        }

        public Criteria andCanGiveFriendLessThan(Boolean value) {
            addCriterion("canGiveFriend <", value, "canGiveFriend");
            return (Criteria) this;
        }

        public Criteria andCanGiveFriendLessThanOrEqualTo(Boolean value) {
            addCriterion("canGiveFriend <=", value, "canGiveFriend");
            return (Criteria) this;
        }

        public Criteria andCanGiveFriendIn(List<Boolean> values) {
            addCriterion("canGiveFriend in", values, "canGiveFriend");
            return (Criteria) this;
        }

        public Criteria andCanGiveFriendNotIn(List<Boolean> values) {
            addCriterion("canGiveFriend not in", values, "canGiveFriend");
            return (Criteria) this;
        }

        public Criteria andCanGiveFriendBetween(Boolean value1, Boolean value2) {
            addCriterion("canGiveFriend between", value1, value2, "canGiveFriend");
            return (Criteria) this;
        }

        public Criteria andCanGiveFriendNotBetween(Boolean value1, Boolean value2) {
            addCriterion("canGiveFriend not between", value1, value2, "canGiveFriend");
            return (Criteria) this;
        }

        public Criteria andNeedPushOnViewIsNull() {
            addCriterion("needPushOnView is null");
            return (Criteria) this;
        }

        public Criteria andNeedPushOnViewIsNotNull() {
            addCriterion("needPushOnView is not null");
            return (Criteria) this;
        }

        public Criteria andNeedPushOnViewEqualTo(Boolean value) {
            addCriterion("needPushOnView =", value, "needPushOnView");
            return (Criteria) this;
        }

        public Criteria andNeedPushOnViewNotEqualTo(Boolean value) {
            addCriterion("needPushOnView <>", value, "needPushOnView");
            return (Criteria) this;
        }

        public Criteria andNeedPushOnViewGreaterThan(Boolean value) {
            addCriterion("needPushOnView >", value, "needPushOnView");
            return (Criteria) this;
        }

        public Criteria andNeedPushOnViewGreaterThanOrEqualTo(Boolean value) {
            addCriterion("needPushOnView >=", value, "needPushOnView");
            return (Criteria) this;
        }

        public Criteria andNeedPushOnViewLessThan(Boolean value) {
            addCriterion("needPushOnView <", value, "needPushOnView");
            return (Criteria) this;
        }

        public Criteria andNeedPushOnViewLessThanOrEqualTo(Boolean value) {
            addCriterion("needPushOnView <=", value, "needPushOnView");
            return (Criteria) this;
        }

        public Criteria andNeedPushOnViewIn(List<Boolean> values) {
            addCriterion("needPushOnView in", values, "needPushOnView");
            return (Criteria) this;
        }

        public Criteria andNeedPushOnViewNotIn(List<Boolean> values) {
            addCriterion("needPushOnView not in", values, "needPushOnView");
            return (Criteria) this;
        }

        public Criteria andNeedPushOnViewBetween(Boolean value1, Boolean value2) {
            addCriterion("needPushOnView between", value1, value2, "needPushOnView");
            return (Criteria) this;
        }

        public Criteria andNeedPushOnViewNotBetween(Boolean value1, Boolean value2) {
            addCriterion("needPushOnView not between", value1, value2, "needPushOnView");
            return (Criteria) this;
        }

        public Criteria andCustomCodeModeIsNull() {
            addCriterion("customCodeMode is null");
            return (Criteria) this;
        }

        public Criteria andCustomCodeModeIsNotNull() {
            addCriterion("customCodeMode is not null");
            return (Criteria) this;
        }

        public Criteria andCustomCodeModeEqualTo(String value) {
            addCriterion("customCodeMode =", value, "customCodeMode");
            return (Criteria) this;
        }

        public Criteria andCustomCodeModeNotEqualTo(String value) {
            addCriterion("customCodeMode <>", value, "customCodeMode");
            return (Criteria) this;
        }

        public Criteria andCustomCodeModeGreaterThan(String value) {
            addCriterion("customCodeMode >", value, "customCodeMode");
            return (Criteria) this;
        }

        public Criteria andCustomCodeModeGreaterThanOrEqualTo(String value) {
            addCriterion("customCodeMode >=", value, "customCodeMode");
            return (Criteria) this;
        }

        public Criteria andCustomCodeModeLessThan(String value) {
            addCriterion("customCodeMode <", value, "customCodeMode");
            return (Criteria) this;
        }

        public Criteria andCustomCodeModeLessThanOrEqualTo(String value) {
            addCriterion("customCodeMode <=", value, "customCodeMode");
            return (Criteria) this;
        }

        public Criteria andCustomCodeModeLike(String value) {
            addCriterion("customCodeMode like", value, "customCodeMode");
            return (Criteria) this;
        }

        public Criteria andCustomCodeModeNotLike(String value) {
            addCriterion("customCodeMode not like", value, "customCodeMode");
            return (Criteria) this;
        }

        public Criteria andCustomCodeModeIn(List<String> values) {
            addCriterion("customCodeMode in", values, "customCodeMode");
            return (Criteria) this;
        }

        public Criteria andCustomCodeModeNotIn(List<String> values) {
            addCriterion("customCodeMode not in", values, "customCodeMode");
            return (Criteria) this;
        }

        public Criteria andCustomCodeModeBetween(String value1, String value2) {
            addCriterion("customCodeMode between", value1, value2, "customCodeMode");
            return (Criteria) this;
        }

        public Criteria andCustomCodeModeNotBetween(String value1, String value2) {
            addCriterion("customCodeMode not between", value1, value2, "customCodeMode");
            return (Criteria) this;
        }

        public Criteria andUseAllLocationsIsNull() {
            addCriterion("useAllLocations is null");
            return (Criteria) this;
        }

        public Criteria andUseAllLocationsIsNotNull() {
            addCriterion("useAllLocations is not null");
            return (Criteria) this;
        }

        public Criteria andUseAllLocationsEqualTo(Boolean value) {
            addCriterion("useAllLocations =", value, "useAllLocations");
            return (Criteria) this;
        }

        public Criteria andUseAllLocationsNotEqualTo(Boolean value) {
            addCriterion("useAllLocations <>", value, "useAllLocations");
            return (Criteria) this;
        }

        public Criteria andUseAllLocationsGreaterThan(Boolean value) {
            addCriterion("useAllLocations >", value, "useAllLocations");
            return (Criteria) this;
        }

        public Criteria andUseAllLocationsGreaterThanOrEqualTo(Boolean value) {
            addCriterion("useAllLocations >=", value, "useAllLocations");
            return (Criteria) this;
        }

        public Criteria andUseAllLocationsLessThan(Boolean value) {
            addCriterion("useAllLocations <", value, "useAllLocations");
            return (Criteria) this;
        }

        public Criteria andUseAllLocationsLessThanOrEqualTo(Boolean value) {
            addCriterion("useAllLocations <=", value, "useAllLocations");
            return (Criteria) this;
        }

        public Criteria andUseAllLocationsIn(List<Boolean> values) {
            addCriterion("useAllLocations in", values, "useAllLocations");
            return (Criteria) this;
        }

        public Criteria andUseAllLocationsNotIn(List<Boolean> values) {
            addCriterion("useAllLocations not in", values, "useAllLocations");
            return (Criteria) this;
        }

        public Criteria andUseAllLocationsBetween(Boolean value1, Boolean value2) {
            addCriterion("useAllLocations between", value1, value2, "useAllLocations");
            return (Criteria) this;
        }

        public Criteria andUseAllLocationsNotBetween(Boolean value1, Boolean value2) {
            addCriterion("useAllLocations not between", value1, value2, "useAllLocations");
            return (Criteria) this;
        }

        public Criteria andCenterTitleIsNull() {
            addCriterion("centerTitle is null");
            return (Criteria) this;
        }

        public Criteria andCenterTitleIsNotNull() {
            addCriterion("centerTitle is not null");
            return (Criteria) this;
        }

        public Criteria andCenterTitleEqualTo(String value) {
            addCriterion("centerTitle =", value, "centerTitle");
            return (Criteria) this;
        }

        public Criteria andCenterTitleNotEqualTo(String value) {
            addCriterion("centerTitle <>", value, "centerTitle");
            return (Criteria) this;
        }

        public Criteria andCenterTitleGreaterThan(String value) {
            addCriterion("centerTitle >", value, "centerTitle");
            return (Criteria) this;
        }

        public Criteria andCenterTitleGreaterThanOrEqualTo(String value) {
            addCriterion("centerTitle >=", value, "centerTitle");
            return (Criteria) this;
        }

        public Criteria andCenterTitleLessThan(String value) {
            addCriterion("centerTitle <", value, "centerTitle");
            return (Criteria) this;
        }

        public Criteria andCenterTitleLessThanOrEqualTo(String value) {
            addCriterion("centerTitle <=", value, "centerTitle");
            return (Criteria) this;
        }

        public Criteria andCenterTitleLike(String value) {
            addCriterion("centerTitle like", value, "centerTitle");
            return (Criteria) this;
        }

        public Criteria andCenterTitleNotLike(String value) {
            addCriterion("centerTitle not like", value, "centerTitle");
            return (Criteria) this;
        }

        public Criteria andCenterTitleIn(List<String> values) {
            addCriterion("centerTitle in", values, "centerTitle");
            return (Criteria) this;
        }

        public Criteria andCenterTitleNotIn(List<String> values) {
            addCriterion("centerTitle not in", values, "centerTitle");
            return (Criteria) this;
        }

        public Criteria andCenterTitleBetween(String value1, String value2) {
            addCriterion("centerTitle between", value1, value2, "centerTitle");
            return (Criteria) this;
        }

        public Criteria andCenterTitleNotBetween(String value1, String value2) {
            addCriterion("centerTitle not between", value1, value2, "centerTitle");
            return (Criteria) this;
        }

        public Criteria andCenterSubTitleIsNull() {
            addCriterion("centerSubTitle is null");
            return (Criteria) this;
        }

        public Criteria andCenterSubTitleIsNotNull() {
            addCriterion("centerSubTitle is not null");
            return (Criteria) this;
        }

        public Criteria andCenterSubTitleEqualTo(String value) {
            addCriterion("centerSubTitle =", value, "centerSubTitle");
            return (Criteria) this;
        }

        public Criteria andCenterSubTitleNotEqualTo(String value) {
            addCriterion("centerSubTitle <>", value, "centerSubTitle");
            return (Criteria) this;
        }

        public Criteria andCenterSubTitleGreaterThan(String value) {
            addCriterion("centerSubTitle >", value, "centerSubTitle");
            return (Criteria) this;
        }

        public Criteria andCenterSubTitleGreaterThanOrEqualTo(String value) {
            addCriterion("centerSubTitle >=", value, "centerSubTitle");
            return (Criteria) this;
        }

        public Criteria andCenterSubTitleLessThan(String value) {
            addCriterion("centerSubTitle <", value, "centerSubTitle");
            return (Criteria) this;
        }

        public Criteria andCenterSubTitleLessThanOrEqualTo(String value) {
            addCriterion("centerSubTitle <=", value, "centerSubTitle");
            return (Criteria) this;
        }

        public Criteria andCenterSubTitleLike(String value) {
            addCriterion("centerSubTitle like", value, "centerSubTitle");
            return (Criteria) this;
        }

        public Criteria andCenterSubTitleNotLike(String value) {
            addCriterion("centerSubTitle not like", value, "centerSubTitle");
            return (Criteria) this;
        }

        public Criteria andCenterSubTitleIn(List<String> values) {
            addCriterion("centerSubTitle in", values, "centerSubTitle");
            return (Criteria) this;
        }

        public Criteria andCenterSubTitleNotIn(List<String> values) {
            addCriterion("centerSubTitle not in", values, "centerSubTitle");
            return (Criteria) this;
        }

        public Criteria andCenterSubTitleBetween(String value1, String value2) {
            addCriterion("centerSubTitle between", value1, value2, "centerSubTitle");
            return (Criteria) this;
        }

        public Criteria andCenterSubTitleNotBetween(String value1, String value2) {
            addCriterion("centerSubTitle not between", value1, value2, "centerSubTitle");
            return (Criteria) this;
        }

        public Criteria andCenterUrlIsNull() {
            addCriterion("centerUrl is null");
            return (Criteria) this;
        }

        public Criteria andCenterUrlIsNotNull() {
            addCriterion("centerUrl is not null");
            return (Criteria) this;
        }

        public Criteria andCenterUrlEqualTo(String value) {
            addCriterion("centerUrl =", value, "centerUrl");
            return (Criteria) this;
        }

        public Criteria andCenterUrlNotEqualTo(String value) {
            addCriterion("centerUrl <>", value, "centerUrl");
            return (Criteria) this;
        }

        public Criteria andCenterUrlGreaterThan(String value) {
            addCriterion("centerUrl >", value, "centerUrl");
            return (Criteria) this;
        }

        public Criteria andCenterUrlGreaterThanOrEqualTo(String value) {
            addCriterion("centerUrl >=", value, "centerUrl");
            return (Criteria) this;
        }

        public Criteria andCenterUrlLessThan(String value) {
            addCriterion("centerUrl <", value, "centerUrl");
            return (Criteria) this;
        }

        public Criteria andCenterUrlLessThanOrEqualTo(String value) {
            addCriterion("centerUrl <=", value, "centerUrl");
            return (Criteria) this;
        }

        public Criteria andCenterUrlLike(String value) {
            addCriterion("centerUrl like", value, "centerUrl");
            return (Criteria) this;
        }

        public Criteria andCenterUrlNotLike(String value) {
            addCriterion("centerUrl not like", value, "centerUrl");
            return (Criteria) this;
        }

        public Criteria andCenterUrlIn(List<String> values) {
            addCriterion("centerUrl in", values, "centerUrl");
            return (Criteria) this;
        }

        public Criteria andCenterUrlNotIn(List<String> values) {
            addCriterion("centerUrl not in", values, "centerUrl");
            return (Criteria) this;
        }

        public Criteria andCenterUrlBetween(String value1, String value2) {
            addCriterion("centerUrl between", value1, value2, "centerUrl");
            return (Criteria) this;
        }

        public Criteria andCenterUrlNotBetween(String value1, String value2) {
            addCriterion("centerUrl not between", value1, value2, "centerUrl");
            return (Criteria) this;
        }

        public Criteria andUseLimitIsNull() {
            addCriterion("useLimit is null");
            return (Criteria) this;
        }

        public Criteria andUseLimitIsNotNull() {
            addCriterion("useLimit is not null");
            return (Criteria) this;
        }

        public Criteria andUseLimitEqualTo(Integer value) {
            addCriterion("useLimit =", value, "useLimit");
            return (Criteria) this;
        }

        public Criteria andUseLimitNotEqualTo(Integer value) {
            addCriterion("useLimit <>", value, "useLimit");
            return (Criteria) this;
        }

        public Criteria andUseLimitGreaterThan(Integer value) {
            addCriterion("useLimit >", value, "useLimit");
            return (Criteria) this;
        }

        public Criteria andUseLimitGreaterThanOrEqualTo(Integer value) {
            addCriterion("useLimit >=", value, "useLimit");
            return (Criteria) this;
        }

        public Criteria andUseLimitLessThan(Integer value) {
            addCriterion("useLimit <", value, "useLimit");
            return (Criteria) this;
        }

        public Criteria andUseLimitLessThanOrEqualTo(Integer value) {
            addCriterion("useLimit <=", value, "useLimit");
            return (Criteria) this;
        }

        public Criteria andUseLimitIn(List<Integer> values) {
            addCriterion("useLimit in", values, "useLimit");
            return (Criteria) this;
        }

        public Criteria andUseLimitNotIn(List<Integer> values) {
            addCriterion("useLimit not in", values, "useLimit");
            return (Criteria) this;
        }

        public Criteria andUseLimitBetween(Integer value1, Integer value2) {
            addCriterion("useLimit between", value1, value2, "useLimit");
            return (Criteria) this;
        }

        public Criteria andUseLimitNotBetween(Integer value1, Integer value2) {
            addCriterion("useLimit not between", value1, value2, "useLimit");
            return (Criteria) this;
        }

        public Criteria andIsSwipeCardIsNull() {
            addCriterion("isSwipeCard is null");
            return (Criteria) this;
        }

        public Criteria andIsSwipeCardIsNotNull() {
            addCriterion("isSwipeCard is not null");
            return (Criteria) this;
        }

        public Criteria andIsSwipeCardEqualTo(Boolean value) {
            addCriterion("isSwipeCard =", value, "isSwipeCard");
            return (Criteria) this;
        }

        public Criteria andIsSwipeCardNotEqualTo(Boolean value) {
            addCriterion("isSwipeCard <>", value, "isSwipeCard");
            return (Criteria) this;
        }

        public Criteria andIsSwipeCardGreaterThan(Boolean value) {
            addCriterion("isSwipeCard >", value, "isSwipeCard");
            return (Criteria) this;
        }

        public Criteria andIsSwipeCardGreaterThanOrEqualTo(Boolean value) {
            addCriterion("isSwipeCard >=", value, "isSwipeCard");
            return (Criteria) this;
        }

        public Criteria andIsSwipeCardLessThan(Boolean value) {
            addCriterion("isSwipeCard <", value, "isSwipeCard");
            return (Criteria) this;
        }

        public Criteria andIsSwipeCardLessThanOrEqualTo(Boolean value) {
            addCriterion("isSwipeCard <=", value, "isSwipeCard");
            return (Criteria) this;
        }

        public Criteria andIsSwipeCardIn(List<Boolean> values) {
            addCriterion("isSwipeCard in", values, "isSwipeCard");
            return (Criteria) this;
        }

        public Criteria andIsSwipeCardNotIn(List<Boolean> values) {
            addCriterion("isSwipeCard not in", values, "isSwipeCard");
            return (Criteria) this;
        }

        public Criteria andIsSwipeCardBetween(Boolean value1, Boolean value2) {
            addCriterion("isSwipeCard between", value1, value2, "isSwipeCard");
            return (Criteria) this;
        }

        public Criteria andIsSwipeCardNotBetween(Boolean value1, Boolean value2) {
            addCriterion("isSwipeCard not between", value1, value2, "isSwipeCard");
            return (Criteria) this;
        }

        public Criteria andIsPayAndQrcodeIsNull() {
            addCriterion("isPayAndQrcode is null");
            return (Criteria) this;
        }

        public Criteria andIsPayAndQrcodeIsNotNull() {
            addCriterion("isPayAndQrcode is not null");
            return (Criteria) this;
        }

        public Criteria andIsPayAndQrcodeEqualTo(Boolean value) {
            addCriterion("isPayAndQrcode =", value, "isPayAndQrcode");
            return (Criteria) this;
        }

        public Criteria andIsPayAndQrcodeNotEqualTo(Boolean value) {
            addCriterion("isPayAndQrcode <>", value, "isPayAndQrcode");
            return (Criteria) this;
        }

        public Criteria andIsPayAndQrcodeGreaterThan(Boolean value) {
            addCriterion("isPayAndQrcode >", value, "isPayAndQrcode");
            return (Criteria) this;
        }

        public Criteria andIsPayAndQrcodeGreaterThanOrEqualTo(Boolean value) {
            addCriterion("isPayAndQrcode >=", value, "isPayAndQrcode");
            return (Criteria) this;
        }

        public Criteria andIsPayAndQrcodeLessThan(Boolean value) {
            addCriterion("isPayAndQrcode <", value, "isPayAndQrcode");
            return (Criteria) this;
        }

        public Criteria andIsPayAndQrcodeLessThanOrEqualTo(Boolean value) {
            addCriterion("isPayAndQrcode <=", value, "isPayAndQrcode");
            return (Criteria) this;
        }

        public Criteria andIsPayAndQrcodeIn(List<Boolean> values) {
            addCriterion("isPayAndQrcode in", values, "isPayAndQrcode");
            return (Criteria) this;
        }

        public Criteria andIsPayAndQrcodeNotIn(List<Boolean> values) {
            addCriterion("isPayAndQrcode not in", values, "isPayAndQrcode");
            return (Criteria) this;
        }

        public Criteria andIsPayAndQrcodeBetween(Boolean value1, Boolean value2) {
            addCriterion("isPayAndQrcode between", value1, value2, "isPayAndQrcode");
            return (Criteria) this;
        }

        public Criteria andIsPayAndQrcodeNotBetween(Boolean value1, Boolean value2) {
            addCriterion("isPayAndQrcode not between", value1, value2, "isPayAndQrcode");
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