package com.young.pact.api.domain.param.rest.financereceiptpay;

import java.io.Serializable;
import java.util.Date;
/**
 * 
* @ClassName: FinanceReceiptPayVoucherParam
* @Description: TODO( 收支凭证)
* @author HeZeMin
* @date 2018年8月9日 下午4:16:37
*
 */
public class FinanceReceiptPayVoucherParam implements Serializable {
    private static final long serialVersionUID = 1L;
    /*** @Fields id:主键id*/
    private Long id;
    /*** @Fields receiptPayId:收支id*/
    private Long receiptPayId;
    /*** @Fields url:url*/
    private String url;
    /*** @Fields sort:排序*/
    private Integer sort;
    /*** @Fields create:创建时间*/
    private Date create;
    /*** @Fields createName:创建人*/
    private String createName;
    /*** @Fields createDeptName:创建人部门*/
    private String createDeptName;
    
    
    public FinanceReceiptPayVoucherParam() {
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
    public Date getCreate() {
        return create;
    }



    public void setCreate(Date create) {
        this.create = create;
    }



    public String getCreateName() {
        return createName;
    }



    public void setCreateName(String createName) {
        this.createName = createName;
    }



    public String getCreateDeptName() {
        return createDeptName;
    }



    public void setCreateDeptName(String createDeptName) {
        this.createDeptName = createDeptName;
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
