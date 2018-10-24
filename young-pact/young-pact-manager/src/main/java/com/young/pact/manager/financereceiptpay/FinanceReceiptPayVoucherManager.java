package com.young.pact.manager.financereceiptpay;

import java.util.List;

import com.young.pact.domain.financereceiptpay.FinanceReceiptPayVoucherDO;

/**
 * 
* @ClassName: FinanceReceiptPayVoucherManager
* @Description: TODO( 收支凭证)
* @author HeZeMin
* @date 2018年8月9日 下午3:47:55
*
 */
public interface FinanceReceiptPayVoucherManager {
    /**
     * 
    * @Title: listVoucherByPids
    * @Description: TODO( 根据实收、实支 id集合查询凭证集合)
    * @author HeZeMin
    * @param pids   实收、实支 id集合
    * @return   返回凭证集合
     */
    List<FinanceReceiptPayVoucherDO> listVoucherByPids(List<Long> pids);
}
