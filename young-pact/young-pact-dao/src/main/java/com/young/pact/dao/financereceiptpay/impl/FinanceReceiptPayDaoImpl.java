package com.young.pact.dao.financereceiptpay.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tools.common.ibatis.base.BaseDao;
import com.young.common.exception.DaoException;
import com.young.pact.dao.financereceiptpay.FinanceReceiptPayDao;
import com.young.pact.domain.financereceiptpay.FinanceReceiptPayDO;
import com.young.pact.domain.financereceiptpay.FinanceReceiptPayVO;
import com.young.pact.domain.financereceiptpay.FinanceReceiptPayQuery;
/**
 * 
* @ClassName: FinanceReceiptPayDaoImpl
* @Description: TODO( 收支)
* @author HeZeMin
* @date 2018年8月2日 下午10:03:20
*
 */
@SuppressWarnings("all")
@Repository("financeReceiptPayDao")
public class FinanceReceiptPayDaoImpl extends BaseDao implements FinanceReceiptPayDao {

    @Override
    public int saveFinanceReceiptPay(List<FinanceReceiptPayDO> financeReceiptPayDOs) throws DaoException {
        try {
            return this.batchInsert("FinanceReceiptPay.saveFinanceReceiptPay", financeReceiptPayDOs);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Long saveReceiptPay(FinanceReceiptPayDO financeReceiptPayDO) throws DaoException {
        try {
            return (Long) this.insert("FinanceReceiptPay.saveFinanceReceiptPay", financeReceiptPayDO);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public int removeFinanceReceiptPay(FinanceReceiptPayDO financeReceiptPayDO) throws DaoException {
        try {
            return this.update("FinanceReceiptPay.removeFinanceReceiptPay", financeReceiptPayDO);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<FinanceReceiptPayVO> listFinanceReceiptPay(String pactCode) throws DaoException {
        try {
            return this.queryForList("FinanceReceiptPay.listFinanceReceiptPay", pactCode);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public FinanceReceiptPayVO getPayeeReceiptPayByPactCode(FinanceReceiptPayQuery financeReceiptPayQuery)throws DaoException {
        try {
            return (FinanceReceiptPayVO) this.queryForObject("FinanceReceiptPay.getPayeeReceiptPayByPactCode", financeReceiptPayQuery);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public int removeNotFinanceReceiptPay(Map<String, Object> map) throws DaoException {
        try {
            return this.update("FinanceReceiptPay.removeNotFinanceReceiptPay", map);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public int updateFinanceReceiptPay(List<FinanceReceiptPayDO> financeReceiptPayDOs) throws DaoException {
        try {
            return this.batchUpdate("FinanceReceiptPay.updateFinanceReceiptPay", financeReceiptPayDOs);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public int countAllFinanceReceiptPay(FinanceReceiptPayQuery query) throws DaoException {
        try {
            return (int) this.queryForObject("FinanceReceiptPay.countAllFinanceReceiptPay", query);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<FinanceReceiptPayVO> listAllFinanceReceiptPay(FinanceReceiptPayQuery query) throws DaoException {
        try {
            return this.queryForList("FinanceReceiptPay.listAllFinanceReceiptPay", query);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public int countReceivFinanceReceiptPay(FinanceReceiptPayQuery query) throws DaoException {
        try {
            return (int) this.queryForObject("FinanceReceiptPay.countReceivFinanceReceiptPay", query);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<FinanceReceiptPayVO> listReceivFinanceReceiptPay(FinanceReceiptPayQuery query) throws DaoException {
        try {
            return this.queryForList("FinanceReceiptPay.listReceivFinanceReceiptPay", query);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public int countNetFinanceReceiptPay(FinanceReceiptPayQuery query) throws DaoException {
        try {
            return (int) this.queryForObject("FinanceReceiptPay.countNetFinanceReceiptPay", query);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<FinanceReceiptPayVO> listNetFinanceReceiptPay(FinanceReceiptPayQuery query) throws DaoException {
        try {
            return this.queryForList("FinanceReceiptPay.listNetFinanceReceiptPay", query);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<FinanceReceiptPayVO> listSumReceiptPay(List<Long> pids) throws DaoException {
        try {
            return this.queryForList("FinanceReceiptPay.listSumReceiptPay", pids);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public int updateValidByCode(FinanceReceiptPayDO financeReceiptPayDO) throws DaoException {
        try {
            return this.update("FinanceReceiptPay.updateValidByCode", financeReceiptPayDO);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public FinanceReceiptPayVO getAnswerCollectDetail(Long id) throws DaoException {
        try {
            return (FinanceReceiptPayVO) this.queryForObject("FinanceReceiptPay.getAnswerCollectDetail", id);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public FinanceReceiptPayVO getRealCollectDetail(Long id) throws DaoException {
        try {
            return (FinanceReceiptPayVO) this.queryForObject("FinanceReceiptPay.getRealCollectDetail", id);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<FinanceReceiptPayVO> listRealCollectByPid(Long pid) throws DaoException {
        try {
            return this.queryForList("FinanceReceiptPay.listRealCollectByPid", pid);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public FinanceReceiptPayVO getAnswerExpendDetail(Long id) throws DaoException {
        try {
            return (FinanceReceiptPayVO) this.queryForObject("FinanceReceiptPay.getAnswerExpendDetail", id);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public FinanceReceiptPayVO getRealExpendDetail(Long id) throws DaoException {
        try {
            return (FinanceReceiptPayVO) this.queryForObject("FinanceReceiptPay.getRealExpendDetail", id);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<FinanceReceiptPayVO> listRealExpendByPid(Long pid) throws DaoException {
        try {
            return this.queryForList("FinanceReceiptPay.listRealExpendByPid", pid);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public int countReceivReceiptPay(FinanceReceiptPayQuery query) throws DaoException {
        try {
            return (int) this.queryForObject("FinanceReceiptPay.countReceivReceiptPay", query);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<FinanceReceiptPayVO> listReceivReceiptPay(FinanceReceiptPayQuery query) throws DaoException {
        try {
            return this.queryForList("FinanceReceiptPay.listReceivReceiptPay", query);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public int countNetReceiptPay(FinanceReceiptPayQuery query) throws DaoException {
        try {
            return (int) this.queryForObject("FinanceReceiptPay.countNetReceiptPay", query);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<FinanceReceiptPayVO> listNetReceiptPay(FinanceReceiptPayQuery query) throws DaoException {
        try {
            return this.queryForList("FinanceReceiptPay.listNetReceiptPay", query);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public FinanceReceiptPayVO getPactAnswerCollectDetail(Long id) throws DaoException {
        try {
            return (FinanceReceiptPayVO) this.queryForObject("FinanceReceiptPay.getPactAnswerCollectDetail", id);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public FinanceReceiptPayVO getPactRealCollectDetail(Long id) throws DaoException {
        try {
            return (FinanceReceiptPayVO) this.queryForObject("FinanceReceiptPay.getPactRealCollectDetail", id);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public FinanceReceiptPayVO getPactAnswerExpendDetail(Long id) throws DaoException {
        try {
            return (FinanceReceiptPayVO) this.queryForObject("FinanceReceiptPay.getPactAnswerExpendDetail", id);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public FinanceReceiptPayVO getPactRealExpendDetail(Long id) throws DaoException {
        try {
            return (FinanceReceiptPayVO) this.queryForObject("FinanceReceiptPay.getPactRealExpendDetail", id);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }
    @Override
    public List<FinanceReceiptPayVO> listReceiptPayByDate(FinanceReceiptPayQuery financeReceiptPayQuery) throws DaoException {
        try {
            return this.queryForList("FinanceReceiptPay.listReceiptPayByDate", financeReceiptPayQuery);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public int removeOldPactFinanceReceiptPay(FinanceReceiptPayDO oldFinanceReceiptPayDO) throws DaoException{
        try {
            return this.update("FinanceReceiptPay.removeOldPactFinanceReceiptPay", oldFinanceReceiptPayDO);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

	@Override
	public int delFinanceReceiptPay(FinanceReceiptPayDO oldFinanceReceiptPayDO) throws DaoException{
		try {
			return update("FinanceReceiptPay.delFinanceReceiptPay", oldFinanceReceiptPayDO);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

    @Override
    public int updateCostState(FinanceReceiptPayDO oldFinanceReceiptPayDO) throws DaoException {
        try {
            return this.update("FinanceReceiptPay.updateCostState", oldFinanceReceiptPayDO);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public double getRealAmountByPid(Long pid) throws DaoException {
        try {
            return (double) this.queryForObject("FinanceReceiptPay.getRealAmountByPid", pid);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public int updateFinancePayById(FinanceReceiptPayDO financeReceiptPayDO) throws DaoException {
        try {
            return this.update("FinanceReceiptPay.updateFinancePayById", financeReceiptPayDO);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

	@Override
	public List<FinanceReceiptPayVO> listFinanceReceiptPayDO(FinanceReceiptPayQuery financeReceiptPayQuery) throws DaoException {
		try {
			return this.queryForList("FinanceReceiptPay.listFinanceReceiptPayDO", financeReceiptPayQuery);
		} catch (Exception e) {
			throw new DaoException(e);
		}
		
	}

    @Override
    public FinanceReceiptPayVO getPermissions(FinanceReceiptPayQuery query) throws DaoException {
        try {
            return (FinanceReceiptPayVO) this.queryForObject("FinanceReceiptPay.getPermissions", query);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<FinanceReceiptPayVO> listFinanceReceiptPayByPactCode(FinanceReceiptPayQuery financeReceiptPayQuery) throws DaoException {
        try {
            return this.queryForList("FinanceReceiptPay.listFinanceReceiptPayByPactCode", financeReceiptPayQuery);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Double countRecPayAmount(FinanceReceiptPayQuery query) throws DaoException {
        try {
            return (Double) this.queryForObject("FinanceReceiptPay.countRecPayAmount", query);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }
    @Override
    public FinanceReceiptPayVO getLastRecPay(FinanceReceiptPayQuery query) throws DaoException {
        try {
            return (FinanceReceiptPayVO) this.queryForObject("FinanceReceiptPay.getLastRecPay", query);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }
}
