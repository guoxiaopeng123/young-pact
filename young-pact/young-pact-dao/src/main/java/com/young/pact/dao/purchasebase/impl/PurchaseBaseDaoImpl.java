package com.young.pact.dao.purchasebase.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tools.common.ibatis.base.BaseDao;
import com.young.common.exception.DaoException;
import com.young.pact.dao.purchasebase.PurchaseBaseDao;
import com.young.pact.domain.purchasebase.PurchaseBaseDO;
import com.young.pact.domain.purchasebase.PurchaseBaseQuery;
import com.young.pact.domain.purchasebase.PurchaseBaseVO;
/**
 * 
* @ClassName: PurchaseBaseDaoImpl
* @Description: TODO( 托进合同)
* @author HeZeMin
* @date 2018年8月2日 下午8:49:50
*
 */
@SuppressWarnings("all")
@Repository("purchaseBaseDao")
public class PurchaseBaseDaoImpl extends BaseDao implements PurchaseBaseDao {

    @Override
    public Long savePurchaseBase(PurchaseBaseDO purchaseBaseDO) throws DaoException {
        try {
            return (Long) this.insert("PurchaseBase.savePurchaseBase", purchaseBaseDO);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public int countByDeclareCode(String declareCode) throws DaoException {
        try {
            return (int) this.queryForObject("PurchaseBase.countByDeclareCode", declareCode);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public int countPurchaseBase(PurchaseBaseQuery query) throws DaoException {
        try {
            return (int) this.queryForObject("PurchaseBase.countPurchaseBase", query);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<PurchaseBaseVO> listPurchaseBase(PurchaseBaseQuery query) throws DaoException {
        try {
            return this.queryForList("PurchaseBase.listPurchaseBase", query);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public PurchaseBaseVO getPurchaseBase(String purchaseCode) throws DaoException {
        try {
            return (PurchaseBaseVO) this.queryForObject("PurchaseBase.getPurchaseBase", purchaseCode);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public int removePurchaseBase(PurchaseBaseDO purchaseBaseDO) throws DaoException {
        try {
            return this.update("PurchaseBase.removePurchaseBase", purchaseBaseDO);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public int updateState(PurchaseBaseDO purchaseBaseDO) throws DaoException {
        try {
            return this.update("PurchaseBase.updateState", purchaseBaseDO);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public int updatePurchaseBase(PurchaseBaseDO purchaseBaseDO) throws DaoException {
        try {
            return this.update("PurchaseBase.updatePurchaseBase", purchaseBaseDO);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<PurchaseBaseVO> listPurchaseBaseByParam(PurchaseBaseQuery purchaseBaseQuery) throws DaoException {
        try {
            return this.queryForList("PurchaseBase.listPurchaseBaseByParam", purchaseBaseQuery);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public int updateTerminationState(PurchaseBaseDO purchaseBaseDO) throws DaoException {
        try {
            return this.update("PurchaseBase.updateTerminationState", purchaseBaseDO);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public int updateStandard(PurchaseBaseDO purchaseBaseDO) throws DaoException {
        try {
            return this.update("PurchaseBase.updateStandard", purchaseBaseDO);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public PurchaseBaseVO getPermissions(PurchaseBaseQuery query) throws DaoException {
        try {
            return (PurchaseBaseVO) this.queryForObject("PurchaseBase.getPermissions", query);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<PurchaseBaseVO> listUnsubmitted(PurchaseBaseQuery purchaseBaseQuery) throws DaoException {
        try {
            return this.queryForList("PurchaseBase.listUnsubmitted", purchaseBaseQuery);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<PurchaseBaseVO> listUnReview(PurchaseBaseQuery purchaseBaseQuery) throws DaoException {
        try {
            return this.queryForList("PurchaseBase.listUnReview", purchaseBaseQuery);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<PurchaseBaseVO> listUnCreateRoom() throws DaoException{
        try {
            return this.queryForList("PurchaseBase.listUnCreateRoom");
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<PurchaseBaseVO> listRejectUnApply() throws DaoException {
        try {
            return this.queryForList("PurchaseBase.listRejectUnApply");
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<PurchaseBaseVO> listPurchaseExpir() throws DaoException {
        try {
            return this.queryForList("PurchaseBase.listPurchaseExpir");
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }
}
