package com.young.pact.domain.purchasetermination;

import java.util.Date;
import java.util.List;

import com.young.common.domain.BaseDomain;
import com.young.pact.domain.commonpic.CommonPicVO;

/**
 * 
* @ClassName: PurchaseTerminationBaseVO
* @Description: 拖进合同解约协议基本信息
* @author SunYiXuan
* @date 2018年8月10日 下午5:07:06
*
 */
public class PurchaseTerminationBaseVO extends BaseDomain {

    private static final long serialVersionUID = 1L;
    /*** @Fields id:主键id*/
    private Long id;
    /*** @Fields terminationCode:协议编号*/
    private String terminationCode;
    /*** @Fields type:解约类型*/
    private Integer type;
    /*** @Fields pactEndDate:原合同截止时间*/
    private Date pactEndDate;
    /*** @Fields terminationDate:解约时间*/
    private Date terminationDate;
    /*** @Fields userName:签约管家*/
    private String userName;
    /*** @Fields state:审核状态*/
    private Integer state;
    /*** @Fields reason:原因*/
    private String reason;
    /*** @Fields terminationRemark:其他约定*/
    private String terminationRemark;
    /*** @Fields terminationBelonging:物品归属*/
    private String terminationBelonging;
    /*** @Fields commonPicList:照片*/
    private List<CommonPicVO> commonPicList;
    /** 合同部分 */
    /*** @Fields purchaseCode:合同编码*/
    private String purchaseCode;
    //纸质合同编码
    /*** @Fields paperNumber:纸质合同编码*/
    private String paperNumber;
    /*** @Fields houseCode:房源编码*/
    private String houseCode;
    /*** @Fields entrustStart:委托日期*/
    private Date entrustStart;
    /*** @Fields entrustEnd:结束日期*/
    private Date entrustEnd;
    /*** @Fields entrustPeriod:委托期限*/
    private String entrustPeriod;
    /*** @Fields houseDate:交房时间*/
    private Date houseDate;
    /*** @Fields dealDate:签约时间*/
    private Date dealDate;
    /*** @Fields payWay:付款方式*/
    private Integer payWay;
    /*** @Fields hostingPrice:月租金*/
    private Double hostingPrice;
    /*** @Fields deposit:押金*/
    private Double deposit;
    /*** @Fields purchaseBelonging:物品归属*/
    private String purchaseBelonging;
    /*** @Fields purchaseRemark:其他约定*/
    private String purchaseRemark;
    
    public PurchaseTerminationBaseVO(){}

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
    public List<CommonPicVO> getCommonPicList() {
        return commonPicList;
    }

    public void setCommonPicList(List<CommonPicVO> commonPicList) {
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

    public String getPurchaseRemark() {
        return purchaseRemark;
    }

    public void setPurchaseRemark(String purchaseRemark) {
        this.purchaseRemark = purchaseRemark;
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

    public String getPurchaseBelonging() {
        return purchaseBelonging;
    }

    public void setPurchaseBelonging(String purchaseBelonging) {
        this.purchaseBelonging = purchaseBelonging;
    }

    
    
}
