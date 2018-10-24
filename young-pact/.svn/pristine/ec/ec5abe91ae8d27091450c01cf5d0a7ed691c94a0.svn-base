package com.young.pact.service.purchasebase.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.tools.common.util.convert.BeanUtils;
import com.young.common.dict.CommonEnum;
import com.young.common.domain.ApiResult;
import com.young.pact.api.domain.param.rpc.purchasebase.PurchaseBaseParam;
import com.young.pact.api.domain.result.rest.purchasehouse.PurchaseHouseOwnerResult;
import com.young.pact.api.domain.result.rest.purchasehouse.PurchaseHouseResult;
import com.young.pact.api.domain.result.rpc.purchasebase.PurchaseBaseResult;
import com.young.pact.api.service.rpc.purchasebase.PurchaseBaseRpcService;
import com.young.pact.common.dict.PactCommonEnum;
import com.young.pact.common.exception.PactManagerExcepion;
import com.young.pact.domain.purchasebase.PurchaseBaseDO;
import com.young.pact.domain.purchasebase.PurchaseBaseQuery;
import com.young.pact.domain.purchasebase.PurchaseBaseVO;
import com.young.pact.manager.purchasebase.PurchaseBaseManager;
import com.young.pact.rpc.operatelog.OperateLogRpc;
/**
 * 
* @ClassName: PurchaseBaseRpcServiceImpl
* @Description: TODO( 托进合同)
* @author HeZeMin
* @date 2018年8月15日 上午11:37:45
*
 */
@Service("purchaseBaseRpcService")
public class PurchaseBaseRpcServiceImpl implements PurchaseBaseRpcService {
    /*******************声明区**************************/
    private static final Log LOG = LogFactory.getLog(PurchaseBaseRpcServiceImpl.class);
    private PurchaseBaseManager purchaseBaseManager;
    private OperateLogRpc operateLogRpc;
    /*******************方法区**************************/
    
    @Override
    public ApiResult<PurchaseBaseResult> getPurchaseBaseByCode(String purchaseCode) {
        ApiResult<PurchaseBaseResult> result = new ApiResult<>();
        if(StringUtils.isBlank(purchaseCode)){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        try {
            PurchaseBaseVO purchaseBaseVO = purchaseBaseManager.getPurchaseBaseDetail(purchaseCode);
            if(null != purchaseBaseVO){
                PurchaseBaseResult purchaseBaseResult = new PurchaseBaseResult();
                BeanUtils.copyProperties(purchaseBaseVO, purchaseBaseResult);
                PurchaseHouseResult purchaseHouseResult = new PurchaseHouseResult();
                BeanUtils.copyProperties(purchaseBaseVO.getPurchaseHouseVO(), purchaseHouseResult);
                purchaseBaseResult.setPurchaseHouseResult(purchaseHouseResult);
                PurchaseHouseOwnerResult purchaseHouseOwnerResult = new PurchaseHouseOwnerResult();
                BeanUtils.copyProperties(purchaseBaseVO.getPurchaseHouseOwnerVO(), purchaseHouseOwnerResult);
                purchaseBaseResult.setPurchaseHouseOwnerResult(purchaseHouseOwnerResult);
                result.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
                result.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
                result.setData(purchaseBaseResult);
            }else{// 托进合同编码无效
                result.setCode(PactCommonEnum.PURCHASE_CODE_INVALID.getCode());
                result.setMessage(PactCommonEnum.PURCHASE_CODE_INVALID.getMessage());
            }
        } catch (PactManagerExcepion e) {
            LOG.error(CommonEnum.SYSTEM_EXCEPTION.getMessage(), e);
            result.setCode(CommonEnum.SYSTEM_EXCEPTION.getCode());
            result.setMessage(CommonEnum.SYSTEM_EXCEPTION.getMessage());
        } catch (Exception e) {
            LOG.error(CommonEnum.SYSTEM_EXCEPTION.getMessage(), e);
            result.setCode(CommonEnum.SYSTEM_EXCEPTION.getCode());
            result.setMessage(CommonEnum.SYSTEM_EXCEPTION.getMessage());
        }
        return result;
    }

    @Override
    public ApiResult<Boolean> updateStandard(PurchaseBaseParam param) {
        ApiResult<Boolean> result = new ApiResult<>();
        if(null == param || StringUtils.isBlank(param.getPurchaseCode())){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        try {
            PurchaseBaseVO purchaseBaseVO = purchaseBaseManager.getPurchaseBase(param.getPurchaseCode());
            if(null != purchaseBaseVO){
                PurchaseBaseDO purchaseBaseDO = new PurchaseBaseDO();
                BeanUtils.copyProperties(param, purchaseBaseDO);
                purchaseBaseDO.setIsStandard(1);// 已标准化
                boolean flag = purchaseBaseManager.updateStandard(purchaseBaseDO);
                if(flag){
                    result.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
                    result.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
                    result.setData(flag);
                }else{
                    result.setCode(CommonEnum.REQUEST_FAIL.getCode());
                    result.setMessage(CommonEnum.REQUEST_FAIL.getMessage());
                }
            }else{// 托进合同编码无效
                result.setCode(PactCommonEnum.PURCHASE_CODE_INVALID.getCode());
                result.setMessage(PactCommonEnum.PURCHASE_CODE_INVALID.getMessage());
            }
        } catch (PactManagerExcepion e) {
            LOG.error(CommonEnum.SYSTEM_EXCEPTION.getMessage(), e);
            result.setCode(CommonEnum.SYSTEM_EXCEPTION.getCode());
            result.setMessage(CommonEnum.SYSTEM_EXCEPTION.getMessage());
        } catch (Exception e) {
            LOG.error(CommonEnum.SYSTEM_EXCEPTION.getMessage(), e);
            result.setCode(CommonEnum.SYSTEM_EXCEPTION.getCode());
            result.setMessage(CommonEnum.SYSTEM_EXCEPTION.getMessage());
        }
        return result;
    }
    
    @Override
    public ApiResult<List<PurchaseBaseResult>> listPurchaseBase(PurchaseBaseParam param) {
        ApiResult<List<PurchaseBaseResult>> result = new ApiResult<>();
        try {
            if(null == param || null == param.getTerminationState()){
                result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
                result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
                return result;
            }
            PurchaseBaseQuery purchaseBaseQuery = new PurchaseBaseQuery();
            BeanUtils.copyProperties(param, purchaseBaseQuery);
            List<PurchaseBaseVO> list = purchaseBaseManager.listPurchaseBaseByParam(purchaseBaseQuery);
            List<PurchaseBaseResult> purchaseBaseResults = new ArrayList<>();
            BeanUtils.copyListProperties(list, purchaseBaseResults, PurchaseBaseResult.class);
            result.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
            result.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
            result.setData(purchaseBaseResults);
        } catch (Exception e) {
            LOG.error(CommonEnum.SYSTEM_EXCEPTION.getMessage(), e);
            result.setCode(CommonEnum.SYSTEM_EXCEPTION.getCode());
            result.setMessage(CommonEnum.SYSTEM_EXCEPTION.getMessage());
        }
        return result;
    }
    
    /*******************get/set**************************/

    public PurchaseBaseManager getPurchaseBaseManager() {
        return purchaseBaseManager;
    }

    public void setPurchaseBaseManager(PurchaseBaseManager purchaseBaseManager) {
        this.purchaseBaseManager = purchaseBaseManager;
    }

    public OperateLogRpc getOperateLogRpc() {
        return operateLogRpc;
    }

    public void setOperateLogRpc(OperateLogRpc operateLogRpc) {
        this.operateLogRpc = operateLogRpc;
    }

   

}
