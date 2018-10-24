package com.young.pact.dao.purchaserentfree.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tools.common.ibatis.base.BaseDao;
import com.young.common.exception.DaoException;
import com.young.pact.dao.purchaserentfree.PurchaseRentfreeDao;
import com.young.pact.domain.purchaserentfree.PurchaseRentfreeDO;
import com.young.pact.domain.purchaserentfree.PurchaseRentfreeVO;
/**
 * 
* @ClassName: PurchaseRentfreeDaoImpl
* @Description: TODO( 托进免租期)
* @author HeZeMin
* @date 2018年8月2日 下午10:02:56
*
 */
@SuppressWarnings("all")
@Repository("purchaseRentfreeDao")
public class PurchaseRentfreeDaoImpl extends BaseDao implements PurchaseRentfreeDao {

    @Override
    public int savePurchaseRentfree(List<PurchaseRentfreeDO> purchaseRentfreeDOs) throws DaoException {
        try {
            return this.batchInsert("PurchaseRentfree.savePurchaseRentfree", purchaseRentfreeDOs);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<PurchaseRentfreeVO> listPurchaseRentfree(String purchaseCode) throws DaoException {
        try {
            return this.queryForList("PurchaseRentfree.listPurchaseRentfree", purchaseCode);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public int removePurchaseRentfree(String purchaseCode) throws DaoException {
        try {
            return this.delete("PurchaseRentfree.removePurchaseRentfree", purchaseCode);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public int removeNotPurchaseRentfree(Map<String, Object> map) throws DaoException {
        try {
            return this.delete("PurchaseRentfree.removeNotPurchaseRentfree", map);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public int updatePurchaseRentfree(List<PurchaseRentfreeDO> purchaseRentfreeDOs) throws DaoException {
        try {
            return this.batchUpdate("PurchaseRentfree.updatePurchaseRentfree", purchaseRentfreeDOs);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

}
