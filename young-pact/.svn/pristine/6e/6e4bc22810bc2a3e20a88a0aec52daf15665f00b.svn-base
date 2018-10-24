package com.young.pact.dao.financeamortize.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tools.common.ibatis.base.BaseDao;
import com.young.common.exception.DaoException;
import com.young.pact.dao.financeamortize.FinanceAmortizeDao;
import com.young.pact.domain.financeamortize.FinanceAmortizeDO;
import com.young.pact.domain.financeamortize.FinanceAmortizeQuery;
import com.young.pact.domain.financeamortize.FinanceAmortizeVO;
/**
 * 
* @ClassName: FinanceAmortizeDaoImpl
* @Description: TODO( 摊销)
* @author HeZeMin
* @date 2018年8月6日 上午10:48:07
*
 */
@SuppressWarnings("all")
@Repository("financeAmortizeDao")
public class FinanceAmortizeDaoImpl extends BaseDao implements FinanceAmortizeDao {

    @Override
    public int saveFinanceAmortizes(List<FinanceAmortizeDO> amortizeDOs) throws DaoException {
        try {
            return this.batchInsert("FinanceAmortize.saveFinanceAmortize", amortizeDOs);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public int countFinanceAmortize(FinanceAmortizeQuery financeAmortizeQuery) throws DaoException {
        try {
            return (int) queryForObject("FinanceAmortize.countFinanceAmortize", financeAmortizeQuery);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<FinanceAmortizeVO> listFinanceAmortize(FinanceAmortizeQuery financeAmortizeQuery) throws DaoException {
        try {
            return queryForList("FinanceAmortize.listFinanceAmortize", financeAmortizeQuery);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

}
