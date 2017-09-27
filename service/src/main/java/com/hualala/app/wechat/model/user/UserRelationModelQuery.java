package com.hualala.app.wechat.model.user;

import java.util.ArrayList;
import java.util.List;

public class UserRelationModelQuery {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer pageNo = 1;

    protected Integer startRow;

    protected Integer pageSize = 10;

    protected String fields;

    public UserRelationModelQuery() {
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

        public Criteria andUserIDIsNull() {
            addCriterion("userID is null");
            return (Criteria) this;
        }

        public Criteria andUserIDIsNotNull() {
            addCriterion("userID is not null");
            return (Criteria) this;
        }

        public Criteria andUserIDEqualTo(Long value) {
            addCriterion("userID =", value, "userID");
            return (Criteria) this;
        }

        public Criteria andUserIDNotEqualTo(Long value) {
            addCriterion("userID <>", value, "userID");
            return (Criteria) this;
        }

        public Criteria andUserIDGreaterThan(Long value) {
            addCriterion("userID >", value, "userID");
            return (Criteria) this;
        }

        public Criteria andUserIDGreaterThanOrEqualTo(Long value) {
            addCriterion("userID >=", value, "userID");
            return (Criteria) this;
        }

        public Criteria andUserIDLessThan(Long value) {
            addCriterion("userID <", value, "userID");
            return (Criteria) this;
        }

        public Criteria andUserIDLessThanOrEqualTo(Long value) {
            addCriterion("userID <=", value, "userID");
            return (Criteria) this;
        }

        public Criteria andUserIDIn(List<Long> values) {
            addCriterion("userID in", values, "userID");
            return (Criteria) this;
        }

        public Criteria andUserIDNotIn(List<Long> values) {
            addCriterion("userID not in", values, "userID");
            return (Criteria) this;
        }

        public Criteria andUserIDBetween(Long value1, Long value2) {
            addCriterion("userID between", value1, value2, "userID");
            return (Criteria) this;
        }

        public Criteria andUserIDNotBetween(Long value1, Long value2) {
            addCriterion("userID not between", value1, value2, "userID");
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

        public Criteria andOpenidIsNull() {
            addCriterion("openid is null");
            return (Criteria) this;
        }

        public Criteria andOpenidIsNotNull() {
            addCriterion("openid is not null");
            return (Criteria) this;
        }

        public Criteria andOpenidEqualTo(String value) {
            addCriterion("openid =", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotEqualTo(String value) {
            addCriterion("openid <>", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidGreaterThan(String value) {
            addCriterion("openid >", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidGreaterThanOrEqualTo(String value) {
            addCriterion("openid >=", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidLessThan(String value) {
            addCriterion("openid <", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidLessThanOrEqualTo(String value) {
            addCriterion("openid <=", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidLike(String value) {
            addCriterion("openid like", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotLike(String value) {
            addCriterion("openid not like", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidIn(List<String> values) {
            addCriterion("openid in", values, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotIn(List<String> values) {
            addCriterion("openid not in", values, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidBetween(String value1, String value2) {
            addCriterion("openid between", value1, value2, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotBetween(String value1, String value2) {
            addCriterion("openid not between", value1, value2, "openid");
            return (Criteria) this;
        }

        public Criteria andSubscribeIsNull() {
            addCriterion("subscribe is null");
            return (Criteria) this;
        }

        public Criteria andSubscribeIsNotNull() {
            addCriterion("subscribe is not null");
            return (Criteria) this;
        }

        public Criteria andSubscribeEqualTo(Integer value) {
            addCriterion("subscribe =", value, "subscribe");
            return (Criteria) this;
        }

        public Criteria andSubscribeNotEqualTo(Integer value) {
            addCriterion("subscribe <>", value, "subscribe");
            return (Criteria) this;
        }

        public Criteria andSubscribeGreaterThan(Integer value) {
            addCriterion("subscribe >", value, "subscribe");
            return (Criteria) this;
        }

        public Criteria andSubscribeGreaterThanOrEqualTo(Integer value) {
            addCriterion("subscribe >=", value, "subscribe");
            return (Criteria) this;
        }

        public Criteria andSubscribeLessThan(Integer value) {
            addCriterion("subscribe <", value, "subscribe");
            return (Criteria) this;
        }

        public Criteria andSubscribeLessThanOrEqualTo(Integer value) {
            addCriterion("subscribe <=", value, "subscribe");
            return (Criteria) this;
        }

        public Criteria andSubscribeIn(List<Integer> values) {
            addCriterion("subscribe in", values, "subscribe");
            return (Criteria) this;
        }

        public Criteria andSubscribeNotIn(List<Integer> values) {
            addCriterion("subscribe not in", values, "subscribe");
            return (Criteria) this;
        }

        public Criteria andSubscribeBetween(Integer value1, Integer value2) {
            addCriterion("subscribe between", value1, value2, "subscribe");
            return (Criteria) this;
        }

        public Criteria andSubscribeNotBetween(Integer value1, Integer value2) {
            addCriterion("subscribe not between", value1, value2, "subscribe");
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