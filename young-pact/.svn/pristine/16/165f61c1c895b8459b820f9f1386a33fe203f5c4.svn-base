package com.young.pact.dao.purchasetermination.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tools.common.ibatis.base.BaseDao;
import com.young.common.exception.DaoException;
import com.young.pact.dao.purchasetermination.PurchaseTerminationDao;
import com.young.pact.domain.purchasebase.PurchaseBaseVO;
import com.young.pact.domain.purchasetermination.PurchaseTerminationBaseVO;
import com.young.pact.domain.purchasetermination.PurchaseTerminationDO;
import com.young.pact.domain.purchasetermination.PurchaseTerminationEditVO;
import com.young.pact.domain.purchasetermination.PurchaseTerminationQuery;
import com.young.pact.domain.purchasetermination.PurchaseTerminationVO;

/**
 * 
* @ClassName: PurchaseTerminationDaoImpl
* @Description: 托进解约协议
* @author SunYiXuan
* @date 2018年8月9日 下午4:38:26
*
 */

@SuppressWarnings("all")
@Repository("purchaseTerminationDao")
public class PurchaseTerminationDaoImpl extends BaseDao implements PurchaseTerminationDao{

    @Override
    public int countByPurchaseCode(String purchaseCode) throws DaoException {
        try {
            return (int) this.queryForObject("PurchaseTermination.countByPurchaseCode", purchaseCode);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Long savePurchaseTermination(PurchaseTerminationDO purchaseTerminationDO) throws DaoException {
        try {
            return (Long) this.insert("PurchaseTermination.savePurchaseTermination", purchaseTerminationDO);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public int countPurchaseTermination(PurchaseTerminationQuery query) throws DaoException {
        try {
            return (int) this.queryForObject("PurchaseTermination.countPurchaseTermination", query);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<PurchaseTerminationVO> listPurchaseTermination(PurchaseTerminationQuery query) throws DaoException {
        try {
            return this.queryForList("PurchaseTermination.listPurchaseTermination", query);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public PurchaseTerminationBaseVO getPurchaseTerminationBase(String terminationCode) throws DaoException {
        try {
            return (PurchaseTerminationBaseVO) this.queryForObject("PurchaseTermination.getPurchaseTerminationBase", terminationCode);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public PurchaseTerminationEditVO getPurchaseTerminationEdit(String terminationCode) throws DaoException {
        try {
            return (PurchaseTerminationEditVO) this.queryForObject("PurchaseTermination.getPurchaseTerminationEdit", terminationCode);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public PurchaseTerminationVO getPurchaseTermination(String terminationCode) throws DaoException {
        try {
            return (PurchaseTerminationVO) this.queryForObject("PurchaseTermination.getPurchaseTermination", terminationCode);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public int updatePurchaseTermination(PurchaseTerminationDO terminationDO) throws DaoException {
        try {
            return this.update("PurchaseTermination.updatePurchaseTermination", terminationDO);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public int removePurchaseTermination(PurchaseTerminationDO terminationDO) throws DaoException {
        try {
            return this.update("PurchaseTermination.removePurchaseTermination", terminationDO);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public int updateState(PurchaseTerminationDO terminationDO) throws DaoException {
        try {
            return this.update("PurchaseTermination.updateState", terminationDO);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public PurchaseTerminationBaseVO getPermissions(PurchaseTerminationQuery query) throws DaoException {
        try {
            return (PurchaseTerminationBaseVO) this.queryForObject("PurchaseTermination.getPermissions", query);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<PurchaseTerminationVO> listPurTerminationByParam(PurchaseTerminationQuery query) throws DaoException {
        try {
            return this.queryForList("PurchaseTermination.listPurTerminationByParam", query);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<PurchaseTerminationVO> listUnReview(PurchaseTerminationQuery query) throws DaoException {
        try {
            return this.queryForList("PurchaseTermination.listUnReview", query);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<PurchaseTerminationVO> listUnsubmitted(PurchaseTerminationQuery query) throws DaoException {
        try {
            return this.queryForList("PurchaseTermination.listUnsubmitted", query);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

}
