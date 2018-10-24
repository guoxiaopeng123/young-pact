package com.young.pact.domain.financereceiptpay;

import com.young.common.domain.BaseDomain;
/**
 * 
* @ClassName: financeReceiptPayVoucherDO
* @Description: TODO( 收支凭证)
* @author HeZeMin
* @date 2018年8月1日 下午8:45:48
*
 */
public class FinanceReceiptPayVoucherDO extends BaseDomain {
    private static final long serialVersionUID = 1L;
    /*** @Fields id:主键id*/
    private Long id;
    /*** @Fields receiptPayId:收支id*/
    private Long receiptPayId;
    /*** @Fields url:url*/
    private String url;
    /*** @Fields sort:排序*/
    private Integer sort;
    
    
    public FinanceReceiptPayVoucherDO() {
    }
    
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getReceiptPayId() {
        return receiptPayId;
    }
    public void setReceiptPayId(Long receiptPayId) {
        this.receiptPayId = receiptPayId;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public Integer getSort() {
        return sort;
    }
    public void setSort(Integer sort) {
        this.sort = sort;
    }
    
}
