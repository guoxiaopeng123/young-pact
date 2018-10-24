package com.young.pact.domain.rentcustomer;

import java.io.Serializable;
import java.util.List;

import com.young.pact.domain.rentcommonowner.RentCommonOwnerDO;

/**
 * @描述 : 托出租客共同居住人
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月1日 下午5:37:04
 */
public class CustomerOwnerDO implements Serializable{
    /**
    * @Fields serialVersionUID : TODO( )
    */
    private static final long serialVersionUID = 1L;
    /*** @Fields key:租客信息*/
    private RentCustomerDO renter;
    /*** @Fields cohabitantList:共同居住人（集合）*/
    private List<RentCommonOwnerDO> cohabitantList;
    /*** @Fields key:缓存key*/
    private String key;
    
    private Integer type;
    /** @Fields serviceCode:业务编码(redis用)type=0 房间编码 type=2 托出合同编码**/
    private String serviceCode;
    /*** @Fields createName:创建人pin*/
    private String createName;
    /*** @Fields ip:ip地址*/
    private String ip;
    public CustomerOwnerDO(){}
    public RentCustomerDO getRenter() {
        return renter;
    }
    public void setRenter(RentCustomerDO renter) {
        this.renter = renter;
    }
    public List<RentCommonOwnerDO> getCohabitantList() {
        return cohabitantList;
    }
    public void setCohabitantList(List<RentCommonOwnerDO> cohabitantList) {
        this.cohabitantList = cohabitantList;
    }
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
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
    
}
