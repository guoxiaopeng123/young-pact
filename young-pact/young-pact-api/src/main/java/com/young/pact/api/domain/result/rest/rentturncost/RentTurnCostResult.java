package com.young.pact.api.domain.result.rest.rentturncost;

import java.io.Serializable;
import java.util.Date;

/**
 * @描述 : 转让费用明细
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月7日 上午11:28:51
 */
public class RentTurnCostResult implements Serializable{

    /**
    * @Fields serialVersionUID : TODO( )
    */
    private static final long serialVersionUID = 1L;
    /*** @Fields id: 主键 */
    private Long id;
    /*** @Fields reletCode:  转租协议编码*/
    private String reletCode;
    /*** @Fields customerName:转让方  */
    private String customerName;
    /*** @Fields type: 费用类型 */
    private String type;
    /*** @Fields price: 费用金额 */
    private String price;
    /*** @Fields startDate: 开始时间 */
    private Date startDate;
    /*** @Fields endDate: 结束时间 */
    private Date endDate;
    /*** @Fields remark:  描述*/
    private String remark;
    public RentTurnCostResult(){}
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getReletCode() {
        return reletCode;
    }
    public void setReletCode(String reletCode) {
        this.reletCode = reletCode;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    
    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
}
