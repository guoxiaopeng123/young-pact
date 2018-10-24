package com.young.pact.manager.purchasehouse.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.tools.common.redis.client.RedisClient;
import com.tools.common.redis.exception.RedisAccessException;
import com.young.common.exception.DaoException;
import com.young.house.api.service.rpc.house.HouseRpcService;
import com.young.pact.common.constant.ErrorPactConsts;
import com.young.pact.common.constant.RedisConsts;
import com.young.pact.common.exception.PactManagerExcepion;
import com.young.pact.dao.purchasehouse.PurchaseHouseDao;
import com.young.pact.domain.purchasehouse.PurchaseHouseDO;
import com.young.pact.domain.purchasehouse.PurchaseHouseQuery;
import com.young.pact.domain.purchasehouse.PurchaseHouseVO;
import com.young.pact.manager.purchasehouse.PurchaseHouseManager;
/**
 * 
* @ClassName: PurchaseHouseManagerImpl
* @Description: TODO( 托进房源信息)
* @author HeZeMin
* @date 2018年8月1日 下午4:41:27
*
 */
@Component("purchaseHouseManager")
public class PurchaseHouseManagerImpl implements PurchaseHouseManager {
    /**************声明区*****************/
    private static final Log LOG = LogFactory.getLog(PurchaseHouseManagerImpl.class);
    private RedisClient redisClient;
    private PurchaseHouseDao purchaseHouseDao;
    private HouseRpcService houseRpcService;
    private PlatformTransactionManager transactionManager;
    /**************方法区*****************/
    
    @Override
    public void saveHouseRedis(PurchaseHouseDO purchaseHouseDO, String redisKey) {
        try {
            redisClient.setObject(redisKey, RedisConsts.PURCHASE_TIME, purchaseHouseDO);
        } catch (RedisAccessException e) {
            LOG.error(ErrorPactConsts.REDIS_SET_EROR + redisKey, e);
            throw new PactManagerExcepion(ErrorPactConsts.REDIS_SET_EROR + redisKey, e);
        }
    }
    @Override
    public PurchaseHouseDO getHouseRedis(String redisKey) {
        try {
            if (redisClient.exists(redisKey)) {
                PurchaseHouseDO purchaseHouseDO = redisClient.getObject(redisKey);
                if(null != purchaseHouseDO){
                    return purchaseHouseDO;
                }
            }
        } catch (RedisAccessException e) {
            LOG.error(ErrorPactConsts.REDIS_GET_EROR + redisKey, e);
            throw new PactManagerExcepion(ErrorPactConsts.REDIS_GET_EROR + redisKey, e);
        }
        return null;
    }
    @Override
    public PurchaseHouseVO getPurchaseHouse(String purchaseCode) {
        try {
            return purchaseHouseDao.getPurchaseHouse(purchaseCode);
        } catch (DaoException e) {
            LOG.error(ErrorPactConsts.GET_PURCHASE_HOUSE_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.GET_PURCHASE_HOUSE_ERROR, e);
        }
    }
    @Override
    public boolean updatePurchaseHouse(final PurchaseHouseDO purchaseHouseDO) {
        
        TransactionTemplate transactionTemplate =  new TransactionTemplate(transactionManager);
        return transactionTemplate.execute(new TransactionCallback<Boolean>() {
            @Override
            public Boolean doInTransaction(TransactionStatus status) {
                try {
                    int flag = purchaseHouseDao.updatePurchaseHouse(purchaseHouseDO);
                    if(flag > 0){
                        return true;
                    }else{
                        status.setRollbackOnly();
                        return false;
                    }
                } catch (DaoException e) {
                    status.setRollbackOnly();
                    LOG.error(ErrorPactConsts.UPDATE_PURCHASE_HOUSE_ERROR, e);
                    throw new PactManagerExcepion(ErrorPactConsts.UPDATE_PURCHASE_HOUSE_ERROR, e);
                }
            }
        });
    }
    @Override
    public boolean exitPurchaseHouseByParam(PurchaseHouseQuery purchaseHouseQuery) {
        try {
            PurchaseHouseVO purchaseHouseVO = purchaseHouseDao.getPurchaseHouseByParam(purchaseHouseQuery);
            if(null == purchaseHouseVO){
                return false;
            }
            return true;
        } catch (DaoException e) {
            LOG.error(ErrorPactConsts.GET_PURCHASE_HOUSE_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.GET_PURCHASE_HOUSE_ERROR, e);
        }
    }
    /**************get/set*****************/
    public RedisClient getRedisClient() {
        return redisClient;
    }
    public void setRedisClient(RedisClient redisClient) {
        this.redisClient = redisClient;
    }
    public PurchaseHouseDao getPurchaseHouseDao() {
        return purchaseHouseDao;
    }
    public void setPurchaseHouseDao(PurchaseHouseDao purchaseHouseDao) {
        this.purchaseHouseDao = purchaseHouseDao;
    }
    public HouseRpcService getHouseRpcService() {
        return houseRpcService;
    }
    public void setHouseRpcService(HouseRpcService houseRpcService) {
        this.houseRpcService = houseRpcService;
    }
    public PlatformTransactionManager getTransactionManager() {
        return transactionManager;
    }
    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }
}
