package com.young.pact.manager.financereceiptpay.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.young.common.exception.DaoException;
import com.young.pact.common.constant.ErrorPactConsts;
import com.young.pact.common.exception.PactManagerExcepion;
import com.young.pact.dao.financereceiptpay.FinanceReceiptPayVoucherDao;
import com.young.pact.domain.financereceiptpay.FinanceReceiptPayVoucherDO;
import com.young.pact.manager.financereceiptpay.FinanceReceiptPayVoucherManager;
/**
 * 
* @ClassName: FinanceReceiptPayVoucherManagerImpl
* @Description: TODO( 收支凭证)
* @author HeZeMin
* @date 2018年8月9日 下午3:48:10
*
 */
@Component("financeReceiptPayVoucherManager")
public class FinanceReceiptPayVoucherManagerImpl implements FinanceReceiptPayVoucherManager {
    /******************声明区*********************/
    private static final Log LOG = LogFactory.getLog(FinanceReceiptPayVoucherManagerImpl.class);
    private FinanceReceiptPayVoucherDao financeReceiptPayVoucherDao;
    /******************方法区*********************/
    
    @Override
    public List<FinanceReceiptPayVoucherDO> listVoucherByPids(List<Long> pids) {
        try {
            return financeReceiptPayVoucherDao.listVoucherByPids(pids);
        } catch (DaoException e) {
            LOG.error(ErrorPactConsts.LIST_FINANCERECEIPTPAY_VOUCHER_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.LIST_FINANCERECEIPTPAY_VOUCHER_ERROR,e);
        }
    }
    /******************get/set*********************/
    public FinanceReceiptPayVoucherDao getFinanceReceiptPayVoucherDao() {
        return financeReceiptPayVoucherDao;
    }
    public void setFinanceReceiptPayVoucherDao(FinanceReceiptPayVoucherDao financeReceiptPayVoucherDao) {
        this.financeReceiptPayVoucherDao = financeReceiptPayVoucherDao;
    }
    
}
