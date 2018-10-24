package com.young.pact.manager.commonmeterread.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.LogFactoryImpl;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.tools.common.redis.client.RedisClient;
import com.tools.common.redis.exception.RedisAccessException;
import com.tools.common.util.convert.BeanUtils;
import com.young.common.dict.CommonEnum;
import com.young.common.exception.DaoException;
import com.young.pact.common.constant.ErrorPactConsts;
import com.young.pact.common.constant.RedisConsts;
import com.young.pact.common.exception.PactManagerExcepion;
import com.young.pact.dao.commonmeterread.CommonMeterReadDao;
import com.young.pact.dao.commonmeterread.CommonMeterReadPicDao;
import com.young.pact.domain.commonmeterread.CommonMeterReadDO;
import com.young.pact.domain.commonmeterread.CommonMeterReadPicDO;
import com.young.pact.domain.commonmeterread.CommonMeterReadPicVO;
import com.young.pact.domain.commonmeterread.CommonMeterReadQuery;
import com.young.pact.domain.commonmeterread.CommonMeterReadVO;
import com.young.pact.domain.commonmeterread.MeterReadGoodsDO;
import com.young.pact.domain.commonmeterread.MeterReadGoodsVO;
import com.young.pact.manager.commonmeterread.CommonMeterReadManager;

/**
 * @描述 : 合同物业交接抄表信息
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月2日 下午5:58:55
 */
@Component("commonMeterReadManager")
public class CommonMeterReadManagerImpl implements CommonMeterReadManager{

    private static Log LOG = LogFactoryImpl.getLog(CommonMeterReadManagerImpl.class);
    private PlatformTransactionManager transactionManager;
    private RedisClient redisClient;
    private CommonMeterReadDao commonMeterReadDao;
    private CommonMeterReadPicDao commonMeterReadPicDao;
    @Override
    public void saveMeterGoodsRedis(String key,MeterReadGoodsDO meterReadGoodsDO) {
        try {
            redisClient.setObject(key,RedisConsts.PURCHASE_TIME, meterReadGoodsDO);
        } catch (RedisAccessException e) {
            LOG.error(ErrorPactConsts.REDIS_SET_EROR + key , e);
            throw new PactManagerExcepion(ErrorPactConsts.REDIS_SET_EROR + key, e);
        }
    }

    @Override
    public MeterReadGoodsVO getMeterGoodsRedis(String key) {
        try {
            if(redisClient.exists(key)){
                MeterReadGoodsVO meterReadGoodsVO = new MeterReadGoodsVO();
                BeanUtils.copyProperties(redisClient.getObject(key), meterReadGoodsVO);
                return meterReadGoodsVO;
            }else{
                return null;
            }
        } catch (RedisAccessException e) {
            LOG.error(ErrorPactConsts.REDIS_SET_EROR + key, e);
            throw new PactManagerExcepion(ErrorPactConsts.REDIS_SET_EROR + key, e);
        } catch (Exception e) {
            LOG.error(CommonEnum.SYSTEM_EXCEPTION + key, e);
            throw new PactManagerExcepion(CommonEnum.SYSTEM_EXCEPTION.getMessage(), e);
        }
    }

    @Override
    public boolean insertMeterRead(final CommonMeterReadDO commonMeterReadDO) {
        TransactionTemplate transactionTemplate =  new TransactionTemplate(transactionManager);
        return transactionTemplate.execute(new TransactionCallback<Boolean>() {
            @Override
            public Boolean doInTransaction(TransactionStatus status) {
                try {
                    Long meterReadId = commonMeterReadDao.saveCommonMeterRead(commonMeterReadDO);
                    if(meterReadId<=0){
                        status.setRollbackOnly();
                        return false;
                    }
                    for(CommonMeterReadPicDO commonMeterReadPicDO:commonMeterReadDO.getCommonMeterReadPicList()){
                        commonMeterReadPicDO.setMeterReadId(meterReadId);
                        commonMeterReadPicDO.setCreateName(commonMeterReadDO.getCreateName());
                        commonMeterReadPicDO.setIp(commonMeterReadDO.getIp());
                    }
                    // 保存抄表图片
                    int meterReadPicFlag = commonMeterReadPicDao.saveCommonMeterReadPic(commonMeterReadDO.getCommonMeterReadPicList());
                    if (!(meterReadPicFlag > 0)) {
                        status.setRollbackOnly();
                        return false;
                    }
                    return true;
                } catch (DaoException e) {
                    status.setRollbackOnly();
                    status.setRollbackOnly();
                    LOG.error(ErrorPactConsts.SAVE_METERREAD_ERROR, e);
                    throw new PactManagerExcepion(ErrorPactConsts.SAVE_METERREAD_ERROR, e);
                } catch (Exception e) {
                    status.setRollbackOnly();
                    LOG.error(ErrorPactConsts.SAVE_METERREAD_ERROR, e);
                    throw new PactManagerExcepion(ErrorPactConsts.SAVE_METERREAD_ERROR, e);
                }
            }
        });
    }

    @Override
    public CommonMeterReadVO getMeterRead(Long id) {
        CommonMeterReadVO commonMeterReadVO = new CommonMeterReadVO();
        try {
            commonMeterReadVO = commonMeterReadDao.getMeterReadById(id);
            List<CommonMeterReadPicVO> commonMeterReadPicVOs = commonMeterReadPicDao.listMeterReadByMeterId(id);
            commonMeterReadVO.setCommonMeterReadPicList(commonMeterReadPicVOs);
            return commonMeterReadVO;
        } catch (DaoException e) {
            LOG.error(ErrorPactConsts.QUERY_METERREAD_BYID_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.QUERY_METERREAD_BYID_ERROR, e);
        } catch (Exception e) {
            LOG.error(ErrorPactConsts.QUERY_METERREAD_BYID_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.QUERY_METERREAD_BYID_ERROR, e);
        }
    }

    @Override
    public List<CommonMeterReadVO> listMeterReadByParam(CommonMeterReadQuery query) {
        List<CommonMeterReadVO> commonMeterReadVOs = new ArrayList<>();
        try {
            commonMeterReadVOs = commonMeterReadDao.listCommonMeterReadByPactCode(query.getPactCode());
            for(CommonMeterReadVO commonMeterReadVO:commonMeterReadVOs){
                List<CommonMeterReadPicVO> commonMeterReadPicVOs = new ArrayList<>();
                BeanUtils.copyListProperties(commonMeterReadVO.getCommonMeterReadPicList(), commonMeterReadPicVOs, CommonMeterReadPicVO.class);
                commonMeterReadVO.setCommonMeterReadPicList(commonMeterReadPicVOs);
            }
        }  catch (DaoException e) {
            LOG.error(ErrorPactConsts.LIST_METERREAD_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.LIST_METERREAD_ERROR, e);
        } catch (Exception e) {
            LOG.error(ErrorPactConsts.LIST_METERREAD_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.LIST_METERREAD_ERROR, e);
        }
        return commonMeterReadVOs;
    }

    @Override
    public boolean updateMeterRead(final CommonMeterReadDO commonMeterReadDO) {
        TransactionTemplate transactionTemplate =  new TransactionTemplate(transactionManager);
        return transactionTemplate.execute(new TransactionCallback<Boolean>() {
            @Override
            public Boolean doInTransaction(TransactionStatus status) {
                 try {
                     int commonMeterReadCount = commonMeterReadDao.updateMeterReadById(commonMeterReadDO);
                     if(commonMeterReadCount<=0){
                         status.setRollbackOnly();
                         return false;
                     }
                     int delCount = commonMeterReadPicDao.removeMeterReadByMeterId(commonMeterReadDO.getId());
                     if(delCount <=0){
                         status.setRollbackOnly();
                         return false;
                     }
                     int meterReadPicCount =  commonMeterReadPicDao.saveCommonMeterReadPic(commonMeterReadDO.getCommonMeterReadPicList());
                     if(meterReadPicCount<=0){
                         status.setRollbackOnly();
                         return false;
                     }
                     return true;
                } catch (DaoException e) {
                    LOG.error(ErrorPactConsts.UPDATE_METERREAD_ERROR, e);
                    throw new PactManagerExcepion(ErrorPactConsts.UPDATE_METERREAD_ERROR, e);
                } catch (Exception e) {
                    status.setRollbackOnly();
                    LOG.error(ErrorPactConsts.UPDATE_METERREAD_ERROR, e);
                    throw new PactManagerExcepion(ErrorPactConsts.UPDATE_METERREAD_ERROR, e);
                }
                 
            }
        });
    }

    @Override
    public boolean removeMeterRead(final CommonMeterReadDO commonMeterReadDO) {
        TransactionTemplate transactionTemplate =  new TransactionTemplate(transactionManager);
        return transactionTemplate.execute(new TransactionCallback<Boolean>() {
            @Override
            public Boolean doInTransaction(TransactionStatus status) {
                try {
                    int count = commonMeterReadDao.removeCommonMeterReadById(commonMeterReadDO);
                    if(count<=0){
                        status.setRollbackOnly();
                        return false;
                    }
                    int picCount = commonMeterReadPicDao.removeMeterReadByMeterId(commonMeterReadDO.getId());
                    if(picCount<=0){
                        status.setRollbackOnly();
                        return false;
                    }
                    return true;
                } catch (DaoException e) {
                    status.setRollbackOnly();
                    LOG.error(ErrorPactConsts.DELETE_METERREAD_ERROR, e);
                    throw new PactManagerExcepion(ErrorPactConsts.DELETE_METERREAD_ERROR, e);
                }
            }
        });
    }
    
    public RedisClient getRedisClient() {
        return redisClient;
    }

    public void setRedisClient(RedisClient redisClient) {
        this.redisClient = redisClient;
    }

    public PlatformTransactionManager getTransactionManager() {
        return transactionManager;
    }

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public CommonMeterReadDao getCommonMeterReadDao() {
        return commonMeterReadDao;
    }

    public void setCommonMeterReadDao(CommonMeterReadDao commonMeterReadDao) {
        this.commonMeterReadDao = commonMeterReadDao;
    }

    public CommonMeterReadPicDao getCommonMeterReadPicDao() {
        return commonMeterReadPicDao;
    }

    public void setCommonMeterReadPicDao(CommonMeterReadPicDao commonMeterReadPicDao) {
        this.commonMeterReadPicDao = commonMeterReadPicDao;
    }
}
