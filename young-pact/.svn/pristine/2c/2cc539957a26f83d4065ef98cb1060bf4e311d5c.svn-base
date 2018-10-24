package com.young.pact.service.commonbelonger.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.tools.common.util.convert.BeanUtils;
import com.young.common.dict.CommonEnum;
import com.young.common.domain.ApiResult;
import com.young.pact.api.domain.param.rpc.commonbelonger.CommonBelongerParam;
import com.young.pact.api.service.rpc.commonbelonger.CommonBelongerRpcService;
import com.young.pact.common.constant.StringConsts;
import com.young.pact.common.dict.PactCommonEnum;
import com.young.pact.common.exception.PactManagerExcepion;
import com.young.pact.domain.commonbelonger.CommonBelongerDO;
import com.young.pact.domain.commonbelonger.CommonBelongerQuery;
import com.young.pact.domain.commonbelonger.CommonBelongerVO;
import com.young.pact.manager.commonbelonger.CommonBelongerManager;
import com.young.pact.rpc.remind.RemindRpc;
/**
 * 
* @ClassName: CommonBelongerRpcServiceImpl
* @Description: TODO( 合同责任人)
* @author HeZeMin
* @date 2018年8月15日 下午2:20:04
*
 */
@Service("commonBelongerRpcService")
public class CommonBelongerRpcServiceImpl implements CommonBelongerRpcService {
    /*******************声明区**************************/
    private static final Log LOG = LogFactory.getLog(CommonBelongerRpcServiceImpl.class);
    private CommonBelongerManager commonBelongerManager;
    private Map<String,String> remindMap;
    private RemindRpc remindRpc;
    /*******************方法区**************************/
    @Override
    public ApiResult<Boolean> saveCommonBelonger(List<CommonBelongerParam> params) {
        ApiResult<Boolean> result = new ApiResult<>();
        List<CommonBelongerDO> addBelongerDOs = new ArrayList<>();
        List<CommonBelongerDO> updateBelongerDOs = new ArrayList<>();
        if(null == params || !(params.size() > 0)){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        try {
            List<CommonBelongerDO> commonBelongerDOs = new ArrayList<>();
            BeanUtils.copyListProperties(params, commonBelongerDOs, CommonBelongerDO.class);
            for(CommonBelongerDO commonBelongerDO:commonBelongerDOs){
                if(StringConsts.BELONGER_SERVICE.equals(commonBelongerDO.getUserRole())){
                    CommonBelongerQuery commonBelongerQuery = new CommonBelongerQuery();
                    BeanUtils.copyProperties(commonBelongerDO, commonBelongerQuery);
                    CommonBelongerVO afterBelongerVO = commonBelongerManager.getBelongersByParam(commonBelongerQuery);
                    if(null != afterBelongerVO){
                        commonBelongerDO.setId(afterBelongerVO.getId());
                        updateBelongerDOs.add(commonBelongerDO);
                    }else{
                        addBelongerDOs.add(commonBelongerDO);
                    }
                }else{
                    addBelongerDOs.add(commonBelongerDO);
                }
                
            }
            boolean flag = commonBelongerManager.updateCommonBelonger(addBelongerDOs, updateBelongerDOs);
            if(flag){
                result.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
                result.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
                result.setData(flag);
            }else{
                result.setCode(CommonEnum.REQUEST_FAIL.getCode());
                result.setMessage(CommonEnum.REQUEST_FAIL.getMessage());
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
    public ApiResult<Boolean> updateCommonBelonger(CommonBelongerParam param) {
        ApiResult<Boolean> result = new ApiResult<>();
        if(null == param){
            result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
            result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
            return result;
        }
        try {
            CommonBelongerDO commonBelongerDO = new CommonBelongerDO();
            BeanUtils.copyProperties(param, commonBelongerDO);
            boolean flag = commonBelongerManager.updateCommonBelongers(commonBelongerDO);
            if(flag){
                result.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
                result.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
                result.setData(flag);
            }else{
                result.setCode(CommonEnum.REQUEST_FAIL.getCode());
                result.setMessage(CommonEnum.REQUEST_FAIL.getMessage());
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
    

    /******************get/set**************************/
    public CommonBelongerManager getCommonBelongerManager() {
        return commonBelongerManager;
    }
    public void setCommonBelongerManager(CommonBelongerManager commonBelongerManager) {
        this.commonBelongerManager = commonBelongerManager;
    }

    public Map<String, String> getRemindMap() {
        return remindMap;
    }

    public void setRemindMap(Map<String, String> remindMap) {
        this.remindMap = remindMap;
    }

    public RemindRpc getRemindRpc() {
        return remindRpc;
    }

    public void setRemindRpc(RemindRpc remindRpc) {
        this.remindRpc = remindRpc;
    }
}
