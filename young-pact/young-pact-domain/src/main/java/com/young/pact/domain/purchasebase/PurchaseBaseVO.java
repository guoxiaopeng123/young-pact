package com.young.pact.domain.purchasebase;

import java.util.Date;
import java.util.List;

import com.young.common.domain.BaseDomain;
import com.young.pact.domain.commonbelonger.CommonBelongerVO;
import com.young.pact.domain.commoncostrule.CommonCostRuleVO;
import com.young.pact.domain.commonpic.CommonPicVO;
import com.young.pact.domain.financereceiptpay.FinanceReceiptPayVO;
import com.young.pact.domain.purchasehouse.PurchaseHouseOwnerVO;
import com.young.pact.domain.purchasehouse.PurchaseHouseVO;
import com.young.pact.domain.purchaserentfree.PurchaseRentfreeVO;
/**
 * 
* @ClassName: PurchaseBaseVO
* @Description: TODO( 托进合同基本信息)
* @author HeZeMin
* @date 2018年8月1日 上午10:54:14
*
 */
public class PurchaseBaseVO extends BaseDomain {
    /***************属性*******************/
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
    private String payPeriodDate;
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
    /*** @Fields rentMode:租赁方式 (整租，合租)*/
    private String rentMode;
    /*** @Fields isDelete:删除状态0 未删除 1 删除*/
    private Integer isDelete;
    /*** @Fields houseCode:房源编号*/
    private String houseCode;
    /*** @Fields address:物业地址*/
    private String address;
    /*** @Fields operatingName:物业地址*/
    private String operatingName;
    /*** @Fields operatingDeptName:物业地址*/
    private String operatingDeptName;
    /*** @Fields serviceName:物业地址*/
    private String serviceName;
    /*** @Fields serviceDeptName:物业地址*/
    private String serviceDeptName;
    /*** @Fields operatePin:运管管家PIN*/
    private String operatePin;
    /*** @Fields afterRentingPin:租后管家PIN*/
    private String afterRentingPin;
    /*** @Fields afterRentingUserName:租后管家名称*/
    private String afterRentingName;
    /*** @Fields afterRentingDeptCode:租后管家部门*/
    private String afterRentingDeptCode;
    /*** @Fields afterRentingDeptName:租后管家部门名称*/
    private String afterRentingDeptName;
    /*** @Fields purchaseHouseList:托进合同房源信息*/
    private PurchaseHouseVO purchaseHouseVO;
    /*** @Fields purchaseHouseOwnerList:托进合同业主信息*/
    private PurchaseHouseOwnerVO purchaseHouseOwnerVO;
    /*** @Fields commonCostRuleList:托进合同收支规则*/
    private List<CommonCostRuleVO> commonCostRuleList;
    /*** @Fields commonBelongerList:合同责任人信息*/
    private List<CommonBelongerVO> commonBelongerList;
    /*** @Fields commonPicList:合同照片*/
    private List<CommonPicVO> commonPicList;
    /*** @Fields purchaseRentfreeList:免租期*/
    private List<PurchaseRentfreeVO> purchaseRentfreeList;
    /*** @Fields financeReceiptPayList:收支*/
    private List<FinanceReceiptPayVO> financeReceiptPayList;
    
    /***************构造*******************/
    public PurchaseBaseVO() {
        
    }
    /***************get/set*******************/
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getPurchaseCode() {
        return purchaseCode;
    }
    public PurchaseHouseVO getPurchaseHouseVO() {
        return purchaseHouseVO;
    }
    public void setPurchaseHouseVO(PurchaseHouseVO purchaseHouseVO) {
        this.purchaseHouseVO = purchaseHouseVO;
    }
    public List<PurchaseRentfreeVO> getPurchaseRentfreeList() {
        return purchaseRentfreeList;
    }
    public void setPurchaseRentfreeList(List<PurchaseRentfreeVO> purchaseRentfreeList) {
        this.purchaseRentfreeList = purchaseRentfreeList;
    }
    public List<FinanceReceiptPayVO> getFinanceReceiptPayList() {
        return financeReceiptPayList;
    }
    public void setFinanceReceiptPayList(List<FinanceReceiptPayVO> financeReceiptPayList) {
        this.financeReceiptPayList = financeReceiptPayList;
    }
    public PurchaseHouseOwnerVO getPurchaseHouseOwnerVO() {
        return purchaseHouseOwnerVO;
    }
    public void setPurchaseHouseOwnerVO(PurchaseHouseOwnerVO purchaseHouseOwnerVO) {
        this.purchaseHouseOwnerVO = purchaseHouseOwnerVO;
    }
    public List<CommonCostRuleVO> getCommonCostRuleList() {
        return commonCostRuleList;
    }
    public void setCommonCostRuleList(List<CommonCostRuleVO> commonCostRuleList) {
        this.commonCostRuleList = commonCostRuleList;
    }
    public List<CommonBelongerVO> getCommonBelongerList() {
        return commonBelongerList;
    }
    public void setCommonBelongerList(List<CommonBelongerVO> commonBelongerList) {
        this.commonBelongerList = commonBelongerList;
    }
    public List<CommonPicVO> getCommonPicList() {
        return commonPicList;
    }
    public void setCommonPicList(List<CommonPicVO> commonPicList) {
        this.commonPicList = commonPicList;
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
    public String getPayPeriodDate() {
        return payPeriodDate;
    }
    public void setPayPeriodDate(String payPeriodDate) {
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
    public String getHouseCode() {
        return houseCode;
    }
    public void setHouseCode(String houseCode) {
        this.houseCode = houseCode;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getOperatingName() {
        return operatingName;
    }
    public void setOperatingName(String operatingName) {
        this.operatingName = operatingName;
    }
    public String getOperatingDeptName() {
        return operatingDeptName;
    }
    public void setOperatingDeptName(String operatingDeptName) {
        this.operatingDeptName = operatingDeptName;
    }
    public String getServiceName() {
        return serviceName;
    }
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
    public String getServiceDeptName() {
        return serviceDeptName;
    }
    public void setServiceDeptName(String serviceDeptName) {
        this.serviceDeptName = serviceDeptName;
    }
    public String getRentMode() {
        return rentMode;
    }
    public void setRentMode(String rentMode) {
        this.rentMode = rentMode;
    }
    public String getOperatePin() {
        return operatePin;
    }
    public void setOperatePin(String operatePin) {
        this.operatePin = operatePin;
    }
    public String getAfterRentingPin() {
        return afterRentingPin;
    }
    public void setAfterRentingPin(String afterRentingPin) {
        this.afterRentingPin = afterRentingPin;
    }
    
    public String getAfterRentingName() {
        return afterRentingName;
    }
    public void setAfterRentingName(String afterRentingName) {
        this.afterRentingName = afterRentingName;
    }
    public String getAfterRentingDeptCode() {
        return afterRentingDeptCode;
    }
    public void setAfterRentingDeptCode(String afterRentingDeptCode) {
        this.afterRentingDeptCode = afterRentingDeptCode;
    }
    public String getAfterRentingDeptName() {
        return afterRentingDeptName;
    }
    public void setAfterRentingDeptName(String afterRentingDeptName) {
        this.afterRentingDeptName = afterRentingDeptName;
    }
}
