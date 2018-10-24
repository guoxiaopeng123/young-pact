package com.young.pact.manager.rentcontinued.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.young.common.page.PageBean;
import com.young.common.page.PageTableBean;
import com.young.common.util.DateUtil;
import com.young.pact.common.constant.ErrorPactConsts;
import com.young.pact.common.constant.PactTypeConsts;
import com.young.pact.common.constant.RedisConsts;
import com.young.pact.common.constant.StringConsts;
import com.young.pact.common.exception.PactManagerExcepion;
import com.young.pact.dao.commonbelonger.CommonBelongerDao;
import com.young.pact.dao.commoncostrule.CommonCostRuleDao;
import com.young.pact.dao.commongoods.CommonGoodsDao;
import com.young.pact.dao.commongoods.CommonGoodsPicDao;
import com.young.pact.dao.commonmeterread.CommonMeterReadDao;
import com.young.pact.dao.commonmeterread.CommonMeterReadPicDao;
import com.young.pact.dao.commonpic.CommonPicDao;
import com.young.pact.dao.commontradingrecord.CommonTradingRecordDao;
import com.young.pact.dao.financereceiptpay.FinanceReceiptPayDao;
import com.young.pact.dao.rentbase.RentBaseDao;
import com.young.pact.dao.rentcommonowner.CommonOwnerDao;
import com.young.pact.dao.rentcontinued.RentContinuedDao;
import com.young.pact.dao.rentcustomer.RentCustomerDao;
import com.young.pact.dao.rentroom.RentRoomDao;
import com.young.pact.domain.commonbelonger.CommonBelongerDO;
import com.young.pact.domain.commonbelonger.CommonBelongerQuery;
import com.young.pact.domain.commonbelonger.CommonBelongerVO;
import com.young.pact.domain.commoncostrule.CommonCostRuleDO;
import com.young.pact.domain.commoncostrule.CommonCostRuleVO;
import com.young.pact.domain.commongoods.CommonGoodsDO;
import com.young.pact.domain.commongoods.CommonGoodsPicDO;
import com.young.pact.domain.commonmeterread.CommonMeterReadDO;
import com.young.pact.domain.commonmeterread.CommonMeterReadPicDO;
import com.young.pact.domain.commonpic.CommonPicDO;
import com.young.pact.domain.commonpic.CommonPicVO;
import com.young.pact.domain.commontradingrecord.CommonTradingRecordDO;
import com.young.pact.domain.financereceiptpay.FinanceReceiptPayDO;
import com.young.pact.domain.financereceiptpay.FinanceReceiptPayQuery;
import com.young.pact.domain.financereceiptpay.FinanceReceiptPayVO;
import com.young.pact.domain.rentbase.RentBaseDO;
import com.young.pact.domain.rentbase.RentBaseVO;
import com.young.pact.domain.rentbase.RentPactDO;
import com.young.pact.domain.rentbase.RentPactVO;
import com.young.pact.domain.rentcontinued.RentContinuedDO;
import com.young.pact.domain.rentcontinued.RentContinuedQuery;
import com.young.pact.domain.rentcontinued.RentContinuedVO;
import com.young.pact.domain.rentcustomer.RentCustomerDO;
import com.young.pact.domain.rentcustomer.RentCustomerVO;
import com.young.pact.domain.rentroom.RentRoomDO;
import com.young.pact.domain.rentroom.RentRoomVO;
import com.young.pact.manager.rentcontinued.RentContinuedManager;

/**
 * @描述 : 续签协议
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月10日 下午8:46:39
 */
@Component("rentContinuedManager")
public class RentContinuedManagerImpl implements RentContinuedManager {
    private static Log LOG = LogFactoryImpl.getLog(RentContinuedManagerImpl.class);
    private RentContinuedDao rentContinuedDao;
    private RedisClient redisClient;
    private PlatformTransactionManager transactionManager;
    private RentBaseDao rentBaseDao;
    private CommonBelongerDao commonBelongerDao;
    private CommonCostRuleDao commonCostRuleDao;
    private CommonGoodsDao commonGoodsDao;
    private CommonGoodsPicDao commonGoodsPicDao;
    private CommonMeterReadDao commonMeterReadDao;
    private CommonMeterReadPicDao commonMeterReadPicDao;
    private CommonPicDao commonPicDao;
    private FinanceReceiptPayDao financeReceiptPayDao;
    private RentRoomDao rentRoomDao;
    private RentCustomerDao rentCustomerDao;
    private CommonOwnerDao commonOwnerDao;
    private CommonTradingRecordDao commonTradingRecordDao;
    @Override
    public void saveRentContinuedRedis(RentContinuedDO rentContinuedDO, String redisKey) {
        try {
            redisClient.setObject(redisKey, RedisConsts.RENT_TIME, rentContinuedDO);
        } catch (RedisAccessException e) {
            LOG.error(ErrorPactConsts.REDIS_SET_EROR + redisKey, e);
            throw new PactManagerExcepion(ErrorPactConsts.REDIS_SET_EROR + redisKey, e);
        }
    }

    @Override
    public RentContinuedVO getRentContinuedRedis(String redisKey) {
        RentContinuedVO rentContinuedVO = new RentContinuedVO();
        try {
            if(redisClient.exists(redisKey)){
                RentContinuedDO rentContinuedDO = redisClient.getObject(redisKey);
                BeanUtils.copyProperties(rentContinuedDO, rentContinuedVO);
                List<CommonPicVO> commonPicVOs = new ArrayList<>();
                BeanUtils.copyListProperties(rentContinuedDO.getUrlList(), commonPicVOs, CommonPicVO.class);
                rentContinuedVO.setUrlList(commonPicVOs);
                return rentContinuedVO;
            }else{
                return null;
            }
        } catch (RedisAccessException e) {
            LOG.error(ErrorPactConsts.REDIS_GET_EROR + redisKey, e);
            throw new PactManagerExcepion(ErrorPactConsts.REDIS_GET_EROR + redisKey, e);
        }
    }

    @Override
    public void saveRentContinuedPactRedis(String redisKey, RentPactDO rentPactDO) {
        try {
            redisClient.setObject(redisKey, RedisConsts.PURCHASE_TIME, rentPactDO);
        } catch (RedisAccessException e) {
            LOG.error(ErrorPactConsts.REDIS_SET_EROR + redisKey, e);
            throw new PactManagerExcepion(ErrorPactConsts.REDIS_SET_EROR + redisKey, e);
        }
    }

    @Override
    public RentPactVO getRentContinuedPactRedis(String redisKey) {
        try {
            if (redisClient.exists(redisKey)) {
                RentPactDO rentPactDO = redisClient.getObject(redisKey);
                RentPactVO rentPactVO = new RentPactVO();
                //合同收支(集合)
                RentBaseVO rentBaseVO =new RentBaseVO();
                BeanUtils.copyProperties(rentPactDO.getPact(), rentBaseVO);
                rentPactVO.setPact(rentBaseVO);
                //合同收支规则(集合)
                List<CommonCostRuleVO> pactReceiptPayList = new ArrayList<>();
                BeanUtils.copyListProperties(rentPactDO.getPactReceiptPayList(), pactReceiptPayList, CommonCostRuleVO.class);
                rentPactVO.setPactReceiptPayList(pactReceiptPayList);
                //收支(集合)
                List<FinanceReceiptPayVO> receiptPayList = new ArrayList<>();
                BeanUtils.copyListProperties(rentPactDO.getReceiptPayList(), receiptPayList, FinanceReceiptPayVO.class);
                rentPactVO.setReceiptPayList(receiptPayList);
                //合同照片(集合)
                List<CommonPicVO> urlList = new ArrayList<>();;
                BeanUtils.copyListProperties(rentPactDO.getUrlList(), urlList, CommonPicVO.class);
                rentPactVO.setUrlList(urlList);
                return rentPactVO;
            } else {
                return null;
            }
        } catch (RedisAccessException e) {
            LOG.error(ErrorPactConsts.REDIS_SET_EROR + redisKey, e);
            throw new PactManagerExcepion(ErrorPactConsts.REDIS_SET_EROR + redisKey, e);
        } catch (Exception e) {
            LOG.error(CommonEnum.SYSTEM_EXCEPTION + redisKey, e);
            throw new PactManagerExcepion(CommonEnum.SYSTEM_EXCEPTION.getMessage(), e);
        }
    }
    
    @Override
    public Integer countAllProtocolByPactCode(String rentPactCode) {
        try {
            Integer count = rentContinuedDao.countAllProtocolByPactCode(rentPactCode);
            return count;
        } catch (DaoException e) {
            LOG.error(ErrorPactConsts.COUNT_PROTOCOL_ERROR , e);
            throw new PactManagerExcepion(ErrorPactConsts.COUNT_PROTOCOL_ERROR , e);
        } catch (Exception e) {
            LOG.error(CommonEnum.SYSTEM_EXCEPTION , e);
            throw new PactManagerExcepion(CommonEnum.SYSTEM_EXCEPTION.getMessage(), e);
        }
    }
    
    @Override
    public boolean saveRentContinuedPact(final RentContinuedDO rentContinuedDO,
            final List<CommonMeterReadDO> protocolCommonMeterReadDOs,
            final List<CommonGoodsDO> protocolCommonGoodsDOs,
            final RentBaseDO rentBaseDO,
            final List<CommonCostRuleDO> newPactReceiptPayList,
            final List<FinanceReceiptPayDO> newReceiptPayList,
            final List<CommonMeterReadDO> newCommonMeterReadDOs,
            final List<CommonGoodsDO> newCommonGoodsDOs,
            final RentCustomerDO rentCustomerDO,
            final List<CommonPicDO> commonPicDOs,
            final List<CommonBelongerDO> commonBelongerDOList,
            final RentRoomDO rentRoomDO) {
        TransactionTemplate transactionTemplate = getDataSourceTransactionManager();
        return transactionTemplate.execute(new TransactionCallback<Boolean>() {

            @Override
            public Boolean doInTransaction(TransactionStatus status) {
                try {
                    Long rentContinuedId = rentContinuedDao.saveRentContinued(rentContinuedDO);
                    if(rentContinuedId<=0){
                        status.setRollbackOnly();
                        return false;
                    }
                    // 保存协议抄表信息
                    for (CommonMeterReadDO commonMeterReadDO : protocolCommonMeterReadDOs) {
                        Long meterReadId = commonMeterReadDao.saveCommonMeterRead(commonMeterReadDO);
                        if (meterReadId > 0) {
                            for (CommonMeterReadPicDO commonMeterReadPicDO : commonMeterReadDO.getCommonMeterReadPicList()) {
                                commonMeterReadPicDO.setMeterReadId(meterReadId);
                                commonMeterReadPicDO.setCreateName(rentContinuedDO.getCreateName());
                                commonMeterReadPicDO.setIp(rentContinuedDO.getIp());
                            }
                            // 保存抄表图片
                            int meterReadPicFlag = commonMeterReadPicDao.saveCommonMeterReadPic(commonMeterReadDO.getCommonMeterReadPicList());
                            if (!(meterReadPicFlag > 0)) {
                                status.setRollbackOnly();
                                return false;
                            }
                        } else {
                            status.setRollbackOnly();
                            return false;
                        }
                    }
                    // 保存协议物品信息
                    for (CommonGoodsDO commonGoodsDO : protocolCommonGoodsDOs) {
                        Long goodsId = commonGoodsDao.saveCommonGoods(commonGoodsDO);
                        if (goodsId > 0) {
                            for (CommonGoodsPicDO commonGoodsPicDO : commonGoodsDO.getCommonGoodsPicList()) {
                                 commonGoodsPicDO.setCreateName(rentContinuedDO.getCreateName());
                                 commonGoodsPicDO.setIp(rentContinuedDO.getIp());
                                 commonGoodsPicDO.setGoodsId(goodsId);
                            }
                            // 保存物品图片
                            
                            int goodsPicFlag = commonGoodsPicDao.saveCommonGoodsPic(commonGoodsDO.getCommonGoodsPicList());
                            if (!(goodsPicFlag > 0)) {
                                status.setRollbackOnly();
                                return false;
                            }
                        } else {
                            status.setRollbackOnly();
                            return false;
                        }
                    }
                    //托出合同
                    Long rentBaseId = rentBaseDao.saveRentBase(rentBaseDO);
                    if(rentBaseId < 0){
                        status.setRollbackOnly();
                        return false;
                    }
                    //托出房间
                    Long rentRoomFlag = rentRoomDao.saveRentRoom(rentRoomDO);
                    if(rentRoomFlag < 0){
                        status.setRollbackOnly();
                        return false;
                    }
                    if(null!=newPactReceiptPayList&&newPactReceiptPayList.size()>0){
                        int commonCostRuleId = commonCostRuleDao.saveCommonCostRule(newPactReceiptPayList);
                        if(commonCostRuleId<=0){
                            status.setRollbackOnly();
                            return false;
                        }
                    }
                    if(null!=newReceiptPayList&&newReceiptPayList.size()>0){
                        int financeReceiptPayId = financeReceiptPayDao.saveFinanceReceiptPay(newReceiptPayList);
                        if(financeReceiptPayId<=0){
                            status.setRollbackOnly();
                            return false;
                        }
                    }
                    // 保存合同抄表信息
                    for (CommonMeterReadDO commonMeterReadDO : newCommonMeterReadDOs) {
                        Long meterReadId = commonMeterReadDao.saveCommonMeterRead(commonMeterReadDO);
                        if (meterReadId > 0) {
                            for (CommonMeterReadPicDO commonMeterReadPicDO : commonMeterReadDO.getCommonMeterReadPicList()) {
                                commonMeterReadPicDO.setMeterReadId(meterReadId);
                                commonMeterReadPicDO.setCreateName(rentContinuedDO.getCreateName());
                                commonMeterReadPicDO.setIp(rentContinuedDO.getIp());
                            }
                            // 保存抄表图片
                            int meterReadPicFlag = commonMeterReadPicDao.saveCommonMeterReadPic(commonMeterReadDO.getCommonMeterReadPicList());
                            if (!(meterReadPicFlag > 0)) {
                                status.setRollbackOnly();
                                return false;
                            }
                        } else {
                            status.setRollbackOnly();
                            return false;
                        }
                    }
                    // 保存合同物品信息
                    for (CommonGoodsDO commonGoodsDO : newCommonGoodsDOs) {
                        Long goodsId = commonGoodsDao.saveCommonGoods(commonGoodsDO);
                        if (goodsId > 0) {
                            for (CommonGoodsPicDO commonGoodsPicDO : commonGoodsDO.getCommonGoodsPicList()) {
                                 commonGoodsPicDO.setCreateName(rentContinuedDO.getCreateName());
                                 commonGoodsPicDO.setIp(rentContinuedDO.getIp());
                                 commonGoodsPicDO.setGoodsId(goodsId);
                            }
                            // 保存物品图片
                            
                            int goodsPicFlag = commonGoodsPicDao.saveCommonGoodsPic(commonGoodsDO.getCommonGoodsPicList());
                            if (!(goodsPicFlag > 0)) {
                                status.setRollbackOnly();
                                return false;
                            }
                        } else {
                            status.setRollbackOnly();
                            return false;
                        }
                    }
                    Long rentCustomerId = rentCustomerDao.saveRentCustomer(rentCustomerDO);
                    if(rentCustomerId <= 0){
                        status.setRollbackOnly();
                        return false;
                    }
                    int commonPicId = commonPicDao.saveCommonPic(commonPicDOs);
                    if(commonPicId <= 0){
                        status.setRollbackOnly();
                        return false;
                    }
                    int commonBelongerId = commonBelongerDao.saveCommonBelonger(commonBelongerDOList);
                    if(commonBelongerId <= 0){
                        status.setRollbackOnly();
                        return false;
                    }
                    return true;
                } catch (DaoException e) {
                    status.setRollbackOnly();
                    LOG.error(ErrorPactConsts.SAVE_RENTER_CONTUINED_ERROR, e);
                    throw new PactManagerExcepion(ErrorPactConsts.SAVE_RENTER_CONTUINED_ERROR, e);
                } catch (Exception e) {
                    status.setRollbackOnly();
                    LOG.error(ErrorPactConsts.SAVE_RENTER_CONTUINED_ERROR, e);
                    throw new PactManagerExcepion(ErrorPactConsts.SAVE_RENTER_CONTUINED_ERROR, e);
                }
            }
        });
    }
    
    @Override
    public PageBean<RentContinuedVO> listParam(RentContinuedQuery query) {
        try {
            Integer count =  rentContinuedDao.count(query);
            PageBean<RentContinuedVO> page = new PageTableBean<>(query.getPageIndex(), query.getPageSize());
            if(null != count && count != 0) {
                page.setTotalItem(count);
                int startRow = page.getStartRow();
                if(startRow > 0) {
                    startRow -= 1;
                }
                query.setStartRow(startRow);
                List<RentContinuedVO> rentTurnVOs = rentContinuedDao.listParam(query);
                page.addAll(rentTurnVOs);
            }
            return page;
        } catch (DaoException e) {
            // TODO: handle exception
            LOG.error(ErrorPactConsts.QUERY_CONTINUED_TABLE_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.QUERY_CONTINUED_TABLE_ERROR,e);
        }
    }
    
    @Override
    public RentContinuedVO getRentContinuedByRenewCode(String renewCode) {
        try {
            return rentContinuedDao.getRentContinuedByRenewCode(renewCode);
        } catch (DaoException e) {
            // TODO: handle exception
            LOG.error(ErrorPactConsts.QUERY_RENTCONTINUED_DEARIL_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.QUERY_RENTCONTINUED_DEARIL_ERROR,e);
        }
    }
    
    @Override
    public boolean updateRentContinuedStateByCode(final RentContinuedDO rentContinuedDO) {

        TransactionTemplate transactionTemplate = getDataSourceTransactionManager();
        return transactionTemplate.execute(new TransactionCallback<Boolean>() {

            @Override
            public Boolean doInTransaction(TransactionStatus status) {
                try {
                    Long flag = rentContinuedDao.updateRentContinuedStateByCode(rentContinuedDO);
                    if(flag > 0){
                        if(3==rentContinuedDO.getState()){
                            /***********************新合同审核通过后在合同列表显示并且审核状态改为审核通过  start****************/
                            RentBaseDO newRentBaseDO = new RentBaseDO();
                            newRentBaseDO.setRentPactCode(rentContinuedDO.getNewPactCode());
                            newRentBaseDO.setIsEffective(0);
                            newRentBaseDO.setState(3);
                            newRentBaseDO.setModifiedName(rentContinuedDO.getModifiedName());
                            newRentBaseDO.setIp(rentContinuedDO.getIp());
                            Long newPactId = rentBaseDao.updateRentBaseByCode(newRentBaseDO);
                            if(newPactId<=0){
                                status.setRollbackOnly();
                                return false;
                            }
                            /***********************新合同审核通过后在合同列表显示  end****************/
                            /**********************把新合同的账单is_valid设置成1已生效 根据合同编码修改收支生效状态为已生效，合同审核通过时调用  start*****************************/
                            FinanceReceiptPayDO financeReceiptPayDO = new FinanceReceiptPayDO();
                            financeReceiptPayDO.setPactCode(rentContinuedDO.getNewPactCode());
                            financeReceiptPayDO.setModifiedName(rentContinuedDO.getModifiedName());
                            financeReceiptPayDO.setIp(rentContinuedDO.getIp());
                            int financeReceiptCount = financeReceiptPayDao.updateValidByCode(financeReceiptPayDO);
                            if(financeReceiptCount<=0){
                                status.setRollbackOnly();
                                return false;
                            }
                            /**********************把新合同的账单is_valid设置成1已生效  根据合同编码修改收支生效状态为已生效，合同审核通过时调用  end*****************************/
                            /***************老合同处理 start  *******************/
                            RentBaseDO oldRentBaseDO = new RentBaseDO();
                            oldRentBaseDO.setRentPactCode(rentContinuedDO.getPirmaryPactCode());
                            oldRentBaseDO.setDueState(2);
                            oldRentBaseDO.setCancelState(6);
                            oldRentBaseDO.setModifiedName(rentContinuedDO.getModifiedName());
                            oldRentBaseDO.setIp(rentContinuedDO.getIp());
                            Long oldPactId = rentBaseDao.updateRentBaseByCode(oldRentBaseDO);
                            if(oldPactId<=0){
                                status.setRollbackOnly();
                                return false;
                            }
                            //将续签时间以后（不含当天）的账单都删除
                            boolean oldreceiptyFlag = deleteOldPactFinanceReceiptPay(rentContinuedDO);
                            if(!oldreceiptyFlag){
                                status.setRollbackOnly();
                                return false;
                            }
                            /***************老合同处理 end   *******************/
                            RentRoomVO rentRoomVO = rentRoomDao.getRommByRentCode(rentContinuedDO.getNewPactCode());
                            /********************* 保存交易记录    start*************************/
                            RentBaseVO rentBaseVO = rentBaseDao.getRentBaseByCode(rentContinuedDO.getNewPactCode());
                            CommonBelongerQuery belongerQuery = new CommonBelongerQuery();
                            belongerQuery.setPactCode(rentContinuedDO.getNewPactCode());
                            belongerQuery.setUserRole(StringConsts.BELONGER_DEAL);
                            CommonBelongerVO commonBelongerVO = commonBelongerDao.getBelongerByParam(belongerQuery);
                            CommonTradingRecordDO commonTradingRecordDO = new CommonTradingRecordDO();
                            commonTradingRecordDO.setRoomCode(rentRoomVO.getRoomCode());
                            commonTradingRecordDO.setPactCode(rentContinuedDO.getRenewCode());
                            commonTradingRecordDO.setType( PactTypeConsts.RENT_RENEW);
                            commonTradingRecordDO.setPrice(rentBaseVO.getPrice());
                            commonTradingRecordDO.setDate(rentBaseVO.getSigningTime());
                            commonTradingRecordDO.setUserName(commonBelongerVO.getUserName());
                            commonTradingRecordDO.setDeptName(commonBelongerVO.getDeptName());
                            Long tradingRecordId = commonTradingRecordDao.saveTradingRecord(commonTradingRecordDO);
                            if(tradingRecordId <= 0){
                                status.setRollbackOnly();
                                return false;
                            }
                            /********************* 保存交易记录    end*************************/
                            return true;
                        }else{
                            return true;
                        }
                    }else{
                        return false;
                    }
                } catch (DaoException e) {
                    status.setRollbackOnly();
                    LOG.error(ErrorPactConsts.UPDATE_RENTCONTINUED_BASE_ERROR, e);
                    throw new PactManagerExcepion(ErrorPactConsts.UPDATE_RENTCONTINUED_BASE_ERROR,e);
                }
            }
            /**
             * @Title: deleteOldFinanceReceiptPay
             * @Description: TODO(将大于签约日期的老合同的账单都删除)
             * @author GuoXiaoPeng
             * @param rentContinuedDO 老合同编码+转租日期
             * @return 成功或失败
              */
            private boolean deleteOldPactFinanceReceiptPay(RentContinuedDO rentContinuedDO) {
                List<FinanceReceiptPayVO> financeReceiptPayVOs = null;
                try {
                    FinanceReceiptPayQuery financeReceiptPayQuery = new FinanceReceiptPayQuery();
                    financeReceiptPayQuery.setPactCode(rentContinuedDO.getPirmaryPactCode());
                    Date date = DateUtil.getDayByDay(rentContinuedDO.getRenewTime(), 1);
                    financeReceiptPayQuery.setCostTimeStr(DateUtil.formatDate(date));
                    financeReceiptPayVOs = financeReceiptPayDao.listReceiptPayByDate(financeReceiptPayQuery);
                    if(null!=financeReceiptPayVOs&&financeReceiptPayVOs.size()>0){
                        FinanceReceiptPayDO oldFinanceReceiptPayDO = new FinanceReceiptPayDO();
                        oldFinanceReceiptPayDO.setPactCode(rentContinuedDO.getPirmaryPactCode());
                        oldFinanceReceiptPayDO.setCostTime(date);
                        oldFinanceReceiptPayDO.setModifiedName(rentContinuedDO.getModifiedName());
                        oldFinanceReceiptPayDO.setIp(rentContinuedDO.getIp());
                        oldFinanceReceiptPayDO.setPactCode(rentContinuedDO.getPirmaryPactCode());
                        int receiptPayCount = financeReceiptPayDao.removeOldPactFinanceReceiptPay(oldFinanceReceiptPayDO);
                        if(receiptPayCount>0){
                           return true;
                        }else{
                            return false;
                        }
                    }else{
                        return true;
                    }
                } catch (DaoException e) {
                    LOG.error(ErrorPactConsts.QUERY_RECEIPTPAY_BYPACT_ERROR, e);
                    throw new PactManagerExcepion(ErrorPactConsts.QUERY_RECEIPTPAY_BYPACT_ERROR,e);
                }
                catch (Exception e) {
                    LOG.error(ErrorPactConsts.UPDATE_RENTCONTINUED_BASE_ERROR, e);
                    throw new PactManagerExcepion(ErrorPactConsts.UPDATE_RENTCONTINUED_BASE_ERROR,e);
                }
            }
        });
    }
    
    @Override
    public boolean updateRentContinuedByRenewCode(final RentContinuedDO rentContinuedDO, 
            final List<CommonBelongerDO> commonBelongerDOs, 
            final List<CommonPicDO> commonPicDOList) {
        TransactionTemplate transactionTemplate = getDataSourceTransactionManager();
        return transactionTemplate.execute(new TransactionCallback<Boolean>() {

            @Override
            public Boolean doInTransaction(TransactionStatus status) {
                try {
                    Integer rentContinuedId = rentContinuedDao.updateRentContinuedByRenewCode(rentContinuedDO);
                    if(rentContinuedId<=0){
                        status.setRollbackOnly();
                        return false;
                    }
                    // 对图片的 增删改
                    Map<String, Object> commonPicMap = new HashMap<>();
                    commonPicMap.put("pactCode", rentContinuedDO.getRenewCode());
                    commonPicMap.put("commonPicList", commonPicDOList);
                    commonPicDao.removeNotCommonPic(commonPicMap);
                    if(null != commonPicDOList && commonPicDOList.size() > 0){
                        commonPicDao.updateCommonPic(commonPicDOList);
                        List<CommonPicDO> commonPicDOs = new ArrayList<>();
                        for (CommonPicDO commonPicDO : commonPicDOList) {
                            if(null == commonPicDO.getId() || !(commonPicDO.getId() > 0)){
                                commonPicDOs.add(commonPicDO);
                            }
                        }
                        if(commonPicDOs.size() > 0){
                            commonPicDao.saveCommonPic(commonPicDOs);
                        }
                    }
                    int commonBelongerId = commonBelongerDao.batchUpdateCommonBelonger(commonBelongerDOs);
                    if(commonBelongerId<=0){
                        status.setRollbackOnly();
                        return false;
                    }
                    return true;
                } catch (DaoException e) {
                    status.setRollbackOnly();
                    LOG.error(ErrorPactConsts.UPDATE_RENTCONTINUED_BASE_ERROR, e);
                    throw new PactManagerExcepion(ErrorPactConsts.UPDATE_RENTCONTINUED_BASE_ERROR,e);
                }
            }
        });
    }
    
    @Override
    public boolean removeRentContinuedByReletCode(final RentContinuedDO rentContinuedDO) {
        TransactionTemplate transactionTemplate = getDataSourceTransactionManager();
        return transactionTemplate.execute(new TransactionCallback<Boolean>() {

            @Override
            public Boolean doInTransaction(TransactionStatus status) {
                try {
                    /**********************续签协议相关表信息删除  start********************************/
                    //续签协议
                    int rentContinuedId = rentContinuedDao.removeRentContinuedByReletCode(rentContinuedDO);
                    if(rentContinuedId<=0){
                        status.setRollbackOnly();
                        return false;
                    }
                    //协议抄表信息
                    int protocolCommonMeterRead = commonMeterReadDao.removeCommonMeterRead(rentContinuedDO.getRenewCode());
                    if(protocolCommonMeterRead<0){
                        status.setRollbackOnly();
                        return false;
                    }
                    //协议物品信息
                    int removeCommonGoods = commonGoodsDao.removeCommonGoods(rentContinuedDO.getRenewCode());
                    if(removeCommonGoods<0){
                        status.setRollbackOnly();
                        return false;
                    }
                    //协议图片
                    int protocolCommonPic = commonPicDao.removeCommonPic(rentContinuedDO.getRenewCode());
                    if(protocolCommonPic<0){
                        status.setRollbackOnly();
                        return false;
                    }
                    //协议负责人
                    int protocolBelonger = commonBelongerDao.removeCommonBelonger(rentContinuedDO.getRenewCode());
                    if(protocolBelonger<0){
                        status.setRollbackOnly();
                        return false;
                    }
                    /**********************续签协议相关表信息删除  end********************************/
                    /**********************新合同相关表信息删除  start********************************/
                    //托出合同
                    RentBaseDO rentBaseDO =new RentBaseDO();
                    rentBaseDO.setIp(rentContinuedDO.getIp());
                    rentBaseDO.setModifiedName(rentContinuedDO.getModifiedName());
                    rentBaseDO.setRentPactCode(rentContinuedDO.getNewPactCode());
                    Long newRentBaseByCode = rentBaseDao.deleteRentBaseByCode(rentBaseDO);
                    if(newRentBaseByCode<=0){
                        status.setRollbackOnly();
                        return false;
                    }
                    //合同抄表信息
                    int newCommonMeterRead = commonMeterReadDao.removeCommonMeterRead(rentContinuedDO.getNewPactCode());
                    if(newCommonMeterRead<0){
                        status.setRollbackOnly();
                        return false;
                    }
                    //合同物品信息
                    int newCommonGoods = commonGoodsDao.removeCommonGoods(rentContinuedDO.getNewPactCode());
                    if(newCommonGoods<0){
                        status.setRollbackOnly();
                        return false;
                    }
                    //合同图片
                    int newCommonPic = commonPicDao.removeCommonPic(rentContinuedDO.getNewPactCode());
                    if(newCommonPic<0){
                        status.setRollbackOnly();
                        return false;
                    }
                    //合同负责人
                    int newBelonger = commonBelongerDao.removeCommonBelonger(rentContinuedDO.getNewPactCode());
                    if(newBelonger<0){
                        status.setRollbackOnly();
                        return false;
                    }
                    //合同租客
                    RentCustomerVO rentCustomerVO = rentCustomerDao.getRentCustomerByPactCode(rentContinuedDO.getNewPactCode());
                    int rentCustomerCount = rentCustomerDao.removeRentCustomer(rentContinuedDO.getNewPactCode());
                    if(rentCustomerCount<=0){
                        status.setRollbackOnly();
                        return false;
                    }
                    commonOwnerDao.removeCommonOwner(rentCustomerVO.getRenterCode());
                    //合同收支
                    FinanceReceiptPayDO newfinanceReceiptPayDO = new FinanceReceiptPayDO();
                    newfinanceReceiptPayDO.setIp(rentContinuedDO.getIp());
                    newfinanceReceiptPayDO.setModifiedName(rentContinuedDO.getModifiedName());
                    newfinanceReceiptPayDO.setPactCode(rentContinuedDO.getNewPactCode());
                    int newFinanceReceiptPay = financeReceiptPayDao.removeFinanceReceiptPay(newfinanceReceiptPayDO);
                    if(newFinanceReceiptPay < 0){
                        status.setRollbackOnly();
                        return false;
                    }
                    //合同收支规则
                    int removeCommonCostRule = commonCostRuleDao.removeCommonCostRule(rentContinuedDO.getNewPactCode());
                    if(removeCommonCostRule<0){
                        status.setRollbackOnly();
                        return false;
                    }
                    /**********************新合同相关表信息删除  end********************************/
                    return true;
                } catch (DaoException e) {
                    // TODO: handle exception
                    status.setRollbackOnly();
                    LOG.error(ErrorPactConsts.DELETE_RENTCONTINUED_ERROR , e);
                    throw new PactManagerExcepion(ErrorPactConsts.DELETE_RENTCONTINUED_ERROR , e);
                }
            }
        });
    }
    
    @Override
    public List<RentContinuedVO> listUnsubmitted(RentContinuedQuery rentContinuedQuery) {
        try {
            return rentContinuedDao.listUnsubmitted(rentContinuedQuery);
        } catch (Exception e) {
            LOG.error(ErrorPactConsts.LIST_RENTCONTINUED_UNSUBMITTED_ERROR , e);
            throw new PactManagerExcepion(ErrorPactConsts.LIST_RENTCONTINUED_UNSUBMITTED_ERROR , e);
        }
    }
    
    @Override
    public List<RentContinuedVO> listUnReview(RentContinuedQuery rentContinuedQuery) {
        try {
            return rentContinuedDao.listUnReview(rentContinuedQuery);
        } catch (Exception e) {
            LOG.error(ErrorPactConsts.LIST_RENTCONTINUED_UNREVIEW_ERROR , e);
            throw new PactManagerExcepion(ErrorPactConsts.LIST_RENTCONTINUED_UNREVIEW_ERROR , e);
        }
    }
    
    @Override
    public List<RentContinuedVO> listRentcontinuedByParam(RentContinuedQuery rentContinuedQuery) {
        try {
            return rentContinuedDao.listRentcontinuedByParam(rentContinuedQuery);
        } catch (Exception e) {
            LOG.error(ErrorPactConsts.LIST_RENTCONTINUED_BYPARAM_ERROR , e);
            throw new PactManagerExcepion(ErrorPactConsts.LIST_RENTCONTINUED_BYPARAM_ERROR , e);
        }
    }
    
    @Override
    public boolean getPermissions(RentContinuedQuery rentContinuedQuery) {
        try {
            RentContinuedVO rentContinuedVO = rentContinuedDao.getPermissions(rentContinuedQuery);
            if(null == rentContinuedVO){
                return false;
            }
            return true;
        } catch (Exception e) {
            LOG.error(ErrorPactConsts.QUERY_RENTCONTINUED_DEARIL_ERROR , e);
            throw new PactManagerExcepion(ErrorPactConsts.QUERY_RENTCONTINUED_DEARIL_ERROR , e);
        }
    }
    private TransactionTemplate getDataSourceTransactionManager() {
        return new TransactionTemplate(transactionManager);
    }

    public RentContinuedDao getRentContinuedDao() {
        return rentContinuedDao;
    }

    public void setRentContinuedDao(RentContinuedDao rentContinuedDao) {
        this.rentContinuedDao = rentContinuedDao;
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

    public RentBaseDao getRentBaseDao() {
        return rentBaseDao;
    }

    public void setRentBaseDao(RentBaseDao rentBaseDao) {
        this.rentBaseDao = rentBaseDao;
    }

    public CommonBelongerDao getCommonBelongerDao() {
        return commonBelongerDao;
    }

    public void setCommonBelongerDao(CommonBelongerDao commonBelongerDao) {
        this.commonBelongerDao = commonBelongerDao;
    }

    public CommonCostRuleDao getCommonCostRuleDao() {
        return commonCostRuleDao;
    }

    public void setCommonCostRuleDao(CommonCostRuleDao commonCostRuleDao) {
        this.commonCostRuleDao = commonCostRuleDao;
    }

    public CommonGoodsDao getCommonGoodsDao() {
        return commonGoodsDao;
    }

    public void setCommonGoodsDao(CommonGoodsDao commonGoodsDao) {
        this.commonGoodsDao = commonGoodsDao;
    }

    public CommonGoodsPicDao getCommonGoodsPicDao() {
        return commonGoodsPicDao;
    }

    public void setCommonGoodsPicDao(CommonGoodsPicDao commonGoodsPicDao) {
        this.commonGoodsPicDao = commonGoodsPicDao;
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

    public CommonPicDao getCommonPicDao() {
        return commonPicDao;
    }

    public void setCommonPicDao(CommonPicDao commonPicDao) {
        this.commonPicDao = commonPicDao;
    }

    public FinanceReceiptPayDao getFinanceReceiptPayDao() {
        return financeReceiptPayDao;
    }

    public void setFinanceReceiptPayDao(FinanceReceiptPayDao financeReceiptPayDao) {
        this.financeReceiptPayDao = financeReceiptPayDao;
    }

    public RentRoomDao getRentRoomDao() {
        return rentRoomDao;
    }

    public void setRentRoomDao(RentRoomDao rentRoomDao) {
        this.rentRoomDao = rentRoomDao;
    }

    public RentCustomerDao getRentCustomerDao() {
        return rentCustomerDao;
    }

    public void setRentCustomerDao(RentCustomerDao rentCustomerDao) {
        this.rentCustomerDao = rentCustomerDao;
    }

    public CommonOwnerDao getCommonOwnerDao() {
        return commonOwnerDao;
    }

    public void setCommonOwnerDao(CommonOwnerDao commonOwnerDao) {
        this.commonOwnerDao = commonOwnerDao;
    }

    public CommonTradingRecordDao getCommonTradingRecordDao() {
        return commonTradingRecordDao;
    }

    public void setCommonTradingRecordDao(CommonTradingRecordDao commonTradingRecordDao) {
        this.commonTradingRecordDao = commonTradingRecordDao;
    }

    

}
