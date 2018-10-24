package com.young.pact.manager.purchasetermination.impl;

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

import com.young.common.dict.CommonEnum;
import com.young.common.domain.ApiResult;
import com.young.common.exception.DaoException;
import com.young.common.page.PageBean;
import com.young.common.page.PageTableBean;
import com.young.house.api.service.rpc.house.HouseRpcService;
import com.young.pact.common.constant.ErrorPactConsts;
import com.young.pact.common.constant.StringConsts;
import com.young.pact.common.exception.PactManagerExcepion;
import com.young.pact.dao.commonbelonger.CommonBelongerDao;
import com.young.pact.dao.commongoods.CommonGoodsDao;
import com.young.pact.dao.commongoods.CommonGoodsPicDao;
import com.young.pact.dao.commonmeterread.CommonMeterReadDao;
import com.young.pact.dao.commonmeterread.CommonMeterReadPicDao;
import com.young.pact.dao.commonpic.CommonPicDao;
import com.young.pact.dao.financereceiptpay.FinanceReceiptPayDao;
import com.young.pact.dao.purchasebase.PurchaseBaseDao;
import com.young.pact.dao.purchasehouse.PurchaseHouseDao;
import com.young.pact.dao.purchasetermination.PurchaseTerminationDao;
import com.young.pact.domain.commonbelonger.CommonBelongerDO;
import com.young.pact.domain.commongoods.CommonGoodsDO;
import com.young.pact.domain.commongoods.CommonGoodsPicDO;
import com.young.pact.domain.commonmeterread.CommonMeterReadDO;
import com.young.pact.domain.commonmeterread.CommonMeterReadPicDO;
import com.young.pact.domain.commonpic.CommonPicDO;
import com.young.pact.domain.financereceiptpay.FinanceReceiptPayDO;
import com.young.pact.domain.purchasebase.PurchaseBaseDO;
import com.young.pact.domain.purchasehouse.PurchaseHouseVO;
import com.young.pact.domain.purchasetermination.PurchaseTerminationBaseVO;
import com.young.pact.domain.purchasetermination.PurchaseTerminationDO;
import com.young.pact.domain.purchasetermination.PurchaseTerminationEditVO;
import com.young.pact.domain.purchasetermination.PurchaseTerminationQuery;
import com.young.pact.domain.purchasetermination.PurchaseTerminationVO;
import com.young.pact.manager.purchasetermination.PurchaseTerminationManager;
import com.young.product.api.domain.param.rpc.room.RoomParam;
import com.young.product.api.service.rpc.room.RoomRpcService;

/**
 * 
* @ClassName: PurchaseTerminatiorManagerImpl
* @Description: 托进解约协议
* @author SunYiXuan
* @date 2018年8月9日 下午4:26:22
*
 */
@Component("purchaseTerminationManager")
public class PurchaseTerminationManagerImpl implements PurchaseTerminationManager{

    private static final Log LOG = LogFactory.getLog(PurchaseTerminationManagerImpl.class);
    private PlatformTransactionManager transactionManager;
    private PurchaseTerminationDao purchaseTerminationDao;
    private FinanceReceiptPayDao financeReceiptPayDao;
    private CommonPicDao commonPicDao;
    private CommonBelongerDao commonBelongerDao;
    private CommonGoodsDao commonGoodsDao;
    private CommonGoodsPicDao commonGoodsPicDao;
    private CommonMeterReadDao commonMeterReadDao;
    private CommonMeterReadPicDao commonMeterReadPicDao;
    private PurchaseBaseDao purchaseBaseDao;
    private PurchaseHouseDao purchaseHouseDao;
    private HouseRpcService houseRpcService;
    private RoomRpcService roomRpcService;
    @Override
    public int countByPurchaseCode(String purchaseCode) {
        try {
            return purchaseTerminationDao.countByPurchaseCode(purchaseCode);
        } catch (DaoException e) {
            LOG.error(ErrorPactConsts.COUNT_PURCHASE_TERMINATION_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.COUNT_PURCHASE_TERMINATION_ERROR, e);
        }
    }


    @Override
    public Boolean savePurchaseTermination(final PurchaseTerminationDO purchaseTerminationDO,
            final List<FinanceReceiptPayDO> financeReceiptPayList, final List<CommonPicDO> commonPicList, 
            final List<CommonMeterReadDO> commonMeterReadList, final List<CommonGoodsDO> commonGoodsList, 
            final List<CommonBelongerDO> commonBelongerDOList) {
        try {
            TransactionTemplate transactionTemplate =  new TransactionTemplate(transactionManager);
            return transactionTemplate.execute(new TransactionCallback<Boolean>() {

                @Override
                public Boolean doInTransaction(TransactionStatus status) {
                    try {
                        //保存解约协议信息
                        Long terminationId = purchaseTerminationDao.savePurchaseTermination(purchaseTerminationDO);
                        if(terminationId > 0){
                            //保存收支
                            int receiptPayFlag = financeReceiptPayDao.saveFinanceReceiptPay(financeReceiptPayList);
                            if(receiptPayFlag >= 0){
                                //保存协议照片
                                int commonPicFlag = commonPicDao.saveCommonPic(commonPicList);
                                if(commonPicFlag > 0){
                                    //保存负责人信息
                                    int belongerFlag = commonBelongerDao.saveCommonBelonger(commonBelongerDOList);
                                    if(belongerFlag > 0){
                                        //保存物品
                                        for (CommonGoodsDO commonGoodsDO : commonGoodsList) {
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
                                        //保存抄表
                                        for (CommonMeterReadDO commonMeterReadDO : commonMeterReadList) {
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
                    } catch (DaoException e) {
                        status.setRollbackOnly();
                        LOG.error(ErrorPactConsts.SAVE_PURCHASE_TERMINATION_ERROR, e);
                        throw new PactManagerExcepion(ErrorPactConsts.SAVE_PURCHASE_TERMINATION_ERROR, e);
                    }
                }
                
            });
        } catch (Exception e) {
            LOG.error(ErrorPactConsts.SAVE_PURCHASE_TERMINATION_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.SAVE_PURCHASE_TERMINATION_ERROR, e);
        }
    }
    
    
    
    @Override
    public PageBean<PurchaseTerminationVO> listPurchaseTermination(PurchaseTerminationQuery query) {
        try {
            int count = purchaseTerminationDao.countPurchaseTermination(query);
            PageBean<PurchaseTerminationVO> page = new PageTableBean<PurchaseTerminationVO>(query.getPageIndex(), query.getPageSize());
            if(count > 0){
                page.setTotalItem(count);
                int startRow = page.getStartRow();
                if (startRow > 0) {
                    startRow -= 1;
                }
                query.setStartRow(startRow);
                page.addAll(purchaseTerminationDao.listPurchaseTermination(query));
            }
            return page;
        } catch (DaoException e) {
            LOG.error(ErrorPactConsts.LIST_PURCHASE_TERMINATION_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.LIST_PURCHASE_TERMINATION_ERROR, e);
        }
    }
    
    
    
    @Override
    public PurchaseTerminationBaseVO getPurchaseTerminationBase(String terminationCode) {
        try {
            PurchaseTerminationBaseVO terminationBaseVO = purchaseTerminationDao.getPurchaseTerminationBase(terminationCode);
            if(null != terminationBaseVO){
                terminationBaseVO.setCommonPicList(commonPicDao.listCommonPic(terminationCode));
            }
            return terminationBaseVO;
        } catch (DaoException e) {
            LOG.error(ErrorPactConsts.GET_PURCHASE_TERMINATION_BASE_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.GET_PURCHASE_TERMINATION_BASE_ERROR, e);
        }
    }
    
    @Override
    public boolean getPermissions(PurchaseTerminationQuery query) {
        try {
            PurchaseTerminationBaseVO terminationBaseVO = purchaseTerminationDao.getPermissions(query);
            if(null != terminationBaseVO){
                return true;
            }
            return false;
        } catch (DaoException e) {
            LOG.error(ErrorPactConsts.GET_PURCHASE_TERMINATION_BASE_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.GET_PURCHASE_TERMINATION_BASE_ERROR, e);
        }
    }
    @Override
    public PurchaseTerminationEditVO getPurchaseTerminationEdit(String terminationCode) {
        try {
            PurchaseTerminationEditVO terminationEditVO = purchaseTerminationDao.getPurchaseTerminationEdit(terminationCode);
            if(null != terminationEditVO){
                terminationEditVO.setFinanceReceiptPayList(financeReceiptPayDao.listFinanceReceiptPay(terminationCode));
                terminationEditVO.setCommonPicList(commonPicDao.listCommonPic(terminationCode));
            }
            return terminationEditVO;
        } catch (DaoException e) {
            LOG.error(ErrorPactConsts.GET_PURCHASE_TERMINATION_EDIT_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.GET_PURCHASE_TERMINATION_EDIT_ERROR, e);
        }
    }
    
    
    
    @Override
    public PurchaseTerminationVO getPurchaseTerminarion(String terminationCode) {
        try {
            return purchaseTerminationDao.getPurchaseTermination(terminationCode);
        } catch (DaoException e) {
            LOG.error(ErrorPactConsts.GET_PURCHASE_TERMINATION_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.GET_PURCHASE_TERMINATION_ERROR, e);
        }
    }
    
    
    @Override
    public Boolean updateTermination(final PurchaseTerminationDO terminationDO, final List<FinanceReceiptPayDO> financeReceiptPayDOList, 
           final List<CommonPicDO> commonPicDOList, final CommonBelongerDO commonBelongerDO) {
        try {
            TransactionTemplate transactionTemplate =  new TransactionTemplate(transactionManager);
            return transactionTemplate.execute(new TransactionCallback<Boolean>() {

                @Override
                public Boolean doInTransaction(TransactionStatus status) {
                    try {
                        //协议信息
                        int updateTerminationId = purchaseTerminationDao.updatePurchaseTermination(terminationDO);
                        if(updateTerminationId > 0){
                            //收支
                            Map<String, Object> financeReceiptPayMap = new HashMap<>();
                            financeReceiptPayMap.put("pactCode", terminationDO.getTerminationCode());
                            financeReceiptPayMap.put("financeReceiptPayList", financeReceiptPayDOList);
                            financeReceiptPayMap.put("modifiedName", terminationDO.getModifiedName());
                            financeReceiptPayMap.put("ip", terminationDO.getIp());
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
                            
                            //协议照片
                            Map<String, Object> commonPicMap = new HashMap<>();
                            commonPicMap.put("pactCode", terminationDO.getTerminationCode());
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
                            
                            //签约管家和责任人
                            commonBelongerDao.updateCommonBelongers(commonBelongerDO);
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
    public Boolean removePurchaseTermination(final PurchaseTerminationDO terminationDO) {
        try {
            TransactionTemplate transactionTemplate =  new TransactionTemplate(transactionManager);
            return transactionTemplate.execute(new TransactionCallback<Boolean>() {

                @Override
                public Boolean doInTransaction(TransactionStatus status) {
                    try {
                        //逻辑删除拖进合同解约协议
                        purchaseTerminationDao.removePurchaseTermination(terminationDO);
                        // 逻辑删除 收支信息
                        FinanceReceiptPayDO financeReceiptPayDO = new FinanceReceiptPayDO();
                        financeReceiptPayDO.setIp(terminationDO.getIp());
                        financeReceiptPayDO.setModifiedName(terminationDO.getModifiedName());
                        financeReceiptPayDO.setPactCode(terminationDO.getTerminationCode());
                        financeReceiptPayDO.setIsDelete(terminationDO.getIsDelete());
                        financeReceiptPayDao.removeFinanceReceiptPay(financeReceiptPayDO);
                        //删除协议图片
                        commonPicDao.removeCommonPic(terminationDO.getTerminationCode());
                        //删除责任人
                        commonBelongerDao.removeCommonBelonger(terminationDO.getTerminationCode());
                        //删除合同物品
                        commonGoodsDao.removeCommonGoods(terminationDO.getTerminationCode());
                        //删除合同抄表
                        commonMeterReadDao.removeCommonMeterRead(terminationDO.getTerminationCode());
                        return true;
                    } catch (DaoException e) {
                        status.setRollbackOnly();
                        LOG.error(ErrorPactConsts.REMOVE_PURCHASE_TERMINATION_ERROR, e);
                        throw new PactManagerExcepion(ErrorPactConsts.REMOVE_PURCHASE_TERMINATION_ERROR, e);
                    }
                }
                
            });
        } catch (Exception e) {
            LOG.error(ErrorPactConsts.REMOVE_PURCHASE_TERMINATION_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.REMOVE_PURCHASE_TERMINATION_ERROR, e);
        }
    }
    
    
    
    @Override
    public Boolean updateState(final PurchaseTerminationDO terminationDO) {
        try {
            TransactionTemplate transactionTemplate =  new TransactionTemplate(transactionManager);
            return transactionTemplate.execute(new TransactionCallback<Boolean>() {

                @SuppressWarnings("rawtypes")
                @Override
                public Boolean doInTransaction(TransactionStatus status) {
                    try {
                        if(3 == terminationDO.getState()){//审核通过
                            int flag = purchaseTerminationDao.updateState(terminationDO);
                            if(flag > 0){
                                PurchaseTerminationVO purchaseTerminationVO = purchaseTerminationDao.getPurchaseTermination(terminationDO.getTerminationCode());
                                if(null != purchaseTerminationVO){
                                    PurchaseBaseDO purchaseBaseDO = new PurchaseBaseDO();
                                    purchaseBaseDO.setPurchaseCode(purchaseTerminationVO.getPurchaseCode());
                                    if(1 == purchaseTerminationVO.getType()){// 到期解约
                                        purchaseBaseDO.setExpireState(2);
                                        purchaseBaseDO.setTerminationState(2);
                                    }else if(2 == purchaseTerminationVO.getType()){// 违约解约
                                        purchaseBaseDO.setTerminationState(3);
                                    }
                                    purchaseBaseDO.setModifiedName(terminationDO.getModifiedName());
                                    purchaseBaseDO.setIp(terminationDO.getIp());
                                    int purchaseBaseFlag = purchaseBaseDao.updateTerminationState(purchaseBaseDO);
                                    if(purchaseBaseFlag > 0){
                                        FinanceReceiptPayDO oldFinanceReceiptPayDO = new FinanceReceiptPayDO();
                                        oldFinanceReceiptPayDO.setModifiedName(terminationDO.getModifiedName());
                                        oldFinanceReceiptPayDO.setIp(terminationDO.getIp());
                                        oldFinanceReceiptPayDO.setPactCode(purchaseTerminationVO.getPurchaseCode());
                                        oldFinanceReceiptPayDO.setIsDelete(1);// 逻辑删除
                                        oldFinanceReceiptPayDO.setCostTime(purchaseTerminationVO.getPactEndDate());
                                        financeReceiptPayDao.delFinanceReceiptPay(oldFinanceReceiptPayDO);
                                        
                                        /*-----对摊销的操作-----start---*/
                                        
                                        /*-----对摊销的操作-----end---*/
                                        /*-----房源状态已成交变成解约-----start---*/
                                        PurchaseHouseVO purchaseHouse = purchaseHouseDao.getPurchaseHouse(purchaseBaseDO.getPurchaseCode());
                                        ApiResult<Boolean> houseResult = houseRpcService.updateState(purchaseHouse.getHouseCode(), StringConsts.DISSOLUTION);
                                        if(!CommonEnum.REQUEST_SUCCESS.getCode().equals(houseResult.getCode())){
                                            status.setRollbackOnly();
                                            return false;
                                        }
                                        /*-----房源状态已成交变成解约-----end---*/
                                        /*-----房间状态、上线状态变成无效-----start---*/
                                        RoomParam roomParam = new RoomParam();
                                        roomParam.setPactCode(purchaseTerminationVO.getPurchaseCode());
                                        roomParam.setModifiedName(terminationDO.getModifiedName());
                                        roomParam.setIp(terminationDO.getIp());
                                        roomParam.setStockState(3);
                                        roomParam.setIsOnline(4);
                                        ApiResult roomResult = roomRpcService.updateByPactCode(roomParam);
                                        if(!CommonEnum.REQUEST_SUCCESS.getCode().equals(roomResult.getCode())){
                                            status.setRollbackOnly();
                                            return false;
                                        }    
                                        /*-----房间状态、上线状态变成无效-----end---*/
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
                        }else{//申请审核、驳回
                            int flag = purchaseTerminationDao.updateState(terminationDO);
                            if(flag > 0){
                                return true;
                            }else{
                                status.setRollbackOnly();
                                return false;
                            }
                        }
                    } catch (DaoException e) {
                        status.setRollbackOnly();
                        LOG.error(ErrorPactConsts.UPDATE_PURCHASE_TERMINATION_STATE_ERROR, e);
                        throw new PactManagerExcepion(ErrorPactConsts.UPDATE_PURCHASE_TERMINATION_STATE_ERROR, e);
                    }
                }
                
            });
        } catch (Exception e) {
            LOG.error(ErrorPactConsts.UPDATE_PURCHASE_TERMINATION_STATE_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.UPDATE_PURCHASE_TERMINATION_STATE_ERROR, e);
        }
    }
    
    
    @Override
    public List<PurchaseTerminationVO> listPurTerminationByParam(PurchaseTerminationQuery purchaseBaseQuery) {
        try {
            return purchaseTerminationDao.listPurTerminationByParam(purchaseBaseQuery);
        } catch (Exception e) {
            LOG.error(ErrorPactConsts.LIST_PURCHASE_TERMINATION_BYPARAM_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.LIST_PURCHASE_TERMINATION_BYPARAM_ERROR, e);
        }
    }

    @Override
    public List<PurchaseTerminationVO> listUnReview(PurchaseTerminationQuery purchaseTerminationQuery) {
        try {
            return purchaseTerminationDao.listUnReview(purchaseTerminationQuery);
        } catch (Exception e) {
            LOG.error(ErrorPactConsts.LIST_UNREVIEW_TERMINATION_BYPARAM_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.LIST_UNREVIEW_TERMINATION_BYPARAM_ERROR, e);
        }
    }
    
    @Override
    public List<PurchaseTerminationVO> listUnsubmitted(PurchaseTerminationQuery purchaseTerminationQuery) {
        try {
            return purchaseTerminationDao.listUnsubmitted(purchaseTerminationQuery);
        } catch (Exception e) {
            LOG.error(ErrorPactConsts.LIST_UNSUBMITTED_TERMINATION_BYPARAM_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.LIST_UNSUBMITTED_TERMINATION_BYPARAM_ERROR, e);
        }
    }
    public PurchaseTerminationDao getPurchaseTerminationDao() {
        return purchaseTerminationDao;
    }

    public void setPurchaseTerminationDao(PurchaseTerminationDao purchaseTerminationDao) {
        this.purchaseTerminationDao = purchaseTerminationDao;
    }

    public PlatformTransactionManager getTransactionManager() {
        return transactionManager;
    }


    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }


    public FinanceReceiptPayDao getFinanceReceiptPayDao() {
        return financeReceiptPayDao;
    }


    public void setFinanceReceiptPayDao(FinanceReceiptPayDao financeReceiptPayDao) {
        this.financeReceiptPayDao = financeReceiptPayDao;
    }


    public CommonPicDao getCommonPicDao() {
        return commonPicDao;
    }


    public void setCommonPicDao(CommonPicDao commonPicDao) {
        this.commonPicDao = commonPicDao;
    }


    public CommonBelongerDao getCommonBelongerDao() {
        return commonBelongerDao;
    }


    public void setCommonBelongerDao(CommonBelongerDao commonBelongerDao) {
        this.commonBelongerDao = commonBelongerDao;
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


    public PurchaseBaseDao getPurchaseBaseDao() {
        return purchaseBaseDao;
    }


    public void setPurchaseBaseDao(PurchaseBaseDao purchaseBaseDao) {
        this.purchaseBaseDao = purchaseBaseDao;
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


    public RoomRpcService getRoomRpcService() {
        return roomRpcService;
    }


    public void setRoomRpcService(RoomRpcService roomRpcService) {
        this.roomRpcService = roomRpcService;
    }




    
}
