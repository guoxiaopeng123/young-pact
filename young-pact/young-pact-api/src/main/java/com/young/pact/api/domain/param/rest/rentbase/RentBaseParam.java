package com.young.pact.api.domain.param.rest.rentbase;

import java.io.Serializable;
import java.util.Date;

/**
 * @描述 : 托出合同基本对象
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月2日 上午10:04:26
 */
public class RentBaseParam  implements Serializable {

    /**
    * @Fields serialVersionUID : TODO( )
    */
    private static final long serialVersionUID = 1L;
    /** @Fields rentPactCode:托出合同编码**/
    private String rentPactCode;
    /** @Fields paperPactCode:纸版合同编号**/
    private String paperPactCode;
    /** @Fields startTime:开始时间**/
    private Date startTime;
    /** @Fields endTime:结束时间**/
    private Date endTime;
    /** @Fields pactTerm:合同期限**/
    private Integer pactTerm;
    /** @Fields price:月租金(元)**/
    private Double price;
    /** @Fields payWay:付款方式**/
    private String payWay;
    /** @Fields deposit:押金**/
    private Double deposit;
    /** @Fields payDays:提前付款**/
    private Integer payDays;
    /** @Fields firstPayTime:首次付租时间**/
    private Date firstPayTime;
    /** @Fields account:付款账户**/
    private String account;
    /** @Fields accountName:账户名称**/
    private String accountName;
    /** @Fields openBank:开户行**/
    private String openBank;
    /** @Fields bank:银行**/
    private String bank;
    /** @Fields tel:付款人电话**/
    private String tel;
    /** @Fields monthlyPayment:月付机构**/
    private String monthlyPayment;
    /** @Fields dealPin:签约管家pin**/
    private String dealPin;
    /** @Fields dealName:签约管家**/
    private String dealName;
    /** @Fields signingTime:签约时间**/
    private Date signingTime;
    /** @Fields remark:其他约定**/
    private String remark;
    /** @Fields createName:登录人**/
    private String createName;
    /** @Fields ip:ip地址**/
    private String ip;
    /** @Fields roomCode:房间编码**/
    private String roomCode;
    /** @Fields pageIndex:页数**/
    private int pageIndex;
    /** @Fields pageSize:每页显示个数**/
    private int pageSize;
    /** @Fields expireStartTime:到期开始时间**/
    private String expireStartTime;
    /** @Fields expireEndTime:到期结束时间**/
    private String expireEndTime;
    /** @Fields dueState:到期状态**/
    private Integer dueState;
    /** @Fields cancelState:解约状态**/
    private Integer cancelState;
    /** @Fields dealState:成交属性**/
    private Integer dealState;
    /** @Fields dealUserPin:签约管家**/
    private String dealUserPin;
    /** @Fields rentUserPin:租后管家**/
    private String rentedUserPin;
    /** @Fields dealDeptCode:签约管家部门**/
    private String dealDeptCode;
    /** @Fields rentDeptCode:租后管家部门**/
    private String rentDeptCode;
    /** @Fields state:审核状态**/
    private Integer state;
    /** @Fields keyword:合同编号/物业地址/客源编号/租客姓名/租客电话**/
    private String keyword;
    /** @Fields reason:审核驳回原因**/
    private String reason;
    /** @Fields delayDay:延期天数**/
    private Integer delayDay;
    /** @Fields modifiedName:修改人**/
    private String modifiedName;
    /** @Fields scope:修改人**/
    private Integer scope;
    public RentBaseParam(){
        
    }
    public String getPaperPactCode() {
        return paperPactCode;
    }
    public void setPaperPactCode(String paperPactCode) {
        this.paperPactCode = paperPactCode;
    }
    public Date getStartTime() {
        return startTime;
    }
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    public Date getEndTime() {
        return endTime;
    }
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
    public Integer getPactTerm() {
        return pactTerm;
    }
    public void setPactTerm(Integer pactTerm) {
        this.pactTerm = pactTerm;
    }
   
    public Double getDeposit() {
        return deposit;
    }
    public void setDeposit(Double deposit) {
        this.deposit = deposit;
    }
    public Integer getPayDays() {
        return payDays;
    }
    public void setPayDays(Integer payDays) {
        this.payDays = payDays;
    }
    public Date getFirstPayTime() {
        return firstPayTime;
    }
    public void setFirstPayTime(Date firstPayTime) {
        this.firstPayTime = firstPayTime;
    }
    public String getAccount() {
        return account;
    }
    public void setAccount(String account) {
        this.account = account;
    }
    public String getAccountName() {
        return accountName;
    }
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
    public String getOpenBank() {
        return openBank;
    }
    public void setOpenBank(String openBank) {
        this.openBank = openBank;
    }
    public String getMonthlyPayment() {
        return monthlyPayment;
    }
    public void setMonthlyPayment(String monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }
    public String getDealName() {
        return dealName;
    }
    public void setDealName(String dealName) {
        this.dealName = dealName;
    }
    public Date getSigningTime() {
        return signingTime;
    }
    public void setSigningTime(Date signingTime) {
        this.signingTime = signingTime;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getPayWay() {
        return payWay;
    }
    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }
    public String getDealPin() {
        return dealPin;
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
    public String getRoomCode() {
        return roomCode;
    }
    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public int getPageIndex() {
        return pageIndex;
    }
    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }
    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    public String getExpireStartTime() {
        return expireStartTime;
    }
    public void setExpireStartTime(String expireStartTime) {
        this.expireStartTime = expireStartTime;
    }
    public String getExpireEndTime() {
        return expireEndTime;
    }
    public void setExpireEndTime(String expireEndTime) {
        this.expireEndTime = expireEndTime;
    }
    public Integer getDueState() {
        return dueState;
    }
    public void setDueState(Integer dueState) {
        this.dueState = dueState;
    }
    public Integer getCancelState() {
        return cancelState;
    }
    public void setCancelState(Integer cancelState) {
        this.cancelState = cancelState;
    }
    public Integer getDealState() {
        return dealState;
    }
    public void setDealState(Integer dealState) {
        this.dealState = dealState;
    }
    public String getDealUserPin() {
        return dealUserPin;
    }
    public void setDealUserPin(String dealUserPin) {
        this.dealUserPin = dealUserPin;
    }
    public String getRentedUserPin() {
        return rentedUserPin;
    }
    public void setRentedUserPin(String rentedUserPin) {
        this.rentedUserPin = rentedUserPin;
    }
    public String getDealDeptCode() {
        return dealDeptCode;
    }
    public void setDealDeptCode(String dealDeptCode) {
        this.dealDeptCode = dealDeptCode;
    }
    public String getRentDeptCode() {
        return rentDeptCode;
    }
    public void setRentDeptCode(String rentDeptCode) {
        this.rentDeptCode = rentDeptCode;
    }
    public Integer getState() {
        return state;
    }
    public void setState(Integer state) {
        this.state = state;
    }
    public String getKeyword() {
        return keyword;
    }
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
    public String getRentPactCode() {
        return rentPactCode;
    }
    public void setRentPactCode(String rentPactCode) {
        this.rentPactCode = rentPactCode;
    }
    public String getReason() {
        return reason;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }
    public Integer getDelayDay() {
        return delayDay;
    }
    public void setDelayDay(Integer delayDay) {
        this.delayDay = delayDay;
    }
    public String getModifiedName() {
        return modifiedName;
    }
    public void setModifiedName(String modifiedName) {
        this.modifiedName = modifiedName;
    }
    public Integer getScope() {
        return scope;
    }
    public void setScope(Integer scope) {
        this.scope = scope;
    }
    public String getBank() {
        return bank;
    }
    public void setBank(String bank) {
        this.bank = bank;
    }
    public String getTel() {
        return tel;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }
    
}
