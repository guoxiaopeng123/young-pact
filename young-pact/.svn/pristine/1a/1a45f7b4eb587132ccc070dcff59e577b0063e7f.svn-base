package com.young.pact.api.domain.param.rest.rentbase;

import java.io.Serializable;
import java.util.List;

import com.young.pact.api.domain.param.rest.commoncostrule.CommonCostRuleParam;
import com.young.pact.api.domain.param.rest.commonpic.CommonPicParam;
import com.young.pact.api.domain.param.rest.financereceiptpay.FinanceReceiptPayParam;

/**
 * @描述 : 托出合同
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月2日 下午12:11:06
 */
public class RentPactParam  implements Serializable {

    /**
    * @Fields serialVersionUID : TODO( )
    */
    private static final long serialVersionUID = 1L;
    /** @Fields pact:合同信息**/
    private RentBaseParam pact;
    /** @Fields pactReceiptPayList:合同收支(集合)**/
    private List<CommonCostRuleParam> pactReceiptPayList;
    /** @Fields receiptPayList:收支(集合)**/
    private List<FinanceReceiptPayParam> receiptPayList;
    /** @Fields urlList:合同照片(集合)**/
    private List<CommonPicParam> urlList;
    /** @Fields createName:登录人**/
    private String createName;
    /** @Fields serviceCode:资源编码**/
    private String serviceCode;
    /** @Fields ip:ip**/
    private String ip;
    /** @Fields modifiedName:修改人**/
    private String modifiedName;
    public RentPactParam(){
        
    }
    public RentBaseParam getPact() {
        return pact;
    }
    public void setPact(RentBaseParam pact) {
        this.pact = pact;
    }
    public List<CommonCostRuleParam> getPactReceiptPayList() {
        return pactReceiptPayList;
    }
    public void setPactReceiptPayList(List<CommonCostRuleParam> pactReceiptPayList) {
        this.pactReceiptPayList = pactReceiptPayList;
    }
    public List<CommonPicParam> getUrlList() {
        return urlList;
    }
    public void setUrlList(List<CommonPicParam> urlList) {
        this.urlList = urlList;
    }
    public String getCreateName() {
        return createName;
    }
    public void setCreateName(String createName) {
        this.createName = createName;
    }
    public String getServiceCode() {
        return serviceCode;
    }
    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }
    public String getIp() {
        return ip;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }
    public List<FinanceReceiptPayParam> getReceiptPayList() {
        return receiptPayList;
    }
    public void setReceiptPayList(List<FinanceReceiptPayParam> receiptPayList) {
        this.receiptPayList = receiptPayList;
    }
    public String getModifiedName() {
        return modifiedName;
    }
    public void setModifiedName(String modifiedName) {
        this.modifiedName = modifiedName;
    }
}
