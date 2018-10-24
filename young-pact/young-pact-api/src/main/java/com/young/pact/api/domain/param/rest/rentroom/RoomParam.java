package com.young.pact.api.domain.param.rest.rentroom;

import java.io.Serializable;

/**
 * @description : 房间客源
 * @author : guoxiaopeng
 * @date : 2018年8月1日 上午9:26:46
 */
public class RoomParam implements Serializable{
    private static final long serialVersionUID = 1L;
    /*** @Fields roomCode:房间编码*/
    private String roomCode;
    /*** @Fields needCode:需求编码*/
    private String needCode;
    /*** @Fields type:类型 */
    private Integer type;
    /*** @Fields customerCode:客源编码*/
    private String customerCode;
    /** 业务编码(redis用)type=1 房间编码 type=2 托出合同编码 type=3 托出合同编码  **/
    private String serviceCode;
    /*** @Fields createName:创建人pin*/
    private String createName;
    /*** @Fields ip:ip地址*/
    private String ip;
    public RoomParam(){
        
    }

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }
    public String getNeedCode() {
        return needCode;
    }

    public void setNeedCode(String needCode) {
        this.needCode = needCode;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
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

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }
}
