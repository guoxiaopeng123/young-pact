package com.young.pact.manager.purchasehouse.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.tools.common.redis.client.RedisClient;
import com.tools.common.redis.exception.RedisAccessException;
import com.tools.common.rocketmq.client.RocketmqClient;
import com.tools.common.util.json.JsonUtil;
import com.young.common.domain.ApiResult;
import com.young.common.exception.DaoException;
import com.young.house.api.service.rpc.house.HouseRpcService;
import com.young.pact.common.constant.ErrorPactConsts;
import com.young.pact.common.constant.NormalConstant;
import com.young.pact.common.constant.RedisConsts;
import com.young.pact.common.constant.SerializeTypeConts;
import com.young.pact.common.constant.StringConsts;
import com.young.pact.common.exception.PactManagerExcepion;
import com.young.pact.dao.purchasehouse.PurchaseHouseOwnerDao;
import com.young.pact.domain.purchasehouse.PurchaseHouseOwnerDO;
import com.young.pact.domain.purchasehouse.PurchaseHouseOwnerVO;
import com.young.pact.manager.purchasehouse.PurchaseHouseOwnerManager;
import com.young.sso.api.domain.result.rpc.personal.PersonalResult;
import com.young.sso.api.service.rpc.personal.PersonalRpcService;
/**
 * 
* @ClassName: PurchaseHouseOwnerManagerImpl
* @Description: TODO( 托进业主信息)
* @author HeZeMin
* @date 2018年8月1日 下午4:43:26
*
 */
@Component("purchaseHouseOwnerManager")
public class PurchaseHouseOwnerManagerImpl implements PurchaseHouseOwnerManager {
    /**************声明区*****************/
    private static final Log LOG = LogFactory.getLog(PurchaseHouseOwnerManagerImpl.class);
    private RedisClient redisClient;
    private PurchaseHouseOwnerDao purchaseHouseOwnerDao;
    private PlatformTransactionManager transactionManager;
    private HouseRpcService houseRpcService;
    private PersonalRpcService personalRpcService;
    private RocketmqClient rocketmqClient;
    /**************方法区*****************/
    
    @Override
    public void saveHouseOwnerRedis(PurchaseHouseOwnerDO purchaseHouseOwnerDO, String redisKey) {
        try {
            redisClient.setObject(redisKey, RedisConsts.PURCHASE_TIME, purchaseHouseOwnerDO);
        } catch (RedisAccessException e) {
            LOG.error(ErrorPactConsts.REDIS_SET_EROR + redisKey, e);
            throw new PactManagerExcepion(ErrorPactConsts.REDIS_SET_EROR + redisKey, e);
        }
    }
    @Override
    public PurchaseHouseOwnerDO getHouseOwnerRedis(String redisKey) {
        try {
            if (redisClient.exists(redisKey)) {
                PurchaseHouseOwnerDO purchaseHouseOwnerDO = redisClient.getObject(redisKey);
                if(null != purchaseHouseOwnerDO){
                    return purchaseHouseOwnerDO;
                }
            }
        } catch (RedisAccessException e) {
            LOG.error(ErrorPactConsts.REDIS_GET_EROR + redisKey, e);
            throw new PactManagerExcepion(ErrorPactConsts.REDIS_GET_EROR + redisKey, e);
        }
        return null;
    }
    @Override
    public PurchaseHouseOwnerVO getPurchaseHouseOwner(String purchaseCode) {
        try {
            return purchaseHouseOwnerDao.getPurchaseHouseOwner(purchaseCode);
        } catch (DaoException e) {
            LOG.error(ErrorPactConsts.GET_PURCHASE_HOUSE_OWNER_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.GET_PURCHASE_HOUSE_OWNER_ERROR, e);
        }
    }
    @Override
    public boolean updatePurchaseHouseOwner(final PurchaseHouseOwnerDO purchaseHouseOwnerDO) {
        TransactionTemplate transactionTemplate =  new TransactionTemplate(transactionManager);
        return transactionTemplate.execute(new TransactionCallback<Boolean>() {
            @Override
            public Boolean doInTransaction(TransactionStatus status) {
                try {
                    int flag = purchaseHouseOwnerDao.updatePurchaseHouseOwner(purchaseHouseOwnerDO);
                    if(flag > 0){
                        saveOperateLog(purchaseHouseOwnerDO);
                        return true;
                    }else{
                        status.setRollbackOnly();
                        return false;
                    }
                } catch (DaoException e) {
                    status.setRollbackOnly();
                    LOG.error(ErrorPactConsts.UPDATE_PURCHASE_HOUSE_OWNER_ERROR, e);
                    throw new PactManagerExcepion(ErrorPactConsts.UPDATE_PURCHASE_HOUSE_OWNER_ERROR, e);
                }
            }
            /**
             * @Title: saveOperateLog
             * @Description: TODO( 保存修改托进合同业主日志 )
             * @author GuoXiaoPeng
             * @param purchaseHouseOwnerDO 托进合同业主信息
              */
            private void saveOperateLog(PurchaseHouseOwnerDO purchaseHouseOwnerDO) {
                ApiResult<PersonalResult> result = personalRpcService.getPersonInterface(purchaseHouseOwnerDO.getCreateName());
                PersonalResult personalResult = result.getData();
                Map<String, Object> map = new HashMap<String, Object>();
                map.put(NormalConstant.SERVICETYPE, SerializeTypeConts.PURCHASE);
                map.put(NormalConstant.SERVICECODE, purchaseHouseOwnerDO.getOwnerCode());
                map.put(NormalConstant.OPERATETYPE, StringConsts.UPDATE);
                map.put(NormalConstant.OPERATEPIN, purchaseHouseOwnerDO.getCreateName());
                map.put(NormalConstant.OPERATENAME, personalResult.getEmployeeName());
                map.put(NormalConstant.OPERATEDEPT, personalResult.getDeptName());
                map.put(NormalConstant.OPERATETIME, new Date());
                String json = JsonUtil.toJson(map);
                rocketmqClient.sendMessage(NormalConstant.TOPIC_OPERATE, NormalConstant.TAG_OPERATE_SAVE, NormalConstant.KEY_OPERATE_PACT, json);
            }
        });
    }
    /**************get/set*****************/
    public RedisClient getRedisClient() {
        return redisClient;
    }
    public void setRedisClient(RedisClient redisClient) {
        this.redisClient = redisClient;
    }
    public PurchaseHouseOwnerDao getPurchaseHouseOwnerDao() {
        return purchaseHouseOwnerDao;
    }
    public void setPurchaseHouseOwnerDao(PurchaseHouseOwnerDao purchaseHouseOwnerDao) {
        this.purchaseHouseOwnerDao = purchaseHouseOwnerDao;
    }
    public PlatformTransactionManager getTransactionManager() {
        return transactionManager;
    }
    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }
    public HouseRpcService getHouseRpcService() {
        return houseRpcService;
    }
    public void setHouseRpcService(HouseRpcService houseRpcService) {
        this.houseRpcService = houseRpcService;
    }
    public PersonalRpcService getPersonalRpcService() {
        return personalRpcService;
    }
    public void setPersonalRpcService(PersonalRpcService personalRpcService) {
        this.personalRpcService = personalRpcService;
    }
    public RocketmqClient getRocketmqClient() {
        return rocketmqClient;
    }
    public void setRocketmqClient(RocketmqClient rocketmqClient) {
        this.rocketmqClient = rocketmqClient;
    }
}
