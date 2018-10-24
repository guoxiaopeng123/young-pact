package com.young.pact.api.domain.param.rest.propertytransfer;

import java.io.Serializable;
import java.util.List;

import com.young.pact.api.domain.param.rest.commongoods.CommonGoodsParam;
import com.young.pact.api.domain.param.rest.commonmeterread.CommonMeterReadParam;
/**
 * 
* @ClassName: PropertyTransferParam
* @Description: TODO( 物业交接)
* @author HeZeMin
* @date 2018年8月2日 上午10:13:58
*
 */
public class PropertyTransferParam implements Serializable {
    private static final long serialVersionUID = 1L;
    /*** @Fields declareCode:申报编码*/
    private String declareCode;
    /*** @Fields createName:创建人pin*/
    private String createName;
    /*** @Fields ip:ip地址*/
    private String ip;
    
    /*** @Fields commonMeterReadList:抄表集合*/
    private List<CommonMeterReadParam> commonMeterReadList;
    /*** @Fields commonGoodsList:物品集合*/
    private List<CommonGoodsParam> commonGoodsList;
    /*** @Fields houseCode:项目编码*/
    private String houseCode;
    public PropertyTransferParam() {
    }
    
    
    public String getDeclareCode() {
        return declareCode;
    }
    public void setDeclareCode(String declareCode) {
        this.declareCode = declareCode;
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


    public String getHouseCode() {
        return houseCode;
    }


    public void setHouseCode(String houseCode) {
        this.houseCode = houseCode;
    }
}
