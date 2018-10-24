package com.young.pact.api.domain.result.rest.rentcontinued;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.young.pact.api.domain.result.rest.commonpic.CommonPicResult;

/**
 * @描述 :  续签协议
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月10日 下午8:50:55
 */
public class RentContinuedResult implements Serializable{

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
    /** @Fields urlList:协议照片（集合）**/
    private List<CommonPicResult> urlList;
    /*** @Fields duardianDeptName:维护人部门名称  */
    private String duardianDeptName;
    /*** @Fields duardianUserName:维护人用户名称  */
    private String duardianUserName;
    /*** @Fields rentWay:租赁方式  */
    private String rentWay;
    /*** @Fields dealName:签约管家名称  */
    private String dealName;
    /*** @Fields dealDeptName:签约管家部门  */
    private String dealDeptName;
    /*** @Fields dealUserPin:签约管家部pin  */
    private String dealUserPin;
    /*** @Fields create:创建时间  */
    private Date create;
    public RentContinuedResult(){
        
    }
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
    public List<CommonPicResult> getUrlList() {
        return urlList;
    }
    public void setUrlList(List<CommonPicResult> urlList) {
        this.urlList = urlList;
    }
    public String getDealUserPin() {
        return dealUserPin;
    }
    public void setDealUserPin(String dealUserPin) {
        this.dealUserPin = dealUserPin;
    }
    public String getDuardianDeptName() {
        return duardianDeptName;
    }
    public void setDuardianDeptName(String duardianDeptName) {
        this.duardianDeptName = duardianDeptName;
    }
    public String getDuardianUserName() {
        return duardianUserName;
    }
    public void setDuardianUserName(String duardianUserName) {
        this.duardianUserName = duardianUserName;
    }
    public String getRentWay() {
        return rentWay;
    }
    public void setRentWay(String rentWay) {
        this.rentWay = rentWay;
    }
    public String getDealName() {
        return dealName;
    }
    public void setDealName(String dealName) {
        this.dealName = dealName;
    }
    public String getDealDeptName() {
        return dealDeptName;
    }
    public void setDealDeptName(String dealDeptName) {
        this.dealDeptName = dealDeptName;
    }
    public Date getCreate() {
        return create;
    }
    public void setCreate(Date create) {
        this.create = create;
    }
}
