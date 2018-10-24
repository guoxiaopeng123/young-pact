package com.young.pact.api.domain.param.rest.commonmeterread;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
/**
 * 
* @ClassName: CommonMeterReadParam
* @Description: TODO( 物业 抄表)
* @author HeZeMin
* @date 2018年8月2日 下午2:52:19
*
 */
public class CommonMeterReadParam implements Serializable {
    private static final long serialVersionUID = 1L;
    /*** @Fields id:主键id*/
    private Long id;
    /*** @Fields pactCode:合同编码*/
    private String pactCode;
    /*** @Fields pactType:合同类型*/
    private String pactType;
    /*** @Fields tableNumber:表号*/
    private String tableNumber;
    /*** @Fields tableItem:表项*/
    private String tableItem;
    /*** @Fields type:类型*/
    private Integer type;
    /*** @Fields number:数值*/
    private Integer number;
    /*** @Fields date:抄表时间*/
    private Date date;
    /*** @Fields remark:备注*/
    private String remark;
    /*** @Fields commonMeterReadPicList:抄表照片集合*/
    private List<CommonMeterReadPicParam> commonMeterReadPicList;
    
    /*** @Fields createName:创建人*/
    private String createName;
    /*** @Fields modifiedName:修改人*/
    private String modifiedName;
    /*** @Fields ip:ip地址*/
    private String ip;
    
    public CommonMeterReadParam() {
    }
    
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getPactCode() {
        return pactCode;
    }
    public void setPactCode(String pactCode) {
        this.pactCode = pactCode;
    }
    public String getPactType() {
        return pactType;
    }
    public List<CommonMeterReadPicParam> getCommonMeterReadPicList() {
        return commonMeterReadPicList;
    }


    public void setCommonMeterReadPicList(List<CommonMeterReadPicParam> commonMeterReadPicList) {
        this.commonMeterReadPicList = commonMeterReadPicList;
    }


    public void setPactType(String pactType) {
        this.pactType = pactType;
    }
    public String getTableNumber() {
        return tableNumber;
    }
    public void setTableNumber(String tableNumber) {
        this.tableNumber = tableNumber;
    }
    public String getTableItem() {
        return tableItem;
    }
    public void setTableItem(String tableItem) {
        this.tableItem = tableItem;
    }
    public Integer getType() {
        return type;
    }
    public void setType(Integer type) {
        this.type = type;
    }
    public Integer getNumber() {
        return number;
    }
    public void setNumber(Integer number) {
        this.number = number;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }


    public String getCreateName() {
        return createName;
    }


    public void setCreateName(String createName) {
        this.createName = createName;
    }


    public String getModifiedName() {
        return modifiedName;
    }


    public void setModifiedName(String modifiedName) {
        this.modifiedName = modifiedName;
    }


    public String getIp() {
        return ip;
    }


    public void setIp(String ip) {
        this.ip = ip;
    }
}
