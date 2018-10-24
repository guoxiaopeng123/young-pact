package com.young.pact.api.domain.result.rest.customer;

import java.io.Serializable;
import java.util.Date;

/**
 * @描述 : 客源
 * @创建者 : GuoXiaoPeng
 * @创建时间 : 2018年9月13日 上午10:29:49
 */
public class CustomerResult implements Serializable{
    /**
    * @Fields serialVersionUID : TODO( )
    */
    private static final long serialVersionUID = 1L;
    /*** @Fields customerCode:客户编号*/
    private String  customerCode;
    /*** @Fields customerName:客户姓名*/
    private String  customerName;
    /*** @Fields customerSex:客户性别*/
    private Integer  customerSex;
    /*** @Fields customerTel:客户电话*/
    private String  customerTel;
    /*** @Fields createName:客户创建人*/
    private String  createName; 
    /*** @Fields create:创建时间*/
    private Date  create;
    /*** @Fields customerState:状态*/
    private Integer  customerState;
    /*** @Fields count:共同居住人数量*/
    private Integer  count;
    public CustomerResult(){}
    public String getCustomerCode() {
        return customerCode;
    }
    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public Integer getCustomerSex() {
        return customerSex;
    }
    public void setCustomerSex(Integer customerSex) {
        this.customerSex = customerSex;
    }
    public String getCustomerTel() {
        return customerTel;
    }
    public void setCustomerTel(String customerTel) {
        this.customerTel = customerTel;
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
    public Integer getCustomerState() {
        return customerState;
    }
    public void setCustomerState(Integer customerState) {
        this.customerState = customerState;
    }
    public Integer getCount() {
        return count;
    }
    public void setCount(Integer count) {
        this.count = count;
    }
    
}
