package com.young.pact.api.domain.result.rest.rentbase;

import java.io.Serializable;
import java.util.List;

import com.young.pact.api.domain.result.rest.commoncostrule.CommonCostRuleResult;
import com.young.pact.api.domain.result.rest.commonpic.CommonPicResult;
import com.young.pact.api.domain.result.rest.financereceiptpay.FinanceReceiptPayResult;
import com.young.pact.api.domain.result.rest.rentcustomer.RentCustomerResult;
import com.young.pact.api.domain.result.rest.rentroom.RoomResult;

/**
 * @描述 : 托出合同
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月2日 下午12:11:06
 */
public class RentPactResult implements Serializable {

    /**
    * @Fields serialVersionUID : TODO( )
    */
    private static final long serialVersionUID = 1L;
    /** @Fields pact:合同信息**/
    private RentBaseResult pact;
    /** @Fields pactReceiptPayList:合同收支(集合)**/
    private List<CommonCostRuleResult> pactReceiptPayList;
    /** @Fields receiptPayList:收支(集合)**/
    private List<FinanceReceiptPayResult> receiptPayList;
    /** @Fields urlList:合同照片(集合)**/
    private List<CommonPicResult> urlList;
    /** @Fields rentCustomerResult:租客信息**/
    private RentCustomerResult rentCustomerResult;
    /** @Fields roomResult:房间信息**/
    private RoomResult roomResult;
    public RentPactResult(){
        
    }
    public RentBaseResult getPact() {
        return pact;
    }
    public void setPact(RentBaseResult pact) {
        this.pact = pact;
    }
    public List<CommonPicResult> getUrlList() {
        return urlList;
    }
    public void setUrlList(List<CommonPicResult> urlList) {
        this.urlList = urlList;
    }
    public List<CommonCostRuleResult> getPactReceiptPayList() {
        return pactReceiptPayList;
    }
    public void setPactReceiptPayList(List<CommonCostRuleResult> pactReceiptPayList) {
        this.pactReceiptPayList = pactReceiptPayList;
    }
    public List<FinanceReceiptPayResult> getReceiptPayList() {
        return receiptPayList;
    }
    public void setReceiptPayList(List<FinanceReceiptPayResult> receiptPayList) {
        this.receiptPayList = receiptPayList;
    }
    public RentCustomerResult getRentCustomerResult() {
        return rentCustomerResult;
    }
    public void setRentCustomerResult(RentCustomerResult rentCustomerResult) {
        this.rentCustomerResult = rentCustomerResult;
    }
    public RoomResult getRoomResult() {
        return roomResult;
    }
    public void setRoomResult(RoomResult roomResult) {
        this.roomResult = roomResult;
    }
}
