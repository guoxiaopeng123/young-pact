package com.young.pact.dao.pactrenttermination.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tools.common.ibatis.base.BaseDao;
import com.young.common.exception.DaoException;
import com.young.pact.dao.pactrenttermination.PactRentTerminationDao;
import com.young.pact.domain.pactrenttermination.PactRentTerminationDO;
import com.young.pact.domain.pactrenttermination.PactRentTerminationQuery;
import com.young.pact.domain.pactrenttermination.PactRentTerminationVO;

/**
 * 
* @ClassName: PactRentTerminationDaoImpl
* @Description: 解约协议dao实现类
* @author LiuYuChi
* @date 2018年8月12日 上午11:44:54
*
 */
@SuppressWarnings("all")
@Repository("pactRentTerminationDao")
public class PactRentTerminationDaoImpl extends BaseDao implements PactRentTerminationDao{

	@Override
	public Long insertTermination(PactRentTerminationDO termination) throws DaoException {
		try {
			return (Long) this.insert("PactRentTermination.insertTermination", termination);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public List<PactRentTerminationVO> listTermination(PactRentTerminationQuery query) throws DaoException{
		try {
			return this.queryForList("PactRentTermination.listTermination", query);
		} catch (Exception e) {
			throw new DaoException(e);
		}
		
	}

	@Override
	public int countTermination(PactRentTerminationQuery query) throws DaoException {
		try {
			return (int) this.queryForObject("PactRentTermination.countTermination", query);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public PactRentTerminationVO getTermination(PactRentTerminationQuery query)throws DaoException {
		try {
			return (PactRentTerminationVO) this.queryForObject("PactRentTermination.getTermination", query);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

			
		
	@Override
	public int updateTermination(PactRentTerminationDO termination)throws DaoException {
		try {
			return this.update("PactRentTermination.updateTermination", termination);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

    @Override
    public List<PactRentTerminationVO> listRentTerminationByParam(PactRentTerminationQuery pactRentTerminationQuery) throws DaoException {
        try {
            return this.queryForList("PactRentTermination.listRentTerminationByParam", pactRentTerminationQuery);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<PactRentTerminationVO> listUnsubmitted(PactRentTerminationQuery pactRentTerminationQuery) throws DaoException {
        try {
            return this.queryForList("PactRentTermination.listUnsubmitted", pactRentTerminationQuery);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<PactRentTerminationVO> listUnReview(PactRentTerminationQuery pactRentTerminationQuery) throws DaoException {
        try {
            return this.queryForList("PactRentTermination.listUnReview", pactRentTerminationQuery);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public PactRentTerminationVO getPermissions(PactRentTerminationQuery query) throws DaoException {
        try {
            return (PactRentTerminationVO) this.queryForObject("PactRentTermination.getPermissions", query);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }
}
