package com.young.pact.service.rentbase.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.LogFactoryImpl;
import org.springframework.stereotype.Service;

import com.tools.common.util.convert.BeanUtils;
import com.young.common.dict.CommonEnum;
import com.young.common.domain.ApiResult;
import com.young.pact.api.domain.param.rpc.rentbase.RentBaseParam;
import com.young.pact.api.domain.result.rpc.rentbase.RentBaseResult;
import com.young.pact.api.service.rpc.rentbase.RentBaseRpcService;
import com.young.pact.domain.rentbase.RentBaseQuery;
import com.young.pact.domain.rentbase.RentBaseVO;
import com.young.pact.manager.rentbase.RentBaseManager;

/**
 * @描述 : 托出合同
 * @创建者 : guoxiaopeng
 * @创建时间 : 2018年9月15日 下午4:47:21
 */
@Service("rentBaseRpcService")
public class RentBaseRpcServiceImpl implements RentBaseRpcService {
    private static Log LOG = LogFactoryImpl.getLog(RentBaseRpcServiceImpl.class);
    private RentBaseManager rentBaseManager;
    @Override
    public ApiResult<List<RentBaseResult>> listRentBase(RentBaseParam param) {
        ApiResult<List<RentBaseResult>> result = new ApiResult<>();
        List<RentBaseResult> rentBaseResults = new ArrayList<>();
        try {
            RentBaseQuery rentBaseQuery = new RentBaseQuery();
            BeanUtils.copyProperties(param, rentBaseQuery);
            List<RentBaseVO> list = rentBaseManager.listRentBaseByParam(rentBaseQuery);
            BeanUtils.copyListProperties(list, rentBaseResults, RentBaseResult.class);
            result.setData(rentBaseResults);
            result.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
            result.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
        } catch (Exception e) {
            LOG.error(CommonEnum.SYSTEM_EXCEPTION.getMessage(), e);
            result.setCode(CommonEnum.REQUEST_FAIL.getCode());
            result.setMessage(CommonEnum.REQUEST_FAIL.getMessage());
        }
        return result;
    }
    public RentBaseManager getRentBaseManager() {
        return rentBaseManager;
    }
    public void setRentBaseManager(RentBaseManager rentBaseManager) {
        this.rentBaseManager = rentBaseManager;
    }

}
