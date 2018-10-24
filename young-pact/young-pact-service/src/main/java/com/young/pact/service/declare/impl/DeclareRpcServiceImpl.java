package com.young.pact.service.declare.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.LogFactoryImpl;
import org.springframework.stereotype.Service;

import com.young.common.dict.CommonEnum;
import com.young.common.domain.ApiResult;
import com.young.pact.api.service.rpc.declare.DeclareRpcService;
import com.young.pact.common.exception.PactManagerExcepion;
import com.young.pact.manager.declare.DeclareManager;

/**
 * @描述 : 申报
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月15日 下午6:27:53
 */
@Service("declareRpcService")
public class DeclareRpcServiceImpl implements DeclareRpcService {
    private static Log LOG = LogFactoryImpl.getLog(DeclareRpcServiceImpl.class);
    private DeclareManager declareManager;
    @Override
    public ApiResult<Integer> countUnauditedDeclare(String houseCode) {
        ApiResult<Integer> result = new ApiResult<>();
        try {
            Integer count = declareManager.countUnauditedDeclare(houseCode);
            result.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
            result.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
            result.setData(count);
        }   catch (PactManagerExcepion e) {
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
    public DeclareManager getDeclareManager() {
        return declareManager;
    }
    public void setDeclareManager(DeclareManager declareManager) {
        this.declareManager = declareManager;
    }
}
