package com.young.pact.api.domain.param.rest.rentturn;

import java.io.Serializable;
import java.util.List;

import com.young.pact.api.domain.param.rest.commongoods.CommonGoodsParam;
import com.young.pact.api.domain.param.rest.commonmeterread.CommonMeterReadParam;
import com.young.pact.api.domain.param.rest.commonpic.CommonPicParam;
import com.young.pact.api.domain.param.rest.financereceiptpay.FinanceReceiptPayParam;
import com.young.pact.api.domain.param.rest.rentturncost.RentTurnCostParam;

/**
 * @描述 : 转租协议
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月7日 上午11:02:02
 */
public class RentTurnProtocolParam implements Serializable{

    /**
    * @Fields serialVersionUID : TODO( )
    */
    private static final long serialVersionUID = 1L;
    /** @Fields rentTurnParam:转租协议信息**/
    private RentTurnParam rentTurnParam;
    /** @Fields receiptPayList:收支(集合)**/
    private List<FinanceReceiptPayParam> receiptPayList;
    /** @Fields receiptPayList:转让费用(集合)**/
    private List<RentTurnCostParam> rentTurnCostList;
    /*** @Fields commonMeterReadList:抄表集合*/
    private List<CommonMeterReadParam> commonMeterReadList;
    /*** @Fields commonGoodsList:物品集合*/
    private List<CommonGoodsParam> commonGoodsList;
    /** @Fields urlList:合同照片(集合)**/
    private List<CommonPicParam> urlList;
    /** @Fields createName:登录人**/
    private String createName;
    /** @Fields serviceCode:资源编码**/
    private String serviceCode;
    private String ip;
    
    public RentTurnProtocolParam(){}

    public RentTurnParam getRentTurnParam() {
        return rentTurnParam;
    }

    public void setRentTurnParam(RentTurnParam rentTurnParam) {
        this.rentTurnParam = rentTurnParam;
    }

    public List<RentTurnCostParam> getRentTurnCostList() {
        return rentTurnCostList;
    }

    public void setRentTurnCostList(List<RentTurnCostParam> rentTurnCostList) {
        this.rentTurnCostList = rentTurnCostList;
    }

    public List<CommonMeterReadParam> getCommonMeterReadList() {
        return commonMeterReadList;
    }

    public void setCommonMeterReadList(List<CommonMeterReadParam> commonMeterReadList) {
        this.commonMeterReadList = commonMeterReadList;
    }

    public List<CommonGoodsParam> getCommonGoodsList() {
        return commonGoodsList;
    }

    public void setCommonGoodsList(List<CommonGoodsParam> commonGoodsList) {
        this.commonGoodsList = commonGoodsList;
    }

    public List<FinanceReceiptPayParam> getReceiptPayList() {
        return receiptPayList;
    }

    public void setReceiptPayList(List<FinanceReceiptPayParam> receiptPayList) {
        this.receiptPayList = receiptPayList;
    }

    public List<CommonPicParam> getUrlList() {
        return urlList;
    }

    public void setUrlList(List<CommonPicParam> urlList) {
        this.urlList = urlList;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
    
    
}
