package com.young.pact.api.domain.result.rest.purchasetermination;

import java.io.Serializable;
import java.util.Date;

/**
 * 
* @ClassName: PurchaseTerminationListResult
* @Description: 托进合同解约协议
* @author SunYiXuan
* @date 2018年8月10日 下午1:39:14
*
 */
public class PurchaseTerminationListResult implements Serializable{

    private static final long serialVersionUID = 1L;
    //主键
    private Long id;
    //协议编号
    private String terminationCode;
    //合同编号
    private String purchaseCode;
    //物业地址
    private String address;
    //解约类型
    private Integer type;
    //原合同截止时间
    private Date pactEndDate;
    //解约时间
    private Date terminationDate;
    //维护人姓名
    private String userName;
    //维护人部门
    private String deptName;
    //创建时间
    private Date create;
    //审核状态
    private Integer state;
    
    
    
    
    
    
    
    public PurchaseTerminationListResult(){}

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

    public Date getCreate() {
        return create;
    }

    public void setCreate(Date create) {
        this.create = create;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    };
    
    
}
