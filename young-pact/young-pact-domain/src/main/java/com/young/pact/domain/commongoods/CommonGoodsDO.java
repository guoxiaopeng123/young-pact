package com.young.pact.domain.commongoods;

import java.util.List;

import com.young.common.domain.BaseDomain;
/**
 * 
* @ClassName: CommonGoodsDO
* @Description: TODO( 物业物品)
* @author HeZeMin
* @date 2018年8月1日 下午9:14:17
*
 */
public class CommonGoodsDO extends BaseDomain {
    private static final long serialVersionUID = 1L;
    /*** @Fields id:主键id*/
    private Long id;
    /*** @Fields pactCode:合同编码*/
    private String pactCode;
    /*** @Fields pactType:合同类型*/
    private String pactType;
    /*** @Fields goodsName:物品名称*/
    private String goodsName;
    /*** @Fields unit:单位*/
    private String unit;
    /*** @Fields number:数量*/
    private Integer number;
    /*** @Fields goodsStatus:物品状况*/
    private String goodsStatus;
    /*** @Fields remark:备注*/
    private String remark;
    /*** @Fields commonGoodsPicList:物品图片集合*/
    private List<CommonGoodsPicDO> commonGoodsPicList;
    
    public CommonGoodsDO() {
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
    public List<CommonGoodsPicDO> getCommonGoodsPicList() {
        return commonGoodsPicList;
    }


    public void setCommonGoodsPicList(List<CommonGoodsPicDO> commonGoodsPicList) {
        this.commonGoodsPicList = commonGoodsPicList;
    }


    public String getPactType() {
        return pactType;
    }
    public void setPactType(String pactType) {
        this.pactType = pactType;
    }
    public String getGoodsName() {
        return goodsName;
    }
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }
    public String getUnit() {
        return unit;
    }
    public void setUnit(String unit) {
        this.unit = unit;
    }
    public Integer getNumber() {
        return number;
    }
    public void setNumber(Integer number) {
        this.number = number;
    }
    public String getGoodsStatus() {
        return goodsStatus;
    }
    public void setGoodsStatus(String goodsStatus) {
        this.goodsStatus = goodsStatus;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }

}
