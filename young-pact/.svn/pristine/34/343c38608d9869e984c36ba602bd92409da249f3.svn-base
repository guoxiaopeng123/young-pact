package com.young.pact.dao.purchasehouse.impl;

import org.springframework.stereotype.Repository;

import com.tools.common.ibatis.base.BaseDao;
import com.young.common.exception.DaoException;
import com.young.pact.dao.purchasehouse.PurchaseHouseOwnerDao;
import com.young.pact.domain.purchasehouse.PurchaseHouseOwnerDO;
import com.young.pact.domain.purchasehouse.PurchaseHouseOwnerVO;
import com.young.pact.domain.purchasehouse.PurchaseHouseVO;
/**
 * 
* @ClassName: PurchaseHouseOwnerDaoImpl
* @Description: TODO( 托进合同业主)
* @author HeZeMin
* @date 2018年8月2日 下午9:09:24
*
 */
@SuppressWarnings("all")
@Repository("purchaseHouseOwnerDao")
public class PurchaseHouseOwnerDaoImpl extends BaseDao implements PurchaseHouseOwnerDao {

    @Override
    public Long savePurchaseHouseOwner(PurchaseHouseOwnerDO purchaseHouseOwnerDO) throws DaoException {
        try {
            return (Long) this.insert("PurchaseHouseOwner.savePurchaseHouseOwner", purchaseHouseOwnerDO);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public PurchaseHouseOwnerVO getPurchaseHouseOwner(String purchaseCode) throws DaoException {
        try {
            return (PurchaseHouseOwnerVO) this.queryForObject("PurchaseHouseOwner.getPurchaseHouseOwner", purchaseCode);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public int removePurchaseHouseOwner(String purchaseCode) throws DaoException {
        try {
            return this.delete("PurchaseHouseOwner.removePurchaseHouseOwner", purchaseCode);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public int updatePurchaseHouseOwner(PurchaseHouseOwnerDO purchaseHouseOwnerDO) throws DaoException {
        try {
            return this.update("PurchaseHouseOwner.updatePurchaseHouseOwner", purchaseHouseOwnerDO);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

}
