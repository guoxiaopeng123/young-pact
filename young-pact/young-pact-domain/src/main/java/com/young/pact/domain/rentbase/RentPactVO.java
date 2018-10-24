package com.young.pact.domain.rentbase;

import java.util.List;

import com.young.common.domain.BaseDomain;
import com.young.pact.domain.commoncostrule.CommonCostRuleVO;
import com.young.pact.domain.commonpic.CommonPicVO;
import com.young.pact.domain.financereceiptpay.FinanceReceiptPayVO;

/**
 * @描述 : 托出合同
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月2日 下午12:11:06
 */
public class RentPactVO  extends BaseDomain {

    /**
    * @Fields serialVersionUID : TODO( )
    */
    private static final long serialVersionUID = 1L;
    /** @Fields pact:合同信息**/
    private RentBaseVO pact;
    /** @Fields pactReceiptPayList:合同收支(集合)**/
    private List<CommonCostRuleVO> pactReceiptPayList;
    /** @Fields receiptPayList:收支(集合)**/
    private List<FinanceReceiptPayVO> receiptPayList;
    /** @Fields urlList:合同照片(集合)**/
    private List<CommonPicVO> urlList;
    public RentPactVO(){}
    public RentBaseVO getPact() {
        return pact;
    }

    public void setPact(RentBaseVO pact) {
        this.pact = pact;
    }

    public List<CommonCostRuleVO> getPactReceiptPayList() {
        return pactReceiptPayList;
    }
    public void setPactReceiptPayList(List<CommonCostRuleVO> pactReceiptPayList) {
        this.pactReceiptPayList = pactReceiptPayList;
    }
    public List<FinanceReceiptPayVO> getReceiptPayList() {
        return receiptPayList;
    }
    public void setReceiptPayList(List<FinanceReceiptPayVO> receiptPayList) {
        this.receiptPayList = receiptPayList;
    }
    public List<CommonPicVO> getUrlList() {
        return urlList;
    }
    public void setUrlList(List<CommonPicVO> urlList) {
        this.urlList = urlList;
    }
}
