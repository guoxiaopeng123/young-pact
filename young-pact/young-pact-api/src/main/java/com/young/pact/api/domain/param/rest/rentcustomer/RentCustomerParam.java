package com.young.pact.api.domain.param.rest.rentcustomer;

import java.io.Serializable;
import java.util.List;

import com.young.pact.api.domain.param.rest.commonowner.CommonOwnerParam;

/**
 * @description : 租客
 * @author : guoxiaopeng
 * @date : 2018年8月1日 上午10:10:06
 */
public class RentCustomerParam implements Serializable{

    /**
    * @Fields serialVersionUID : TODO( )
    */
    private static final long serialVersionUID = 1L;
    
    /** @Fields renter:租客信息**/
    private RenterParam renter;
    /** @Fields cohabitantList:共同居住人（集合）**/
    private List<CommonOwnerParam> cohabitantList;
    /** @Fields type:类型**/
    private Integer type;
    /** @Fields serviceCode:业务编码(redis用)type=0 房间编码 type=2 托出合同编码**/
    private String serviceCode;
    /*** @Fields createName:创建人pin*/
    private String createName;
    /*** @Fields ip:ip地址*/
    private String ip;
    /** @Fields rentPactCode:托出合同编码**/
    private String rentPactCode; 
    public RentCustomerParam(){}
    public RenterParam getRenter() {
        return renter;
    }
    public void setRenter(RenterParam renter) {
        this.renter = renter;
    }
    public List<CommonOwnerParam> getCohabitantList() {
        return cohabitantList;
    }
    public void setCohabitantList(List<CommonOwnerParam> cohabitantList) {
        this.cohabitantList = cohabitantList;
    }
    public Integer getType() {
        return type;
    }
    public void setType(Integer type) {
        this.type = type;
    }
    public String getServiceCode() {
        return serviceCode;
    }
    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }
    public String getCreateName() {
        return createName;
    }
    public void setCreateName(String createName) {
        this.createName = createName;
    }
    public String getIp() {
        return ip;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }
    public String getRentPactCode() {
        return rentPactCode;
    }
    public void setRentPactCode(String rentPactCode) {
        this.rentPactCode = rentPactCode;
    }
}
