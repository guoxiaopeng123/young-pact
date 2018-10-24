package com.young.pact.api.domain.result.rest.pactrenttransfer;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.young.pact.api.domain.result.rest.commongoods.CommonGoodsResult;
import com.young.pact.api.domain.result.rest.commonmeterread.CommonMeterReadResult;
import com.young.pact.api.domain.result.rest.commonpic.CommonPicResult;
import com.young.pact.api.domain.result.rest.financereceiptpay.FinanceReceiptPayResult;
import com.young.pact.api.domain.result.rest.rentbase.RentPactResult;
/**
 * 
* @ClassName: PactRentTransferResult
* @Description: 调房协议结果类
* @author LiuYuChi
* @date 2018年8月8日 下午1:56:46
*
 */
public class PactRentTransferResult implements Serializable{

	private static final long serialVersionUID = 1L;
	/*** @Fields id:主键id*/
    private Long id;
    
    /*** @Fields transferCode:调房协议编码*/
    private String transferCode;
    
    /*** @Fields natives:调房性质*/
    private String natives;
    
    /*** @Fields address:物业地址*/
    private String address;
    
    /*** @Fields addressOld:调整前物业地址*/
    private String addressOld;
    
    /*** @Fields addressNew:调整后物业地址*/
    private String addressNew;
    
    /*** @Fields userName:管家姓名*/
    private String userName;
    
    /*** @Fields deptName:管家部门*/
    private String deptName;
    
    /*** @Fields userPin:管家pin*/
    private String userPin;
    
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
    
    /*** @Fields receiptPayList:收支列表*/
    private List<FinanceReceiptPayResult> receiptPayList;
    
    /*** @Fields goodsList:物品列表*/
    private List<CommonGoodsResult> goodsList;
    
    /*** @Fields meterReadList:抄表列表*/
    private List<CommonMeterReadResult> meterReadList;
    
    /*** @Fields picList:图片列表*/
    private List<CommonPicResult> picList;
    
    /*** @Fields oldPact:老合同信息*/
    private RentPactResult oldPact;
    
    /*** @Fields newPact:新合同信息*/
    private RentPactResult newPact;
    
    /*** @Fields gmtCreate:创建时间*/
    private Date gmtCreate;
    /*** @Fields dealName:签约管家姓名*/
    private String dealName;
    /*** @Fields dealPin:签约管家pin*/
    private String dealPin;
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

	public List<FinanceReceiptPayResult> getReceiptPayList() {
		return receiptPayList;
	}

	public void setReceiptPayList(List<FinanceReceiptPayResult> receiptPayList) {
		this.receiptPayList = receiptPayList;
	}

	public List<CommonGoodsResult> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<CommonGoodsResult> goodsList) {
		this.goodsList = goodsList;
	}

	public List<CommonMeterReadResult> getMeterReadList() {
		return meterReadList;
	}

	public void setMeterReadList(List<CommonMeterReadResult> meterReadList) {
		this.meterReadList = meterReadList;
	}

	public List<CommonPicResult> getPicList() {
		return picList;
	}

	public void setPicList(List<CommonPicResult> picList) {
		this.picList = picList;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPin() {
		return userPin;
	}

	public void setUserPin(String userPin) {
		this.userPin = userPin;
	}

	public RentPactResult getOldPact() {
		return oldPact;
	}

	public void setOldPact(RentPactResult oldPact) {
		this.oldPact = oldPact;
	}

	public RentPactResult getNewPact() {
		return newPact;
	}

	public void setNewPact(RentPactResult newPact) {
		this.newPact = newPact;
	}

	public String getNatives() {
		return natives;
	}

	public void setNatives(String natives) {
		this.natives = natives;
	}


	public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getAddressOld() {
		return addressOld;
	}

	public void setAddressOld(String addressOld) {
		this.addressOld = addressOld;
	}

	public String getAddressNew() {
		return addressNew;
	}

	public void setAddressNew(String addressNew) {
		this.addressNew = addressNew;
	}


    public String getDealName() {
        return dealName;
    }

    public void setDealName(String dealName) {
        this.dealName = dealName;
    }

    public String getDealPin() {
        return dealPin;
    }

    public void setDealPin(String dealPin) {
        this.dealPin = dealPin;
    }
	
}
