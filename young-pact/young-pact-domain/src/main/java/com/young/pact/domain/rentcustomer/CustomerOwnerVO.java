package com.young.pact.domain.rentcustomer;

import java.util.List;

import com.young.pact.domain.rentcommonowner.RentCommonOwnerVO;

/**
 * @描述 : 托出租客共同居住人
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月2日 上午11:05:36
 */
public class CustomerOwnerVO {
    /**
     * @Fields serialVersionUID : TODO( )
     */
     @SuppressWarnings("unused")
     private static final long serialVersionUID = 1L;
     /*** @Fields key:租客信息*/
     private RentCustomerVO renter;
     /*** @Fields cohabitantList:共同居住人（集合）*/
     private List<RentCommonOwnerVO> cohabitantList;
     /*** @Fields key:缓存key*/
     public CustomerOwnerVO(){}
     
    public RentCustomerVO getRenter() {
        return renter;
    }

    public void setRenter(RentCustomerVO renter) {
        this.renter = renter;
    }

    public List<RentCommonOwnerVO> getCohabitantList() {
        return cohabitantList;
    }
    public void setCohabitantList(List<RentCommonOwnerVO> cohabitantList) {
        this.cohabitantList = cohabitantList;
    }
}
