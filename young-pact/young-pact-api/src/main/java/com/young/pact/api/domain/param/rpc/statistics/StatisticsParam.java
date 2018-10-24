package com.young.pact.api.domain.param.rpc.statistics;

import java.io.Serializable;

/**
 * @描述 : 首页统计
 * @创建者 : GuoXiaoPeng
 * @创建时间 : 2018年9月26日 上午10:19:26
 */
public class StatisticsParam implements Serializable{

    /**
    * @Fields serialVersionUID : TODO( )
    */
    private static final long serialVersionUID = 1L;
    /*** @Fields purchaseCode:托进合同编码*/
    private String purchaseCode;
    /*** @Fields rentWay:租赁方式，合租，整租*/
    private String rentWay;
    /*** @Fields roomCount:房间数*/
    private Integer roomCount;
    /*** @Fields afterBelogerPin:租后管家PIN*/
    private String afterBelogerPin;
    /*** @Fields afterBelogerUserName:租后管家名称*/
    private String afterBelogerUserName;
    /*** @Fields afterBelogerDeptCode:租后管家部门编码*/
    private String afterBelogerDeptCode;
    /*** @Fields afterBelogerDeptName:租后管家部门名称*/
    private String afterBelogerDeptName;
    public StatisticsParam(){
        
    }
    public String getPurchaseCode() {
        return purchaseCode;
    }
    public void setPurchaseCode(String purchaseCode) {
        this.purchaseCode = purchaseCode;
    }
    public String getRentWay() {
        return rentWay;
    }
    public void setRentWay(String rentWay) {
        this.rentWay = rentWay;
    }
    public Integer getRoomCount() {
        return roomCount;
    }
    public void setRoomCount(Integer roomCount) {
        this.roomCount = roomCount;
    }
    public String getAfterBelogerPin() {
        return afterBelogerPin;
    }
    public void setAfterBelogerPin(String afterBelogerPin) {
        this.afterBelogerPin = afterBelogerPin;
    }
    public String getAfterBelogerUserName() {
        return afterBelogerUserName;
    }
    public void setAfterBelogerUserName(String afterBelogerUserName) {
        this.afterBelogerUserName = afterBelogerUserName;
    }
    public String getAfterBelogerDeptCode() {
        return afterBelogerDeptCode;
    }
    public void setAfterBelogerDeptCode(String afterBelogerDeptCode) {
        this.afterBelogerDeptCode = afterBelogerDeptCode;
    }
    public String getAfterBelogerDeptName() {
        return afterBelogerDeptName;
    }
    public void setAfterBelogerDeptName(String afterBelogerDeptName) {
        this.afterBelogerDeptName = afterBelogerDeptName;
    }

}
