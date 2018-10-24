package com.young.pact.api.domain.param.rest.commonmeterread;

import java.io.Serializable;
import java.util.List;

import com.young.pact.api.domain.param.rest.commongoods.CommonGoodsParam;

/**
 * @描述 :  物品+抄表
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月2日 下午5:02:27
 */
public class MeterGoodsParam implements Serializable{

    /**
    * @Fields serialVersionUID : TODO( )
    */
    private static final long serialVersionUID = 1L;

    /** @Fields meterReadList:抄表信息(集合)**/
    private List<CommonMeterReadParam> meterReadList;
    /** @Fields goodsList:物品信息(集合)**/
    private List<CommonGoodsParam> goodsList;
    /** @Fields serviceCode:资源编码**/
    private String serviceCode;
    /** @Fields type:类型**/
    private Integer type;
    /** @Fields type:创建人**/
    private String createName;
    /** @Fields ip:ip地址**/
    private String ip;
    public MeterGoodsParam(){}

    public List<CommonMeterReadParam> getMeterReadList() {
        return meterReadList;
    }

    public void setMeterReadList(List<CommonMeterReadParam> meterReadList) {
        this.meterReadList = meterReadList;
    }


    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public List<CommonGoodsParam> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<CommonGoodsParam> goodsList) {
        this.goodsList = goodsList;
    }
}
