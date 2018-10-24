package com.young.pact.api.domain.result.rest.commonmeterread;

import java.io.Serializable;
import java.util.List;

import com.young.pact.api.domain.result.rest.commongoods.CommonGoodsResult;

/**
 * @描述 :  物品+抄表
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月2日 下午5:02:27
 */
public class MeterGoodsResult implements Serializable{

    /**
    * @Fields serialVersionUID : TODO( )
    */
    private static final long serialVersionUID = 1L;

    /** @Fields meterReadList:抄表信息(集合)**/
    private List<CommonMeterReadResult> meterReadList;
    /** @Fields goodsList:物品信息(集合)**/
    private List<CommonGoodsResult> goodsList;
  
    public MeterGoodsResult(){}


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
    
}
