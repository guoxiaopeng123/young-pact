package com.young.pact.api.domain.result.rest.rentcustomer;

import java.io.Serializable;
import java.util.List;

import com.young.pact.api.domain.result.rest.commonowner.CommonOwnerResult;

/**
 * @描述 : 租客+共同居住人
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月1日 上午11:03:15
 */
public class RentCustomerResult implements Serializable{

    /**
    * @Fields serialVersionUID : TODO( )
    */
    private static final long serialVersionUID = 1L;
    /** @Fields renter:租客信息**/
    private RenterResult renter;
    /** @Fields cohabitantList:共同居住人（集合）**/
    private List<CommonOwnerResult> cohabitantList;
    
    public RentCustomerResult(){}

    public RenterResult getRenter() {
        return renter;
    }

    public void setRenter(RenterResult renter) {
        this.renter = renter;
    }

    public List<CommonOwnerResult> getCohabitantList() {
        return cohabitantList;
    }

    public void setCohabitantList(List<CommonOwnerResult> cohabitantList) {
        this.cohabitantList = cohabitantList;
    }
}
