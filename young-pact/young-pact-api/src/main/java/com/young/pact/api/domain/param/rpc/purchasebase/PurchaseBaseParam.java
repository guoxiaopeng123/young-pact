package com.young.pact.api.domain.param.rpc.purchasebase;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.young.pact.api.domain.param.rest.commoncostrule.CommonCostRuleParam;
import com.young.pact.api.domain.param.rest.commonpic.CommonPicParam;
import com.young.pact.api.domain.param.rest.financereceiptpay.FinanceReceiptPayParam;
import com.young.pact.api.domain.param.rest.purchaserentfree.PurchaseRentfreeParam;
/**
 * 
* @ClassName: PurchaseBaseParam
* @Description: TODO( 合同信息)
* @author HeZeMin
* @date 2018年8月2日 上午10:21:30
*
 */
public class PurchaseBaseParam implements Serializable {
    private static final long serialVersionUID = 1L;
    /*** @Fields id:主键id*/
    private Long id;
    /*** @Fields purchaseCode:合同编码*/
    private String purchaseCode;
    /*** @Fields paperNumber:纸版合同编号*/
    private String paperNumber;
    /*** @Fields declareCode:申报编码*/
    private String declareCode;
    /*** @Fields entrustStart:委托开始*/
    private Date entrustStart;
    /*** @Fields entrustEnd:委托结束*/
    private Date entrustEnd;
    /*** @Fields entrustPeriod:委托期限*/
    private String entrustPeriod;
    /*** @Fields houseDate:交房时间*/
    private Date houseDate;
    /*** @Fields payWay:付款方式*/
    private Integer payWay;
    /*** @Fields hostingPrice:月租金*/
    private Double hostingPrice;
    /*** @Fields deposit:押金*/
    private Double deposit;
    /*** @Fields belonging:到期物品归属*/
    private String belonging;
    /*** @Fields depositReturnWay:到期押金退回方式*/
    private String depositReturnWay;
    /*** @Fields dealDate:签约时间*/
    private Date dealDate;
    /*** @Fields remark:其他约定*/
    private String remark;
    /*** @Fields firstPayDate:首次付款时间*/
    private Date firstPayDate;
    /*** @Fields payPeriodDate:付款账期的开始时间*/
    private Date payPeriodDate;
    /*** @Fields state:合同状态1草稿状态 2待审核 3已通过 4已驳回*/
    private Integer state;
    /*** @Fields expireState:合同到期状态1未到期  2到期*/
    private Integer expireState;
    /*** @Fields dealAttr:合同成交属性(1:新签 2:续签)*/
    private Integer dealAttr;
    /*** @Fields terminationState:合同解约状态1未解约 2到期解约  3违约解约*/
    private Integer terminationState;
    /*** @Fields isStandard:是否标准化0 未标准化  1 已标准化*/
    private Integer isStandard;
    /*** @Fields isDelete:删除状态0 未删除 1 删除*/
    private Integer isDelete;
    /*** @Fields createName:创建人pin*/
    private String createName;
    /*** @Fields modifiedName:修改人pin*/
    private String modifiedName;
    /*** @Fields ip:ip地址*/
    private String ip;
    /*** @Fields dealPin:签约管家*/
    private String dealPin;
    /*** @Fields reason:原因*/
    private String reason;
    
    
    /*** @Fields commonCostRuleList:合同收支规则集合*/
    private List<CommonCostRuleParam> commonCostRuleList;
    /*** @Fields PurchaseRentfreeList:合同免租期集合*/
    private List<PurchaseRentfreeParam> purchaseRentfreeList;
    /*** @Fields FinanceReceiptPayList:合同收支集合*/
    private List<FinanceReceiptPayParam> financeReceiptPayList;
    /*** @Fields CommonPicList:合同图片集合*/
    private List<CommonPicParam> commonPicList;
    
    
    
    public PurchaseBaseParam() {
    }
    
    
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getDealPin() {
        return dealPin;
    }
    public String getReason() {
        return reason;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }
    public String getModifiedName() {
        return modifiedName;
    }
    public void setModifiedName(String modifiedName) {
        this.modifiedName = modifiedName;
    }
    public void setDealPin(String dealPin) {
        this.dealPin = dealPin;
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
    public String getPurchaseCode() {
        return purchaseCode;
    }
    public void setPurchaseCode(String purchaseCode) {
        this.purchaseCode = purchaseCode;
    }
    public String getPaperNumber() {
        return paperNumber;
    }
    public void setPaperNumber(String paperNumber) {
        this.paperNumber = paperNumber;
    }
    public String getDeclareCode() {
        return declareCode;
    }
    public void setDeclareCode(String declareCode) {
        this.declareCode = declareCode;
    }
    public Date getEntrustStart() {
        return entrustStart;
    }
    public void setEntrustStart(Date entrustStart) {
        this.entrustStart = entrustStart;
    }
    public Date getEntrustEnd() {
        return entrustEnd;
    }
    public void setEntrustEnd(Date entrustEnd) {
        this.entrustEnd = entrustEnd;
    }
    public String getEntrustPeriod() {
        return entrustPeriod;
    }
    public void setEntrustPeriod(String entrustPeriod) {
        this.entrustPeriod = entrustPeriod;
    }
    public Date getHouseDate() {
        return houseDate;
    }
    public void setHouseDate(Date houseDate) {
        this.houseDate = houseDate;
    }
    public Integer getPayWay() {
        return payWay;
    }
    public void setPayWay(Integer payWay) {
        this.payWay = payWay;
    }
    public Double getHostingPrice() {
        return hostingPrice;
    }
    public void setHostingPrice(Double hostingPrice) {
        this.hostingPrice = hostingPrice;
    }
    public Double getDeposit() {
        return deposit;
    }
    public void setDeposit(Double deposit) {
        this.deposit = deposit;
    }
    public String getBelonging() {
        return belonging;
    }
    public void setBelonging(String belonging) {
        this.belonging = belonging;
    }
    public String getDepositReturnWay() {
        return depositReturnWay;
    }
    public void setDepositReturnWay(String depositReturnWay) {
        this.depositReturnWay = depositReturnWay;
    }
    public Date getDealDate() {
        return dealDate;
    }
    public void setDealDate(Date dealDate) {
        this.dealDate = dealDate;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public Date getFirstPayDate() {
        return firstPayDate;
    }
    public void setFirstPayDate(Date firstPayDate) {
        this.firstPayDate = firstPayDate;
    }
    public Date getPayPeriodDate() {
        return payPeriodDate;
    }
    public void setPayPeriodDate(Date payPeriodDate) {
        this.payPeriodDate = payPeriodDate;
    }
    public Integer getState() {
        return state;
    }
    public void setState(Integer state) {
        this.state = state;
    }
    public Integer getExpireState() {
        return expireState;
    }
    public void setExpireState(Integer expireState) {
        this.expireState = expireState;
    }
    public Integer getDealAttr() {
        return dealAttr;
    }
    public void setDealAttr(Integer dealAttr) {
        this.dealAttr = dealAttr;
    }
    public Integer getTerminationState() {
        return terminationState;
    }
    public void setTerminationState(Integer terminationState) {
        this.terminationState = terminationState;
    }
    public Integer getIsStandard() {
        return isStandard;
    }
    public void setIsStandard(Integer isStandard) {
        this.isStandard = isStandard;
    }
    public Integer getIsDelete() {
        return isDelete;
    }
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }



    public List<CommonCostRuleParam> getCommonCostRuleList() {
        return commonCostRuleList;
    }



    public void setCommonCostRuleList(List<CommonCostRuleParam> commonCostRuleList) {
        this.commonCostRuleList = commonCostRuleList;
    }



    public List<PurchaseRentfreeParam> getPurchaseRentfreeList() {
        return purchaseRentfreeList;
    }



    public void setPurchaseRentfreeList(List<PurchaseRentfreeParam> purchaseRentfreeList) {
        this.purchaseRentfreeList = purchaseRentfreeList;
    }



    public List<FinanceReceiptPayParam> getFinanceReceiptPayList() {
        return financeReceiptPayList;
    }



    public void setFinanceReceiptPayList(List<FinanceReceiptPayParam> financeReceiptPayList) {
        this.financeReceiptPayList = financeReceiptPayList;
    }



    public List<CommonPicParam> getCommonPicList() {
        return commonPicList;
    }



    public void setCommonPicList(List<CommonPicParam> commonPicList) {
        this.commonPicList = commonPicList;
    }
}
