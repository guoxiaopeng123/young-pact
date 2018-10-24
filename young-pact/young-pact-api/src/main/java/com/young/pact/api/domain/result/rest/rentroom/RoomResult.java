package com.young.pact.api.domain.result.rest.rentroom;

import java.io.Serializable;
import java.util.Date;

/**
 * @description : 房间客源
 * @author : guoxiaopeng
 * @date : 2018年8月1日 上午9:36:41
 */
public class RoomResult implements Serializable{

    private static final long serialVersionUID = 1L;
    
    
    /*** @Fields id:主键id*/
    private Long id;
    /*** @Fields roomCode:房间编码*/
    private String roomCode;
    /*** @Fields roomNo:分间号*/
    private String roomNo;
    /*** @Fields shsCode:改造后房屋编码*/
    private String shsCode;
    /*** @Fields rentPactCode:合同编码*/
    private String rentPactCode;
    /*** @Fields houseCode:房源编码*/
    private String houseCode;
    /*** @Fields style:房型*/
    private String style;
    /*** @Fields roomAcreage:房间面积*/
    private String roomAcreage;
    /*** @Fields orientation:朝向*/
    private String orientation;
    /*** @Fields count:居住人数*/
    private Integer count;
    /*** @Fields floorPrice:底价*/
    private Double floorPrice;
    /*** @Fields price:基础价格*/
    private Double price;
    /*** @Fields monthlyPrice:月付价格*/
    private Double monthlyPrice;
    /*** @Fields quarterlyPrice:季付价格*/
    private Double quarterlyPrice;
    /*** @Fields channelMonthly:渠道月付价格*/
    private Double channelMonthly;
    /*** @Fields channelQuarterly:渠道季付价格*/
    private Double channelQuarterly;
    /*** @Fields propertyMoney:物业费*/
    private Double propertyMoney;
    /*** @Fields waterMoney:水费*/
    private Double waterMoney;
    /*** @Fields heatingMoney:采暖费*/
    private Double heatingMoney;
    /*** @Fields serviceMoney:服务费*/
    private Double serviceMoney;
    /*** @Fields cardDeposit:门禁卡押金*/
    private Double cardDeposit;
    /*** @Fields keyDeposit:钥匙押金*/
    private Double keyDeposit;
    /*** @Fields managementMoney:管理费*/
    private Double managementMoney;
    /*** @Fields feature:房间特色json*/
    private String feature;
    /*** @Fields roomDeploy:房间配置json*/
    private String roomDeploy;
    /*** @Fields publicDeploy:公共区域配置json*/
    private String publicDeploy;
    /*** @Fields rentWay:租赁方式*/
    private String rentWay;
    /*** @Fields stockState:库存状态*/
    private Integer stockState;
    /*** @Fields saleState:销售状态*/
    private Integer saleState;
    /*** @Fields decorationState:装修状态*/
    private Integer decorationState;
    /*** @Fields isPurchase:是否在托进合同期*/
    private Integer isPurchase;
    /*** @Fields rentTime:预计可租时间*/
    private Date rentTime;
    /*** @Fields rentTime:总空置数*/
    private String vacantDays;
    /*** @Fields vacantCount:*/
    private String vacantCount;
    /*** @Fields floor:楼层*/
    private String floor;
    /*** @Fields allFloor:总楼层*/
    private String allFloor;
    /*** @Fields address:物业地址*/
    private String address;
    /**@Fields customerCode: 客源编码 **/
    private String customerCode;
    /**@Fields demandCode:需求编码 **/
    private String demandCode;
    /**@Fields internetFee:网费**/
    private Double internetFee;
    public RoomResult(){
        
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
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getRoomNo() {
        return roomNo;
    }
    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }
    public String getShsCode() {
        return shsCode;
    }
    public void setShsCode(String shsCode) {
        this.shsCode = shsCode;
    }
    public String getRentPactCode() {
        return rentPactCode;
    }
    public void setRentPactCode(String rentPactCode) {
        this.rentPactCode = rentPactCode;
    }
    public String getHouseCode() {
        return houseCode;
    }
    public void setHouseCode(String houseCode) {
        this.houseCode = houseCode;
    }
    public String getStyle() {
        return style;
    }
    public void setStyle(String style) {
        this.style = style;
    }
    public String getRoomAcreage() {
        return roomAcreage;
    }
    public void setRoomAcreage(String roomAcreage) {
        this.roomAcreage = roomAcreage;
    }
    public String getOrientation() {
        return orientation;
    }
    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }
    public Integer getCount() {
        return count;
    }
    public void setCount(Integer count) {
        this.count = count;
    }
    public Double getFloorPrice() {
        return floorPrice;
    }
    public void setFloorPrice(Double floorPrice) {
        this.floorPrice = floorPrice;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public Double getMonthlyPrice() {
        return monthlyPrice;
    }
    public void setMonthlyPrice(Double monthlyPrice) {
        this.monthlyPrice = monthlyPrice;
    }
    public Double getQuarterlyPrice() {
        return quarterlyPrice;
    }
    public void setQuarterlyPrice(Double quarterlyPrice) {
        this.quarterlyPrice = quarterlyPrice;
    }
    public Double getChannelMonthly() {
        return channelMonthly;
    }
    public void setChannelMonthly(Double channelMonthly) {
        this.channelMonthly = channelMonthly;
    }
    public Double getChannelQuarterly() {
        return channelQuarterly;
    }
    public void setChannelQuarterly(Double channelQuarterly) {
        this.channelQuarterly = channelQuarterly;
    }
    public Double getPropertyMoney() {
        return propertyMoney;
    }
    public void setPropertyMoney(Double propertyMoney) {
        this.propertyMoney = propertyMoney;
    }
    public Double getWaterMoney() {
        return waterMoney;
    }
    public void setWaterMoney(Double waterMoney) {
        this.waterMoney = waterMoney;
    }
    public Double getHeatingMoney() {
        return heatingMoney;
    }
    public void setHeatingMoney(Double heatingMoney) {
        this.heatingMoney = heatingMoney;
    }
    public Double getServiceMoney() {
        return serviceMoney;
    }
    public void setServiceMoney(Double serviceMoney) {
        this.serviceMoney = serviceMoney;
    }
    public Double getCardDeposit() {
        return cardDeposit;
    }
    public void setCardDeposit(Double cardDeposit) {
        this.cardDeposit = cardDeposit;
    }
    public Double getKeyDeposit() {
        return keyDeposit;
    }
    public void setKeyDeposit(Double keyDeposit) {
        this.keyDeposit = keyDeposit;
    }
    public Double getManagementMoney() {
        return managementMoney;
    }
    public void setManagementMoney(Double managementMoney) {
        this.managementMoney = managementMoney;
    }
    public String getFeature() {
        return feature;
    }
    public void setFeature(String feature) {
        this.feature = feature;
    }
    public String getRoomDeploy() {
        return roomDeploy;
    }
    public void setRoomDeploy(String roomDeploy) {
        this.roomDeploy = roomDeploy;
    }
    public String getPublicDeploy() {
        return publicDeploy;
    }
    public void setPublicDeploy(String publicDeploy) {
        this.publicDeploy = publicDeploy;
    }
    public String getRentWay() {
        return rentWay;
    }
    public void setRentWay(String rentWay) {
        this.rentWay = rentWay;
    }
    public Integer getStockState() {
        return stockState;
    }
    public void setStockState(Integer stockState) {
        this.stockState = stockState;
    }
    public Integer getSaleState() {
        return saleState;
    }
    public void setSaleState(Integer saleState) {
        this.saleState = saleState;
    }
    public Integer getDecorationState() {
        return decorationState;
    }
    public void setDecorationState(Integer decorationState) {
        this.decorationState = decorationState;
    }
    public Integer getIsPurchase() {
        return isPurchase;
    }
    public void setIsPurchase(Integer isPurchase) {
        this.isPurchase = isPurchase;
    }
    public Date getRentTime() {
        return rentTime;
    }
    public void setRentTime(Date rentTime) {
        this.rentTime = rentTime;
    }
    public String getVacantDays() {
        return vacantDays;
    }
    public void setVacantDays(String vacantDays) {
        this.vacantDays = vacantDays;
    }
    public String getVacantCount() {
        return vacantCount;
    }
    public void setVacantCount(String vacantCount) {
        this.vacantCount = vacantCount;
    }
    public String getFloor() {
        return floor;
    }
    public void setFloor(String floor) {
        this.floor = floor;
    }
    public String getAllFloor() {
        return allFloor;
    }
    public void setAllFloor(String allFloor) {
        this.allFloor = allFloor;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getDemandCode() {
        return demandCode;
    }
    public void setDemandCode(String demandCode) {
        this.demandCode = demandCode;
    }
    public Double getInternetFee() {
        return internetFee;
    }
    public void setInternetFee(Double internetFee) {
        this.internetFee = internetFee;
    }
    
}
