package com.young.pact.domain.rentcontinued;

import java.util.Date;
import java.util.List;

import com.young.common.domain.BaseDomain;
import com.young.pact.domain.commonpic.CommonPicDO;

/**
 * @描述 : 续签协议
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月10日 下午8:42:09
 */
public class RentContinuedDO extends BaseDomain {

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
    /*** @Fields isDelete:删除状态(0:未删除 1:已删除)*/
    private Integer isDelete;
    /*** @Fields urlList:协议照片（集合）*/
    private List<CommonPicDO> urlList;
    /** @Fields dealUserPin:签约管家PIN**/
    private String dealUserPin;
    public RentContinuedDO(){}

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

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public List<CommonPicDO> getUrlList() {
        return urlList;
    }

    public void setUrlList(List<CommonPicDO> urlList) {
        this.urlList = urlList;
    }

    public String getDealUserPin() {
        return dealUserPin;
    }

    public void setDealUserPin(String dealUserPin) {
        this.dealUserPin = dealUserPin;
    }
}
