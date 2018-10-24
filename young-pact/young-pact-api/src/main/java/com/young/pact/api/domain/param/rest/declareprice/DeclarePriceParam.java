package com.young.pact.api.domain.param.rest.declareprice;

import java.io.Serializable;

/**
 * @描述 : 申报价格
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月13日 下午3:37:21
 */
public class DeclarePriceParam  implements Serializable{

    /**
    * @Fields serialVersionUID : TODO( )
    */
    private static final long serialVersionUID = 1L;
    /*** @Fields id:主键id*/
    private Long id;
    /*** @Fields declareCode:申报编码*/
    private String declareCode;
    /*** @Fields sellPrice:托出租金*/
    private Double sellPrice;
    /*** @Fields buyPrice:拖进租金*/
    private Double buyPrice;
    /*** @Fields roomNumber:分间号*/
    private String roomNumber;
    public DeclarePriceParam() {
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
    public Double getSellPrice() {
        return sellPrice;
    }
    public void setSellPrice(Double sellPrice) {
        this.sellPrice = sellPrice;
    }
    public Double getBuyPrice() {
        return buyPrice;
    }
    public void setBuyPrice(Double buyPrice) {
        this.buyPrice = buyPrice;
    }
    public String getRoomNumber() {
        return roomNumber;
    }
    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }
}
