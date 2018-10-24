package com.young.pact.domain.rentcommonowner;

import java.util.Date;

import com.young.common.domain.BaseDomain;

/**
 * @描述 : 托出租客共同居住人
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月1日 上午11:58:58
 */
public class RentCommonOwnerVO extends BaseDomain{

    /**
    * @Fields serialVersionUID : TODO( )
    */
    private static final long serialVersionUID = 1L;
    public Long id;
    /*** @Fields renterCode:租客ID*/
    private String renterCode;
    /*** @Fields name:姓名*/
    private String name;
    /*** @Fields sex:性别(0:女 1:男)*/
    private Integer sex;
    /*** @Fields tel:手机号*/
    private String tel;
    /*** @Fields spareTel:备用手机号*/
    private String spareTel;
    /*** @Fields idType:证件类型*/
    private String idType;
    /*** @Fields idCode:证件号*/
    private String idCode;
    /*** @Fields birthday:出生日期*/
    private Date birthday;
    /*** @Fields address:联系地址*/
    private String address;
    /*** @Fields wechat:微信*/
    private String wechat;
    /*** @Fields nativePlance:籍贯*/
    private String nativePlance;
    /*** @Fields maritalStatus:婚姻状况*/
    private String maritalStatus;
    /*** @Fields label:租客标签json*/
    private String label;
    /*** @Fields monthlyIncome:月收入*/
    private String monthlyIncome;
    /*** @Fields cardPositiveUrl:证件照片正面*/
    private String cardPositiveUrl;
    /*** @Fields cardBackUrl:证件照片反面*/
    private String cardBackUrl;
    /*** @Fields profession:职业*/
    private String profession;
    /*** @Fields companyName:单位名称*/
    private String companyName;
    /*** @Fields companyAddress:单位地址*/
    private String companyAddress;
    
    
    public RentCommonOwnerVO(){
        
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getRenterCode() {
        return renterCode;
    }
    public void setRenterCode(String renterCode) {
        this.renterCode = renterCode;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getSex() {
        return sex;
    }
    public void setSex(Integer sex) {
        this.sex = sex;
    }
    public String getTel() {
        return tel;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }
    public String getSpareTel() {
        return spareTel;
    }
    public void setSpareTel(String spareTel) {
        this.spareTel = spareTel;
    }
    public String getIdType() {
        return idType;
    }
    public void setIdType(String idType) {
        this.idType = idType;
    }
    public String getIdCode() {
        return idCode;
    }
    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }
    public Date getBirthday() {
        return birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getWechat() {
        return wechat;
    }
    public void setWechat(String wechat) {
        this.wechat = wechat;
    }
    public String getNativePlance() {
        return nativePlance;
    }
    public void setNativePlance(String nativePlance) {
        this.nativePlance = nativePlance;
    }
    public String getMaritalStatus() {
        return maritalStatus;
    }
    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }
    public String getLabel() {
        return label;
    }
    public void setLabel(String label) {
        this.label = label;
    }
    public String getMonthlyIncome() {
        return monthlyIncome;
    }
    public void setMonthlyIncome(String monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }
    public String getCardPositiveUrl() {
        return cardPositiveUrl;
    }
    public void setCardPositiveUrl(String cardPositiveUrl) {
        this.cardPositiveUrl = cardPositiveUrl;
    }
    public String getCardBackUrl() {
        return cardBackUrl;
    }
    public void setCardBackUrl(String cardBackUrl) {
        this.cardBackUrl = cardBackUrl;
    }
    public String getProfession() {
        return profession;
    }
    public void setProfession(String profession) {
        this.profession = profession;
    }
    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public String getCompanyAddress() {
        return companyAddress;
    }
    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    } 
}
