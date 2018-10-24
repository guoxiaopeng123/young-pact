package com.young.pact.domain.pactrenttransfer;

import java.util.Date;

import com.young.common.domain.BaseDomain;
/**
 * 
* @ClassName: PactRentTransferDO
* @Description: 调房协议实体类
* @author LiuYuChi
* @date 2018年8月8日 上午11:52:40
*
 */
public class PactRentTransferDO extends BaseDomain{
	
	private static final long serialVersionUID = 1L;
	
	/*** @Fields id:主键id*/
    private Long id;
    
    /*** @Fields transferCode:调房协议编码*/
    private String transferCode;
    
    /*** @Fields address:物业地址*/
    private String address;
    
    /*** @Fields natives:调房性质*/
    private String natives;
    
    /*** @Fields pirmaryPactCode:原合同编码*/
    private String pirmaryPactCode;
    
    /*** @Fields newPactCode:新合同编码*/
    private String newPactCode;
    
    /*** @Fields name:收款人姓名*/
    private String name;
    
    /*** @Fields accountNum:收款账号*/
    private String accountNum;
    
    /*** @Fields caseBank:收款银行*/
    private String caseBank;
    
    /*** @Fields openBank:开户行*/
    private String openBank;
    
    /*** @Fields tel:电话号*/
    private String tel;
    
    /*** @Fields transferReason:调房原因*/
    private String transferReason;
    
    /*** @Fields remark:其他约定*/
    private String remark;
    
    /*** @Fields transferTime:调房时间*/
    private Date transferTime;
    
    /*** @Fields signingTime:签约时间*/
    private Date signingTime;
    
    /*** @Fields state:合同状态*/
    private Integer state;
    
    /*** @Fields isDelete:删除状态*/
    private Integer isDelete;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTransferCode() {
		return transferCode;
	}

	public void setTransferCode(String transferCode) {
		this.transferCode = transferCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccountNum() {
		return accountNum;
	}

	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}

	public String getCaseBank() {
		return caseBank;
	}

	public void setCaseBank(String caseBank) {
		this.caseBank = caseBank;
	}

	public String getOpenBank() {
		return openBank;
	}

	public void setOpenBank(String openBank) {
		this.openBank = openBank;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getTransferReason() {
		return transferReason;
	}

	public void setTransferReason(String transferReason) {
		this.transferReason = transferReason;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getTransferTime() {
		return transferTime;
	}

	public void setTransferTime(Date transferTime) {
		this.transferTime = transferTime;
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

	public String getNatives() {
		return natives;
	}

	public void setNatives(String natives) {
		this.natives = natives;
	}
	
}
