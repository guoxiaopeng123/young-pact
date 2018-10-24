package com.young.pact.api.domain.param.rest.purchasetermination;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.young.pact.api.domain.param.rest.commongoods.CommonGoodsParam;
import com.young.pact.api.domain.param.rest.commonmeterread.CommonMeterReadParam;
import com.young.pact.api.domain.param.rest.commonpic.CommonPicParam;
import com.young.pact.api.domain.param.rest.financereceiptpay.FinanceReceiptPayParam;

/**
 * 
* @ClassName: PurchaseTerminationParam
* @Description: 托进解约协议
* @author SunYiXuan
* @date 2018年8月9日 下午2:18:53
*
 */
public class PurchaseTerminationParam implements Serializable{

    private static final long serialVersionUID = 1L;
    //主键
    private Long id;
    //需要解约的合同编码
    private String purchaseCode;
    //物业地址
    private String address;
    //签约管家
    private String userPin;
    //原合同截止时间
    private Date pactEndDate;
    //物品归属
    private String belonging;
    //解约原因
    private String reason;
    //解约时间
    private Date terminationDate;
    //其他约定
    private String remark;
    //解约类型
    private Integer type;
    //协议编码
    private String terminationCode;
    //创建人
    private String createName;
    //修改人
    private String modifiedName;
    //ip
    private String ip;
    
    //收支
    private List<FinanceReceiptPayParam> financeReceiptPayList;
    //抄表
    private List<CommonMeterReadParam> commonMeterReadList;
    //物品
    private List<CommonGoodsParam> commonGoodsList;
    //照片
    private List<CommonPicParam> commonPicList;
    
    public PurchaseTerminationParam(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPurchaseCode() {
        return purchaseCode;
    }

    public void setPurchaseCode(String purchaseCode) {
        this.purchaseCode = purchaseCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserPin() {
        return userPin;
    }

    public void setUserPin(String userPin) {
        this.userPin = userPin;
    }

    public Date getPactEndDate() {
        return pactEndDate;
    }

    public void setPactEndDate(Date pactEndDate) {
        this.pactEndDate = pactEndDate;
    }

    public String getBelonging() {
        return belonging;
    }

    public void setBelonging(String belonging) {
        this.belonging = belonging;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getTerminationDate() {
        return terminationDate;
    }

    public void setTerminationDate(Date terminationDate) {
        this.terminationDate = terminationDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<FinanceReceiptPayParam> getFinanceReceiptPayList() {
        return financeReceiptPayList;
    }

    public void setFinanceReceiptPayList(List<FinanceReceiptPayParam> financeReceiptPayList) {
        this.financeReceiptPayList = financeReceiptPayList;
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

    public List<CommonPicParam> getCommonPicList() {
        return commonPicList;
    }

    public void setCommonPicList(List<CommonPicParam> commonPicList) {
        this.commonPicList = commonPicList;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTerminationCode() {
        return terminationCode;
    }

    public void setTerminationCode(String terminationCode) {
        this.terminationCode = terminationCode;
    }

    public String getModifiedName() {
        return modifiedName;
    }

    public void setModifiedName(String modifiedName) {
        this.modifiedName = modifiedName;
    };
    
    
}
