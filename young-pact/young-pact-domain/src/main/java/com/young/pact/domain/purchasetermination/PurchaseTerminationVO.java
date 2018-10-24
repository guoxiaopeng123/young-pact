package com.young.pact.domain.purchasetermination;

import java.util.Date;

import com.young.common.domain.BaseDomain;

public class PurchaseTerminationVO extends BaseDomain {

    private static final long serialVersionUID = 1L;
    /*** @Fields id:主键id*/
    private Long id;
    /*** @Fields terminationCode:协议编号*/
    private String terminationCode;
    /*** @Fields purchaseCode:合同编号*/
    private String purchaseCode;
    /*** @Fields address:物业地址*/
    private String address;
    /*** @Fields type:解约类型*/
    private Integer type;
    /*** @Fields pactEndDate:原合同截止时间*/
    private Date pactEndDate;
    /*** @Fields terminationDate:解约时间*/
    private Date terminationDate;
    /*** @Fields userName:维护人姓名*/
    private String userName;
    /*** @Fields deptName:维护人部门*/
    private String deptName;
    /*** @Fields state:审核状态*/
    private Integer state;
    /*** @Fields 维护人pin:guardianPin*/
    private String guardianPin;
    
    public PurchaseTerminationVO(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTerminationCode() {
        return terminationCode;
    }

    public void setTerminationCode(String terminationCode) {
        this.terminationCode = terminationCode;
    }

    public String getPurchaseCode() {
        return purchaseCode;
    }

    public void setPurchaseCode(String purchaseCode) {
        this.purchaseCode = purchaseCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getPactEndDate() {
        return pactEndDate;
    }

    public void setPactEndDate(Date pactEndDate) {
        this.pactEndDate = pactEndDate;
    }

    public Date getTerminationDate() {
        return terminationDate;
    }

    public void setTerminationDate(Date terminationDate) {
        this.terminationDate = terminationDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getGuardianPin() {
        return guardianPin;
    }

    public void setGuardianPin(String guardianPin) {
        this.guardianPin = guardianPin;
    }
    
}
