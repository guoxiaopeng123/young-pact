package com.young.pact.manager.purchasebase.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import com.young.common.dict.CommonEnum;
import com.young.common.domain.ApiResult;
import com.young.common.exception.DaoException;
import com.young.common.page.PageBean;
import com.young.common.page.PageTableBean;
import com.young.house.api.domain.param.rpc.house.UpdateHouseRpcParam;
import com.young.house.api.service.rpc.house.HouseRpcService;
import com.young.pact.common.constant.ErrorPactConsts;
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
import com.young.pact.dao.financeamortize.FinanceAmortizeDao;
import com.young.pact.dao.financereceiptpay.FinanceReceiptPayDao;
import com.young.pact.dao.purchasebase.PurchaseBaseDao;
import com.young.pact.dao.purchasehouse.PurchaseHouseDao;
import com.young.pact.dao.purchasehouse.PurchaseHouseOwnerDao;
import com.young.pact.dao.purchaserentfree.PurchaseRentfreeDao;
import com.young.pact.domain.commonbelonger.CommonBelongerDO;
import com.young.pact.domain.commoncostrule.CommonCostRuleDO;
import com.young.pact.domain.commongoods.CommonGoodsDO;
import com.young.pact.domain.commongoods.CommonGoodsPicDO;
import com.young.pact.domain.commonmeterread.CommonMeterReadDO;
import com.young.pact.domain.commonmeterread.CommonMeterReadPicDO;
import com.young.pact.domain.commonpic.CommonPicDO;
import com.young.pact.domain.financereceiptpay.FinanceReceiptPayDO;
import com.young.pact.domain.purchasebase.PurchaseBaseDO;
import com.young.pact.domain.purchasebase.PurchaseBaseQuery;
import com.young.pact.domain.purchasebase.PurchaseBaseVO;
import com.young.pact.domain.purchasehouse.PurchaseHouseDO;
import com.young.pact.domain.purchasehouse.PurchaseHouseOwnerDO;
import com.young.pact.domain.purchasehouse.PurchaseHouseOwnerVO;
import com.young.pact.domain.purchasehouse.PurchaseHouseVO;
import com.young.pact.domain.purchaserentfree.PurchaseRentfreeDO;
import com.young.pact.manager.purchasebase.PurchaseBaseManager;
import com.young.sso.api.domain.result.rpc.personal.PersonalResult;
import com.young.sso.api.service.rpc.personal.PersonalRpcService;
/**
 * 
* @ClassName: PurchaseBaseManagerImpl
* @Description: TODO( 托进合同)
* @author HeZeMin
* @date 2018年8月2日 上午10:59:44
*
 */
@Component("purchaseBaseManager")
public class PurchaseBaseManagerImpl implements PurchaseBaseManager {
    /**************声明区*****************/
    private static final Log LOG = LogFactory.getLog(PurchaseBaseManagerImpl.class);
    private RedisClient redisClient;
    private PlatformTransactionManager transactionManager;
    private PurchaseBaseDao purchaseBaseDao;
    private CommonBelongerDao commonBelongerDao;
    private CommonCostRuleDao commonCostRuleDao;
    private CommonGoodsDao commonGoodsDao;
    private CommonGoodsPicDao commonGoodsPicDao;
    private CommonMeterReadDao commonMeterReadDao;
    private CommonMeterReadPicDao commonMeterReadPicDao;
    private CommonPicDao commonPicDao;
    private FinanceReceiptPayDao financeReceiptPayDao;
    private PurchaseHouseDao purchaseHouseDao;
    private PurchaseHouseOwnerDao purchaseHouseOwnerDao;
    private PurchaseRentfreeDao purchaseRentfreeDao;
    private FinanceAmortizeDao financeAmortizeDao;
    private RocketmqClient rocketmqClient;
    private PersonalRpcService personalRpcService; 
    private HouseRpcService houseRpcService;
    /**************方法区*****************/
    
    
    @Override
    public void savePurchaseBaseRedis(Map<String, Object> map, String redisKey) {
        try {
            redisClient.setObject(redisKey, RedisConsts.PURCHASE_TIME, map);
        } catch (RedisAccessException e) {
            LOG.error(ErrorPactConsts.REDIS_SET_EROR + redisKey, e);
            throw new PactManagerExcepion(ErrorPactConsts.REDIS_SET_EROR + redisKey, e);
        }
    }
    @Override
    public Map<String, Object> getPurchaseBaseRedis(String redisKey) {
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
    public boolean savePurchaseBase(final PurchaseBaseDO purchaseBaseDO, final List<CommonCostRuleDO> commonCostRuleDOs, final List<PurchaseRentfreeDO> purchaseRentfreeDOList, 
            final List<FinanceReceiptPayDO> financeReceiptPayDOList, final List<CommonPicDO> commonPicDOList, final PurchaseHouseDO purchaseHouseDO, 
            final PurchaseHouseOwnerDO purchaseHouseOwnerDO, final List<CommonGoodsDO> commonGoodsDOList, final List<CommonMeterReadDO> commonMeterReadDOList, final List<CommonBelongerDO> commonBelongerDOList) {
        try {
            TransactionTemplate transactionTemplate =  new TransactionTemplate(transactionManager);
            return transactionTemplate.execute(new TransactionCallback<Boolean>() {
                @Override
                public Boolean doInTransaction(TransactionStatus status) {
                    try {
                        // 保存托进合同信息
                        Long purchaseId = purchaseBaseDao.savePurchaseBase(purchaseBaseDO);
                        if(purchaseId > 0){
                            // 保存托进合同收支规则
                            int costRuleFlag = commonCostRuleDao.saveCommonCostRule(commonCostRuleDOs);
                            if(costRuleFlag >= 0){
                                // 保存托进合同免租期信息
                                int rentfreeFlag = purchaseRentfreeDao.savePurchaseRentfree(purchaseRentfreeDOList);
                                if(rentfreeFlag >= 0){
                                    // 保存托进合同收支
                                    int receiptPayFlag = financeReceiptPayDao.saveFinanceReceiptPay(financeReceiptPayDOList);
                                    if(receiptPayFlag > 0){
                                        // 保存合同照片
                                        int commonPicFlag = commonPicDao.saveCommonPic(commonPicDOList);
                                        if(commonPicFlag > 0){
                                            // 保存托进合同房源信息
                                            Long houseId = purchaseHouseDao.savePurchaseHouse(purchaseHouseDO);
                                            if(houseId > 0){
                                                // 保存托进合同业主信息
                                                Long houseOwnerId = purchaseHouseOwnerDao.savePurchaseHouseOwner(purchaseHouseOwnerDO);
                                                if(houseOwnerId > 0){
                                                    // 保存合同责任人信息
                                                    int belongerFlag = commonBelongerDao.saveCommonBelonger(commonBelongerDOList);
                                                    if(belongerFlag > 0){
                                                        // 保存合同物品信息
                                                        for (CommonGoodsDO commonGoodsDO : commonGoodsDOList) {
                                                            Long goodsId = commonGoodsDao.saveCommonGoods(commonGoodsDO);
                                                            if(goodsId > 0){
                                                                for (CommonGoodsPicDO commonGoodsPicDO : commonGoodsDO.getCommonGoodsPicList()) {
                                                                    commonGoodsPicDO.setGoodsId(goodsId);
                                                                }
                                                                // 保存物品图片
                                                                int goodsPicFlag = commonGoodsPicDao.saveCommonGoodsPic(commonGoodsDO.getCommonGoodsPicList());
                                                                if(!(goodsPicFlag > 0)){
                                                                    status.setRollbackOnly();
                                                                    return false;
                                                                }
                                                            }else{
                                                                status.setRollbackOnly();
                                                                return false;
                                                            }
                                                        }
                                                        // 保存合同抄表信息
                                                        for (CommonMeterReadDO commonMeterReadDO : commonMeterReadDOList) {
                                                            Long meterReadId = commonMeterReadDao.saveCommonMeterRead(commonMeterReadDO);
                                                            if(meterReadId > 0){
                                                                for (CommonMeterReadPicDO commonMeterReadPicDO : commonMeterReadDO.getCommonMeterReadPicList()) {
                                                                    commonMeterReadPicDO.setMeterReadId(meterReadId);
                                                                }
                                                                // 保存抄表图片
                                                                int meterReadPicFlag = commonMeterReadPicDao.saveCommonMeterReadPic(commonMeterReadDO.getCommonMeterReadPicList());
                                                                if(!(meterReadPicFlag > 0)){
                                                                    status.setRollbackOnly();
                                                                    return false;
                                                                }
                                                            }else{
                                                                status.setRollbackOnly();
                                                                return false;
                                                            }
                                                        }
                                                        /*-------房源成交状态变成"签约中"-----start------------*/
                                                        ApiResult<Boolean> houseStateFlag = houseRpcService.updateState(purchaseHouseDO.getHouseCode(), StringConsts.DEALING);
                                                        if(!CommonEnum.REQUEST_SUCCESS.getCode().equals(houseStateFlag.getCode())){
                                                            LOG.error("同步房源成交状态变成签约中失败,操作对象：" + purchaseBaseDO.getHouseCode());
                                                            LOG.error("同步同步托进房源数据失败，失败原因：" + JsonUtil.toJson(houseStateFlag));
                                                            status.setRollbackOnly();
                                                            return false;
                                                        }
                                                        /*-------房源成交状态变成"签约中"-----end------------*/
                                                        return true;
                                                    }else{
                                                        status.setRollbackOnly();
                                                        return false;
                                                    }
                                                }else{
                                                    status.setRollbackOnly();
                                                    return false;
                                                }
                                            }else{
                                                status.setRollbackOnly();
                                                return false;
                                            }
                                        }else{
                                            status.setRollbackOnly();
                                            return false;
                                        }
                                    }else{
                                        status.setRollbackOnly();
                                        return false;
                                    }
                                }else{
                                    status.setRollbackOnly();
                                    return false;
                                }
                            }else{
                                status.setRollbackOnly();
                                return false;
                            }
                        }else{
                            status.setRollbackOnly();
                            return false;
                        }
                    } catch (DaoException e) {
                        status.setRollbackOnly();
                        LOG.error(ErrorPactConsts.SAVE_PURCHASE_BASE_ERROR, e);
                        throw new PactManagerExcepion(ErrorPactConsts.SAVE_PURCHASE_BASE_ERROR, e);
                    }
                }
            });
        } catch (Exception e) {
            LOG.error(ErrorPactConsts.SAVE_PURCHASE_BASE_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.SAVE_PURCHASE_BASE_ERROR, e);
        }
    }
    @Override
    public void removePurchaseBaseRedis(List<String> redisKeys) {
        try {
            if(null != redisKeys && redisKeys.size() > 0){
                for (String key : redisKeys) {
                    redisClient.del(key);
                }
            }
        } catch (RedisAccessException e) {
            LOG.error(ErrorPactConsts.REDIS_DEL_EROR + JsonUtil.toJson(redisKeys), e);
            throw new PactManagerExcepion(ErrorPactConsts.REDIS_DEL_EROR + JsonUtil.toJson(redisKeys), e);
        }
    }
    @Override
    public int countByDeclareCode(String declareCode) {
        try {
            return purchaseBaseDao.countByDeclareCode(declareCode);
        } catch (DaoException e) {
            LOG.error(ErrorPactConsts.COUNT_PURCHASE_BASE_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.COUNT_PURCHASE_BASE_ERROR, e);
        }
    }
    @Override
    public PageBean<PurchaseBaseVO> listPurchaseBase(PurchaseBaseQuery query) {
        try {
            int count = purchaseBaseDao.countPurchaseBase(query);
            PageBean<PurchaseBaseVO> page = new PageTableBean<PurchaseBaseVO>(query.getPageIndex(), query.getPageSize());
            if (count != 0){
                page.setTotalItem(count);
                int startRow = page.getStartRow();
                if (startRow > 0) {
                    startRow -= 1;
                }
                query.setStartRow(startRow);
                page.addAll(purchaseBaseDao.listPurchaseBase(query));
            }
            return page;
        } catch (DaoException e) {
            LOG.error(ErrorPactConsts.LIST_PURCHASE_BASE_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.LIST_PURCHASE_BASE_ERROR, e);
        }
    }
    @Override
    public PurchaseBaseVO getPurchaseBaseDetail(String purchaseCode) {
        try {
            PurchaseBaseVO purchaseBase = purchaseBaseDao.getPurchaseBase(purchaseCode);
            if(null != purchaseBase){
                purchaseBase.setCommonBelongerList(commonBelongerDao.listCommonBelonger(purchaseCode));
                purchaseBase.setCommonCostRuleList(commonCostRuleDao.listCommonCostRule(purchaseCode));
                purchaseBase.setCommonPicList(commonPicDao.listCommonPic(purchaseCode));
                purchaseBase.setPurchaseHouseVO(purchaseHouseDao.getPurchaseHouse(purchaseCode));
                purchaseBase.setPurchaseHouseOwnerVO(purchaseHouseOwnerDao.getPurchaseHouseOwner(purchaseCode));
            }
            return purchaseBase;
        } catch (DaoException e) {
            LOG.error(ErrorPactConsts.GET_PURCHASE_BASE_DETAIL_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.GET_PURCHASE_BASE_DETAIL_ERROR, e);
        }
    }
    @Override
    public PurchaseBaseVO getPurchaseBase(String purchaseCode) {
        try {
            return purchaseBaseDao.getPurchaseBase(purchaseCode);
        } catch (DaoException e) {
            LOG.error(ErrorPactConsts.GET_PURCHASE_BASE_DETAIL_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.GET_PURCHASE_BASE_DETAIL_ERROR, e);
        }
    }
    @Override
    public boolean removePurchaseBase(final PurchaseBaseDO purchaseBaseDO) {
        try {
            TransactionTemplate transactionTemplate =  new TransactionTemplate(transactionManager);
            return transactionTemplate.execute(new TransactionCallback<Boolean>() {
                @Override
                public Boolean doInTransaction(TransactionStatus status) {
                    try {
                        // 查询托进房源信息
                        PurchaseHouseVO purchaseHouseVO = purchaseHouseDao.getPurchaseHouse(purchaseBaseDO.getPurchaseCode());
                        // 逻辑删除 托进合同信息
                        purchaseBaseDao.removePurchaseBase(purchaseBaseDO);
                        // 删除合同收支规则
                        commonCostRuleDao.removeCommonCostRule(purchaseBaseDO.getPurchaseCode());
                        // 删除托进合同免租期
                        purchaseRentfreeDao.removePurchaseRentfree(purchaseBaseDO.getPurchaseCode());
                        FinanceReceiptPayDO financeReceiptPayDO = new FinanceReceiptPayDO();
                        financeReceiptPayDO.setIp(purchaseBaseDO.getIp());
                        financeReceiptPayDO.setModifiedName(purchaseBaseDO.getModifiedName());
                        financeReceiptPayDO.setPactCode(purchaseBaseDO.getPurchaseCode());
                        financeReceiptPayDO.setIsDelete(purchaseBaseDO.getIsDelete());
                        // 逻辑删除 收支信息
                        financeReceiptPayDao.removeFinanceReceiptPay(financeReceiptPayDO);
                        // 删除合同图片
                        commonPicDao.removeCommonPic(purchaseBaseDO.getPurchaseCode());
                        // 删除托进合同房源
                        purchaseHouseDao.removePurchaseHouse(purchaseBaseDO.getPurchaseCode());
                        // 删除托进合同业主
                        purchaseHouseOwnerDao.removePurchaseHouseOwner(purchaseBaseDO.getPurchaseCode());
                        // 删除合同责任人
                        commonBelongerDao.removeCommonBelonger(purchaseBaseDO.getPurchaseCode());
                        // 删除合同物品
                        commonGoodsDao.removeCommonGoods(purchaseBaseDO.getPurchaseCode());
                        // 删除合同抄表
                        commonMeterReadDao.removeCommonMeterRead(purchaseBaseDO.getPurchaseCode());
                        
                        /*--------房源状态 由签约中变成未成交----start-----------*/
                        if(null != purchaseHouseVO){
                            ApiResult<Boolean> houseStateFlag = houseRpcService.updateState( purchaseHouseVO.getHouseCode(), StringConsts.BARGAIN_NOT);
                            if(!CommonEnum.REQUEST_SUCCESS.getCode().equals(houseStateFlag.getCode())){
                                LOG.error("同步房源状态 由签约中变成未成交失败,操作对象：" + purchaseHouseVO.getHouseCode());
                                LOG.error("同步同步托进房源数据失败，失败原因：" + JsonUtil.toJson(houseStateFlag));
                                status.setRollbackOnly();
                                return false;
                            }
                        }
                        /*--------房源状态 由签约中变成未成交----end-----------*/
                        return true;
                    } catch (DaoException e) {
                        status.setRollbackOnly();
                        LOG.error(ErrorPactConsts.REMOVE_PURCHASE_BASE_ERROR, e);
                        throw new PactManagerExcepion(ErrorPactConsts.REMOVE_PURCHASE_BASE_ERROR, e);
                    }
                }
            });
        } catch (Exception e) {
            LOG.error(ErrorPactConsts.REMOVE_PURCHASE_BASE_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.REMOVE_PURCHASE_BASE_ERROR, e);
        }
    }
    @Override
    public boolean updateState(final PurchaseBaseDO purchaseBaseDO) {
        try {
            TransactionTemplate transactionTemplate =  new TransactionTemplate(transactionManager);
            return transactionTemplate.execute(new TransactionCallback<Boolean>() {
                @Override
                public Boolean doInTransaction(TransactionStatus status) {
                    try {
                        if(3 == purchaseBaseDO.getState()){// 审核通过
                            int flag = purchaseBaseDao.updateState(purchaseBaseDO);
                            if(flag > 0){
                                // 收支信息生效
                                FinanceReceiptPayDO financeReceiptPayDO = new FinanceReceiptPayDO();
                                financeReceiptPayDO.setIp(purchaseBaseDO.getIp());
                                financeReceiptPayDO.setModifiedName(purchaseBaseDO.getModifiedName());
                                financeReceiptPayDO.setPactCode(purchaseBaseDO.getPurchaseCode());
                                financeReceiptPayDao.updateValidByCode(financeReceiptPayDO);
                                /*-----生成摊销记录-----start---*/
//                                financeAmortizeDao.
                                
                                /*-----生成摊销记录-----end---*/
                                /*-----房源状态签约中变成成交-----start---*/
                                PurchaseHouseVO purchaseHouseVO = purchaseHouseDao.getPurchaseHouse(purchaseBaseDO.getPurchaseCode());
                                ApiResult<Boolean>  houseStateFlag = houseRpcService.updateState(purchaseHouseVO.getHouseCode(), StringConsts.BARGAIN_SUCCESS);
                                if(!CommonEnum.REQUEST_SUCCESS.getCode().equals(houseStateFlag.getCode())){
                                    LOG.error("同步房源状态签约中变成成交失败，操作对象：" + purchaseBaseDO.getHouseCode());
                                    LOG.error("同步托进房源数据失败，失败原因：" + JsonUtil.toJson(houseStateFlag));
                                    status.setRollbackOnly();
                                    return false;
                                }
                                /*-----房源状态签约中变成成交-----end---*/
                                /*-----同步托进房源数据-----start---*/
//                                PurchaseHouseVO purchaseHouse = purchaseHouseDao.getPurchaseHouse(purchaseBaseDO.getPurchaseCode());
//                                PurchaseHouseOwnerVO purchaseHouseOwner = purchaseHouseOwnerDao.getPurchaseHouseOwner(purchaseBaseDO.getPurchaseCode());
                                UpdateHouseRpcParam updateHouseRpcParam = new UpdateHouseRpcParam();
                               
                                PurchaseHouseOwnerVO purchaseHouseOwnerVO = purchaseHouseOwnerDao.getPurchaseHouseOwner(purchaseBaseDO.getPurchaseCode());
                                updateHouseRpcParam = initUpdateHouseRpcParam(purchaseHouseVO,purchaseHouseOwnerVO);
                                ApiResult<PersonalResult> personalResult = personalRpcService.getPersonInterface(purchaseBaseDO.getModifiedName());
                                PersonalResult personalData = personalResult.getData();
                                ApiResult<Boolean> houseFlag = houseRpcService.updateHouse(updateHouseRpcParam, purchaseBaseDO.getModifiedName(), personalData.getEmployeeName(), personalData.getDeptName());
                                if(!CommonEnum.REQUEST_SUCCESS.getCode().equals(houseFlag.getCode())){
                                    LOG.error("同步托进房源数据失败，操作对象：" + JsonUtil.toJson(updateHouseRpcParam));
                                    LOG.error("同步托进房源数据失败，失败原因：" + JsonUtil.toJson(houseFlag));
                                    status.setRollbackOnly();
                                    return false;
                                }
                                
                                /*-----同步托进房源数据-----end---*/
                                return true;
                            }else{
                                status.setRollbackOnly();
                                return false;
                            }
                        }else{// 申请审核、审核驳回
                            int flag = purchaseBaseDao.updateState(purchaseBaseDO);
                            if(flag > 0){
                                return true;
                            }else{
                                status.setRollbackOnly();
                                return false;
                            }
                        }
                        
                    } catch (DaoException e) {
                        status.setRollbackOnly();
                        LOG.error(ErrorPactConsts.UPDATE_PURCHASE_BASE_STATE_ERROR, e);
                        throw new PactManagerExcepion(ErrorPactConsts.UPDATE_PURCHASE_BASE_STATE_ERROR, e);
                    }
                }
                /**
                * @param purchaseHouseOwnerVO 
                * @Title: initUpdateHouseRpcParam
                * @Description: TODO( 初始化房源信息)
                * @author GuoXiaoPeng
                * @param purchaseHouseVO
                * @throws
                 */
                private UpdateHouseRpcParam initUpdateHouseRpcParam(PurchaseHouseVO purchaseHouseVO, PurchaseHouseOwnerVO purchaseHouseOwnerVO) {
                    UpdateHouseRpcParam updateHouseRpcParam = new UpdateHouseRpcParam();
                    updateHouseRpcParam.setHouseCode(purchaseHouseVO.getHouseCode());
                    updateHouseRpcParam.setOwnerName(purchaseHouseOwnerVO.getName());
                    if(StringConsts.MAN.equals(purchaseHouseOwnerVO.getSex())){
                        updateHouseRpcParam.setOwnerSex(1);
                    }else if(StringConsts.WOMAN.equals(purchaseHouseOwnerVO.getSex())){
                        updateHouseRpcParam.setOwnerSex(0);
                    }
                    updateHouseRpcParam.setOwnerTel(purchaseHouseOwnerVO.getTel());
                    updateHouseRpcParam.setSpareTel(purchaseHouseOwnerVO.getSpareTel());
                    updateHouseRpcParam.setWechat(purchaseHouseOwnerVO.getWechat());
                    updateHouseRpcParam.setCurrentFloor(Integer.parseInt(purchaseHouseVO.getCurrentFloor()));
                    updateHouseRpcParam.setAllFloor(Integer.parseInt(purchaseHouseVO.getAllFloor()));
                    updateHouseRpcParam.setRoom(purchaseHouseVO.getRoom());
                    updateHouseRpcParam.setParlour(purchaseHouseVO.getParlour());
                    updateHouseRpcParam.setToilet(purchaseHouseVO.getToilet());
                    updateHouseRpcParam.setKitchen(purchaseHouseVO.getKitchen());
                    updateHouseRpcParam.setBalcony(purchaseHouseVO.getBalcony());
                    updateHouseRpcParam.setBuildAcreage(new BigDecimal(purchaseHouseVO.getBuildAcreage()).toString());
                    updateHouseRpcParam.setUseAcreage(new BigDecimal(purchaseHouseVO.getUseAcreage()).toString());
                    updateHouseRpcParam.setOrientation(purchaseHouseVO.getOrientation());
                    updateHouseRpcParam.setPropertyTel(purchaseHouseVO.getPropertyTel());
                    updateHouseRpcParam.setHouseNature(purchaseHouseVO.getHouseNature());
                    updateHouseRpcParam.setDecoration(purchaseHouseVO.getDecoration());
                    updateHouseRpcParam.setFurniture(purchaseHouseVO.getFurniture());
                    updateHouseRpcParam.setHouseType(purchaseHouseVO.getHouseType());
                    updateHouseRpcParam.setBuildStructure(purchaseHouseVO.getBuildStructure());
                    updateHouseRpcParam.setBuildYear(purchaseHouseVO.getBuildYear());
                    updateHouseRpcParam.setRentPrice(null);
                    updateHouseRpcParam.setRentTime(null);
                    updateHouseRpcParam.setHouseState(null);
                    updateHouseRpcParam.setExpiryTime(null);
                    updateHouseRpcParam.setSource(purchaseHouseVO.getSource());
                    updateHouseRpcParam.setRemark(purchaseHouseVO.getRemark());
                    updateHouseRpcParam.setContactName(null);
                    updateHouseRpcParam.setRelationship(null);
                    updateHouseRpcParam.setContactTel(null);
                    updateHouseRpcParam.setContactWechat(null);
                    updateHouseRpcParam.setCertificateType(purchaseHouseOwnerVO.getCertificateType());
                    updateHouseRpcParam.setBirthday(purchaseHouseOwnerVO.getBirthday());
                    updateHouseRpcParam.setEmail(purchaseHouseOwnerVO.getEmail());
                    updateHouseRpcParam.setAddress(purchaseHouseOwnerVO.getEmail());
                    updateHouseRpcParam.setCertificatePic(purchaseHouseOwnerVO.getCertificatePic());
                    return updateHouseRpcParam;
                }
            });
        } catch (Exception e) {
            LOG.error(ErrorPactConsts.UPDATE_PURCHASE_BASE_STATE_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.UPDATE_PURCHASE_BASE_STATE_ERROR, e);
        }
    }
    @Override
    public PurchaseBaseVO getUpdatePurchaseBase(String purchaseCode) {
        try {
            PurchaseBaseVO purchaseBase = purchaseBaseDao.getPurchaseBase(purchaseCode);
            if(null != purchaseBase){
                purchaseBase.setCommonBelongerList(commonBelongerDao.listCommonBelonger(purchaseCode));
                purchaseBase.setCommonCostRuleList(commonCostRuleDao.listCommonCostRule(purchaseCode));
                purchaseBase.setCommonPicList(commonPicDao.listCommonPic(purchaseCode));
                purchaseBase.setPurchaseRentfreeList(purchaseRentfreeDao.listPurchaseRentfree(purchaseCode));
                purchaseBase.setFinanceReceiptPayList(financeReceiptPayDao.listFinanceReceiptPay(purchaseCode));
            }
            return purchaseBase;
        } catch (DaoException e) {
            LOG.error(ErrorPactConsts.GET_PURCHASE_BASE_DETAIL_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.GET_PURCHASE_BASE_DETAIL_ERROR, e);
        }
    }
    @Override
    public boolean updatePurchaseBase(final PurchaseBaseDO purchaseBaseDO, final List<CommonCostRuleDO> commonCostRuleDOList, final List<PurchaseRentfreeDO> purchaseRentfreeDOList, 
            final List<FinanceReceiptPayDO> financeReceiptPayDOList, final List<CommonPicDO> commonPicDOList, final CommonBelongerDO commonBelongerDO) {
        try {
            TransactionTemplate transactionTemplate =  new TransactionTemplate(transactionManager);
            return transactionTemplate.execute(new TransactionCallback<Boolean>() {
                @Override
                public Boolean doInTransaction(TransactionStatus status) {
                    try {
                        // 修改托进合同
                        int purchaseBaseFlag = purchaseBaseDao.updatePurchaseBase(purchaseBaseDO);
                        if(purchaseBaseFlag > 0){
                            // 对收支规则的 增删改
                            Map<String, Object> commonCostRuleMap = new HashMap<>();
                            commonCostRuleMap.put("pactCode", purchaseBaseDO.getPurchaseCode());
                            commonCostRuleMap.put("commonCostRuleList", commonCostRuleDOList);
                            commonCostRuleDao.removeNotCommonCostRule(commonCostRuleMap);// 删除
                            if(null != commonCostRuleDOList && commonCostRuleDOList.size() > 0){
                                commonCostRuleDao.updateCommonCostRule(commonCostRuleDOList);// 修改
                                List<CommonCostRuleDO> commonCostRuleDOs = new ArrayList<>();
                                for (CommonCostRuleDO commonCostRuleDO : commonCostRuleDOList) {
                                    if(null == commonCostRuleDO.getId() || !(commonCostRuleDO.getId() > 0)){
                                        commonCostRuleDOs.add(commonCostRuleDO);
                                    }
                                }
                                if(commonCostRuleDOs.size() >= 0){
                                    commonCostRuleDao.saveCommonCostRule(commonCostRuleDOs);// 添加
                                }
                            }
                            // 对托进合同免租期的 增删改
                            Map<String, Object> purchaseRentfreeMap = new HashMap<>();
                            purchaseRentfreeMap.put("purchaseCode", purchaseBaseDO.getPurchaseCode());
                            purchaseRentfreeMap.put("purchaseRentfreeList", purchaseRentfreeDOList);
                            purchaseRentfreeDao.removeNotPurchaseRentfree(purchaseRentfreeMap);
                            if(null != purchaseRentfreeDOList && purchaseRentfreeDOList.size() > 0){
                                purchaseRentfreeDao.updatePurchaseRentfree(purchaseRentfreeDOList);
                                List<PurchaseRentfreeDO> purchaseRentfreeDOs = new ArrayList<>();
                                for (PurchaseRentfreeDO purchaseRentfreeDO : purchaseRentfreeDOList) {
                                    if(null == purchaseRentfreeDO.getId() || !(purchaseRentfreeDO.getId() > 0)){
                                        purchaseRentfreeDOs.add(purchaseRentfreeDO);
                                    }
                                }
                                if(purchaseRentfreeDOs.size() > 0){
                                    purchaseRentfreeDao.savePurchaseRentfree(purchaseRentfreeDOs);
                                }
                            }
                            // 对收支的 增删改
                            Map<String, Object> financeReceiptPayMap = new HashMap<>();
                            financeReceiptPayMap.put("pactCode", purchaseBaseDO.getPurchaseCode());
                            financeReceiptPayMap.put("financeReceiptPayList", financeReceiptPayDOList);
                            financeReceiptPayMap.put("modifiedName", purchaseBaseDO.getModifiedName());
                            financeReceiptPayMap.put("ip", purchaseBaseDO.getIp());
                            financeReceiptPayDao.removeNotFinanceReceiptPay(financeReceiptPayMap);
                            if(null != financeReceiptPayDOList && financeReceiptPayDOList.size() > 0){
                                financeReceiptPayDao.updateFinanceReceiptPay(financeReceiptPayDOList);
                                List<FinanceReceiptPayDO> financeReceiptPayDOs = new ArrayList<>();
                                for (FinanceReceiptPayDO financeReceiptPayDO : financeReceiptPayDOList) {
                                    if(null == financeReceiptPayDO.getId() || !(financeReceiptPayDO.getId() > 0)){
                                        financeReceiptPayDOs.add(financeReceiptPayDO);
                                    }
                                }
                                if(financeReceiptPayDOs.size() > 0){
                                    financeReceiptPayDao.saveFinanceReceiptPay(financeReceiptPayDOs);
                                }
                            }
                            // 对图片的 增删改
                            Map<String, Object> commonPicMap = new HashMap<>();
                            commonPicMap.put("pactCode", purchaseBaseDO.getPurchaseCode());
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
                            // 修改签约人
                            commonBelongerDao.updateCommonBelonger(commonBelongerDO);
                            return true;
                        }else{
                            status.setRollbackOnly();
                            return false;
                        }
                    } catch (DaoException e) {
                        status.setRollbackOnly();
                        LOG.error(ErrorPactConsts.UPDATE_PURCHASE_BASE_ERROR, e);
                        throw new PactManagerExcepion(ErrorPactConsts.UPDATE_PURCHASE_BASE_ERROR, e);
                    }
                }
            });
        } catch (Exception e) {
            LOG.error(ErrorPactConsts.UPDATE_PURCHASE_BASE_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.UPDATE_PURCHASE_BASE_ERROR, e);
        }
    }
    
    @Override
    public List<PurchaseBaseVO> listPurchaseBaseByParam(PurchaseBaseQuery purchaseBaseQuery) {
        try {
            return purchaseBaseDao.listPurchaseBaseByParam(purchaseBaseQuery);
        } catch (DaoException e) {
            LOG.error(ErrorPactConsts.LIST_PURCHASE_BASE_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.LIST_PURCHASE_BASE_ERROR, e);
        }
    }
    @Override
    public boolean updateStandard(PurchaseBaseDO purchaseBaseDO) {
        try {
            purchaseBaseDao.updateStandard(purchaseBaseDO);
            return true;
        } catch (DaoException e) {
            LOG.error(ErrorPactConsts.UPDATE_PURCHASEBASE_STANDARD, e);
            throw new PactManagerExcepion(ErrorPactConsts.UPDATE_PURCHASEBASE_STANDARD, e);
        }
    }
    @Override
    public boolean getPermissions(PurchaseBaseQuery query) {
        try {
            PurchaseBaseVO purchaseBaseVO =  purchaseBaseDao.getPermissions(query);
            if(null == purchaseBaseVO){
                return false;
            }
            return  true;
        } catch (DaoException e) {
            LOG.error(ErrorPactConsts.QUERY_PURCHASEBASE_PERMISSIONS_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.QUERY_PURCHASEBASE_PERMISSIONS_ERROR, e);
        }
    }
    
    @Override
    public List<PurchaseBaseVO> listUnsubmitted(PurchaseBaseQuery purchaseBaseQuery) {
        try {
            return purchaseBaseDao.listUnsubmitted(purchaseBaseQuery);
        } catch (Exception e) {
            LOG.error(ErrorPactConsts.LIST_UNSUBMITTED_PURCHASE_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.LIST_UNSUBMITTED_PURCHASE_ERROR, e);
        }
    }
    @Override
    public List<PurchaseBaseVO> listUnReview(PurchaseBaseQuery purchaseBaseQuery) {
        try {
            return purchaseBaseDao.listUnReview(purchaseBaseQuery);
        } catch (Exception e) {
            LOG.error(ErrorPactConsts.LIST_UNREVIEW_PURCHASE_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.LIST_UNREVIEW_PURCHASE_ERROR, e);
        }
    }
    @Override
    public List<PurchaseBaseVO> listUnCreateRoom() {
        try {
            return purchaseBaseDao.listUnCreateRoom();
        } catch (Exception e) {
            LOG.error(ErrorPactConsts.LIST_UNCREATEROOM_PURCHASE_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.LIST_UNCREATEROOM_PURCHASE_ERROR, e);
        }
    }
    @Override
    public List<PurchaseBaseVO> listRejectUnApply(PurchaseBaseQuery purchaseBaseQuery) {
        try {
            return purchaseBaseDao.listRejectUnApply();
        } catch (Exception e) {
            LOG.error(ErrorPactConsts.LIST_REJECT_UNAPPLY_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.LIST_REJECT_UNAPPLY_ERROR, e);
        }
    }
    
    @Override
    public List<PurchaseBaseVO> listPurchaseExpir() {
        try {
            return purchaseBaseDao.listPurchaseExpir();
        } catch (Exception e) {
            LOG.error(ErrorPactConsts.LIST_PURCHASE_EXPIR_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.LIST_PURCHASE_EXPIR_ERROR, e);
        }
    }
    /**************get/set*****************/
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
    public PurchaseBaseDao getPurchaseBaseDao() {
        return purchaseBaseDao;
    }
    public void setPurchaseBaseDao(PurchaseBaseDao purchaseBaseDao) {
        this.purchaseBaseDao = purchaseBaseDao;
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
    public PurchaseHouseDao getPurchaseHouseDao() {
        return purchaseHouseDao;
    }
    public void setPurchaseHouseDao(PurchaseHouseDao purchaseHouseDao) {
        this.purchaseHouseDao = purchaseHouseDao;
    }
    public PurchaseHouseOwnerDao getPurchaseHouseOwnerDao() {
        return purchaseHouseOwnerDao;
    }
    public void setPurchaseHouseOwnerDao(PurchaseHouseOwnerDao purchaseHouseOwnerDao) {
        this.purchaseHouseOwnerDao = purchaseHouseOwnerDao;
    }
    public PurchaseRentfreeDao getPurchaseRentfreeDao() {
        return purchaseRentfreeDao;
    }
    public void setPurchaseRentfreeDao(PurchaseRentfreeDao purchaseRentfreeDao) {
        this.purchaseRentfreeDao = purchaseRentfreeDao;
    }
    public FinanceAmortizeDao getFinanceAmortizeDao() {
        return financeAmortizeDao;
    }
    public void setFinanceAmortizeDao(FinanceAmortizeDao financeAmortizeDao) {
        this.financeAmortizeDao = financeAmortizeDao;
    }
    public RocketmqClient getRocketmqClient() {
        return rocketmqClient;
    }
    public void setRocketmqClient(RocketmqClient rocketmqClient) {
        this.rocketmqClient = rocketmqClient;
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
   
}
