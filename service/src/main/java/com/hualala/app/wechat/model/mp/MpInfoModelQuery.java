package com.hualala.app.wechat.model.mp;

import java.util.ArrayList;
import java.util.List;

public class MpInfoModelQuery {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer pageNo = 1;

    protected Integer startRow;

    protected Integer pageSize = 10;

    protected String fields;

    public MpInfoModelQuery() {
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

        public Criteria andItemIDIsNull() {
            addCriterion("itemID is null");
            return (Criteria) this;
        }

        public Criteria andItemIDIsNotNull() {
            addCriterion("itemID is not null");
            return (Criteria) this;
        }

        public Criteria andItemIDEqualTo(Long value) {
            addCriterion("itemID =", value, "itemID");
            return (Criteria) this;
        }

        public Criteria andItemIDNotEqualTo(Long value) {
            addCriterion("itemID <>", value, "itemID");
            return (Criteria) this;
        }

        public Criteria andItemIDGreaterThan(Long value) {
            addCriterion("itemID >", value, "itemID");
            return (Criteria) this;
        }

        public Criteria andItemIDGreaterThanOrEqualTo(Long value) {
            addCriterion("itemID >=", value, "itemID");
            return (Criteria) this;
        }

        public Criteria andItemIDLessThan(Long value) {
            addCriterion("itemID <", value, "itemID");
            return (Criteria) this;
        }

        public Criteria andItemIDLessThanOrEqualTo(Long value) {
            addCriterion("itemID <=", value, "itemID");
            return (Criteria) this;
        }

        public Criteria andItemIDIn(List<Long> values) {
            addCriterion("itemID in", values, "itemID");
            return (Criteria) this;
        }

        public Criteria andItemIDNotIn(List<Long> values) {
            addCriterion("itemID not in", values, "itemID");
            return (Criteria) this;
        }

        public Criteria andItemIDBetween(Long value1, Long value2) {
            addCriterion("itemID between", value1, value2, "itemID");
            return (Criteria) this;
        }

        public Criteria andItemIDNotBetween(Long value1, Long value2) {
            addCriterion("itemID not between", value1, value2, "itemID");
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

        public Criteria andGhIDIsNull() {
            addCriterion("ghID is null");
            return (Criteria) this;
        }

        public Criteria andGhIDIsNotNull() {
            addCriterion("ghID is not null");
            return (Criteria) this;
        }

        public Criteria andGhIDEqualTo(String value) {
            addCriterion("ghID =", value, "ghID");
            return (Criteria) this;
        }

        public Criteria andGhIDNotEqualTo(String value) {
            addCriterion("ghID <>", value, "ghID");
            return (Criteria) this;
        }

        public Criteria andGhIDGreaterThan(String value) {
            addCriterion("ghID >", value, "ghID");
            return (Criteria) this;
        }

        public Criteria andGhIDGreaterThanOrEqualTo(String value) {
            addCriterion("ghID >=", value, "ghID");
            return (Criteria) this;
        }

        public Criteria andGhIDLessThan(String value) {
            addCriterion("ghID <", value, "ghID");
            return (Criteria) this;
        }

        public Criteria andGhIDLessThanOrEqualTo(String value) {
            addCriterion("ghID <=", value, "ghID");
            return (Criteria) this;
        }

        public Criteria andGhIDLike(String value) {
            addCriterion("ghID like", value, "ghID");
            return (Criteria) this;
        }

        public Criteria andGhIDNotLike(String value) {
            addCriterion("ghID not like", value, "ghID");
            return (Criteria) this;
        }

        public Criteria andGhIDIn(List<String> values) {
            addCriterion("ghID in", values, "ghID");
            return (Criteria) this;
        }

        public Criteria andGhIDNotIn(List<String> values) {
            addCriterion("ghID not in", values, "ghID");
            return (Criteria) this;
        }

        public Criteria andGhIDBetween(String value1, String value2) {
            addCriterion("ghID between", value1, value2, "ghID");
            return (Criteria) this;
        }

        public Criteria andGhIDNotBetween(String value1, String value2) {
            addCriterion("ghID not between", value1, value2, "ghID");
            return (Criteria) this;
        }

        public Criteria andMpNameIsNull() {
            addCriterion("mpName is null");
            return (Criteria) this;
        }

        public Criteria andMpNameIsNotNull() {
            addCriterion("mpName is not null");
            return (Criteria) this;
        }

        public Criteria andMpNameEqualTo(String value) {
            addCriterion("mpName =", value, "mpName");
            return (Criteria) this;
        }

        public Criteria andMpNameNotEqualTo(String value) {
            addCriterion("mpName <>", value, "mpName");
            return (Criteria) this;
        }

        public Criteria andMpNameGreaterThan(String value) {
            addCriterion("mpName >", value, "mpName");
            return (Criteria) this;
        }

        public Criteria andMpNameGreaterThanOrEqualTo(String value) {
            addCriterion("mpName >=", value, "mpName");
            return (Criteria) this;
        }

        public Criteria andMpNameLessThan(String value) {
            addCriterion("mpName <", value, "mpName");
            return (Criteria) this;
        }

        public Criteria andMpNameLessThanOrEqualTo(String value) {
            addCriterion("mpName <=", value, "mpName");
            return (Criteria) this;
        }

        public Criteria andMpNameLike(String value) {
            addCriterion("mpName like", value, "mpName");
            return (Criteria) this;
        }

        public Criteria andMpNameNotLike(String value) {
            addCriterion("mpName not like", value, "mpName");
            return (Criteria) this;
        }

        public Criteria andMpNameIn(List<String> values) {
            addCriterion("mpName in", values, "mpName");
            return (Criteria) this;
        }

        public Criteria andMpNameNotIn(List<String> values) {
            addCriterion("mpName not in", values, "mpName");
            return (Criteria) this;
        }

        public Criteria andMpNameBetween(String value1, String value2) {
            addCriterion("mpName between", value1, value2, "mpName");
            return (Criteria) this;
        }

        public Criteria andMpNameNotBetween(String value1, String value2) {
            addCriterion("mpName not between", value1, value2, "mpName");
            return (Criteria) this;
        }

        public Criteria andMpTypeIsNull() {
            addCriterion("mpType is null");
            return (Criteria) this;
        }

        public Criteria andMpTypeIsNotNull() {
            addCriterion("mpType is not null");
            return (Criteria) this;
        }

        public Criteria andMpTypeEqualTo(Integer value) {
            addCriterion("mpType =", value, "mpType");
            return (Criteria) this;
        }

        public Criteria andMpTypeNotEqualTo(Integer value) {
            addCriterion("mpType <>", value, "mpType");
            return (Criteria) this;
        }

        public Criteria andMpTypeGreaterThan(Integer value) {
            addCriterion("mpType >", value, "mpType");
            return (Criteria) this;
        }

        public Criteria andMpTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("mpType >=", value, "mpType");
            return (Criteria) this;
        }

        public Criteria andMpTypeLessThan(Integer value) {
            addCriterion("mpType <", value, "mpType");
            return (Criteria) this;
        }

        public Criteria andMpTypeLessThanOrEqualTo(Integer value) {
            addCriterion("mpType <=", value, "mpType");
            return (Criteria) this;
        }

        public Criteria andMpTypeIn(List<Integer> values) {
            addCriterion("mpType in", values, "mpType");
            return (Criteria) this;
        }

        public Criteria andMpTypeNotIn(List<Integer> values) {
            addCriterion("mpType not in", values, "mpType");
            return (Criteria) this;
        }

        public Criteria andMpTypeBetween(Integer value1, Integer value2) {
            addCriterion("mpType between", value1, value2, "mpType");
            return (Criteria) this;
        }

        public Criteria andMpTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("mpType not between", value1, value2, "mpType");
            return (Criteria) this;
        }

        public Criteria andTokenIsNull() {
            addCriterion("token is null");
            return (Criteria) this;
        }

        public Criteria andTokenIsNotNull() {
            addCriterion("token is not null");
            return (Criteria) this;
        }

        public Criteria andTokenEqualTo(String value) {
            addCriterion("token =", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenNotEqualTo(String value) {
            addCriterion("token <>", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenGreaterThan(String value) {
            addCriterion("token >", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenGreaterThanOrEqualTo(String value) {
            addCriterion("token >=", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenLessThan(String value) {
            addCriterion("token <", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenLessThanOrEqualTo(String value) {
            addCriterion("token <=", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenLike(String value) {
            addCriterion("token like", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenNotLike(String value) {
            addCriterion("token not like", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenIn(List<String> values) {
            addCriterion("token in", values, "token");
            return (Criteria) this;
        }

        public Criteria andTokenNotIn(List<String> values) {
            addCriterion("token not in", values, "token");
            return (Criteria) this;
        }

        public Criteria andTokenBetween(String value1, String value2) {
            addCriterion("token between", value1, value2, "token");
            return (Criteria) this;
        }

        public Criteria andTokenNotBetween(String value1, String value2) {
            addCriterion("token not between", value1, value2, "token");
            return (Criteria) this;
        }

        public Criteria andAppIDIsNull() {
            addCriterion("appID is null");
            return (Criteria) this;
        }

        public Criteria andAppIDIsNotNull() {
            addCriterion("appID is not null");
            return (Criteria) this;
        }

        public Criteria andAppIDEqualTo(String value) {
            addCriterion("appID =", value, "appID");
            return (Criteria) this;
        }

        public Criteria andAppIDNotEqualTo(String value) {
            addCriterion("appID <>", value, "appID");
            return (Criteria) this;
        }

        public Criteria andAppIDGreaterThan(String value) {
            addCriterion("appID >", value, "appID");
            return (Criteria) this;
        }

        public Criteria andAppIDGreaterThanOrEqualTo(String value) {
            addCriterion("appID >=", value, "appID");
            return (Criteria) this;
        }

        public Criteria andAppIDLessThan(String value) {
            addCriterion("appID <", value, "appID");
            return (Criteria) this;
        }

        public Criteria andAppIDLessThanOrEqualTo(String value) {
            addCriterion("appID <=", value, "appID");
            return (Criteria) this;
        }

        public Criteria andAppIDLike(String value) {
            addCriterion("appID like", value, "appID");
            return (Criteria) this;
        }

        public Criteria andAppIDNotLike(String value) {
            addCriterion("appID not like", value, "appID");
            return (Criteria) this;
        }

        public Criteria andAppIDIn(List<String> values) {
            addCriterion("appID in", values, "appID");
            return (Criteria) this;
        }

        public Criteria andAppIDNotIn(List<String> values) {
            addCriterion("appID not in", values, "appID");
            return (Criteria) this;
        }

        public Criteria andAppIDBetween(String value1, String value2) {
            addCriterion("appID between", value1, value2, "appID");
            return (Criteria) this;
        }

        public Criteria andAppIDNotBetween(String value1, String value2) {
            addCriterion("appID not between", value1, value2, "appID");
            return (Criteria) this;
        }

        public Criteria andAppSecretIsNull() {
            addCriterion("appSecret is null");
            return (Criteria) this;
        }

        public Criteria andAppSecretIsNotNull() {
            addCriterion("appSecret is not null");
            return (Criteria) this;
        }

        public Criteria andAppSecretEqualTo(String value) {
            addCriterion("appSecret =", value, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretNotEqualTo(String value) {
            addCriterion("appSecret <>", value, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretGreaterThan(String value) {
            addCriterion("appSecret >", value, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretGreaterThanOrEqualTo(String value) {
            addCriterion("appSecret >=", value, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretLessThan(String value) {
            addCriterion("appSecret <", value, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretLessThanOrEqualTo(String value) {
            addCriterion("appSecret <=", value, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretLike(String value) {
            addCriterion("appSecret like", value, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretNotLike(String value) {
            addCriterion("appSecret not like", value, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretIn(List<String> values) {
            addCriterion("appSecret in", values, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretNotIn(List<String> values) {
            addCriterion("appSecret not in", values, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretBetween(String value1, String value2) {
            addCriterion("appSecret between", value1, value2, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretNotBetween(String value1, String value2) {
            addCriterion("appSecret not between", value1, value2, "appSecret");
            return (Criteria) this;
        }

        public Criteria andEncodingAESKeyIsNull() {
            addCriterion("encodingAESKey is null");
            return (Criteria) this;
        }

        public Criteria andEncodingAESKeyIsNotNull() {
            addCriterion("encodingAESKey is not null");
            return (Criteria) this;
        }

        public Criteria andEncodingAESKeyEqualTo(String value) {
            addCriterion("encodingAESKey =", value, "encodingAESKey");
            return (Criteria) this;
        }

        public Criteria andEncodingAESKeyNotEqualTo(String value) {
            addCriterion("encodingAESKey <>", value, "encodingAESKey");
            return (Criteria) this;
        }

        public Criteria andEncodingAESKeyGreaterThan(String value) {
            addCriterion("encodingAESKey >", value, "encodingAESKey");
            return (Criteria) this;
        }

        public Criteria andEncodingAESKeyGreaterThanOrEqualTo(String value) {
            addCriterion("encodingAESKey >=", value, "encodingAESKey");
            return (Criteria) this;
        }

        public Criteria andEncodingAESKeyLessThan(String value) {
            addCriterion("encodingAESKey <", value, "encodingAESKey");
            return (Criteria) this;
        }

        public Criteria andEncodingAESKeyLessThanOrEqualTo(String value) {
            addCriterion("encodingAESKey <=", value, "encodingAESKey");
            return (Criteria) this;
        }

        public Criteria andEncodingAESKeyLike(String value) {
            addCriterion("encodingAESKey like", value, "encodingAESKey");
            return (Criteria) this;
        }

        public Criteria andEncodingAESKeyNotLike(String value) {
            addCriterion("encodingAESKey not like", value, "encodingAESKey");
            return (Criteria) this;
        }

        public Criteria andEncodingAESKeyIn(List<String> values) {
            addCriterion("encodingAESKey in", values, "encodingAESKey");
            return (Criteria) this;
        }

        public Criteria andEncodingAESKeyNotIn(List<String> values) {
            addCriterion("encodingAESKey not in", values, "encodingAESKey");
            return (Criteria) this;
        }

        public Criteria andEncodingAESKeyBetween(String value1, String value2) {
            addCriterion("encodingAESKey between", value1, value2, "encodingAESKey");
            return (Criteria) this;
        }

        public Criteria andEncodingAESKeyNotBetween(String value1, String value2) {
            addCriterion("encodingAESKey not between", value1, value2, "encodingAESKey");
            return (Criteria) this;
        }

        public Criteria andWeixinURLIsNull() {
            addCriterion("weixinURL is null");
            return (Criteria) this;
        }

        public Criteria andWeixinURLIsNotNull() {
            addCriterion("weixinURL is not null");
            return (Criteria) this;
        }

        public Criteria andWeixinURLEqualTo(String value) {
            addCriterion("weixinURL =", value, "weixinURL");
            return (Criteria) this;
        }

        public Criteria andWeixinURLNotEqualTo(String value) {
            addCriterion("weixinURL <>", value, "weixinURL");
            return (Criteria) this;
        }

        public Criteria andWeixinURLGreaterThan(String value) {
            addCriterion("weixinURL >", value, "weixinURL");
            return (Criteria) this;
        }

        public Criteria andWeixinURLGreaterThanOrEqualTo(String value) {
            addCriterion("weixinURL >=", value, "weixinURL");
            return (Criteria) this;
        }

        public Criteria andWeixinURLLessThan(String value) {
            addCriterion("weixinURL <", value, "weixinURL");
            return (Criteria) this;
        }

        public Criteria andWeixinURLLessThanOrEqualTo(String value) {
            addCriterion("weixinURL <=", value, "weixinURL");
            return (Criteria) this;
        }

        public Criteria andWeixinURLLike(String value) {
            addCriterion("weixinURL like", value, "weixinURL");
            return (Criteria) this;
        }

        public Criteria andWeixinURLNotLike(String value) {
            addCriterion("weixinURL not like", value, "weixinURL");
            return (Criteria) this;
        }

        public Criteria andWeixinURLIn(List<String> values) {
            addCriterion("weixinURL in", values, "weixinURL");
            return (Criteria) this;
        }

        public Criteria andWeixinURLNotIn(List<String> values) {
            addCriterion("weixinURL not in", values, "weixinURL");
            return (Criteria) this;
        }

        public Criteria andWeixinURLBetween(String value1, String value2) {
            addCriterion("weixinURL between", value1, value2, "weixinURL");
            return (Criteria) this;
        }

        public Criteria andWeixinURLNotBetween(String value1, String value2) {
            addCriterion("weixinURL not between", value1, value2, "weixinURL");
            return (Criteria) this;
        }

        public Criteria andHeadImgIsNull() {
            addCriterion("headImg is null");
            return (Criteria) this;
        }

        public Criteria andHeadImgIsNotNull() {
            addCriterion("headImg is not null");
            return (Criteria) this;
        }

        public Criteria andHeadImgEqualTo(String value) {
            addCriterion("headImg =", value, "headImg");
            return (Criteria) this;
        }

        public Criteria andHeadImgNotEqualTo(String value) {
            addCriterion("headImg <>", value, "headImg");
            return (Criteria) this;
        }

        public Criteria andHeadImgGreaterThan(String value) {
            addCriterion("headImg >", value, "headImg");
            return (Criteria) this;
        }

        public Criteria andHeadImgGreaterThanOrEqualTo(String value) {
            addCriterion("headImg >=", value, "headImg");
            return (Criteria) this;
        }

        public Criteria andHeadImgLessThan(String value) {
            addCriterion("headImg <", value, "headImg");
            return (Criteria) this;
        }

        public Criteria andHeadImgLessThanOrEqualTo(String value) {
            addCriterion("headImg <=", value, "headImg");
            return (Criteria) this;
        }

        public Criteria andHeadImgLike(String value) {
            addCriterion("headImg like", value, "headImg");
            return (Criteria) this;
        }

        public Criteria andHeadImgNotLike(String value) {
            addCriterion("headImg not like", value, "headImg");
            return (Criteria) this;
        }

        public Criteria andHeadImgIn(List<String> values) {
            addCriterion("headImg in", values, "headImg");
            return (Criteria) this;
        }

        public Criteria andHeadImgNotIn(List<String> values) {
            addCriterion("headImg not in", values, "headImg");
            return (Criteria) this;
        }

        public Criteria andHeadImgBetween(String value1, String value2) {
            addCriterion("headImg between", value1, value2, "headImg");
            return (Criteria) this;
        }

        public Criteria andHeadImgNotBetween(String value1, String value2) {
            addCriterion("headImg not between", value1, value2, "headImg");
            return (Criteria) this;
        }

        public Criteria andQrCodeURLIsNull() {
            addCriterion("qrCodeURL is null");
            return (Criteria) this;
        }

        public Criteria andQrCodeURLIsNotNull() {
            addCriterion("qrCodeURL is not null");
            return (Criteria) this;
        }

        public Criteria andQrCodeURLEqualTo(String value) {
            addCriterion("qrCodeURL =", value, "qrCodeURL");
            return (Criteria) this;
        }

        public Criteria andQrCodeURLNotEqualTo(String value) {
            addCriterion("qrCodeURL <>", value, "qrCodeURL");
            return (Criteria) this;
        }

        public Criteria andQrCodeURLGreaterThan(String value) {
            addCriterion("qrCodeURL >", value, "qrCodeURL");
            return (Criteria) this;
        }

        public Criteria andQrCodeURLGreaterThanOrEqualTo(String value) {
            addCriterion("qrCodeURL >=", value, "qrCodeURL");
            return (Criteria) this;
        }

        public Criteria andQrCodeURLLessThan(String value) {
            addCriterion("qrCodeURL <", value, "qrCodeURL");
            return (Criteria) this;
        }

        public Criteria andQrCodeURLLessThanOrEqualTo(String value) {
            addCriterion("qrCodeURL <=", value, "qrCodeURL");
            return (Criteria) this;
        }

        public Criteria andQrCodeURLLike(String value) {
            addCriterion("qrCodeURL like", value, "qrCodeURL");
            return (Criteria) this;
        }

        public Criteria andQrCodeURLNotLike(String value) {
            addCriterion("qrCodeURL not like", value, "qrCodeURL");
            return (Criteria) this;
        }

        public Criteria andQrCodeURLIn(List<String> values) {
            addCriterion("qrCodeURL in", values, "qrCodeURL");
            return (Criteria) this;
        }

        public Criteria andQrCodeURLNotIn(List<String> values) {
            addCriterion("qrCodeURL not in", values, "qrCodeURL");
            return (Criteria) this;
        }

        public Criteria andQrCodeURLBetween(String value1, String value2) {
            addCriterion("qrCodeURL between", value1, value2, "qrCodeURL");
            return (Criteria) this;
        }

        public Criteria andQrCodeURLNotBetween(String value1, String value2) {
            addCriterion("qrCodeURL not between", value1, value2, "qrCodeURL");
            return (Criteria) this;
        }

        public Criteria andMenuJsonIsNull() {
            addCriterion("menuJson is null");
            return (Criteria) this;
        }

        public Criteria andMenuJsonIsNotNull() {
            addCriterion("menuJson is not null");
            return (Criteria) this;
        }

        public Criteria andMenuJsonEqualTo(String value) {
            addCriterion("menuJson =", value, "menuJson");
            return (Criteria) this;
        }

        public Criteria andMenuJsonNotEqualTo(String value) {
            addCriterion("menuJson <>", value, "menuJson");
            return (Criteria) this;
        }

        public Criteria andMenuJsonGreaterThan(String value) {
            addCriterion("menuJson >", value, "menuJson");
            return (Criteria) this;
        }

        public Criteria andMenuJsonGreaterThanOrEqualTo(String value) {
            addCriterion("menuJson >=", value, "menuJson");
            return (Criteria) this;
        }

        public Criteria andMenuJsonLessThan(String value) {
            addCriterion("menuJson <", value, "menuJson");
            return (Criteria) this;
        }

        public Criteria andMenuJsonLessThanOrEqualTo(String value) {
            addCriterion("menuJson <=", value, "menuJson");
            return (Criteria) this;
        }

        public Criteria andMenuJsonLike(String value) {
            addCriterion("menuJson like", value, "menuJson");
            return (Criteria) this;
        }

        public Criteria andMenuJsonNotLike(String value) {
            addCriterion("menuJson not like", value, "menuJson");
            return (Criteria) this;
        }

        public Criteria andMenuJsonIn(List<String> values) {
            addCriterion("menuJson in", values, "menuJson");
            return (Criteria) this;
        }

        public Criteria andMenuJsonNotIn(List<String> values) {
            addCriterion("menuJson not in", values, "menuJson");
            return (Criteria) this;
        }

        public Criteria andMenuJsonBetween(String value1, String value2) {
            addCriterion("menuJson between", value1, value2, "menuJson");
            return (Criteria) this;
        }

        public Criteria andMenuJsonNotBetween(String value1, String value2) {
            addCriterion("menuJson not between", value1, value2, "menuJson");
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

        public Criteria andGroupIDEqualTo(Integer value) {
            addCriterion("groupID =", value, "groupID");
            return (Criteria) this;
        }

        public Criteria andGroupIDNotEqualTo(Integer value) {
            addCriterion("groupID <>", value, "groupID");
            return (Criteria) this;
        }

        public Criteria andGroupIDGreaterThan(Integer value) {
            addCriterion("groupID >", value, "groupID");
            return (Criteria) this;
        }

        public Criteria andGroupIDGreaterThanOrEqualTo(Integer value) {
            addCriterion("groupID >=", value, "groupID");
            return (Criteria) this;
        }

        public Criteria andGroupIDLessThan(Integer value) {
            addCriterion("groupID <", value, "groupID");
            return (Criteria) this;
        }

        public Criteria andGroupIDLessThanOrEqualTo(Integer value) {
            addCriterion("groupID <=", value, "groupID");
            return (Criteria) this;
        }

        public Criteria andGroupIDIn(List<Integer> values) {
            addCriterion("groupID in", values, "groupID");
            return (Criteria) this;
        }

        public Criteria andGroupIDNotIn(List<Integer> values) {
            addCriterion("groupID not in", values, "groupID");
            return (Criteria) this;
        }

        public Criteria andGroupIDBetween(Integer value1, Integer value2) {
            addCriterion("groupID between", value1, value2, "groupID");
            return (Criteria) this;
        }

        public Criteria andGroupIDNotBetween(Integer value1, Integer value2) {
            addCriterion("groupID not between", value1, value2, "groupID");
            return (Criteria) this;
        }

        public Criteria andBrandIDIsNull() {
            addCriterion("brandID is null");
            return (Criteria) this;
        }

        public Criteria andBrandIDIsNotNull() {
            addCriterion("brandID is not null");
            return (Criteria) this;
        }

        public Criteria andBrandIDEqualTo(Long value) {
            addCriterion("brandID =", value, "brandID");
            return (Criteria) this;
        }

        public Criteria andBrandIDNotEqualTo(Long value) {
            addCriterion("brandID <>", value, "brandID");
            return (Criteria) this;
        }

        public Criteria andBrandIDGreaterThan(Long value) {
            addCriterion("brandID >", value, "brandID");
            return (Criteria) this;
        }

        public Criteria andBrandIDGreaterThanOrEqualTo(Long value) {
            addCriterion("brandID >=", value, "brandID");
            return (Criteria) this;
        }

        public Criteria andBrandIDLessThan(Long value) {
            addCriterion("brandID <", value, "brandID");
            return (Criteria) this;
        }

        public Criteria andBrandIDLessThanOrEqualTo(Long value) {
            addCriterion("brandID <=", value, "brandID");
            return (Criteria) this;
        }

        public Criteria andBrandIDIn(List<Long> values) {
            addCriterion("brandID in", values, "brandID");
            return (Criteria) this;
        }

        public Criteria andBrandIDNotIn(List<Long> values) {
            addCriterion("brandID not in", values, "brandID");
            return (Criteria) this;
        }

        public Criteria andBrandIDBetween(Long value1, Long value2) {
            addCriterion("brandID between", value1, value2, "brandID");
            return (Criteria) this;
        }

        public Criteria andBrandIDNotBetween(Long value1, Long value2) {
            addCriterion("brandID not between", value1, value2, "brandID");
            return (Criteria) this;
        }

        public Criteria andShopIDIsNull() {
            addCriterion("shopID is null");
            return (Criteria) this;
        }

        public Criteria andShopIDIsNotNull() {
            addCriterion("shopID is not null");
            return (Criteria) this;
        }

        public Criteria andShopIDEqualTo(Long value) {
            addCriterion("shopID =", value, "shopID");
            return (Criteria) this;
        }

        public Criteria andShopIDNotEqualTo(Long value) {
            addCriterion("shopID <>", value, "shopID");
            return (Criteria) this;
        }

        public Criteria andShopIDGreaterThan(Long value) {
            addCriterion("shopID >", value, "shopID");
            return (Criteria) this;
        }

        public Criteria andShopIDGreaterThanOrEqualTo(Long value) {
            addCriterion("shopID >=", value, "shopID");
            return (Criteria) this;
        }

        public Criteria andShopIDLessThan(Long value) {
            addCriterion("shopID <", value, "shopID");
            return (Criteria) this;
        }

        public Criteria andShopIDLessThanOrEqualTo(Long value) {
            addCriterion("shopID <=", value, "shopID");
            return (Criteria) this;
        }

        public Criteria andShopIDIn(List<Long> values) {
            addCriterion("shopID in", values, "shopID");
            return (Criteria) this;
        }

        public Criteria andShopIDNotIn(List<Long> values) {
            addCriterion("shopID not in", values, "shopID");
            return (Criteria) this;
        }

        public Criteria andShopIDBetween(Long value1, Long value2) {
            addCriterion("shopID between", value1, value2, "shopID");
            return (Criteria) this;
        }

        public Criteria andShopIDNotBetween(Long value1, Long value2) {
            addCriterion("shopID not between", value1, value2, "shopID");
            return (Criteria) this;
        }

        public Criteria andCustomerWithoutBindMobileIsNull() {
            addCriterion("customerWithoutBindMobile is null");
            return (Criteria) this;
        }

        public Criteria andCustomerWithoutBindMobileIsNotNull() {
            addCriterion("customerWithoutBindMobile is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerWithoutBindMobileEqualTo(Integer value) {
            addCriterion("customerWithoutBindMobile =", value, "customerWithoutBindMobile");
            return (Criteria) this;
        }

        public Criteria andCustomerWithoutBindMobileNotEqualTo(Integer value) {
            addCriterion("customerWithoutBindMobile <>", value, "customerWithoutBindMobile");
            return (Criteria) this;
        }

        public Criteria andCustomerWithoutBindMobileGreaterThan(Integer value) {
            addCriterion("customerWithoutBindMobile >", value, "customerWithoutBindMobile");
            return (Criteria) this;
        }

        public Criteria andCustomerWithoutBindMobileGreaterThanOrEqualTo(Integer value) {
            addCriterion("customerWithoutBindMobile >=", value, "customerWithoutBindMobile");
            return (Criteria) this;
        }

        public Criteria andCustomerWithoutBindMobileLessThan(Integer value) {
            addCriterion("customerWithoutBindMobile <", value, "customerWithoutBindMobile");
            return (Criteria) this;
        }

        public Criteria andCustomerWithoutBindMobileLessThanOrEqualTo(Integer value) {
            addCriterion("customerWithoutBindMobile <=", value, "customerWithoutBindMobile");
            return (Criteria) this;
        }

        public Criteria andCustomerWithoutBindMobileIn(List<Integer> values) {
            addCriterion("customerWithoutBindMobile in", values, "customerWithoutBindMobile");
            return (Criteria) this;
        }

        public Criteria andCustomerWithoutBindMobileNotIn(List<Integer> values) {
            addCriterion("customerWithoutBindMobile not in", values, "customerWithoutBindMobile");
            return (Criteria) this;
        }

        public Criteria andCustomerWithoutBindMobileBetween(Integer value1, Integer value2) {
            addCriterion("customerWithoutBindMobile between", value1, value2, "customerWithoutBindMobile");
            return (Criteria) this;
        }

        public Criteria andCustomerWithoutBindMobileNotBetween(Integer value1, Integer value2) {
            addCriterion("customerWithoutBindMobile not between", value1, value2, "customerWithoutBindMobile");
            return (Criteria) this;
        }

        public Criteria andSubscribeToCcustomerIsNull() {
            addCriterion("subscribeToCcustomer is null");
            return (Criteria) this;
        }

        public Criteria andSubscribeToCcustomerIsNotNull() {
            addCriterion("subscribeToCcustomer is not null");
            return (Criteria) this;
        }

        public Criteria andSubscribeToCcustomerEqualTo(Integer value) {
            addCriterion("subscribeToCcustomer =", value, "subscribeToCcustomer");
            return (Criteria) this;
        }

        public Criteria andSubscribeToCcustomerNotEqualTo(Integer value) {
            addCriterion("subscribeToCcustomer <>", value, "subscribeToCcustomer");
            return (Criteria) this;
        }

        public Criteria andSubscribeToCcustomerGreaterThan(Integer value) {
            addCriterion("subscribeToCcustomer >", value, "subscribeToCcustomer");
            return (Criteria) this;
        }

        public Criteria andSubscribeToCcustomerGreaterThanOrEqualTo(Integer value) {
            addCriterion("subscribeToCcustomer >=", value, "subscribeToCcustomer");
            return (Criteria) this;
        }

        public Criteria andSubscribeToCcustomerLessThan(Integer value) {
            addCriterion("subscribeToCcustomer <", value, "subscribeToCcustomer");
            return (Criteria) this;
        }

        public Criteria andSubscribeToCcustomerLessThanOrEqualTo(Integer value) {
            addCriterion("subscribeToCcustomer <=", value, "subscribeToCcustomer");
            return (Criteria) this;
        }

        public Criteria andSubscribeToCcustomerIn(List<Integer> values) {
            addCriterion("subscribeToCcustomer in", values, "subscribeToCcustomer");
            return (Criteria) this;
        }

        public Criteria andSubscribeToCcustomerNotIn(List<Integer> values) {
            addCriterion("subscribeToCcustomer not in", values, "subscribeToCcustomer");
            return (Criteria) this;
        }

        public Criteria andSubscribeToCcustomerBetween(Integer value1, Integer value2) {
            addCriterion("subscribeToCcustomer between", value1, value2, "subscribeToCcustomer");
            return (Criteria) this;
        }

        public Criteria andSubscribeToCcustomerNotBetween(Integer value1, Integer value2) {
            addCriterion("subscribeToCcustomer not between", value1, value2, "subscribeToCcustomer");
            return (Criteria) this;
        }

        public Criteria andTableMsgTemplateIsNull() {
            addCriterion("tableMsgTemplate is null");
            return (Criteria) this;
        }

        public Criteria andTableMsgTemplateIsNotNull() {
            addCriterion("tableMsgTemplate is not null");
            return (Criteria) this;
        }

        public Criteria andTableMsgTemplateEqualTo(String value) {
            addCriterion("tableMsgTemplate =", value, "tableMsgTemplate");
            return (Criteria) this;
        }

        public Criteria andTableMsgTemplateNotEqualTo(String value) {
            addCriterion("tableMsgTemplate <>", value, "tableMsgTemplate");
            return (Criteria) this;
        }

        public Criteria andTableMsgTemplateGreaterThan(String value) {
            addCriterion("tableMsgTemplate >", value, "tableMsgTemplate");
            return (Criteria) this;
        }

        public Criteria andTableMsgTemplateGreaterThanOrEqualTo(String value) {
            addCriterion("tableMsgTemplate >=", value, "tableMsgTemplate");
            return (Criteria) this;
        }

        public Criteria andTableMsgTemplateLessThan(String value) {
            addCriterion("tableMsgTemplate <", value, "tableMsgTemplate");
            return (Criteria) this;
        }

        public Criteria andTableMsgTemplateLessThanOrEqualTo(String value) {
            addCriterion("tableMsgTemplate <=", value, "tableMsgTemplate");
            return (Criteria) this;
        }

        public Criteria andTableMsgTemplateLike(String value) {
            addCriterion("tableMsgTemplate like", value, "tableMsgTemplate");
            return (Criteria) this;
        }

        public Criteria andTableMsgTemplateNotLike(String value) {
            addCriterion("tableMsgTemplate not like", value, "tableMsgTemplate");
            return (Criteria) this;
        }

        public Criteria andTableMsgTemplateIn(List<String> values) {
            addCriterion("tableMsgTemplate in", values, "tableMsgTemplate");
            return (Criteria) this;
        }

        public Criteria andTableMsgTemplateNotIn(List<String> values) {
            addCriterion("tableMsgTemplate not in", values, "tableMsgTemplate");
            return (Criteria) this;
        }

        public Criteria andTableMsgTemplateBetween(String value1, String value2) {
            addCriterion("tableMsgTemplate between", value1, value2, "tableMsgTemplate");
            return (Criteria) this;
        }

        public Criteria andTableMsgTemplateNotBetween(String value1, String value2) {
            addCriterion("tableMsgTemplate not between", value1, value2, "tableMsgTemplate");
            return (Criteria) this;
        }

        public Criteria andFuncInfoIsNull() {
            addCriterion("funcInfo is null");
            return (Criteria) this;
        }

        public Criteria andFuncInfoIsNotNull() {
            addCriterion("funcInfo is not null");
            return (Criteria) this;
        }

        public Criteria andFuncInfoEqualTo(String value) {
            addCriterion("funcInfo =", value, "funcInfo");
            return (Criteria) this;
        }

        public Criteria andFuncInfoNotEqualTo(String value) {
            addCriterion("funcInfo <>", value, "funcInfo");
            return (Criteria) this;
        }

        public Criteria andFuncInfoGreaterThan(String value) {
            addCriterion("funcInfo >", value, "funcInfo");
            return (Criteria) this;
        }

        public Criteria andFuncInfoGreaterThanOrEqualTo(String value) {
            addCriterion("funcInfo >=", value, "funcInfo");
            return (Criteria) this;
        }

        public Criteria andFuncInfoLessThan(String value) {
            addCriterion("funcInfo <", value, "funcInfo");
            return (Criteria) this;
        }

        public Criteria andFuncInfoLessThanOrEqualTo(String value) {
            addCriterion("funcInfo <=", value, "funcInfo");
            return (Criteria) this;
        }

        public Criteria andFuncInfoLike(String value) {
            addCriterion("funcInfo like", value, "funcInfo");
            return (Criteria) this;
        }

        public Criteria andFuncInfoNotLike(String value) {
            addCriterion("funcInfo not like", value, "funcInfo");
            return (Criteria) this;
        }

        public Criteria andFuncInfoIn(List<String> values) {
            addCriterion("funcInfo in", values, "funcInfo");
            return (Criteria) this;
        }

        public Criteria andFuncInfoNotIn(List<String> values) {
            addCriterion("funcInfo not in", values, "funcInfo");
            return (Criteria) this;
        }

        public Criteria andFuncInfoBetween(String value1, String value2) {
            addCriterion("funcInfo between", value1, value2, "funcInfo");
            return (Criteria) this;
        }

        public Criteria andFuncInfoNotBetween(String value1, String value2) {
            addCriterion("funcInfo not between", value1, value2, "funcInfo");
            return (Criteria) this;
        }

        public Criteria andAliasIsNull() {
            addCriterion("alias is null");
            return (Criteria) this;
        }

        public Criteria andAliasIsNotNull() {
            addCriterion("alias is not null");
            return (Criteria) this;
        }

        public Criteria andAliasEqualTo(String value) {
            addCriterion("alias =", value, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasNotEqualTo(String value) {
            addCriterion("alias <>", value, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasGreaterThan(String value) {
            addCriterion("alias >", value, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasGreaterThanOrEqualTo(String value) {
            addCriterion("alias >=", value, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasLessThan(String value) {
            addCriterion("alias <", value, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasLessThanOrEqualTo(String value) {
            addCriterion("alias <=", value, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasLike(String value) {
            addCriterion("alias like", value, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasNotLike(String value) {
            addCriterion("alias not like", value, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasIn(List<String> values) {
            addCriterion("alias in", values, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasNotIn(List<String> values) {
            addCriterion("alias not in", values, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasBetween(String value1, String value2) {
            addCriterion("alias between", value1, value2, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasNotBetween(String value1, String value2) {
            addCriterion("alias not between", value1, value2, "alias");
            return (Criteria) this;
        }

        public Criteria andAuthorizeIsNull() {
            addCriterion("authorize is null");
            return (Criteria) this;
        }

        public Criteria andAuthorizeIsNotNull() {
            addCriterion("authorize is not null");
            return (Criteria) this;
        }

        public Criteria andAuthorizeEqualTo(Integer value) {
            addCriterion("authorize =", value, "authorize");
            return (Criteria) this;
        }

        public Criteria andAuthorizeNotEqualTo(Integer value) {
            addCriterion("authorize <>", value, "authorize");
            return (Criteria) this;
        }

        public Criteria andAuthorizeGreaterThan(Integer value) {
            addCriterion("authorize >", value, "authorize");
            return (Criteria) this;
        }

        public Criteria andAuthorizeGreaterThanOrEqualTo(Integer value) {
            addCriterion("authorize >=", value, "authorize");
            return (Criteria) this;
        }

        public Criteria andAuthorizeLessThan(Integer value) {
            addCriterion("authorize <", value, "authorize");
            return (Criteria) this;
        }

        public Criteria andAuthorizeLessThanOrEqualTo(Integer value) {
            addCriterion("authorize <=", value, "authorize");
            return (Criteria) this;
        }

        public Criteria andAuthorizeIn(List<Integer> values) {
            addCriterion("authorize in", values, "authorize");
            return (Criteria) this;
        }

        public Criteria andAuthorizeNotIn(List<Integer> values) {
            addCriterion("authorize not in", values, "authorize");
            return (Criteria) this;
        }

        public Criteria andAuthorizeBetween(Integer value1, Integer value2) {
            addCriterion("authorize between", value1, value2, "authorize");
            return (Criteria) this;
        }

        public Criteria andAuthorizeNotBetween(Integer value1, Integer value2) {
            addCriterion("authorize not between", value1, value2, "authorize");
            return (Criteria) this;
        }

        public Criteria andAuthorizerRefreshTokenIsNull() {
            addCriterion("authorizerRefreshToken is null");
            return (Criteria) this;
        }

        public Criteria andAuthorizerRefreshTokenIsNotNull() {
            addCriterion("authorizerRefreshToken is not null");
            return (Criteria) this;
        }

        public Criteria andAuthorizerRefreshTokenEqualTo(String value) {
            addCriterion("authorizerRefreshToken =", value, "authorizerRefreshToken");
            return (Criteria) this;
        }

        public Criteria andAuthorizerRefreshTokenNotEqualTo(String value) {
            addCriterion("authorizerRefreshToken <>", value, "authorizerRefreshToken");
            return (Criteria) this;
        }

        public Criteria andAuthorizerRefreshTokenGreaterThan(String value) {
            addCriterion("authorizerRefreshToken >", value, "authorizerRefreshToken");
            return (Criteria) this;
        }

        public Criteria andAuthorizerRefreshTokenGreaterThanOrEqualTo(String value) {
            addCriterion("authorizerRefreshToken >=", value, "authorizerRefreshToken");
            return (Criteria) this;
        }

        public Criteria andAuthorizerRefreshTokenLessThan(String value) {
            addCriterion("authorizerRefreshToken <", value, "authorizerRefreshToken");
            return (Criteria) this;
        }

        public Criteria andAuthorizerRefreshTokenLessThanOrEqualTo(String value) {
            addCriterion("authorizerRefreshToken <=", value, "authorizerRefreshToken");
            return (Criteria) this;
        }

        public Criteria andAuthorizerRefreshTokenLike(String value) {
            addCriterion("authorizerRefreshToken like", value, "authorizerRefreshToken");
            return (Criteria) this;
        }

        public Criteria andAuthorizerRefreshTokenNotLike(String value) {
            addCriterion("authorizerRefreshToken not like", value, "authorizerRefreshToken");
            return (Criteria) this;
        }

        public Criteria andAuthorizerRefreshTokenIn(List<String> values) {
            addCriterion("authorizerRefreshToken in", values, "authorizerRefreshToken");
            return (Criteria) this;
        }

        public Criteria andAuthorizerRefreshTokenNotIn(List<String> values) {
            addCriterion("authorizerRefreshToken not in", values, "authorizerRefreshToken");
            return (Criteria) this;
        }

        public Criteria andAuthorizerRefreshTokenBetween(String value1, String value2) {
            addCriterion("authorizerRefreshToken between", value1, value2, "authorizerRefreshToken");
            return (Criteria) this;
        }

        public Criteria andAuthorizerRefreshTokenNotBetween(String value1, String value2) {
            addCriterion("authorizerRefreshToken not between", value1, value2, "authorizerRefreshToken");
            return (Criteria) this;
        }

        public Criteria andOauthIsNull() {
            addCriterion("oauth is null");
            return (Criteria) this;
        }

        public Criteria andOauthIsNotNull() {
            addCriterion("oauth is not null");
            return (Criteria) this;
        }

        public Criteria andOauthEqualTo(Integer value) {
            addCriterion("oauth =", value, "oauth");
            return (Criteria) this;
        }

        public Criteria andOauthNotEqualTo(Integer value) {
            addCriterion("oauth <>", value, "oauth");
            return (Criteria) this;
        }

        public Criteria andOauthGreaterThan(Integer value) {
            addCriterion("oauth >", value, "oauth");
            return (Criteria) this;
        }

        public Criteria andOauthGreaterThanOrEqualTo(Integer value) {
            addCriterion("oauth >=", value, "oauth");
            return (Criteria) this;
        }

        public Criteria andOauthLessThan(Integer value) {
            addCriterion("oauth <", value, "oauth");
            return (Criteria) this;
        }

        public Criteria andOauthLessThanOrEqualTo(Integer value) {
            addCriterion("oauth <=", value, "oauth");
            return (Criteria) this;
        }

        public Criteria andOauthIn(List<Integer> values) {
            addCriterion("oauth in", values, "oauth");
            return (Criteria) this;
        }

        public Criteria andOauthNotIn(List<Integer> values) {
            addCriterion("oauth not in", values, "oauth");
            return (Criteria) this;
        }

        public Criteria andOauthBetween(Integer value1, Integer value2) {
            addCriterion("oauth between", value1, value2, "oauth");
            return (Criteria) this;
        }

        public Criteria andOauthNotBetween(Integer value1, Integer value2) {
            addCriterion("oauth not between", value1, value2, "oauth");
            return (Criteria) this;
        }

        public Criteria andWechatEndDateIsNull() {
            addCriterion("wechatEndDate is null");
            return (Criteria) this;
        }

        public Criteria andWechatEndDateIsNotNull() {
            addCriterion("wechatEndDate is not null");
            return (Criteria) this;
        }

        public Criteria andWechatEndDateEqualTo(Long value) {
            addCriterion("wechatEndDate =", value, "wechatEndDate");
            return (Criteria) this;
        }

        public Criteria andWechatEndDateNotEqualTo(Long value) {
            addCriterion("wechatEndDate <>", value, "wechatEndDate");
            return (Criteria) this;
        }

        public Criteria andWechatEndDateGreaterThan(Long value) {
            addCriterion("wechatEndDate >", value, "wechatEndDate");
            return (Criteria) this;
        }

        public Criteria andWechatEndDateGreaterThanOrEqualTo(Long value) {
            addCriterion("wechatEndDate >=", value, "wechatEndDate");
            return (Criteria) this;
        }

        public Criteria andWechatEndDateLessThan(Long value) {
            addCriterion("wechatEndDate <", value, "wechatEndDate");
            return (Criteria) this;
        }

        public Criteria andWechatEndDateLessThanOrEqualTo(Long value) {
            addCriterion("wechatEndDate <=", value, "wechatEndDate");
            return (Criteria) this;
        }

        public Criteria andWechatEndDateIn(List<Long> values) {
            addCriterion("wechatEndDate in", values, "wechatEndDate");
            return (Criteria) this;
        }

        public Criteria andWechatEndDateNotIn(List<Long> values) {
            addCriterion("wechatEndDate not in", values, "wechatEndDate");
            return (Criteria) this;
        }

        public Criteria andWechatEndDateBetween(Long value1, Long value2) {
            addCriterion("wechatEndDate between", value1, value2, "wechatEndDate");
            return (Criteria) this;
        }

        public Criteria andWechatEndDateNotBetween(Long value1, Long value2) {
            addCriterion("wechatEndDate not between", value1, value2, "wechatEndDate");
            return (Criteria) this;
        }

        public Criteria andIsActiveUseIsNull() {
            addCriterion("isActiveUse is null");
            return (Criteria) this;
        }

        public Criteria andIsActiveUseIsNotNull() {
            addCriterion("isActiveUse is not null");
            return (Criteria) this;
        }

        public Criteria andIsActiveUseEqualTo(Integer value) {
            addCriterion("isActiveUse =", value, "isActiveUse");
            return (Criteria) this;
        }

        public Criteria andIsActiveUseNotEqualTo(Integer value) {
            addCriterion("isActiveUse <>", value, "isActiveUse");
            return (Criteria) this;
        }

        public Criteria andIsActiveUseGreaterThan(Integer value) {
            addCriterion("isActiveUse >", value, "isActiveUse");
            return (Criteria) this;
        }

        public Criteria andIsActiveUseGreaterThanOrEqualTo(Integer value) {
            addCriterion("isActiveUse >=", value, "isActiveUse");
            return (Criteria) this;
        }

        public Criteria andIsActiveUseLessThan(Integer value) {
            addCriterion("isActiveUse <", value, "isActiveUse");
            return (Criteria) this;
        }

        public Criteria andIsActiveUseLessThanOrEqualTo(Integer value) {
            addCriterion("isActiveUse <=", value, "isActiveUse");
            return (Criteria) this;
        }

        public Criteria andIsActiveUseIn(List<Integer> values) {
            addCriterion("isActiveUse in", values, "isActiveUse");
            return (Criteria) this;
        }

        public Criteria andIsActiveUseNotIn(List<Integer> values) {
            addCriterion("isActiveUse not in", values, "isActiveUse");
            return (Criteria) this;
        }

        public Criteria andIsActiveUseBetween(Integer value1, Integer value2) {
            addCriterion("isActiveUse between", value1, value2, "isActiveUse");
            return (Criteria) this;
        }

        public Criteria andIsActiveUseNotBetween(Integer value1, Integer value2) {
            addCriterion("isActiveUse not between", value1, value2, "isActiveUse");
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