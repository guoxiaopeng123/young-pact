package com.young.pact.domain.purchasetermination;

import java.util.Date;
import java.util.List;

import com.young.common.domain.BaseDomain;
import com.young.pact.domain.commonpic.CommonPicVO;
import com.young.pact.domain.financereceiptpay.FinanceReceiptPayVO;

/**
 * 
* @ClassName: PurchaseTerminationEditVO
* @Description: 拖进合同解约协议
* @author SunYiXuan
* @date 2018年8月11日 上午10:58:33
*
 */
public class PurchaseTerminationEditVO extends BaseDomain {

    private static final long serialVersionUID = 1L;
    /*** @Fields id:主键id*/
    private Long id;
    /*** @Fields address:物业地址*/
    private String address;
    /*** @Fields type:解约类型*/
    private Integer type;
    /*** @Fields userPin:签约管家pin*/
    private String userPin;
    /*** @Fields userName:签约管家姓名*/
    private String userName;
    /*** @Fields pactEndDate:原合同截止时间*/
    private Date pactEndDate;
    /*** @Fields belonging:物品归属*/
    private String belonging;
    /*** @Fields reason:解约原因*/
    private String reason;
    /*** @Fields terminationDate:解约时间*/
    private Date terminationDate;
    /*** @Fields remark:其他约定*/
    private String remark;
    /*** @Fields terminationCode:协议编码*/
    private String terminationCode;
    /*** @Fields financeReceiptPayList:收支*/
    private List<FinanceReceiptPayVO> financeReceiptPayList;
    /*** @Fields commonPicList:协议照片*/
    private List<CommonPicVO> commonPicList;
    
    public PurchaseTerminationEditVO(){}

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public List<FinanceReceiptPayVO> getFinanceReceiptPayList() {
        return financeReceiptPayList;
    }

    public void setFinanceReceiptPayList(List<FinanceReceiptPayVO> financeReceiptPayList) {
        this.financeReceiptPayList = financeReceiptPayList;
    }

    public List<CommonPicVO> getCommonPicList() {
        return commonPicList;
    }

    public void setCommonPicList(List<CommonPicVO> commonPicList) {
        this.commonPicList = commonPicList;
    }

    public String getTerminationCode() {
        return terminationCode;
    }

    public void setTerminationCode(String terminationCode) {
        this.terminationCode = terminationCode;
    };
    
    
}
