package com.young.pact.service.purchasehouse.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.tools.common.util.convert.BeanUtils;
import com.young.common.dict.CommonEnum;
import com.young.common.domain.ApiResult;
import com.young.pact.api.domain.param.rest.purchasehouse.PurchaseHouseOwnerParam;
import com.young.pact.api.domain.result.rest.purchasehouse.PurchaseHouseOwnerResult;
import com.young.pact.api.service.rest.purchasehouse.PurchaseHouseOwnerRestService;
import com.young.pact.common.constant.StringConsts;
import com.young.pact.common.dict.PactCommonEnum;
import com.young.pact.common.exception.PactManagerExcepion;
import com.young.pact.domain.purchasebase.PurchaseBaseVO;
import com.young.pact.domain.purchasehouse.PurchaseHouseOwnerDO;
import com.young.pact.domain.purchasehouse.PurchaseHouseOwnerVO;
import com.young.pact.manager.purchasebase.PurchaseBaseManager;
import com.young.pact.manager.purchasehouse.PurchaseHouseOwnerManager;
/**
 * 
* @ClassName: PurchaseHouseOwnerRestServiceImpl
* @Description: TODO( 托进合同业主信息)
* @author HeZeMin
* @date 2018年8月1日 下午2:56:36
*
 */
@Service("purchaseHouseOwnerRestService")
public class PurchaseHouseOwnerRestServiceImpl implements PurchaseHouseOwnerRestService {
    /**********************声明区***************************/
    private static final Log LOG = LogFactory.getLog(PurchaseHouseOwnerRestServiceImpl.class);
    private PurchaseHouseOwnerManager purchaseHouseOwnerManager;
    private PurchaseBaseManager purchaseBaseManager;
    /**********************方法区***************************/
    @Override
    public ApiResult<String> saveHouseOwnerRedis(PurchaseHouseOwnerParam param) {
        ApiResult<String> result = new ApiResult<>();
        if(null == param){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        if(StringUtils.isBlank(param.getDeclareCode())&&StringUtils.isBlank(param.getHouseCode())){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        try {
            PurchaseHouseOwnerDO purchaseHouseOwnerDO = new PurchaseHouseOwnerDO();
            BeanUtils.copyProperties(param, purchaseHouseOwnerDO);
            StringBuilder sb = new StringBuilder();
            if(StringUtils.isNotBlank(param.getDeclareCode())){
                sb.append(purchaseHouseOwnerDO.getDeclareCode()).append(StringConsts.UNDERLINE)
                .append(StringConsts.HOUSE_OWNER).append(StringConsts.UNDERLINE).append(purchaseHouseOwnerDO.getCreateName());
            }else if(StringUtils.isNotBlank(param.getHouseCode())){
                sb.append(param.getHouseCode()).append(StringConsts.UNDERLINE)
                .append(StringConsts.HOUSE_OWNER).append(StringConsts.UNDERLINE).append(purchaseHouseOwnerDO.getCreateName());
            }
            purchaseHouseOwnerManager.saveHouseOwnerRedis(purchaseHouseOwnerDO, sb.toString());
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
    public ApiResult<PurchaseHouseOwnerResult> getHouseOwnerRedis(String redisKey) {
        ApiResult<PurchaseHouseOwnerResult> result = new ApiResult<>();
        if(StringUtils.isBlank(redisKey)){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        try {
            PurchaseHouseOwnerDO purchaseHouseOwnerDO = purchaseHouseOwnerManager.getHouseOwnerRedis(redisKey);
            if(null != purchaseHouseOwnerDO){
                PurchaseHouseOwnerResult purchaseHouseOwnerResult = new PurchaseHouseOwnerResult();
                BeanUtils.copyProperties(purchaseHouseOwnerDO, purchaseHouseOwnerResult);
                result.setData(purchaseHouseOwnerResult);
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
    public ApiResult<PurchaseHouseOwnerResult> getOwnerByPurchaseCode(String purchaseCode) {
        ApiResult<PurchaseHouseOwnerResult> result = new ApiResult<>();
        if(StringUtils.isBlank(purchaseCode)){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        try {
            PurchaseHouseOwnerVO purchaseHouseOwnerVO = purchaseHouseOwnerManager.getPurchaseHouseOwner(purchaseCode);
            if(null != purchaseHouseOwnerVO){
                PurchaseHouseOwnerResult purchaseHouseOwnerResult = new PurchaseHouseOwnerResult();
                BeanUtils.copyProperties(purchaseHouseOwnerVO, purchaseHouseOwnerResult);
                
                result.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
                result.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
                result.setData(purchaseHouseOwnerResult);
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
    public ApiResult<Boolean> updatePurchaseHouseOwner(PurchaseHouseOwnerParam param) {
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
                    PurchaseHouseOwnerDO purchaseHouseOwnerDO = new PurchaseHouseOwnerDO();
                    BeanUtils.copyProperties(param, purchaseHouseOwnerDO);
                    boolean flag = purchaseHouseOwnerManager.updatePurchaseHouseOwner(purchaseHouseOwnerDO);
                    if(flag){
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

    public PurchaseHouseOwnerManager getPurchaseHouseOwnerManager() {
        return purchaseHouseOwnerManager;
    }

    public void setPurchaseHouseOwnerManager(PurchaseHouseOwnerManager purchaseHouseOwnerManager) {
        this.purchaseHouseOwnerManager = purchaseHouseOwnerManager;
    }

    public PurchaseBaseManager getPurchaseBaseManager() {
        return purchaseBaseManager;
    }

    public void setPurchaseBaseManager(PurchaseBaseManager purchaseBaseManager) {
        this.purchaseBaseManager = purchaseBaseManager;
    }

}
