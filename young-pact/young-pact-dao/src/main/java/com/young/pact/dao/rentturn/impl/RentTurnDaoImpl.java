package com.young.pact.dao.rentturn.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tools.common.ibatis.base.BaseDao;
import com.young.common.exception.DaoException;
import com.young.pact.dao.rentturn.RentTurnDao;
import com.young.pact.domain.rentturn.RentTurnDO;
import com.young.pact.domain.rentturn.RentTurnQuery;
import com.young.pact.domain.rentturn.RentTurnVO;

/**
 * @描述 : 转租协议
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月6日 下午8:56:12
 */
@Repository("rentTurnDao")
public class RentTurnDaoImpl extends BaseDao implements RentTurnDao {

    @SuppressWarnings("deprecation")
    @Override
    public Long saveRentTurn(RentTurnDO rentTurnDO) throws DaoException {
        try {
            return (Long) this.insert("RentTurn.saveRentTurn", rentTurnDO);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public Integer count(RentTurnQuery query) throws DaoException {
        try {
            return (Integer) this.queryForObject("RentTurn.count", query);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @SuppressWarnings({ "deprecation", "unchecked" })
    @Override
    public List<RentTurnVO> listParam(RentTurnQuery query) throws DaoException {
        try {
            return this.queryForList("RentTurn.listParam", query);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public RentTurnVO getRentTurnByReletCode(String reletCode) throws DaoException {
        try {
            return (RentTurnVO) this.queryForObject("RentTurn.getRentTurnByReletCode", reletCode);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public Long updateRentTurnStateByCode(RentTurnDO rentTurnDO) throws DaoException {
        try {
            return (long) this.update("RentTurn.updateRentTurnStateByCode", rentTurnDO);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public Long updateRentTurn(RentTurnDO rentTurnDO) throws DaoException {
        try {
            return (long) this.update("RentTurn.updateRentTurn",rentTurnDO);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public Long removeRentTurnByReletCode(RentTurnDO rentTurnDO) throws DaoException {
        try {
            return (long) this.update("RentTurn.removeRentTurnByReletCode",rentTurnDO);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @SuppressWarnings({ "deprecation", "unchecked" })
    @Override
    public List<RentTurnVO> listRentTurnByParam(RentTurnQuery rentTurnQuery) throws DaoException {
        try {
            return this.queryForList("RentTurn.listRentTurnByParam",rentTurnQuery);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @SuppressWarnings({ "deprecation", "unchecked" })
    @Override
    public List<RentTurnVO> listUnsubmitted(RentTurnQuery rentTurnQuery) throws DaoException {
        try {
            return this.queryForList("RentTurn.listUnsubmitted",rentTurnQuery);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @SuppressWarnings({ "deprecation", "unchecked" })
    @Override
    public List<RentTurnVO> listUnReview(RentTurnQuery rentTurnQuery) throws DaoException {
        try {
            return this.queryForList("RentTurn.listUnReview",rentTurnQuery);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public RentTurnVO getPermissions(RentTurnQuery query) throws DaoException {
        try {
            return (RentTurnVO) this.queryForObject("RentTurn.getPermissions",query);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

}
