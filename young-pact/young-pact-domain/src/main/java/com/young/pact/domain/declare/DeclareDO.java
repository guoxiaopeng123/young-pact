package com.young.pact.domain.declare;

import com.young.common.domain.BaseDomain;

/**
 * @描述 : 申报
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月13日 下午1:37:07
 */
public class DeclareDO extends BaseDomain {

    /**
    * @Fields serialVersionUID : TODO( )
    */
    private static final long serialVersionUID = 1L;

    /*** @Fields id:主键id*/
    private Long id;
    /*** @Fields declareCode:申报编码*/
    private String declareCode;
    /*** @Fields surveyCode:实勘编码*/
    private String surveyCode;
    /*** @Fields surveyCode:房源编号*/
    private String houseCode;
    /*** @Fields address:物业地址*/
    private String address;
    /*** @Fields decoration:装修情况*/
    private String decoration;
    /*** @Fields configure:配置情况*/
    private String configure;
    /*** @Fields rentPrice:拖进租金*/
    private String rentPrice;
    /*** @Fields period:合同期限*/
    private String period;
    /*** @Fields measurePeriod:测价合同期限*/
    private String measurePeriod;
    /*** @Fields freePeriod:免租期*/
    private String freePeriod;
    /*** @Fields cycle:付款周期*/
    private String cycle;
    /*** @Fields roomNumber:拖进间数*/
    private Integer roomNumber;
    /*** @Fields rentMode:租赁方式*/
    private String rentMode;
    /*** @Fields decorationCost:装修费用*/
    private Double decorationCost;
    /*** @Fields interest:支付利息*/
    private Double interest;
    /*** @Fields manageCost:管理费用*/
    private Double manageCost;
    /*** @Fields interestCost:单间单月利息成本*/
    private Double interestCost;
    /*** @Fields income:合同期收入*/
    private Double income;
    /*** @Fields spending:合同期支出*/
    private Double spending;
    /*** @Fields grossProfit:合同期毛利润*/
    private Double grossProfit;
    /*** @Fields costCycle:精装成本回收周期*/
    private String costCycle;
    /*** @Fields maori:单间毛利*/
    private Double maori;
    /*** @Fields state:申报状态*/
    private Integer state;
    /*** @Fields type:申报类型*/
    private Integer type;
    /*** @Fields userPin:申报人pin*/
    private String userPin;
    /*** @Fields deptName:申报人部门*/
    private String deptName;
    /*** @Fields userName:申报人姓名*/
    private String userName;
    /*** @Fields surveyInfo:申报实勘基本信息json*/
    private String surveyInfo;
    /*** @Fields surveyInfo:申报实勘物品json*/
    private String surveyGoods;
    /*** @Fields surveyGoodsPic:申报实勘物品图片json*/
    private String surveyGoodsPic;
    /*** @Fields surveyPic:申报实勘图片json*/
    private String surveyPic;
    /*** @Fields monthPayInterest:月付租金利息*/
    private Double monthPayInterest;
    /*** @Fields decoratePeriod:装修周期*/
    private Double decoratePeriod;
    /*** @Fields rentPeriod:托出周期*/
    private Double rentPeriod;
    /*** @Fields vacantCost:空置成本*/
    private Double vacantCost;
    /*** @Fields deptCode:申报人部门编码*/
    private String deptCode;
    public DeclareDO() {
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getDeclareCode() {
        return declareCode;
    }
    public void setDeclareCode(String declareCode) {
        this.declareCode = declareCode;
    }
    public String getSurveyCode() {
        return surveyCode;
    }
    public void setSurveyCode(String surveyCode) {
        this.surveyCode = surveyCode;
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
    public String getDecoration() {
        return decoration;
    }
    public void setDecoration(String decoration) {
        this.decoration = decoration;
    }
    public String getConfigure() {
        return configure;
    }
    public void setConfigure(String configure) {
        this.configure = configure;
    }
    public String getRentPrice() {
        return rentPrice;
    }
    public void setRentPrice(String rentPrice) {
        this.rentPrice = rentPrice;
    }
    public String getPeriod() {
        return period;
    }
    public void setPeriod(String period) {
        this.period = period;
    }
    public String getMeasurePeriod() {
        return measurePeriod;
    }
    public void setMeasurePeriod(String measurePeriod) {
        this.measurePeriod = measurePeriod;
    }
    public String getFreePeriod() {
        return freePeriod;
    }
    public void setFreePeriod(String freePeriod) {
        this.freePeriod = freePeriod;
    }
    public String getCycle() {
        return cycle;
    }
    public void setCycle(String cycle) {
        this.cycle = cycle;
    }
    public Integer getRoomNumber() {
        return roomNumber;
    }
    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }
    public String getRentMode() {
        return rentMode;
    }
    public void setRentMode(String rentMode) {
        this.rentMode = rentMode;
    }
    public Double getDecorationCost() {
        return decorationCost;
    }
    public void setDecorationCost(Double decorationCost) {
        this.decorationCost = decorationCost;
    }
    public Double getInterest() {
        return interest;
    }
    public void setInterest(Double interest) {
        this.interest = interest;
    }
    public Double getManageCost() {
        return manageCost;
    }
    public void setManageCost(Double manageCost) {
        this.manageCost = manageCost;
    }
    public Double getInterestCost() {
        return interestCost;
    }
    public void setInterestCost(Double interestCost) {
        this.interestCost = interestCost;
    }
    public Double getIncome() {
        return income;
    }
    public void setIncome(Double income) {
        this.income = income;
    }
    public Double getSpending() {
        return spending;
    }
    public void setSpending(Double spending) {
        this.spending = spending;
    }
    public Double getGrossProfit() {
        return grossProfit;
    }
    public void setGrossProfit(Double grossProfit) {
        this.grossProfit = grossProfit;
    }
    public String getCostCycle() {
        return costCycle;
    }
    public void setCostCycle(String costCycle) {
        this.costCycle = costCycle;
    }
    public Double getMaori() {
        return maori;
    }
    public void setMaori(Double maori) {
        this.maori = maori;
    }
    public Integer getState() {
        return state;
    }
    public void setState(Integer state) {
        this.state = state;
    }
    public Integer getType() {
        return type;
    }
    public void setType(Integer type) {
        this.type = type;
    }
    public String getUserPin() {
        return userPin;
    }
    public void setUserPin(String userPin) {
        this.userPin = userPin;
    }
    public String getDeptName() {
        return deptName;
    }
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getSurveyInfo() {
        return surveyInfo;
    }
    public void setSurveyInfo(String surveyInfo) {
        this.surveyInfo = surveyInfo;
    }
    public String getSurveyGoods() {
        return surveyGoods;
    }
    public void setSurveyGoods(String surveyGoods) {
        this.surveyGoods = surveyGoods;
    }
    public String getSurveyGoodsPic() {
        return surveyGoodsPic;
    }
    public void setSurveyGoodsPic(String surveyGoodsPic) {
        this.surveyGoodsPic = surveyGoodsPic;
    }
    public String getSurveyPic() {
        return surveyPic;
    }
    public void setSurveyPic(String surveyPic) {
        this.surveyPic = surveyPic;
    }
    public Double getMonthPayInterest() {
        return monthPayInterest;
    }
    public void setMonthPayInterest(Double monthPayInterest) {
        this.monthPayInterest = monthPayInterest;
    }
    public Double getDecoratePeriod() {
        return decoratePeriod;
    }
    public void setDecoratePeriod(Double decoratePeriod) {
        this.decoratePeriod = decoratePeriod;
    }
    public Double getRentPeriod() {
        return rentPeriod;
    }
    public void setRentPeriod(Double rentPeriod) {
        this.rentPeriod = rentPeriod;
    }
    public Double getVacantCost() {
        return vacantCost;
    }
    public void setVacantCost(Double vacantCost) {
        this.vacantCost = vacantCost;
    }
    public String getDeptCode() {
        return deptCode;
    }
    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }
   
}


