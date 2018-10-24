package com.young.pact.service.purchasehouse.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.tools.common.util.convert.BeanUtils;
import com.young.common.dict.CommonEnum;
import com.young.common.domain.ApiResult;
import com.young.pact.api.domain.param.rest.purchasehouse.PurchaseHouseParam;
import com.young.pact.api.domain.result.rest.purchasehouse.PurchaseHouseResult;
import com.young.pact.api.service.rest.purchasehouse.PurchaseHouseRestService;
import com.young.pact.common.constant.NormalConstant;
import com.young.pact.common.constant.SerializeTypeConts;
import com.young.pact.common.constant.StringConsts;
import com.young.pact.common.dict.PactCommonEnum;
import com.young.pact.common.exception.PactManagerExcepion;
import com.young.pact.domain.purchasebase.PurchaseBaseVO;
import com.young.pact.domain.purchasehouse.PurchaseHouseDO;
import com.young.pact.domain.purchasehouse.PurchaseHouseVO;
import com.young.pact.manager.purchasebase.PurchaseBaseManager;
import com.young.pact.manager.purchasehouse.PurchaseHouseManager;
import com.young.pact.rpc.operatelog.OperateLogRpc;
/**
 * 
* @ClassName: PurchaseHouseRestServiceImpl
* @Description: TODO( 托进合同房源信息)
* @author HeZeMin
* @date 2018年8月1日 下午2:57:00
*
 */
@Service("purchaseHouseRestService")
public class PurchaseHouseRestServiceImpl implements PurchaseHouseRestService {
    /**********************声明区***************************/
    private static final Log LOG = LogFactory.getLog(PurchaseHouseRestServiceImpl.class);
    private PurchaseHouseManager purchaseHouseManager;
    private PurchaseBaseManager purchaseBaseManager;
    private OperateLogRpc operateLogRpc;
    /**********************方法区***************************/
    @Override
    public ApiResult<String> saveHouseRedis(PurchaseHouseParam param) {
        ApiResult<String> result = new ApiResult<>();
        if(null == param ){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        try {
            PurchaseHouseDO purchaseHouseDO = new PurchaseHouseDO();
            BeanUtils.copyProperties(param, purchaseHouseDO);
            StringBuilder sb = new StringBuilder();
            if(StringUtils.isNotBlank(param.getDeclareCode())){
                sb.append(purchaseHouseDO.getDeclareCode()).append(StringConsts.UNDERLINE)
                .append(StringConsts.HOUSE).append(StringConsts.UNDERLINE).append(purchaseHouseDO.getCreateName());
            }else{
                sb.append(purchaseHouseDO.getHouseCode()).append(StringConsts.UNDERLINE)
                .append(StringConsts.HOUSE).append(StringConsts.UNDERLINE).append(purchaseHouseDO.getCreateName());
            }
            purchaseHouseManager.saveHouseRedis(purchaseHouseDO, sb.toString());
            result.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
            result.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
            result.setData(sb.toString());
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
    public ApiResult<PurchaseHouseResult> getHouseRedis(String redisKey) {
        ApiResult<PurchaseHouseResult> result = new ApiResult<>();
        if(StringUtils.isBlank(redisKey)){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        try {
            PurchaseHouseDO purchaseHouseDO = purchaseHouseManager.getHouseRedis(redisKey);
            if(null != purchaseHouseDO){
                PurchaseHouseResult purchaseHouseResult = new PurchaseHouseResult();
                BeanUtils.copyProperties(purchaseHouseDO, purchaseHouseResult);
                result.setData(purchaseHouseResult);
            }
            result.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
            result.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
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
    public ApiResult<PurchaseHouseResult> getHouseByPurchaseCode(String purchaseCode) {
        ApiResult<PurchaseHouseResult> result = new ApiResult<>();
        if(StringUtils.isBlank(purchaseCode)){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        try {
            PurchaseHouseVO purchaseHouseVO = purchaseHouseManager.getPurchaseHouse(purchaseCode);
            if(null != purchaseHouseVO){
                PurchaseHouseResult purchaseHouseResult = new PurchaseHouseResult();
                BeanUtils.copyProperties(purchaseHouseVO, purchaseHouseResult);
                
                result.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
                result.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
                result.setData(purchaseHouseResult);
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
    public ApiResult<Boolean> updatePurchaseHouse(PurchaseHouseParam param) {
        ApiResult<Boolean> result = new ApiResult<>();
        if(null == param || StringUtils.isBlank(param.getPurchaseCode())){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        try {
            PurchaseBaseVO purchaseBaseVO = purchaseBaseManager.getPurchaseBase(param.getPurchaseCode());
            if(null != purchaseBaseVO){
                // 状态 草稿和驳回 才可以修改
                if(1 == purchaseBaseVO.getState() || 4 == purchaseBaseVO.getState()){
                    PurchaseHouseDO purchaseHouseDO = new PurchaseHouseDO();
                    BeanUtils.copyProperties(param, purchaseHouseDO);
                    boolean flag = purchaseHouseManager.updatePurchaseHouse(purchaseHouseDO);
                    if(flag){
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put(NormalConstant.SERVICETYPE, SerializeTypeConts.PURCHASE);
                        map.put(NormalConstant.SERVICECODE, purchaseHouseDO.getHouseCode());
                        map.put(NormalConstant.OPERATETYPE, StringConsts.UPDATE);
                        map.put(NormalConstant.OPERATEPIN, purchaseHouseDO.getModifiedName());
                        map.put(NormalConstant.OPERATECONTENT, StringConsts.UPDATE_DATA);
                        operateLogRpc.saveOperateLog(map);
                        result.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
                        result.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
                        result.setData(flag);
                    }else{
                        result.setCode(CommonEnum.REQUEST_FAIL.getCode());
                        result.setMessage(CommonEnum.REQUEST_FAIL.getMessage());
                    }
                }else{// 不是 待审核 不可以修改
                    result.setCode(PactCommonEnum.PURCHASE_BASE_STATE_UPDATE_ERROR.getCode());
                    result.setMessage(PactCommonEnum.PURCHASE_BASE_STATE_UPDATE_ERROR.getMessage());
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

    /**********************get/set***************************/

    public PurchaseHouseManager getPurchaseHouseManager() {
        return purchaseHouseManager;
    }

    public void setPurchaseHouseManager(PurchaseHouseManager purchaseHouseManager) {
        this.purchaseHouseManager = purchaseHouseManager;
    }

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
