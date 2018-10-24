package com.young.pact.api.domain.param.rest.pactrenttransfer;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.young.pact.api.domain.param.rest.commongoods.CommonGoodsParam;
import com.young.pact.api.domain.param.rest.commonmeterread.CommonMeterReadParam;
import com.young.pact.api.domain.param.rest.commonpic.CommonPicParam;
import com.young.pact.api.domain.param.rest.financereceiptpay.FinanceReceiptPayParam;
/**
 * 
* @ClassName: PactRentTransferParam
* @Description: 调房协议参数类
* @author LiuYuChi
* @date 2018年8月8日 下午1:55:32
*
 */
public class PactRentTransferParam implements Serializable{

	private static final long serialVersionUID = 1L;

	/*** @Fields id:主键id*/
    private Long id;
    
    /*** @Fields transferCode:调房协议编码*/
    private String transferCode;
    
    /*** @Fields userName:签约管家姓名*/
    private String userName;
    
    /*** @Fields userPin:签约管家pin*/
    private String userPin;
    
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
    
    /*** @Fields transferTimeStart:合同截止开始时间*/
    private String transferTimeStart;
    
    /*** @Fields transferTimeEnd:合同截止结束时间*/
    private String transferTimeEnd;
    
    /*** @Fields signingTimeStart:签约开始时间*/
    private String signingTimeStart;
    
    /*** @Fields signingTimeEnd:签约结束时间*/
    private String signingTimeEnd;
    
    /*** @Fields signingTime:签约时间*/
    private Date signingTime;
    
    /*** @Fields state:合同状态*/
    private Integer state;
    
    /*** @Fields isDelete:删除状态*/
    private Integer isDelete;
    
    /*** @Fields createName:创建人*/
    private String createName;
    
    /*** @Fields create:创建时间*/
    private Date create;
    
    /*** @Fields modifiedName:修改人*/
    private String modifiedName;
    
    /*** @Fields ip:ip地址*/
    private String ip;
    
    /*** @Fields deptCode:部门*/
    private String deptCode;
    
    /*** @Fields keyword:关键字*/
    private String keyword;
    
    /** @Fields pageIndex:页数**/
    private int pageIndex;
    
    /** @Fields pageSize:每页显示个数**/
    private int pageSize;
    
    /*** @Fields receiptPayList:收支列表*/
    private List<FinanceReceiptPayParam> receiptPayList;
    
    /*** @Fields goodsList:物品列表*/
    private List<CommonGoodsParam> goodsList;
    
    /*** @Fields meterReadList:抄表列表*/
    private List<CommonMeterReadParam> meterReadList;
    
    /*** @Fields picList:图片列表*/
    private List<CommonPicParam> picList;
    /*** @Fields scope:数据权限作用域*/
    private Integer scope;
    
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

	public List<FinanceReceiptPayParam> getReceiptPayList() {
		return receiptPayList;
	}

	public void setReceiptPayList(List<FinanceReceiptPayParam> receiptPayList) {
		this.receiptPayList = receiptPayList;
	}

	public List<CommonGoodsParam> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<CommonGoodsParam> goodsList) {
		this.goodsList = goodsList;
	}

	public List<CommonMeterReadParam> getMeterReadList() {
		return meterReadList;
	}

	public void setMeterReadList(List<CommonMeterReadParam> meterReadList) {
		this.meterReadList = meterReadList;
	}

	public List<CommonPicParam> getPicList() {
		return picList;
	}

	public void setPicList(List<CommonPicParam> picList) {
		this.picList = picList;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public Date getCreate() {
		return create;
	}

	public void setCreate(Date create) {
		this.create = create;
	}

	public String getModifiedName() {
		return modifiedName;
	}

	public void setModifiedName(String modifiedName) {
		this.modifiedName = modifiedName;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getNatives() {
		return natives;
	}

	public void setNatives(String natives) {
		this.natives = natives;
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

	public String getTransferTimeStart() {
		return transferTimeStart;
	}

	public void setTransferTimeStart(String transferTimeStart) {
		this.transferTimeStart = transferTimeStart;
	}

	public String getTransferTimeEnd() {
		return transferTimeEnd;
	}

	public void setTransferTimeEnd(String transferTimeEnd) {
		this.transferTimeEnd = transferTimeEnd;
	}

	public String getSigningTimeStart() {
		return signingTimeStart;
	}

	public void setSigningTimeStart(String signingTimeStart) {
		this.signingTimeStart = signingTimeStart;
	}

	public String getSigningTimeEnd() {
		return signingTimeEnd;
	}

	public void setSigningTimeEnd(String signingTimeEnd) {
		this.signingTimeEnd = signingTimeEnd;
	}

	public Date getSigningTime() {
		return signingTime;
	}

	public void setSigningTime(Date signingTime) {
		this.signingTime = signingTime;
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

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getScope() {
        return scope;
    }

    public void setScope(Integer scope) {
        this.scope = scope;
    }
}
