package com.young.pact.dao.rentcontinued.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tools.common.ibatis.base.BaseDao;
import com.young.common.exception.DaoException;
import com.young.pact.dao.rentcontinued.RentContinuedDao;
import com.young.pact.domain.rentcontinued.RentContinuedDO;
import com.young.pact.domain.rentcontinued.RentContinuedQuery;
import com.young.pact.domain.rentcontinued.RentContinuedVO;

/**
 * @描述 :  续签协议
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月10日 下午8:44:58
 */
@Repository("rentContinuedDao")
public class RentContinuedDaoImpl extends BaseDao implements RentContinuedDao {


    @SuppressWarnings("deprecation")
    @Override
    public Integer countAllProtocolByPactCode(String rentPactCode) throws DaoException {
        try {
            return (Integer) this.queryForObject("RentContinued.countAllProtocolByPactCode",rentPactCode);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public Long saveRentContinued(RentContinuedDO rentContinuedDO) throws DaoException {
        try {
            return (Long) this.insert("RentContinued.saveRentContinued",rentContinuedDO);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public Integer count(RentContinuedQuery query) throws DaoException {
        try {
            return (Integer) this.queryForObject("RentContinued.count", query);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @SuppressWarnings({ "deprecation", "unchecked" })
    @Override
    public List<RentContinuedVO> listParam(RentContinuedQuery query) throws DaoException {
        try {
            return this.queryForList("RentContinued.listParam", query);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public RentContinuedVO getRentContinuedByRenewCode(String renewCode) throws DaoException {
        try {
            return (RentContinuedVO) this.queryForObject("RentContinued.getRentContinuedByRenewCode", renewCode);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public Long updateRentContinuedStateByCode(RentContinuedDO rentContinuedDO) throws DaoException {
        try {
            return (long) this.update("RentContinued.updateRentContinuedStateByCode", rentContinuedDO);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public Integer updateRentContinuedByRenewCode(RentContinuedDO rentContinuedDO) throws DaoException {
        try {
            return this.update("RentContinued.updateRentContinuedByRenewCode", rentContinuedDO);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public Integer removeRentContinuedByReletCode(RentContinuedDO rentContinuedDO) throws DaoException {
        try {
            return this.update("RentContinued.removeRentContinuedByReletCode", rentContinuedDO);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @SuppressWarnings({ "deprecation", "unchecked" })
    @Override
    public List<RentContinuedVO> listUnsubmitted(RentContinuedQuery rentContinuedQuery) throws DaoException {
        try {
            return this.queryForList("RentContinued.listUnsubmitted", rentContinuedQuery);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @SuppressWarnings({ "deprecation", "unchecked" })
    @Override
    public List<RentContinuedVO> listUnReview(RentContinuedQuery rentContinuedQuery) throws DaoException {
        try {
            return this.queryForList("RentContinued.listUnReview", rentContinuedQuery);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @SuppressWarnings({ "deprecation", "unchecked" })
    @Override
    public List<RentContinuedVO> listRentcontinuedByParam(RentContinuedQuery rentContinuedQuery) throws DaoException {
        try {
            return this.queryForList("RentContinued.listRentcontinuedByParam", rentContinuedQuery);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public RentContinuedVO getPermissions(RentContinuedQuery rentContinuedQuery) throws DaoException {
        try {
            return (RentContinuedVO) this.queryForObject("RentContinued.getPermissions", rentContinuedQuery);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }
}
