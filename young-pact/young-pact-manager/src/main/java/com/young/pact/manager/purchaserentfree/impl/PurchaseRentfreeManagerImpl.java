package com.young.pact.manager.purchaserentfree.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.young.common.exception.DaoException;
import com.young.pact.common.constant.ErrorPactConsts;
import com.young.pact.common.exception.PactManagerExcepion;
import com.young.pact.dao.purchaserentfree.PurchaseRentfreeDao;
import com.young.pact.domain.purchaserentfree.PurchaseRentfreeVO;
import com.young.pact.manager.purchaserentfree.PurchaseRentfreeManager;
/**
 * 
* @ClassName: PurchaseRentfreeManagerImpl
* @Description: TODO( 免租期)
* @author HeZeMin
* @date 2018年8月6日 下午4:10:32
*
 */
@Component("purchaseRentfreeManager")
public class PurchaseRentfreeManagerImpl implements PurchaseRentfreeManager {
    /**************声明区*****************/
    private static final Log LOG = LogFactory.getLog(PurchaseRentfreeManagerImpl.class);
    private PurchaseRentfreeDao purchaseRentfreeDao;
    /**************方法区*****************/
    @Override
    public List<PurchaseRentfreeVO> listPurchaseRentfree(String purchaseCode) {
        try {
            return purchaseRentfreeDao.listPurchaseRentfree(purchaseCode);
        } catch (DaoException e) {
            LOG.error(ErrorPactConsts.LIST_PURCHASE_RENTFREE_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.LIST_PURCHASE_RENTFREE_ERROR, e);
        }
    }
    /**************get/set*****************/
    public PurchaseRentfreeDao getPurchaseRentfreeDao() {
        return purchaseRentfreeDao;
    }
    public void setPurchaseRentfreeDao(PurchaseRentfreeDao purchaseRentfreeDao) {
        this.purchaseRentfreeDao = purchaseRentfreeDao;
    }
    
}
