package com.young.pact.api.domain.result.rest.commontradingrecord;

import java.io.Serializable;
import java.util.Date;

/**
 * @描述 : 公共交易-交易记录
 * @创建者 : guoxiaopeng
 * @创建时间 : 2018年8月25日 上午8:47:47
 */
public class CommonTradingRecordResult implements Serializable{
    /**
    * @Fields serialVersionUID : TODO( )
    */
    private static final long serialVersionUID = 1L;
    /*** @Fields id:主键id*/
    private Long id;
    /*** @Fields roomCode:房间编码 */
    private String roomCode;      
    /*** @Fields pactCode: 合同编码  */
    private String pactCode;    
    /*** @Fields type: 合同类型  */
    private String type;        
    /*** @Fields price: 合同价格 */
    private Double price;       
    /*** @Fields date: 签约时间 */
    private Date date;        
    /*** @Fields userName: 签约管家 */
    private String userName;       
    /*** @Fields deptName:  签约部门*/
    private String deptName;   
    public CommonTradingRecordResult(){}
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getRoomCode() {
        return roomCode;
    }
    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }
    public String getPactCode() {
        return pactCode;
    }
    public void setPactCode(String pactCode) {
        this.pactCode = pactCode;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getDeptName() {
        return deptName;
    }
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
}
