package com.hualala.app.wechat.model.card;

import java.util.ArrayList;
import java.util.List;

public class MemberMsgModelQuery {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer pageNo = 1;

    protected Integer startRow;

    protected Integer pageSize = 10;

    protected String fields;

    public MemberMsgModelQuery() {
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

        public Criteria andCodeIsNull() {
            addCriterion("code is null");
            return (Criteria) this;
        }

        public Criteria andCodeIsNotNull() {
            addCriterion("code is not null");
            return (Criteria) this;
        }

        public Criteria andCodeEqualTo(String value) {
            addCriterion("code =", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotEqualTo(String value) {
            addCriterion("code <>", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThan(String value) {
            addCriterion("code >", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThanOrEqualTo(String value) {
            addCriterion("code >=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThan(String value) {
            addCriterion("code <", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThanOrEqualTo(String value) {
            addCriterion("code <=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLike(String value) {
            addCriterion("code like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotLike(String value) {
            addCriterion("code not like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeIn(List<String> values) {
            addCriterion("code in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotIn(List<String> values) {
            addCriterion("code not in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeBetween(String value1, String value2) {
            addCriterion("code between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotBetween(String value1, String value2) {
            addCriterion("code not between", value1, value2, "code");
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

        public Criteria andMsgCreateTimeIsNull() {
            addCriterion("msgCreateTime is null");
            return (Criteria) this;
        }

        public Criteria andMsgCreateTimeIsNotNull() {
            addCriterion("msgCreateTime is not null");
            return (Criteria) this;
        }

        public Criteria andMsgCreateTimeEqualTo(Long value) {
            addCriterion("msgCreateTime =", value, "msgCreateTime");
            return (Criteria) this;
        }

        public Criteria andMsgCreateTimeNotEqualTo(Long value) {
            addCriterion("msgCreateTime <>", value, "msgCreateTime");
            return (Criteria) this;
        }

        public Criteria andMsgCreateTimeGreaterThan(Long value) {
            addCriterion("msgCreateTime >", value, "msgCreateTime");
            return (Criteria) this;
        }

        public Criteria andMsgCreateTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("msgCreateTime >=", value, "msgCreateTime");
            return (Criteria) this;
        }

        public Criteria andMsgCreateTimeLessThan(Long value) {
            addCriterion("msgCreateTime <", value, "msgCreateTime");
            return (Criteria) this;
        }

        public Criteria andMsgCreateTimeLessThanOrEqualTo(Long value) {
            addCriterion("msgCreateTime <=", value, "msgCreateTime");
            return (Criteria) this;
        }

        public Criteria andMsgCreateTimeIn(List<Long> values) {
            addCriterion("msgCreateTime in", values, "msgCreateTime");
            return (Criteria) this;
        }

        public Criteria andMsgCreateTimeNotIn(List<Long> values) {
            addCriterion("msgCreateTime not in", values, "msgCreateTime");
            return (Criteria) this;
        }

        public Criteria andMsgCreateTimeBetween(Long value1, Long value2) {
            addCriterion("msgCreateTime between", value1, value2, "msgCreateTime");
            return (Criteria) this;
        }

        public Criteria andMsgCreateTimeNotBetween(Long value1, Long value2) {
            addCriterion("msgCreateTime not between", value1, value2, "msgCreateTime");
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