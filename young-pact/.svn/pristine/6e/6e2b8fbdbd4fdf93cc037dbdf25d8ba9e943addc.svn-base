package com.young.pact.dao.pactrenttransfer.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tools.common.ibatis.base.BaseDao;
import com.young.common.exception.DaoException;
import com.young.pact.dao.pactrenttransfer.PactRentTransferDao;
import com.young.pact.domain.pactrenttransfer.PactRentTransferDO;
import com.young.pact.domain.pactrenttransfer.PactRentTransferQuery;
import com.young.pact.domain.pactrenttransfer.PactRentTransferVO;

/**
 * 
* @ClassName: PactRentTransferDaoImpl
* @Description: 调房协议dao实现类
* @author LiuYuChi
* @date 2018年8月8日 下午1:48:01
*
 */
@Repository("pactRentTransferDao")
public class PactRentTransferDaoImpl extends BaseDao implements PactRentTransferDao{

	@SuppressWarnings("deprecation")
	@Override
	public Long saveTransfer(PactRentTransferDO pactRentTransferDO)throws DaoException {
		try {
			return (Long) this.insert("PactRentTransfer.insertTransfer", pactRentTransferDO);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public PactRentTransferVO getTransfer(String transferCode) throws DaoException {
		try {
			return (PactRentTransferVO) this.queryForObject("PactRentTransfer.getTransfer", transferCode);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public int updateTransfer(PactRentTransferDO pactRentTransferDO)  throws DaoException {
		try {
			return this.update("PactRentTransfer.updateTransfer",pactRentTransferDO);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<PactRentTransferVO> listPactRentTransfer(PactRentTransferQuery query) throws DaoException {
		try {
			return this.queryForList("PactRentTransfer.listTransfer", query);
		} catch (Exception e) {
			throw new DaoException(e);
		}
		
	}

	@SuppressWarnings("deprecation")
	@Override
	public int countTransfer(PactRentTransferQuery query) throws DaoException {
		try {
			return (int) this.queryForObject("PactRentTransfer.countTransfer",query);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

    @SuppressWarnings({ "deprecation", "unchecked" })
    @Override
    public List<PactRentTransferVO> listTransferByParam(PactRentTransferQuery rentTransferQuery) throws DaoException {
        try {
            return this.queryForList("PactRentTransfer.listTransferByParam",rentTransferQuery);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @SuppressWarnings({ "deprecation", "unchecked" })
    @Override
    public List<PactRentTransferVO> listUnReview(PactRentTransferQuery rentTransferQuery) throws DaoException {
        try {
            return this.queryForList("PactRentTransfer.listUnReview",rentTransferQuery);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @SuppressWarnings({ "deprecation", "unchecked" })
    @Override
    public List<PactRentTransferVO> listUnsubmitted(PactRentTransferQuery rentTransferQuery) throws DaoException {
        try {
            return this.queryForList("PactRentTransfer.listUnsubmitted",rentTransferQuery);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public PactRentTransferVO getPermissions(PactRentTransferQuery query) throws DaoException {
        try {
            return (PactRentTransferVO) this.queryForObject("PactRentTransfer.getPermissions",query);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

}
