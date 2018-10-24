package com.young.pact.api.domain.result.rest.purchasetermination;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.young.pact.api.domain.result.rest.commonpic.CommonPicResult;
import com.young.pact.api.domain.result.rest.financereceiptpay.FinanceReceiptPayResult;

/**
 * 
* @ClassName: PurchaseTerminationEditResult
* @Description: 拖进合同解约协议
* @author SunYiXuan
* @date 2018年8月11日 上午10:58:07
*
 */
public class PurchaseTerminationEditResult implements Serializable {

    private static final long serialVersionUID = 1L;
    //主键
    private Long id;
    //物业地址
    private String address;
    //解约类型
    private Integer type;
    //管家pin
    private String userPin;
    //管家姓名
    private String userName;
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
    //协议编码
    private String terminationCode;
    //收支
    private List<FinanceReceiptPayResult> financeReceiptPayList;
    //协议照片
    private List<CommonPicResult> commonPicList;
    
    public PurchaseTerminationEditResult(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public List<FinanceReceiptPayResult> getFinanceReceiptPayList() {
        return financeReceiptPayList;
    }

    public void setFinanceReceiptPayList(List<FinanceReceiptPayResult> financeReceiptPayList) {
        this.financeReceiptPayList = financeReceiptPayList;
    }

    public List<CommonPicResult> getCommonPicList() {
        return commonPicList;
    }

    public void setCommonPicList(List<CommonPicResult> commonPicList) {
        this.commonPicList = commonPicList;
    }

    public String getTerminationCode() {
        return terminationCode;
    }

    public void setTerminationCode(String terminationCode) {
        this.terminationCode = terminationCode;
    };
    
    
}
