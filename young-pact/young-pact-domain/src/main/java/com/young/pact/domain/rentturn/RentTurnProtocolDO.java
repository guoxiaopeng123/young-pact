package com.young.pact.domain.rentturn;

import java.io.Serializable;
import java.util.List;

import com.young.common.domain.BaseDomain;
import com.young.pact.domain.commongoods.CommonGoodsDO;
import com.young.pact.domain.commonmeterread.CommonMeterReadDO;
import com.young.pact.domain.commonpic.CommonPicDO;
import com.young.pact.domain.financereceiptpay.FinanceReceiptPayDO;
import com.young.pact.domain.rentturncost.RentTurnCostDO;


/**
 * @描述 :  转租协议
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月7日 上午11:43:46
 */
public class RentTurnProtocolDO extends BaseDomain implements Serializable {

    /**
    * @Fields serialVersionUID : TODO( )
    */
    private static final long serialVersionUID = 1L;
    /** @Fields rentTurnResult:转租信息**/
    private RentTurnDO rentTurnDO;
    /** @Fields receiptPayList:收支(集合)**/
    private List<FinanceReceiptPayDO> financeReceiptPayDOs;
    /** @Fields rentTurnCostResults:转让费用(集合)**/
    private List<RentTurnCostDO> rentTurnCostDOs;
    /** @Fields meterReadList:抄表信息(集合)**/
    private List<CommonMeterReadDO> meterReadList;
    /** @Fields goodsList:物品信息(集合)**/
    private List<CommonGoodsDO> goodsList;
    /** @Fields urlList:协议照片(集合)**/
    private List<CommonPicDO> urlList;
    public RentTurnProtocolDO(){}
    public RentTurnDO getRentTurnDO() {
        return rentTurnDO;
    }
    public void setRentTurnDO(RentTurnDO rentTurnDO) {
        this.rentTurnDO = rentTurnDO;
    }
    public List<FinanceReceiptPayDO> getFinanceReceiptPayDOs() {
        return financeReceiptPayDOs;
    }
    public void setFinanceReceiptPayDOs(List<FinanceReceiptPayDO> financeReceiptPayDOs) {
        this.financeReceiptPayDOs = financeReceiptPayDOs;
    }
    public List<RentTurnCostDO> getRentTurnCostDOs() {
        return rentTurnCostDOs;
    }
    public void setRentTurnCostDOs(List<RentTurnCostDO> rentTurnCostDOs) {
        this.rentTurnCostDOs = rentTurnCostDOs;
    }
    public List<CommonMeterReadDO> getMeterReadList() {
        return meterReadList;
    }
    public void setMeterReadList(List<CommonMeterReadDO> meterReadList) {
        this.meterReadList = meterReadList;
    }
    public List<CommonGoodsDO> getGoodsList() {
        return goodsList;
    }
    public void setGoodsList(List<CommonGoodsDO> goodsList) {
        this.goodsList = goodsList;
    }
    public List<CommonPicDO> getUrlList() {
        return urlList;
    }
    public void setUrlList(List<CommonPicDO> urlList) {
        this.urlList = urlList;
    }
}
