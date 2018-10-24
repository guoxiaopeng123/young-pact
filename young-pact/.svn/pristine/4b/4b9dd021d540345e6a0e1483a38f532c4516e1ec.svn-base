package com.young.pact.manager.commonbelonger.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.tools.common.util.json.JsonUtil;
import com.young.common.dict.CommonEnum;
import com.young.common.domain.ApiResult;
import com.young.common.exception.DaoException;
import com.young.pact.common.constant.ErrorPactConsts;
import com.young.pact.common.exception.PactManagerExcepion;
import com.young.pact.dao.commonbelonger.CommonBelongerDao;
import com.young.pact.domain.commonbelonger.CommonBelongerDO;
import com.young.pact.domain.commonbelonger.CommonBelongerQuery;
import com.young.pact.domain.commonbelonger.CommonBelongerVO;
import com.young.pact.manager.commonbelonger.CommonBelongerManager;
import com.young.product.api.domain.param.rpc.house.HouseParam;
import com.young.product.api.service.rpc.house.HouseRpcService;
/**
 * 
* @ClassName: CommonBelongerManagerImpl
* @Description: TODO( 合同责任人)
* @author HeZeMin
* @date 2018年8月5日 下午3:58:53
*
 */
@Component("commonBelongerManager")
public class CommonBelongerManagerImpl implements CommonBelongerManager {
    /**************声明区*****************/
    private static final Log LOG = LogFactory.getLog(CommonBelongerManagerImpl.class);
    private PlatformTransactionManager transactionManager;
    private CommonBelongerDao commonBelongerDao;
    private HouseRpcService houseRoomRpcService;
    /**************方法区*****************/

    @Override
    public List<CommonBelongerVO> listCommonBelonger(String pactCode) {
        try {
            return commonBelongerDao.listCommonBelonger(pactCode);
        } catch (DaoException e) {
            LOG.error(ErrorPactConsts.LIST_COMMON_BELONGER_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.LIST_COMMON_BELONGER_ERROR, e);
        }
    }

    @Override
    public boolean updateCommonBelonger(CommonBelongerDO commonBelongerDO) {
        try {
            int flag = commonBelongerDao.updateCommonBelonger(commonBelongerDO);
            if(flag > 0){
                return true;
            }
            return false;
        } catch (DaoException e) {
            LOG.error(ErrorPactConsts.UPDATE_COMMON_BELONGER_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.UPDATE_COMMON_BELONGER_ERROR, e);
        }
    }
    @Override
    public boolean saveCommonBelonger(List<CommonBelongerDO> commonBelongerDOs) {
        try {
            int flag = commonBelongerDao.saveCommonBelonger(commonBelongerDOs);
            if(flag > 0){
                return true;
            }
            return false;
        } catch (DaoException e) {
            LOG.error(ErrorPactConsts.SAVE_COMMON_BELONGER_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.SAVE_COMMON_BELONGER_ERROR, e);
        }
    }
    
    @Override
    public boolean updateCommonBelongers(CommonBelongerDO commonBelongerDO) {
        try {
            int flag = commonBelongerDao.updateCommonBelongers(commonBelongerDO);
            if(flag > 0){
                return true;
            }
            return false;
        } catch (DaoException e) {
            LOG.error(ErrorPactConsts.UPDATE_COMMON_BELONGER_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.UPDATE_COMMON_BELONGER_ERROR, e);
        }
    }
    
    @Override
    public CommonBelongerVO getBelongersByParam(CommonBelongerQuery commonBelongerQuery) {
        try {
            return commonBelongerDao.getBelongerByParam(commonBelongerQuery);
        } catch (DaoException e) {
            LOG.error(ErrorPactConsts.QUERY_COMMON_BELONGER_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.QUERY_COMMON_BELONGER_ERROR, e);
        }
    }
    
    @Override
    public boolean updateCommonBelonger(final List<CommonBelongerDO> addBelongerDOs, final List<CommonBelongerDO> updateBelongerDOs) {
        TransactionTemplate transactionTemplate = getDataSourceTransactionManager();
        return transactionTemplate.execute(new TransactionCallback<Boolean>() {

            @Override
            public Boolean doInTransaction(TransactionStatus status) {
                try {
                    if(null != addBelongerDOs && addBelongerDOs.size() > 0){
                        int addCout = commonBelongerDao.batchSaveCommonBelonger(addBelongerDOs);
                        if(addCout <= 0){
                            status.setRollbackOnly();
                            return false;
                        }
                    }
                    if(null != updateBelongerDOs && updateBelongerDOs.size() > 0){
                        int updateCount = commonBelongerDao.batchUpdateCommonBelonger(updateBelongerDOs);
                        if(updateCount < 0){
                            status.setRollbackOnly();
                            return false;
                        }
                    }
                    return true;
                } catch (Exception e) {
                    status.setRollbackOnly();
                    LOG.error(ErrorPactConsts.SAVE_COMMON_BELONGER_ERROR, e);
                    throw new PactManagerExcepion(ErrorPactConsts.SAVE_COMMON_BELONGER_ERROR, e);
                }
            }
        });
    }
    
    @Override
    public boolean updateSyncOperateBelonger(final CommonBelongerDO commonBelongerDO) {
        TransactionTemplate transactionTemplate = getDataSourceTransactionManager();
        return transactionTemplate.execute(new TransactionCallback<Boolean>() {
            @SuppressWarnings("rawtypes")
            @Override
            public Boolean doInTransaction(TransactionStatus status) {
                try {
                    int flag = commonBelongerDao.updateCommonBelonger(commonBelongerDO);
                    if(flag <= 0){
                        status.setRollbackOnly();
                        return false;
                    }
                    /*-------根据托进合同编码修改房间的责任人管家------start----------*/
                    HouseParam houseParam = new HouseParam();
                    List<String> pactCodeList = new ArrayList<>();
                    pactCodeList.add(commonBelongerDO.getPactCode());
                    houseParam.setPactCodeList(pactCodeList);
                    houseParam.setStewardPin(commonBelongerDO.getUserPin());
                    houseParam.setStewardDeptCode(commonBelongerDO.getDeptCode());
                    ApiResult operateResult  = houseRoomRpcService.updateByPactCodeList(houseParam);
                    if(!CommonEnum.REQUEST_SUCCESS.getCode().equals(operateResult.getCode())){
                        status.setRollbackOnly();
                        return false;
                    }
                    /*-------根据托进合同编码修改房间的责任人管家------end----------*/
                    return true;
                } catch (DaoException e) {
                    status.setRollbackOnly();
                    return false;
                }
            }
        });
    }
    
    @Override
    public boolean transferPurchase(final List<CommonBelongerDO> addBelongerDOs, final List<CommonBelongerDO> updateBelongerDOs, final HouseParam houseParam) {
        TransactionTemplate transactionTemplate = getDataSourceTransactionManager();
        return transactionTemplate.execute(new TransactionCallback<Boolean>() {

            @SuppressWarnings("rawtypes")
            @Override
            public Boolean doInTransaction(TransactionStatus status) {
                try {
                    if(null!=addBelongerDOs&&addBelongerDOs.size()>0){
                        int addCout = commonBelongerDao.batchSaveCommonBelonger(addBelongerDOs);
                        if(addCout <= 0){
                            status.setRollbackOnly();
                            return false;
                        }
                    }
                    int updateCount = commonBelongerDao.batchUpdateCommonBelonger(updateBelongerDOs);
                    if(updateCount<=0){
                        status.setRollbackOnly();
                        return false;
                    }
                    /*-------根据托进合同编码修改房间的责任人管家------start----------*/
                    ApiResult operateResult  = houseRoomRpcService.updateByPactCodeList(houseParam);
                    if(!CommonEnum.REQUEST_SUCCESS.getCode().equals(operateResult.getCode())){
                        LOG.error("转移托进合同失败，失败原因：根据托进合同编码修改房间的责任人管家失败，操作对象：" + JsonUtil.toJson(houseParam));
                        status.setRollbackOnly();
                        return false;
                    }
                    /*-------根据托进合同编码修改房间的责任人管家------end----------*/
                    return true;
                } catch (Exception e) {
                    status.setRollbackOnly();
                    LOG.error(ErrorPactConsts.SAVE_COMMON_BELONGER_ERROR, e);
                    throw new PactManagerExcepion(ErrorPactConsts.SAVE_COMMON_BELONGER_ERROR, e);
                }
            }
        });
    }
    private TransactionTemplate getDataSourceTransactionManager() {
        return new TransactionTemplate(transactionManager);
    }

    /**************get/set*****************/

    public CommonBelongerDao getCommonBelongerDao() {
        return commonBelongerDao;
    }

    public void setCommonBelongerDao(CommonBelongerDao commonBelongerDao) {
        this.commonBelongerDao = commonBelongerDao;
    }

    public PlatformTransactionManager getTransactionManager() {
        return transactionManager;
    }

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public HouseRpcService getHouseRoomRpcService() {
        return houseRoomRpcService;
    }

    public void setHouseRoomRpcService(HouseRpcService houseRoomRpcService) {
        this.houseRoomRpcService = houseRoomRpcService;
    }


    
}
