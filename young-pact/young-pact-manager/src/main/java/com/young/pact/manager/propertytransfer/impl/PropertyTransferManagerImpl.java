package com.young.pact.manager.propertytransfer.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.tools.common.redis.client.RedisClient;
import com.tools.common.redis.exception.RedisAccessException;
import com.young.common.exception.DaoException;
import com.young.pact.common.constant.ErrorPactConsts;
import com.young.pact.common.constant.RedisConsts;
import com.young.pact.common.exception.PactManagerExcepion;
import com.young.pact.dao.commongoods.CommonGoodsDao;
import com.young.pact.dao.commonmeterread.CommonMeterReadDao;
import com.young.pact.domain.commongoods.GoodsVO;
import com.young.pact.domain.commonmeterread.CommonMeterReadVO;
import com.young.pact.manager.propertytransfer.PropertyTransferManager;
/**
 * 
* @ClassName: PropertyTransferManagerImpl
* @Description: TODO( 物业交接)
* @author HeZeMin
* @date 2018年8月2日 下午3:32:57
*
 */
@Component("propertyTransferManager")
public class PropertyTransferManagerImpl implements PropertyTransferManager {
    /**************声明区*****************/
    private static final Log LOG = LogFactory.getLog(PropertyTransferManagerImpl.class);
    private RedisClient redisClient;
    private CommonMeterReadDao commonMeterReadDao;
    private CommonGoodsDao commonGoodsDao;
    /**************方法区*****************/
    
    @Override
    public void savePropertyTransferRedis(Map<String, Object> map, String redisKey) {
        try {
            redisClient.setObject(redisKey, RedisConsts.PURCHASE_TIME, map);
        } catch (RedisAccessException e) {
            LOG.error(ErrorPactConsts.REDIS_SET_EROR + redisKey, e);
            throw new PactManagerExcepion(ErrorPactConsts.REDIS_SET_EROR + redisKey, e);
        }
    }
    @Override
    public Map<String, Object> getPropertyTransferRedis(String redisKey) {
        try {
            if (redisClient.exists(redisKey)) {
                Map<String, Object> map = redisClient.getObject(redisKey);
                if(null != map && map.size() > 0){
                    return map;
                }
            }
        } catch (RedisAccessException e) {
            LOG.error(ErrorPactConsts.REDIS_GET_EROR + redisKey, e);
            throw new PactManagerExcepion(ErrorPactConsts.REDIS_GET_EROR + redisKey, e);
        }
        return null;
    }
    
    @Override
    public List<CommonMeterReadVO> listCommonMeterReadByPactCode(String pactCode) {
        try {
            List<CommonMeterReadVO> commonMeterReadVOs = commonMeterReadDao.listCommonMeterReadByPactCode(pactCode);
            return commonMeterReadVOs;
        } catch (DaoException e) {
            LOG.error(ErrorPactConsts.QUERY_COMMONMETERREAD_BY_PACTCODE_ERROR , e);
            throw new PactManagerExcepion(ErrorPactConsts.QUERY_COMMONMETERREAD_BY_PACTCODE_ERROR, e);
        }
    }
    @Override
    public List<GoodsVO> listCommonGoodsByPactCode(String pactCode) {
        try {
            return commonGoodsDao.listCommonGoodsByPactCode(pactCode);
        } catch (DaoException e) {
            LOG.error(ErrorPactConsts.QUERY_COMMONMGOODS_BY_PACTCODE_ERROR , e);
            throw new PactManagerExcepion(ErrorPactConsts.QUERY_COMMONMGOODS_BY_PACTCODE_ERROR, e);
        }
    }
    
    /**************get/set*****************/
    public RedisClient getRedisClient() {
        return redisClient;
    }
    public void setRedisClient(RedisClient redisClient) {
        this.redisClient = redisClient;
    }
    public CommonMeterReadDao getCommonMeterReadDao() {
        return commonMeterReadDao;
    }
    public void setCommonMeterReadDao(CommonMeterReadDao commonMeterReadDao) {
        this.commonMeterReadDao = commonMeterReadDao;
    }
    public CommonGoodsDao getCommonGoodsDao() {
        return commonGoodsDao;
    }
    public void setCommonGoodsDao(CommonGoodsDao commonGoodsDao) {
        this.commonGoodsDao = commonGoodsDao;
    }
    
}
