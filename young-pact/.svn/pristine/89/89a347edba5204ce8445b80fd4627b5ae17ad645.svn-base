package com.young.pact.dao.financereceiptpay.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tools.common.ibatis.base.BaseDao;
import com.young.common.exception.DaoException;
import com.young.pact.dao.financereceiptpay.FinanceReceiptPayVoucherDao;
import com.young.pact.domain.financereceiptpay.FinanceReceiptPayVoucherDO;
/**
 * 
* @ClassName: FinanceReceiptPayVoucherDaoImpl
* @Description: TODO( 收支- 凭证)
* @author HeZeMin
* @date 2018年8月8日 下午9:37:35
*
 */
@SuppressWarnings("all")
@Repository("financeReceiptPayVoucherDao")
public class FinanceReceiptPayVoucherDaoImpl extends BaseDao implements FinanceReceiptPayVoucherDao {

    @Override
    public List<FinanceReceiptPayVoucherDO> listVoucherByPids(List<Long> pids) throws DaoException {
        try {
            return this.queryForList("FinanceReceiptPayVoucher.listVoucherByPids", pids);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public int saveFinanceReceiptPayVouchers(List<FinanceReceiptPayVoucherDO> financeReceiptPayVoucherDOs) throws DaoException {
        try {
            return this.batchInsert("FinanceReceiptPayVoucher.saveFinanceReceiptPayVoucher", financeReceiptPayVoucherDOs);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public int updateFinanceReceiptPayVouchers(List<FinanceReceiptPayVoucherDO> financeReceiptPayVoucherDOs) throws DaoException {
        try {
            return this.batchUpdate("FinanceReceiptPayVoucher.updateFinanceReceiptPayVoucher", financeReceiptPayVoucherDOs);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public int removeVouchersByPid(Map<String, Object> map) throws DaoException {
        try {
            return this.delete("FinanceReceiptPayVoucher.removeVouchersByPid", map);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

}
