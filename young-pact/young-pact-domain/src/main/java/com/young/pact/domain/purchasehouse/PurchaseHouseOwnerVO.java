package com.young.pact.domain.purchasehouse;

import java.util.Date;

import com.young.common.domain.BaseDomain;
/**
 * 
* @ClassName: PurchaseHouseOwnerVO
* @Description: TODO( 托进合同房主信息)
* @author HeZeMin
* @date 2018年8月1日 上午11:43:38
*
 */
public class PurchaseHouseOwnerVO extends BaseDomain {
    private static final long serialVersionUID = 1L;
    /*** @Fields id:主键id*/
    private Long id;
    /*** @Fields purchaseCode:合同编号*/
    private String purchaseCode;
    /*** @Fields ownerCode:房主编号*/
    private String ownerCode;
    /*** @Fields name:姓名*/
    private String name;
    /*** @Fields sex:性别*/
    private String sex;
    /*** @Fields tel:手机号*/
    private String tel;
    /*** @Fields spareTel:备用手机号*/
    private String spareTel;
    /*** @Fields certificateType:证件类型*/
    private String certificateType;
    /*** @Fields certificateNumber:证件号*/
    private String certificateNumber;
    /*** @Fields birthday:出生日期*/
    private Date birthday;
    /*** @Fields email:邮箱*/
    private String email;
    /*** @Fields wechat:微信*/
    private String wechat;
    /*** @Fields address:通讯地址*/
    private String address;
    /*** @Fields certificatePic:证件(照片)*/
    private String certificatePic;
    /*** @Fields isAgent:是否有代理人0没有，1有*/
    private Integer isAgent;
    /*** @Fields payeeName:收款人姓名*/
    private String payeeName;
    /*** @Fields payeeAccount:收款账户*/
    private String payeeAccount;
    /*** @Fields payeeBank:收款银行*/
    private String payeeBank;
    /*** @Fields payeeOpeningBank:开户行*/
    private String payeeOpeningBank;
    /*** @Fields payeeTel:手机号*/
    private String payeeTel;
    /*** @Fields payeeProve:委托证明(文件)URL*/
    private String payeeProve;
    /*** @Fields agentName:代理人姓名*/
    private String agentName;
    /*** @Fields agentSex:性别*/
    private String agentSex;
    /*** @Fields agentTel:手机号*/
    private String agentTel;
    /*** @Fields agentSpareTel:备用手机号*/
    private String agentSpareTel;
    /*** @Fields agentCertificateType:证件类型*/
    private String agentCertificateType;
    /*** @Fields agentCertificateNumber:证件号*/
    private String agentCertificateNumber;
    /*** @Fields agentBirthday:出生日期*/
    private Date agentBirthday;
    /*** @Fields agentEmail:邮箱*/
    private String agentEmail;
    /*** @Fields agentWechat:微信*/
    private String agentWechat;
    /*** @Fields agentCertificatePic:证件(照片)*/
    private String agentCertificatePic;
    /*** @Fields agentAddress:通讯地址*/
    private String agentAddress;
    /*** @Fields agentProve:委托证明或视频*/
    private String agentProve;
    
    public PurchaseHouseOwnerVO() {
    }
    
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getPurchaseCode() {
        return purchaseCode;
    }
    public void setPurchaseCode(String purchaseCode) {
        this.purchaseCode = purchaseCode;
    }
    public String getOwnerCode() {
        return ownerCode;
    }
    public void setOwnerCode(String ownerCode) {
        this.ownerCode = ownerCode;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
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
    public String getCertificateType() {
        return certificateType;
    }
    public void setCertificateType(String certificateType) {
        this.certificateType = certificateType;
    }
    public String getCertificateNumber() {
        return certificateNumber;
    }
    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }
    public Date getBirthday() {
        return birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getWechat() {
        return wechat;
    }
    public void setWechat(String wechat) {
        this.wechat = wechat;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getCertificatePic() {
        return certificatePic;
    }
    public void setCertificatePic(String certificatePic) {
        this.certificatePic = certificatePic;
    }
    public Integer getIsAgent() {
        return isAgent;
    }
    public void setIsAgent(Integer isAgent) {
        this.isAgent = isAgent;
    }
    public String getPayeeName() {
        return payeeName;
    }
    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName;
    }
    public String getPayeeAccount() {
        return payeeAccount;
    }
    public void setPayeeAccount(String payeeAccount) {
        this.payeeAccount = payeeAccount;
    }
    public String getPayeeBank() {
        return payeeBank;
    }
    public void setPayeeBank(String payeeBank) {
        this.payeeBank = payeeBank;
    }
    public String getPayeeOpeningBank() {
        return payeeOpeningBank;
    }
    public void setPayeeOpeningBank(String payeeOpeningBank) {
        this.payeeOpeningBank = payeeOpeningBank;
    }
    public String getPayeeTel() {
        return payeeTel;
    }
    public void setPayeeTel(String payeeTel) {
        this.payeeTel = payeeTel;
    }
    public String getPayeeProve() {
        return payeeProve;
    }
    public void setPayeeProve(String payeeProve) {
        this.payeeProve = payeeProve;
    }
    public String getAgentName() {
        return agentName;
    }
    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }
    public String getAgentSex() {
        return agentSex;
    }
    public void setAgentSex(String agentSex) {
        this.agentSex = agentSex;
    }
    public String getAgentTel() {
        return agentTel;
    }
    public void setAgentTel(String agentTel) {
        this.agentTel = agentTel;
    }
    public String getAgentSpareTel() {
        return agentSpareTel;
    }
    public void setAgentSpareTel(String agentSpareTel) {
        this.agentSpareTel = agentSpareTel;
    }
    public String getAgentCertificateType() {
        return agentCertificateType;
    }
    public void setAgentCertificateType(String agentCertificateType) {
        this.agentCertificateType = agentCertificateType;
    }
    public String getAgentCertificateNumber() {
        return agentCertificateNumber;
    }
    public void setAgentCertificateNumber(String agentCertificateNumber) {
        this.agentCertificateNumber = agentCertificateNumber;
    }
    public Date getAgentBirthday() {
        return agentBirthday;
    }
    public void setAgentBirthday(Date agentBirthday) {
        this.agentBirthday = agentBirthday;
    }
    public String getAgentEmail() {
        return agentEmail;
    }
    public void setAgentEmail(String agentEmail) {
        this.agentEmail = agentEmail;
    }
    public String getAgentWechat() {
        return agentWechat;
    }
    public void setAgentWechat(String agentWechat) {
        this.agentWechat = agentWechat;
    }
    public String getAgentCertificatePic() {
        return agentCertificatePic;
    }
    public void setAgentCertificatePic(String agentCertificatePic) {
        this.agentCertificatePic = agentCertificatePic;
    }
    public String getAgentAddress() {
        return agentAddress;
    }
    public void setAgentAddress(String agentAddress) {
        this.agentAddress = agentAddress;
    }
    public String getAgentProve() {
        return agentProve;
    }
    public void setAgentProve(String agentProve) {
        this.agentProve = agentProve;
    }
}
