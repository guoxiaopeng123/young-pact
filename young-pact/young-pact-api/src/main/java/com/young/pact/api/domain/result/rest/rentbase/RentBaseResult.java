package com.young.pact.api.domain.result.rest.rentbase;

import java.io.Serializable;
import java.util.Date;

/**
 * @描述 : 托出合同基本对象
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月2日 上午10:04:26
 */
public class RentBaseResult  implements Serializable {

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
    /** @Fields bank:银行**/
    private String bank;
    /** @Fields tel:付款人电话**/
    private String tel;
    private String openBank;
    /** @Fields monthlyPayment:月付机构**/
    private String monthlyPayment;
    /** @Fields dealName:签约管家pin**/
    private String dealPin;
    /** @Fields dealName:签约管家**/
    private String dealName;
    /** @Fields signingTime:签约时间**/
    private Date signingTime;
    /** @Fields remark:其他约定**/
    private String remark;
    
    /** @Fields dealDeptName:签约管家部门**/
    private String dealDeptName;
    /** @Fields rentedDeptName:租后管家部门**/
    private String rentedDeptName;
    /** @Fields dealUserName:签约管家**/
    private String dealUserName;
    /** @Fields rentedUserName:租后管家**/
    private String rentedUserName;
    /** @Fields propertyAddress:物业地址**/
    private String propertyAddress;
    /** @Fields roomCode:房间编码**/
    private String roomCode;
    /** @Fields customerCode:客源编码**/
    private String customerCode;
    /** @Fields create:创建时间**/
    private Date create;
    /** @Fields dealState:成交属性 0新签 1续签**/
    private Integer dealState;
    /** @Fields dueState:到期状态**/
    private Integer dueState;
    /** @Fields cancelState:解约状态 0 未解约1到期解约 2 违约解约 3调房解约 4转租解约 **/
    private Integer cancelState;
    /** @Fields state:审核状态  0草稿状态 1待审核 2已通过 3已驳回 **/
    private Integer state;
    /** @Fields custody:付1 **/
    private String custody;
    /** @Fields pay:押0 **/
    private String pay;
    /** @Fields renterCode:租客编码 **/
    private String renterCode;
    /** @Fields renterName:租客姓名 **/
    private String renterName;
    /** @Fields renterTel:租客电话 **/
    private String renterTel;
    /** @Fields customerName: 客源姓名**/
    private String customerName;
    /** @Fields customerTel:客源联系电话**/
    private String customerTel;
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
    
    public String getPayWay() {
        return payWay;
    }
    public void setPayWay(String payWay) {
        this.payWay = payWay;
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
    public String getDealPin() {
        return dealPin;
    }
    public void setDealPin(String dealPin) {
        this.dealPin = dealPin;
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
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public String getDealDeptName() {
        return dealDeptName;
    }
    public void setDealDeptName(String dealDeptName) {
        this.dealDeptName = dealDeptName;
    }
    public String getRentedDeptName() {
        return rentedDeptName;
    }
    public void setRentedDeptName(String rentedDeptName) {
        this.rentedDeptName = rentedDeptName;
    }
    public String getDealUserName() {
        return dealUserName;
    }
    public void setDealUserName(String dealUserName) {
        this.dealUserName = dealUserName;
    }
    public String getRentedUserName() {
        return rentedUserName;
    }
    public void setRentedUserName(String rentedUserName) {
        this.rentedUserName = rentedUserName;
    }
    public String getPropertyAddress() {
        return propertyAddress;
    }
    public void setPropertyAddress(String propertyAddress) {
        this.propertyAddress = propertyAddress;
    }
    public String getRoomCode() {
        return roomCode;
    }
    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }
    public String getCustomerCode() {
        return customerCode;
    }
    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }
    public Date getCreate() {
        return create;
    }
    public void setCreate(Date create) {
        this.create = create;
    }
    public String getRentPactCode() {
        return rentPactCode;
    }
    public void setRentPactCode(String rentPactCode) {
        this.rentPactCode = rentPactCode;
    }
    public Integer getDealState() {
        return dealState;
    }
    public void setDealState(Integer dealState) {
        this.dealState = dealState;
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
    public Integer getState() {
        return state;
    }
    public void setState(Integer state) {
        this.state = state;
    }
    public String getCustody() {
        return custody;
    }
    public void setCustody(String custody) {
        this.custody = custody;
    }
    public String getPay() {
        return pay;
    }
    public void setPay(String pay) {
        this.pay = pay;
    }
    public String getRenterCode() {
        return renterCode;
    }
    public void setRenterCode(String renterCode) {
        this.renterCode = renterCode;
    }
    public String getRenterName() {
        return renterName;
    }
    public void setRenterName(String renterName) {
        this.renterName = renterName;
    }
    public String getRenterTel() {
        return renterTel;
    }
    public void setRenterTel(String renterTel) {
        this.renterTel = renterTel;
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
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public String getCustomerTel() {
        return customerTel;
    }
    public void setCustomerTel(String customerTel) {
        this.customerTel = customerTel;
    }
    
}
