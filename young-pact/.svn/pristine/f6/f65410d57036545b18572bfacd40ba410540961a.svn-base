package com.young.pact.api.domain.result.rest.rentturn;

import java.io.Serializable;
import java.util.List;

import com.young.pact.api.domain.result.rest.commongoods.CommonGoodsResult;
import com.young.pact.api.domain.result.rest.commonmeterread.CommonMeterReadResult;
import com.young.pact.api.domain.result.rest.commonpic.CommonPicResult;
import com.young.pact.api.domain.result.rest.financereceiptpay.FinanceReceiptPayResult;
import com.young.pact.api.domain.result.rest.rentbase.RentBaseResult;
import com.young.pact.api.domain.result.rest.rentroom.RoomResult;
import com.young.pact.api.domain.result.rest.rentturncost.RentTurnCostResult;

/**
 * @描述 : 转租协议
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月7日 上午11:06:07
 */
public class RentTurnProtocolResult implements Serializable{

    /**
    * @Fields serialVersionUID : TODO( )
    */
    private static final long serialVersionUID = 1L;
    /** @Fields rentTurnResult:转租信息**/
    private RentTurnResult rentTurnResult;
    /** @Fields receiptPayList:收支(集合)**/
    private List<FinanceReceiptPayResult> receiptPayList;
    /** @Fields rentTurnCostResults:转让费用(集合)**/
    private List<RentTurnCostResult> rentTurnCostResults;
    /** @Fields meterReadList:抄表信息(集合)**/
    private List<CommonMeterReadResult> meterReadList;
    /** @Fields goodsList:物品信息(集合)**/
    private List<CommonGoodsResult> goodsList;
    /** @Fields urlList:协议照片(集合)**/
    private List<CommonPicResult> urlList;
    /** @Fields roomResult:转租房间**/
    private RoomResult roomResult;
    /** @Fields oldPact:老合同信息**/
    private RentBaseResult oldPact;
    /** @Fields newPact:新合同信息**/
    private RentBaseResult newPact;
    public RentTurnProtocolResult(){}
    
    public RentTurnResult getRentTurnResult() {
        return rentTurnResult;
    }
    public void setRentTurnResult(RentTurnResult rentTurnResult) {
        this.rentTurnResult = rentTurnResult;
    }
    public List<FinanceReceiptPayResult> getReceiptPayList() {
        return receiptPayList;
    }
    public void setReceiptPayList(List<FinanceReceiptPayResult> receiptPayList) {
        this.receiptPayList = receiptPayList;
    }
    public List<CommonMeterReadResult> getMeterReadList() {
        return meterReadList;
    }
    public void setMeterReadList(List<CommonMeterReadResult> meterReadList) {
        this.meterReadList = meterReadList;
    }
    public List<CommonGoodsResult> getGoodsList() {
        return goodsList;
    }
    public void setGoodsList(List<CommonGoodsResult> goodsList) {
        this.goodsList = goodsList;
    }
    public List<CommonPicResult> getUrlList() {
        return urlList;
    }
    public void setUrlList(List<CommonPicResult> urlList) {
        this.urlList = urlList;
    }
    public List<RentTurnCostResult> getRentTurnCostResults() {
        return rentTurnCostResults;
    }
    public void setRentTurnCostResults(List<RentTurnCostResult> rentTurnCostResults) {
        this.rentTurnCostResults = rentTurnCostResults;
    }

    public RoomResult getRoomResult() {
        return roomResult;
    }

    public void setRoomResult(RoomResult roomResult) {
        this.roomResult = roomResult;
    }

    public RentBaseResult getOldPact() {
        return oldPact;
    }

    public void setOldPact(RentBaseResult oldPact) {
        this.oldPact = oldPact;
    }

    public RentBaseResult getNewPact() {
        return newPact;
    }

    public void setNewPact(RentBaseResult newPact) {
        this.newPact = newPact;
    }
}
