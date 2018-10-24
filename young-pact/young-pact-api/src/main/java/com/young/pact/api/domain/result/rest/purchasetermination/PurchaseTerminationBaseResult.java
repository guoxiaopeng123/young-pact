package com.young.pact.api.domain.result.rest.purchasetermination;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.young.pact.api.domain.result.rest.commonpic.CommonPicResult;

/**
 * 
* @ClassName: PurchaseTerminationBaseResult
* @Description: 拖进合同解约协议基本信息
* @author SunYiXuan
* @date 2018年8月10日 下午5:05:55
*
 */
public class PurchaseTerminationBaseResult implements Serializable{

    private static final long serialVersionUID = 1L;
    //主键
    private Long id;
    //协议编号
    private String terminationCode;
    //解约类型
    private Integer type;
    //原合同截止时间
    private Date pactEndDate;
    //解约时间
    private Date terminationDate;
    //签约管家
    private String userName;
    //创建时间
    private Date create;
    //审核状态
    private Integer state;
    //原因
    private String reason;
    //其他约定
    private String terminationRemark;
    //物品归属
    private String terminationBelonging;
    //照片
    private List<CommonPicResult> commonPicList;
    
    
    /** 合同部分 */
    //合同编码
    private String purchaseCode;
    //纸质合同编码
    private String paperNumber;
    //房源编码
    private String houseCode;
    //委托日期
    private Date entrustStart;
    //结束日期
    private Date entrustEnd;
    //委托期限
    private String entrustPeriod;
    //交房时间
    private Date houseDate;
    //签约时间
    private Date dealDate;
    //付款方式
    private Integer payWay;
    //月租金
    private Double hostingPrice;
    //押金
    private Double deposit;
    //物品归属
    private String purchaseBelonging;
    //其他约定
    private String purchaseRemark;
    
    public PurchaseTerminationBaseResult(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTerminationCode() {
        return terminationCode;
    }

    public void setTerminationCode(String terminationCode) {
        this.terminationCode = terminationCode;
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

    public Date getTerminationDate() {
        return terminationDate;
    }

    public void setTerminationDate(Date terminationDate) {
        this.terminationDate = terminationDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getCreate() {
        return create;
    }

    public void setCreate(Date create) {
        this.create = create;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getTerminationRemark() {
        return terminationRemark;
    }

    public void setTerminationRemark(String terminationRemark) {
        this.terminationRemark = terminationRemark;
    }

    public String getTerminationBelonging() {
        return terminationBelonging;
    }

    public void setTerminationBelonging(String terminationBelonging) {
        this.terminationBelonging = terminationBelonging;
    }

    public List<CommonPicResult> getCommonPicList() {
        return commonPicList;
    }

    public void setCommonPicList(List<CommonPicResult> commonPicList) {
        this.commonPicList = commonPicList;
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

    public String getHouseCode() {
        return houseCode;
    }

    public void setHouseCode(String houseCode) {
        this.houseCode = houseCode;
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

    public Date getDealDate() {
        return dealDate;
    }

    public void setDealDate(Date dealDate) {
        this.dealDate = dealDate;
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

    public String getPurchaseBelonging() {
        return purchaseBelonging;
    }

    public void setPurchaseBelonging(String purchaseBelonging) {
        this.purchaseBelonging = purchaseBelonging;
    }

    public String getPurchaseRemark() {
        return purchaseRemark;
    }

    public void setPurchaseRemark(String purchaseRemark) {
        this.purchaseRemark = purchaseRemark;
    }

}
