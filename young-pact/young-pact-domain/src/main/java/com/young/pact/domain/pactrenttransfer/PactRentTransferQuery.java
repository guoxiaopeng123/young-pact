package com.young.pact.domain.pactrenttransfer;

import java.util.List;

import com.young.common.domain.BaseDomain;

/**
 * 
* @ClassName: PactRentTransferQuery
* @Description: 调房协议查询类
* @author LiuYuChi
* @date 2018年8月8日 下午1:38:39
*
 */
public class PactRentTransferQuery extends BaseDomain{

	private static final long serialVersionUID = 1L;
	
	/*** @Fields id:主键id*/
    private Long id;
    
    /*** @Fields transferCode:调房协议编码*/
    private String transferCode;
    
    /*** @Fields address:物业地址*/
    private String address;
    
    /*** @Fields pirmaryPactCode:原合同编码*/
    private String pirmaryPactCode;
    
    /*** @Fields newPactCode:新合同编码*/
    private String newPactCode;
    
    /*** @Fields signingTime:签约时间*/
    private String signingTime;
    
    /*** @Fields state:合同状态*/
    private Integer state;
    
    /*** @Fields isDelete:删除状态*/
    private Integer isDelete;
    
    /*** @Fields transferTimeStart:合同截止开始时间*/
    private String transferTimeStart;
    
    /*** @Fields transferTimeEnd:合同截止结束时间*/
    private String transferTimeEnd;
    
    /*** @Fields signingTimeStart:签约开始时间*/
    private String signingTimeStart;
    
    /*** @Fields signingTimeEnd:签约结束时间*/
    private String signingTimeEnd;
    
    /*** @Fields deptCode:部门*/
    private String deptCode;
    
    /*** @Fields natives:调房性质*/
    private String natives;
    
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

	public String getSigningTime() {
		return signingTime;
	}

	public void setSigningTime(String signingTime) {
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

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getNatives() {
		return natives;
	}

	public void setNatives(String natives) {
		this.natives = natives;
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
