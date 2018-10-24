package com.young.pact.domain.commonmeterread;

import java.util.Date;
import java.util.List;

import com.young.common.domain.BaseDomain;

/**
 * @描述 : 合同物业交接抄表信息
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月2日 下午4:37:34
 */
public class CommonMeterReadVO extends BaseDomain{
    
    /**
    * @Fields serialVersionUID : TODO( )
    */
    private static final long serialVersionUID = 1L;
    /** @Fields id:唯一标识**/
    private Long id;
    /** @Fields pactCode:合同编码**/
    private String pactCode;
    /** @Fields pactType:合同类型**/
    private String pactType;
    /** @Fields tableNumber:表号**/
    private String tableNumber;
    /** @Fields tableItem:表项**/
    private String tableItem;
    /** @Fields type:类型**/
    private Integer type;
    /** @Fields number:数值**/
    private Integer number;
    /** @Fields date:抄表时间**/
    private Date date;
    /** @Fields remark:备注**/
    private String remark;
    /** @Fields id:抄表照片(集合)**/
    private List<CommonMeterReadPicVO> commonMeterReadPicList;
    
    public CommonMeterReadVO(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<CommonMeterReadPicVO> getCommonMeterReadPicList() {
        return commonMeterReadPicList;
    }

    public void setCommonMeterReadPicList(List<CommonMeterReadPicVO> commonMeterReadPicList) {
        this.commonMeterReadPicList = commonMeterReadPicList;
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

    public void setPactType(String pactType) {
        this.pactType = pactType;
    }
}
