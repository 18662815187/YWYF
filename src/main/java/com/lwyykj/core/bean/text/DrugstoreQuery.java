package com.lwyykj.core.bean.text;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DrugstoreQuery {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer pageNo = 1;

    protected Integer startRow;

    protected Integer pageSize = 10;

    protected String fields;

    public DrugstoreQuery() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNull() {
            addCriterion("phone is null");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNotNull() {
            addCriterion("phone is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneEqualTo(String value) {
            addCriterion("phone =", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotEqualTo(String value) {
            addCriterion("phone <>", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThan(String value) {
            addCriterion("phone >", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("phone >=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThan(String value) {
            addCriterion("phone <", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThanOrEqualTo(String value) {
            addCriterion("phone <=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLike(String value) {
            addCriterion("phone like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotLike(String value) {
            addCriterion("phone not like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneIn(List<String> values) {
            addCriterion("phone in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotIn(List<String> values) {
            addCriterion("phone not in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneBetween(String value1, String value2) {
            addCriterion("phone between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotBetween(String value1, String value2) {
            addCriterion("phone not between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPharmacyIsNull() {
            addCriterion("pharmacy is null");
            return (Criteria) this;
        }

        public Criteria andPharmacyIsNotNull() {
            addCriterion("pharmacy is not null");
            return (Criteria) this;
        }

        public Criteria andPharmacyEqualTo(String value) {
            addCriterion("pharmacy =", value, "pharmacy");
            return (Criteria) this;
        }

        public Criteria andPharmacyNotEqualTo(String value) {
            addCriterion("pharmacy <>", value, "pharmacy");
            return (Criteria) this;
        }

        public Criteria andPharmacyGreaterThan(String value) {
            addCriterion("pharmacy >", value, "pharmacy");
            return (Criteria) this;
        }

        public Criteria andPharmacyGreaterThanOrEqualTo(String value) {
            addCriterion("pharmacy >=", value, "pharmacy");
            return (Criteria) this;
        }

        public Criteria andPharmacyLessThan(String value) {
            addCriterion("pharmacy <", value, "pharmacy");
            return (Criteria) this;
        }

        public Criteria andPharmacyLessThanOrEqualTo(String value) {
            addCriterion("pharmacy <=", value, "pharmacy");
            return (Criteria) this;
        }

        public Criteria andPharmacyLike(String value) {
            addCriterion("pharmacy like", value, "pharmacy");
            return (Criteria) this;
        }

        public Criteria andPharmacyNotLike(String value) {
            addCriterion("pharmacy not like", value, "pharmacy");
            return (Criteria) this;
        }

        public Criteria andPharmacyIn(List<String> values) {
            addCriterion("pharmacy in", values, "pharmacy");
            return (Criteria) this;
        }

        public Criteria andPharmacyNotIn(List<String> values) {
            addCriterion("pharmacy not in", values, "pharmacy");
            return (Criteria) this;
        }

        public Criteria andPharmacyBetween(String value1, String value2) {
            addCriterion("pharmacy between", value1, value2, "pharmacy");
            return (Criteria) this;
        }

        public Criteria andPharmacyNotBetween(String value1, String value2) {
            addCriterion("pharmacy not between", value1, value2, "pharmacy");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNull() {
            addCriterion("password is null");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNotNull() {
            addCriterion("password is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordEqualTo(String value) {
            addCriterion("password =", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotEqualTo(String value) {
            addCriterion("password <>", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThan(String value) {
            addCriterion("password >", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("password >=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThan(String value) {
            addCriterion("password <", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThanOrEqualTo(String value) {
            addCriterion("password <=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLike(String value) {
            addCriterion("password like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotLike(String value) {
            addCriterion("password not like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordIn(List<String> values) {
            addCriterion("password in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotIn(List<String> values) {
            addCriterion("password not in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordBetween(String value1, String value2) {
            addCriterion("password between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotBetween(String value1, String value2) {
            addCriterion("password not between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andCdqcIsNull() {
            addCriterion("CDQC is null");
            return (Criteria) this;
        }

        public Criteria andCdqcIsNotNull() {
            addCriterion("CDQC is not null");
            return (Criteria) this;
        }

        public Criteria andCdqcEqualTo(String value) {
            addCriterion("CDQC =", value, "cdqc");
            return (Criteria) this;
        }

        public Criteria andCdqcNotEqualTo(String value) {
            addCriterion("CDQC <>", value, "cdqc");
            return (Criteria) this;
        }

        public Criteria andCdqcGreaterThan(String value) {
            addCriterion("CDQC >", value, "cdqc");
            return (Criteria) this;
        }

        public Criteria andCdqcGreaterThanOrEqualTo(String value) {
            addCriterion("CDQC >=", value, "cdqc");
            return (Criteria) this;
        }

        public Criteria andCdqcLessThan(String value) {
            addCriterion("CDQC <", value, "cdqc");
            return (Criteria) this;
        }

        public Criteria andCdqcLessThanOrEqualTo(String value) {
            addCriterion("CDQC <=", value, "cdqc");
            return (Criteria) this;
        }

        public Criteria andCdqcLike(String value) {
            addCriterion("CDQC like", value, "cdqc");
            return (Criteria) this;
        }

        public Criteria andCdqcNotLike(String value) {
            addCriterion("CDQC not like", value, "cdqc");
            return (Criteria) this;
        }

        public Criteria andCdqcIn(List<String> values) {
            addCriterion("CDQC in", values, "cdqc");
            return (Criteria) this;
        }

        public Criteria andCdqcNotIn(List<String> values) {
            addCriterion("CDQC not in", values, "cdqc");
            return (Criteria) this;
        }

        public Criteria andCdqcBetween(String value1, String value2) {
            addCriterion("CDQC between", value1, value2, "cdqc");
            return (Criteria) this;
        }

        public Criteria andCdqcNotBetween(String value1, String value2) {
            addCriterion("CDQC not between", value1, value2, "cdqc");
            return (Criteria) this;
        }

        public Criteria andBusinesslicenseIsNull() {
            addCriterion("businessLicense is null");
            return (Criteria) this;
        }

        public Criteria andBusinesslicenseIsNotNull() {
            addCriterion("businessLicense is not null");
            return (Criteria) this;
        }

        public Criteria andBusinesslicenseEqualTo(String value) {
            addCriterion("businessLicense =", value, "businesslicense");
            return (Criteria) this;
        }

        public Criteria andBusinesslicenseNotEqualTo(String value) {
            addCriterion("businessLicense <>", value, "businesslicense");
            return (Criteria) this;
        }

        public Criteria andBusinesslicenseGreaterThan(String value) {
            addCriterion("businessLicense >", value, "businesslicense");
            return (Criteria) this;
        }

        public Criteria andBusinesslicenseGreaterThanOrEqualTo(String value) {
            addCriterion("businessLicense >=", value, "businesslicense");
            return (Criteria) this;
        }

        public Criteria andBusinesslicenseLessThan(String value) {
            addCriterion("businessLicense <", value, "businesslicense");
            return (Criteria) this;
        }

        public Criteria andBusinesslicenseLessThanOrEqualTo(String value) {
            addCriterion("businessLicense <=", value, "businesslicense");
            return (Criteria) this;
        }

        public Criteria andBusinesslicenseLike(String value) {
            addCriterion("businessLicense like", value, "businesslicense");
            return (Criteria) this;
        }

        public Criteria andBusinesslicenseNotLike(String value) {
            addCriterion("businessLicense not like", value, "businesslicense");
            return (Criteria) this;
        }

        public Criteria andBusinesslicenseIn(List<String> values) {
            addCriterion("businessLicense in", values, "businesslicense");
            return (Criteria) this;
        }

        public Criteria andBusinesslicenseNotIn(List<String> values) {
            addCriterion("businessLicense not in", values, "businesslicense");
            return (Criteria) this;
        }

        public Criteria andBusinesslicenseBetween(String value1, String value2) {
            addCriterion("businessLicense between", value1, value2, "businesslicense");
            return (Criteria) this;
        }

        public Criteria andBusinesslicenseNotBetween(String value1, String value2) {
            addCriterion("businessLicense not between", value1, value2, "businesslicense");
            return (Criteria) this;
        }

        public Criteria andGspIsNull() {
            addCriterion("GSP is null");
            return (Criteria) this;
        }

        public Criteria andGspIsNotNull() {
            addCriterion("GSP is not null");
            return (Criteria) this;
        }

        public Criteria andGspEqualTo(String value) {
            addCriterion("GSP =", value, "gsp");
            return (Criteria) this;
        }

        public Criteria andGspNotEqualTo(String value) {
            addCriterion("GSP <>", value, "gsp");
            return (Criteria) this;
        }

        public Criteria andGspGreaterThan(String value) {
            addCriterion("GSP >", value, "gsp");
            return (Criteria) this;
        }

        public Criteria andGspGreaterThanOrEqualTo(String value) {
            addCriterion("GSP >=", value, "gsp");
            return (Criteria) this;
        }

        public Criteria andGspLessThan(String value) {
            addCriterion("GSP <", value, "gsp");
            return (Criteria) this;
        }

        public Criteria andGspLessThanOrEqualTo(String value) {
            addCriterion("GSP <=", value, "gsp");
            return (Criteria) this;
        }

        public Criteria andGspLike(String value) {
            addCriterion("GSP like", value, "gsp");
            return (Criteria) this;
        }

        public Criteria andGspNotLike(String value) {
            addCriterion("GSP not like", value, "gsp");
            return (Criteria) this;
        }

        public Criteria andGspIn(List<String> values) {
            addCriterion("GSP in", values, "gsp");
            return (Criteria) this;
        }

        public Criteria andGspNotIn(List<String> values) {
            addCriterion("GSP not in", values, "gsp");
            return (Criteria) this;
        }

        public Criteria andGspBetween(String value1, String value2) {
            addCriterion("GSP between", value1, value2, "gsp");
            return (Criteria) this;
        }

        public Criteria andGspNotBetween(String value1, String value2) {
            addCriterion("GSP not between", value1, value2, "gsp");
            return (Criteria) this;
        }

        public Criteria andHandheldphotoIsNull() {
            addCriterion("handheldphoto is null");
            return (Criteria) this;
        }

        public Criteria andHandheldphotoIsNotNull() {
            addCriterion("handheldphoto is not null");
            return (Criteria) this;
        }

        public Criteria andHandheldphotoEqualTo(String value) {
            addCriterion("handheldphoto =", value, "handheldphoto");
            return (Criteria) this;
        }

        public Criteria andHandheldphotoNotEqualTo(String value) {
            addCriterion("handheldphoto <>", value, "handheldphoto");
            return (Criteria) this;
        }

        public Criteria andHandheldphotoGreaterThan(String value) {
            addCriterion("handheldphoto >", value, "handheldphoto");
            return (Criteria) this;
        }

        public Criteria andHandheldphotoGreaterThanOrEqualTo(String value) {
            addCriterion("handheldphoto >=", value, "handheldphoto");
            return (Criteria) this;
        }

        public Criteria andHandheldphotoLessThan(String value) {
            addCriterion("handheldphoto <", value, "handheldphoto");
            return (Criteria) this;
        }

        public Criteria andHandheldphotoLessThanOrEqualTo(String value) {
            addCriterion("handheldphoto <=", value, "handheldphoto");
            return (Criteria) this;
        }

        public Criteria andHandheldphotoLike(String value) {
            addCriterion("handheldphoto like", value, "handheldphoto");
            return (Criteria) this;
        }

        public Criteria andHandheldphotoNotLike(String value) {
            addCriterion("handheldphoto not like", value, "handheldphoto");
            return (Criteria) this;
        }

        public Criteria andHandheldphotoIn(List<String> values) {
            addCriterion("handheldphoto in", values, "handheldphoto");
            return (Criteria) this;
        }

        public Criteria andHandheldphotoNotIn(List<String> values) {
            addCriterion("handheldphoto not in", values, "handheldphoto");
            return (Criteria) this;
        }

        public Criteria andHandheldphotoBetween(String value1, String value2) {
            addCriterion("handheldphoto between", value1, value2, "handheldphoto");
            return (Criteria) this;
        }

        public Criteria andHandheldphotoNotBetween(String value1, String value2) {
            addCriterion("handheldphoto not between", value1, value2, "handheldphoto");
            return (Criteria) this;
        }

        public Criteria andBusinesspermitIsNull() {
            addCriterion("businesspermit is null");
            return (Criteria) this;
        }

        public Criteria andBusinesspermitIsNotNull() {
            addCriterion("businesspermit is not null");
            return (Criteria) this;
        }

        public Criteria andBusinesspermitEqualTo(String value) {
            addCriterion("businesspermit =", value, "businesspermit");
            return (Criteria) this;
        }

        public Criteria andBusinesspermitNotEqualTo(String value) {
            addCriterion("businesspermit <>", value, "businesspermit");
            return (Criteria) this;
        }

        public Criteria andBusinesspermitGreaterThan(String value) {
            addCriterion("businesspermit >", value, "businesspermit");
            return (Criteria) this;
        }

        public Criteria andBusinesspermitGreaterThanOrEqualTo(String value) {
            addCriterion("businesspermit >=", value, "businesspermit");
            return (Criteria) this;
        }

        public Criteria andBusinesspermitLessThan(String value) {
            addCriterion("businesspermit <", value, "businesspermit");
            return (Criteria) this;
        }

        public Criteria andBusinesspermitLessThanOrEqualTo(String value) {
            addCriterion("businesspermit <=", value, "businesspermit");
            return (Criteria) this;
        }

        public Criteria andBusinesspermitLike(String value) {
            addCriterion("businesspermit like", value, "businesspermit");
            return (Criteria) this;
        }

        public Criteria andBusinesspermitNotLike(String value) {
            addCriterion("businesspermit not like", value, "businesspermit");
            return (Criteria) this;
        }

        public Criteria andBusinesspermitIn(List<String> values) {
            addCriterion("businesspermit in", values, "businesspermit");
            return (Criteria) this;
        }

        public Criteria andBusinesspermitNotIn(List<String> values) {
            addCriterion("businesspermit not in", values, "businesspermit");
            return (Criteria) this;
        }

        public Criteria andBusinesspermitBetween(String value1, String value2) {
            addCriterion("businesspermit between", value1, value2, "businesspermit");
            return (Criteria) this;
        }

        public Criteria andBusinesspermitNotBetween(String value1, String value2) {
            addCriterion("businesspermit not between", value1, value2, "businesspermit");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Boolean value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Boolean value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Boolean value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Boolean value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Boolean value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Boolean value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Boolean> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Boolean> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Boolean value1, Boolean value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Boolean value1, Boolean value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andAddtimeIsNull() {
            addCriterion("addtime is null");
            return (Criteria) this;
        }

        public Criteria andAddtimeIsNotNull() {
            addCriterion("addtime is not null");
            return (Criteria) this;
        }

        public Criteria andAddtimeEqualTo(Date value) {
            addCriterion("addtime =", value, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeNotEqualTo(Date value) {
            addCriterion("addtime <>", value, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeGreaterThan(Date value) {
            addCriterion("addtime >", value, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("addtime >=", value, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeLessThan(Date value) {
            addCriterion("addtime <", value, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeLessThanOrEqualTo(Date value) {
            addCriterion("addtime <=", value, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeIn(List<Date> values) {
            addCriterion("addtime in", values, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeNotIn(List<Date> values) {
            addCriterion("addtime not in", values, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeBetween(Date value1, Date value2) {
            addCriterion("addtime between", value1, value2, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeNotBetween(Date value1, Date value2) {
            addCriterion("addtime not between", value1, value2, "addtime");
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