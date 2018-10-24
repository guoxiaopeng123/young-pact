package com.young.pact.api.domain.param.rest.financeamortize;

import java.io.Serializable;
import java.util.Date;
/**
 * 
* @ClassName: FinanceAmortizeDO
* @Description: TODO( 摊销)
* @author HeZeMin
* @date 2018年8月1日 下午8:31:28
*
 */
public class FinanceAmortizeQueryParam implements Serializable {
    private static final long serialVersionUID = 1L;
    /*** @Fields id:主键id*/
    private Long id;
    /*** @Fields amortizeCode:摊销编码*/
    private String amortizeCode;
    /*** @Fields pactCode:合同编码*/
    private String pactCode;
    /*** @Fields pactType:合同类型*/
    private String pactType;
    /*** @Fields belongDeptCode:归属部门编码*/
    private String belongDeptCode;
    /*** @Fields belongDeptName:归属部门名称*/
    private String belongDeptName;
    /*** @Fields address:物业地址*/
    private String address;
    /*** @Fields amortizeRentTime:摊销日期*/
    private Date amortizeRentTime;
    /*** @Fields startTime:摊销账期开始时间*/
    private Date startTime;
    /*** @Fields endTime:摊销账期结束时间*/
    private Date endTime;
    /*** @Fields payAmount:自然月金额*/
    private Double payAmount;
    /*** @Fields amortizeAmount:应摊金额*/
    private Double amortizeAmount;
    /*** @Fields reduceAmount:减少金额*/
    private Double reduceAmount;
    /*** @Fields actualAmount:实摊金额*/
    private Double actualAmount;
    /*** @Fields amortizeState:摊销状态(0:未摊 1:已摊)*/
    private Integer amortizeState;
    /*** @Fields isDelete:删除状态(0:未删除 1:已删除)*/
    private Integer isDelete;
    /*** @Fields costType:收支类型(0 应收  2 应支 )*/
    private Integer costType;
    /*** @Fields amortizeRentTime:摊销日期min*/
    private String amortizeRentTimeMin;
    /*** @Fields amortizeRentTime:摊销日期max*/
    private String amortizeRentTimeMax;
    /*** @Fields keyword:关键词*/
    private String keyword;
    /*** @Fields createName:创建人*/
    private String createName;
    /*** @Fields modifiedName:修改人*/
    private String modifiedName;
    /*** @Fields pageIndex:第几页*/
    private int pageIndex = 1;
    /*** @Fields pageSize:每页个数*/
    private int pageSize = 10;
    
    
    
    public FinanceAmortizeQueryParam() {
    }
    
    
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getAmortizeCode() {
        return amortizeCode;
    }
    public void setAmortizeCode(String amortizeCode) {
        this.amortizeCode = amortizeCode;
    }
    public String getKeyword() {
        return keyword;
    }



    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }



    public String getPactCode() {
        return pactCode;
    }
    public String getAmortizeRentTimeMin() {
        return amortizeRentTimeMin;
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



    public int getPageIndex() {
        return pageIndex;
    }



    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }



    public int getPageSize() {
        return pageSize;
    }



    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }



    public void setAmortizeRentTimeMin(String amortizeRentTimeMin) {
        this.amortizeRentTimeMin = amortizeRentTimeMin;
    }



    public String getAmortizeRentTimeMax() {
        return amortizeRentTimeMax;
    }



    public void setAmortizeRentTimeMax(String amortizeRentTimeMax) {
        this.amortizeRentTimeMax = amortizeRentTimeMax;
    }



    public void setPactCode(String pactCode) {
        this.pactCode = pactCode;
    }
    public Integer getCostType() {
        return costType;
    }



    public void setCostType(Integer costType) {
        this.costType = costType;
    }



    public String getPactType() {
        return pactType;
    }
    public void setPactType(String pactType) {
        this.pactType = pactType;
    }
    public String getBelongDeptCode() {
        return belongDeptCode;
    }
    public void setBelongDeptCode(String belongDeptCode) {
        this.belongDeptCode = belongDeptCode;
    }
    public String getBelongDeptName() {
        return belongDeptName;
    }
    public void setBelongDeptName(String belongDeptName) {
        this.belongDeptName = belongDeptName;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public Date getAmortizeRentTime() {
        return amortizeRentTime;
    }
    public void setAmortizeRentTime(Date amortizeRentTime) {
        this.amortizeRentTime = amortizeRentTime;
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
    public Double getPayAmount() {
        return payAmount;
    }
    public void setPayAmount(Double payAmount) {
        this.payAmount = payAmount;
    }
    public Double getAmortizeAmount() {
        return amortizeAmount;
    }
    public void setAmortizeAmount(Double amortizeAmount) {
        this.amortizeAmount = amortizeAmount;
    }
    public Double getReduceAmount() {
        return reduceAmount;
    }
    public void setReduceAmount(Double reduceAmount) {
        this.reduceAmount = reduceAmount;
    }
    public Double getActualAmount() {
        return actualAmount;
    }
    public void setActualAmount(Double actualAmount) {
        this.actualAmount = actualAmount;
    }
    public Integer getAmortizeState() {
        return amortizeState;
    }
    public void setAmortizeState(Integer amortizeState) {
        this.amortizeState = amortizeState;
    }
    public Integer getIsDelete() {
        return isDelete;
    }
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}
