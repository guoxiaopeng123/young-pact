package com.young.pact.domain.rentcontinued;

import java.util.Date;
import java.util.List;

import com.young.common.domain.BaseDomain;

/**
 * @描述 : 续签协议
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月10日 下午8:43:02
 */
public class RentContinuedQuery extends BaseDomain {

    /**
    * @Fields serialVersionUID : TODO( )
    */
    private static final long serialVersionUID = 1L;

    /*** @Fields id:主键id*/
    private Long id;
    /*** @Fields renewCode:续签协议编码*/
    private String renewCode;
    /*** @Fields address:物业地址*/
    private String address;
    /*** @Fields nativePlance:续签性质*/
    private String nativePlance;
    /*** @Fields pirmaryPactCode:原合同编码*/
    private String pirmaryPactCode;
    /*** @Fields newPactCode:新合同编号*/
    private String newPactCode;
    /*** @Fields renewReason:续签原因*/
    private String renewReason;
    /*** @Fields renewTime:其他约定*/
    private String remark;
    /*** @Fields renewTime:续签时间*/
    private Date renewTime;
    /*** @Fields signingTime:签约时间*/
    private Date signingTime;
    /*** @Fields state:合同状态(1:草稿状态 2:待审核 3:已通过 4:已驳回)*/
    private Integer state;
    /** @Fields startDeadline:原合同截止开始时间 **/
    private String startDeadline;
    /** @Fields endDeadline:原合同截止结束时间 **/
    private String endDeadline;
    /** @Fields startDealTime:签约开始时间**/
    private String startDealTime;
    /** @Fields endDealTime:签约结束时间**/
    private String endDealTime;
    /*** @Fields deptCode:部门*/
    private String deptCode;
    /*** @Fields keyword:关键字*/
    private String keyword;
    /** @Fields scope:数据作用域**/
    private Integer scope;
    /** @Fields userPin:登录人PIN**/
    private String userPin;
    /** @Fields 本人及下属集合:本人及下属集合**/
    private List<String> pinList;
    /** @Fields deptList:本部门及下属部门集合**/
    private List<String> deptList;
    public RentContinuedQuery(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRenewCode() {
        return renewCode;
    }

    public void setRenewCode(String renewCode) {
        this.renewCode = renewCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNativePlance() {
        return nativePlance;
    }

    public void setNativePlance(String nativePlance) {
        this.nativePlance = nativePlance;
    }

    public String getPirmaryPactCode() {
        return pirmaryPactCode;
    }

    public void setPirmaryPactCode(String pirmaryPactCode) {
        this.pirmaryPactCode = pirmaryPactCode;
    }

    public String getNewPactCode() {
        return newPactCode;
    }

    public void setNewPactCode(String newPactCode) {
        this.newPactCode = newPactCode;
    }

    public String getRenewReason() {
        return renewReason;
    }

    public void setRenewReason(String renewReason) {
        this.renewReason = renewReason;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getRenewTime() {
        return renewTime;
    }

    public void setRenewTime(Date renewTime) {
        this.renewTime = renewTime;
    }

    public Date getSigningTime() {
        return signingTime;
    }

    public void setSigningTime(Date signingTime) {
        this.signingTime = signingTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getStartDeadline() {
        return startDeadline;
    }

    public void setStartDeadline(String startDeadline) {
        this.startDeadline = startDeadline;
    }

    public String getEndDeadline() {
        return endDeadline;
    }

    public void setEndDeadline(String endDeadline) {
        this.endDeadline = endDeadline;
    }

    public String getStartDealTime() {
        return startDealTime;
    }

    public void setStartDealTime(String startDealTime) {
        this.startDealTime = startDealTime;
    }

    public String getEndDealTime() {
        return endDealTime;
    }

    public void setEndDealTime(String endDealTime) {
        this.endDealTime = endDealTime;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getScope() {
        return scope;
    }

    public void setScope(Integer scope) {
        this.scope = scope;
    }

    public String getUserPin() {
        return userPin;
    }

    public void setUserPin(String userPin) {
        this.userPin = userPin;
    }

    public List<String> getPinList() {
        return pinList;
    }

    public void setPinList(List<String> pinList) {
        this.pinList = pinList;
    }

    public List<String> getDeptList() {
        return deptList;
    }

    public void setDeptList(List<String> deptList) {
        this.deptList = deptList;
    }
}
