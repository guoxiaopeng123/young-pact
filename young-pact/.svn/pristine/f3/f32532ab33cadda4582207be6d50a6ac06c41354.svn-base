package com.young.pact.domain.rentbase;

import java.io.Serializable;
import java.util.List;

import com.young.pact.domain.commoncostrule.CommonCostRuleDO;
import com.young.pact.domain.commonpic.CommonPicDO;
import com.young.pact.domain.financereceiptpay.FinanceReceiptPayDO;

/**
 * @描述 : 托出合同
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月2日 下午12:11:06
 */
public class RentPactDO implements Serializable {

    /**
    * @Fields serialVersionUID : TODO( )
    */
    private static final long serialVersionUID = 1L;
    /** @Fields pact:合同信息**/
    private RentBaseDO pact;
    /** @Fields pactReceiptPayList:合同收支(集合)**/
    private List<CommonCostRuleDO> pactReceiptPayList;
    /** @Fields receiptPayList:收支(集合)**/
    private List<FinanceReceiptPayDO> receiptPayList;
    /** @Fields urlList:合同照片(集合)**/
    private List<CommonPicDO> urlList;
    public RentPactDO(){}
    public RentBaseDO getPact() {
        return pact;
    }
    public void setPact(RentBaseDO pact) {
        this.pact = pact;
    }
    public List<CommonCostRuleDO> getPactReceiptPayList() {
        return pactReceiptPayList;
    }
    public void setPactReceiptPayList(List<CommonCostRuleDO> pactReceiptPayList) {
        this.pactReceiptPayList = pactReceiptPayList;
    }
    public List<FinanceReceiptPayDO> getReceiptPayList() {
        return receiptPayList;
    }
    public void setReceiptPayList(List<FinanceReceiptPayDO> receiptPayList) {
        this.receiptPayList = receiptPayList;
    }
    public List<CommonPicDO> getUrlList() {
        return urlList;
    }
    public void setUrlList(List<CommonPicDO> urlList) {
        this.urlList = urlList;
    }
}
