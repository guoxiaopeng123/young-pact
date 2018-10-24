package com.young.pact.dao.rentbase.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tools.common.ibatis.base.BaseDao;
import com.young.common.exception.DaoException;
import com.young.pact.dao.rentbase.RentBaseDao;
import com.young.pact.domain.commonbelonger.CommonBelongerVO;
import com.young.pact.domain.rentbase.RentBaseDO;
import com.young.pact.domain.rentbase.RentBaseQuery;
import com.young.pact.domain.rentbase.RentBaseVO;

/**
 * @描述 : 托出合同
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月3日 上午11:43:39
 */
@Repository("rentBaseDao")
public class RentBaseDaoImpl extends BaseDao implements RentBaseDao {

    @SuppressWarnings("deprecation")
    @Override
    public Long saveRentBase(RentBaseDO rentBaseDO)throws DaoException {
        try {
            return (Long) this.insert("RentBase.saveRentBase", rentBaseDO);
        } catch (Exception e) {
            // TODO: handle exception
            throw new DaoException(e);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public Integer count(RentBaseQuery query) throws DaoException {
        try {
            return (Integer) this.queryForObject("RentBase.count", query);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @SuppressWarnings({ "deprecation", "unchecked" })
    @Override
    public List<RentBaseVO> listParam(RentBaseQuery query) throws DaoException {
        try {
            return this.queryForList("RentBase.listByParam", query);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public RentBaseVO getRentBaseByCode(String rentPactCode) throws DaoException {
        try {
            return (RentBaseVO) this.queryForObject("RentBase.getRentBaseByCode", rentPactCode);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public Long updateRentBaseStateByCode(RentBaseDO rentBaseDO) throws DaoException {
        try {
            return (long) this.update("RentBase.updateRentBaseStateByCode", rentBaseDO);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public Long updateRentBaseByCode(RentBaseDO rentBaseDO) throws DaoException {
        try {
            return (long) this.update("RentBase.updateRentBaseByCode", rentBaseDO);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public Long deleteRentBaseByCode(RentBaseDO rentBaseDO)throws DaoException {
        try {
            return (long) this.update("RentBase.deleteRentBaseByCode", rentBaseDO);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }


    @SuppressWarnings("deprecation")
    @Override
    public int countRentBaseByCustomer(RentBaseQuery rentBaseQuery) throws DaoException {
        try {
            return (int) this.queryForObject("RentBase.countRentBaseByCustomer", rentBaseQuery);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public RentBaseVO getPermissions(RentBaseQuery query) throws DaoException {
        try {
            return (RentBaseVO) this.queryForObject("RentBase.getPermissions", query);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public CommonBelongerVO getRentAfterBelonger(String pactCode) throws DaoException {
        try {
            return (CommonBelongerVO) this.queryForObject("RentBase.getRentAfterBelonger", pactCode);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @SuppressWarnings({ "deprecation", "unchecked" })
    @Override
    public List<RentBaseVO> listUnsubmitted(RentBaseQuery rentBaseQuery) throws DaoException {
        try {
            return this.queryForList("RentBase.listUnsubmitted", rentBaseQuery);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @SuppressWarnings({ "deprecation", "unchecked" })
    @Override
    public List<RentBaseVO> listUnReview(RentBaseQuery rentBaseQuery) throws DaoException {
        try {
            return this.queryForList("RentBase.listUnReview", rentBaseQuery);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @SuppressWarnings({ "deprecation", "unchecked" })
    @Override
    public List<RentBaseVO> listRentBaseByParam(RentBaseQuery rentBaseQuery) throws DaoException {
        try {
            return this.queryForList("RentBase.listRentBaseByParam", rentBaseQuery);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @SuppressWarnings({ "deprecation", "unchecked" })
    @Override
    public List<RentBaseVO> listRentExpir() throws DaoException {
        try {
            return this.queryForList("RentBase.listRentExpir");
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }
}
