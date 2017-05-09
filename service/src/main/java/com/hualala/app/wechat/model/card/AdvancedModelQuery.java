package com.hualala.app.wechat.model.card;

import java.util.ArrayList;
import java.util.List;

public class AdvancedModelQuery {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer pageNo = 1;

    protected Integer startRow;

    protected Integer pageSize = 10;

    protected String fields;

    public AdvancedModelQuery() {
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

        public Criteria andAbstractInfoIsNull() {
            addCriterion("abstractInfo is null");
            return (Criteria) this;
        }

        public Criteria andAbstractInfoIsNotNull() {
            addCriterion("abstractInfo is not null");
            return (Criteria) this;
        }

        public Criteria andAbstractInfoEqualTo(String value) {
            addCriterion("abstractInfo =", value, "abstractInfo");
            return (Criteria) this;
        }

        public Criteria andAbstractInfoNotEqualTo(String value) {
            addCriterion("abstractInfo <>", value, "abstractInfo");
            return (Criteria) this;
        }

        public Criteria andAbstractInfoGreaterThan(String value) {
            addCriterion("abstractInfo >", value, "abstractInfo");
            return (Criteria) this;
        }

        public Criteria andAbstractInfoGreaterThanOrEqualTo(String value) {
            addCriterion("abstractInfo >=", value, "abstractInfo");
            return (Criteria) this;
        }

        public Criteria andAbstractInfoLessThan(String value) {
            addCriterion("abstractInfo <", value, "abstractInfo");
            return (Criteria) this;
        }

        public Criteria andAbstractInfoLessThanOrEqualTo(String value) {
            addCriterion("abstractInfo <=", value, "abstractInfo");
            return (Criteria) this;
        }

        public Criteria andAbstractInfoLike(String value) {
            addCriterion("abstractInfo like", value, "abstractInfo");
            return (Criteria) this;
        }

        public Criteria andAbstractInfoNotLike(String value) {
            addCriterion("abstractInfo not like", value, "abstractInfo");
            return (Criteria) this;
        }

        public Criteria andAbstractInfoIn(List<String> values) {
            addCriterion("abstractInfo in", values, "abstractInfo");
            return (Criteria) this;
        }

        public Criteria andAbstractInfoNotIn(List<String> values) {
            addCriterion("abstractInfo not in", values, "abstractInfo");
            return (Criteria) this;
        }

        public Criteria andAbstractInfoBetween(String value1, String value2) {
            addCriterion("abstractInfo between", value1, value2, "abstractInfo");
            return (Criteria) this;
        }

        public Criteria andAbstractInfoNotBetween(String value1, String value2) {
            addCriterion("abstractInfo not between", value1, value2, "abstractInfo");
            return (Criteria) this;
        }

        public Criteria andBusinessServiceIsNull() {
            addCriterion("businessService is null");
            return (Criteria) this;
        }

        public Criteria andBusinessServiceIsNotNull() {
            addCriterion("businessService is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessServiceEqualTo(String value) {
            addCriterion("businessService =", value, "businessService");
            return (Criteria) this;
        }

        public Criteria andBusinessServiceNotEqualTo(String value) {
            addCriterion("businessService <>", value, "businessService");
            return (Criteria) this;
        }

        public Criteria andBusinessServiceGreaterThan(String value) {
            addCriterion("businessService >", value, "businessService");
            return (Criteria) this;
        }

        public Criteria andBusinessServiceGreaterThanOrEqualTo(String value) {
            addCriterion("businessService >=", value, "businessService");
            return (Criteria) this;
        }

        public Criteria andBusinessServiceLessThan(String value) {
            addCriterion("businessService <", value, "businessService");
            return (Criteria) this;
        }

        public Criteria andBusinessServiceLessThanOrEqualTo(String value) {
            addCriterion("businessService <=", value, "businessService");
            return (Criteria) this;
        }

        public Criteria andBusinessServiceLike(String value) {
            addCriterion("businessService like", value, "businessService");
            return (Criteria) this;
        }

        public Criteria andBusinessServiceNotLike(String value) {
            addCriterion("businessService not like", value, "businessService");
            return (Criteria) this;
        }

        public Criteria andBusinessServiceIn(List<String> values) {
            addCriterion("businessService in", values, "businessService");
            return (Criteria) this;
        }

        public Criteria andBusinessServiceNotIn(List<String> values) {
            addCriterion("businessService not in", values, "businessService");
            return (Criteria) this;
        }

        public Criteria andBusinessServiceBetween(String value1, String value2) {
            addCriterion("businessService between", value1, value2, "businessService");
            return (Criteria) this;
        }

        public Criteria andBusinessServiceNotBetween(String value1, String value2) {
            addCriterion("businessService not between", value1, value2, "businessService");
            return (Criteria) this;
        }

        public Criteria andTextImageIsNull() {
            addCriterion("textImage is null");
            return (Criteria) this;
        }

        public Criteria andTextImageIsNotNull() {
            addCriterion("textImage is not null");
            return (Criteria) this;
        }

        public Criteria andTextImageEqualTo(String value) {
            addCriterion("textImage =", value, "textImage");
            return (Criteria) this;
        }

        public Criteria andTextImageNotEqualTo(String value) {
            addCriterion("textImage <>", value, "textImage");
            return (Criteria) this;
        }

        public Criteria andTextImageGreaterThan(String value) {
            addCriterion("textImage >", value, "textImage");
            return (Criteria) this;
        }

        public Criteria andTextImageGreaterThanOrEqualTo(String value) {
            addCriterion("textImage >=", value, "textImage");
            return (Criteria) this;
        }

        public Criteria andTextImageLessThan(String value) {
            addCriterion("textImage <", value, "textImage");
            return (Criteria) this;
        }

        public Criteria andTextImageLessThanOrEqualTo(String value) {
            addCriterion("textImage <=", value, "textImage");
            return (Criteria) this;
        }

        public Criteria andTextImageLike(String value) {
            addCriterion("textImage like", value, "textImage");
            return (Criteria) this;
        }

        public Criteria andTextImageNotLike(String value) {
            addCriterion("textImage not like", value, "textImage");
            return (Criteria) this;
        }

        public Criteria andTextImageIn(List<String> values) {
            addCriterion("textImage in", values, "textImage");
            return (Criteria) this;
        }

        public Criteria andTextImageNotIn(List<String> values) {
            addCriterion("textImage not in", values, "textImage");
            return (Criteria) this;
        }

        public Criteria andTextImageBetween(String value1, String value2) {
            addCriterion("textImage between", value1, value2, "textImage");
            return (Criteria) this;
        }

        public Criteria andTextImageNotBetween(String value1, String value2) {
            addCriterion("textImage not between", value1, value2, "textImage");
            return (Criteria) this;
        }

        public Criteria andTimeLimitIsNull() {
            addCriterion("timeLimit is null");
            return (Criteria) this;
        }

        public Criteria andTimeLimitIsNotNull() {
            addCriterion("timeLimit is not null");
            return (Criteria) this;
        }

        public Criteria andTimeLimitEqualTo(String value) {
            addCriterion("timeLimit =", value, "timeLimit");
            return (Criteria) this;
        }

        public Criteria andTimeLimitNotEqualTo(String value) {
            addCriterion("timeLimit <>", value, "timeLimit");
            return (Criteria) this;
        }

        public Criteria andTimeLimitGreaterThan(String value) {
            addCriterion("timeLimit >", value, "timeLimit");
            return (Criteria) this;
        }

        public Criteria andTimeLimitGreaterThanOrEqualTo(String value) {
            addCriterion("timeLimit >=", value, "timeLimit");
            return (Criteria) this;
        }

        public Criteria andTimeLimitLessThan(String value) {
            addCriterion("timeLimit <", value, "timeLimit");
            return (Criteria) this;
        }

        public Criteria andTimeLimitLessThanOrEqualTo(String value) {
            addCriterion("timeLimit <=", value, "timeLimit");
            return (Criteria) this;
        }

        public Criteria andTimeLimitLike(String value) {
            addCriterion("timeLimit like", value, "timeLimit");
            return (Criteria) this;
        }

        public Criteria andTimeLimitNotLike(String value) {
            addCriterion("timeLimit not like", value, "timeLimit");
            return (Criteria) this;
        }

        public Criteria andTimeLimitIn(List<String> values) {
            addCriterion("timeLimit in", values, "timeLimit");
            return (Criteria) this;
        }

        public Criteria andTimeLimitNotIn(List<String> values) {
            addCriterion("timeLimit not in", values, "timeLimit");
            return (Criteria) this;
        }

        public Criteria andTimeLimitBetween(String value1, String value2) {
            addCriterion("timeLimit between", value1, value2, "timeLimit");
            return (Criteria) this;
        }

        public Criteria andTimeLimitNotBetween(String value1, String value2) {
            addCriterion("timeLimit not between", value1, value2, "timeLimit");
            return (Criteria) this;
        }

        public Criteria andUseCoditionIsNull() {
            addCriterion("useCodition is null");
            return (Criteria) this;
        }

        public Criteria andUseCoditionIsNotNull() {
            addCriterion("useCodition is not null");
            return (Criteria) this;
        }

        public Criteria andUseCoditionEqualTo(String value) {
            addCriterion("useCodition =", value, "useCodition");
            return (Criteria) this;
        }

        public Criteria andUseCoditionNotEqualTo(String value) {
            addCriterion("useCodition <>", value, "useCodition");
            return (Criteria) this;
        }

        public Criteria andUseCoditionGreaterThan(String value) {
            addCriterion("useCodition >", value, "useCodition");
            return (Criteria) this;
        }

        public Criteria andUseCoditionGreaterThanOrEqualTo(String value) {
            addCriterion("useCodition >=", value, "useCodition");
            return (Criteria) this;
        }

        public Criteria andUseCoditionLessThan(String value) {
            addCriterion("useCodition <", value, "useCodition");
            return (Criteria) this;
        }

        public Criteria andUseCoditionLessThanOrEqualTo(String value) {
            addCriterion("useCodition <=", value, "useCodition");
            return (Criteria) this;
        }

        public Criteria andUseCoditionLike(String value) {
            addCriterion("useCodition like", value, "useCodition");
            return (Criteria) this;
        }

        public Criteria andUseCoditionNotLike(String value) {
            addCriterion("useCodition not like", value, "useCodition");
            return (Criteria) this;
        }

        public Criteria andUseCoditionIn(List<String> values) {
            addCriterion("useCodition in", values, "useCodition");
            return (Criteria) this;
        }

        public Criteria andUseCoditionNotIn(List<String> values) {
            addCriterion("useCodition not in", values, "useCodition");
            return (Criteria) this;
        }

        public Criteria andUseCoditionBetween(String value1, String value2) {
            addCriterion("useCodition between", value1, value2, "useCodition");
            return (Criteria) this;
        }

        public Criteria andUseCoditionNotBetween(String value1, String value2) {
            addCriterion("useCodition not between", value1, value2, "useCodition");
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